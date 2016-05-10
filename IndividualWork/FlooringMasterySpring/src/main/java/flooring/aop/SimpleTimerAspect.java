/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flooring.aop;

import org.aspectj.lang.ProceedingJoinPoint;

/**
 *
 * @author apprentice
 */
public class SimpleTimerAspect {

 // Note 1 - ProceedingJoinPoint parameter
 public Object timeMethod(ProceedingJoinPoint jp) {
 Object ret = null;

 try {
 // Note 2 - start timer
 long start = System.currentTimeMillis();
 // Note 3 - this allows the target method to execute
 ret = jp.proceed();
 // Note 4 - target method has returned, end timer and calc
 // elapsed time
 long end = System.currentTimeMillis();
 System.out.println("++++++++++++++++++++++++++++++++++++++++++");
 System.out.println(jp.getSignature().getName() + " took " +
 (end - start) + " ms");
 System.out.println("++++++++++++++++++++++++++++++++++++++++++");
 } catch (Throwable ex) {
 System.out.println("Exception in SimpleTimerAspect.timeMethod()");
 }
 // Note 5 - return whatever was returned by the target method (see
 // Note 3)
 return ret;
 }
}
