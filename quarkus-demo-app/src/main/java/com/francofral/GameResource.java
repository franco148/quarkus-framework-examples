package com.francofral;

import com.francofral.entity.Game;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.NewCookie;
import jakarta.ws.rs.core.Response;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Path("/games")
public class GameResource {

    private List<Game>  games;

    public GameResource() {
        this.games = new ArrayList<>();
        games.add(new Game(1, "R6", "FPS"));
        games.add(new Game(2, "Battlefield 1", "FPS"));
        games.add(new Game(3, "The Elder Scrolls V: Skyrim", "RPG"));
        games.add(new Game(4, "Cyberpunk 2077", "RPG"));
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
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
    @Produces(MediaType.APPLICATION_JSON)
    public Response getGame(@PathParam("id") int id) {
        return games.stream()
                .filter(v -> v.getId() == id)
                .findFirst()
                .map(v -> Response.ok(v)
                        .cookie(new NewCookie("gameCategory", v.getCategory(), "/", null, "Games Category", 3600, false))
                        .build())
                .orElse(Response.status(Response.Status.NOT_FOUND).build());
    }
}
