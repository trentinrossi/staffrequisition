package br.com.prisma.requisicaovaga.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.time.LocalDateTime;

@JsonIgnoreProperties(ignoreUnknown = true)
public class JsonToken {

    private String accessToken;
    private LocalDateTime expiresDate;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccess_token(String access_token) {
        this.accessToken = access_token;
    }

    public boolean isExpired() {
        return this.expiresDate == null || this.expiresDate.isAfter(LocalDateTime.now());
    }

    public void setExpires_in(int expires_in) {
        this.expiresDate = LocalDateTime.now().plusSeconds(expires_in);
    }
}
