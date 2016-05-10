/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flooring.aop;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import java.util.Date;
import org.aspectj.lang.JoinPoint;

/**
 *
 * @author apprentice
 */
public class Logger {
    
    public void logging(JoinPoint jp) throws IOException {
    
        PrintWriter out = new PrintWriter(new FileWriter("logging.txt", true));
        Date time = new java.util.Date();
        out.println(jp.getSignature().getName() + "\t" + time);
        out.flush();
        out.close();
}
}
