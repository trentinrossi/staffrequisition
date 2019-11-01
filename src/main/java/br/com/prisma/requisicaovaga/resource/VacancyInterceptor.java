package br.com.prisma.requisicaovaga.resource;

import br.com.prisma.requisicaovaga.model.StaffRequisition;
import br.com.prisma.requisicaovaga.service.StaffRequisitionService;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@RestController
@RequestMapping("/")
public class VacancyInterceptor {

    @Autowired
    StaffRequisitionService service;

    @RequestMapping("/")
    public String index() {
        return "O aplicativo de customização das requisições de vagas está online!";
    }

    @PostMapping(path = "/createStaffRequisition")
    public ResponseEntity<?> post(@RequestBody StaffRequisition request) {
        System.out.println("Nova requisição: " + request);

        service.validateStaffRequisition(request);

        return ResponseEntity.badRequest().body("ErroRodrigo");
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "Error message")
    public Object handleError(HttpServletRequest req, Exception ex) {
        Object errorObject = new Object();
        return errorObject;
    }

}
