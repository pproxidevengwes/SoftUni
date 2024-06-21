import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());
        Map<String, Department> departments = new HashMap<>();

        for (int i = 0; i < n; i++) {
            String[] input = scanner.nextLine().split("\\s+");
            String name = input[0];
            double salary = Double.parseDouble(input[1]);
            String position = input[2];
            String department = input[3];
            Employee employee;
            departments.putIfAbsent(department, new Department());
            if (input.length == 5) {
                if (Character.isAlphabetic(input[4].charAt(0))) {
                    String email = input[4];
                    employee = new Employee(name, salary, position, department, email);
                } else {
                    int age = Integer.parseInt(input[4]);
                    employee = new Employee(name, salary, position, department, age);
                }
            } else if (input.length == 6) {
                String email = input[4];
                int age = Integer.parseInt(input[5]);
                employee = new Employee(name, salary, position, department, email, age);
            } else {
                employee = new Employee(name, salary, position, department);
            }
            departments.get(department).addEmployee(employee);
        }
        double bestAvgSalary = 0.0;
        String bestDepartment = "";
        for (Map.Entry<String, Department> entry : departments.entrySet()) {
            if (entry.getValue().getAvarageSalary() > bestAvgSalary) {
                bestAvgSalary = entry.getValue().getAvarageSalary();
                bestDepartment = entry.getKey();
            }
        }
        System.out.printf("Highest Average Salary: %s%n", bestDepartment);
        departments.get(bestDepartment).getEmployees()
                .stream().sorted((a, b) -> Double.compare(b.getSalary(), a.getSalary())).forEach(System.out::println);
    }
}
