package model;

public class Magazine extends LibraryItem {
    private String issueNumber;

    public Magazine(int id,String title,String issueNumber){
        super(id,title);
        this.issueNumber = issueNumber;
    }

    public String getIssueNumber() {
        return issueNumber;
    }

    public void setIssueNumber(String issueNumber) {
        this.issueNumber = issueNumber;
    }

    @Override
    public void displayInfo() {
            System.out.print(
                    "ID: " + id +
                            " | Type: MAGAZINE" +
                            " | Title: " + title +
                            " | Issue: " + issueNumber
            );
    }

}