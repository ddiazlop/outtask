package test;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import src.Priorities;
import src.Student;

public class StudentTest {
    @Test
    public void testDefaultInput() {
        List<String> events = new ArrayList<>();
        events.add("12");
        events.add("ENTER John 3.75 50");
        events.add("ENTER Mark 3.8 24");
        events.add("ENTER Shafaet 3.7 35");
        events.add("SERVED");
        events.add("SERVED");
        events.add("ENTER Samiha 3.85 36");
        events.add("SERVED");
        events.add("ENTER Ashley 3.9 42");
        events.add("ENTER Maria 3.6 46");
        events.add("ENTER Anik 3.95 49");
        events.add("ENTER Dan 3.95 50");
        events.add("SERVED");

        List<Student> students = Priorities.getStudents(events);

        assertEquals("[Dan, Ashley, Shafaet, Maria]", students.toString());

    }

    @Test
    public void testEmptyQ() {
        List<String> events = new ArrayList<>();
        events.add("2");
        events.add("ENTER John 3.75 50");
        events.add("SERVED");

        List<Student> students = Priorities.getStudents(events);

        assertEquals("[EMPTY]", students.toString());
    }

    @Test
    public void testEmptyInput() {
        List<String> events = new ArrayList<>();

        List<Student> students = Priorities.getStudents(events);

        assertEquals("[EMPTY]", students.toString());
    }

}
