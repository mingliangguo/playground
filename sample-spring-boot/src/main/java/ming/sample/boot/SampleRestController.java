package ming.sample.boot;

import ming.sample.boot.model.Entity;
import ming.sample.boot.services.CosService;
import ming.sample.boot.services.EntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class SampleRestController {
  @Autowired
  @Qualifier("config1")
  SampleApplication.Config config1;
  @Autowired
  @Qualifier("config2")
  SampleApplication.Config config2;
  @Autowired
  EntityService entityService;
  @Autowired
  CosService cosService;


  @RequestMapping(path = "/entities")
  public ResponseEntity<List<Entity>> entities() {
    List<Entity> entities = entityService.readEntity();
    return new ResponseEntity<>(entities, HttpStatus.OK);
  }
  @RequestMapping(path = "/entities/{id:.*}", method = RequestMethod.DELETE)
  public ResponseEntity<Void> delete(@PathVariable String id) {
    entityService.deleteCompositeEntityByFirstId(id);
    return new ResponseEntity<>(HttpStatus.OK);
  }

  @RequestMapping(path = "/configs")
  public ResponseEntity<List<SampleApplication.Config>> configs() {
    return new ResponseEntity<>(Arrays.asList(config1, config2), HttpStatus.OK);
  }

  @RequestMapping(path = "/buckets/{bucket:.*}")
  public ResponseEntity<List<String>> listObjectsInBucket(@PathVariable String bucket) {
    return new ResponseEntity<>(cosService.listFilesInBucket(bucket), HttpStatus.OK);
  }

  @RequestMapping(path = "/buckets")
  public ResponseEntity<List<String>> listBuckets() {
    return new ResponseEntity<>(cosService.listBuckets(), HttpStatus.OK);
  }
}
