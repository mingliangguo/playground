package com.example.demo;

import com.example.demo.model.Entity;
import com.example.demo.services.EntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DemoRestController {
  @Autowired
  @Qualifier("config1")
  TomcatApplication.Config config1;
  @Autowired
  @Qualifier("config2")
  TomcatApplication.Config config2;
  @Autowired
  EntityService entityService;


  @RequestMapping(path = "/hello/{world:.*}")
  public ResponseEntity<List<Entity>> hello(@PathVariable String world) {
    System.out.println("Config1: " + config1);
    System.out.println("Config2: " + config2);
    List<Entity> entities = entityService.readEntity();
    return new ResponseEntity<List<Entity>>(entities, HttpStatus.OK);
  }
}
