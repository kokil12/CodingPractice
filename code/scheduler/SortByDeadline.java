import java.util.Comparator;

public class SortByDeadline implements Comparator<Job> {

    public int compare(Job j1, Job j2)
    {
        if (j1.getDeadline() != j2.getDeadline())
            return j1.getDeadline() -j2.getDeadline();
        else if(j1.getPriority() != j2.getPriority())
            return j1.getPriority() - j2.getPriority();
        else
            return j1.getDuration() - j2.getDuration();
    }
}
