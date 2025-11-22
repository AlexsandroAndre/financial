package com.financial.report.infrastructure.presentation.version;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.lang.reflect.Method;

public class ApiVersionRequestMappingHandlerMapping extends RequestMappingHandlerMapping {

    private static final Logger log = LoggerFactory.getLogger(ApiVersionRequestMappingHandlerMapping.class);

    @Override
    protected RequestMappingInfo getMappingForMethod(Method method, Class<?> handlerType) {
        RequestMappingInfo info = super.getMappingForMethod(method, handlerType);

        if (info == null) {
            return null;
        }

        log.debug("Found base mapping {} for handler {}.{}", info, handlerType.getSimpleName(), method.getName());

        var apiVersion = AnnotatedElementUtils.findMergedAnnotation(handlerType, ApiVersion.class);

        var baseClassRequestMapping =
                AnnotatedElementUtils.findMergedAnnotation(handlerType.getSuperclass(), RequestMapping.class);

        RequestMappingInfo basePathInfo = null;
        if (baseClassRequestMapping != null && baseClassRequestMapping.value().length > 0) {
            String[] basePaths = baseClassRequestMapping.value();
            basePathInfo = RequestMappingInfo.paths(basePaths).build();
            log.info("Applying base path {} from superclass {} to handler {}.{}",
                    String.join(",", basePaths),
                    handlerType.getSuperclass().getSimpleName(),
                    handlerType.getSimpleName(),
                    method.getName());
        }

        RequestMappingInfo versionInfo = null;
        if (apiVersion != null) {
            String version = apiVersion.value();
            versionInfo = RequestMappingInfo.paths("/" + version).build();
            log.info("Applying API version '{}' to handler {}.{}", version, handlerType.getSimpleName(), method.getName());
        }

        if (versionInfo != null) {
            info = versionInfo.combine(info);
        }
        if (basePathInfo != null) {
            info = basePathInfo.combine(info);
        }

        log.info("Final mapping for {}.{} => {}", handlerType.getSimpleName(), method.getName(), info);

        return info;
    }
}
