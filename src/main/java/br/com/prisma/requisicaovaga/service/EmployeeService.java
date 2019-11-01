package br.com.prisma.requisicaovaga.service;

import br.com.prisma.requisicaovaga.model.Employee;
import javax.inject.Inject;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {

    @Inject
    PlatformService platform;

    public Employee getEmployeeByEmployeeId(String employeeId, String token) {
        System.out.println("Chamando getEmployeeByEmployeeId: " + employeeId);
        return platform.getEntity("employee/work-contract-situation/", employeeId, Employee.class, token);
    }
}
