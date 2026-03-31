package com.grademanager;

import java.io.Serializable;

/**
 * Represents a single academic subject with a name, marks, and max marks.
 */
public class Subject implements Serializable {

    private String name;
    private double marksObtained;
    private double totalMarks;
    private int creditHours;

    public Subject(String name, double marksObtained, double totalMarks, int creditHours) {
        this.name = name;
        this.marksObtained = marksObtained;
        this.totalMarks = totalMarks;
        this.creditHours = creditHours;
    }

    public double getPercentage() {
        if (totalMarks == 0) return 0;
        return (marksObtained / totalMarks) * 100;
    }

    public String getGrade() {
        double pct = getPercentage();
        if (pct >= 90) return "A+";
        if (pct >= 80) return "A";
        if (pct >= 70) return "B";
        if (pct >= 60) return "C";
        if (pct >= 50) return "D";
        return "F";
    }

    public double getGradePoints() {
        double pct = getPercentage();
        if (pct >= 90) return 10.0;
        if (pct >= 80) return 9.0;
        if (pct >= 70) return 8.0;
        if (pct >= 60) return 7.0;
        if (pct >= 50) return 6.0;
        return 0.0;
    }

    // Getters and setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public double getMarksObtained() { return marksObtained; }
    public void setMarksObtained(double marksObtained) { this.marksObtained = marksObtained; }

    public double getTotalMarks() { return totalMarks; }
    public void setTotalMarks(double totalMarks) { this.totalMarks = totalMarks; }

    public int getCreditHours() { return creditHours; }
    public void setCreditHours(int creditHours) { this.creditHours = creditHours; }

    @Override
    public String toString() {
        return String.format("%-25s | %6.1f / %-6.1f | %5.1f%% | Grade: %-2s | Credits: %d",
                name, marksObtained, totalMarks, getPercentage(), getGrade(), creditHours);
    }
}
