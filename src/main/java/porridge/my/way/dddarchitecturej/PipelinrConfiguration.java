package porridge.my.way.dddarchitecturej;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import an.awesome.pipelinr.Command;
import an.awesome.pipelinr.Notification;
import an.awesome.pipelinr.Pipeline;
import an.awesome.pipelinr.Pipelinr;

@Configuration
class PipelinrConfiguration {

    @Bean
    Pipeline pipeline(ObjectProvider<Command.Handler> commandHandlers,
            ObjectProvider<Notification.Handler> notificationHandlers) {
        return new Pipelinr()
                .with(commandHandlers::stream)
                .with(notificationHandlers::stream);
    }
}