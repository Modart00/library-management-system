package model;

public class LibraryItem {
    private int id;
    private String title;
    private boolean isAvailable = true;

    public LibraryItem(int id, String title) {
        this.id = id;
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public void displayInfo(){
        System.out.println("ID: " + getId());
        System.out.println("Başlık: " + getTitle());
        if (isAvailable()) {
            System.out.println("Durum: Mevcut.");
        } else {
            System.out.println("Durum: Mevcut Değil.");
        }
    }

    protected void displayBasicInfo() {
        System.out.println("ID: " + getId());
        System.out.println("Başlık: " + getTitle());
    }

    protected void displayStatus() {
        if (isAvailable()) {
            System.out.println("Durum: Mevcut.");
        } else {
            System.out.println("Durum: Mevcut Değil.");
        }
    }

}
