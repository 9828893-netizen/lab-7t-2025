package ua.opnu;

import java.util.Arrays;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class Main {

    // Клас Student, адаптований для Завдань 2 і 4
    static class Student {
        private String lastName;
        private String firstName;
        private String group;
        private int[] marks;

        public Student(String lastName, String firstName, String group, int[] marks) {
            this.lastName = lastName;
            this.firstName = firstName;
            this.group = group;
            this.marks = marks;
        }

        public String getLastName() {
            return lastName;
        }

        public String getFirstName() {
            return firstName;
        }

        public int[] getMarks() {
            return marks;
        }
    }

    // Завдання 2: Метод фільтрації за одним предикатом
    public static Student[] filterStudents(Student[] students, Predicate<Student> condition) {
        Student[] temp = new Student[students.length];
        int count = 0;
        for (Student s : students) {
            if (condition.test(s)) {
                temp[count++] = s;
            }
        }
        return Arrays.copyOf(temp, count);
    }

    // Завдання 3: Метод фільтрації за двома умовами (два предикати)
    public static int[] filterTwoConditions(int[] input, Predicate<Integer> p1, Predicate<Integer> p2) {
        int[] temp = new int[input.length];
        int count = 0;
        for (int i : input) {
            if (p1.test(i) && p2.test(i)) {
                temp[count++] = i;
            }
        }
        return Arrays.copyOf(temp, count);
    }

    // Завдання 5: Метод, який приймає Predicate та Consumer
    public static void processIf(int[] input, Predicate<Integer> condition, Consumer<Integer> action) {
        for (int i : input) {
            if (condition.test(i)) {
                action.accept(i);
            }
        }
    }

    // Завдання 7: Метод stringify
    public static String[] stringify(int[] input, Function<Integer, String> func) {
        String[] result = new String[input.length];
        for (int i = 0; i < input.length; i++) {
            result[i] = func.apply(input[i]);
        }
        return result;
    }

    public static void main(String[] args) {

        // --- ЗАВДАННЯ 1 ---
        System.out.println("--- ЗАВДАННЯ 1 ---");
        Predicate<Integer> isPrime = n -> {
            if (n <= 1) return false;
            for (int i = 2; i <= Math.sqrt(n); i++) {
                if (n % i == 0) return false;
            }
            return true;
        };
        System.out.println("Чи 7 просте? " + isPrime.test(7));

        // --- ЗАВДАННЯ 2 ---
        System.out.println("\n--- ЗАВДАННЯ 2 ---");
        Student[] students = {
                new Student("Шевченко", "Тарас", "АІ-252", new int[]{90, 85, 95}),
                new Student("Глухова", "Анна", "АІ-252", new int[]{55, 80, 70}), // заборгованість
                new Student("Українка", "Леся", "АІ-252", new int[]{95, 100, 100})
        };

        Predicate<Student> hasDebt = s -> {
            for (int mark : s.getMarks()) {
                if (mark < 60) return true;
            }
            return false;
        };

        Student[] debtors = filterStudents(students, hasDebt);
        for (Student s : debtors) {
            System.out.println("Студент з боргом: " + s.getLastName());
        }

        // --- ЗАВДАННЯ 3 ---
        System.out.println("\n--- ЗАВДАННЯ 3 ---");
        int[] numbers = {1, 2, 3, 4, 5, 6, 10, 12, 15, 20};
        // Умова 1: число парне; Умова 2: число більше 5
        int[] filteredNums = filterTwoConditions(numbers, n -> n % 2 == 0, n -> n > 5);
        System.out.println("Парні та більші за 5: " + Arrays.toString(filteredNums));

        // --- ЗАВДАННЯ 4 ---
        System.out.println("\n--- ЗАВДАННЯ 4 ---");
        Consumer<Student> printFullName = s -> System.out.println(s.getLastName() + " " + s.getFirstName());
        for (Student s : students) {
            printFullName.accept(s);
        }

        // --- ЗАВДАННЯ 5 ---
        System.out.println("\n--- ЗАВДАННЯ 5 ---");
        int[] testArr = {-5, 10, 0, -3, 8};
        processIf(testArr, n -> n > 0, n -> System.out.println("Додатнє число: " + n));

        // --- ЗАВДАННЯ 6 ---
        System.out.println("\n--- ЗАВДАННЯ 6 ---");
        Function<Integer, Integer> powerOfTwo = n -> (int) Math.pow(2, n);
        int[] exponents = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        for (int exp : exponents) {
            System.out.println("2^" + exp + " = " + powerOfTwo.apply(exp));
        }

        // --- ЗАВДАННЯ 7 ---
        System.out.println("\n--- ЗАВДАННЯ 7 ---");
        int[] digits = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        Function<Integer, String> digitToString = n -> {
            switch (n) {
                case 0:
                    return "нуль";
                case 1:
                    return "один";
                case 2:
                    return "два";
                case 3:
                    return "три";
                case 4:
                    return "чотири";
                case 5:
                    return "п'ять";
                case 6:
                    return "шість";
                case 7:
                    return "сім";
                case 8:
                    return "вісім";
                case 9:
                    return "дев'ять";
                default:
                    return "невідомо";
            }
        };
        String[] stringifiedArray = stringify(digits, digitToString);
        System.out.println(Arrays.toString(stringifiedArray));
    }
}