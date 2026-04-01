package app;
import model.*;
import service.Library;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Library library = new Library();
        Member member;

        while (true) {
            System.out.println("\n--- KÜTÜPHANE SİSTEMİ ---");
            System.out.println("1. Ürünleri Listele");
            System.out.println("2. Kitap ekle");
            System.out.println("3. DVD ekle");
            System.out.println("4. Dergi ekle");
            System.out.println("5. Üye Ekle");
            System.out.println("6. Üyeleri görüntüle");
            System.out.println("7. Ödünç Ver");
            System.out.println("8. İade Al");
            System.out.println("0. Çıkış");

            int choice = input.nextInt();

            switch (choice) {
                // Ürünleri görüntüleme
                case 1:
                    library.listItems();
                    break;
                // Kitap Ekleme
                case 2:
                    input.nextLine();
                    System.out.print("Kitap başlığını giriniz: ");
                    String bookTitle = input.nextLine();
                    System.out.print("Kitabın yazarını giriniz: ");
                    String author = input.nextLine();
                    System.out.print("Kitabın sayfa sayısını giriniz: ");
                    int pageNumber = input.nextInt();
                    int bookId;
                    if(library.itemList.isEmpty()){bookId = 1;}
                    else{ bookId = library.itemList.getLast().getId() + 1;}
                    Book book = new Book(bookId,bookTitle,author,pageNumber);
                    library.addItem(book);
                    break;
                // Dvd ekleme
                case 3:
                    input.nextLine();
                    System.out.print("DVD başlığını giriniz: ");
                    String dvdTitle = input.nextLine();
                    System.out.print("DVD türünü giriniz: ");
                    String genre = input.nextLine();
                    System.out.print("DVD oynatma süresini giriniz: ");
                    int duration = input.nextInt();
                    int dvdId;
                    if(library.itemList.isEmpty()){dvdId = 1;}
                    else{ dvdId = library.itemList.getLast().getId() + 1;}
                     DVD dvd = new DVD(dvdId,dvdTitle,duration,genre);
                    library.addItem(dvd);
                    break;
                    // Dergi ekleme
                case 4:
                    input.nextLine();
                    System.out.print("Dergi başlığını giriniz: ");
                    String magazineTitle = input.nextLine();
                    System.out.print("Dergi sayı numarasını giriniz: ");
                    int issueNumber = input.nextInt();
                    int magazineId;
                    if(library.itemList.isEmpty()){magazineId = 1;}
                    else{ magazineId = library.itemList.getLast().getId() + 1;}
                    Magazine magazine = new Magazine(magazineId,magazineTitle,issueNumber);
                    library.addItem(magazine);
                    break;
                    //Üye ekleme
                case 5:
                    input.nextLine();
                    System.out.print("Üye ismi giriniz: ");
                    String name = input.nextLine();
                    if (library.memberList.isEmpty()) {
                        member = new Member(name, 1);
                        library.addMember(member);
                    } else {
                        member = new Member(name, library.memberList.getLast().getMemberId() + 1);
                        library.addMember(member);
                    }
                    break;
                    //Üyeleri GÖrüntüleme
                case 6:
                    library.listMembers();
                    break;
                    // Ürün ödünç verme
                case 7:
                    library.listItems();

                    System.out.print("\nÖdünç vermek istediğiniz kitabın ID numarasını giriniz: ");
                    int itemId = input.nextInt();
                    System.out.print("\nÖdünç vermek istediğiniz kişinin ID numarasını giriniz: ");
                    int memberId = input.nextInt();
                    LibraryItem item = library.findItemById(itemId);
                    Member member1 = library.findMemberById(memberId);
                    if (item == null) {
                        System.out.println("Ürün bulunamadı!");
                        break;
                    }

                    if (member1 == null) {
                        System.out.println("Üye bulunamadı!");
                        break;
                    }
                    library.lendItem(item,member1);

                    break;
                    // ürünleri geri almak
                case 8:
                    System.out.print("\nİade edilen kitabın ID numarasını giriniz: ");
                    int itemId1 = input.nextInt();
                    System.out.print("\niade eden kişinin ID numarasını giriniz: ");
                    int memberId1 = input.nextInt();
                    LibraryItem item1 = library.findItemById(itemId1);
                    Member member2 = library.findMemberById(memberId1);
                    if (item1 == null) {
                        System.out.println("Ürün bulunamadı!");
                        break;
                    }

                    if (member2 == null) {
                        System.out.println("Üye bulunamadı!");
                        break;
                    }
                    library.returnItem(item1,member2);

                    break;
                case 0:
                    System.out.println("Çıkış yapılıyor...");
                    return;
                default:
                    System.out.println("Geçersiz Seçim!");
            }
        }

    }

}
