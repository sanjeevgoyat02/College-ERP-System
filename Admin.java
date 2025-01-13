import java.util.GregorianCalendar;
import java.util.Objects;
import java.util.Scanner;
import java.time.LocalDate;

public class Admin implements User {
    Scanner sc = new Scanner(System.in);

    // VIEW ALL THE COURSES IN THE UNIVERSITY
    public void viewCourses() {
        for (Course course : Course.getCourseList()) {
            System.out.println(course);
        }
    }

    // ADD NEW COURSES TO THE UNIVERSITY CATALOGUE
    public void addCourses() {
        System.out.println("Enter course Code: ");
        String code = sc.nextLine();
        System.out.println("Enter semester: ");
        int sem = sc.nextInt();
        sc.nextLine();
        System.out.println("Enter course title: ");
        String title = sc.nextLine();
        System.out.println("Enter course credits: ");
        int credits = sc.nextInt();
        sc.nextLine();
        System.out.println("Enter class timings(if multiple add them in a single comma separated line): ");
        String timings = sc.nextLine();
        System.out.println("Enter course professors(if multiple add them in a single comma separated line): ");
        String professors = sc.nextLine();
        System.out.println("Enter course syllabus: ");
        String syllabus = sc.nextLine();
        System.out.println("Enter course Pre-Requisites: ");
        String prereq = sc.nextLine();
        System.out.println("Enter course Enrollment Limit: ");
        int el = sc.nextInt();
        sc.nextLine();
        System.out.println("Enter office hours(Enter 0 to leave blank for teacher to add): ");
        String oh = sc.nextLine();
        System.out.println("Enter the last date to drop the course: ");
        LocalDate dropDeadline = LocalDate.parse(sc.nextLine());
        Course adminCourse = new Course(code, sem, title, credits, timings, professors, syllabus, prereq, el, oh, null, dropDeadline);
    }

    // DELETE COURSE FROM UNIVERSITY CATALOGUE
    public void deleteCourses() {
        System.out.println("Enter Course Code of the course you want to delete: ");
        String courseToDelete = sc.nextLine();
        Course.courseList.removeIf(course -> Objects.equals(course.getCode(), courseToDelete));
    }

    // ASSIGN A COURSE TO A PROFESSOR BASED ON EXPERTISE
    public void assignProfessor() {
        System.out.println("Available professors are: ");
        for (Professor professor : Professor.professorLoginDatabase) {
            System.out.println(professor);
        }
        System.out.println("Available courses are: ");
        for (Course course : Course.courseList) {
            System.out.println(course);
        }
        System.out.println("Select course code to assign a professor to it: ");
        String courseSelection = sc.nextLine();
        System.out.println("Select professor by entering name: ");
        String profSelection = sc.nextLine();
        for (Course course : Course.courseList) {
            if (Objects.equals(course.getCode(), courseSelection)) {
                course.setProfessor(profSelection);

            }
        }
    }

    //PRIVATE METHOD TO VIEW COMPLAINTS USED IN RESOLVE COMPLAINTS CLASS
    private void viewComplaints() {
        for (Complaints complaints : Complaints.complaintList) {
            System.out.println(complaints);
        }
    }

    // VIEW AND RESOLVE COMPLAINTS SUBMITTED BY STUDENTS
    public void resolveComplaints() {
        System.out.println("Pending complaints are: ");
        viewComplaints();
        System.out.println("Enter the ID of the complaint to mark as resolved: : ");
        int markAsDone = sc.nextInt();
        sc.nextLine();
        for (Complaints complaints : Complaints.complaintList) {
            if (complaints.complaintID == markAsDone) {
                complaints.complaintStatus = "Resolved";
            }
        }
    }

    // MANAGE STUDENT DETAILS
    public void manageStudents() {
        RegisteredStudents rs = new RegisteredStudents();
        String selectedStudent = null;
        System.out.println("Enter name of student you want to manage: ");
        for (RegisteredStudents registeredStudents : RegisteredStudents.studentLoginDatabase) {
            System.out.println(registeredStudents);
        }
        String studentToManage = sc.nextLine();
        for (RegisteredStudents registeredStudents : RegisteredStudents.studentLoginDatabase) {
            if (Objects.equals(studentToManage, registeredStudents.getName())) {
                rs = registeredStudents;
            }
        }
        System.out.println("Student details are: " + rs);
        System.out.println("1. Update name.\n" +
                "2. Update email.\n" +
                "3. Update password.\n" +
                "4. Update Grades.");
        int updateChoice = sc.nextInt();
        sc.nextLine();
        if (updateChoice == 1) {
            System.out.println("Enter new name: ");
            String newName = sc.nextLine();
            rs.setName(newName);
            System.out.println("Name updated!");
            System.out.println(rs.getName());
        } else if (updateChoice == 2) {
            System.out.println("Enter new email: ");
            String newMail = sc.nextLine();
            rs.setEmail(newMail);
            System.out.println(rs.getEmail());
            System.out.println("Email updated!");
            System.out.println(rs.getEmail());
        } else if (updateChoice == 3) {
            System.out.println("Enter new password: ");
            String newPass = sc.nextLine();
            rs.setPassword(newPass);
            System.out.println(rs.getPassword());
            System.out.println("Password updated!");

        } else if (updateChoice == 4) {
            System.out.println("Registered courses by student are: ");
            for (Course course : Student.registeredCourses) {
                System.out.println(course);
            }

            System.out.println("Grade the courses for the student: ");
            int registeredCourseCount = 0;
            for (Course course : Student.registeredCourses) {
                System.out.println(course);
                System.out.println("Enter Grade: ");
                String grade = sc.nextLine();
                course.setGrade(grade);
                registeredCourseCount++;
            }
            int passCount = 0;
            for (Course course : Student.registeredCourses) {
                if (!(Objects.equals(course.getGrade(), "F")) || !(Objects.equals(course.getGrade(), "f"))) {
                    passCount++;
                }
            }
            if (passCount == registeredCourseCount) {
                Student.sem++;
            }
        }
    }

    @Override
    public void menu() {
        System.out.println("Select what you want to do: ");
        char redoChoice = 'y';
        while (redoChoice == 'y') {
            System.out.println("1. View Courses.\n" +
                    "2. Add Courses.\n" +
                    "3. Remove Courses.\n" +
                    "4. Manage Students.\n" +
                    "5. Assign course to a Professor.\n" +
                    "6. View and Resolve complaints.");
            int adminMenuChoice = sc.nextInt();
            sc.nextLine();
            switch (adminMenuChoice) {
                case 1:
                    viewCourses();
                    break;
                case 2:
                    addCourses();
                    break;
                case 3:
                    deleteCourses();
                    break;
                case 4:
                    manageStudents();
                    break;
                case 5:
                    assignProfessor();
                    break;
                case 6:
                    resolveComplaints();
                    break;
                default:
                    System.out.println("Invalid input!");
            }
            System.out.println("Do you want to more as an admin? (y/n)");
            redoChoice = sc.next().charAt(0);
            sc.nextLine();
        }
    }
}