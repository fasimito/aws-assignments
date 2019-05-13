package com.aws.config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.context.WebServerInitializedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.net.InetAddress;
import java.net.UnknownHostException;

@Component
public class ServerConfig  implements ApplicationListener<WebServerInitializedEvent> {
    @Value("${server.ip}")
    private String ip;
    private int serverPort;
    public String getUrl() {
        if(ip!=null){
            return "http://"+ip +":"+this.serverPort;
        }else{
            InetAddress address = null;
            try {
                address = InetAddress.getLocalHost();
            } catch (UnknownHostException e) {
                e.printStackTrace();
            }
            return "http://"+address.getHostAddress() +":"+this.serverPort;
        }
    }

    @Override
    public void onApplicationEvent(WebServerInitializedEvent event) {
        this.serverPort = event.getWebServer().getPort();
    }

}