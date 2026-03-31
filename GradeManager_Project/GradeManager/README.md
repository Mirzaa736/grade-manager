# Student Grade Manager

A simple Java console app I built to solve something that actually annoyed me — keeping track of marks across multiple subjects and never knowing my actual CGPA until the semester ended.

Nothing fancy. Just a menu you can interact with, enter your subjects and marks, and it gives you your grade and CGPA on the spot. Data saves to a CSV file so you don't lose it.

---

## Why I made this

Every semester I'd be manually adding up marks in a calculator or a rough Excel sheet. Wanted something cleaner that just works from the terminal. This does that.

---

## What it can do

- Add multiple students (useful if you're tracking friends too)
- Add subjects with marks obtained, total marks, and credit hours
- Automatically figures out your percentage, letter grade (A+, A, B...) and CGPA
- View a proper report card per student
- See a summary table of all students at once
- Save everything to a CSV file and load it back next time

---

## How to run it

You need Java 17 or above installed. Check with:

```bash
java -version
```

Then compile and run:

```bash
cd src
javac com/grademanager/*.java
java com.grademanager.Main
```

Or if you're running it from the root folder directly:

```bash
cd "C:\path\to\GradeManager\src" && javac com/grademanager/*.java && java com.grademanager.Main
```

The `data/` folder gets created automatically when you save. No setup needed.

---

## Project structure

```
GradeManager/
├── src/
│   └── com/grademanager/
│       ├── Main.java          # menu, user input, all the flow control
│       ├── Student.java       # stores subjects, calculates CGPA
│       ├── Subject.java       # stores marks, computes grade and percentage
│       └── FileHandler.java   # reads and writes the CSV file
└── data/
    └── grades.csv             # gets created when you hit Save
```

---

## Sample output

```
========================================
  Name       : Rahul Sharma
  Roll No    : 22BCE1234
  Semester   : Sem 3
========================================

  Subject                   Marks           %       Grade   Credits
  --------------------------------------------------------------
  Mathematics               78.0/100.0     78.0%    B       4 credits
  Java Programming          91.0/100.0     91.0%    A+      3 credits
  --------------------------------------------------------------
  Overall Percentage : 84.50%
  CGPA               : 8.86 / 10.0
========================================
```

---

## Java concepts used

| Concept | Where it shows up |
|---|---|
| Classes and Objects | `Student`, `Subject`, `FileHandler` each handle one thing |
| Encapsulation | All fields are private, accessed through getters/setters |
| ArrayList | Used to store the list of students and subjects |
| File I/O | `BufferedReader` and `BufferedWriter` in `FileHandler.java` |
| Exception handling | try/catch around file operations and Scanner input |
| String methods | `split()`, `trim()`, `equalsIgnoreCase()` for CSV parsing |
| Lambda expression | `removeIf()` used to delete a subject by name |

---

## Known limitations

- File save doesn't work on online compilers (no real filesystem) — everything else does
- No database, just a plain CSV file
- Built for single-user use, not multi-user

---

*Made by — Mirzaa736*  
*Course: Programming in Java*  
*Submitted: March 2026*
