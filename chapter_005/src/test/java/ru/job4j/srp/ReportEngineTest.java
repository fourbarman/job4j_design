package ru.job4j.srp;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;

/**
 * Test.
 *
 * @author fourbarman (maks.java@yandex.ru).
 * @version %I%, %G%.
 * @since 29.09.2021.
 */
public class ReportEngineTest {
    MemStore store;
    Calendar now;
    Report engine;
    StringBuilder expect;
    Employee worker1;
    Employee worker2;
    Employee worker3;
    Employee worker4;
    Employee worker5;

    /**
     * Setting variables.
     */
    @Before
    public void setVars() {
        store = new MemStore();
        now = Calendar.getInstance();
        engine = new ReportEngine(store);
        expect = new StringBuilder();
        worker1 = new Employee("Ivan", now, now, 550);
        worker2 = new Employee("Max", now, now, 300);
        worker3 = new Employee("Petr", now, now, 100);
        worker4 = new Employee("Alex", now, now, 120);
        worker5 = new Employee("Julia", now, now, 400);
        store.add(worker1);
        store.add(worker2);
        store.add(worker3);
        store.add(worker4);
        store.add(worker5);
    }

    /**
     * Test generate().
     * With ItReport object and filter 1 Employee by name.
     * Should return report view specified in ItReport object.
     */
    @Test
    public void whenITGenerated() {
        StringBuilder expect = new StringBuilder()
                .append("<!DOCTYPE html>")
                .append("<html>")
                .append("<head>")
                .append("<title>").append("ITReport").append("</title>")
                .append("</head>")
                .append("<body>")
                .append("Name; Hired; Fired; Salary;")
                .append(System.lineSeparator())
                .append(worker1.getName()).append(";")
                .append(worker1.getHired()).append(";")
                .append(worker1.getFired()).append(";")
                .append(worker1.getSalary()).append(";")
                .append(System.lineSeparator())
                .append("</body>")
                .append("</html>");
        assertThat(engine.generate(x -> x.getName().equals("Ivan"), new ItReport()), is(expect.toString()));
    }

    /**
     * Test generate().
     * With HrReport object and filter all Employees and sorted in ascending order.
     * Should pass if return report view specified in HrReport object.
     */
    @Test
    public void whenHrGenerated() {
        StringBuilder expect = new StringBuilder()
                .append("Name; Salary;")
                .append(System.lineSeparator())
                .append(worker3.getName()).append(";")
                .append(worker3.getSalary()).append(";")
                .append(System.lineSeparator())
                .append(worker4.getName()).append(";")
                .append(worker4.getSalary()).append(";")
                .append(System.lineSeparator())
                .append(worker2.getName()).append(";")
                .append(worker2.getSalary()).append(";")
                .append(System.lineSeparator())
                .append(worker5.getName()).append(";")
                .append(worker5.getSalary()).append(";")
                .append(System.lineSeparator())
                .append(worker1.getName()).append(";")
                .append(worker1.getSalary()).append(";")
                .append(System.lineSeparator());
        assertThat(engine.generate(x -> true, new HrReport()), is(expect.toString()));
    }

    /**
     * Test generate().
     * With AccountantReport object and filter 1 Employee by name with changed salary view.
     * Should pass if return report view specified in AccountantReport object.
     */
    @Test
    public void whenBuhGenerated() {
        StringBuilder expect = new StringBuilder()
                .append("Name; Hired; Fired; Salary;")
                .append(System.lineSeparator())
                .append(worker1.getName()).append(";")
                .append(worker1.getHired()).append(";")
                .append(worker1.getFired()).append(";")
                .append("478.5 + 71.5").append(";")
                .append(System.lineSeparator());
        assertThat(engine.generate(x -> x.getName().equals("Ivan"), new AccountantReport()), is(expect.toString()));
    }
}
