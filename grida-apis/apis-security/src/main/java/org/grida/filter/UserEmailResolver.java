package org.grida.filter;

import org.grida.exception.ApisSecurityException;
import org.grida.util.RequestUserEmail;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;

import static org.grida.exception.ApisSecurityErrorCode.NO_AUTHENTICATED_USER;

public class UserEmailResolver implements HandlerMethodArgumentResolver {

    private static final String USER_EMAIL_ATTRIBUTE_KEY = "userEmail";

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        boolean hasAnnotation = parameter.hasParameterAnnotation(RequestUserEmail.class);
        boolean isStringValue = String.class.isAssignableFrom(parameter.getParameterType());

        return hasAnnotation && isStringValue;
    }

    @Override
    public Object resolveArgument(
            MethodParameter parameter,
            ModelAndViewContainer mavContainer,
            NativeWebRequest webRequest,
            WebDataBinderFactory binderFactory
    ) {
        HttpServletRequest request = (HttpServletRequest) webRequest.getNativeRequest();
        try {
            return request.getAttribute(USER_EMAIL_ATTRIBUTE_KEY);
        } catch (NullPointerException e) {
            throw new ApisSecurityException(NO_AUTHENTICATED_USER);
        }
    }
}
