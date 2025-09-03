package com.globalbooks.catalog;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.jws.WebService;

@WebService(endpointInterface = "com.globalbooks.catalog.CatalogService",
        serviceName = "CatalogService",
        targetNamespace = "http://catalog.globalbooks.com/")
public class CatalogServiceImpl implements CatalogService {

    private static final Map<String, Book> bookDatabase = new HashMap<>();

    static {
    bookDatabase.put("978-1593275846", new Book("978-1593275846",
        "Eloquent JavaScript, 2nd Ed.", "Marijn Haverbeke", "Web Development", 30.50, 175));
    bookDatabase.put("978-1492078028", new Book("978-1492078028",
        "Python for Data Analysis, 2nd Ed.", "Wes McKinney", "Data Science", 52.00, 150));
    bookDatabase.put("978-0321765723", new Book("978-0321765723",
        "The Pragmatic Programmer: Your Journey to Mastery", "Andrew Hunt, David Thomas", "Software Development", 45.00, 300));
}


    @Override
    public List<Book> searchBooks(String query, String category) {
        return bookDatabase.values().stream()
                .filter(book -> (query == null || book.getTitle().toLowerCase()
                        .contains(query.toLowerCase()) ||
                        book.getAuthor().toLowerCase().contains(query.toLowerCase())))
                .filter(book -> (category == null || book.getCategory()
                        .equalsIgnoreCase(category)))
                .collect(Collectors.toList());
    }

    @Override
    public Book getBookById(String bookId) {
        return bookDatabase.get(bookId);
    }

    @Override
    public double getBookPrice(String bookId) {
        Book book = bookDatabase.get(bookId);
        return book != null ? book.getPrice() : 0.0;
    }

    @Override
    public boolean checkAvailability(String bookId, int quantity) {
        Book book = bookDatabase.get(bookId);
        return book != null && book.getStock() >= quantity;
    }
}