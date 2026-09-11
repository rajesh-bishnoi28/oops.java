public class LibraryTest {
    public static void main(String[] args) {
        useCase1 b1 = new useCase1("Java Basics", "J. Author", 350.0, "ISBN001");
        useCase1 b2 = new useCase1("OOP Concepts", "K. Writer", 420.0, "ISBN002");

        System.out.println(b1.getTitle() + " by " + b1.getAuthor());
        System.out.println("Price: " + b2.getPrice());
        System.out.println("Library: " + useCase1.libraryName);
        System.out.println("Total books added: " + useCase1.bookCount);
    }
}

