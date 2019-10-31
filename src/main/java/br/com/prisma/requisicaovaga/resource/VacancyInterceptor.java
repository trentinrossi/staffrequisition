package br.com.prisma.requisicaovaga.resource;

import br.com.prisma.requisicaovaga.model.VacancyInput;
import br.com.prisma.requisicaovaga.model.VacancyOutput;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import javax.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@RestController
@RequestMapping("/")
public class VacancyInterceptor {

    @PostMapping(path = "/staffRequisition")
    public ResponseEntity<?> post(@RequestBody VacancyInput request) {         
        return ResponseEntity.badRequest().body("ErroRodrigo");
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "Error message")
    public Object handleError(HttpServletRequest req, Exception ex) {
        Object errorObject = new Object();
        return errorObject;
    }

}
