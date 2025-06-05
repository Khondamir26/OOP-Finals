// --- Person.java ---
import java.util.*;
import java.time.LocalDateTime;
import java.util.Scanner;

abstract class Person {
    protected String id;
    protected String name;

    public Person(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public abstract String getRole();
}

// --- Employee.java ---
class Employee extends Person {
    public Employee(String id, String name) {
        super(id, name);
    }

    @Override
    public String getRole() {
        return "Employee";
    }
}

// --- Visitor.java ---
class Visitor extends Person {
    public Visitor(String id, String name) {
        super(id, name);
    }

    @Override
    public String getRole() {
        return "Visitor";
    }
}

// --- AccessLog.java ---


class AccessLog {
    private String personId;
    private String name;
    private String action; // "ENTER" or "EXIT"
    private LocalDateTime timestamp;

    public AccessLog(String personId, String name, String action) {
        this.personId = personId;
        this.name = name;
        this.action = action;
        this.timestamp = LocalDateTime.now();
    }

    public String toString() {
        return timestamp + " - " + action + " - " + name + " (" + personId + ")";
    }

    public String getPersonId() { return personId; }
    public LocalDateTime getTimestamp() { return timestamp; }
    public String getAction() { return action; }
}

// --- Custom Exceptions ---
class PersonNotFoundException extends Exception {
    public PersonNotFoundException(String message) { super(message); }
}

class DuplicateEntryException extends Exception {
    public DuplicateEntryException(String message) { super(message); }
}

class ExitWithoutEntryException extends Exception {
    public ExitWithoutEntryException(String message) { super(message); }
}

// --- SecuritySystem.java ---


class SecuritySystem {
    private Map<String, Person> personRegistry = new HashMap<>();
    private Map<String, Boolean> presenceStatus = new HashMap<>();
    private List<AccessLog> accessLogs = new ArrayList<>();

    public void registerPerson(Person person) {
        personRegistry.put(person.getId(), person);
        presenceStatus.put(person.getId(), false);
    }

    public void logEntry(String id) throws PersonNotFoundException, DuplicateEntryException {
        if (!personRegistry.containsKey(id)) throw new PersonNotFoundException("Person not found");
        if (presenceStatus.get(id)) throw new DuplicateEntryException("Person already inside");

        Person person = personRegistry.get(id);
        accessLogs.add(new AccessLog(id, person.getName(), "ENTER"));
        presenceStatus.put(id, true);
    }

    public void logExit(String id) throws PersonNotFoundException, ExitWithoutEntryException {
        if (!personRegistry.containsKey(id)) throw new PersonNotFoundException("Person not found");
        if (!presenceStatus.get(id)) throw new ExitWithoutEntryException("Person not inside");

        Person person = personRegistry.get(id);
        accessLogs.add(new AccessLog(id, person.getName(), "EXIT"));
        presenceStatus.put(id, false);
    }

    public void showCurrentPresence() {
        System.out.println("--- People currently inside ---");
        for (String id : presenceStatus.keySet()) {
            if (presenceStatus.get(id)) {
                Person p = personRegistry.get(id);
                System.out.println(p.getName() + " (" + p.getId() + ") - " + p.getRole());
            }
        }
    }

    public void showAllLogs() {
        System.out.println("--- Access Logs ---");
        for (AccessLog log : accessLogs) {
            System.out.println(log);
        }
    }

    public void searchLogsByPersonId(String id) {
        System.out.println("--- Logs for ID: " + id + " ---");
        for (AccessLog log : accessLogs) {
            if (log.getPersonId().equals(id)) {
                System.out.println(log);
            }
        }
    }
}

// --- Main.java ---


public class main {
    public static void main(String[] args) {
        SecuritySystem system = new SecuritySystem();

        // --- DEMO Section ---
        system.registerPerson(new Employee("E101", "Alice"));
        system.registerPerson(new Visitor("V201", "Bob"));
        try {
            system.logEntry("E101");
            system.logEntry("V201");
            system.logExit("E101");
            system.logExit("V201");
        } catch (Exception e) {
            System.out.println("Demo Error: " + e.getMessage());
        }

        system.showCurrentPresence();
        system.showAllLogs();
        system.searchLogsByPersonId("E101");

        // --- INTERACTIVE MENU ---
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\n--- Security System Menu ---");
            System.out.println("1. Register Employee");
            System.out.println("2. Register Visitor");
            System.out.println("3. Log Entry");
            System.out.println("4. Log Exit");
            System.out.println("5. View Current Presence");
            System.out.println("6. View All Logs");
            System.out.println("7. Search Logs by ID");
            System.out.println("8. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            try {
                switch (choice) {
                    case 1:
                        System.out.print("Enter ID: ");
                        String eId = scanner.nextLine();
                        System.out.print("Enter Name: ");
                        String eName = scanner.nextLine();
                        system.registerPerson(new Employee(eId, eName));
                        break;
                    case 2:
                        System.out.print("Enter ID: ");
                        String vId = scanner.nextLine();
                        System.out.print("Enter Name: ");
                        String vName = scanner.nextLine();
                        system.registerPerson(new Visitor(vId, vName));
                        break;
                    case 3:
                        System.out.print("Enter ID: ");
                        String entryId = scanner.nextLine();
                        system.logEntry(entryId);
                        break;
                    case 4:
                        System.out.print("Enter ID: ");
                        String exitId = scanner.nextLine();
                        system.logExit(exitId);
                        break;
                    case 5:
                        system.showCurrentPresence();
                        break;
                    case 6:
                        system.showAllLogs();
                        break;
                    case 7:
                        System.out.print("Enter ID to search: ");
                        String searchId = scanner.nextLine();
                        system.searchLogsByPersonId(searchId);
                        break;
                    case 8:
                        System.out.println("Exiting system.");
                        return;
                    default:
                        System.out.println("Invalid option.");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
}

