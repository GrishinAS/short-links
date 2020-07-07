package com.korus.shorter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.concurrent.Semaphore;


@SpringBootApplication
public class StartApplication {

  public static void main(String[] args) {
    SpringApplication.run(StartApplication.class, args);
  }

  @Bean
  public Semaphore parallelLinkGeneration(@Value("${max.parallel.link.generation}") int maxGeneration){
    return new Semaphore(maxGeneration);
  }

}
