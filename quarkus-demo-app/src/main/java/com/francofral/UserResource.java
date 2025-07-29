package com.francofral;

import com.francofral.entity.ResponseModel;
import com.francofral.entity.User;
import com.francofral.service.UserService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/user")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UserResource {

    @Inject
    UserService userService;

    @POST
    public ResponseModel register(User user){
        userService.createUser(user);
        return new ResponseModel("The user has been created",200);
    }

    @POST
    @Path("/login")
    public Response login(User user){
        return userService.login(user.getUsername(), user.getPassword());
    }
}

