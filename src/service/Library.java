package service;

import model.*;
import dao.*;

import java.util.ArrayList;

public class Library {
    public ArrayList<LibraryItem> itemList = new ArrayList<>();
    public ArrayList<Member> memberList = new ArrayList<>();
    LibraryItemDao itemDao = new LibraryItemDao();
    MemberDao memberDao = new MemberDao();
    LendAndReturnDao lendAndReturnDao = new LendAndReturnDao();
    BookDao bookDao = new BookDao();
    MagazineDao magazineDao = new MagazineDao();
    DvdDao dvdDao = new DvdDao();

    public void addItem(LibraryItem item) {
        if (item instanceof Book) {
            bookDao.saveBook((Book) item);
        } else if (item instanceof Magazine) {
            magazineDao.saveMagazine((Magazine) item);
        } else if (item instanceof DVD) {
            dvdDao.saveDVD((DVD) item);
        } else {
            itemDao.save(item);
        }
    }

    public void addMember(Member member){
        memberList.add(member);
        memberDao.saveMember(member);
    }

    public void listItems() {
        System.out.println("--- Kütüphane Listesi ---");

        for (Book book : bookDao.getAllBooks()) {
            book.displayInfo();
            System.out.println("| Durum: " + getStatus(book));
        }

        for (Magazine magazine : magazineDao.getAllMagazines()) {
            magazine.displayInfo();
            System.out.println("| Durum: " + getStatus(magazine));
        }

        for (DVD dvd : dvdDao.getAllDVDs()) {
            dvd.displayInfo();
            System.out.println("| Durum: " + getStatus(dvd));
        }
    }

    public void listMembers() {
        System.out.println("--- Üye Listesi ---");
        System.out.println(memberDao.GetAllMembers());
    }

    public void lendItem(LibraryItem item, Member member) {
        lendAndReturnDao.lendItem(item.getId(), member.getMemberId());
    }

    public void returnItem(LibraryItem item, Member member) {
        lendAndReturnDao.returnItem(item.getId(), member.getMemberId());
    }

    public LibraryItem findItemById(int id){
        return itemDao.findById(id);
    }

    public Member findMemberById(int id){
        return memberDao.findById(id);
    }

    public void listActiveLoans() {
        lendAndReturnDao.listActiveLoans();
    }

    private String getStatus(LibraryItem item) {
        return lendAndReturnDao.isItemAvailable(item.getId())
                ? "✔ Müsait"
                : "✖ Ödünçte";
    }

}