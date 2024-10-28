import java.util.ArrayList;
import java.util.List;

public class EmployeeService {
    private List<Employee> employees;

    public EmployeeService() {
        this.employees = new ArrayList<>();
    }

    public List<Employee> getEmployees() { return employees; }

    public void addEmployee(Employee employee) {
        employees.add(employee);
    }

    public Employee findEmployeeById(int id) {
        for (Employee employee : employees) {
            if (employee.getId() == id) {
                return employee;
            }
        }
        return null;
    }

    public boolean deleteEmployee(int id) {
        Employee employee = findEmployeeById(id);
        if (employee != null) {
            employees.remove(employee);
            return true;
        }
        return false;
    }

    public void editEmployee(int id, String name, String department, double salary) {
        Employee employee = findEmployeeById(id);
        if (employee != null) {
            if (!name.isEmpty()) employee.setName(name);
            if (!department.isEmpty()) employee.setDepartment(department);
            if (salary != -1) employee.setSalary(salary);
        }
    }
}

