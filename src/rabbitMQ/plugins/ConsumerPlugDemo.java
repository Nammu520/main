package rabbitMQ.plugins;

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
 * ConsumerPlugDemo
 *
 * @author dengyu
 * @date 2020/3/18
 * @since 1.0.0
 */
public class ConsumerPlugDemo {

    private static String QUEUE_NAME = "coupon_delay";

    public static void main(String[] args) throws IOException {
        ConnectionFactory connectionFactory = RabbitConfig.INSTANCE.connectionFactory();
        Connection connection = connectionFactory.newConnection();
        Channel channel = connection.createChannel();

        channel.queueDeclare(QUEUE_NAME, false, false, false, null);

        System.out.println("Customer Waiting Received Message");

        Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String message = new String(body, "UTF-8");
                System.out.println("Customer Received '" + message + "'");
            }
        };
        channel.basicConsume(QUEUE_NAME, true, consumer);
    }
}
