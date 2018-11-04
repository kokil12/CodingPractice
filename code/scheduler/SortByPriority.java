import java.util.Comparator;

public class SortByPriority implements Comparator<Job> {
    public int compare(Job j1, Job j2)
    {
        if (j1.getPriority() > j2.getPriority())
            return 1;
        else if (j1.getPriority() < j2.getPriority())
            return -1;
        else {
            if (j1.getUserType().equalsIgnoreCase(j2.getUserType()))
                return j2.getDuration()-j1.getDuration();
            else if (j1.getUserType().equalsIgnoreCase("Admin") && j2.getUserType().equalsIgnoreCase("Root"))
                return 1;
            else if (j1.getUserType().equalsIgnoreCase("User"))
                return 1;
            else
                return -1;
        }
    }
}
