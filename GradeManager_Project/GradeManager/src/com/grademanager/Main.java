package com.grademanager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Main class — runs the console menu for the Student Grade Manager.
 */
public class Main {

    static List<Student> students = new ArrayList<>();
    static Scanner scanner = new Scanner(System.in);
    static FileHandler fileHandler = new FileHandler("data/grades.csv");

    public static void main(String[] args) {

        // Load saved data on startup
        try {
            students = fileHandler.load();
            if (!students.isEmpty())
                System.out.println("Loaded " + students.size() + " student(s) from saved data.");
        } catch (IOException e) {
            System.out.println("No saved data found. Starting fresh.");
        }

        System.out.println("\n========================================");
        System.out.println("   Student Grade Manager");
        System.out.println("========================================");

        boolean running = true;
        while (running) {
            printMenu();
            int choice = readInt("Enter choice: ");

            switch (choice) {
                case 1 -> addStudent();
                case 2 -> addSubjectToStudent();
                case 3 -> viewStudent();
                case 4 -> viewAllStudents();
                case 5 -> removeSubject();
                case 6 -> saveData();
                case 0 -> { running = false; System.out.println("Goodbye!"); }
                default -> System.out.println("Invalid option. Try again.");
            }
        }

        scanner.close();
    }

    static void printMenu() {
        System.out.println("\n----------------------------------------");
        System.out.println(" 1. Add Student");
        System.out.println(" 2. Add Subject to Student");
        System.out.println(" 3. View Student Report");
        System.out.println(" 4. View All Students");
        System.out.println(" 5. Remove Subject");
        System.out.println(" 6. Save Data");
        System.out.println(" 0. Exit");
        System.out.println("----------------------------------------");
    }

    // ── Add a new student ──────────────────────────────────────

    static void addStudent() {
        System.out.println("\n--- Add Student ---");
        System.out.print("Enter name: ");
        String name = scanner.nextLine().trim();

        System.out.print("Enter roll number: ");
        String roll = scanner.nextLine().trim();

        // Check duplicate
        for (Student s : students) {
            if (s.getRollNumber().equalsIgnoreCase(roll)) {
                System.out.println("A student with this roll number already exists.");
                return;
            }
        }

        System.out.print("Enter semester (e.g. Sem 3): ");
        String semester = scanner.nextLine().trim();

        students.add(new Student(name, roll, semester));
        System.out.println("Student '" + name + "' added successfully.");
    }

    // ── Add a subject to an existing student ──────────────────

    static void addSubjectToStudent() {
        System.out.println("\n--- Add Subject ---");
        Student student = findStudent();
        if (student == null) return;

        System.out.print("Subject name: ");
        String subjectName = scanner.nextLine().trim();

        double totalMarks = readDouble("Total marks possible: ");
        double marksObtained = readDouble("Marks obtained: ");

        if (marksObtained > totalMarks) {
            System.out.println("Marks obtained cannot exceed total marks.");
            return;
        }

        int credits = readInt("Credit hours (1-6): ");

        student.addSubject(new Subject(subjectName, marksObtained, totalMarks, credits));
        System.out.println("Subject '" + subjectName + "' added.");
    }

    // ── View a student's full report ───────────────────────────

    static void viewStudent() {
        System.out.println("\n--- Student Report ---");
        Student student = findStudent();
        if (student == null) return;
        printReport(student);
    }

    static void printReport(Student student) {
        System.out.println("\n========================================");
        System.out.println("  Name       : " + student.getName());
        System.out.println("  Roll No    : " + student.getRollNumber());
        System.out.println("  Semester   : " + student.getSemester());
        System.out.println("========================================");

        if (student.getSubjects().isEmpty()) {
            System.out.println("  No subjects added yet.");
            return;
        }

        System.out.println();
        System.out.printf("  %-25s | %-13s | %-8s | %-8s | %s%n",
                "Subject", "Marks", "Percent", "Grade", "Credits");
        System.out.println("  " + "-".repeat(72));

        for (Subject s : student.getSubjects()) {
            System.out.println("  " + s);
        }

        System.out.println("  " + "-".repeat(72));
        System.out.printf("  Overall Percentage : %.2f%%%n", student.getOverallPercentage());
        System.out.printf("  CGPA               : %.2f / 10.0%n", student.calculateCGPA());
        System.out.println("========================================");
    }

    // ── View summary of all students ──────────────────────────

    static void viewAllStudents() {
        if (students.isEmpty()) {
            System.out.println("No students found.");
            return;
        }
        System.out.println("\n--- All Students ---");
        System.out.printf("%-5s %-20s %-15s %-12s %s%n",
                "No.", "Name", "Roll Number", "Semester", "CGPA");
        System.out.println("-".repeat(65));

        int i = 1;
        for (Student s : students) {
            System.out.printf("%-5d %-20s %-15s %-12s %.2f%n",
                    i++, s.getName(), s.getRollNumber(),
                    s.getSemester(), s.calculateCGPA());
        }
    }

    // ── Remove a subject ──────────────────────────────────────

    static void removeSubject() {
        System.out.println("\n--- Remove Subject ---");
        Student student = findStudent();
        if (student == null) return;

        System.out.print("Subject name to remove: ");
        String subjectName = scanner.nextLine().trim();

        boolean removed = student.removeSubject(subjectName);
        if (removed) System.out.println("Subject removed.");
        else System.out.println("Subject not found.");
    }

    // ── Save to file ──────────────────────────────────────────

    static void saveData() {
        try {
            new java.io.File("data").mkdirs();
            fileHandler.save(students);
        } catch (IOException e) {
            System.out.println("Error saving: " + e.getMessage());
        }
    }

    // ── Helpers ───────────────────────────────────────────────

    static Student findStudent() {
        System.out.print("Enter roll number: ");
        String roll = scanner.nextLine().trim();
        for (Student s : students) {
            if (s.getRollNumber().equalsIgnoreCase(roll)) return s;
        }
        System.out.println("Student not found.");
        return null;
    }

    static int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                int val = Integer.parseInt(scanner.nextLine().trim());
                return val;
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
    }

    static double readDouble(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                return Double.parseDouble(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
    }
}
