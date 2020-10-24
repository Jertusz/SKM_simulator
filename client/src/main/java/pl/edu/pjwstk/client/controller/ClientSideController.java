package pl.edu.pjwstk.client.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


@RestController
public class ClientSideController {

    private final String trainsList = "http://127.0.0.1:9000/trains";
    private final String trainDetail = "http://simulator/9000/trains/";

    @GetMapping("/trains")
    public ResponseEntity getTrains() {
        RestTemplate template = new RestTemplate();
        return template.getForEntity(trainsList, String.class);
    }
}
