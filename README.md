# Employee Management System

## Overview

The **Employee Management System** is a simple terminal application built with Java. It allows users to manage employee records, including adding, updating, deleting, and viewing employee information. This system is designed to help small businesses or HR departments organize employee data efficiently.

## Features

- Add new employees with details like name, ID, department, and salary.
- View all employees in a list format.
- Edit existing employee information.
- Delete employees from the system.
- Data persistence using file handling.

## Tech Stack

- **Programming Language**: Java
- **Database**: File handling

## Installation & Setup

### Prerequisites

- Java Development Kit (JDK) 8 or higher

### Steps

1. Clone the repository:

   ```bash
   git clone https://github.com/monster0506/employee-management-system.git
   ```

2. Open the project in your preferred IDE (e.g., IntelliJ IDEA, Eclipse) **or** follow the terminal instructions below.

3. **Compile and Run in the Terminal** (if not using an IDE):

   - Navigate to the root of the project:

     ```bash
     cd employee-management-system
     ```

   - **Compile** all Java files:

     ```bash
     javac src/\*.java -d out
     ```

   - **Run** the application:

     ```bash
     java -cp out Main
     ```

## Usage

1. On startup, the main window will display a list of employees.
2. Use the **Add Employee** button to create a new employee record.
3. Click on an employee in the list to edit their details.
4. Use the **Delete** button to remove an employee.
5. Employee data is saved to a file (or database) and persists between sessions.

## Future Enhancements

- Implement search functionality to quickly find employees.
- Add data validation for employee input fields.

## Contributing

If you’d like to contribute to this project, feel free to fork the repository and submit a pull request. For any issues or suggestions, please open a new issue on GitHub.

## License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for more details.
