package org.example.entity;

import java.util.HashSet;
import java.util.Set;

public class TaskData {
    private Set<Task> annsTasks;
    private Set<Task> bobsTasks;
    private Set<Task> carolsTasks;
    private Set<Task> unassignedTasks;

    public TaskData(Set<Task> annsTasks, Set<Task> bobsTasks, Set<Task> carolsTasks, Set<Task> unassignedTasks) {
        this.annsTasks = annsTasks;
        this.bobsTasks = bobsTasks;
        this.carolsTasks = carolsTasks;
        this.unassignedTasks = unassignedTasks;
    }

    public Set<Task> getTasks(String type) {
        return switch (type.toLowerCase()) {
            case "ann" -> annsTasks;
            case "bob" -> bobsTasks;
            case "carol" -> carolsTasks;
            case "all" -> getUnion(annsTasks, bobsTasks, carolsTasks, unassignedTasks);
            default -> throw new IllegalArgumentException();
        };
    }

    public Set<Task> getUnassignedTasks() {
        return unassignedTasks;
    }

    public void addTask(Task task, String assigneeList) {
        String[] assignees = assigneeList.split(",\\s*");
        for (String assignee : assignees) {
            switch (assignee.trim().toLowerCase()) {
                case "ann":
                    annsTasks.add(task);
                    break;
                case "bob":
                    bobsTasks.add(task);
                    break;
                case "carol":
                    carolsTasks.add(task);
                    break;
                default:
                    unassignedTasks.add(task);
                    break;
            }
        }
    }

    public Set<Task> getTasksAssignedToMultipleEmployees() {
        Set<Task> commonTasks = getIntersection(annsTasks, bobsTasks);
        commonTasks.addAll(getIntersection(bobsTasks, carolsTasks));
        commonTasks.addAll(getIntersection(carolsTasks, annsTasks));
        return commonTasks;
    }

    public Set<Task> getUnion(Set<Task>... sets) {
        Set<Task> union = new HashSet<>();
        for (Set<Task> set : sets) {
            union.addAll(set);
        }
        return union;
    }

    public Set<Task> getIntersection(Set<Task> set1, Set<Task> set2) {
        Set<Task> intersection = new HashSet<>(set1);
        intersection.retainAll(set2);
        return intersection;
    }

    public Set<Task> getDifferences(Set<Task> set1, Set<Task> set2) {
        Set<Task> difference = new HashSet<>(set1);
        difference.removeAll(set2);
        return difference;
    }
}
