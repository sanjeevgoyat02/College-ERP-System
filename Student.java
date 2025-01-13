import java.net.Inet4Address;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;
import java.time.LocalDate;

// CLASS MADE TO HANDLE COMPLAINTS, AUTO-INCREMENTING THE ID AND ADDING IT TO A STATIC ARRAYLIST
class Complaints {
    public static int complaintIdCounter = 0;
    String complaint;
    int complaintID;
    String complaintStatus = "Unresolved";
    static ArrayList<Complaints> complaintList = new ArrayList<>();

    public Complaints(String complaint) {
        this.complaint = complaint;
        this.complaintID = ++complaintIdCounter;
        complaintList.add(this);
    }

    @Override
    public String toString() {
        return "ID: " + complaintID + " | Complaint: " + complaint + " | Status: " + complaintStatus;
    }
}

// STUDENT CLASS
public class Student implements User {
    private String email;
    private String password;
    private String name;
    private float sgpa;
    private float cgpa;
    boolean complaintStatus;
    static protected int sem = 1;
    private int grade;
    public int totalCreditsRegistered;
    Scanner sc = new Scanner(System.in);
    static ArrayList<Course> registeredCourses = new ArrayList<>();

    // CONSTRUCTOR OVERLOADING

    public Student(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public Student() {
    }


    public void viewAvailableCourses() {
        for (Course course : Course.getCourseList()) {
            System.out.println(course);
        }
    }

    // SHOWS AVAILABLE COURSES AND THEN ALLOWS TO REGISTER AS PER SEMESTER(YOU HAVE TO ENTER SEM ONLY ONCE
    // AFTER THAT IF YOU PASS ALL THE COURSES YOUR SEM IS AUTOMATICALLY UPDATED TO NEXT SEM)
    public void registerCourse() {
        if (sem == 1) {
            System.out.println("Select your current semester: ");
            sem = sc.nextInt();
            sc.nextLine();
        }
        System.out.println("Courses available to you are: ");
        for (Course course : Course.getCourseList()) {
            if (course.getSem() == sem) {
                System.out.println(course);
            }
        }
        char choice = 'y';
        while (choice == 'y' || choice == 'Y') {
            if (totalCreditsRegistered < 20) {
                System.out.println("Select Courses to Register by entering the Course Code: ");
                String selectedCode = sc.nextLine();
                for (Course course : Course.courseList) {
                    if (Objects.equals(selectedCode, course.getCode())) {
                        try {
                            if (course.getEnrollmentLimits() <= 0) {
                                throw new CourseFullException("Course: " + course.getCode() + " is full. Cannot register.");
                            }
                            registeredCourses.add(course);
                            course.setEnrollmentLimits(course.getEnrollmentLimits() - 1);
                            System.out.println("Course added!");
                            totalCreditsRegistered += course.getCredits();
                            System.out.println("Credits left : " + (20 - totalCreditsRegistered));
                            break;

                        } catch (CourseFullException e) {
                            System.out.println(e.getMessage());
                        }
                    }
                }
                System.out.println("Do you want to register any more courses?(y/n)");
                choice = sc.next().charAt(0);
                sc.nextLine();
            } else {
                System.out.println("Credit limit reached! ");
            }
        }
        System.out.println("Your registered courses are : ");
        for (Course course : registeredCourses) {
            System.out.println(course);
        }
    }

    // VIEW SCHEDULE BASED ON YOUR SEMESTER REGISTERED COURSES
    public void viewSchedule() {
        if (registeredCourses.isEmpty()) {
            System.out.println("No course registered!");
        } else {
            for (Course course : registeredCourses) {
                System.out.println("Course: " + course.getTitle() + " | Timing: " + course.getTimings() + " | Professor Name: " + course.getProfessor());
            }
        }
    }

    // CALCULATED AFTER GRADES ARE ASSIGNED TO YOU BY THE ADMIN
    public void trackAcademicProgress() {
        System.out.println("Your grades are: ");
        for (Course course : registeredCourses) {
            System.out.println(course.getCode() + " -> " + course.getGrade());
        }
        System.out.println("Your CGPA is: " + getCgpa());
        System.out.println("Your SGPA is: " + getSgpa());
    }

    // DROP COURSES AFTER YOU HAVE REGISTERED THEM
    public void dropCourses() throws DropDeadlinePassedException {
        System.out.println("Your registered courses are : ");
        for (Course course : registeredCourses) {
            System.out.println(course);
        }
        char choice = 'y';
        while (choice == 'y' || choice == 'Y') {
            System.out.println("Select Courses you want to drop by entering the Course Code: ");
            String selectedCode = sc.nextLine();
            for (Course course : Course.courseList) {
                if (Objects.equals(selectedCode, course.getCode())) {
                    try{
                        if (LocalDate.now().isAfter(course.getDropDeadline())) {
                            throw new DropDeadlinePassedException("The drop deadline for " + course.getCode() + " has passed. You cannot drop this course now.");
                        }
                        registeredCourses.remove(course);
                        System.out.println("Course removed!");
                        totalCreditsRegistered -= course.getCredits();
                        break;
                    }
                    catch(DropDeadlinePassedException e) {
                        System.out.println(e.getMessage());
                    }
                }
            }
            System.out.println("Do you want to drop more courses?(y/n)");
            choice = sc.next().charAt(0);
            sc.nextLine();
        }
        System.out.println("Your registered courses are : ");
        for (Course course : registeredCourses) {
            System.out.println(course);
        }
    }

    // SUBMIT COMPLAINT TO BE RESOLVED BY THE ADMIN
    public void submitComplaint() {
        System.out.println("Enter your complaint: ");
        String complaint = sc.nextLine();
        Complaints complaints = new Complaints(complaint);
    }

    // VIEW COMPLAINTS TO CHECK IF THEY ARE RESOLVED OR NOT
    public void viewComplaints() {
        System.out.println("Your previous complaints are: ");
        for (Complaints complaint : Complaints.complaintList) {
            System.out.println(complaint);
        }
    }

    public void submitFeedback() {
        System.out.println("Your completed courses are: ");
        for (Course course : registeredCourses) {
            if (course.getGrade() != null) {
                System.out.println(course);
            }
        }
        System.out.println("Enter course code to give feedback (if no courses registered then type exit and register and complete course first) : ");
        String courseCode = sc.nextLine();
        System.out.println("Do you want to: \n1.Rate out of 10\n2.Give detailed feedback");
        int choice = sc.nextInt();
        sc.nextLine();
        if (choice == 1) {
            System.out.println("Enter your rating out of 10: ");
            int rating = sc.nextInt();
            Feedback<Integer> feedback = new Feedback<>(courseCode, rating);
        } else if (choice == 2) {
            System.out.println("Enter detailed feedback: ");
            String detailedFeedback = sc.nextLine();
            Feedback<String> feedback = new Feedback<>(courseCode, detailedFeedback);

        }
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getSgpa() {
        float totalCredits = 0;
        float totalMarks = 0;
        for (Course course : registeredCourses) {
            if (course.getSem() == sem - 1) {
                if (Objects.equals(course.getGrade(), "S") || Objects.equals(course.getGrade(), "s")) {
                    totalCredits += course.getCredits();
                    totalMarks += (float) 10 * course.getCredits();
                }
                if (Objects.equals(course.getGrade(), "A") || Objects.equals(course.getGrade(), "a")) {
                    totalCredits += course.getCredits();
                    totalMarks += (float) 9 * course.getCredits();
                }
                if (Objects.equals(course.getGrade(), "B") || Objects.equals(course.getGrade(), "b")) {
                    totalCredits += course.getCredits();
                    totalMarks += (float) 8 * course.getCredits();
                }
                if (Objects.equals(course.getGrade(), "C") || Objects.equals(course.getGrade(), "c")) {
                    totalCredits += course.getCredits();
                    totalMarks += (float) 7 * course.getCredits();
                }
                if (Objects.equals(course.getGrade(), "D") || Objects.equals(course.getGrade(), "d")) {
                    totalCredits += course.getCredits();
                    totalMarks += (float) 6 * course.getCredits();
                }
                if (Objects.equals(course.getGrade(), "E") || Objects.equals(course.getGrade(), "e")) {
                    totalCredits += course.getCredits();
                    totalMarks += (float) 5 * course.getCredits();
                }
            }
        }
        sgpa = totalMarks / totalCredits;
        return sgpa;
    }

    public void setSgpa(float sgpa) {
        this.sgpa = sgpa;
    }

    public float getCgpa() {
        float totalCredits = 0;
        float totalMarks = 0;
        for (Course course : registeredCourses) {
            if (Objects.equals(course.getGrade(), "S") || Objects.equals(course.getGrade(), "s")) {
                totalCredits += course.getCredits();
                totalMarks += (float) 10 * course.getCredits();
            }
            if (Objects.equals(course.getGrade(), "A") || Objects.equals(course.getGrade(), "a")) {
                totalCredits += course.getCredits();
                totalMarks += (float) 9 * course.getCredits();
            }
            if (Objects.equals(course.getGrade(), "B") || Objects.equals(course.getGrade(), "b")) {
                totalCredits += course.getCredits();
                totalMarks += (float) 8 * course.getCredits();
            }
            if (Objects.equals(course.getGrade(), "C") || Objects.equals(course.getGrade(), "c")) {
                totalCredits += course.getCredits();
                totalMarks += (float) 7 * course.getCredits();
            }
            if (Objects.equals(course.getGrade(), "D") || Objects.equals(course.getGrade(), "d")) {
                totalCredits += course.getCredits();
                totalMarks += (float) 6 * course.getCredits();
            }
            if (Objects.equals(course.getGrade(), "E") || Objects.equals(course.getGrade(), "e")) {
                totalCredits += course.getCredits();
                totalMarks += (float) 5 * course.getCredits();
            }
        }
        cgpa = totalMarks / totalCredits;
        return cgpa;
    }

    public void setCgpa(float cgpa) {
        this.cgpa = cgpa;
    }

    public boolean isComplaintStatus() {
        return complaintStatus;
    }

    public void setComplaintStatus(boolean complaintStatus) {
        this.complaintStatus = complaintStatus;
    }

    public int getSem() {
        return sem;
    }

    public void setSem(int sem) {
        this.sem = sem;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }
    public ArrayList<Course> getRegisteredCourses() {
        return registeredCourses;
    }

    @Override
    public void menu() throws DropDeadlinePassedException {
        System.out.println("Select what you want to do: ");
        char redoChoice = 'y';
        while (redoChoice == 'y') {
            System.out.println("1. View Available Courses.\n" +
                    "2. Register for Courses.\n" +
                    "3. View Schedule.\n" +
                    "4. View CGPA and SGPA.\n" +
                    "5. Drop Courses\n" +
                    "6. Submit complaints.\n" +
                    "7. View Complaints.\n" +
                    "8. Submit feedback of course.\n" +
                    "9. Logout.");
            int studentMenuChoice = sc.nextInt();
            sc.nextLine();
            switch (studentMenuChoice) {
                case 1:
                    viewAvailableCourses();
                    break;
                case 2:
                    registerCourse();
                    break;
                case 3:
                    viewSchedule();
                    break;
                case 4:
                    trackAcademicProgress();
                    break;
                case 5:
                    dropCourses();
                    break;
                case 6:
                    submitComplaint();
                    break;
                case 7:
                    viewComplaints();
                    break;
                case 8:
                    submitFeedback();
                    break;
                case 9:
                    return;
                default:
                    System.out.println("Invalid input! ");
            }
            System.out.println("Do you want to more as a student? (y/n)");
            redoChoice = sc.next().charAt(0);
            sc.nextLine();
        }
    }


    @Override
    public String toString() {
        return "Student name: " + getName() + " | " +
                "Grade: " + getGrade();
    }
}
