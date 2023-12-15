package org.example;

import com.github.javafaker.Faker;
import net.bytebuddy.asm.Advice;
import org.example.dao.LoansDAO;
import org.example.dao.PrintedItemsDAO;
import org.example.dao.UsersDAO;
import org.example.entities.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Period;
import java.util.ArrayList;
import java.util.Scanner;

public class Application {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("U4-W3-D5");

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        EntityManager em = emf.createEntityManager();
        PrintedItemsDAO itemsDAO = new PrintedItemsDAO(em);
        LocalDate date1 = LocalDate.parse("2023-12-01");
        LocalDate date2 = LocalDate.parse("2023-10-05");
        LocalDate date3 = LocalDate.parse("2023-11-16");
        LocalDate date4 = LocalDate.parse("2023-01-06");
        LocalDate date5 = LocalDate.parse("2023-02-02");
        UsersDAO usersDAO = new UsersDAO(em);
        LoansDAO loansDAO = new LoansDAO(em);
        Book book1 = new Book("123", "title", 2010, 100,"gianni", "fiction");
        Book book2 = new Book("124", "hey", 2010, 100,"gianni", "fiction");
        Book book3 = new Book("125", "title", 2010, 100,"gianni", "fiction");
        // itemsDAO.save(book1);
        // itemsDAO.save(book2);
        // itemsDAO.save(book3);
        // itemsDAO.searchByTitle("it").forEach(System.out::println);
        User user1 = new User("Chicco", "Pallo",LocalDate.now());
        User user2 = new User("Marco", "Frasci",LocalDate.now());
        User user3 = new User("Peppo", "Milli",LocalDate.now());

        Loan loan1 = new Loan(usersDAO.getByCardNumber(1), itemsDAO.findByISBN("123"), date2);
        Loan loan2 = new Loan(usersDAO.getByCardNumber(2), itemsDAO.findByISBN("124"), date2, date1);
        Loan loan3 = new Loan(usersDAO.getByCardNumber(3), itemsDAO.findByISBN("125"), date4, date5);
        Loan loan4 = new Loan(usersDAO.getByCardNumber(1),itemsDAO.findByISBN("123"), date5);

        // usersDAO.save(user1);
        // usersDAO.save(user2);
        // usersDAO.save(user3);

        // loansDAO.save(loan1);
        // loansDAO.save(loan2);
        // loansDAO.save(loan3);
        // loansDAO.save(loan4);
        // createUsers();
        // createPrintedItems();
        // loansDAO.searchCurrentlyBorrowedItems().forEach(System.out::println);
        // loansDAO.searchCurrentlyBorrowedItemsByCardNumber(1).forEach(System.out::println);
        // loansDAO.findUnreturnedLoans().forEach(System.out::println);
        interact(scanner);


        scanner.close();
        em.close();
        emf.close();
    }

    public static void createUsers() {
        EntityManager em = emf.createEntityManager();
        UsersDAO usersDAO = new UsersDAO(em);
        Faker faker = new Faker();
        LocalDate birthday1 = LocalDate.parse("1995-01-01");
        LocalDate birthday2 = LocalDate.parse("1980-12-22");
        LocalDate birthday3 = LocalDate.parse("1952-10-02");
        LocalDate birthday4 = LocalDate.parse("1999-01-31");
        LocalDate birthday5 = LocalDate.parse("1972-10-04");
        LocalDate[] arrayBirthdays = {birthday1,birthday2,birthday3,birthday4,birthday5};
        for (int i = 0; i < 5; i++) {
            User user = new User(faker.name().firstName(), faker.name().lastName(), arrayBirthdays[i]);
            usersDAO.save(user);
        }

    }

    public static void createPrintedItems() {
        EntityManager em = emf.createEntityManager();
        PrintedItemsDAO printedItemsDAO = new PrintedItemsDAO(em);
        Faker faker = new Faker();
        for(int i = 0; i < 20; i++) {
            int minYear = 1910;
            int maxYear = LocalDate.now().getYear();

            int randomYear = faker.number().numberBetween(minYear, maxYear + 1);
            Book book = new Book(faker.code().isbn13(), faker.book().title(),
                    randomYear, faker.number().numberBetween(5,800), faker.book().author(), faker.book().genre());
            printedItemsDAO.save(book);
        }
        Periodicity weekly = Periodicity.WEEKLY;
        Periodicity monthly = Periodicity.MONTHLY;
        Periodicity biannual = Periodicity.BIANNUAL;
        Periodicity[] periodicities = {weekly,monthly,biannual};
        for (int i = 0; i < 20; i++) {
            int minYear = 1910;
            int maxYear = LocalDate.now().getYear();
            int randomYear = faker.number().numberBetween(minYear, maxYear);
            Magazine magazine = new Magazine(faker.code().isbn13(), faker.book().title(),
                    randomYear, faker.number().numberBetween(5,100),periodicities[faker.number().numberBetween(0,3)]);
            printedItemsDAO.save(magazine);
        }
    }

    public static void interact(Scanner scanner) {

        EntityManager em = emf.createEntityManager();
        PrintedItemsDAO itemsDAO = new PrintedItemsDAO(em);
        UsersDAO usersDAO = new UsersDAO(em);
        LoansDAO loansDAO = new LoansDAO(em);
        int cardNumber = 0;
        do {
            System.out.println("Salve, inserisca il codice della sua carta bibliotecaria.");
            cardNumber = Integer.parseInt(scanner.nextLine());

        } while (cardNumber == 0);
        System.out.println("Questo è l'elenco dei libri e delle riviste che abbiamo");
        itemsDAO.showAllElements().forEach(System.out::println);

        String title = null;
        String author = null;
        int choice;
        do {
            System.out.println("Sta cercando qualcosa in particolare? Prema 1 per cercare in base al titolo" +
                    " e 2 per cercare in base all'autore. Altrimenti per uscire prema 3.");
            choice = Integer.parseInt(scanner.nextLine());
            if(choice == 1) {
                do {
                    System.out.println("Inserisca il nome del titolo.");
                    title = scanner.nextLine();
                    System.out.println("Ecco la lista di elementi:");
                    itemsDAO.searchByTitle(title).forEach(System.out::println);
                } while (title == null);
            } else if (choice == 2) {
                do {
                    System.out.println("Inserisca il nome dell'autore.");
                    author = scanner.nextLine();
                    System.out.println("Ecco la lista di elementi:");
                    itemsDAO.searchByAuthor(author).forEach(System.out::println);;
                } while (author == null);
            }
        } while ((choice < 1 || choice > 3));
        String titleToBorrow = null;

        int borrowChoice;
        do {
            System.out.println("Vuole prendere qualcosa in prestito? Prema 1 per confermare, 2 per rifiutare.");
            borrowChoice = Integer.parseInt(scanner.nextLine());
            if(borrowChoice == 1) {
                do {
                    System.out.println("Che cosa vuole prendere in prestito? Inserisca il titolo esatto.");
                    titleToBorrow = scanner.nextLine();
                    System.out.println("CardNumber " + cardNumber);
                    System.out.println(itemsDAO.searchByExactTitle(titleToBorrow) + " item to borrow");
                    Loan loan1 = new Loan(usersDAO.getByCardNumber(cardNumber), itemsDAO.searchByExactTitle(titleToBorrow), LocalDate.now());
                    loansDAO.save(loan1);
                    System.out.println("Grazie, per favore lo riporti entro il " + LocalDate.now().plusDays(30));
                } while (titleToBorrow == null);
            }
        } while (borrowChoice < 1 || borrowChoice > 2);

        if(!loansDAO.searchCurrentlyBorrowedItemsByCardNumber(cardNumber).isEmpty()) {
            System.out.println("Questa è la lista di prestiti associata alla sua tessera: ");
            loansDAO.searchCurrentlyBorrowedItemsByCardNumber(cardNumber).forEach(System.out::println);
            int returnChoice;
            do {
                System.out.println("Vuole fare una restituzione di un prestito? Prema 1 per confermare, 2 per rifiutare.");
                returnChoice = Integer.parseInt(scanner.nextLine());
                if (returnChoice == 1) {
                    int loanToReturn;
                    do {
                        System.out.println("Inserisca un numero da 0 a " + loansDAO.searchCurrentlyBorrowedItemsByCardNumber(cardNumber).size());
                        loanToReturn = Integer.parseInt(scanner.nextLine());
                        loansDAO.returnItem(LocalDate.now());
                    } while (loanToReturn < 0 || loanToReturn > loansDAO.searchCurrentlyBorrowedItemsByCardNumber(cardNumber).size());
                }
            } while (returnChoice < 1 || returnChoice > 2);
        } else System.out.println("Momentaneamente non ha nessun prestito.");
    }
}
