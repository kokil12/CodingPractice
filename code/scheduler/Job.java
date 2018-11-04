public class Job {

    String jobName;
    int duration;
    int priority;
    int deadline;
    String userType;

    public Job(String jobName, int duration, int priority, int deadline, String userType) {
        this.jobName = jobName;
        this.duration = duration;
        this.priority = priority;
        this.deadline = deadline;
        this.userType = userType;
    }

    public String getJobName() {
        return jobName;
    }

    public int getDuration() {
        return duration;
    }

    public int getPriority() {
        return priority;
    }

    public int getDeadline() {
        return deadline;
    }

    public String getUserType() {
        return userType;
    }
}