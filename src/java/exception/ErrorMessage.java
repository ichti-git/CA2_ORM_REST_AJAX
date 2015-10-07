package exception;

import java.io.PrintWriter;
import java.io.StringWriter;

public class ErrorMessage {

  public ErrorMessage(Throwable ex, int code,boolean debug) {
    this.code = code;
    this.message = ex.getMessage();
    
    if(debug){
      StringWriter sw = new StringWriter();
      ex.printStackTrace(new PrintWriter(sw));
      this.stackTrace = sw.toString();
    }
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  
  private int code;
  private String message;
  private String stackTrace;
}
