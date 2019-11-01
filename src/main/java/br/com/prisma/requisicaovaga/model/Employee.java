package br.com.prisma.requisicaovaga.model;

import java.util.Objects;

public class Employee {

    private String employeeId;
    private String name;
    private String companyNumber;
    private String employeeType;
    private String registerNumber;
    private String situation;

    public Employee() {
    }

    public Employee(String employeeId, String name, String companyNumber, String employeeType, String registerNumber, String situation) {
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

    public String getRegisterNumber() {
        return registerNumber;
    }

    public void setRegisterNumber(String registerNumber) {
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
