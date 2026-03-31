package com.grademanager;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a student with a name, roll number, and list of subjects.
 */
public class Student implements Serializable {

    private String name;
    private String rollNumber;
    private String semester;
    private List<Subject> subjects;

    public Student(String name, String rollNumber, String semester) {
        this.name = name;
        this.rollNumber = rollNumber;
        this.semester = semester;
        this.subjects = new ArrayList<>();
    }

    public void addSubject(Subject subject) {
        subjects.add(subject);
    }

    public boolean removeSubject(String subjectName) {
        return subjects.removeIf(s -> s.getName().equalsIgnoreCase(subjectName));
    }

    /**
     * Calculates CGPA using weighted average:
     * sum(gradePoints x credits) / sum(credits)
     */
    public double calculateCGPA() {
        if (subjects.isEmpty()) return 0.0;

        double totalWeighted = 0;
        int totalCredits = 0;

        for (Subject s : subjects) {
            totalWeighted += s.getGradePoints() * s.getCreditHours();
            totalCredits += s.getCreditHours();
        }

        if (totalCredits == 0) return 0.0;
        return totalWeighted / totalCredits;
    }

    public double getOverallPercentage() {
        if (subjects.isEmpty()) return 0.0;
        double total = 0;
        for (Subject s : subjects) total += s.getPercentage();
        return total / subjects.size();
    }

    // Getters and setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getRollNumber() { return rollNumber; }
    public String getSemester() { return semester; }

    public List<Subject> getSubjects() { return subjects; }
}
