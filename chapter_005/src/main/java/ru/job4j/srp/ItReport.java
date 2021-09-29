package ru.job4j.srp;

import java.util.List;

/**
 * ItReport.
 * <p>
 * Class for making report for IT department (HTML style).
 *
 * @author fourbarman (maks.java@yandex.ru).
 * @version %I%, %G%.
 * @since 29.09.2021.
 */
public class ItReport implements DepartmentReport {
    /**
     * Makes String report view for IT department.
     * Report in HTML format.
     *
     * @param employees List of Employee objects.
     * @return String report.
     */
    @Override
    public StringBuilder makeReport(List<Employee> employees) {
        StringBuilder report = new StringBuilder();
        report.append("<!DOCTYPE html>")
                .append("<html>")
                .append("<head>")
                .append("<title>").append("ITReport").append("</title>")
                .append("</head>")
                .append("<body>")
                .append("Name; Hired; Fired; Salary;")
                .append(System.lineSeparator());
        for (Employee employee : employees) {
            report.append(employee.getName()).append(";")
                    .append(employee.getHired()).append(";")
                    .append(employee.getFired()).append(";")
                    .append(employee.getSalary()).append(";")
                    .append(System.lineSeparator());
        }
        report.append("</body>").append("</html>");
        return report;
    }
}
