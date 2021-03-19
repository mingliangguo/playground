package ming.data.transmission.kafka;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ConsumerService {
    @KafkaListener(topics = "product", groupId = "data_group_id")
    public void consume(String message) {
        log.info(String.format("$$$$ => Consumed message: %s", message));
    }
}
