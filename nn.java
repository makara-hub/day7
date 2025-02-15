import java.util.*;

class Job {
    String id;
    int deadline, profit;

    Job(String id, int deadline, int profit) {
        this.id = id;
        this.deadline = deadline;
        this.profit = profit;
    }
}

class JobScheduling {
    public static int[] scheduleJobs(List<Job> jobs) {
        jobs.sort((a, b) -> b.profit - a.profit);

        int maxDeadline = jobs.stream().mapToInt(j -> j.deadline).max().orElse(0);
        String[] schedule = new String[maxDeadline + 1];
        boolean[] filled = new boolean[maxDeadline + 1];

        int totalProfit = 0, taskCount = 0;

        for (Job job : jobs) {
            for (int t = job.deadline; t > 0; t--) {
                if (!filled[t]) { // If slot is free
                    schedule[t] = job.id;
                    filled[t] = true;
                    totalProfit += job.profit;
                    taskCount++;
                    break;
                }
            }
        }

        System.out.print("Scheduled Tasks: ");
        for (String task : schedule) {
            if (task != null) System.out.print(task + " ");
        }
        System.out.println();

        return new int[]{totalProfit, taskCount}; 
    }

    public static void main(String[] args) {
        List<Job> jobs = Arrays.asList(
            new Job("Task1", 2, 100),
            new Job("Task2", 1, 50),
            new Job("Task3", 2, 10),
            new Job("Task4", 1, 20),
            new Job("Task5", 3, 30)
        );

        int[] result = scheduleJobs(jobs);
        System.out.println("Max Profit = " + result[0]);
    }
}
