import java.util.Comparator;

public class SortByDuration implements Comparator<Job> {

    public int compare(Job j1, Job j2)
    {
        if (j1.getDuration() > j2.getDuration())
            return 1;
        else if (j1.getDuration() < j2.getDuration())
            return -1;
        else {
            return j1.getPriority() - j2.getPriority();
        }
    }
}
