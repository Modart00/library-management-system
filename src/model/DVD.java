package model;

public class DVD extends LibraryItem{
    private int duration;
    private String genre;

    public DVD(int id,String title,int duration,String genre){
        super(id,title);
        this.duration = duration;
        this.genre = genre;

    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    @Override
    public void displayInfo(){
        System.out.print(
                "ID: " + id +
                        " | Type: DVD" +
                        " | Title: " + title +
                        " | Duration: " + duration +
                        " | Genre: " + genre
        );
    }

}