import java.util.*;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StudentOperations ops = new StudentOperations();

        while (true) {
            System.out.println("\n=== MENU ===");
            System.out.println("1. Add Student");
            System.out.println("2. Search Student");
            System.out.println("3. Delete Student");
            System.out.println("4. Generate Report");
            System.out.println("5. Exit");

            int choice = sc.nextInt();
            
            switch (choice) {
                case 1:
                    ops.addUser();
                    break;
                case 2:
                    System.out.print("Enter ID to search: ");
                    int idSearch = sc.nextInt();
                    ops.searchUserById(idSearch);
                    break;
                case 3:
                    System.out.print("Enter ID to delete: ");
                    int idDelete = sc.nextInt();
                    ops.deleteUserById(idDelete);
                    break;
                case 4:
                    ops.generateReport();
                    break;
                case 5:
                    // Save all student records to CSV before exiting
                    FileHandler.saveToCSV("students.csv", ops.getUserMap());
                    System.out.println("Exiting... Data saved.");
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
            // sc.close();
        }
    }
}