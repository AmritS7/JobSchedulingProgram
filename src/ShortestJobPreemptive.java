import java.util.*;

public class ShortestJobPreemptive {
	
	//This is an arraylist just so that I can show when certain jobs get "kicked" out of the CS by adding new objects to show that
	//Did not want to make changes to the original job file
	private ArrayList<Job> jobs = new ArrayList<Job>();;
	private PriorityQueue<Job> work;

	public ShortestJobPreemptive(Job[] jobArr, int t) {
		work = new PriorityQueue<Job>();
		
		int limit = t;
		int time = 0;
		int id = 0;
		int index;
		Job top = null;

		//For some reason when I change the value of the job object it affects the
		//original one instead of the one from jobs.clone() in the client class
		for (int i = 0; i < 10; i++) {
			this.jobs.add(new Job(jobArr[i].getId(), jobArr[i].getArrival(), jobArr[i].getBurst()));
		}
		
		
		while (time < limit + 1) {

			//Adds the next job when the current time matches the arrival time
			if (this.jobs.get(id).getArrival() == time) {
				work.add(this.jobs.get(id));
				
				if (id < jobArr.length - 1) {
					id++;
				}
			}
			
			//This is just to initialize it once the first job has been added
			if (time < 1) {
				top = work.peek();
			}
			
			//"Kicks" the job that was first in place because a new job have higher priority
			if (top != work.peek() && !work.isEmpty()) {
				work.remove(top);
				index = this.jobs.indexOf(top);
				
				//Changes the "kicked" job to show that it "paused" at this time
				this.jobs.get(index).setEnd(time);
				
				//Adds a new job to the job arraylist which is a copy of the "kicked" job
				this.jobs.add(new Job(top.getId(), top.getArrival(), top.getBurst()));
				this.jobs.get(this.jobs.size() - 1).setStart(0);
				
				//Adds this "clone" back to the queue
				//This "clone" will be regularly updated but the original one will not
				work.add(this.jobs.get(this.jobs.size() - 1));
			}
			
			//Just so that if the queue is empty the program won't bother looking at the top
			if (!work.isEmpty()) {
				
				if (work.peek().getBurst() == 0) {
					
					//This is for the first job, because its starting point is 0 but if you check the next if statement(not inside this one)
					//The starting point won't stay 0 but will later become 1
					if (work.peek().getId() == 1) {
						work.peek().setStart(work.peek().getArrival());
					}
					
					top = null;
					work.poll().setEnd(time);
				}
				
				//Same thing here because this is after the top job is removed from the queue
				if (!work.isEmpty()) {
					if (work.peek().getStart() == 0) {
						work.peek().setStart(time);
					}
					
					top = work.peek();
					work.peek().setBurst(work.peek().getBurst() - 1);
				}
			}
			
			//Just increment the time
			time++;
			
		}
		
	}

	public void printJobs() {
		//Prints out the jobs and their information
		//0 means they did not start and/or ended
		
		int id = 0;
		
		//Just to see the highest value of the id
		for (int x = 0; x < this.jobs.size(); x++) {
			if (id < this.jobs.get(x).getId()) {
				id = this.jobs.get(x).getId();
			}
		}
		
		//Repeats this process until all of the job id has been shown
		for (int x = 1; x <= id; x++) {
			
			System.out.println("\nJob#" + this.jobs.get(x - 1).getId() + " enters at " + this.jobs.get(x - 1).getArrival());
			
			//Checks the whole arraylist to match the jobs' id
			for (int y = 0; y < this.jobs.size(); y++) {
				
				if (this.jobs.get(y).getId() == x) {
					System.out.println("-begin task at " + this.jobs.get(y).getStart() + " and ends at " + jobs.get(y).getEnd());
					
					//This is so that the jobs that finish will show the turnaround time
					if (this.jobs.get(y).getBurst() == 0) {
						System.out.println("-Turnaround Time: " + (this.jobs.get(y).getEnd() - this.jobs.get(y).getArrival()));
						System.out.println("Job Complete");
					}
					
					//This is so that the user can know which job did not finish
					else if (this.jobs.get(y).getEnd() == 0) {
						System.out.println("Job Incomplete");
					}
				}
			}
		}
	}
}