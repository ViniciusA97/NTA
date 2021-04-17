package com.nta.slack.integracao.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.nta.slack.entidades.slack.Channel;
import lombok.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class ResponseListaDeCanais  extends ResponseAbstract{

    private List<Channel> channels;



}
