package com.tsystems.rehaklinik.board;

import com.tsystems.rehaklinik.board.data.TreatmentEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.ManagedBean;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import java.io.Serializable;
import java.util.List;

@ManagedBean
public class RestClient implements Serializable {

    private Logger logger = LoggerFactory.getLogger(RestClient.class);

    private static final String REST_ENDPOINT = "http://localhost:8081/rehaklinik/api/today-treatment-events";

    public List<TreatmentEvent> getTodayTreatmentEvents() {
        logger.info("NURSE BOARD: RestClient: getting today treatment events");
        Client client = ClientBuilder.newClient();
        List<TreatmentEvent> treatmentEvents = client
                .target(REST_ENDPOINT)
                .request(MediaType.APPLICATION_JSON)
                .get(new GenericType<List<TreatmentEvent>>() {
                });
        client.close();
        logger.info("NURSE BOARD: RestClient: data received");
        return treatmentEvents;
    }
}