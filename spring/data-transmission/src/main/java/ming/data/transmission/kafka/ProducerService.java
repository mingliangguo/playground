package ming.data.transmission.kafka;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Slf4j
@Service
public class ProducerService {
    private final KafkaTemplate kafkaTemplate;

    public ProducerService(KafkaTemplate kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(String topic, String message) {
        log.info("Send message: {}", message);
        ListenableFuture<SendResult<String, String>> future = kafkaTemplate.send(topic, message);
        future.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {
            @Override
            public void onFailure(Throwable ex) {
                log.error("unable to send message: {}, ", message, ex);
            }

            @Override
            public void onSuccess(SendResult<String, String> result) {
                log.info("successfully sent the message: {}, with offset: {}", message, result.getRecordMetadata().offset());
            }
        });
    }
}
