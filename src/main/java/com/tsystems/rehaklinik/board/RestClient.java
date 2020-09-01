package com.tsystems.rehaklinik.board;

import com.tsystems.rehaklinik.board.data.TreatmentEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.ManagedBean;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import java.io.*;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

@ManagedBean
public class RestClient implements Serializable {

    private transient Logger logger = LoggerFactory.getLogger(RestClient.class);

    public List<TreatmentEvent> getTodayTreatmentEvents() {
        logger.info("NURSE BOARD: RestClient: getting today treatment events");

        Properties properties = new Properties();

        try (InputStream input = getClass().getClassLoader().getResourceAsStream("rest.properties")) {

            properties.load(input);
            Client client = ClientBuilder.newClient();
            List<TreatmentEvent> treatmentEvents = client
                    .target(properties.getProperty("rest.endpoint"))
                    .request(MediaType.APPLICATION_JSON)
                    .get(new GenericType<List<TreatmentEvent>>() {
                    });
            client.close();

            logger.info("NURSE BOARD: RestClient: data received");

            return treatmentEvents;

        } catch (FileNotFoundException exception) {
            logger.error("NURSE BOARD: RestClient: could not find properties file", exception.fillInStackTrace());
        } catch (IOException exception) {
            logger.error("NURSE BOARD: RestClient: IOException thrown", exception.fillInStackTrace());
        } catch (Exception exception) {
            logger.error("NURSE BOARD: RestClient: could not connect to remote server");
        }
        return Collections.emptyList();
    }
}