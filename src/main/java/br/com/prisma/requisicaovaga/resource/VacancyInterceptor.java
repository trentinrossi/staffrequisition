package br.com.prisma.requisicaovaga.resource;

import br.com.prisma.requisicaovaga.model.StaffRequisition;
import br.com.prisma.requisicaovaga.model.StaffRequisitionReason;
import br.com.prisma.requisicaovaga.service.StaffRequisitionService;
import java.util.HashMap;
import java.util.Map;
import javax.inject.Inject;
import javax.ws.rs.core.Request;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@RestController
@RequestMapping("/")
public class VacancyInterceptor {

    @Inject
    StaffRequisitionService service;

    @RequestMapping("/")
    public String index() {
        return "O aplicativo de customização das requisições de vagas está online!";
    }

    @PostMapping(path = "/createStaffRequisition")
    public ResponseEntity<?> post(@RequestBody HashMap<String, String> request,
            @RequestHeader Map<String, String> headers,
            @RequestHeader(value = "x-senior-token", required = false) String token) {

        System.out.println("Nova requisição: " + request);

        StaffRequisition req = new StaffRequisition();
        req.setReason(StaffRequisitionReason.valueOf(request.get("reason")));
        req.setReplacedEmployeeId(request.get("replacedEmployeeId"));
        req.setActiveEmployeeId(request.get("activeEmployeeId"));
        
        System.out.println(req);
        
//        headers.forEach((key, value) -> {
//            System.out.println(String.format("Header '%s' = %s", key, value));
//        });
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
