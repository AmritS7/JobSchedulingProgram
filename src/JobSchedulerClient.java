import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class JobSchedulerClient {
    public static void main(String args[]) {
    	Scanner input = new Scanner(System.in);
        Scanner text;
        int time;
        Job[] jobs = new Job[10];
        try {
            text = new Scanner(new File("JobsToRun"));

            //Puts all of the job in the text file into an empty array
            for (int x = 0; x < 10; x++) {
                jobs[x] = new Job(text.nextInt(), text.nextInt(), text.nextInt());
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        System.out.println("How long should the simulation be? (enter -1 for no limit)");
        time = input.nextInt();
        if (time < 0) time = Integer.MAX_VALUE / 2;
        
        System.out.println("\n\n********************************************");
        System.out.println("First Come First Serve");
        System.out.println("********************************************\n\n");
        FirstComeFirstServed fcfs = new FirstComeFirstServed(jobs.clone(), time);
        fcfs.printUnderMax();
        fcfs.printTable();
        
        System.out.println("\n\n********************************************");
        System.out.println("Shortest Job Preemptive");
        System.out.println("********************************************\n\n");
        ShortestJobPreemptive preemptive = new ShortestJobPreemptive(jobs.clone(), time);
        preemptive.printJobs();

        System.out.println("\n\n********************************************");
        System.out.println("Shortest Job Non-preemptive");
        System.out.println("********************************************\n\n");
        ShortestJobNonPreemptive nonPreemptive = new ShortestJobNonPreemptive(jobs.clone(), time);
        for (JobCompletion completion : nonPreemptive.getCompletions())
            System.out.println(completion);
        
        input.close();
    }
}
