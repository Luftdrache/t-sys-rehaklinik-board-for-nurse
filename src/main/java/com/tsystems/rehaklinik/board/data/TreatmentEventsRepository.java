package com.tsystems.rehaklinik.board.data;

import com.tsystems.rehaklinik.board.RestClient;
import lombok.Getter;
import lombok.Setter;

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

    @Inject
    private RestClient restClient;

    @PostConstruct
    public void getTreatmentEventsOnStart() {
        treatmentEventList = restClient.getTodayTreatmentEvents();
    }
}
