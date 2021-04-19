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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
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
    public Event createEvent(CreateEvent createEvent) {


        Event event = Event.builder()
                .channels(createEvent.getChannelList())
                .name(createEvent.getName())
                .build();


        Event save = this.eventRepository.save(event);

        return save;
    }

    @Override
    public List<Event> getAllEvent() {
        return this.eventRepository.findAll();
    }

    @Override
    public boolean publishMessage(PublishMessage publishMessage, int eventId) {
        Event event = this.eventRepository.findById((long) eventId).orElse(null);

        if(event == null){
            //do something
            //Analizar oq pode ser feito
            return false;
        }

        for(Channel channel : event.getChannels()){

            if(!channel.is_member()){
                if(channel.is_private()){
                    //do something
                    //Analizar o que pode ser feito
                    return false;
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

            Message.StatusMessage statusMessage = Message.StatusMessage.enviada;

            if(!responseEnviarMensagem.isOk()){
                statusMessage = Message.StatusMessage.erro;
            }

            Message message = Message.builder()
                    .message(publishMessage.getMessage())
                    .event(event)
                    .date(LocalDateTime.now())
                    .statusMessage(statusMessage)
                    .build();

            this.messageRepository.save(message);


        }


        return true;
    }
}
