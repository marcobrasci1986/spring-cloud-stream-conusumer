package be.avidoo.sandbox.springcloudstreamconsumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * https://www.e4developer.com/2018/02/05/handling-bad-messages-with-rabbitmq-and-spring-cloud-stream/
 */
@Slf4j
@Component
public class DlqConsumer {
    private static final String ORIGINAL_QUEUE = "personExchange.person-queue";

    private static final String DLQ = ORIGINAL_QUEUE + ".dlq";

    private static final String X_RETRIES_HEADER = "x-retries";

    @Autowired
    private RabbitTemplate rabbitTemplate;


    @RabbitListener(queues = DLQ)
    public void rePublish(Message failedMessage) {

        failedMessage = attemptToRepair(failedMessage);

        Integer retriesHeader = (Integer) failedMessage.getMessageProperties().getHeaders().get(X_RETRIES_HEADER);
        if (retriesHeader == null) {
            retriesHeader = Integer.valueOf(0);
        }
        if (retriesHeader < 3) {
            failedMessage.getMessageProperties().getHeaders().put(X_RETRIES_HEADER, retriesHeader + 1);
            this.rabbitTemplate.send(ORIGINAL_QUEUE, failedMessage);
        } else {
            System.out.println("Writing to database: " + failedMessage.toString());
            //we can write to a database or move to a parking lot queue
        }
    }

    private Message attemptToRepair(Message failedMessage) {
        log.info("Attempt to repair message, before retrying");
        return failedMessage;
    }


}
