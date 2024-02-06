package org.grida.util;

import org.grida.exception.ApisSecurityException;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;

import static org.grida.exception.ApisSecurityErrorCode.NO_AUTHENTICATED_USER;

public class ProviderResolver implements HandlerMethodArgumentResolver {

    private static final String PROVIDER_ATTRIBUTE_KEY = "provider";

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        boolean hasAnnotation = parameter.hasParameterAnnotation(RequestProvider.class);
        boolean isLongValue = Long.class.isAssignableFrom(parameter.getParameterType())
                || long.class.isAssignableFrom(parameter.getParameterType());

        return hasAnnotation && isLongValue;
    }

    @Override
    public Object resolveArgument(MethodParameter parameter,
                                  ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest,
                                  WebDataBinderFactory binderFactory) throws Exception {

        HttpServletRequest request = (HttpServletRequest) webRequest.getNativeRequest();
        Object userId = request.getAttribute(PROVIDER_ATTRIBUTE_KEY);
        validateHasUserId(userId);
        return userId;
    }

    private void validateHasUserId(Object userId) {
        if (userId == null) {
            throw new ApisSecurityException(NO_AUTHENTICATED_USER);
        }
    }
}
