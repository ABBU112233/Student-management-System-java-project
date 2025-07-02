// Use generics like <T extends Person> so it can work with Student, Teacher, etc.
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;


public class  FileHandler {
    //method to write content of map in to file
    public static <T extends Person> void saveToCSV(String filename,Map<Integer, T> userMap){
        // STEP 1: Create a File object from the given filename
        File file = new File(filename);

        // STEP 2: Check if file doesn't exist or is empty
        boolean isEmpty = !file.exists() || file.length() == 0;
        
        // FileWriter	Writes characters to a file
        // BufferedWriter	Speeds up FileWriter using a buffer (memory layer)
        // This Java trick automatically closes your file after writing:

        // try (BufferedWriter writer = new BufferedWriter(new FileWriter("filename.csv"))) {

        //the above code(new FileWriter) will eraze the prevoius csv file and write hedder from begening and values 
        //but we want to make students scalable i.e we want to store and add/append sutdent records
        //so we need to over ride the function of write using below code


        // STEP 3: TRY-WITH-RESOURCES BLOCK to open file safely
        try (
            // Create FileWriter in append mode i.e re writable mode
            FileWriter fw = new FileWriter(file, true);
        
            // Wrap FileWriter with BufferedWriter
            BufferedWriter writer = new BufferedWriter(fw);
        ) {
            // STEP 4: If file is empty, write the header
            if (isEmpty) {
                writer.write("ID,Name,Age");
                writer.newLine();
            }
            

            // Loop through the map and write each student
            for (Map.Entry<Integer, T> entry : userMap.entrySet()) {
                T person = entry.getValue();
                int id = entry.getKey();

                String line = id + ", " + person.getName() + ", " + person.getAge();
                writer.write(line);
                writer.newLine();
            }

            System.out.println("Students saved to " + filename);
        } 
        catch (IOException e) {
            System.out.println("Error saving file: " + e.getMessage());
        }
    }

    //method to read file into map before writing
    public static <T extends Person> void loadFromCSV(String filename, Map<Integer, T> userMap){

        //Step 1 creating object of files
        File file = new File(filename);

        // 2. Check if file exists
        if (!file.exists()) {
            System.out.println("CSV file not found. Skipping load.");
            return;
        }
        // 3. Open file using BufferedReader
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {

            // 4. Skip the header
            reader.readLine();

            // 5. Read each line
            String line;
            while ((line = reader.readLine()) != null) {

                
                // 6. Split the line by comma
                String[] parts = line.split(",");

                if (parts.length != 3) {
                    System.out.println("⚠️ Skipping malformed line: " + line);
                    continue;
                }
                
                try{
                    // 7. Parse values
                    int id = Integer.parseInt(parts[0].trim());
                    String name = parts[1].trim();
                    int age = Integer.parseInt(parts[2].trim());

                    // 8. Create new Student object
                    Student student = new Student();
                    student.setId(id);
                    student.setName(name);
                    student.setAge(age);

                    // 9. Put into map
                    userMap.put(id, (T) student);
                }
                catch(NumberFormatException e){
                    System.out.println("Skipping line with invalid number: " + line);
                }
                
            }

        System.out.println("Students loaded from file.");

        } 
        catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }

    //generating report in text format
    public static <T extends Person> void exportToReport(String filename, Map<Integer, T> userMap){
        //Create a File object using the filename
        File file=new File(filename);
        // Not in append mode – we want to overwrite previous report every time.
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(file))){
            writer.write("===== STUDENT REPORT =====");
            writer.newLine();
            writer.newLine();
            for (Map.Entry<Integer, T> entry : userMap.entrySet()) {
                T person = entry.getValue();
                int id = entry.getKey();
            
                writer.write("ID: " + id);
                writer.newLine();
                writer.write("Name: " + person.getName());
                writer.newLine();
                writer.write("Age: " + person.getAge());
                writer.newLine();
                writer.write("---------------------------");
                writer.newLine();
            }
            System.out.println("Report exported to " + filename);
        }
        catch (IOException e) {
            System.out.println(" Error writing report: " + e.getMessage());
        }
    }
    
    
}
