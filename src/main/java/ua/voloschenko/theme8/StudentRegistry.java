package ua.voloschenko.theme8;

import java.util.*;

public class StudentRegistry {

    List<Student> students = new ArrayList<>();
    Set<String> emails = new HashSet<>();
    Map<String, Student> idMap = new HashMap<>();

    public static void main(String[] args) {
        Student Sanya = new Student("Sanya", "sanya@mail.ua", "123");
        Student Sanya2 = new Student("Sanya", "sanya@mail.ua", "123");
        Student Vlad = new Student("Vlad", "vlad@mail.ua", "456");

        StudentRegistry reg = new StudentRegistry();
        reg.addStudent(Sanya);
        reg.addStudent(Vlad);
        System.out.println("Finding id 123: " +reg.findById("123"));

        reg.removeById("123");
        System.out.println(reg.findById("123"));               // null
        System.out.println(reg.containsEmail("sanya@mail.ua")); // false

        reg.addStudent(Sanya2);
        System.out.println(reg.students.size());
        System.out.println(reg.findById("789"));
    }

    public void addStudent(Student st) {
        if(!emails.contains(st.getEmail())) {
            students.add(st);
            emails.add(st.getEmail());
            idMap.put(st.getId(), st);
        }
        else
            System.out.println("Email already exists");
    }

    public Student findById(String id) {
        return idMap.get(id);
    }

    public boolean containsEmail(String email) {
        return emails.contains(email);
    }

    public void removeById(String id) {
        Student st = findById(id);
        if (st != null) {
            students.remove(st);
            emails.remove(st.getEmail());
            idMap.remove(id);
        }
    }
}
