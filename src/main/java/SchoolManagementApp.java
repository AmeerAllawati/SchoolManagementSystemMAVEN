import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class SchoolManagementApp {

   
//   static ArrayList<ArrayList<String>> student_info = new ArrayList<ArrayList<String>>();
   static HashMap<Integer, ArrayList<String>> student_info = new HashMap<Integer, ArrayList<String>>();
   static int highestID = 0;
   
   public static void main(String[] args) {
       // TODO Auto-generated method stub
       readStudents();
       int main_choice = main_menu();
       
       if (main_choice == 1) {
           // bring admissions menu
           int admissions_coice = admissions_menu();
           
           if (admissions_coice == 1) {
               //bring the register options
               register_student();
               saveRegisteredStudents();
           } else if (admissions_coice == 2) {
               searchStudents();
        }
           
           
       }

   }
   
   public static void searchStudents() {
    // TODO Auto-generated method stub
    System.out.println("Enter the student ID you want to pull: ");
    // Create Scanner object
    Scanner s = new Scanner(System.in);

    // Read the next integer from the screen
    int ID = s.nextInt();
    System.out.println("ID: " + ID);
    System.out.println("Name: " + student_info.get(ID).get(0));
    System.out.println("Email:" + student_info.get(ID).get(1));
}

public static void readStudents() {
    // TODO Auto-generated method stub
    File studentsFile = new File("data/students.csv");
    
    if (studentsFile.exists()) {
        Scanner studentsFileScanner = null;
        try {
            studentsFileScanner = new Scanner(studentsFile);
            while(studentsFileScanner.hasNextLine()) {
                String[] student = studentsFileScanner.nextLine().split(",");
                highestID = Integer.parseInt(student[0]);
                ArrayList<String> studentArr = new ArrayList<String>();
                studentArr.add(student[1]);
                studentArr.add(student[2]);
                student_info.put(Integer.parseInt(student[0]), studentArr);
            }
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
       
    }
  
       
}

public static void saveRegisteredStudents() {
    // TODO Auto-generated method stub
       FileWriter studentsFile = null;
       try {
        studentsFile = new FileWriter("data/students.csv", true);
        for(int key:student_info.keySet()) {
            studentsFile.write(key + ", " + student_info.get(key).get(0) + ", " + student_info.get(key).get(1) + "\n");
        }
    } catch (IOException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    } finally {
        try {
            studentsFile.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
}

public static int main_menu() {
       // TODO Auto-generated method stub
       System.out.println("Enter 1 for Admissions: ");
       
       // Create Scanner object
       Scanner s = new Scanner(System.in);

       // Read the next integer from the screen
       int main_choice = s.nextInt();
       return main_choice;
   }
   
   public static int admissions_menu() {
       // TODO Auto-generated method stub
       System.out.println("Enter 1 to register a student");
       System.out.println("Enter 2 to search the saved students");
       
       // Create Scanner object
       Scanner s = new Scanner(System.in);

       // Read the next integer from the screen
       int admission_choice = s.nextInt();
       return admission_choice;
   }
   
   public static void register_student() {
       highestID++;
       Scanner s = new Scanner(System.in);
       
       System.out.println("Student Name: ");
       String student_name = s.nextLine();
       
//       System.out.println("Student ID: ");
//       int student_ID = s.nextInt();
//       s.nextLine();
       
       System.out.println("Student Email: ");
       String student_email = s.nextLine();
       
       System.out.println("Student Name: " + student_name);
       System.out.println("Student ID: " + highestID);
       System.out.println("Student Email: " + student_email);
       
       ArrayList<String> student = new ArrayList<String>();
       student.add(student_name);
       student.add(student_email);
       
       student_info.put(highestID, student);
       
       
               
       
   }

}
