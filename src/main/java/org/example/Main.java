package org.example;

import org.example.entity.Priority;
import org.example.entity.Status;
import org.example.entity.Task;
import org.example.entity.TaskData;

import java.util.HashSet;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        Task task1 = new Task("Project A", "Implement feature X", "Ann", Status.ASSIGNED, Priority.HIGH);
        Task task2 = new Task("Project B", "Fix bug Y", "Bob", Status.IN_PROGRESS, Priority.MED);
        Task task3 = new Task("Project C", "Refactor code Z", "Carol", Status.IN_QUEUE, Priority.LOW);
        Task task4 = new Task("Project A", "Design UI for feature X", "Bob, Ann", Status.ASSIGNED, Priority.MED);
        Task task5 = new Task("Project B", "Test new functionality", "Carol, Bob", Status.IN_PROGRESS, Priority.LOW);
        Task task6 = new Task("Project C", "Refactor database schema", "Ann, Carol", Status.ASSIGNED, Priority.HIGH);
        Task task7 = new Task("Project A", "Write documentation", "", Status.IN_QUEUE, Priority.MED);
        Task task8 = new Task("Project B", "Implement security updates", "", Status.IN_QUEUE, Priority.HIGH);
        Task task9 = new Task("Project C", "Optimize code performance", "", Status.IN_QUEUE, Priority.MED);


        Set<Task> annsTasks = new HashSet<>();
        Set<Task> bobsTasks = new HashSet<>();
        Set<Task> carolsTasks = new HashSet<>();
        Set<Task> unassignedTasks = new HashSet<>();


        TaskData taskData = new TaskData(annsTasks, bobsTasks, carolsTasks, unassignedTasks);

        Task[] tasks = {task1, task2, task3, task4, task5, task6, task7, task8, task9};
        for (Task task : tasks) {
            taskData.addTask(task, task.getAssignee());
        }


        testGetAllTasks(taskData);
        paraBreak();
        testGetIndividualTasks(taskData);
        paraBreak();
        testGetUnassignedTasks(taskData);
        paraBreak();
        testGetSharedTasks(taskData);
        paraBreak();
        testGetDifference(taskData);
    }

    private static void paraBreak() {
        System.out.println("———————————————————————");
    }

    private static void testGetAllTasks(TaskData taskData) {
        System.out.println("All tasks:");
        System.out.println(taskData.getTasks("all"));
    }

    private static void testGetIndividualTasks(TaskData taskData) {
        System.out.println("Ann's tasks:");
        System.out.println(taskData.getTasks("ann"));
        System.out.println("Bob's tasks:");
        System.out.println(taskData.getTasks("bob"));
        System.out.println("Carol's tasks:");
        System.out.println(taskData.getTasks("carol"));
    }

    private static void testGetUnassignedTasks(TaskData taskData) {
        System.out.println("Unassigned tasks:");
        System.out.println(taskData.getUnassignedTasks());
    }

    private static void testGetSharedTasks(TaskData taskData) {
        System.out.println("Tasks assigned to multiple employees:");
        System.out.println(taskData.getTasksAssignedToMultipleEmployees());
    }

    private static void testGetDifference(TaskData taskData) {
        System.out.println("Ann's tasks that she doesn't share with Bob:");
        System.out.println(taskData.getDifferences(taskData.getTasks("ann"), taskData.getTasks("bob")));
    }
}