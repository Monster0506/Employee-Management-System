import java.io.*;
import java.util.List;

public class FileHandler {
    private static final String FILE_NAME = "employees.empdata";

    public static void loadEmployees(EmployeeService employeeService) {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            int id = 0;
            String name = "";
            String department = "";
            double salary = 0;

            while ((line = reader.readLine()) != null) {
                if (line.startsWith("#") || line.isEmpty()) {
                    // Skip comments and blank lines
                    continue;
                } else if (line.equals("[Employee]")) {
                    // When we reach a new employee section, reset values
                    id = 0;
                    name = "";
                    department = "";
                    salary = 0;
                } else if (line.startsWith("ID=")) {
                    id = Integer.parseInt(line.substring(3));
                } else if (line.startsWith("Name=")) {
                    name = line.substring(5);
                } else if (line.startsWith("Department=")) {
                    department = line.substring(11);
                } else if (line.startsWith("Salary=")) {
                    salary = Double.parseDouble(line.substring(7));
                    // When salary is read, add the employee to the service list
                    employeeService.addEmployee(new Employee(id, name, department, salary));
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading employees: " + e.getMessage());
        }
    }

    public static void saveEmployees(List<Employee> employees) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            writer.write("# Employee File (.empdata)");
            writer.newLine();

            for (Employee employee : employees) {
                writer.write("[Employee]");
                writer.newLine();
                writer.write("ID=" + employee.getId());
                writer.newLine();
                writer.write("Name=" + employee.getName());
                writer.newLine();
                writer.write("Department=" + employee.getDepartment());
                writer.newLine();
                writer.write("Salary=" + employee.getSalary());
                writer.newLine();
                writer.newLine();  // Blank line between employees for readability
            }
        } catch (IOException e) {
            System.out.println("Error saving employees: " + e.getMessage());
        }
    }
}

