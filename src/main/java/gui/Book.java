package gui;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable(table = "books")
public class Book {

    String title = "";
    String author = "";

    @PrimaryKey
    String ISBN = "";


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public Book() {
        this("", "", "");
    }

    public Book(String title, String author, String ISBN) {
        setTitle(title);
        setAuthor(author);
        setISBN(ISBN);
    }


}
