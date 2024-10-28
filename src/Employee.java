public class Employee {
    private int id;
    private String name;
    private String department;
    private double salary;

    public Employee(int id, String name, String department, double salary) {
        this.id = id;
        this.name = name;
        this.department = department;
        this.salary = salary;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public String getDepartment() { return department; }
    public double getSalary() { return salary; }

    public void setName(String name) { this.name = name; }
    public void setDepartment(String department) { this.department = department; }
    public void setSalary(double salary) { this.salary = salary; }

    @Override
    public String toString() {
        return id + "," + name + "," + department + "," + salary;
    }

    public static Employee fromString(String data) {
        String[] fields = data.split(",");
        int id = Integer.parseInt(fields[0]);
        String name = fields[1];
        String department = fields[2];
        double salary = Double.parseDouble(fields[3]);
        return new Employee(id, name, department, salary);
    }
}

