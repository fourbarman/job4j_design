package ru.job4j.srp;

import java.util.List;

/**
 * BuhReport.
 * <p>
 * Class for making report for Accounting (with changed salary type).
 *
 * @author fourbarman (maks.java@yandex.ru).
 * @version %I%, %G%.
 * @since 29.09.2021.
 */
public class AccountantReport implements DepartmentReport {
    /**
     * Makes String report view for Accountant department.
     * Report has changed "salary" view.
     *
     * @param employees List of Employee objects.
     * @return String report.
     */
    @Override
    public StringBuilder makeReport(List<Employee> employees) {
        StringBuilder report = new StringBuilder();
        report.append("Name; Hired; Fired; Salary;").append(System.lineSeparator());
        for (Employee employee : employees) {
            report.append(employee.getName()).append(";")
                    .append(employee.getHired()).append(";")
                    .append(employee.getFired()).append(";")
                    .append(salaryChange(employee.getSalary())).append(";")
                    .append(System.lineSeparator());
        }
        return report;
    }

    /**
     * Changes view of "Salary".
     * Converts from whole digit to "w\o tax + tax 13%".
     *
     * @param salary Salary.
     * @return String salary.
     */
    private String salaryChange(double salary) {
        double ndfl = (salary / 100) * 13;
        double ostatok = salary - ndfl;
        return ostatok + " + " + ndfl;
    }
}
