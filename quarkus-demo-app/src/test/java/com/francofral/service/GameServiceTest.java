package com.francofral.service;

import com.francofral.entity.Game;
import com.francofral.repository.GameRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class GameServiceTest {

    @InjectMocks
    private GameService gameService;

    @Mock
    private GameRepository gamesRepository;

    @Test
    void testFindPaginatedWithoutName() {
        List<Game> mockGames = List.of(new Game(1L, "Game1", "Category1"));
        Mockito.when(gamesRepository.findPaginated(0, 10, "category"))
                .thenReturn(mockGames);

        List<Game> result = gameService.findPaginated(0, 10, "category", null);

        assertEquals(1, result.size());
        assertEquals("Game1", result.get(0).getName());
        Mockito.verify(gamesRepository).findPaginated(0, 10, "category");
    }

}