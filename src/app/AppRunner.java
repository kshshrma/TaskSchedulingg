package app;

import db.TaskStore;
import model.Task;
import planner.TaskPlannerDP;

import java.util.List;
import java.util.Scanner;

public class AppRunner {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        TaskStore store = new TaskStore();
        TaskPlannerDP planner = new TaskPlannerDP();

        while (true) {

            System.out.println("\n1. Add Task");
            System.out.println("2. View All Tasks");
            System.out.println("3. Delete Task");
            System.out.println("4. Calculate Optimal Revenue (DP)");
            System.out.println("5. Exit");
            System.out.print("Choose option: ");

            String input = sc.nextLine().trim();

            if (!input.matches("[1-5]")) {
                System.out.println("❌ Enter a valid option (1–5)");
                continue;
            }

            int choice = Integer.parseInt(input);

            // ADD TASK
            if (choice == 1) {

                System.out.print("Task title: ");
                String title = sc.nextLine();

                System.out.print("Deadline (1-5): ");
                String d = sc.nextLine();
                if (!d.matches("[1-5]")) {
                    System.out.println("❌ Invalid deadline");
                    continue;
                }

                System.out.print("Revenue: ");
                String r = sc.nextLine();
                if (!r.matches("\\d+")) {
                    System.out.println("❌ Invalid revenue");
                    continue;
                }

                store.addTask(title, Integer.parseInt(d), Integer.parseInt(r));
                System.out.println("✅ Task added successfully.");
            }

            // VIEW TASKS
            else if (choice == 2) {

                List<Task> tasks = store.fetchAllTasks();

                if (tasks.isEmpty()) {
                    System.out.println("No tasks found.");
                    continue;
                }

                System.out.println("\n--- TASK LIST ---");
                for (Task t : tasks) {
                    System.out.println(
                            "ID: " + t.getId() +
                                    ", Title: " + t.getTitle() +
                                    ", Deadline: " + t.getDeadline() +
                                    ", Revenue: ₹" + t.getRevenue()
                    );
                }
            }

            // DELETE TASK
            else if (choice == 3) {

                System.out.print("Enter Task ID to delete: ");
                String idInput = sc.nextLine();

                if (!idInput.matches("\\d+")) {
                    System.out.println("❌ Invalid ID");
                    continue;
                }

                store.deleteTask(Integer.parseInt(idInput));
            }

            // DP CALCULATION
            else if (choice == 4) {

                List<Task> tasks = store.fetchAllTasks();

                if (tasks.isEmpty()) {
                    System.out.println("No tasks available.");
                    continue;
                }

                int maxRevenue = planner.calculateMaxRevenue(tasks);
                System.out.println("\n✅ Maximum Revenue (DP): ₹" + maxRevenue);
            }

            // EXIT
            else {
                System.out.println("Exiting program.");
                break;
            }
        }
    }
}
