package br.com.prisma.requisicaovaga.service;

import br.com.prisma.requisicaovaga.model.Employee;
import br.com.prisma.requisicaovaga.model.EmployeeSearchRecruitmentInput;
import br.com.prisma.requisicaovaga.model.EmployeeSearchRecruitmentOutput;
import br.com.prisma.requisicaovaga.model.StaffRequisition;
import br.com.prisma.requisicaovaga.model.StaffRequisitionReason;
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
     * @return true ou false se o colaborador já foi ou não vinculado a outras
     * requisições ou vagas
     * @throws java.lang.Exception
     */
    public boolean isPresentInOthersRequisitions(StaffRequisition staffRequisition, String token) throws Exception {

        if (staffRequisition.getReason() == StaffRequisitionReason.REPLACEMENT) {
            Employee e = employeeService.getEmployee(staffRequisition.getReplacedEmployeeId(), token);

            if (e == null) {
                throw new Exception("Contrato do EmployeeId: " + staffRequisition.getReplacedEmployeeId() + " não encontrado no HCM.");
            }

            EmployeeSearchRecruitmentInput input = new EmployeeSearchRecruitmentInput();
            input.setQ(e.getName());
            EmployeeSearchRecruitmentOutput output = recruitmentService.isExistsReferenceOfThisReplacedEmployee(input, token);

            if (output.isExistsReferenceOfThisReplacedEmployeeOnVacancy() || output.isExistsReferenceOfThisReplacedEmployeeOnStaffRequisition()) {
                return true;
            }

            System.out.println("Eployee: " + e);
        }

        return false;
    }
}
