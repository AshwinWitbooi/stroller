package za.co.ashtech.stroller.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import jakarta.servlet.http.HttpServletRequest;
import za.co.ashtech.stroller.db.entities.StrollTransactionLog;
import za.co.ashtech.stroller.db.repo.StrollTransactionLogRepository;


@Aspect
@Component
public class StrollTransactionLogAspect {
	
    private final HttpServletRequest request;
	
	@Autowired
	private StrollTransactionLogRepository auditTrailRepository;


    public StrollTransactionLogAspect(HttpServletRequest request) {
        this.request = request;
    }
	
    @Pointcut("execution(* za.co.ashtech.stroller.services.*.*(..))")
	public void serviceMethods() {}
    
    String transacionType = null;
    String transacionResult = "Fail";

    @Around("serviceMethods()")
    public Object logAuditTrail(ProceedingJoinPoint joinPoint) throws Throwable {
    	                		 
		transacionType = joinPoint.getSignature().getName();
        String senderId = request.getHeader("SenderID");   
        	
        // continue method execution
        Object result = joinPoint.proceed();
        
        transacionResult = "Success";
        
		auditTrailRepository.save(new StrollTransactionLog(senderId , transacionType, transacionResult));
                    
        return result;

    }    
    
    // Advice that handles exceptions
    @AfterThrowing(pointcut = "serviceMethods()", throwing = "ex")
    public void handleServiceExceptions(JoinPoint joinPoint, Throwable ex) {
		transacionType = joinPoint.getSignature().getName();
        String senderId = request.getHeader("SenderID");
        
        auditTrailRepository.save(new StrollTransactionLog(senderId, transacionType, transacionResult));

    }

}

