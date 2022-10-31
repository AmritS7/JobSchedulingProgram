public class Job implements Comparable<Job> {
	private int id;
	private int arrival;
	private int burst;
	private int end;
	private int start;

	public Job(int id, int arrival, int burst) {
		this.id = id;
		this.arrival = arrival;
		this.burst = burst;
		this.end = 0;
		this.start = 0;
	}


	public int getId() {
		return id;
	}

	public int getArrival() {
		return arrival;
	}

	public int getBurst() {
		return burst;
	}
	
	public void setBurst(int CPUBurst) {
		burst = CPUBurst;
	}
	
	public int getEnd() {
		return end;
	}
	
	public void setEnd(int e) {
		end = e;
	}
	
	public int getStart() {
		return start;
	}
	
	public void setStart(int s) {
		start = s;
	}
	
	public int compareTo(Job other) {
		return Integer.valueOf(burst).compareTo(other.getBurst());
	}
	public String toString() {
		return ("Job#" + this.getId() + " " + this.getArrival() + " " + this.getBurst());
	}

}