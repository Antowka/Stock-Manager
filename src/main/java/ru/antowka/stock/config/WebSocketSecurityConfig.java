package ru.antowka.stock.config;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.security.config.annotation.web.messaging.MessageSecurityMetadataSourceRegistry;
import org.springframework.security.config.annotation.web.socket.AbstractSecurityWebSocketMessageBrokerConfigurer;

/**
 * Created by Anton Nikanorov on 21.10.15.
 */
@Configurable
public class WebSocketSecurityConfig extends AbstractSecurityWebSocketMessageBrokerConfigurer {

    protected void configureInbound(MessageSecurityMetadataSourceRegistry messages) {
        messages.simpSubscribeDestMatchers("/ws_portfolio").authenticated();
    }

    @Override
    protected boolean sameOriginDisabled() {
        return true;
    }
}
