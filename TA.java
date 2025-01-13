import java.util.ArrayList;
import java.util.Objects;

public class TA extends Student implements User {
    Professor professorAssigned;
    static ArrayList<TA> TaLoginDatabase = new ArrayList<>();

    // CONSTRUCTOR TO GET STUDENT DETAILS AND ASSIGN PROFESSOR
    public TA(String name, String email, String password, Professor professorAssigned) {
        super(name, email, password);
        this.professorAssigned = professorAssigned;
        TaLoginDatabase.add(this);
    }

    // ASSIGN GRADE OF SUBJECT OF YOUR PROFESSOR
    public void assignGrade() {
        System.out.println("Pick a student by name: ");
        for (Student student : RegisteredStudents.studentLoginDatabase) {
            System.out.println(student);
        }
        String studentChoice = sc.nextLine();
        for (Course course : Course.courseList) {
            if (Objects.equals(professorAssigned.name, course.getProfessor())) {
                for (Student student : RegisteredStudents.studentLoginDatabase) {
                    if (Objects.equals(studentChoice, student.getName())) {
                        System.out.println("Enter new grade for your subject: ");
                        String newGrade = sc.nextLine();
                        course.setGrade(newGrade);
                    }
                }
            }
        }
    }

    //    VIEW GRADES OF STUDENTS

    public void viewGrades() {
        System.out.println("Following are the grades of your assigned professor's students: ");
        for (Course course : Course.courseList) {
            if (Objects.equals(professorAssigned.name, course.getProfessor())) {
                for (Student student : RegisteredStudents.studentLoginDatabase) {
                    System.out.println("Student name: "+ student.getName() + " | Grade in " + course.getCode() + ": " + course.getGrade());
                }
            }
        }
    }


    public void menu() throws DropDeadlinePassedException {
        System.out.println("Select what you want to do: ");
        char redoChoice = 'y';
        while (redoChoice == 'y') {
            System.out.println("1. View Grades.\n" + "2. Assign Grade.\n" + "3. Perform Student Functionalities.");
            int profMenuChoice = sc.nextInt();
            sc.nextLine();
            switch (profMenuChoice) {
                case 1:
                    viewGrades();
                    break;
                case 2:
                    assignGrade();
                    break;
                case 3:
                    Student student = new Student();
                    student.menu();
                default:
                    System.out.println("Invalid input! ");
            }
            System.out.println("Do you want to do more as a Teacher Assistant? (y/n)");
            redoChoice = sc.next().charAt(0);
        }
    }
}


