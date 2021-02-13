package repository;

import model.Book;

import java.util.List;

public interface IBookRepository extends IRepository<Book> {
    List<Book> showAllBook();

    List<Book> searchUserBook(int nim);

    void borrowBook(int nim, List<Book> idBook);

    void returnBookBorrowed(int nim, List<Integer> idBook);

    Book searchBook(int id);

    Book searchBook(String title);
}
