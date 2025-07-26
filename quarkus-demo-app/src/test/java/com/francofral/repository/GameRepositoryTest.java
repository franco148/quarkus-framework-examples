package com.francofral.repository;

import com.francofral.entity.Game;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
public class GameRepositoryTest {

    @Inject
    GameRepository repository;

    @Test
    public void testFindById() {
        Game entity = repository.findById(1L);
        assertEquals(entity.getId(),1L);
    }
}
