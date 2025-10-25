package za.co.ashtech.stroller.aop;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import za.co.ashtech.stroller.util.StrollerServiceException;

import java.util.Optional;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/*
 * This aspect is used to validate that all request received by API has SenderID header set
 */

@Aspect
@Component
@Slf4j
public class ValidateControllerAspect {

    private final HttpServletRequest request;

    public ValidateControllerAspect(HttpServletRequest request) {
        this.request = request;
    }

    /**
     * Pointcut that matches all public methods in my.api.controller package.
     */
    @Pointcut("execution(public * za.co.ashtech.stroller.controller..*(..))")
    public void controllerMethods() {}

    /**
     * Before any controller method executes, access the HTTP request.
     */
    @Before("controllerMethods()")
    public void beforeControllerCall(JoinPoint joinPoint) {
        String uri = request.getRequestURI();
        String method = request.getMethod();
        String clientIp = request.getRemoteAddr();

        log.info(">>> Incoming request: [{}] {} from IP: {}", method, uri, clientIp);

        // Example: access specific headers
        Optional.ofNullable(request.getHeader("SenderID")).orElseThrow(() -> new StrollerServiceException("Request SenderID header not set."));
    }

    /**
     * Handle exceptions from controller methods (optional).
     */
    @AfterThrowing(pointcut = "controllerMethods()", throwing = "ex")
    public void afterException(JoinPoint joinPoint, Throwable ex) {
        log.error("!!! Exception in {}: {}", joinPoint.getSignature().toShortString(), ex.getMessage());
    }
}

