package com.francofral.exception;

import com.francofral.entity.ResponseModel;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class GameDontExistExceptionMapper implements ExceptionMapper<GameDontExistException> {

    @Override
    public Response toResponse(GameDontExistException e) {
        return Response.status(Response.Status.NOT_FOUND)
                .entity(new ResponseModel(e.getMessage(), Response.Status.NOT_FOUND.getStatusCode()))
                .build();
    }
}
