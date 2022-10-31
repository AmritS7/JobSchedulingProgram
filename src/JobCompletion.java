public class JobCompletion {
    private int jobId;
    private int jobStart;
    private int jobEnd;
    private int jobTurnaround;

    public JobCompletion(int jobId, int jobStart, int jobEnd, int jobTurnaround) {
        this.jobId = jobId;
        this.jobStart = jobStart;
        this.jobEnd = jobEnd;
        this.jobTurnaround = jobTurnaround;
    }

    public int getJobId() {return jobId; }

    public int getStart() {
        return jobStart;
    }

    public int getEnd() {
        return jobEnd;
    }

    public void setStart(int start) {
        this.jobStart = start;;
    }

    public void setEnd(int end) {
        this.jobEnd = end;
    }


    public String toString() {
        return String.format("Job Id: %d, Start: %d, End: %d, Turnaround: %d",
                jobId, jobStart, jobEnd, jobTurnaround);
    }
}
