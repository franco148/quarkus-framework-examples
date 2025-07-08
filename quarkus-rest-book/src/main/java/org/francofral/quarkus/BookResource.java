package org.francofral.quarkus;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.jboss.logging.Logger;

import java.util.List;
import java.util.Optional;

@Path("/api/books")
@Produces(MediaType.APPLICATION_JSON)
public class BookResource {

    @Inject
    BookRepository bookRepository;

    @Inject
    Logger logger;

    @GET
    public List<Book> getBooks() {
        logger.info("Returning all Books");
        return bookRepository.getBooks();
    }

    @GET
    @Path("/count")
    @Produces(MediaType.TEXT_PLAIN)
    public int countAllBooks() {
        logger.info("Returning the number of Books");
        return bookRepository.countAllBooks();
    }

    @GET
    @Path("/{id}")
    public Optional<Book> getBook(@PathParam("id") int id) {
        logger.info("Returning an specific Book with id: " + id);
        return bookRepository.getBook(id);
    }
}
