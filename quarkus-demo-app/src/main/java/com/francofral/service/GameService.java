package com.francofral.service;

import com.francofral.entity.Game;
import com.francofral.repository.GameRepository;
import jakarta.enterprise.context.Dependent;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import java.util.List;
import java.util.Optional;

/**
 * DI options
 * - @ApplicationScoped: Only one instance when the application starts
 * - @Singleton: Only one instance for the whole application, not managed by the CDI mechanisms
 * - @RequestScoped: Per request, can't be shared with other users
 * - @Dependent
 */
@Dependent
public class GameService {

    @Inject
    private GameRepository gameRepository;

    public List<Game> findPaginated(int page, int pageSize, String sortField, String name) {
        if (name != null && !name.isEmpty()) {
            return gameRepository.findPaginatedByName(page, pageSize, sortField, name);
        }

        return gameRepository.findPaginated(page, pageSize, sortField);
    }

    public long count(String name) {
        if (name != null && !name.isEmpty()) {
            return gameRepository.countByName(name);
        }

        return gameRepository.count();
    }

    public Optional<Game> findById(Long id) {
        return gameRepository.findByIdOptional(id);
    }

    @Transactional
    public void createGame(Game game) {
        game.setId(0);
        gameRepository.persist(game);
    }

    @Transactional
    public void replaceGame(Game game) {
        gameRepository.findByIdOptional(game.getId())
                .ifPresent(v -> gameRepository.persist(game));
    }

    @Transactional
    public void updateGame(Long id, String name, String category) {
        gameRepository.findByIdOptional(id).ifPresent(game -> {
            if (name != null && !name.isEmpty()) {
                game.setName(name);
            }
            if (category != null && !category.isEmpty()) {
                game.setCategory(category);
            }
            gameRepository.persist(game);
        });
    }

    @Transactional
    public void deleteGame(Long id) {
        gameRepository.findByIdOptional(id).ifPresent(gameRepository::delete);
    }
}

