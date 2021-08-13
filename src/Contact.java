import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Contact {
    public Contact() throws IOException {
    }

    public static int getInput(){
    Scanner in = new Scanner(System.in);
    int choice = in.nextInt();
    if (choice >= 1 && choice<=5) {
        return choice;

    } else{
        System.out.println("Try again");
       return getInput();
    }
}

public static void addFile() throws IOException {
    Files.write(
            Paths.get("data", "contacts.txt"),
            Arrays.asList("eggs"), // list with one item
            StandardOpenOption.APPEND
    );
}

public static void  chooseOption () throws IOException {
    int choice;
    do {
        choice=getInput();
        switch (choice){
            case 1 : getAllContacts();
            case 2: addFile();
        }

    }while (choice !=5);
    System.out.println("Bye Bye");
}

public static void getAllContacts() throws IOException {

    Path contactsPath = Paths.get("data", "contacts.txt");
    List<String> contactList = Files.readAllLines(contactsPath);

    for (int i = 0; i < contactList.size(); i += 1) {
        System.out.println((i + 1) + ": " + contactList.get(i));
    }
}
    public static void main(String[] args) throws IOException {
        displayMenu();


    }

    public static void displayMenu() throws IOException {

        System.out.println("1. View contacts.\n" +
                "2. Add a new contact.\n" +
                "3. Search a contact by name.\n" +
                "4. Delete an existing contact.\n" +
                "5. Exit.\n" +
                "Enter an option (1, 2, 3, 4 or 5):");

chooseOption();
addFile();


    }
    }

