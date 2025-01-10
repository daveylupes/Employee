import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class RowellHoldingsEmployeeManagement {
    public static void main(String[] args) {
        // 1. Create and populate the dataset
        List<Employee> employees = Arrays.asList(
                new Employee("Thabo Mokoena", 45, "Finance", 85000),
                new Employee("Nomvula Khumalo", 32, "HR", 55000),
                new Employee("Sipho Ndlovu", 28, "IT", 70000),
                new Employee("Ayanda Dlamini", 36, "Operations", 60000),
                new Employee("Lerato Molefe", 40, "Marketing", 75000)
        );

        // 2. Use Function interface to concatenate name and department
        Function<Employee, String> nameAndDepartment = employee ->
                employee.getName() + " (" + employee.getDepartment() + ")";

        // 3. Generate a new collection of concatenated strings using streams
        List<String> employeeDetails = employees.stream()
                .map(nameAndDepartment) // Apply the Function interface
                .collect(Collectors.toList());
        System.out.println("Employee Details:\n" + String.join("\n", employeeDetails));

        // 4. Calculate the average salary of all employees
        double averageSalary = employees.stream()
                .mapToDouble(Employee::getSalary) // Extract salaries
                .average() // Calculate average
                .orElse(0.0); // Handle empty dataset case
        System.out.println("Average Salary: " + averageSalary);

        // 5. Filter employees by age threshold (e.g., age > 30)
        int ageThreshold = 30;
        List<Employee> filteredEmployees = employees.stream()
                .filter(employee -> employee.getAge() > ageThreshold) // Apply filter
                .collect(Collectors.toList());
        System.out.println("Filtered Employees (Age > " + ageThreshold + "):\n" +
                filteredEmployees.stream().map(Employee::toString).collect(Collectors.joining("\n")));

        // 6. BONUS: Sort filtered employees by salary (descending order) and print
        List<Employee> sortedEmployees = filteredEmployees.stream()
                .sorted(Comparator.comparingDouble(Employee::getSalary).reversed())
                .collect(Collectors.toList());
        System.out.println("Sorted Employees by Salary (Descending):\n" +
                sortedEmployees.stream().map(Employee::toString).collect(Collectors.joining("\n")));
    }
}