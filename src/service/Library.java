package service;

import model.LibraryItem;
import model.Member;

import java.util.ArrayList;

public class Library {
    public ArrayList<LibraryItem> itemList = new ArrayList<>();
    public ArrayList<Member> memberList = new ArrayList<>();

    public void addItem(LibraryItem item){
     itemList.add(item);
    }

    public void addMember(Member member){
        memberList.add(member);
    }

    public void listItems() {
        System.out.println("--- Kütüphane Listesi ---");
        for (LibraryItem item : itemList) {
            System.out.println("ID: " + item.getId() + " | İsim: " + item.getTitle());
        }
        if (itemList.isEmpty()){
            System.out.println("Listelenecek ürün bulunamadı...");
        }
    }

    public void listMembers() {
        System.out.println("--- Üye Listesi ---");
        for (Member member : memberList) {
            System.out.println("ID: " + member.getMemberId() + " | İsim: " + member.getName());
        }
        if (memberList.isEmpty()){
            System.out.println("Listelenecek üye bulunamadı...");
        }
    }

    public void lendItem(LibraryItem item,Member member){
        System.out.println(item.getTitle() + " adlı ürün (ID:" + item.getId() + "), " + member.getName() + "(ID:" + member.getMemberId() +
                ") isimli kişiye ödünç verildi.");
        if (item.isAvailable()) {
            member.borrowItem(item);
        } else {
            System.out.println("Bu ürün zaten ödünç verilmiş.");
        }
    }

    public void returnItem(LibraryItem item,Member member) {
        System.out.println(item.getTitle() + " adlı kitap(ID:" + item.getId() + "), " + member.getName() + "(ID:" + member.getMemberId() +
                ") isimli kişiden geri alındı.");
        if (!item.isAvailable()) {
            member.returnItem(item);
        } else {
            System.out.println("Bu ürün zaten kütüphanede.");
        }
    }

    public LibraryItem findItemById(int id){
        for(LibraryItem item : itemList){
            if (item.getId() == id ){
                return item;
            }
        }
        return null;
    }

    public Member findMemberById(int id){
        for(Member member : memberList){
            if (member.getMemberId() == id ){
                return member;
            }
        }
        return null;
    }


}
