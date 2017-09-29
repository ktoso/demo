package com.example.demo;

import akka.NotUsed;
import akka.actor.ActorSystem;
import akka.stream.ActorMaterializer;
import akka.stream.Materializer;
import akka.stream.javadsl.AsPublisher;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import akka.stream.javadsl.JavaFlowSupport;
import akka.stream.javadsl.Sink;
import akka.stream.javadsl.Source;

import java.util.concurrent.Flow;

@RestController
public class SampleController {

  private final static ActorSystem system = ActorSystem.create("System");
  private final static Materializer mat = ActorMaterializer.create(system);

  private final Sink<String, Flow.Publisher<String>> asFlowPublisher =
    JavaFlowSupport.Sink.asPublisher(AsPublisher.WITH_FANOUT);

  @RequestMapping("/")
  public Source<String, NotUsed> index() {

    final Flow.Publisher<String> flowPub =
      Source.single("Hello world!").runWith(asFlowPublisher, mat);

    return JavaFlowSupport.Source.fromPublisher(flowPub);
  }

}
