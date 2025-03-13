package com.example.flamingoui.client;

import com.example.flamingoui.client.dto.GameStateResponse;
import com.example.flamingoui.domain.GameStateData;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import static com.example.flamingoui.client.ClientConfiguration.SESSION_SERVICE_URL;

@Component
public class SessionClient {
    private final RestTemplate restTemplate = new RestTemplate();
    public String getSession(){
        String url = SESSION_SERVICE_URL+"/sessions";
        return restTemplate.postForEntity(url, null, String.class).getBody();
    }
    public Void simulateGame(String sessionId){
        String url = SESSION_SERVICE_URL+"/sessions/"+sessionId+"/simulate";
        return restTemplate.postForEntity(url, null, Void.class).getBody();
    }
    public GameStateData getGameState(String sessionId){
        String url = SESSION_SERVICE_URL+"/sessions/"+sessionId;
        return restTemplate.getForObject(url, GameStateResponse.class).data();

    }
}
