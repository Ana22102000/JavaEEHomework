package com.example.demo.Database;

import com.example.demo.Database.AuthorEntity;
import com.example.demo.Database.BookEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {

    private final EntityManager entityManager;

    @Transactional
    public int createBook(String title, String isbn, String authorSurname, String authorName){
        AuthorEntity author = new AuthorEntity();
        author.setAuthorSurname(authorSurname);
        author.setAuthorName(authorName);
        author = entityManager.merge(author);

        BookEntity book = new BookEntity();
        book.setTitle(title);
        book.setIsbn(isbn);
        book.setAuthorId(author.getId());


        book = entityManager.merge(book);
        return book.getId();
    }

    @Transactional
    public BookEntity getBookById(int id) {
        return entityManager.find(BookEntity.class, id);
    }

    @Transactional
    public List<BookEntity> getAllBooks() {
        List<BookEntity> list = entityManager.createQuery("SELECT b FROM BookEntity b LEFT JOIN FETCH b.author", BookEntity.class)  //todo just books?
                .getResultList();
        return list;
    }

    @Transactional
    public BookEntity getBookByIsbn(String isbn) {
        return entityManager.createQuery("SELECT b FROM BookEntity b LEFT JOIN FETCH b.author WHERE b.isbn LIKE :isbn", BookEntity.class)  //todo just books?
                .setParameter("isbn", isbn)
                .getSingleResult();//todo test isbn which doesnt exist
    }

    @Transactional
    public List<BookEntity> getBookByTitle(String title) {
        return entityManager.createQuery("SELECT b FROM BookEntity b LEFT JOIN FETCH b.author WHERE LOWER(b.title) LIKE :title", BookEntity.class)  //todo just books?
                .setParameter("title", '%' + title.toLowerCase() + '%')
                .getResultList();
    }

    @Transactional
    public List<BookEntity> searchBooks(String title, String surname, String name) {
        return entityManager.createQuery("SELECT b FROM BookEntity b LEFT JOIN FETCH b.author WHERE " +
                                            "LOWER(b.title) LIKE :title AND LOWER(b.author.authorSurname) LIKE :surname" +
                                            " AND LOWER(b.author.authorName) LIKE :name", BookEntity.class)  //todo just books?
                .setParameter("title", '%' + title.toLowerCase() + '%')
                .setParameter("surname", '%' + surname.toLowerCase() + '%')
                .setParameter("name", '%' + name.toLowerCase() + '%')
                .getResultList();
    }





//    private static ArrayList<BookEntity> bookModelList = startDatabase();
//
//    private static ArrayList<BookEntity> startDatabase() {
//        ArrayList<BookEntity> arrayList = new ArrayList<>();
//        arrayList.add(new BookEntity("Harry Potter","978-0-2547-5721-6","Rowling","Joanne"));
//        arrayList.add(new BookEntity("Lord of the rings","894-3-9825-2425-1","Tolkien","John"));
//        arrayList.add(new BookEntity("Sherlock Holmes","998-9-3766-9117-4","Conan Doyle","Arthur"));
//
//        return arrayList;
//    }
//
//    public static ArrayList<BookEntity> getBookModelList(){
//        return bookModelList;
//
//    }
//
//    public static ArrayList<BookEntity> getBookModelListSearch(String title){
//        ArrayList<BookEntity> list = new ArrayList<BookEntity>(bookModelList);
//        list.removeIf(s -> !s.getTitle().toLowerCase().contains(title.toLowerCase()));
//        return list;
//
//    }
}
