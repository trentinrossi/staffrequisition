package br.com.prisma.requisicaovaga.resource;

import br.com.prisma.requisicaovaga.model.StaffRequisition;
import br.com.prisma.requisicaovaga.model.StaffRequisitionReason;
import br.com.prisma.requisicaovaga.service.NotificationService;
import br.com.prisma.requisicaovaga.service.StaffRequisitionService;
import java.util.HashMap;
import java.util.Map;
import javax.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@RestController
@RequestMapping("/")
public class VacancyInterceptor {

    private static final Logger LOGGER = LoggerFactory.getLogger(VacancyInterceptor.class);

    @Inject
    StaffRequisitionService service;
    
    @Inject
    NotificationService notificationService;

    @RequestMapping("/")
    public String index() {
        LOGGER.debug("Chamou REST /");
        return "O aplicativo de customização das requisições de vagas está online!";
    }

    @PostMapping(path = "/createStaffRequisition")
    public ResponseEntity<?> post(@RequestBody HashMap<String, String> request,
            @RequestHeader Map<String, String> headers,
            @RequestHeader(value = "x-senior-token", required = false) String token) {

        LOGGER.debug("Nova Requisição: " + request);

        StaffRequisition req = new StaffRequisition();
        req.setReason(StaffRequisitionReason.valueOf(request.get("reason")));
        req.setReplacedEmployeeId(request.get("replacedEmployeeId"));
        req.setActiveEmployeeId(request.get("activeEmployeeId"));

        LOGGER.trace("StaffRequisition serializado:" + req);

        headers.forEach((key, value) -> {
            LOGGER.trace(String.format("Header '%s' = %s", key, value));
        });

        try {
            if (service.isPresentInOthersRequisitions(req, token)) {                
                return ResponseEntity.badRequest().body("Colaborador já informado em outras requisições/vagas");
            } else {
                return ResponseEntity.ok().body(request);
            }
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }
}
