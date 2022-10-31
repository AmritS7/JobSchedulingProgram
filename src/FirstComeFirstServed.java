import java.util.*;

public class FirstComeFirstServed {
	
	private Job[] jobList = new Job[10];
	private Queue<Job> systemQueue = new LinkedList<Job>();
	private ArrayList<Job> underMax = new ArrayList<Job>();
	private int maxTime;
	
	
	public FirstComeFirstServed(Job[] jobArray, int time) {
		
		//For some reason when the value of the job object is change it affects the
		//original one instead of the one from jobs.clone() in the client class
		for (int i = 0; i < 10; i++) {
			this.jobList[i] = new Job(jobArray[i].getId(), jobArray[i].getArrival(), jobArray[i].getBurst());
		}
		
		//For some reason there was an error with the scanner so I moved the scanner
		//from this code and from my code to the client code
		maxTime = time;
		System.out.println();
		
		int currTime = 0;
	
		do {//serves as the system
			for (Job entering : jobList) {
				if (currTime == entering.getArrival()) {
					System.out.println("Job#" + entering.getId() + " Time of Entry: " + currTime);
	
					if (systemQueue.isEmpty() && entering.getArrival() == 0) {
						int burst = entering.getBurst(); 
						entering.setBurst(burst+1);
					}
					systemQueue.add(entering); // added to systemQueue when arrival time and currentTime are the same
					entering.setStart(currTime);
				}
			}
	
			if (!systemQueue.isEmpty()) {
				Job head = systemQueue.peek();
				int dBurst = head.getBurst();
				head.setBurst(dBurst-1);
	
				if (head.getBurst() == 0) {
					System.out.println("Job#" + head.getId() + " Time of Removal: " + currTime);
	
					if (currTime < maxTime) {
						underMax.add(head);
					}
					head.setEnd(currTime);
					systemQueue.remove(); //removed when burst == 0
				}
			}
			currTime++;
		} while (!systemQueue.isEmpty());
	}

	// prints all jobs that have completed before the specified maxTime
	public void printUnderMax() {
		System.out.println("\nAll of the jobs that completed before time " + maxTime + " are:");
		for (Job underTime : underMax) {
			System.out.print("Job#" + underTime.getId() + ", ");
		}
	}
	
	// prints results in table like form -- as asked for in instructions
	public void printTable() {
		System.out.println();
		System.out.println();
		System.out.println("Job\tTime In System\tTurnaround Time");
		for (Job jobs : jobList) {
			System.out.println(
					"Job#" + jobs.getId() + "\t" + jobs.getArrival() + "-" + jobs.getEnd() + "\t\t" + (jobs.getEnd() - jobs.getStart()));
		}
	}
}