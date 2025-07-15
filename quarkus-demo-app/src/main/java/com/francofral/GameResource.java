package com.francofral;

import com.francofral.entity.Game;
import com.francofral.entity.ResponseModel;
import com.francofral.service.GameService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.NewCookie;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.OptionalInt;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Path("/games")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@APIResponse(
    responseCode = "200",
    description = "Return operation data",
    content = @Content(
        mediaType = "application/json",
        schema = @Schema(implementation = ResponseModel.class)
    )
)
@Tag(name = "Game controller")
public class GameResource {

    @Inject
    private GameService gameService;


    @GET
    @Operation(
        summary = "Get all games",
        description = "Retrieves a paginated list of games. The results can be filtered by game name and sorted by the game category specified in the cookies."
    )
    @APIResponse(
        responseCode = "200",
        description = "Return games list",
        content = @Content(
            mediaType = "application/json",
            schema = @Schema(implementation = Game.class, type = SchemaType.ARRAY)
        )
    )
    public Response getGames(@HeaderParam("page") int page,
                             @HeaderParam("size") int size,
                             @QueryParam("name") String name,
                             @CookieParam("gameCategory") String gameCategory) {

        List<Game> games = gameService.findPaginated(page, size, name, gameCategory);
        long totalGames = gameService.count(name);
        int start = (page - 1) * size;

        if (start >= totalGames) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        return Response.ok(games)
                .header("X-Total-Count", String.valueOf(totalGames))
                .build();
    }

    @GET
    @Path("/{id}")
    public Response getGame(@PathParam("id") long id) {
        return gameService.findById(id)
                .map(v -> Response.ok(v)
                        .cookie(new NewCookie("gameCategory", v.getCategory(), "/", null, "Games Category", 3600, false))
                        .build())
                .orElse(Response.status(Response.Status.NOT_FOUND).build());
    }

    @POST
    public Response createGame(Game game) {
        gameService.createGame(game);

        return Response.ok(new ResponseModel("Game created successfully", 201)).build();
    }

    @PATCH
    public Response updateGame(Game game) {
        gameService.createGame(game);

        return Response.ok(new ResponseModel("Game updated successfully", 200)).build();
    }

    @PUT
    public Response replaceGame(Game game) {
        gameService.replaceGame(game);

        return Response.ok(new ResponseModel("Game updated successfully", 200)).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteGame(@PathParam("id")  long id) {
        gameService.deleteGame(id);
        return Response.ok(new ResponseModel("Game deleted successfully", 200)).build();
    }
}
