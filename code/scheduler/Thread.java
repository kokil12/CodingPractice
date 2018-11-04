import com.sun.tools.javac.util.Pair;

import java.util.LinkedList;
import java.util.List;

public class Thread {


    int availabilityTime;
    int threadId;
    List<Pair<String, Integer>> jobList = new LinkedList<>();

    public Thread(int availabilityTime, int count) {
        this.availabilityTime = availabilityTime;
        this.threadId = count;
    }

    public int getAvailabilityTime() {
        return availabilityTime;
    }

    public int getThreadId() {
        return threadId;
    }

    public List<Pair<String, Integer>> getJobList() {
        return jobList;
    }

    public void setAvailabilityTime(int availabilityTime) {
        this.availabilityTime = availabilityTime;
    }
}
