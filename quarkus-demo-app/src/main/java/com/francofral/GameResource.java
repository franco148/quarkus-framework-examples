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

    private List<Game>  games;

    @Inject
    private GameService gameService;

    public GameResource() {
        this.games = new ArrayList<>();
        games.add(new Game(1, "R6", "FPS"));
        games.add(new Game(2, "Battlefield 1", "FPS"));
        games.add(new Game(3, "The Elder Scrolls V: Skyrim", "RPG"));
        games.add(new Game(4, "Cyberpunk 2077", "RPG"));
    }

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

        List<Game> pagedGames = games;

        if (name != null && !name.isEmpty()) {
            pagedGames = pagedGames.stream()
                    .filter(game -> game.getName().toLowerCase().contains(name.toLowerCase()))
                    .collect(Collectors.toList());
        }

        int totalGames = pagedGames.size();
        int start = (page - 1) * size;
        int end = Math.min(start + size, totalGames);

        if (start >= totalGames) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        if (gameCategory != null && !gameCategory.isEmpty()) {
            pagedGames = pagedGames.stream()
                    .sorted((g1, g2) -> {
                        boolean isG1InCategory = g1.getCategory().toLowerCase().contains(gameCategory.toLowerCase());
                        boolean isG2InCategory = g2.getCategory().toLowerCase().contains(gameCategory.toLowerCase());
                        if (isG1InCategory && !isG2InCategory) return -1;
                        if (!isG1InCategory && isG2InCategory) return 1;
                        return 0;
                    }).collect(Collectors.toList());
        }

        pagedGames = pagedGames.subList(start, end);

        return Response.ok(pagedGames)
                .header("X-Total-Pages", totalGames)
                .build();
    }

    @GET
    @Path("/{id}")
    public Response getGame(@PathParam("id") int id) {
        return games.stream()
                .filter(v -> v.getId() == id)
                .findFirst()
                .map(v -> Response.ok(v)
                        .cookie(new NewCookie("gameCategory", v.getCategory(), "/", null, "Games Category", 3600, false))
                        .build())
                .orElse(Response.status(Response.Status.NOT_FOUND).build());
    }

    @POST
    public Response createGame(Game game) {
        long id = games.stream().max(Comparator.comparingLong(Game::getId)).get().getId() + 1;
        game.setId(id);
        games.add(game);

        return Response.ok(new ResponseModel("Game created successfully", 201)).build();
    }

    @PATCH
    public Response updateGame(Game game) {
        games.stream()
                .filter(v -> v.getId() == game.getId())
                .findFirst()
                .ifPresent(v -> {
                    if (game.getName() != null && !game.getName().isEmpty()) {
                        v.setName(game.getName());
                    }
                    if (game.getCategory() != null && !game.getCategory().isEmpty()) {
                        v.setCategory(game.getCategory());
                    }
                });

        return Response.ok(new ResponseModel("Game updated successfully", 200)).build();
    }

    @PUT
    public Response replaceGame(Game game) {
        OptionalInt index = IntStream.range(0, games.size())
                .filter(i -> games.get(i).getId() == game.getId())
                .findFirst();

        if (index.isPresent()) {
            games.set(index.getAsInt(), game);
        }

        return Response.ok(new ResponseModel("Game updated successfully", 200)).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteGame(@PathParam("id")  int id) {
        games.removeIf(v -> v.getId() == id);
        return Response.ok(new ResponseModel("Game deleted successfully", 200)).build();
    }
}
