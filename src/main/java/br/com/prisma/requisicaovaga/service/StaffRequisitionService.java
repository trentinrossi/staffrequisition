package br.com.prisma.requisicaovaga.service;

import br.com.prisma.requisicaovaga.model.Employee;
import br.com.prisma.requisicaovaga.model.StaffRequisition;
import javax.inject.Inject;
import org.springframework.stereotype.Service;

@Service
public class StaffRequisitionService {

    @Inject
    EmployeeService employeeService;

    /**
     * Método para validar se o colaborador informado na requisiçõe da vaga do
     * tipo substituição já foi informado em outra requisição.
     *
     * @param staffRequisition Payload da requisição da vaga
     */
    public void validateStaffRequisition(StaffRequisition staffRequisition) {
        Employee e = employeeService.getEmployeeByEmployeeId(staffRequisition.getReplacedEmployeeId());
        System.out.println("Empoloyee: " + e);
    }
}
