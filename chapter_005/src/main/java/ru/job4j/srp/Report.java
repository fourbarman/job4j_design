package ru.job4j.srp;

import java.util.function.Predicate;

/**
 * Report.
 * <p>
 * Interface for generating reports.
 *
 * @author fourbarman (maks.java@yandex.ru).
 * @version %I%, %G%.
 * @since 29.09.2021.
 */
public interface Report {
    /**
     * Generates report.
     *
     * @param filter           Filters result.
     * @param departmentReport Report type.
     * @return String report.
     */
    String generate(Predicate<Employee> filter, DepartmentReport departmentReport);
}
