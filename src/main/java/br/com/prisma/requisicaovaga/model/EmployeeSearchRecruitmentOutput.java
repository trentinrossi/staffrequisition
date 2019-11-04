package br.com.prisma.requisicaovaga.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class EmployeeSearchRecruitmentOutput {

    private boolean existsReferenceOfThisReplacedEmployeeOnStaffRequisition;
    private boolean existsReferenceOfThisReplacedEmployeeOnVacancy;

    public EmployeeSearchRecruitmentOutput() {
    }

    public EmployeeSearchRecruitmentOutput(boolean existsReferenceOfThisReplacedEmployeeOnStaffRequisition, boolean existsReferenceOfThisReplacedEmployeeOnVacancy) {
        this.existsReferenceOfThisReplacedEmployeeOnStaffRequisition = existsReferenceOfThisReplacedEmployeeOnStaffRequisition;
        this.existsReferenceOfThisReplacedEmployeeOnVacancy = existsReferenceOfThisReplacedEmployeeOnVacancy;
    }

    public boolean isExistsReferenceOfThisReplacedEmployeeOnStaffRequisition() {
        return existsReferenceOfThisReplacedEmployeeOnStaffRequisition;
    }

    public void setExistsReferenceOfThisReplacedEmployeeOnStaffRequisition(boolean existsReferenceOfThisReplacedEmployeeOnStaffRequisition) {
        this.existsReferenceOfThisReplacedEmployeeOnStaffRequisition = existsReferenceOfThisReplacedEmployeeOnStaffRequisition;
    }

    public boolean isExistsReferenceOfThisReplacedEmployeeOnVacancy() {
        return existsReferenceOfThisReplacedEmployeeOnVacancy;
    }

    public void setExistsReferenceOfThisReplacedEmployeeOnVacancy(boolean existsReferenceOfThisReplacedEmployeeOnVacancy) {
        this.existsReferenceOfThisReplacedEmployeeOnVacancy = existsReferenceOfThisReplacedEmployeeOnVacancy;
    }

}
