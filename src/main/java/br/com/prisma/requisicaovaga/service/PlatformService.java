package br.com.prisma.requisicaovaga.service;

import br.com.prisma.requisicaovaga.model.JsonToken;
import br.com.prisma.requisicaovaga.model.LoginWithKeyInput;
import br.com.prisma.requisicaovaga.model.LoginWithKeyOutput;
import java.io.IOException;
import java.util.Optional;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class PlatformService {

    // private static final String VACATION_MANAGEMENT_GETVACATIONPOLICYBYEMPLOYEE_QUERY_PATH = "/t/senior.com.br/bridge/1.0/rest/hcm/vacationmanagement/queries/getVacationPolicyByEmployee";
    // private static final String VACATION_MANAGEMENT_ENTITIES_PATH = "/t/senior.com.br/bridge/1.0/rest/hcm/vacationmanagement/entities/%s/%s";
    private static final String HCM_API_URL = "https://hcm-api.senior.com.br/frontend-api/";
    private static final String LOGIN_WITH_KEY_PATH = "/t/senior.com.br/bridge/1.0/anonymous/rest/platform/authentication/actions/loginWithKey";
    private static final String BEARER_TOKEN = "Bearer %s";

    @Value("${PLATFORM_URL}")
    private String PLATFORM_URL;

    @Value("${TENANT}")
    private String TENANT;

    @Value("${ACCESS_KEY}")
    private String ACCESS_KEY;

    @Value("${SECRET}")
    private String SECRET;

    private Optional<JsonToken> token = Optional.empty();

    private WebTarget getClient() {
        Client client = ClientBuilder.newClient();
        return client.target(PLATFORM_URL);
    }

    public <T> T getEntity(String entity, String id, Class<T> tClass) {
        login();

        WebTarget target = getClient();
        String path = String.format(HCM_API_URL, entity, id);
        Invocation.Builder builder = target.path(path)
                .request(MediaType.APPLICATION_JSON)
                .header(HttpHeaders.AUTHORIZATION, String.format(BEARER_TOKEN, token.get().getAccessToken()));
        return builder.get(tClass);
    }

    /**
     * Realiza o login na Senior X Utiliza as variáveis de ambiente TENANT,
     * ACCESS_KEY e SECRET para fazer o login
     */
    private void login() {
        if (!token.isPresent() || token.get().isExpired()) {
            LoginWithKeyInput loginWithKeyInput = new LoginWithKeyInput();
            loginWithKeyInput.tenantName = TENANT;
            loginWithKeyInput.accessKey = ACCESS_KEY;
            loginWithKeyInput.secret = SECRET;

            WebTarget target = getClient();

            Invocation.Builder builder = target.path(LOGIN_WITH_KEY_PATH).request(MediaType.APPLICATION_JSON);
            Response response = builder.post(Entity.entity(loginWithKeyInput, MediaType.APPLICATION_JSON));
            LoginWithKeyOutput output = response.readEntity(LoginWithKeyOutput.class);
            try {
                JsonToken jsonToken = new ObjectMapper().readValue(output.jsonToken, JsonToken.class);
                System.out.println("Autenticado!: " + output.jsonToken);
                this.token = Optional.of(jsonToken);
            } catch (IOException e) {
                
            }
        }
    }
}
