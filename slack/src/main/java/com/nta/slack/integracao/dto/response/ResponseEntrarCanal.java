package com.nta.slack.integracao.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.nta.slack.entidades.slack.Channel;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class ResponseEntrarCanal extends ResponseAbstract {

    private Channel channel;

}
