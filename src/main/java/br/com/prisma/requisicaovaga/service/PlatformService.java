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

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import javax.ws.rs.NotFoundException;
import org.springframework.beans.factory.annotation.Value;

@Service
public class PlatformService {

    public static final String HCM_API_URL = "https://hcm-api.senior.com.br/frontend-api/";
    public static final String PLATFORM_API_URL = "https://platform.senior.com.br/t/senior.com.br/bridge/1.0/rest/";
    private static final String LOGIN_WITH_KEY_PATH = "/t/senior.com.br/bridge/1.0/anonymous/rest/platform/authentication/actions/loginWithKey";

    @Value("${PLATFORM_URL}")
    private String PLATFORM_URL;

    @Value("${TENANT}")
    private String TENANT;

    @Value("${ACCESS_KEY}")
    private String ACCESS_KEY;

    @Value("${SECRET}")
    private String SECRET;

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
                .header(HttpHeaders.AUTHORIZATION, token);  
        try {
            System.out.println("Tentando realizar a conexão com o HCM: " + target.getUri() + path);
            return builder.get(tClass);            
        } catch (NotFoundException e) {
            System.out.println("Erro ao realizar o retorno da entidade: " + e.getMessage());
        } catch (Exception ex) {
            System.out.println("Erro: "+ex.getMessage());
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
