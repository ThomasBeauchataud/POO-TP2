package thread.common;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

@Aspect
public class CatchThreadExceptionInterceptor {

    @Around("@annotation(thread.common.CatchThreadException))")
    public Object trace (ProceedingJoinPoint joinPoint) throws Throwable {
        try {
           joinPoint.proceed();
        } catch (Exception e) {
            System.out.println("Fin d'un Thread par Exception");
        }
        return null;
    }

}
