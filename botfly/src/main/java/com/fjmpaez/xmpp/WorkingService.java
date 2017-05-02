package com.fjmpaez.xmpp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.xmpp.XmppHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;


@Service
public class WorkingService {

    private static Logger logger = LoggerFactory.getLogger(WorkingService.class);
    
    @Autowired
    private Gateway gateway;
    

    public void processMessage(String msg, @Header(XmppHeaders.FROM) String from) {

        logger.info("Message received: {}", msg);
        
        sendToSlack(msg, from);

    }
    
    public void discardMessages(String msg, @Header(XmppHeaders.FROM) String from) {

        logger.info("Message received: {}", msg);

    }
    
    public void sendToSlack(String msg, String to) {
    	
    	logger.info("ECO Reply");

        Message<String> message = MessageBuilder.withPayload("ECO " + msg).build();

        gateway.send(message, to);

    }

}
