package model;

public class Book extends LibraryItem {
    private String author;
    private int pageCount;

    public Book(int id,String title,String author,int pageCount){
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
        System.out.println("--- Kitap Bilgileri ---");

        displayBasicInfo();

        System.out.println("Yazar: " + getAuthor());
        System.out.println("Sayfa Sayısı: " + getPageCount());

        displayStatus();
    }
}
