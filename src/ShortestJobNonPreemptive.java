import java.util.*;

public class ShortestJobNonPreemptive extends JobScheduler {
    private int time;
    public ShortestJobNonPreemptive(Job[] jobArr, int time) {
        super(jobArr);
        this.time = time;
    }

    public List<JobCompletion> getCompletions() {
        List<JobCompletion> completions = super.completions;
        Queue<Job> jobs = super.jobs;
        PriorityQueue<Job> readyJobs = super.readyJobs;

        if (jobs.size() == 0) return completions;

        sortJobs(jobs.poll(), 0);

        System.out.printf("%d Jobs Completed.%n", completions.size());
        return completions;
    }

    private void sortJobs(Job current, int start)  {
        List<JobCompletion> completions = super.completions;
        if (current == null) {
            // ends if no jobs left
            if (jobs.size() > 0) {
                Job next = jobs.poll();
                sortJobs(next, next.getArrival());
            }
            return;
        }

        int end = start + current.getBurst();
        if (end > time) return;

        completions.add(new JobCompletion(current.getId(), start, end, (end - current.getArrival())));

        // get all next jobs in current interval
        while (jobs.peek() != null && jobs.peek().getArrival() <= end) readyJobs.offer(jobs.poll());

        sortJobs(readyJobs.poll(), end);
    }
}