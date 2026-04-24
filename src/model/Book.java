package model;
import dao.*;
public class Book extends LibraryItem {
    private String author;
    private int pageCount;

    public Book(int id, String title, String author, int pageCount){
        super(id,title);
        this.author = author;
        this.pageCount = pageCount;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    @Override
    public void displayInfo() {
        System.out.print(
                "ID: " + id +
                        " | Type: BOOK" +
                        " | Title: " + title +
                        " | Author: " + author +
                        " | Page: " + pageCount

        );
    }

    @Override
    public String toString() {
        return super.getId() + " - " + super.getTitle() + " - " + author + " - " + pageCount;
    }

}
