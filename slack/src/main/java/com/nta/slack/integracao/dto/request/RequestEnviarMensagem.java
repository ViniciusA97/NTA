package com.nta.slack.integracao.dto.request;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RequestEnviarMensagem {

    private String channel;
    private String text;

}
