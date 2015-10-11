/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exception;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import deploy.DeploymentConfiguration;
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
public class GeneralExceptionMapper 
    implements ExceptionMapper<Throwable> {
    static Gson gson = new GsonBuilder().setPrettyPrinting().create();
    
    @Context
    ServletContext context;
    
    @Override
    public Response toResponse(Throwable exception) {
        //boolean debug = true;
        //boolean debug = context.getInitParameter("debug").toLowerCase().equals("true");
        boolean debug = DeploymentConfiguration.DEBUG;
        ErrorMessage err = new ErrorMessage(exception, debug);
        err.setMessage("Internal error occured. Sorry for the inconvenience");
        
        return Response.status(500)
                       .entity(gson.toJson(err))
                       .type(MediaType.APPLICATION_JSON).build();
    }

    
}
