import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final EmployeeService employeeService = new EmployeeService();

    // ANSI escape codes for colors
    private static final String RESET = "\u001B[0m";
    private static final String CYAN = "\u001B[36m";
    private static final String GREEN = "\u001B[32m";
    private static final String YELLOW = "\u001B[33m";
    private static final String RED = "\u001B[31m";
    private static final String BLUE = "\u001B[34m";
    private static final String PURPLE = "\u001B[35m";

    public static void main(String[] args) {
        FileHandler.loadEmployees(employeeService);

        while (true) {
            System.out.println(BLUE + "\nEmployee Management System" + RESET);
            System.out.println(CYAN + "1. View all employees (summary)" + RESET);
            System.out.println(CYAN + "2. View employee details (by ID)" + RESET);
            System.out.println(CYAN + "3. Add a new employee" + RESET);
            System.out.println(CYAN + "4. Edit employee data" + RESET);
            System.out.println(CYAN + "5. Delete an employee" + RESET);
            System.out.println(CYAN + "6. Exit" + RESET);

            System.out.print(YELLOW + "Enter your choice: " + RESET);
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1 -> viewEmployeesSummary();
                case 2 -> viewEmployeeDetails();
                case 3 -> addEmployee();
                case 4 -> editEmployeeMenu();
                case 5 -> deleteEmployee();
                case 6 -> {
                    FileHandler.saveEmployees(employeeService.getEmployees());
                    System.out.println(GREEN + "Exiting system. Goodbye!" + RESET);
                    System.exit(0);
                }
                default -> System.out.println(RED + "Invalid choice. Please try again." + RESET);
            }
        }
    }

    private static void viewEmployeesSummary() {
        if (employeeService.getEmployees().isEmpty()) {
            System.out.println(RED + "No employees found." + RESET);
        } else {
            System.out.printf(PURPLE + "%-5s %-20s %-20s %-10s %-15s%n" + RESET, "ID", "Name", "Department", "Salary", "Job Title");
            System.out.println(PURPLE + "---------------------------------------------------------------------------------" + RESET);
            for (Employee employee : employeeService.getEmployees()) {
                System.out.printf("%-5d %-20s %-20s %-10.2f %-15s%n",
                        employee.getId(),
                        employee.getName(),
                        employee.getDepartment(),
                        employee.getSalary(),
                        employee.getJobTitle());
            }
        }
    }

    private static void viewEmployeeDetails() {
        System.out.print(YELLOW + "Enter the ID of the employee to view details: " + RESET);
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        Employee employee = employeeService.findEmployeeById(id);
        if (employee == null) {
            System.out.println(RED + "Employee not found." + RESET);
        } else {
            System.out.println(BLUE + "\nEmployee Details:" + RESET);
            System.out.println(PURPLE + "----------------------------------------------------" + RESET);
            System.out.println(GREEN + "ID: " + RESET + employee.getId());
            System.out.println(GREEN + "Name: " + RESET + employee.getName());
            System.out.println(GREEN + "Department: " + RESET + employee.getDepartment());
            System.out.println(GREEN + "Salary: " + RESET + "$" + employee.getSalary());
            System.out.println(GREEN + "Job Title: " + RESET + employee.getJobTitle());
            System.out.println(GREEN + "Date of Birth: " + RESET + formatDate(employee.getDateOfBirth()));
            System.out.println(GREEN + "Hire Date: " + RESET + formatDate(employee.getHireDate()));
            System.out.println(GREEN + "Address: " + RESET + employee.getAddress());
            System.out.println(GREEN + "Phone: " + RESET + employee.getPhone());
            System.out.println(GREEN + "Email: " + RESET + employee.getEmail());
            System.out.println(GREEN + "Position Level: " + RESET + employee.getPositionLevel());
            System.out.println(GREEN + "Employment Status: " + RESET + employee.getEmploymentStatus());
            System.out.println(GREEN + "Performance Score: " + RESET + employee.getPerformanceScore());
            System.out.println(GREEN + "Manager ID: " + RESET + employee.getManagerId());
            System.out.println(GREEN + "Skills: " + RESET + String.join(", ", employee.getSkills()));
            System.out.println(GREEN + "Bonus: " + RESET + "$" + employee.getBonus());
            System.out.println(GREEN + "Benefits Package: " + RESET + employee.getBenefitsPackage());
            System.out.println(GREEN + "Vacation Days: " + RESET + employee.getVacationDays());
            System.out.println(GREEN + "Sick Days: " + RESET + employee.getSickDays());
            System.out.println(GREEN + "Emergency Contact Name: " + RESET + employee.getEmergencyContactName());
            System.out.println(GREEN + "Emergency Contact Relation: " + RESET + employee.getEmergencyContactRelation());
            System.out.println(GREEN + "Emergency Contact Phone: " + RESET + employee.getEmergencyContactPhone());
            System.out.println(PURPLE + "----------------------------------------------------" + RESET);
        }
    }

    private static void addEmployee() {
        System.out.print(YELLOW + "Enter ID: " + RESET);
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        System.out.print(YELLOW + "Enter Name: " + RESET);
        String name = scanner.nextLine();
        System.out.print(YELLOW + "Enter Department: " + RESET);
        String department = scanner.nextLine();
        System.out.print(YELLOW + "Enter Salary: " + RESET);
        double salary = scanner.nextDouble();
        scanner.nextLine(); // Consume newline
        System.out.print(YELLOW + "Enter Job Title: " + RESET);
        String jobTitle = scanner.nextLine();

        Employee employee = new Employee(id, name, department, salary, jobTitle);
        setAdditionalEmployeeInfo(employee);
        employeeService.addEmployee(employee);
        System.out.println(GREEN + "Employee added successfully!" + RESET);
    }

    private static void setAdditionalEmployeeInfo(Employee employee) {
        System.out.print(YELLOW + "Enter Date of Birth (yyyy-MM-dd): " + RESET);
        employee.setDateOfBirth(parseDate(scanner.nextLine()));
        System.out.print(YELLOW + "Enter Hire Date (yyyy-MM-dd): " + RESET);
        employee.setHireDate(parseDate(scanner.nextLine()));
        System.out.print(YELLOW + "Enter Address: " + RESET);
        employee.setAddress(scanner.nextLine());
        System.out.print(YELLOW + "Enter Phone: " + RESET);
        employee.setPhone(scanner.nextLine());
        System.out.print(YELLOW + "Enter Email: " + RESET);
        employee.setEmail(scanner.nextLine());
        
        System.out.print(YELLOW + "Enter Position Level: " + RESET);
        employee.setPositionLevel(scanner.nextLine());
        System.out.print(YELLOW + "Enter Employment Status: " + RESET);
        employee.setEmploymentStatus(scanner.nextLine());
        System.out.print(YELLOW + "Enter Performance Score: " + RESET);
        employee.setPerformanceScore(scanner.nextInt());
        scanner.nextLine(); // Consume newline
        System.out.print(YELLOW + "Enter Manager ID: " + RESET);
        employee.setManagerId(scanner.nextInt());
        scanner.nextLine(); // Consume newline

        System.out.print(YELLOW + "Enter Skills (comma-separated): " + RESET);
        String[] skillsArray = scanner.nextLine().split(",");
        List<String> skills = new ArrayList<>();
        for (String skill : skillsArray) {
            skills.add(skill.trim());
        }
        employee.setSkills(skills);

        System.out.print(YELLOW + "Enter Bonus: " + RESET);
        employee.setBonus(scanner.nextDouble());
        scanner.nextLine(); // Consume newline
        System.out.print(YELLOW + "Enter Benefits Package: " + RESET);
        employee.setBenefitsPackage(scanner.nextLine());
        System.out.print(YELLOW + "Enter Vacation Days: " + RESET);
        employee.setVacationDays(scanner.nextInt());
        System.out.print(YELLOW + "Enter Sick Days: " + RESET);
        employee.setSickDays(scanner.nextInt());
        scanner.nextLine(); // Consume newline

        System.out.print(YELLOW + "Enter Emergency Contact Name: " + RESET);
        employee.setEmergencyContactName(scanner.nextLine());
        System.out.print(YELLOW + "Enter Emergency Contact Relation: " + RESET);
        employee.setEmergencyContactRelation(scanner.nextLine());
        System.out.print(YELLOW + "Enter Emergency Contact Phone: " + RESET);
        employee.setEmergencyContactPhone(scanner.nextLine());
    }

    private static void editEmployeeMenu() {
        System.out.print(YELLOW + "Enter the ID of the employee to edit: " + RESET);
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        Employee employee = employeeService.findEmployeeById(id);
        if (employee == null) {
            System.out.println(RED + "Employee not found." + RESET);
            return;
        }

        while (true) {
            System.out.println(CYAN + "\nEdit Employee Data" + RESET);
            System.out.println(CYAN + "1. Name" + RESET);
            System.out.println(CYAN + "2. Department" + RESET);
            System.out.println(CYAN + "3. Salary" + RESET);
            System.out.println(CYAN + "4. Job Title" + RESET);
            System.out.println(CYAN + "5. Address" + RESET);
            System.out.println(CYAN + "6. Phone" + RESET);
            System.out.println(CYAN + "7. Email" + RESET);
            System.out.println(CYAN + "8. Performance Score" + RESET);
            System.out.println(CYAN + "9. Skills" + RESET);
            System.out.println(CYAN + "10. Exit Edit Mode" + RESET);

            System.out.print(YELLOW + "Select the field to edit: " + RESET);
            int fieldChoice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (fieldChoice) {
                case 1 -> {
                    System.out.print(YELLOW + "Enter new Name: " + RESET);
                    employee.setName(scanner.nextLine());
                }
                case 2 -> {
                    System.out.print(YELLOW + "Enter new Department: " + RESET);
                    employee.setDepartment(scanner.nextLine());
                }
                case 3 -> {
                    System.out.print(YELLOW + "Enter new Salary: " + RESET);
                    employee.setSalary(scanner.nextDouble());
                    scanner.nextLine(); // Consume newline
                }
                case 4 -> {
                    System.out.print(YELLOW + "Enter new Job Title: " + RESET);
                    employee.setJobTitle(scanner.nextLine());
                }
                case 5 -> {
                    System.out.print(YELLOW + "Enter new Address: " + RESET);
                    employee.setAddress(scanner.nextLine());
                }
                case 6 -> {
                    System.out.print(YELLOW + "Enter new Phone: " + RESET);
                    employee.setPhone(scanner.nextLine());
                }
                case 7 -> {
                    System.out.print(YELLOW + "Enter new Email: " + RESET);
                    employee.setEmail(scanner.nextLine());
                }
                case 8 -> {
                    System.out.print(YELLOW + "Enter new Performance Score: " + RESET);
                    employee.setPerformanceScore(scanner.nextInt());
                    scanner.nextLine(); // Consume newline
                }
                case 9 -> {
                    System.out.print(YELLOW + "Enter new Skills (comma-separated): " + RESET);
                    String[] skillsArray = scanner.nextLine().split(",");
                    List<String> skills = new ArrayList<>();
                    for (String skill : skillsArray) {
                        skills.add(skill.trim());
                    }
                    employee.setSkills(skills);
                }
                case 10 -> {
                    System.out.println(GREEN + "Exiting edit mode." + RESET);
                    return;
                }
                default -> System.out.println(RED + "Invalid choice, please try again." + RESET);
            }
            System.out.println(GREEN + "Field updated successfully!" + RESET);
        }
    }

    private static void deleteEmployee() {
        System.out.print(YELLOW + "Enter the ID of the employee to delete: " + RESET);
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        if (employeeService.deleteEmployee(id)) {
            System.out.println(GREEN + "Employee deleted successfully!" + RESET);
        } else {
            System.out.println(RED + "Employee not found." + RESET);
        }
    }

    private static Date parseDate(String dateStr) {
        try {
            return Employee.DATE_FORMAT.parse(dateStr);
        } catch (Exception e) {
            System.out.println(RED + "Invalid date format, setting as null" + RESET);
            return null;
        }
    }

    private static String formatDate(Date date) {
        return date != null ? Employee.DATE_FORMAT.format(date) : "N/A";
    }
}

