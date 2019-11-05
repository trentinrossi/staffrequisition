package br.com.prisma.requisicaovaga.service;

import br.com.prisma.requisicaovaga.model.Employee;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {
    private static final String SERVICE_URL = "employee/work-contract-situation/";
    private final Client client = ClientBuilder.newClient();

    /**
     * Retorna os dados básicos de um colaborador, incluindo NumEmp, TipCol e NumCad
     * 
     * @param employeeId Id do colaborador ao qual se deseja obter as informações
     * @param token Token do usuário logado para login no HCM
     * @return Dados básicos de um colaborador, incluindo NumEmp, TipCol e NumCad
     */
    public Employee getEmployee(String employeeId, String token) {
        Employee emp = new Employee();

        Response response = client
                .target(PlatformService.HCM_API_URL)
                .path(SERVICE_URL)
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
            } catch (Exception ex) {
                Logger.getLogger(EmployeeService.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return emp;
    }
}
