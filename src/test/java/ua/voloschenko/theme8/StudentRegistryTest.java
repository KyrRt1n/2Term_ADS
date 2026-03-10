package ua.voloschenko.theme8;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class StudentRegistryTest {

    StudentRegistry reg;
    Student Sanya;

    @BeforeEach
    void setUp() {
        reg = new StudentRegistry();
        Sanya = new Student("Sanya", "sanya@mail.ua", "123");
        reg.addStudent(Sanya);
    }

    @Test
    void findById_returnsNull_afterRemove() {
        reg.removeById("123");
        assertNull(reg.findById("123"));
    }

    @Test
    void containsEmail_returnsFalse_afterRemove() {
        reg.removeById("123");
        assertFalse(reg.containsEmail("sanya@mail.ua"));
    }

    @Test
    void canAddSameEmail_afterRemove() {
        reg.removeById("123");
        Student Sanya2 = new Student("Sanya2", "sanya@mail.ua", "789");
        reg.addStudent(Sanya2);
        assertNotNull(reg.findById("789"));
    }
}