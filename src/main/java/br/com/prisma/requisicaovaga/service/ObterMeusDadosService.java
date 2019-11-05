package br.com.prisma.requisicaovaga.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class ObterMeusDadosService {

    private static final String SERVICE_URL = "usuarios/userManager/queries/obterMeusDados";
    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(ObterMeusDadosService.class);
    private final Client client = ClientBuilder.newClient();

    public String returnUserLoggedWithDomain(String token) {
        String userName = null;

        LOGGER.debug("Montando requisição para retornar os dados do usuário do token " + token + " URL: " + PlatformService.PLATFORM_API_URL + SERVICE_URL);

        Response response = client
                .target(PlatformService.PLATFORM_API_URL)
                .path(SERVICE_URL)
                .request(MediaType.APPLICATION_JSON)
                .header(HttpHeaders.AUTHORIZATION, token)
                .get();

        if (response.getStatus() == 200) {
            LOGGER.debug("Chamada executada com sucesso: 200-OK:" + response);

            ObjectMapper mapper = new ObjectMapper();

            try {
                JsonNode jsonNode = mapper.readTree(response.readEntity(String.class));
                String nome = jsonNode.get("nome").asText();
                String tenantDomain = jsonNode.get("tenantDomain").asText();
                userName = nome + "@" + tenantDomain;

                LOGGER.debug("usuário retornado: " + userName);
            } catch (JsonProcessingException ex) {
                LOGGER.error("Erro ao retornar os dados do usuário logado:" + ex);
            }
        } else {
            LOGGER.error("Erro ao retornar os dados do usuário logado:" + response.getStatus() + response.getStatusInfo().getReasonPhrase());
        }

        return userName;
    }
}
