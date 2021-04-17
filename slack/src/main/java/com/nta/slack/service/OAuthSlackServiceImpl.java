package com.nta.slack.service;

import com.nta.slack.constants.OAuthConstants;
import com.nta.slack.database.model.Channel;
import com.nta.slack.database.model.Team;
import com.nta.slack.database.repository.ChannelRepository;
import com.nta.slack.database.repository.TeamRepository;
import com.nta.slack.integracao.dto.request.RequestEntrarTime;
import com.nta.slack.integracao.dto.response.ResponseListaDeCanais;
import com.nta.slack.integracao.dto.response.ResponseOauthToken;
import com.nta.slack.integracao.feign.IntegracaoSlackImpl;
import com.nta.slack.constants.SlackConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
@Slf4j
public class OAuthSlackServiceImpl implements OAuthSlackService {

    @Autowired
    private IntegracaoSlackImpl integracaoSlackImpl;

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private ChannelRepository channelRepository;

    private String client_id = SlackConstants.CLIENT_ID;

    private String client_secret = SlackConstants.CLIENT_SECRET;


    @Override
    public void entrarTime(String code) {

        RequestEntrarTime requestEntrarTime = new RequestEntrarTime(code,client_id,client_secret);
        ResponseOauthToken responseOauthToken = this.integracaoSlackImpl.entrarNoTime(requestEntrarTime);

        if(!responseOauthToken.isOk()){
            log.info("Falha na autenticação: ", responseOauthToken.getError());
            return;
        }

        Team team = Team.builder()
                .accessToken(responseOauthToken.getAccess_token())
                .id(responseOauthToken.getTeam().getId())
                .tokenType(responseOauthToken.getToken_type())
                .name(responseOauthToken.getTeam().getName())
                .channels(null)
                .build();

        Team teamSaved = this.teamRepository.save(team);

        ResponseListaDeCanais responseListaDeCanais = this.integracaoSlackImpl.buscarChannels(OAuthConstants.OAUTH_AUTHORIZATION_TYPE+teamSaved.getAccessToken());
        if(responseListaDeCanais.isOk()){
            for(com.nta.slack.entidades.slack.Channel channel : responseListaDeCanais.getChannels()){
                Channel newChannel = Channel.builder()
                        .name(channel.getName())
                        .id(channel.getId())
                        .is_channel(channel.is_channel())
                        .is_group(channel.is_group())
                        .is_member(channel.is_member())
                        .is_private(channel.is_private())
                        .team(teamSaved)
                        .build();

                Channel save = channelRepository.save(newChannel);
            }
        }
        log.info("Times salvos");
    }
}
