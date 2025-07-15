package com.francofral.repository;

import com.francofral.entity.Game;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import io.quarkus.panache.common.Page;
import io.quarkus.panache.common.Sort;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public class GameRepository implements PanacheRepositoryBase<Game, Long> {

    public List<Game> findPaginated(int page, int size, String sortBy) {
        if (sortBy != null && !sortBy.isEmpty()) {
            return findAll(Sort.ascending(sortBy))
                    .page(Page.of(page, size))
                    .list();
        }

        return findAll()
                .page(Page.of(page, size))
                .list();
    }

    public List<Game> findPaginatedByName(int page, int size, String sortBy, String name) {
        if (sortBy != null && !sortBy.isEmpty()) {
            return find("name like ?1", Sort.ascending(sortBy), "%"+name+"%")
                    .page(Page.of(page, size))
                    .list();
        }

        return find("name like ?1", "%"+name+"%")
                .page(Page.of(page, size))
                .list();
    }

    public long countByName(String name) {
        return count("name like ?1", name);
    }
}
