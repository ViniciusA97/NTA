package com.nta.slack.integracao.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.nta.slack.entidades.slack.Message;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class ResponseEnviarMensagem  extends ResponseAbstract{

    private String channel;
    private Message message;

}
