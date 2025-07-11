package com.francofral.entity;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

@Schema(description = "Game model")
public class Game {
    @Schema(description = "Game id", example = "10")
    private long id;
    @Schema(description = "Name of game", example = "Dota")
    private String name;
    @Schema(description = "Category of game", example = "EG")
    private String category;

    public Game() {
    }

    public Game(long id, String name, String category) {
        this.id = id;
        this.name = name;
        this.category = category;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
