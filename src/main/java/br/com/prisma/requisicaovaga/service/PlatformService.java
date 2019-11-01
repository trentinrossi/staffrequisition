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
import javax.ws.rs.NotFoundException;

@Service
public class PlatformService {

    // private static final String VACATION_MANAGEMENT_GETVACATIONPOLICYBYEMPLOYEE_QUERY_PATH = "/t/senior.com.br/bridge/1.0/rest/hcm/vacationmanagement/queries/getVacationPolicyByEmployee";
    // private static final String VACATION_MANAGEMENT_ENTITIES_PATH = "/t/senior.com.br/bridge/1.0/rest/hcm/vacationmanagement/entities/%s/%s";
    private static final String HCM_API_URL = "https://hcm-api.senior.com.br/frontend-api/";
    private static final String LOGIN_WITH_KEY_PATH = "/t/senior.com.br/bridge/1.0/anonymous/rest/platform/authentication/actions/loginWithKey";
    private static final String BEARER_TOKEN = "Bearer %s";

    //@Value("${PLATFORM_URL}")
    private String PLATFORM_URL = "https://platform.senior.com.br";

    //@Value("${TENANT}")
    private String TENANT = "larhom";

    //@Value("${ACCESS_KEY}")
    private String ACCESS_KEY = "8LyLHdZJMhTJHR713_Z2Fl1SfV4a";

    //@Value("${SECRET}")
    private String SECRET = "oiqz3Bv_RXKAWTNm4Qzj63tNdE8a";

    private Optional<JsonToken> token = Optional.empty();

    private WebTarget getClientPlatform() {
        Client client = ClientBuilder.newClient();
        return client.target(PLATFORM_URL);
    }

    private WebTarget getClientHcm() {
        Client client = ClientBuilder.newClient();
        return client.target(HCM_API_URL);
    }

    public <T> T getEntity(String entity, String id, Class<T> tClass, String token) {
        //login();

        WebTarget target = getClientHcm();
        String path = entity.concat(id);        
        Invocation.Builder builder = target.path(path)
                .request(MediaType.APPLICATION_JSON)
                .header(HttpHeaders.AUTHORIZATION, String.format(BEARER_TOKEN, token));
        try {
            System.out.println("Tentando realizar a conexão com o HCM: " + path);
            return builder.get(tClass);
        } catch (NotFoundException e) {
            System.out.println("Erro ao realizar o retorno da entidade: " + e.getMessage());
        }

        return null;
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

            WebTarget target = getClientPlatform();

            Invocation.Builder builder = target.path(LOGIN_WITH_KEY_PATH).request(MediaType.APPLICATION_JSON);
            System.out.println("Realizando autenticação...");
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
