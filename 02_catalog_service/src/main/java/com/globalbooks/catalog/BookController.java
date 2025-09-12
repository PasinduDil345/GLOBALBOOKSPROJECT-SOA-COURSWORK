package com.globalbooks.catalog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/catalog/rest")
public class BookController {

    @Autowired
    private CatalogService catalogService;

    @GetMapping("/book")
    public ResponseEntity<Book> getBookByIsbn(@RequestParam String isbn) {
        Book book = catalogService.getBookById(isbn);
        if (book != null) {
            return ResponseEntity.ok(book);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}