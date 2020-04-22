package rocketmq;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;

/**
 * RocketProductDemo
 *
 * @author dengyu
 * @date 2020/3/20
 * @since 1.0.0
 */
public class RocketProductDemo {

    public static void main(String[] args) throws Exception {
        // 实例化producer，指定一个唯一的组名
        DefaultMQProducer producer = new DefaultMQProducer("unique_product_1");
        producer.setNamesrvAddr("47.93.90.156:9876");
        producer.start();
        for(int i = 0; i < 6; i++) {
            // topic,tag,content
            Message message = new Message("topic_1", "first", ("Hello RocketMQ" + i).getBytes(RemotingHelper.DEFAULT_CHARSET));
            SendResult sendResult = producer.send(message);
            System.out.println(sendResult);
        }
    }
}

