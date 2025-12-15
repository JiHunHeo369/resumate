package com.resumate.global.aop;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.resumate.common.exception.CommonApiException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Aspect
@Slf4j
@Component
@RequiredArgsConstructor
public class ServiceExceptionAspect {

    @AfterThrowing(pointcut = "execution(* com.resumate.domain..service..*(..))", throwing = "ex")
    public void logServiceException(JoinPoint joinPoint, Exception ex) {
        // 비즈니스 로직 에러는 제외.
        if (ex instanceof CommonApiException) {
            return;
        }

        String methodName = joinPoint.getSignature().toShortString();
        Object[] args = joinPoint.getArgs();
        ObjectMapper mapper = new ObjectMapper();
        StringBuilder sb = new StringBuilder();

        try {
            for (Object arg : args) {
                if (!(arg instanceof HttpServletRequest) && !(arg instanceof HttpServletResponse)) {
                    sb.append(mapper.writeValueAsString(arg));
                    log.error("Exception in Service method: {} Arg: {}",methodName, mapper.writeValueAsString(arg));
                }
            }

//            ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
//
//            if (attr != null) {
//                persistErrorLog(ex, attr.getRequest(), sb.toString(), methodName);
//            }

        } catch (JsonProcessingException e) {
            log.error("Arg: {}", e.getMessage());
        }
    }

//    private void persistErrorLog(Exception ex, HttpServletRequest request, String parameter, String methodName) {
//        try {
//            ErrorLog log = new ErrorLog();
//
//            // 예외 관련
//            log.setExceptionClass(ex.getClass().getName());
//            log.setMessage(StringUtil.truncate(ex.getMessage(), 4000));
//            log.setMethodName(methodName);
//            log.setParameter(parameter);
//
//            // HTTP / request 관련
//            if (request != null) {
//                log.setHttpMethod(request.getMethod());
//                log.setPath(request.getRequestURI());
//                log.setClientIp(HttpRequestUtils.extractClientIp(request));
//                log.setUserAgent(StringUtil.truncate(request.getHeader("User-Agent"), 200));
//                if (request.getHeader("Authorization") != null) {
//                    log.setUserId(Integer.parseInt(jwtProvider.getUserId(request)));
//                }
//            }
//
//            logMapper.insertErrorLog(log);
//        } catch (Exception loggingEx) {
//            loggingEx.printStackTrace();
//        }
//    }
}
