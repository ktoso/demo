package com.example.demo;

import akka.NotUsed;
import akka.stream.javadsl.Source;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SampleController {

  @RequestMapping("/")
  public Source<String, NotUsed> index() {
      return Source.single("Hello world!");
  }

}
