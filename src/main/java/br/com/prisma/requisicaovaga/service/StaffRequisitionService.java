package br.com.prisma.requisicaovaga.service;

import br.com.prisma.requisicaovaga.model.Employee;
import br.com.prisma.requisicaovaga.model.EmployeeSearchRecruitmentInput;
import br.com.prisma.requisicaovaga.model.EmployeeSearchRecruitmentOutput;
import br.com.prisma.requisicaovaga.model.StaffRequisition;
import br.com.prisma.requisicaovaga.model.StaffRequisitionReason;
import javax.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class StaffRequisitionService {

    private static final Logger LOGGER = LoggerFactory.getLogger(StaffRequisitionService.class);

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
            LOGGER.debug("Tentando recuperar o contrato do colaborador conforme eu ID: " + staffRequisition.getReplacedEmployeeId());
            Employee e = employeeService.getEmployee(staffRequisition.getReplacedEmployeeId(), token);

            if (e == null) {
                LOGGER.error("Contrato do EmployeeId: " + staffRequisition.getReplacedEmployeeId() + " não encontrado no HCM.");
                throw new Exception("Contrato do EmployeeId: " + staffRequisition.getReplacedEmployeeId() + " não encontrado no HCM.");
            }

            LOGGER.debug("Colaborador retornado: " + e);
            EmployeeSearchRecruitmentInput input = new EmployeeSearchRecruitmentInput();
            input.setQ(e.getName());

            LOGGER.debug("Tentando validar se o colaborador já está vinculado a alguma requisição ou vaga...");
            EmployeeSearchRecruitmentOutput output = recruitmentService.isExistsReferenceOfThisReplacedEmployee(input, token);

            if (output.isExistsReferenceOfThisReplacedEmployeeOnVacancy() || output.isExistsReferenceOfThisReplacedEmployeeOnStaffRequisition()) {
                LOGGER.debug("Colaborador já está vinculado a uma requisição ou vaga.");
                return true;
            }
        }
        
        LOGGER.debug("Colaborador não encontrado em nenhuma nenhuma requisição ou vaga anterior.");
        return false;
    }
}
