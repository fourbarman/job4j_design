package ru.job4j.srp;

import com.google.gson.GsonBuilder;

import java.util.List;

/**
 * JSONReport.
 * <p>
 * Class for making report in JSON format.
 *
 * @author fourbarman (maks.java@yandex.ru).
 * @version %I%, %G%.
 * @since 14.10.2021.
 */
public class JSONReport implements DepartmentReport {
    /**
     * Makes report in JSON format.
     *
     * @param employees List of Employee objects.
     * @return String report.
     */
    @Override
    public StringBuilder makeReport(List<Employee> employees) {
        var lib = new GsonBuilder().create();
        return new StringBuilder().append(lib.toJson(employees));
    }
}
