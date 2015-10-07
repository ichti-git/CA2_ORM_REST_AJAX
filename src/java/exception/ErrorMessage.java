/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exception;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 *
 * @author ichti
 */
public class ErrorMessage {
    String message;
    String stackTrace;
    
    public ErrorMessage(Throwable ex, boolean debug) {
        this.message = ex.getMessage();
        if(debug){
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            ex.printStackTrace(pw);
            this.stackTrace = sw.toString();
        }
    }
    
    public String getMessage() {
        return message;
    }
    
    public void setMessage(String msg) {
        this.message = msg;
    }
    
    public String getStackTrace() {
        return this.stackTrace;
    }
}
