/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flooring.controller;


import flooring.ui.ConsoleIO;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author apprentice
 */
public class FlooringMasteryRunner {

    public static void main(String[] args) {
        ConsoleIO cio = new ConsoleIO();
        FlooringController control;
        
        String mode;
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        mode = cio.readString("\"Production\" or \"Test\" Mode: ");
        if (mode.equals(
                "Production")) {
            control = (FlooringController) ctx.getBean("prodStart");
            cio.write("\nEntering Production Mode!\n");
        } else if (mode.equals(
                "Test")) {
            control = (FlooringController) ctx.getBean("testStart");
            cio.write("\nEntering Test Mode!\n");
        } else {
            control = (FlooringController) ctx.getBean("testStart");
            cio.write("\nInput invalid" + "\nEntering Test Mode!\n");
        }
        
        control.run();
    
    
    }
    
}
