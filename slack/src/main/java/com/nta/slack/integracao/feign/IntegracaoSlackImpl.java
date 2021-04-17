package com.nta.slack.integracao.feign;

import com.nta.slack.integracao.config.IntegracaoFeignConfig;
import com.nta.slack.integracao.dto.request.RequestEntrarCanal;
import com.nta.slack.integracao.dto.request.RequestEntrarTime;
import com.nta.slack.integracao.dto.request.RequestEnviarMensagem;
import com.nta.slack.integracao.dto.response.ResponseEntrarCanal;
import com.nta.slack.integracao.dto.response.ResponseEnviarMensagem;
import com.nta.slack.integracao.dto.response.ResponseListaDeCanais;
import com.nta.slack.integracao.dto.response.ResponseOauthToken;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;


@FeignClient(value="integracao-slack", url="https://slack.com/api", configuration = IntegracaoFeignConfig.class)
public interface IntegracaoSlackImpl{

    @RequestMapping(method = RequestMethod.GET, value="/conversations.list", produces = {MediaType.APPLICATION_JSON_VALUE})
    ResponseListaDeCanais buscarChannels(@RequestHeader("Authorization") String token);

    @RequestMapping(method = RequestMethod.POST, value="/conversations.join", produces = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    ResponseEntrarCanal entrarCanal(@RequestHeader("Authorization") String token, @RequestBody RequestEntrarCanal channel);

    @RequestMapping(method = RequestMethod.POST, value="/oauth.v2.access", consumes ={MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    ResponseOauthToken entrarNoTime(@SpringQueryMap RequestEntrarTime code);

    @RequestMapping(method = RequestMethod.POST, value="/chat.postMessage", produces = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    ResponseEnviarMensagem enviarMensagem(@RequestHeader("Authorization") String token, @RequestBody RequestEnviarMensagem mensagem);

    //Create Conversation


}
