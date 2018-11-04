import com.sun.tools.javac.util.Pair;

import java.util.*;

public class JobScheduling {

    private final List<Job> jobList;
    private final List<Thread> threadPool;

    public JobScheduling(int numThreads) {
        this.jobList = createNewJobList();
        this.threadPool = createNewThreadPool(numThreads);
    }

    private List<Job> createNewJobList() {
        Job j1 = new Job("j1", 10, 0, 10, "root");
        Job j2 = new Job("j2", 20, 0, 40, "admin");
        Job j3 = new Job("j3", 15, 2, 40, "root");
        Job j4 = new Job("j4", 30, 1, 40, "user");
        Job j5 = new Job("j5", 10, 2, 30, "user");

        List<Job> jobList = new LinkedList<>();
        jobList.add(j1);
        jobList.add(j2);
        jobList.add(j3);
        jobList.add(j4);
        jobList.add(j5);

        return jobList;
    }

    private List<Thread> createNewThreadPool(int numThreads) {
        List<Thread> threadPool = new LinkedList<>();
        int count=0;
        for (int i =0 ;i< numThreads; i++) {
            ++count;
            threadPool.add(new Thread(0, count));
        }
        return threadPool;
    }

    public List<Job> getJobList() {
        return jobList;
    }

    public List<Thread> getThreadPool() {
        return threadPool;
    }

    private static Thread getIdleThread(List<Thread> threadPool, int curentTime) {
        for (Thread thread : threadPool) {
            if (thread.getAvailabilityTime() <= curentTime) {
                return thread;
            }
        }
        return null;
    }

    private static void printJobListPerThread(List<Thread> threadPool) {
        for (Thread thread : threadPool) {
            System.out.println("Job scheduled on thread "+ thread.getThreadId());
            for (Pair job : thread.getJobList()) {
                System.out.println("Name: "+ job.fst+"       StartTime: "+ job.snd);
            }
        }
    }

    private static boolean isSchedulable(int currentTime, Job job) {
        if (currentTime+job.getDuration() > job.getDeadline()) {
            return false;
        }
        return true;
    }

    private static int scheduleJob(Job job, List<Thread> threadPool, int currentTime, PriorityQueue<Integer> threadIdleTimeQueue) {
        Thread thread = getIdleThread(threadPool, currentTime);
        int duration = job.getDuration();
        if (thread != null) {
            thread.getJobList().add(new Pair<>(job.getJobName(), currentTime));
            thread.setAvailabilityTime(currentTime+duration);
            threadIdleTimeQueue.add(currentTime+duration);
        } else {
            //All thread busy, wait untill thread becomes idle
            while (threadIdleTimeQueue.peek() <= currentTime) {
                threadIdleTimeQueue.poll();
            }
            currentTime = threadIdleTimeQueue.poll();
            Thread thread1 = getIdleThread(threadPool, currentTime); //can never be null
            if (!isSchedulable(currentTime, job)) {
                System.out.println("Job "+job.getJobName()+" Missed Deadline");
            } else {
                thread1.getJobList().add(new Pair<>(job.getJobName(), currentTime));
                thread1.setAvailabilityTime(currentTime + duration);
                threadIdleTimeQueue.add(currentTime + duration);
            }
        }
        return currentTime;
    }

    private static void shortestJobFirst(List<Job> jobList, List<Thread> threadPool) {
        Collections.sort(jobList, new SortByDuration());
        System.out.println("Scheduling Priority Order");
        for (Job job : jobList) {
            System.out.println(job.getJobName());
        }
        System.out.println();
        int currentTime = 0;
        PriorityQueue<Integer> threadIdleTimeQueue = new PriorityQueue<>();
        for (Job job : jobList) {
            if (!isSchedulable(currentTime, job)) {
                System.out.println("Job "+job.getJobName()+" Missed Deadline");
            } else {
                currentTime = scheduleJob(job, threadPool, currentTime, threadIdleTimeQueue);
            }
        }
        System.out.println();
        printJobListPerThread(threadPool);
    }

    private static void firstComeFirstServe(List<Job> jobList, List<Thread> threadPool) {
        System.out.println("Scheduling Priority Order");
        for (Job job : jobList) {
            System.out.println(job.getJobName());
        }
        System.out.println();
        int currentTime = 0;
        PriorityQueue<Integer> threadIdleTimeQueue = new PriorityQueue<>();
        for (Job job : jobList) {
            if (!isSchedulable(currentTime, job)) {
                System.out.println("Job "+job.getJobName()+" Missed Deadline");
            } else {
                currentTime = scheduleJob(job, threadPool, currentTime, threadIdleTimeQueue);
            }
        }
        //Print job list per thread
        System.out.println();
        printJobListPerThread(threadPool);
    }

    private static void fixedPriorityScheduling(List<Job> jobList, List<Thread> threadPool) {
        Collections.sort(jobList, new SortByPriority());
        System.out.println("Scheduling Priority Order");
        for (Job job : jobList) {
            System.out.println(job.getJobName());
        }
        System.out.println();
        int currentTime = 0;
        PriorityQueue<Integer> threadIdleTimeQueue = new PriorityQueue<>();
        for (Job job : jobList) {
            if (!isSchedulable(currentTime, job)) {
                System.out.println("Job "+job.getJobName()+" Missed Deadline");
            } else {
                currentTime = scheduleJob(job, threadPool, currentTime, threadIdleTimeQueue);
            }
        }
        System.out.println();
        printJobListPerThread(threadPool);
    }

    private static void earliestDeadlineFirst(List<Job> jobList, List<Thread> threadPool) {
        Collections.sort(jobList, new SortByDeadline());
        System.out.println("Scheduling Priority Order");
        for (Job job : jobList) {
            System.out.println(job.getJobName());
        }
        System.out.println();
        int currentTime = 0;
        PriorityQueue<Integer> threadIdleTimeQueue = new PriorityQueue<>();
        for (Job job : jobList) {
            if (!isSchedulable(currentTime, job)) {
                System.out.println("Job: "+job.getJobName()+" missed deadline");
            } else {
                currentTime = scheduleJob(job, threadPool, currentTime, threadIdleTimeQueue);
            }
        }
        System.out.println();
        printJobListPerThread(threadPool);
    }

    public static void main(String[] args) {


        int numThreads = 3;



        JobScheduling obj1 = new JobScheduling(numThreads);
        JobScheduling obj2 = new JobScheduling(numThreads);
        JobScheduling obj3 = new JobScheduling(numThreads);
        JobScheduling obj4 = new JobScheduling(numThreads);
        System.out.println("********************************************");
        System.out.println("FCFS Result ------->");
        firstComeFirstServe(obj1.getJobList(), obj1.getThreadPool());
        System.out.println("********************************************");
        System.out.println();
        System.out.println("********************************************");
        System.out.println("SJF Result ------->");
        shortestJobFirst(obj2.getJobList(), obj2.getThreadPool());
        System.out.println("********************************************");
        System.out.println();
        System.out.println("********************************************");
        System.out.println("FPS Result ------->");
        fixedPriorityScheduling(obj3.getJobList(), obj3.getThreadPool());
        System.out.println("********************************************");
        System.out.println();
        System.out.println("********************************************");
        System.out.println("EDF Result ------->");
        earliestDeadlineFirst(obj4.getJobList(), obj4.getThreadPool());
        System.out.println("********************************************");

    }
}
