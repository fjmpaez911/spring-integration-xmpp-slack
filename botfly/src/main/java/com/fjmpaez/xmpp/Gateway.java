package com.fjmpaez.xmpp;

import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.integration.xmpp.XmppHeaders;

public interface Gateway {

    void send(@Payload Message<String> msg, @Header(XmppHeaders.TO) String to);

}
