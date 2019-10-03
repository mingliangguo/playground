package ming.sandbox.jackson;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import ming.sandbox.jackson.model.CustomMessage;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.Assert.fail;

public class JacksonTest {
  private ObjectMapper mapper = new ObjectMapper();

  @Test
  public void testSerialization() {
    CustomMessage message = new CustomMessage();
    message.setId("fake-id");
    message.setConnectionId("fake-connected-id");
    message.setMembers(Collections.singletonList(
      CustomMessage.EmbeddedIdentity.builder()
        .identityId("fake-identity-id")
        .identityEmail("fake-email-addr@fake.com")
        .build()
    ));
    try {
      String message_str = mapper.writeValueAsString(message);
      System.out.println(message_str);
    } catch (JsonProcessingException e) {
      e.printStackTrace();
      fail("no exception should be thrown");
    }
  }
}
