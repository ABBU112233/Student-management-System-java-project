import java.util.Map;
import java.util.Scanner;

public class StudentOperations extends UserOperations<Student> implements Reportable {
    //Calling FileHandler.loadFromCSV() in the constructor of StudentOperations means:
    //As soon as you create a StudentOperations object in Main,
    //it will automatically load all existing students from students.csv into userMap.
    public StudentOperations() {
        FileHandler.loadFromCSV("students.csv", this.userMap);
    }
    public void addUser() {
        Scanner sc = new Scanner(System.in);
        
        try{
            System.out.print("Enter ID: ");
            int id = sc.nextInt();
            sc.nextLine(); // consume leftover newline

            System.out.print("Enter Name: ");
            String name = sc.nextLine();

            System.out.print("Enter Age: ");
            int age = sc.nextInt();

            if(age<0 || age >100){
                throw new InvalidAgeException("Age must be between 0 and 100");
            }

            // sc.close();
            Student student = new Student();
            student.setId(id);
            student.setName(name);  
            student.setAge(age);

            userMap.put(id, student);
                System.out.println("Student added successfully.");
        }
        catch(InvalidAgeException e){
            System.out.println("ERROR: " + e.getMessage());
        }
        catch(Exception e){
            System.out.println("Unexpected error: " + e.getMessage());
        }
        

    }
    public void generateReport() {
        if (userMap.isEmpty()) {
            System.out.println("No students to display.");
        } else {
            System.out.println("Student Report:");
            for (Map.Entry<Integer, Student> entry : userMap.entrySet()) {
                entry.getValue().displayInfo();
                System.out.println("-----");
            }
            FileHandler.exportToReport("report.txt", userMap);
        }
    }
    //creating method to pass usermap to save in student.csv file
    public Map<Integer, Student> getUserMap() {
        return userMap;
    }
    
}
