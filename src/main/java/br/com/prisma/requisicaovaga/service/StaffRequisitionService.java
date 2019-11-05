package br.com.prisma.requisicaovaga.service;

import br.com.prisma.requisicaovaga.model.Employee;
import br.com.prisma.requisicaovaga.model.EmployeeSearchRecruitmentInput;
import br.com.prisma.requisicaovaga.model.EmployeeSearchRecruitmentOutput;
import br.com.prisma.requisicaovaga.model.NotifyUserInput;
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

    @Inject
    NotificationService notificationService;
    
    @Inject
    ObterMeusDadosService loggedUserService;

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

                String username = loggedUserService.returnUserLoggedWithDomain(token);
                
                NotifyUserInput notification = new NotifyUserInput();
                notification.setDestinationUser(username);
                notification.setNotificationClass("toast");
                notification.setNotificationOrigin("Gestão do Recrutamento");
                notification.setNotificationPriority("None");
                notification.setNotificationKind("Operational");
                notification.setNotificationSubject("Requisição de vaga");
                notification.setNotificationContent("Requisição não pode ser aberta pois " + e.getName() + " já está associado(a) a outra Requisição/Vaga");
                notification.setSourceDomain("hcm");
                notification.setSourceService("recruitment");
                notification.setLink("https://platform.senior.com.br/senior-x/#/Notifica%C3%A7%C3%B5es/0/all?category=notification&link=https%3A%2F%2Fplatform.senior.com.br%2Fnotifications-frontend%2F");

                notificationService.notifyUser(notification, token);

                return true;
            }
        }

        LOGGER.debug("Colaborador não encontrado em nenhuma nenhuma requisição ou vaga anterior.");
        return false;
    }
}
