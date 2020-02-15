package ming.playground.boot.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.ResourcePatternResolver;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class Utils {
  private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
  private ResourcePatternResolver resourceResolver;

  private <T> List<T> loadObjects(final String location, Class<T> clazz) throws IOException {
    List<String> fileContents = loadResourcesAsString(location);
    List<T> list = new ArrayList<>();
    for (String content: fileContents) {
      T object = OBJECT_MAPPER.readValue(content, clazz);
      list.add(object);
    }
    return list;
  }
  private List<String> loadResourcesAsString(String resourcePath) throws IOException {
    List<String> resources = new ArrayList<>();
    for (Resource resource : resourceResolver.getResources(resourcePath)) {
      try (InputStream is = resource.getInputStream()) {
        final String fileName = FilenameUtils.getBaseName(resource.getFilename());
        log.info("read resource: {}", fileName);
        final String fileContent = IOUtils.toString(is, "UTF-8");
        resources.add(fileContent);
      }
    }
    return resources;
  }

}
