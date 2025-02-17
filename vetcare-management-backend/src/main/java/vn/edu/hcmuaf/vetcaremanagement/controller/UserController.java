package vn.edu.hcmuaf.vetcaremanagement.controller;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import vn.edu.hcmuaf.vetcaremanagement.model.User;
import vn.edu.hcmuaf.vetcaremanagement.service.UserService;
import java.util.List;

@Path("/users")
public class UserController {
    private UserService userService;

    public UserController() {
    }

    @Inject
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllUsers() {
        List<User> users = userService.getAllUsers();
        return Response.status(Response.Status.OK).entity(users).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response createUser(User user) {
        userService.createUser(user);
        return Response.status(Response.Status.CREATED).build();
    }
}
