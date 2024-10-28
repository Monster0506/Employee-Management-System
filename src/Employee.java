import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Employee {
    private int id;
    private String name;
    private String department;
    private double salary;
    private String jobTitle;
    private Date dateOfBirth;
    private Date hireDate;
    private String address;
    private String phone;
    private String email;

    private String positionLevel; // e.g., Junior, Senior
    private String employmentStatus; // e.g., Full-Time, Part-Time
    private int performanceScore;
    private int managerId; // ID of the manager supervising this employee
    private List<String> skills;

    private double bonus;
    private String benefitsPackage;
    private int vacationDays;
    private int sickDays;

    private String emergencyContactName;
    private String emergencyContactRelation;
    private String emergencyContactPhone;

    public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    public Employee(int id, String name, String department, double salary, String jobTitle) {
        this.id = id;
        this.name = name;
        this.department = department;
        this.salary = salary;
        this.jobTitle = jobTitle;
        this.skills = new ArrayList<>();
    }

    // Getters and Setters for Personal Information
    public int getId() { return id; }
    public String getName() { return name; }
    public String getDepartment() { return department; }
    public double getSalary() { return salary; }
    public String getJobTitle() { return jobTitle; }
    public Date getDateOfBirth() { return dateOfBirth; }
    public Date getHireDate() { return hireDate; }
    public String getAddress() { return address; }
    public String getPhone() { return phone; }
    public String getEmail() { return email; }

    public void setName(String name) { this.name = name; }
    public void setDepartment(String department) { this.department = department; }
    public void setSalary(double salary) { this.salary = salary; }
    public void setJobTitle(String jobTitle) { this.jobTitle = jobTitle; }
    public void setDateOfBirth(Date dateOfBirth) { this.dateOfBirth = dateOfBirth; }
    public void setHireDate(Date hireDate) { this.hireDate = hireDate; }
    public void setAddress(String address) { this.address = address; }
    public void setPhone(String phone) { this.phone = phone; }
    public void setEmail(String email) { this.email = email; }

    // Getters and Setters for Employment Details
    public String getPositionLevel() { return positionLevel; }
    public String getEmploymentStatus() { return employmentStatus; }
    public int getPerformanceScore() { return performanceScore; }
    public int getManagerId() { return managerId; }
    public List<String> getSkills() { return skills; }

    public void setPositionLevel(String positionLevel) { this.positionLevel = positionLevel; }
    public void setEmploymentStatus(String employmentStatus) { this.employmentStatus = employmentStatus; }
    public void setPerformanceScore(int performanceScore) { this.performanceScore = performanceScore; }
    public void setManagerId(int managerId) { this.managerId = managerId; }
    public void setSkills(List<String> skills) { this.skills = skills; }

    // Getters and Setters for Compensation and Benefits
    public double getBonus() { return bonus; }
    public String getBenefitsPackage() { return benefitsPackage; }
    public int getVacationDays() { return vacationDays; }
    public int getSickDays() { return sickDays; }

    public void setBonus(double bonus) { this.bonus = bonus; }
    public void setBenefitsPackage(String benefitsPackage) { this.benefitsPackage = benefitsPackage; }
    public void setVacationDays(int vacationDays) { this.vacationDays = vacationDays; }
    public void setSickDays(int sickDays) { this.sickDays = sickDays; }

    // Getters and Setters for Emergency Contact
    public String getEmergencyContactName() { return emergencyContactName; }
    public String getEmergencyContactRelation() { return emergencyContactRelation; }
    public String getEmergencyContactPhone() { return emergencyContactPhone; }

    public void setEmergencyContactName(String emergencyContactName) { this.emergencyContactName = emergencyContactName; }
    public void setEmergencyContactRelation(String emergencyContactRelation) { this.emergencyContactRelation = emergencyContactRelation; }
    public void setEmergencyContactPhone(String emergencyContactPhone) { this.emergencyContactPhone = emergencyContactPhone; }

    @Override
    public String toString() {
        return "[Employee]\n" +
                "ID=" + id + "\n" +
                "Name=" + name + "\n" +
                "Department=" + department + "\n" +
                "Salary=" + salary + "\n" +
                "JobTitle=" + jobTitle + "\n" +
                "DateOfBirth=" + (dateOfBirth != null ? DATE_FORMAT.format(dateOfBirth) : "") + "\n" +
                "HireDate=" + (hireDate != null ? DATE_FORMAT.format(hireDate) : "") + "\n" +
                "Address=" + address + "\n" +
                "Phone=" + phone + "\n" +
                "Email=" + email + "\n" +
                "PositionLevel=" + positionLevel + "\n" +
                "EmploymentStatus=" + employmentStatus + "\n" +
                "PerformanceScore=" + performanceScore + "\n" +
                "ManagerId=" + managerId + "\n" +
                "Skills=" + String.join(",", skills) + "\n" +
                "Bonus=" + bonus + "\n" +
                "BenefitsPackage=" + benefitsPackage + "\n" +
                "VacationDays=" + vacationDays + "\n" +
                "SickDays=" + sickDays + "\n" +
                "EmergencyContactName=" + emergencyContactName + "\n" +
                "EmergencyContactRelation=" + emergencyContactRelation + "\n" +
                "EmergencyContactPhone=" + emergencyContactPhone + "\n";
    }

    public static Employee fromString(List<String> lines) {
        Employee employee = null;
        try {
            int id = 0;
            String name = "";
            String department = "";
            double salary = 0;
            String jobTitle = "";
            Date dateOfBirth = null;
            Date hireDate = null;
            String address = "";
            String phone = "";
            String email = "";

            String positionLevel = "";
            String employmentStatus = "";
            int performanceScore = 0;
            int managerId = 0;
            List<String> skills = new ArrayList<>();

            double bonus = 0;
            String benefitsPackage = "";
            int vacationDays = 0;
            int sickDays = 0;

            String emergencyContactName = "";
            String emergencyContactRelation = "";
            String emergencyContactPhone = "";

            for (String line : lines) {
                if (line.startsWith("ID=")) id = Integer.parseInt(line.substring(3));
                else if (line.startsWith("Name=")) name = line.substring(5);
                else if (line.startsWith("Department=")) department = line.substring(11);
                else if (line.startsWith("Salary=")) salary = Double.parseDouble(line.substring(7));
                else if (line.startsWith("JobTitle=")) jobTitle = line.substring(9);
                else if (line.startsWith("DateOfBirth=")) dateOfBirth = DATE_FORMAT.parse(line.substring(12));
                else if (line.startsWith("HireDate=")) hireDate = DATE_FORMAT.parse(line.substring(9));
                else if (line.startsWith("Address=")) address = line.substring(8);
                else if (line.startsWith("Phone=")) phone = line.substring(6);
                else if (line.startsWith("Email=")) email = line.substring(6);

                else if (line.startsWith("PositionLevel=")) positionLevel = line.substring(14);
                else if (line.startsWith("EmploymentStatus=")) employmentStatus = line.substring(17);
                else if (line.startsWith("PerformanceScore=")) performanceScore = Integer.parseInt(line.substring(17));
                else if (line.startsWith("ManagerId=")) managerId = Integer.parseInt(line.substring(10));
                else if (line.startsWith("Skills=")) {
                    String skillsStr = line.substring(7);
                    for (String skill : skillsStr.split(",")) {
                        skills.add(skill.trim());
                    }
                }

                else if (line.startsWith("Bonus=")) bonus = Double.parseDouble(line.substring(6));
                else if (line.startsWith("BenefitsPackage=")) benefitsPackage = line.substring(16);
                else if (line.startsWith("VacationDays=")) vacationDays = Integer.parseInt(line.substring(13));
                else if (line.startsWith("SickDays=")) sickDays = Integer.parseInt(line.substring(9));

                else if (line.startsWith("EmergencyContactName=")) emergencyContactName = line.substring(20);
                else if (line.startsWith("EmergencyContactRelation=")) emergencyContactRelation = line.substring(24);
                else if (line.startsWith("EmergencyContactPhone=")) emergencyContactPhone = line.substring(21);
            }

            employee = new Employee(id, name, department, salary, jobTitle);
            employee.setDateOfBirth(dateOfBirth);
            employee.setHireDate(hireDate);
            employee.setAddress(address);
            employee.setPhone(phone);
            employee.setEmail(email);

            employee.setPositionLevel(positionLevel);
            employee.setEmploymentStatus(employmentStatus);
            employee.setPerformanceScore(performanceScore);
            employee.setManagerId(managerId);
            employee.setSkills(skills);

            employee.setBonus(bonus);
            employee.setBenefitsPackage(benefitsPackage);
            employee.setVacationDays(vacationDays);
            employee.setSickDays(sickDays);

            employee.setEmergencyContactName(emergencyContactName);
            employee.setEmergencyContactRelation(emergencyContactRelation);
            employee.setEmergencyContactPhone(emergencyContactPhone);
        } catch (ParseException e) {
            System.out.println("Error parsing date: " + e.getMessage());
        }
        return employee;
    }
}

