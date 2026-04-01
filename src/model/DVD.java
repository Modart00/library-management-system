package model;

public class DVD extends LibraryItem{
    private int duration;
    private String genre;

    public DVD(int id,String title,int duration,String genre){
        super(id,title);
        this.duration = duration;
        this.genre = genre;

    }

    public double getDuration() {
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
        System.out.println("--- DVD Bilgileri ---");

        displayBasicInfo();
        System.out.println("Tür: " + getGenre());
        System.out.println("Oynatma süresi: " + getDuration() + "dakika");

        displayStatus();
    }

}
