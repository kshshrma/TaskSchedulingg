package planner;

import model.Task;

import java.util.ArrayList;
import java.util.List;

public class TaskPlanner {

    public Task[] planWeek(List<Task> tasks) {

        Task[] week = new Task[5]; // 5 working days
        List<Task> usedTasks = new ArrayList<>();

        for (int day = 5; day >= 1; day--) {

            Task bestTask = null;

            for (Task task : tasks) {
                if (!usedTasks.contains(task) && task.getDeadline() >= day) {

                    if (bestTask == null ||
                            task.getRevenue() > bestTask.getRevenue()) {
                        bestTask = task;
                    }
                }
            }

            if (bestTask != null) {
                week[day - 1] = bestTask;
                usedTasks.add(bestTask);
            }
        }

        return week;
    }
}
