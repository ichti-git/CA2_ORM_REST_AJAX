package exceptions;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import exception.ErrorMessage;
import javax.servlet.ServletContext;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

//@Provider
public class PersonNotFoundExceptionMapper implements ExceptionMapper<PersonNotFoundException> {

  static Gson gson = new GsonBuilder().setPrettyPrinting().create();
  
  @Context
  ServletContext context;

  @Override
  public Response toResponse(PersonNotFoundException ex) {
    boolean isDebug = context.getInitParameter("debug").toLowerCase().equals("true");
    ErrorMessage em = new ErrorMessage(ex, Response.Status.NOT_FOUND.getStatusCode(), isDebug);
    return Response.status(Response.Status.NOT_FOUND).entity(gson.toJson(em)).type(MediaType.APPLICATION_JSON).build();
            
  }

}
