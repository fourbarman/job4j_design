package ru.job4j.srp;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.IOException;
import java.io.StringWriter;
import java.util.List;

/**
 * XMLReport.
 * <p>
 * Class for making report in XML format.
 * Uses inner class Employees for making proper XML report of all Employee objects in List.
 *
 * @author fourbarman (maks.java@yandex.ru).
 * @version %I%, %G%.
 * @since 14.10.2021.
 */
public class XMLReport implements DepartmentReport {
    /**
     * Makes report in XML format.
     *
     * @param employees List of Employee objects.
     * @return String report.
     */
    @Override
    public StringBuilder makeReport(List<Employee> employees) {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            stringBuilder.append(this.makeXML(employees));
        } catch (JAXBException jaxbException) {
            System.out.println(jaxbException.toString());
        }
        return stringBuilder;
    }

    /**
     * Helper method.
     * Uses "javax.xml.bind" libraries.
     * Makes report in XML format.
     *
     * @param employees List of Employee objects.
     * @return String report.
     */
    private String makeXML(List<Employee> employees) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(Employees.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        String xml = "";
        try (StringWriter writer = new StringWriter()) {
            marshaller.marshal(new Employees(employees), writer);
            xml = writer.getBuffer().toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return xml;
    }

    /**
     * Employees.
     * <p>
     * Class represents employee.
     *
     * @author fourbarman (maks.java@yandex.ru).
     * @version %I%, %G%.
     * @since 14.10.2021.
     */
    @XmlRootElement(name = "employees")
    public static class Employees {
        @XmlElement(name = "employee")
        private List<Employee> employees = null;

        public Employees() {
        }

        public Employees(List<Employee> employees) {
            this.employees = employees;
        }

        public void setEmployees(List<Employee> employees) {
            this.employees = employees;
        }
    }
}
