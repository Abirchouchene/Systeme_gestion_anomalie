package tn.itbs.surveillance.config;


import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    public static final String ANOMALIE_QUEUE = "anomalie.queue";
    public static final String ANOMALIE_EXCHANGE = "anomalie.exchange";
    public static final String ANOMALIE_ROUTING_KEY = "anomalie.routing.key";

    @Bean
    public Queue anomalieQueue() {
        return new Queue(ANOMALIE_QUEUE, true);
    }

    @Bean
    public TopicExchange anomalieExchange() {
        return new TopicExchange(ANOMALIE_EXCHANGE);
    }

    @Bean
    public Binding binding(Queue anomalieQueue, TopicExchange anomalieExchange) {
        return BindingBuilder
                .bind(anomalieQueue)
                .to(anomalieExchange)
                .with(ANOMALIE_ROUTING_KEY);
    }

    @Bean
    public Jackson2JsonMessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(messageConverter());
        return rabbitTemplate;
    }
}