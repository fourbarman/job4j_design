package ru.job4j.srp;

import java.util.function.Predicate;

/**
 * ReportEngine.
 * <p>
 * Class for generating reports with given view.
 *
 * @author fourbarman (maks.java@yandex.ru).
 * @version %I%, %G%.
 * @since 29.09.2021.
 */
public class ReportEngine implements Report {

    private Store store;

    public ReportEngine(Store store) {
        this.store = store;
    }

    /**
     * Generates report.
     *
     * @param filter           Filters result.
     * @param departmentReport Report type.
     * @return String report.
     */
    public String generate(Predicate<Employee> filter, DepartmentReport departmentReport) {
        StringBuilder text = new StringBuilder();
        text.append(departmentReport.makeReport(store.findBy(filter)));
        return text.toString();
    }
}
