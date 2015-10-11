/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exception;

import com.google.gson.JsonSyntaxException;
import com.google.gson.stream.MalformedJsonException;
import deploy.DeploymentConfiguration;
import static exception.PersonNotFoundExceptionMapper.gson;
import javax.servlet.ServletContext;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 *
 * @author ichti
 */
@Provider
public class JsonSyntaxExceptionMapper 
    implements ExceptionMapper<JsonSyntaxException> {
    
    @Context
    ServletContext context;
    
    @Override
    public Response toResponse(JsonSyntaxException e) {
        //boolean debug = true;
        //boolean debug = context.getInitParameter("debug").toLowerCase().equals("true");
        boolean debug = DeploymentConfiguration.DEBUG;
        ErrorMessage err = new ErrorMessage(e, debug);
        err.setMessage("Malformed json given");
        return Response.status(500)
                       .entity(gson.toJson(err))
                       .type(MediaType.APPLICATION_JSON).build();
    }
    
}
