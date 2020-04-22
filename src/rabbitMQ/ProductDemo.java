package rabbitMQ;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import rabbitMQ.config.RabbitConfig;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * SendDemo
 *
 * @author dengyu
 * @date 2020/3/16
 * @since 1.0.0
 */
public class ProductDemo {

    private static String QUEUE_NAME = "coupon";

    private static String EXCHANGE_NAME = "gaia";

    private static String ROUTING_KEY = "coupon";

    private static String DEAD_LETTER_QUEUE = "dead_letter_coupon";

    private static String DEAD_LETTER_EXCHANGE = "dead_letter_gaia";

    private static String DEAD_LETTER_ROUTING_KEY = "dead_letter_coupon";

    public static void main(String[] args) throws IOException {
        ConnectionFactory connectionFactory = RabbitConfig.INSTANCE.connectionFactory();
        Connection connection = connectionFactory.newConnection();
        Channel channel = connection.createChannel();
        // 声明一个队列
        Map<String, Object> properties = new HashMap<>();
        properties.put("x-dead-letter-exchange", "dead_letter_gaia");
        properties.put("x-dead-letter-routing-key", "dead_letter_coupon");
        properties.put("x-message-ttl", 6000);
        channel.queueDeclare(QUEUE_NAME, false, false, false, properties);
        channel.queueDeclare(DEAD_LETTER_QUEUE, false, false, false, null);
        // 声明一个直连型exchange
        channel.exchangeDeclare(EXCHANGE_NAME, "direct");
        channel.exchangeDeclare(DEAD_LETTER_EXCHANGE, "direct");
        channel.queueBind(QUEUE_NAME, EXCHANGE_NAME, ROUTING_KEY);
        channel.queueBind(DEAD_LETTER_QUEUE, DEAD_LETTER_EXCHANGE, DEAD_LETTER_ROUTING_KEY);
        for(int i = 0; i < 3; i++){
            channel.basicPublish(EXCHANGE_NAME, ROUTING_KEY, null, "test".getBytes("UTF-8"));
        }
        System.out.println("end");
    }
}
