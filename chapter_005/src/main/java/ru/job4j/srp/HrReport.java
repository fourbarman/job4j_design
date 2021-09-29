package ru.job4j.srp;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * HrReport.
 * <p>
 * Class for making report for HR (2 columns and sorted by salary).
 *
 * @author fourbarman (maks.java@yandex.ru).
 * @version %I%, %G%.
 * @since 29.09.2021.
 */
public class HrReport implements DepartmentReport {
    /**
     * Makes String report view for HR department.
     * Report contains only 2 columns and sorted by salary.
     *
     * @param employees List of Employee objects.
     * @return String report.
     */
    @Override
    public StringBuilder makeReport(List<Employee> employees) {
        List<Employee> sorted = new ArrayList<>(employees);
        sorted.sort(Comparator.comparingDouble(Employee::getSalary));
        StringBuilder report = new StringBuilder();
        report.append("Name; Salary;").append(System.lineSeparator());
        for (Employee employee : sorted) {
            report.append(employee.getName()).append(";")
                    .append(employee.getSalary()).append(";")
                    .append(System.lineSeparator());
        }
        return report;
    }
}
