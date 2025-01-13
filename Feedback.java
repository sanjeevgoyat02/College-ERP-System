import java.util.ArrayList;

public class Feedback<T> {
    private String courseCode;
    private T feedbackDetail;
    static ArrayList<Feedback<?>> courseFeedbackList= new ArrayList<>();

    public Feedback(String courseCode, T feedback) {
        this.courseCode = courseCode;
        this.feedbackDetail = feedback;
        courseFeedbackList.add(this);
    }

    @Override
    public String toString() {
        return "Feedback Detail for the assigned course is: " + feedbackDetail ;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public T getFeedbackDetail() {
        return feedbackDetail;
    }

    public void setFeedbackDetail(T feedbackDetail) {
        this.feedbackDetail = feedbackDetail;
    }

}
