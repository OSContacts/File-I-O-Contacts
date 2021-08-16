import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.*;

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

public static String getNewContact(){
    Scanner in = new Scanner(System.in);
    System.out.print("Enter First name: ");
    String firstName = in.next();
    System.out.print("Enter Last name: ");
    String lastName = in.next();
    System.out.print("Enter Phone #: ");
    Long number = in.nextLong();
    return firstName + "@" + lastName + " "  + number;
}

public static void addFile(String newContact) throws IOException {
    Files.write(
            Paths.get("data", "contacts.txt"),
            Arrays.asList(newContact), // list with one item
            StandardOpenOption.APPEND
    );
}

public static void  chooseOption () throws IOException {
    int choice;
    do {
        choice=getInput();
        switch (choice){
            case 1 : getAllContacts();
                    displayMenu();
                    continue;
            case 2 : addFile(getNewContact());
                    displayMenu();
                    continue;
            case 3 : search(getKey());
                    displayMenu();
                    continue;
            case 4 : delete(getKey());
                    displayMenu();

        }

    }while (choice !=5);
    System.out.println("Bye Bye");
}

public static void getAllContacts() throws IOException {

    Path contactsPath = Paths.get("data", "contacts.txt");
    List<String> contactList = Files.readAllLines(contactsPath);

    for (int i = 0; i < contactList.size(); i += 1) {
        if(!contactList.get(i).equals("")) {
            String line = contactList.get(i);
            String[] nameHalf = line.split(" ");
            String[] name = nameHalf[0].split("@");
            System.out.println(name[0] + " " + name[1] + " " + nameHalf[1]);
        }
    }
}

public static HashMap putDataInHash() throws IOException {
    Path contactsPath = Paths.get("data", "contacts.txt");
    List<String> contactList = Files.readAllLines(contactsPath);

    HashMap<String, String> contacts = new HashMap<>();
    for(String contact : contactList) {
        String[] data = contact.split(" ");
        contacts.put(data[0], contact);
    }
    return contacts;
}

public static void search(String query) throws IOException {
        HashMap table = putDataInHash();

    System.out.println(table.get(query));

}

public static String getKey(){
    Scanner in = new Scanner(System.in);
    System.out.print("Enter First Last: ");
    return String.join("@", in.nextLine().split(" "));
}

public static void delete(String query) throws IOException {
    List<String> lines = Files.readAllLines(Paths.get("data", "contacts.txt"));
    List<String> newList = new ArrayList<>();

    for (String line : lines) {
        HashMap table = putDataInHash();
        Object searchString = table.get(query);
        if (line.equals(searchString)) {
            newList.add("");
            continue;
        }
        newList.add(line);
    }

    Files.write(Paths.get("data", "contacts.txt"), newList);

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
    }
    }

