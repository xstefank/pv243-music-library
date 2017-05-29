package cz.muni.fi.pv243.musiclib.rest.endpoint;

import cz.muni.fi.pv243.musiclib.entity.User;
import cz.muni.fi.pv243.musiclib.service.UserService;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


/**
 * @author <a href="mailto:martin.styk@gmail.com">Martin Styk</a>
 */
@Path("user")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserEndpoint {

    @Inject
    private UserService userService;

    @POST
    @Path("/register")
    public Response register(User user) {
        Response.ResponseBuilder builder;
        try {
            User created = userService.create(user);
            builder = Response.ok(created);
        } catch (Exception e) {
            builder = Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage());
        }
        return builder.build();
    }

    @GET
    public Response get() {
        return Response.ok(userService.findAll()).build();
    }

}
