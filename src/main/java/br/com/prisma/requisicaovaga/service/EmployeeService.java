package br.com.prisma.requisicaovaga.service;

import br.com.prisma.requisicaovaga.model.Employee;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import static javax.ws.rs.client.Entity.json;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {

    private static final String REST_URI = "https://hcm-api.senior.com.br/frontend-api/employee/work-contract-situation/";

    private Client client = ClientBuilder.newClient();

    @Inject
    PlatformService platform;

//    public Employee getEmployeeByEmployeeId(String employeeId, String token) {
//        System.out.println("Chamando getEmployeeByEmployeeId: " + employeeId);
//        return platform.getEntity("employee/work-contract-situation/", employeeId, Employee.class, token);
//    }

    public Employee getEmployee(String employeeId, String token) {
        Employee emp = new Employee();

        Response response = client
                .target(REST_URI)
                .path(String.valueOf(employeeId))
                .request(MediaType.APPLICATION_JSON)
                .header(HttpHeaders.AUTHORIZATION, token)
                .get();

        if (response.getStatus() == 200) {
            System.out.println(response.getEntity().toString());
            System.out.println(response.getMediaType());

            ObjectMapper mapper = new ObjectMapper();

            try {
                JsonNode jsonNode = mapper.readTree(response.readEntity(String.class));
                emp.setEmployeeId(jsonNode.get("employee").get("employeeId").asText());
                emp.setName(jsonNode.get("employee").get("name").asText());
                emp.setCompanyNumber(jsonNode.get("employee").get("companyNumber").asText());
                emp.setEmployeeType(jsonNode.get("employee").get("employeeType").asText());
                emp.setRegisterNumber(jsonNode.get("employee").get("registrationNumber").asInt());
                emp.setSituation(jsonNode.get("employee").get("situation").asText());

            } catch (JsonProcessingException ex) {
                Logger.getLogger(EmployeeService.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return emp;
    }
}
