package br.com.prisma.requisicaovaga.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;
import javax.xml.bind.annotation.XmlRootElement;

@JsonIgnoreProperties(ignoreUnknown = true)
@XmlRootElement(name = "employee")
public class Employee {

    @JsonProperty("employeeId")
    private String employeeId;

    @JsonProperty("name")
    private String name;

    @JsonProperty("companyNumber")
    private String companyNumber;

    @JsonProperty("employeeType")
    private String employeeType;

    @JsonProperty("registerNumber")
    private int registerNumber;

    @JsonProperty("situation")
    private String situation;

    public Employee() {
    }

    public Employee(String employeeId, String name, String companyNumber, String employeeType, int registerNumber, String situation) {
        this.employeeId = employeeId;
        this.name = name;
        this.companyNumber = companyNumber;
        this.employeeType = employeeType;
        this.registerNumber = registerNumber;
        this.situation = situation;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCompanyNumber() {
        return companyNumber;
    }

    public void setCompanyNumber(String companyNumber) {
        this.companyNumber = companyNumber;
    }

    public String getEmployeeType() {
        return employeeType;
    }

    public void setEmployeeType(String employeeType) {
        this.employeeType = employeeType;
    }

    public int getRegisterNumber() {
        return registerNumber;
    }

    public void setRegisterNumber(int registerNumber) {
        this.registerNumber = registerNumber;
    }

    public String getSituation() {
        return situation;
    }

    public void setSituation(String situation) {
        this.situation = situation;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 23 * hash + Objects.hashCode(this.employeeId);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Employee other = (Employee) obj;
        if (!Objects.equals(this.employeeId, other.employeeId)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Employee{" + "employeeId=" + employeeId + ", name=" + name + ", companyNumber=" + companyNumber + ", employeeType=" + employeeType + ", registerNumber=" + registerNumber + ", situation=" + situation + '}';
    }

}
