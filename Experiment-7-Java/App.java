import java.util.*;

public class App {
    public static void main(String[] args) {
        try {
            Scanner sc = new Scanner(System.in);
            StudentDAO dao = new StudentDAO();
            int choice;

            do {
                System.out.println("\n=== Student Management Menu ===");
                System.out.println("1. Add Student");
                System.out.println("2. View All Students");
                System.out.println("3. Update Marks");
                System.out.println("4. Delete Student");
                System.out.println("5. Exit");
                System.out.print("Enter choice: ");
                choice = sc.nextInt();

                switch (choice) {
                    case 1 -> {
                        System.out.print("Enter ID: ");
                        int id = sc.nextInt();
                        sc.nextLine();
                        System.out.print("Enter Name: ");
                        String name = sc.nextLine();
                        System.out.print("Enter Department: ");
                        String dept = sc.nextLine();
                        System.out.print("Enter Marks: ");
                        double marks = sc.nextDouble();
                        dao.addStudent(new Student(id, name, dept, marks));
                    }
                    case 2 -> dao.getAllStudents().forEach(System.out::println);
                    case 3 -> {
                        System.out.print("Enter Student ID to Update Marks: ");
                        int id = sc.nextInt();
                        System.out.print("Enter New Marks: ");
                        double marks = sc.nextDouble();
                        dao.updateStudent(id, marks);
                    }
                    case 4 -> {
                        System.out.print("Enter Student ID to Delete: ");
                        dao.deleteStudent(sc.nextInt());
                    }
                    case 5 -> System.out.println("Exiting...");
                }
            } while (choice != 5);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
