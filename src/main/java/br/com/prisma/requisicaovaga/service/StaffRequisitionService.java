package br.com.prisma.requisicaovaga.service;

import br.com.prisma.requisicaovaga.model.Employee;
import br.com.prisma.requisicaovaga.model.EmployeeSearchRecruitmentInput;
import br.com.prisma.requisicaovaga.model.EmployeeSearchRecruitmentOutput;
import br.com.prisma.requisicaovaga.model.StaffRequisition;
import javax.inject.Inject;
import org.springframework.stereotype.Service;

@Service
public class StaffRequisitionService {

    @Inject
    EmployeeService employeeService;

    @Inject
    RecruitmentService recruitmentService;

    /**
     * Método para validar se o colaborador informado na requisiçõe da vaga do
     * tipo substituição já foi informado em outra requisição.
     *
     * @param staffRequisition Payload da requisição da vaga
     * @param token Token do usuário logado, necessário para realizar o login no
     * HCM
     */
    public void validateStaffRequisition(StaffRequisition staffRequisition, String token) {
        Employee e = employeeService.getEmployee(staffRequisition.getReplacedEmployeeId(), token);

        EmployeeSearchRecruitmentInput input = new EmployeeSearchRecruitmentInput();
        input.setQ(e.getName());
        EmployeeSearchRecruitmentOutput output = recruitmentService.isExistsReferenceOfThisReplacedEmployeeOnVacancy(input, token);

        if (output.isExistsReferenceOfThisReplacedEmployeeOnVacancy() || output.isExistsReferenceOfThisReplacedEmployeeOnStaffRequisition()) {
            System.out.println("Deve bloquear a requisição");
        }

        System.out.println("Eployee: " + e);
    }
}
