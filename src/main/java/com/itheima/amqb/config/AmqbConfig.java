package com.itheima.amqb.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class AmqbConfig {
    private final static String TOPIC_EXCHANGE = "topic_exchange";

    private final static String TOPIC_QUEUE = "topic.queue";

    @Bean(name = "topicQueue")
    public Queue phoneMessage() {
        return new Queue( TOPIC_QUEUE, true );
    }

    @Bean(name = "topicExchange")
    public TopicExchange exchange() {

        return new TopicExchange( TOPIC_EXCHANGE );
    }

    @Bean
    public Binding bindingExchange(@Qualifier("topicQueue") Queue topicQueue, @Qualifier("topicExchange") TopicExchange topicExchange) {
        return BindingBuilder.bind( topicQueue ).to( topicExchange ).with( "topic.#" );
    }

}
