package ru.job4j.srp;

import java.util.List;

/**
 * DepartmentReport.
 * <p>
 * Interface for report type.
 *
 * @author fourbarman (maks.java@yandex.ru).
 * @version %I%, %G%.
 * @since 29.09.2021.
 */
public interface DepartmentReport {
    /**
     * Makes String report view.
     *
     * @param employees List of Employee objects.
     * @return report.
     */
    StringBuilder makeReport(List<Employee> employees);
}
