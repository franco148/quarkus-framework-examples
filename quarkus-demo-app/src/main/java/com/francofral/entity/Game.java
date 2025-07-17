package com.francofral.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

@Schema(description = "Game model")
@Entity
@Table(name = "games")
public class Game extends PanacheEntityBase {

    @Schema(description = "Game id", example = "10")
    @Id
    @GeneratedValue(generator = "game_id_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "game_id_seq", sequenceName = "game_id_seq",  allocationSize = 1)
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
