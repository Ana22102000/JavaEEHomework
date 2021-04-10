package com.example.demo.Database.Repository;

import com.example.demo.Database.Entity.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookRepository extends JpaRepository<BookEntity, Integer> {

    List<BookEntity> findAllBy();
    BookEntity getBookEntityByIsbn(String isbn);

    @Query("SELECT b FROM BookEntity b LEFT JOIN FETCH b.author WHERE " +
            "LOWER(b.title) LIKE %:title%" +
            " AND (b.isbn) LIKE %:isbn%" +
            " AND LOWER(b.author.authorSurname) LIKE %:surname%" +
            " AND LOWER(b.author.authorName) LIKE %:name%")
    List<BookEntity> findBookEntities(@Param("title") String title, @Param("isbn") String isbn, @Param("surname") String surname, @Param("name") String name);


}