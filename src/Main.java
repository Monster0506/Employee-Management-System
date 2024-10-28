import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final EmployeeService employeeService = new EmployeeService();

    public static void main(String[] args) {
        FileHandler.loadEmployees(employeeService);

        while (true) {
            System.out.println("\nEmployee Management System");
            System.out.println("1. View all employees");
            System.out.println("2. Add a new employee");
            System.out.println("3. Edit an employee");
            System.out.println("4. Delete an employee");
            System.out.println("5. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1 -> viewEmployees();
                case 2 -> addEmployee();
                case 3 -> editEmployee();
                case 4 -> deleteEmployee();
                case 5 -> {
                    FileHandler.saveEmployees(employeeService.getEmployees());
                    System.exit(0);
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void viewEmployees() {
        if (employeeService.getEmployees().isEmpty()) {
            System.out.println("No employees found.");
        } else {
            for (Employee employee : employeeService.getEmployees()) {
                System.out.println(employee);  // `toString` method of Employee provides detailed output
            }
        }
    }

    private static void addEmployee() {
        System.out.print("Enter ID: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        System.out.print("Enter Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Department: ");
        String department = scanner.nextLine();
        System.out.print("Enter Salary: ");
        double salary = scanner.nextDouble();
        scanner.nextLine(); // Consume newline
        System.out.print("Enter Job Title: ");
        String jobTitle = scanner.nextLine();

        Employee employee = new Employee(id, name, department, salary, jobTitle);

        // Collecting additional information
        System.out.print("Enter Date of Birth (yyyy-MM-dd): ");
        employee.setDateOfBirth(parseDate(scanner.nextLine()));
        System.out.print("Enter Hire Date (yyyy-MM-dd): ");
        employee.setHireDate(parseDate(scanner.nextLine()));
        System.out.print("Enter Address: ");
        employee.setAddress(scanner.nextLine());
        System.out.print("Enter Phone: ");
        employee.setPhone(scanner.nextLine());
        System.out.print("Enter Email: ");
        employee.setEmail(scanner.nextLine());

        System.out.print("Enter Position Level: ");
        employee.setPositionLevel(scanner.nextLine());
        System.out.print("Enter Employment Status: ");
        employee.setEmploymentStatus(scanner.nextLine());
        System.out.print("Enter Performance Score: ");
        employee.setPerformanceScore(scanner.nextInt());
        scanner.nextLine(); // Consume newline
        System.out.print("Enter Manager ID: ");
        employee.setManagerId(scanner.nextInt());
        scanner.nextLine(); // Consume newline

        System.out.print("Enter Skills (comma-separated): ");
        String[] skillsArray = scanner.nextLine().split(",");
        List<String> skills = new ArrayList<>();
        for (String skill : skillsArray) {
            skills.add(skill.trim());
        }
        employee.setSkills(skills);

        System.out.print("Enter Bonus: ");
        employee.setBonus(scanner.nextDouble());
        scanner.nextLine(); // Consume newline
        System.out.print("Enter Benefits Package: ");
        employee.setBenefitsPackage(scanner.nextLine());
        System.out.print("Enter Vacation Days: ");
        employee.setVacationDays(scanner.nextInt());
        System.out.print("Enter Sick Days: ");
        employee.setSickDays(scanner.nextInt());
        scanner.nextLine(); // Consume newline

        System.out.print("Enter Emergency Contact Name: ");
        employee.setEmergencyContactName(scanner.nextLine());
        System.out.print("Enter Emergency Contact Relation: ");
        employee.setEmergencyContactRelation(scanner.nextLine());
        System.out.print("Enter Emergency Contact Phone: ");
        employee.setEmergencyContactPhone(scanner.nextLine());

        employeeService.addEmployee(employee);
        System.out.println("Employee added successfully!");
    }

    private static void editEmployee() {
        System.out.print("Enter the ID of the employee to edit: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        Employee employee = employeeService.findEmployeeById(id);
        if (employee == null) {
            System.out.println("Employee not found.");
            return;
        }

        // Prompt to update details or leave them unchanged
        System.out.print("Enter new name (leave blank to keep current): ");
        String name = scanner.nextLine();
        if (!name.isEmpty()) employee.setName(name);

        System.out.print("Enter new department (leave blank to keep current): ");
        String department = scanner.nextLine();
        if (!department.isEmpty()) employee.setDepartment(department);

        System.out.print("Enter new salary (or -1 to keep current): ");
        double salary = scanner.nextDouble();
        if (salary != -1) employee.setSalary(salary);
        scanner.nextLine(); // Consume newline

        System.out.println("Employee updated successfully!");
    }

    private static void deleteEmployee() {
        System.out.print("Enter the ID of the employee to delete: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        if (employeeService.deleteEmployee(id)) {
            System.out.println("Employee deleted successfully!");
        } else {
            System.out.println("Employee not found.");
        }
    }

    private static Date parseDate(String dateStr) {
        try {
            return Employee.DATE_FORMAT.parse(dateStr);
        } catch (Exception e) {
            System.out.println("Invalid date format, setting as null");
            return null;
        }
    }
}

