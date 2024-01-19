import java.util.ArrayList;
import java.util.Scanner;

class Course {
    private String code;
    private String title;
    private String description;
    private int capacity;
    private String schedule;

    public Course(String code, String title, String description, int capacity, String schedule) {
        this.code = code;
        this.title = title;
        this.description = description;
        this.capacity = capacity;
        this.schedule = schedule;
    }

    public String getCode() {
        return code;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getCapacity() {
        return capacity;
    }

    public String getSchedule() {
        return schedule;
    }

    @Override
    public String toString() {
        return "Course Code: " + code + ", Title: " + title + ", Description: " + description +
                ", Capacity: " + capacity + ", Schedule: " + schedule;
    }
}

class Student {
    private String studentID;
    private String name;
    private ArrayList<Course> registeredCourses;

    public Student(String studentID, String name) {
        this.studentID = studentID;
        this.name = name;
        this.registeredCourses = new ArrayList<>();
    }

    public String getStudentID() {
        return studentID;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Course> getRegisteredCourses() {
        return registeredCourses;
    }

    public void registerCourse(Course course) {
        if (registeredCourses.size() < 5 && !registeredCourses.contains(course)) {
            registeredCourses.add(course);
            System.out.println("Course registered successfully: " + course.getTitle());
        } else {
            System.out.println(
                    "Registration failed. Either course capacity is full or you have already registered for this course.");
        }
    }

    public void dropCourse(Course course) {
        if (registeredCourses.contains(course)) {
            registeredCourses.remove(course);
            System.out.println("Course dropped successfully: " + course.getTitle());
        } else {
            System.out.println("Dropping failed. You are not registered for this course.");
        }
    }

    public void displayRegisteredCourses() {
        System.out.println("Registered Courses for " + name + " (ID: " + studentID + "):");
        if (registeredCourses.isEmpty()) {
            System.out.println("No courses registered.");
        } else {
            for (Course course : registeredCourses) {
                System.out.println(course);
            }
        }
    }
}

class CourseRegistrationSystem {
    private ArrayList<Course> courses;
    private ArrayList<Student> students;

    public CourseRegistrationSystem() {
        this.courses = new ArrayList<>();
        this.students = new ArrayList<>();
    }

    public void addCourse(Course course) {
        courses.add(course);
    }

    public void addStudent(Student student) {
        students.add(student);
    }

    public void displayCourseListing() {
        System.out.println("Available Courses:");
        for (Course course : courses) {
            System.out.println(course);
        }
    }

    public void displayStudentListing() {
        System.out.println("Registered Students:");
        for (Student student : students) {
            System.out.println("Student ID: " + student.getStudentID() + ", Name: " + student.getName());
        }
    }

    public static void main(String[] args) {
        CourseRegistrationSystem system = new CourseRegistrationSystem();

        // Sample courses
        Course c1 = new Course("CSE101", "Introduction to Programming", "Basic programming concepts", 50,
                "Mon, Wed 10:00 AM");
        Course c2 = new Course("MATH201", "Calculus I", "Limits, derivatives, integrals", 40, "Tue, Thu 2:00 PM");
        Course c3 = new Course("ENG101", "English Composition", "Writing and communication skills", 30,
                "Mon, Wed 1:00 PM");

        // Sample students
        Student s1 = new Student("S001", "John Doe");
        Student s2 = new Student("S002", "Jane Smith");

        system.addCourse(c1);
        system.addCourse(c2);
        system.addCourse(c3);

        system.addStudent(s1);
        system.addStudent(s2);

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n1. Display Course Listing");
            System.out.println("2. Display Student Listing");
            System.out.println("3. Register for a Course");
            System.out.println("4. Drop a Course");
            System.out.println("5. Display Registered Courses for a Student");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    system.displayCourseListing();
                    break;
                case 2:
                    system.displayStudentListing();
                    break;
                case 3:
                    // Register for a course
                    System.out.print("Enter student ID: ");
                    String studentID = scanner.next();
                    System.out.print("Enter course code to register: ");
                    String courseCode = scanner.next();

                    Student student = null;
                    for (Student s : system.students) {
                        if (s.getStudentID().equals(studentID)) {
                            student = s;
                            break;
                        }
                    }

                    Course course = null;
                    for (Course c : system.courses) {
                        if (c.getCode().equals(courseCode)) {
                            course = c;
                            break;
                        }
                    }

                    if (student != null && course != null) {
                        student.registerCourse(course);
                    } else {
                        System.out.println("Invalid student ID or course code.");
                    }
                    break;
                case 4:
                    // Drop a course
                    System.out.print("Enter student ID: ");
                    studentID = scanner.next();
                    System.out.print("Enter course code to drop: ");
                    courseCode = scanner.next();

                    student = null;
                    for (Student s : system.students) {
                        if (s.getStudentID().equals(studentID)) {
                            student = s;
                            break;
                        }
                    }

                    course = null;
                    for (Course c : system.courses) {
                        if (c.getCode().equals(courseCode)) {
                            course = c;
                            break;
                        }
                    }
                    if (student != null && course != null) {
                        student.dropCourse(course);
                    } else {
                        System.out.println("Invalid student ID or course code.");
                    }
                    break;
                case 5:
                    // Display registered courses for a student
                    System.out.print("Enter student ID: ");
                    studentID = scanner.next();

                    student = null;
                    for (Student s : system.students) {
                        if (s.getStudentID().equals(studentID)) {
                            student = s;
                            break;
                        }
                    }

                    if (student != null) {
                        student.displayRegisteredCourses();
                    } else {
                        System.out.println("Invalid student ID.");
                    }
                    break;
                case 6:
                    // Exit the program
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }
        }
    }
}
