package model;

import java.util.ArrayList;

public class Member {
    private int memberId;
    private String name;
    ArrayList<LibraryItem> borrowedItems = new ArrayList<>();

    public Member(){}

    public Member(int memberId,String name){
        this.name = name;
        this.memberId = memberId;
    }

    public void borrowItem(LibraryItem libraryItem){
        borrowedItems.add(libraryItem);
        libraryItem.setAvailable(false);
        if (borrowedItems.size() >= 3) {
            System.out.println("Üye ödünç alma limitine ulaştı.");
            return;
        }
    }

    public void returnItem(LibraryItem libraryItem){
        borrowedItems.remove(libraryItem);
        libraryItem.setAvailable(true);
    }

    public void listBorrowedItems(){
        System.out.println("--- Ödünç Alınan Kitaplar ---");
        for (LibraryItem item : borrowedItems) {
            System.out.println("ID: " + item.getId() + " | İsim: " + item.getTitle());
        }
    }

    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return memberId + " - " + name;
    }

}