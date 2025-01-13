import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;

public class Course {
    private String code;
    private int sem;
    private String title;
    private int credits;
    private String timings;
    private String professor;
    private String syllabus;
    private String preRequisites;
    private int enrollmentLimits;
    private String officeHours;
    private String grade;
    static protected List<Course> courseList = new ArrayList<>();
    private LocalDate dropDeadline;

    public Course(String code, int sem, String title, int credits, String timings, String professor, String syllabus, String preRequisites, int enrollmentLimits, String officeHours,String grade, LocalDate dropDeadline) {
        this.code = code;
        this.sem = sem;
        this.title = title;
        this.credits = credits;
        this.timings = timings;
        this.professor = professor;
        this.syllabus = syllabus;
        this.preRequisites = preRequisites;
        this.enrollmentLimits = enrollmentLimits;
        this.officeHours = officeHours;
        this.grade = grade;
        courseList.add(this);
        this.dropDeadline = dropDeadline;
    }
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public String getTimings() {
        return timings;
    }

    public void setTimings(String timings) {
        this.timings = timings;
    }

    public String getProfessor() {
        return professor;
    }

    public void setProfessor(String professor) {
        this.professor = professor;
    }

    public int getSem() {
        return sem;
    }

    public void setSem(int sem) {
        this.sem = sem;
    }

    public static List<Course> getCourseList() {
        return courseList;
    }

    public static void setCourseList(List<Course> courseList) {
        Course.courseList = courseList;
    }

    public String getSyllabus() {
        return syllabus;
    }

    public void setSyllabus(String syllabus) {
        this.syllabus = syllabus;
    }

    public String getPreRequisites() {
        return preRequisites;
    }

    public void setPreRequisites(String preRequisites) {
        this.preRequisites = preRequisites;
    }

    public int getEnrollmentLimits() {
        return enrollmentLimits;
    }

    public void setEnrollmentLimits(int enrollmentLimits) {
        this.enrollmentLimits = enrollmentLimits;
    }

    public String getOfficeHours() {
        return officeHours;
    }

    public void setOfficeHours(String officeHours) {
        this.officeHours = officeHours;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public LocalDate getDropDeadline() { return dropDeadline;}

    @Override
    public String toString() {
        return "Course Code: " + code +
                " | Semester: " + sem +
                " | Title: " + title +
                " | Credits: " + credits +
                " | Class Timings: " + timings +
                " | Professor: " + professor +
                " | Syllabus: " + syllabus +
                " | Pre Requisites: " + preRequisites +
                " | Enrollment Limit: " + enrollmentLimits +
                " | Office Hours: " + officeHours + "\n";
    }
}