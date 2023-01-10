package de.unikoeln.se.bookstore.repository;

import de.unikoeln.se.bookstore.datamodel.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepo extends JpaRepository<Book, Integer> {

}
