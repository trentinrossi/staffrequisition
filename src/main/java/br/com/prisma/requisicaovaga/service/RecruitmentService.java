package br.com.prisma.requisicaovaga.service;

import br.com.prisma.requisicaovaga.model.EmployeeSearchRecruitmentInput;
import br.com.prisma.requisicaovaga.model.EmployeeSearchRecruitmentOutput;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.springframework.stereotype.Service;

@Service
public class RecruitmentService {

    private static final String SERVICE_URL = "/recruitment/queries/searchEmployees";
    private final Client client = ClientBuilder.newClient();

    public EmployeeSearchRecruitmentOutput isExistsReferenceOfThisReplacedEmployeeOnVacancy(EmployeeSearchRecruitmentInput employeeSearch, String token) {
        EmployeeSearchRecruitmentOutput out = new EmployeeSearchRecruitmentOutput();

        Response response = client
                .target(PlatformService.PLATFORM_API_URL)
                .path(SERVICE_URL)
                .request(MediaType.APPLICATION_JSON)
                .header(HttpHeaders.AUTHORIZATION, token)
                .post(Entity.entity(employeeSearch, MediaType.APPLICATION_JSON));

        if (response.getStatus() == 200) {
            ObjectMapper mapper = new ObjectMapper();

            try {
                JsonNode jsonNode = mapper.readTree(response.readEntity(String.class));                
                out.setExistsReferenceOfThisReplacedEmployeeOnVacancy(jsonNode.findValue("existsReferenceOfThisReplacedEmployeeOnVacancy").asBoolean());
                out.setExistsReferenceOfThisReplacedEmployeeOnStaffRequisition(jsonNode.findValue("existsReferenceOfThisReplacedEmployeeOnStaffRequisition").asBoolean());
            } catch (JsonProcessingException ex) {
                System.out.println("Erro: " + ex.getMessage());
            } catch (Exception ex) {
                System.out.println("Erro: " + ex.getMessage());
            }
        }
        return out;
    }
}
