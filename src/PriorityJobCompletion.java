public class PriorityJobCompletion extends JobCompletion {
    private int jobPriorityBurst;
    private int jobArrival;
    public PriorityJobCompletion(Job job) {
        super(job.getId(), 0, 0, 0);
        jobPriorityBurst = job.getBurst();
        jobArrival = job.getArrival();
    }

    public void decrementBurst() {
        this.jobPriorityBurst--;
    }

    public int getJobPriorityBurst() {return jobPriorityBurst; }

    public int getJobArrival() {return jobArrival; }
}
