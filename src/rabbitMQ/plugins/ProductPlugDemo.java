package rabbitMQ.plugins;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import rabbitMQ.config.RabbitConfig;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * ProductPlugDemo
 *
 * @author dengyu
 * @date 2020/3/18
 * @since 1.0.0
 */
public class ProductPlugDemo {

    private static String QUEUE_NAME = "coupon_delay";

    private static String EXCHANGE_NAME = "gaia_delay";

    private static String ROUTING_KEY = "coupon_delay";

    public static void main(String[] args) throws IOException {
        ConnectionFactory connectionFactory = RabbitConfig.INSTANCE.connectionFactory();
        Connection connection = connectionFactory.newConnection();
        Channel channel = connection.createChannel();
        // 声明一个队列
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        // 声明一个直连型exchange
        HashMap<String, Object> pros = new HashMap<>();
        pros.put("x-delayed-type", "direct");
        channel.exchangeDeclare(EXCHANGE_NAME, "x-delayed-message", true, false, pros);
        channel.queueBind(QUEUE_NAME, EXCHANGE_NAME, ROUTING_KEY);
        for(int i = 0; i < 3; i++){
            Map<String, Object> headers = new HashMap<>();
            headers.put("x-delay", 12000);
            AMQP.BasicProperties basicProperties = new AMQP.BasicProperties.Builder().headers(headers).build();
            channel.basicPublish(EXCHANGE_NAME, ROUTING_KEY, basicProperties, "test".getBytes("UTF-8"));
        }
        System.out.println("end");
    }
}
