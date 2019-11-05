package br.com.prisma.requisicaovaga.service;

import br.com.prisma.requisicaovaga.model.Employee;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {

    private static final String SERVICE_URL = "employee/work-contract-situation/";
    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(StaffRequisitionService.class);
    private final Client client = ClientBuilder.newClient();

    /**
     * Retorna os dados básicos de um colaborador, incluindo NumEmp, TipCol e
     * NumCad
     *
     * @param employeeId Id do colaborador ao qual se deseja obter as
     * informações
     * @param token Token do usuário logado para login no HCM
     * @return Dados básicos de um colaborador, incluindo NumEmp, TipCol e
     * NumCad
     */
    public Employee getEmployee(String employeeId, String token) {
        Employee emp = null;

        LOGGER.debug("Montando requisição para retornar o colaborador conforme o employeeId " + PlatformService.HCM_API_URL + SERVICE_URL + employeeId);
        Response response = client
                .target(PlatformService.HCM_API_URL)
                .path(SERVICE_URL)
                .path(String.valueOf(employeeId))
                .request(MediaType.APPLICATION_JSON)
                .header(HttpHeaders.AUTHORIZATION, token)
                .get();

        if (response.getStatus() == 200) {
            LOGGER.debug("Chamada executada com sucesso: 200-OK:" + response);

            ObjectMapper mapper = new ObjectMapper();

            try {
                JsonNode jsonNode = mapper.readTree(response.readEntity(String.class));
                emp = new Employee();
                emp.setEmployeeId(jsonNode.get("employee").get("employeeId").asText());
                emp.setName(jsonNode.get("employee").get("name").asText());
                emp.setCompanyNumber(jsonNode.get("employee").get("companyNumber").asText());
                emp.setEmployeeType(jsonNode.get("employee").get("employeeType").asText());
                emp.setRegisterNumber(jsonNode.get("employee").get("registrationNumber").asInt());
                emp.setSituation(jsonNode.get("employee").get("situation").asText());
                LOGGER.debug("Colaborador retornado: " + emp);
            } catch (JsonProcessingException ex) {
                LOGGER.error("Erro ao procesar o Json do colaborador:" + ex);
            } catch (Exception ex) {
                LOGGER.error("Erro ao retornar o colaborador:" + ex);
            }
        }

        return emp;
    }
}
