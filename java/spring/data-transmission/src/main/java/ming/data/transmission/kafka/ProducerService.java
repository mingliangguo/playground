package ming.data.transmission.kafka;

import lombok.extern.slf4j.Slf4j;
import ming.data.transmission.model.Product;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Slf4j
@Service
public class ProducerService {
    private final KafkaTemplate<String, String> kafkaTemplate;

    public ProducerService(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendProduct(String topic, Product p) {
        ListenableFuture<SendResult<String, String>> future = kafkaTemplate.send(topic, String.valueOf(p.getId()), p.toString());

        future.addCallback(new ListenableFutureCallback<>() {
            @Override
            public void onFailure(Throwable ex) {
                log.error("unable to send message: {}, ", p, ex);
            }

            @Override
            public void onSuccess(SendResult<String, String> result) {
                log.info("successfully sent the message: {}, with offset: {}, partition: {}", p,
                        result.getRecordMetadata().offset(),
                        result.getRecordMetadata().partition()
                );
            }
        });
    }
    public void sendMessage(String topic, String message) {
        log.info("Send message: {}", message);
        ListenableFuture<SendResult<String, String>> future = kafkaTemplate.send(topic, message);
        future.addCallback(new ListenableFutureCallback<>() {
            @Override
            public void onFailure(Throwable ex) {
                log.error("unable to send message: {}, ", message, ex);
            }

            @Override
            public void onSuccess(SendResult<String, String> result) {
                log.info("successfully sent the message: {}, with offset: {}, partition: {}", message,
                        result.getRecordMetadata().offset(),
                        result.getRecordMetadata().partition()
                );
            }
        });
    }
}
