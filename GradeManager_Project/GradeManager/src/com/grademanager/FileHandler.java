package com.grademanager;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Handles reading and writing student data to a CSV file.
 * Format per line: name,rollNumber,semester,subjectName,marks,totalMarks,credits
 */
public class FileHandler {

    private final String filePath;

    public FileHandler(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Saves all students to a CSV file.
     */
    public void save(List<Student> students) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));

        for (Student student : students) {
            for (Subject subject : student.getSubjects()) {
                String line = student.getName() + ","
                        + student.getRollNumber() + ","
                        + student.getSemester() + ","
                        + subject.getName() + ","
                        + subject.getMarksObtained() + ","
                        + subject.getTotalMarks() + ","
                        + subject.getCreditHours();
                writer.write(line);
                writer.newLine();
            }
        }

        writer.close();
        System.out.println("Data saved to " + filePath);
    }

    /**
     * Loads students from the CSV file.
     */
    public List<Student> load() throws IOException {
        List<Student> students = new ArrayList<>();
        File file = new File(filePath);

        if (!file.exists()) return students;

        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line;

        while ((line = reader.readLine()) != null) {
            line = line.trim();
            if (line.isEmpty()) continue;

            String[] parts = line.split(",");
            if (parts.length < 7) continue;

            String name       = parts[0];
            String roll       = parts[1];
            String semester   = parts[2];
            String subName    = parts[3];
            double marks      = Double.parseDouble(parts[4]);
            double total      = Double.parseDouble(parts[5]);
            int credits       = Integer.parseInt(parts[6]);

            // Find existing student or create new one
            Student student = null;
            for (Student s : students) {
                if (s.getRollNumber().equals(roll)) {
                    student = s;
                    break;
                }
            }
            if (student == null) {
                student = new Student(name, roll, semester);
                students.add(student);
            }

            student.addSubject(new Subject(subName, marks, total, credits));
        }

        reader.close();
        return students;
    }
}
