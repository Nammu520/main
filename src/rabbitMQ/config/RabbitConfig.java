package rabbitMQ.config;

import com.rabbitmq.client.ConnectionFactory;

/**
 * RabbitConfig
 *
 * @author dengyu
 * @date 2020/3/16
 * @since 1.0.0
 */
public enum RabbitConfig {
    INSTANCE;

    public ConnectionFactory connectionFactory(){
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("47.93.90.156");
        connectionFactory.setPort(5672);
        connectionFactory.setUsername("guest");
        connectionFactory.setPassword("guest");
        connectionFactory.setVirtualHost("/");
        return connectionFactory;
    }
}
