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
    public ResponseEntity<?> post(@RequestBody StaffRequisition request, @RequestHeader Map<String, String> headers, @RequestHeader(value = "x-senior-token", required = false) String token) {
        System.out.println("Nova requisição: " + request);
        System.out.println("Token: " + token);

        headers.forEach((key, value) -> {
            System.out.println(String.format("Header '%s' = %s", key, value));
        });

        service.validateStaffRequisition(request, token);

        return ResponseEntity.badRequest().body("ErroRodrigo");
    }
//    @PostMapping(path = "/createStaffRequisition")
//    public ResponseEntity<?> post(@RequestBody StaffRequisition request, @RequestHeader (name="Authorization") String authToken) {
//        System.out.println("Nova requisição: " + request);
//
//        service.validateStaffRequisition(request, authToken);
//
//        return ResponseEntity.badRequest().body("ErroRodrigo");
//    }

//    @ExceptionHandler(Exception.class)
//    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "Error message")
//    public Object handleError(HttpServletRequest req, Exception ex) {
//        Object errorObject = new Object();
//        return errorObject;
//    }
}
