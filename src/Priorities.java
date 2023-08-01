package src;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class Priorities {

    /**
     * Returns a list of students in the order in which they are served.
     * 
     * The time complexity of this method is O(n * log(n)) where n is the number of
     * students in the queue, since its the complexity of the students.sort()
     * method.
     * The space complexity is O(n) where n is the number of students in the queue,
     * since we are creating a new list of students.
     * 
     * @param events
     * @return
     */
    public static List<Student> getStudents(List<String> events) {
        if (events == null || events.isEmpty()) {
            return getEmptyStudentList();
        }

        int k = Integer.parseInt(events.get(0));
        Comparator<Student> comparator = Comparator.comparing(Student::getCgpa).reversed()
                .thenComparing(Student::getName)
                .thenComparing(Student::getId);
        PriorityQueue<Student> queue = new PriorityQueue<Student>(k, comparator);

        for (String event : events) {
            if (event.startsWith("ENTER")) {
                String[] parts = event.split(" ");
                String name = parts[1];
                double cgpa = Double.parseDouble(parts[2]);
                int id = Integer.parseInt(parts[3]);
                queue.add(new Student(id, name, cgpa));
            } else if (event.startsWith("SERVED")) {
                queue.poll();
            }

        }

        // This could be changed to return an empty list if the queue is empty.
        if (queue.isEmpty()) {
            return getEmptyStudentList();
        }

        List<Student> students = new ArrayList<>(queue);
        students.sort(comparator);

        return students;

    }

    private static List<Student> getEmptyStudentList() {
        List<Student> students = new ArrayList<>();
        Student emptyStudent = new Student(0, "EMPTY", 0.0);
        students.add(emptyStudent);
        return new ArrayList<>(students);
    }

    /**
     * Returns a list of students in the order in which they are served.
     * 
     * With this implementation we are not using the students.sort() to sort the
     * final list and instead using PriorityQueue.poll()
     * to get the students in the order in which they are served. This maybe makes
     * time complexity a little better, but I do not think it
     * makes a big difference.
     * 
     * @param events
     * @return
     */
    public static List<Student> getStudents2(List<String> events) {
        if (events == null || events.isEmpty()) {
            return new ArrayList<>();
        }

        int k = Integer.parseInt(events.get(0));
        Comparator<Student> comparator = Comparator.comparing(Student::getCgpa).reversed()
                .thenComparing(Student::getName)
                .thenComparing(Student::getId);
        PriorityQueue<Student> queue = new PriorityQueue<Student>(k, comparator);

        for (String event : events) {
            if (event.startsWith("ENTER")) {
                String[] parts = event.split(" ");
                String name = parts[1];
                double cgpa = Double.parseDouble(parts[2]);
                int id = Integer.parseInt(parts[3]);
                queue.add(new Student(id, name, cgpa));
            } else if (event.startsWith("SERVED")) {
                queue.poll();
            }

        }

        List<Student> students = new ArrayList<>();
        while (!queue.isEmpty()) {
            students.add(queue.poll());
        }

        return students;

    }
}
