package br.com.prisma.requisicaovaga.service;

import br.com.prisma.requisicaovaga.model.NotifyUserInput;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    private static final String SERVICE_URL = "platform/notifications/actions/notifyUser";
    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(NotificationService.class);
    private final Client client = ClientBuilder.newClient();

    public void notifyUser(NotifyUserInput notification, String token) {
        LOGGER.debug("Montando requisição para enviar a mensagem para o destinatário " + notification.getDestinationUser() + " URL: " + PlatformService.PLATFORM_API_URL + SERVICE_URL);

        Response response = client
                .target(PlatformService.PLATFORM_API_URL)
                .path(SERVICE_URL)
                .request(MediaType.APPLICATION_JSON)
                .header(HttpHeaders.AUTHORIZATION, token)
                .post(Entity.entity(notification, MediaType.APPLICATION_JSON));

        if (response.getStatus() == 200) {
            LOGGER.debug("Chamada executada com sucesso: 200-OK:" + response);
        } else {
            LOGGER.error("Erro ao enviar a mensagem para o destinatário:" + response.getStatus() + response.getStatusInfo().getReasonPhrase());
        }
    }
}
