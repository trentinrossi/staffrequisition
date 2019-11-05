package br.com.prisma.requisicaovaga;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RequisicaovagaApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(RequisicaovagaApplication.class);

    public static void main(String[] args) {
        LOGGER.debug("Iniciando aplicação...");
        SpringApplication.run(RequisicaovagaApplication.class, args);
        LOGGER.debug("-- Aplicação iniciada --");
    }
}
