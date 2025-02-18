package vn.edu.hcmuaf.vetcaremanagement.controller;

import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import vn.edu.hcmuaf.vetcaremanagement.dto.request.ApiResponse;
import vn.edu.hcmuaf.vetcaremanagement.dto.request.UserCreationRequest;
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
    public ApiResponse<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();

        return ApiResponse.<List<User>>builder()
                .data(users)
                .build();
    }

    @GET
    @Path("/{username}")
    @Produces(MediaType.APPLICATION_JSON)
    public ApiResponse<User> getUserByUsername(@PathParam("username") String username) {
        User user = userService.getUserByUsername(username);

        return ApiResponse.<User>builder()
                .data(user)
                .build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public ApiResponse<String> createUser(@Valid UserCreationRequest request) {
        userService.createUser(request);
        return ApiResponse.<String>builder()
                .data("User created successfully")
                .build();
    }
}
