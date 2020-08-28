package com.tsystems.rehaklinik.board;

import com.tsystems.rehaklinik.board.data.TreatmentEvent;
import com.tsystems.rehaklinik.board.data.TreatmentEventsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.faces.push.Push;
import javax.faces.push.PushContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import java.util.List;

@Named
@MessageDriven(name = "nurse-board-listener", activationConfig = {
        @ActivationConfigProperty(propertyName = "destinationLookup",
                propertyValue = "java:jboss/exported/todayTreatmentEventsQueue"),
        @ActivationConfigProperty(propertyName = "acknowledgeMode",
                propertyValue = "Auto-acknowledge")
})
public class NurseBoardMessageListener implements MessageListener {

    private Logger logger = LoggerFactory.getLogger(NurseBoardMessageListener.class);

    @Inject
    private RestClient restClient;

    @Inject
    private TreatmentEventsRepository treatmentEventsRepository;

    @Inject
    @Push(channel = "push")
    private PushContext push;

    @Override
    public void onMessage(Message message) {
        try {
            logger.info("NURSE BOARD: NurseBoardMessageListener --> {}", message.getBody(String.class));
            List<TreatmentEvent> treatmentEventList = restClient.getTodayTreatmentEvents();
            treatmentEventsRepository.setTreatmentEventList(treatmentEventList);
            logger.info("NURSE BOARD: NurseBoardMessageListener -->: repository updated");
            push.send("pushed");
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

    public void update() {
        logger.info("NURSE BOARD: NurseBoardMessageListener --> Ajax");
    }
}
