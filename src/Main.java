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
                System.out.println("ID: " + employee.getId() +
                        ", Name: " + employee.getName() +
                        ", Department: " + employee.getDepartment() +
                        ", Salary: " + employee.getSalary());
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

        employeeService.addEmployee(new Employee(id, name, department, salary));
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

        System.out.print("Enter new name (leave blank to keep current): ");
        String name = scanner.nextLine();
        System.out.print("Enter new department (leave blank to keep current): ");
        String department = scanner.nextLine();
        System.out.print("Enter new salary (or -1 to keep current): ");
        double salary = scanner.nextDouble();

        employeeService.editEmployee(id, name, department, salary);
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
}

