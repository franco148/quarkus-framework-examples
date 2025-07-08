package org.francofral.quarkus;

import jakarta.enterprise.context.ApplicationScoped;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class BookRepository {

    @ConfigProperty(name = "books.genre", defaultValue = "Sci-Fi")
    String genre;

    public List<Book> getBooks() {
        return List.of(
                new Book(1, "Understanding Quarkus", "Franco", 2025, genre),
                new Book(2, "Practicing Quarkus", "Franco", 2023, genre),
                new Book(3, "Effective Java", "Josh Bloch", 2001, genre),
                new Book(4, "Thinking in Java", "Bruce Eckel", 1998, genre)
        );
    }

    public int countAllBooks() {
        return getBooks().size();
    }

    public Optional<Book> getBook(int id) {
        return getBooks().stream().filter(book -> book.getId() == id).findFirst();
    }
}
