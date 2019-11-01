package br.com.prisma.requisicaovaga.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class LoginWithKeyOutput {
    public String jsonToken;
}
