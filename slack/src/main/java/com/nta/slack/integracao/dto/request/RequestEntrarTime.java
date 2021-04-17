package com.nta.slack.integracao.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RequestEntrarTime {

    private String code;
    private String client_id;
    private String client_secret;

    public Map<String, String> toMap(){
        Map<String,String> map = new HashMap<String, String>();
        map.put("code",code);
        map.put("client_id",client_id);
        map.put("client_secret",client_secret);
        return map;
    }

}
