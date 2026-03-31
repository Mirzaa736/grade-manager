# Student Grade Manager

A console-based Java application to manage student grades, calculate CGPA, and save data to a file.

---

## Features

- Add students with name, roll number, and semester
- Add subjects with marks and credit hours
- Auto-calculates percentage, letter grade, and CGPA
- View individual student report card
- View summary table of all students
- Save and load data from a CSV file

---

## How to Run

### Requirements
- Java 17 or higher

### Compile

```bash
cd src
javac com/grademanager/*.java
```

### Run

```bash
java com.grademanager.Main
```

Make sure a `data/` folder exists in the directory where you run the program — it will be created automatically if missing.

---

## Project Structure

```
GradeManager/
├── src/
│   └── com/grademanager/
│       ├── Main.java          # Menu-driven console UI
│       ├── Student.java       # Student class with CGPA logic
│       ├── Subject.java       # Subject class with grade calculation
│       └── FileHandler.java   # CSV file read/write
└── data/
    └── grades.csv             # Auto-generated on save
```

---

## Sample Output

```
========================================
  Name       : Arjun Sharma
  Roll No    : 22BCE1234
  Semester   : Sem 3
========================================

  Subject                   | Marks         | Percent  | Grade    | Credits
  ------------------------------------------------------------------------
  Data Structures           |   82.0 / 100  |  82.0%   | A        | 4
  Mathematics III           |   74.0 / 100  |  74.0%   | B        | 4
  Operating Systems         |   91.0 / 100  |  91.0%   | A+       | 3
  ------------------------------------------------------------------------
  Overall Percentage : 82.33%
  CGPA               : 8.86 / 10.0
========================================
```

---

## Java Concepts Used

| Concept | Where |
|---|---|
| OOP — Classes & Inheritance | `Student`, `Subject` |
| Encapsulation | Private fields + getters/setters |
| Collections — ArrayList | Storing subjects and students |
| File I/O — BufferedReader/Writer | `FileHandler.java` |
| String manipulation | CSV parsing, formatted output |
| Exception handling | try/catch for file and input errors |

