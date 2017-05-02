package com.fjmpaez.config;

import java.io.IOException;

import org.jivesoftware.smack.ConnectionConfiguration;
import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.tcp.XMPPTCPConnection;
import org.jivesoftware.smack.tcp.XMPPTCPConnectionConfiguration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CustomConnectionConfiguration {

    @Value("${xmpp.slack.host}")
    private String host;

    @Value("${xmpp.slack.user}")
    private String user;

    @Value("${xmpp.slack.pass}")
    private String password;

    @Bean("xmppConnection")
    public XMPPTCPConnection xmppConnection() {
        XMPPTCPConnectionConfiguration config = XMPPTCPConnectionConfiguration.builder()
                .setServiceName(host).setSecurityMode(ConnectionConfiguration.SecurityMode.required)
                .setDebuggerEnabled(true).build();

        XMPPTCPConnection mConnection = new XMPPTCPConnection(config);

        try {
            System.out.println("connecting");
            mConnection.setPacketReplyTimeout(10000);
            mConnection.connect();
            System.out.println("connected");
            mConnection.login(user, password);

        } catch (SmackException | IOException | XMPPException e) {
            e.printStackTrace();
        }
        return mConnection;
    }
}