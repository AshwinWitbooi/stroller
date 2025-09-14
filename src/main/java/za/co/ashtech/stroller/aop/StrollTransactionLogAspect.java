package za.co.ashtech.stroller.aop;

import java.util.Optional;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import jakarta.servlet.http.HttpServletRequest;
import za.co.ashtech.stroller.controller.entities.StrollUserCommentRequest;
import za.co.ashtech.stroller.db.entities.StrollTransactionLog;
import za.co.ashtech.stroller.db.repo.StrollTransactionLogRepository;


@Aspect
@Component
public class StrollTransactionLogAspect {
	
	@Autowired
	private StrollTransactionLogRepository auditTrailRepository;
	
//    @Pointcut("execution(* za.co.ashtech.stroller.services.*.*(..)) && !execution(* za.co.ashtech.stroller.services.StrollUserDetailsService.*(..))")
	 @Pointcut("execution(* za.co.ashtech.stroller.services.*.*(..))")
	public void serviceMethods() {}
    
    String userId = null;
    String transacionType = null;
    String transacionResult = "Fail";

    @Around("serviceMethods()")
    public Object logAuditTrail(ProceedingJoinPoint joinPoint) throws Throwable {
    	                		 
		transacionType = joinPoint.getSignature().getName();
		HttpServletRequest request = null;
		ServletRequestAttributes attrs = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
	    if (attrs != null) {
		   request = attrs.getRequest();
		}
	    
	    
		/*
		 * Only extract userId from parameters if ist not a postContact else get from
		 * method args object
		 */
	    if(!transacionType.equalsIgnoreCase("postComment")) {
	    		//Default to integration test
	    		userId = Optional.ofNullable(request.getParameter("userId")).orElse("integration@test");
	    }else {
    		userId = "user-comment";	    	
	    }
	    
	    
        	
        // continue method execution
        Object result = joinPoint.proceed();
        
        transacionResult = "Success";
        
        auditTrailRepository.save(new StrollTransactionLog(userId, transacionType, transacionResult));
                    
        return result;

    }    
    
    // Advice that handles exceptions
    @AfterThrowing(pointcut = "serviceMethods()", throwing = "ex")
    public void handleServiceExceptions(JoinPoint joinPoint, Throwable ex) {
		transacionType = joinPoint.getSignature().getName();
		HttpServletRequest request = null;
		ServletRequestAttributes attrs = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
	    if (attrs != null) {
		   request = attrs.getRequest();
		}

	    userId = request.getParameter("userId");

        
        auditTrailRepository.save(new StrollTransactionLog(userId, transacionType, transacionResult));

    }

}

