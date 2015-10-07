package exception;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javax.servlet.ServletContext;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

//@Provider
public class AllExceptionMapper implements ExceptionMapper<Throwable> {

  static Gson gson = new GsonBuilder().setPrettyPrinting().create();
  
  @Context
  ServletContext context;

  @Override
  public Response toResponse(Throwable ex) {
    boolean isDebug = context.getInitParameter("debug").toLowerCase().equals("true");
    ErrorMessage em = new ErrorMessage(ex, Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(),isDebug);
    em.setMessage("Internal server Error, we are very sorry for the inconvenience");
    return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(gson.toJson(em)).type(MediaType.APPLICATION_JSON).build();
            
  }

}
