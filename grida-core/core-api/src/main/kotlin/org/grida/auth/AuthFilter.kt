package org.grida.auth

import io.wwan13.wintersecurity.exception.unauthirized.ExpiredJwtTokenException
import io.wwan13.wintersecurity.exception.unauthirized.InvalidJwtTokenException
import io.wwan13.wintersecurity.jwt.TokenClaims
import io.wwan13.wintersecurity.jwt.TokenDecoder
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.grida.error.ExpiredJwtToken
import org.grida.error.GridaException
import org.grida.error.InvalidJwtToken
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter

@Component
class AuthFilter(
    private val tokenDecoder: TokenDecoder
) : OncePerRequestFilter() {
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain,
    ) {
        extractToken(request)?.let {
            val authentication = provideAuthentication(it)
            val securityContextHolder = SecurityContextHolder.getContext()
            securityContextHolder.authentication = authentication
        }
        filterChain.doFilter(request, response)
    }

    private fun extractToken(request: HttpServletRequest): String? {
        val bearerToken = request.getHeader("Authorization")
        return bearerToken?.substring(7)
    }

    private fun provideAuthentication(token: String): UsernamePasswordAuthenticationToken {
        val claims = decodeTokenWithExceptionHandling(token)
        val userId = claims.subject
        val roles = claims.roles.map { SimpleGrantedAuthority(it) }

        return UsernamePasswordAuthenticationToken.authenticated(userId, null, roles)
    }

    private fun decodeTokenWithExceptionHandling(token: String): TokenClaims {
        return try {
            tokenDecoder.decode(token)
        } catch (e: ExpiredJwtTokenException) {
            throw GridaException(ExpiredJwtToken)
        } catch (e: InvalidJwtTokenException) {
            throw GridaException(InvalidJwtToken)
        }
    }
}
