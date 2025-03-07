import java.util.*;

public class Student {
    private String name;
    private String group;
    private int course;
    private Map<String, Integer> grades;

    // Конструктор класса Студент
    public Student(String name, String group, int course, Map<String, Integer> grades) {
        this.name = name;
        this.group = group;
        this.course = course;
        this.grades = grades;
    }

    public String getName() {
        return name;
    }

    public int getCourse() {
        return course;
    }
    // Вычисление среднего балла
    public double getAverageGrade() {
        return grades.values().stream().mapToInt(Integer::intValue).average().orElse(0);
    }
    // Метод для перевода на следующий курс
    public void promoteToNextCourse() {
        if (getAverageGrade() >= 3) {
            course++;
        }
    }
    // Метод для отчисления студента
    public static void removeUnderperformingStudents(Set<Student> students) {
        students.removeIf(student -> student.getAverageGrade() < 3);
    }

    public static void printStudents(Set<Student> students, int course) {
        System.out.println("Студенты курса " + course + ":");
        for (Student student : students) {
            if (student.getCourse() == course) {
                System.out.println(student.getName());
            }
        }
    }
}
