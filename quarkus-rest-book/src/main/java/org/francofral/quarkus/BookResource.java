package org.francofral.quarkus;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import java.util.List;
import java.util.Optional;

@Path("/api/books")
@Produces(MediaType.APPLICATION_JSON)
public class BookResource {

    @GET
    public List<Book> getBooks() {
        return List.of(
            new Book(1, "Understanding Quarkus", "Franco", 2025, "IT"),
            new Book(2, "Practicing Quarkus", "Franco", 2023, "IT"),
            new Book(3, "Effective Java", "Josh Bloch", 2001, "IT"),
            new Book(4, "Thinking in Java", "Bruce Eckel", 1998, "IT")
        );
    }

    @GET
    @Path("/count")
    @Produces(MediaType.TEXT_PLAIN)
    public int countAllBooks() {
        return getBooks().size();
    }

    @GET
    @Path("/{id}")
    public Optional<Book> getBook(@PathParam("id") int id) {
        return getBooks().stream().filter(book -> book.getId() == id).findFirst();
    }
}
