package rabbitMQ;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;
import rabbitMQ.config.RabbitConfig;

import java.io.IOException;

/**
 * ConsumerDemo
 *
 * @author dengyu
 * @date 2020/3/16
 * @since 1.0.0
 */
public class DelayConsumerDemo {

    private static String DEAD_LETTER_QUEUE = "dead_letter_coupon";

    private static String DEAD_LETTER_EXCHANGE = "dead_letter_gaia";

    private static String DEAD_LETTER_ROUTING_KEY = "dead_letter_coupon";

    public static void main(String[] args) throws IOException {
        ConnectionFactory connectionFactory = RabbitConfig.INSTANCE.connectionFactory();
        Connection connection = connectionFactory.newConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(DEAD_LETTER_QUEUE, false, false, false, null);
        channel.queueBind(DEAD_LETTER_QUEUE, DEAD_LETTER_EXCHANGE, DEAD_LETTER_ROUTING_KEY);
        System.out.println("Customer Waiting Received Message");

        Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String message = new String(body, "UTF-8");
                System.out.println("Customer Received '" + message + "'");
            }
        };
        channel.basicConsume(DEAD_LETTER_QUEUE, true, consumer);
    }
}
