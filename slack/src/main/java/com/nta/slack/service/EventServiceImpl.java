package com.nta.slack.service;

import com.nta.slack.constants.OAuthConstants;
import com.nta.slack.constants.SlackConstants;
import com.nta.slack.database.model.Channel;
import com.nta.slack.database.model.Event;
import com.nta.slack.database.model.Message;
import com.nta.slack.database.repository.ChannelRepository;
import com.nta.slack.database.repository.EventRepository;
import com.nta.slack.database.repository.MessageRepository;
import com.nta.slack.integracao.dto.request.RequestEntrarCanal;
import com.nta.slack.integracao.dto.request.RequestEnviarMensagem;
import com.nta.slack.integracao.dto.response.ResponseEntrarCanal;
import com.nta.slack.integracao.dto.response.ResponseEnviarMensagem;
import com.nta.slack.integracao.feign.IntegracaoSlackImpl;
import com.nta.slack.web.dto.request.CreateEvent;
import com.nta.slack.web.dto.request.PublishMessage;
import com.nta.slack.web.dto.response.ApiResponse;
import com.nta.slack.web.dto.response.ApiResponseImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class EventServiceImpl implements EventService {


    @Autowired
    private OAuthSlackService oAuthSlackService;

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private IntegracaoSlackImpl integracaoSlack;


    @Override
    public ApiResponse createEvent(CreateEvent createEvent) {


        ApiResponse response;

        Event event = Event.builder()
                .channels(createEvent.getChannelList())
                .name(createEvent.getName())
                .build();
        try{
            Event save = this.eventRepository.save(event);
            response = new ApiResponseImpl(HttpStatus.CREATED, true, save);

        }catch (Exception e){
            log.info("Error on create Event: "+ e.getMessage());
            response = new ApiResponseImpl(HttpStatus.BAD_REQUEST, false, e.getMessage());
        }

        return response;

    }

    @Override
    public ApiResponse getAllEvent() {
        ApiResponse apiResponse = new ApiResponseImpl(HttpStatus.OK, true, this.eventRepository.findAll());
        return apiResponse;
    }

    @Override
    public ApiResponse publishMessage(PublishMessage publishMessage, int eventId) {

        Event event = this.eventRepository.findById((long) eventId).orElse(null);
        ApiResponse apiResponse = new ApiResponseImpl();

        if(event == null){
            apiResponse = new ApiResponseImpl(HttpStatus.BAD_REQUEST, false,"Event dont exist");
            return apiResponse;
        }

        for(Channel channel : event.getChannels()){

            if(!channel.is_member()){
                if(channel.is_private()){
                    apiResponse = new ApiResponseImpl(HttpStatus.BAD_REQUEST,
                            false,
                            "The channel with id "+channel.getId()+" is private and our bot is not a member. Please change the privacity and send the request again.");
                    return apiResponse;
                }
                ResponseEntrarCanal responseEntrarCanal = this.integracaoSlack.entrarCanal(
                        OAuthConstants.OAUTH_AUTHORIZATION_TYPE + channel.getTeam().getAccessToken(),
                        new RequestEntrarCanal(channel.getId())
                );
            }

            RequestEnviarMensagem requestEnviarMensagem = new RequestEnviarMensagem(
                    channel.getId(),
                    publishMessage.getMessage());

            ResponseEnviarMensagem responseEnviarMensagem = integracaoSlack.enviarMensagem(
                    OAuthConstants.OAUTH_AUTHORIZATION_TYPE + channel.getTeam().getAccessToken(),
                    requestEnviarMensagem);

            Message message = Message.builder()
                    .message(publishMessage.getMessage())
                    .event(event)
                    .date(LocalDateTime.now())
                    .statusMessage(responseEnviarMensagem.isOk() ? Message.StatusMessage.enviada : Message.StatusMessage.erro)
                    .build();

            Message save = this.messageRepository.save(message);

            apiResponse = new ApiResponseImpl(HttpStatus.OK, true, save);
        }
        return apiResponse;

    }
}
