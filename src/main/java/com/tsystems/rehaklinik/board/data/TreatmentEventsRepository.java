package com.tsystems.rehaklinik.board.data;

import com.tsystems.rehaklinik.board.RestClient;
import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;


@Setter
@Getter
@ApplicationScoped
@Named
public class TreatmentEventsRepository {

    private List<TreatmentEvent> treatmentEventList = new ArrayList<>();

    private Logger logger = LoggerFactory.getLogger(TreatmentEventsRepository.class);

    @Inject
    private RestClient restClient;

    @PostConstruct
    public void getTreatmentEventsOnStart() {
        logger.info("NURSE BOARD: TreatmentEventsRepository: getting today treatment events at start");
        treatmentEventList = restClient.getTodayTreatmentEvents();
    }
}
