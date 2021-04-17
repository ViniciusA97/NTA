package com.nta.slack.web.resource;

import com.nta.slack.constants.OAuthConstants;
import com.nta.slack.integracao.dto.response.ResponseEntrarCanal;
import com.nta.slack.integracao.dto.response.ResponseListaDeCanais;
import com.nta.slack.integracao.feign.IntegracaoSlackImpl;
import com.nta.slack.service.OAuthSlackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/slack")
public class SlackResource {

    @Autowired
    private IntegracaoSlackImpl integracaoSlackImpl;

    @Autowired
    private OAuthSlackService oAuthSlackService;

    @Value("${slack.oauth-token.bot-token}")
    private String botToken;


    @GetMapping("/channels")
    public ResponseEntity<?> atualizarChannels(@RequestBody ResponseEntrarCanal canal){
        ResponseListaDeCanais responseListaDeCanais = this.integracaoSlackImpl.buscarChannels(OAuthConstants.OAUTH_AUTHORIZATION_TYPE + botToken);
        return ResponseEntity.ok(responseListaDeCanais);
    }

    @GetMapping(path="/oauth")
    public ResponseEntity<?> returnOauth(@RequestParam(required = false) String code){
        this.oAuthSlackService.entrarTime(code);
        return ResponseEntity.ok(null);
    }



}
