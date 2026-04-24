package ui;

import service.Library;
import model.*;

import javax.swing.*;
import java.awt.*;

public class LibraryGUI extends JFrame {

    private Library library;

    private JTextArea outputArea;

    public LibraryGUI() {
        library = new Library();

        setTitle("Kütüphane Yönetim Sistemi");
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        setLayout(new BorderLayout());

        outputArea = new JTextArea();
        outputArea.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(outputArea);
        add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(2, 3, 10, 10));

        JButton addBookButton = new JButton("Kitap Ekle");
        JButton addMagazineButton = new JButton("Dergi Ekle");
        JButton addDvdButton = new JButton("DVD Ekle");
        JButton addMemberButton = new JButton("Üye Ekle");
        JButton listItemsButton = new JButton("Ürünleri Listele");
        JButton listMembersButton = new JButton("Üyeleri Listele");

        buttonPanel.add(addBookButton);
        buttonPanel.add(addMagazineButton);
        buttonPanel.add(addDvdButton);
        buttonPanel.add(addMemberButton);
        buttonPanel.add(listItemsButton);
        buttonPanel.add(listMembersButton);

        add(buttonPanel, BorderLayout.SOUTH);

        addBookButton.addActionListener(e -> addBook());
        addMagazineButton.addActionListener(e -> addMagazine());
        addDvdButton.addActionListener(e -> addDvd());
        addMemberButton.addActionListener(e -> addMember());

        listItemsButton.addActionListener(e -> {
            outputArea.setText("");
            library.listItems();
            outputArea.append("Ürünler console'a yazdırıldı.\n");
        });

        listMembersButton.addActionListener(e -> {
            outputArea.setText("");
            library.listMembers();
            outputArea.append("Üyeler console'a yazdırıldı.\n");
        });
    }

    private void addBook() {
        String title = JOptionPane.showInputDialog("Kitap başlığı:");
        String author = JOptionPane.showInputDialog("Yazar:");
        int pageCount = Integer.parseInt(JOptionPane.showInputDialog("Sayfa sayısı:"));

        Book book = new Book(0, title, author, pageCount);
        library.addItem(book);

        outputArea.append("Kitap eklendi: " + title + "\n");
    }

    private void addMagazine() {
        String title = JOptionPane.showInputDialog("Dergi başlığı:");
        String issueNumber = JOptionPane.showInputDialog("Sayı numarası:");

        Magazine magazine = new Magazine(0, title, issueNumber);
        library.addItem(magazine);

        outputArea.append("Dergi eklendi: " + title + "\n");
    }

    private void addDvd() {
        String title = JOptionPane.showInputDialog("DVD başlığı:");
        String genre = JOptionPane.showInputDialog("Tür:");
        int duration = Integer.parseInt(JOptionPane.showInputDialog("Süre:"));

        DVD dvd = new DVD(0, title, duration, genre);
        library.addItem(dvd);

        outputArea.append("DVD eklendi: " + title + "\n");
    }

    private void addMember() {
        String name = JOptionPane.showInputDialog("Üye adı:");

        Member member = new Member(0, name);
        library.addMember(member);

        outputArea.append("Üye eklendi: " + name + "\n");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            LibraryGUI gui = new LibraryGUI();
            gui.setVisible(true);
        });
    }
}