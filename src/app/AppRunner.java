package app;

import db.TaskStore;
import model.Task;
import planner.TaskPlanner;

import java.util.List;
import java.util.Scanner;

public class AppRunner {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        TaskStore store = new TaskStore();
        TaskPlanner planner = new TaskPlanner();

        while (true) {

            System.out.println("\n1. Add Task");
            System.out.println("2. Generate Weekly Schedule");
            System.out.println("3. Exit");
            System.out.print("Choose option: ");

            String choiceInput = sc.nextLine().trim();

            if (!choiceInput.matches("[1-3]")) {
                System.out.println("❌ Invalid option. Enter 1, 2 or 3 only.");
                continue;
            }

            int choice = Integer.parseInt(choiceInput);

            // ---------- OPTION 1 ----------
            if (choice == 1) {

                System.out.print("Task title: ");
                String title = sc.nextLine();

                System.out.print("Deadline (1-5): ");
                String deadlineInput = sc.nextLine();
                if (!deadlineInput.matches("[1-5]")) {
                    System.out.println("❌ Deadline must be between 1 and 5.");
                    continue;
                }
                int deadline = Integer.parseInt(deadlineInput);

                System.out.print("Revenue: ");
                String revenueInput = sc.nextLine();
                if (!revenueInput.matches("\\d+")) {
                    System.out.println("❌ Revenue must be a number.");
                    continue;
                }
                int revenue = Integer.parseInt(revenueInput);

                store.addTask(title, deadline, revenue);
                System.out.println("✅ Task added successfully.");

            }
            // ---------- OPTION 2 ----------
            else if (choice == 2) {

                List<Task> tasks = store.fetchAllTasks();
                Task[] week = planner.planWeek(tasks);

                String[] days = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday"};
                int totalRevenue = 0;

                System.out.println("\n--- Weekly Schedule ---");
                for (int i = 0; i < 5; i++) {
                    if (week[i] != null) {
                        System.out.println(days[i] + ": " +
                                week[i].getTitle() + " | ₹" + week[i].getRevenue());
                        totalRevenue += week[i].getRevenue();
                    } else {
                        System.out.println(days[i] + ": Free");
                    }
                }

                System.out.println("Total Revenue: ₹" + totalRevenue);
            }
            // ---------- OPTION 3 ----------
            else {
                System.out.println("Exiting program.");
                break;
            }
        }
    }
}
