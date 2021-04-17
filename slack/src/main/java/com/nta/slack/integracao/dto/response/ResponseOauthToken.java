package com.nta.slack.integracao.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.nta.slack.entidades.slack.Team;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class ResponseOauthToken extends ResponseAbstract{

    private String access_token;
    private String token_type;
    private Team team;
    private String enterprise;
    private boolean is_enterprise_install;


}
