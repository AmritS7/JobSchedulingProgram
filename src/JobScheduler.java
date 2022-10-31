import java.util.*;

public abstract class JobScheduler {
    protected List<JobCompletion> completions;
    protected Queue<Job> jobs;
    protected PriorityQueue<Job> readyJobs;

    public JobScheduler(Job[] jobArr) {
        completions = new ArrayList<>();
        jobs = new LinkedList<>(Arrays.asList(jobArr));
        readyJobs = new PriorityQueue<>();
    }

    public abstract List<JobCompletion> getCompletions();
}
