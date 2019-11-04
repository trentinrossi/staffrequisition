package br.com.prisma.requisicaovaga.resource;

import br.com.prisma.requisicaovaga.model.StaffRequisition;
import br.com.prisma.requisicaovaga.service.StaffRequisitionService;
import java.util.Map;
import javax.inject.Inject;
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
    public ResponseEntity<?> post(@RequestBody StaffRequisition request,
            @RequestHeader Map<String, String> headers,
            @RequestHeader(value = "x-senior-token", required = false) String token) {

        System.out.println("Nova requisição: " + request);

//        headers.forEach((key, value) -> {
//            System.out.println(String.format("Header '%s' = %s", key, value));
//        });
        service.validateStaffRequisition(request, token);

        return ResponseEntity.badRequest().body("ErroRodrigo");
    }
}
