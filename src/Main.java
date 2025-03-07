import java.util.*;

public class Main {
    public static void main(String[] args) {
        // Задание 1: Работа со студентами
        Set<Student> students = new HashSet<>();
        students.add(new Student("Алиса", "Группа A", 1, Map.of("Математика", 4, "Физика", 3)));
        students.add(new Student("Маруся", "Группа B", 2, Map.of("Математика", 2, "Физика", 2)));
        students.add(new Student("Кристина", "Группа C", 1, Map.of("Математика", 5, "Физика", 4)));

        System.out.println("Первоначальный состав студентов:");
        Student.printStudents(students, 1);

        Student.removeUnderperformingStudents(students);
        students.forEach(Student::promoteToNextCourse);

        System.out.println("\nПосле перевода на новый курс и исключений:");
        Student.printStudents(students, 1);

        // Задание 2: Телефонный справочник
        PhoneDirectory phoneDirectory = new PhoneDirectory();
        phoneDirectory.add("Иванов", "+ 7 123-456-78-90");
        phoneDirectory.add("Петров", "+7 987-654-32-10");
        phoneDirectory.add("Сидоров", "+7 555-555-55-55");

        System.out.println("\nНомера телефонов Петрова:");
        phoneDirectory.get("Петров").forEach(System.out::println);

        System.out.println("Номера телефонов Сидорова:");
        phoneDirectory.get("Сидоров").forEach(System.out::println);
    }
}