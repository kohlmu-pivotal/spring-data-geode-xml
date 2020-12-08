package com.example.demo;

import com.example.demo.model.ExampleDataObject;
import com.example.demo.model.PhoneNumber;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ImportResource;

import org.apache.geode.cache.Region;

@SpringBootApplication
@ImportResource({"classpath*:spring-gemfire.xml"})
public class DemoApplication {

  public static void main(String[] args) {
    ApplicationContext applicationContext = SpringApplication.run(DemoApplication.class, args);

    Region region = applicationContext.getBean("UnmaintainedPortfolios", Region.class);

    System.out.println("region.size() = " + region.size());

    for (int i = 0; i < 1000000; i++) {
      region.put(i, new ExampleDataObject("firstName" + i, "surname" + i, 10 + i,
          new PhoneNumber(0 + i, 10 + i, 1000000 + i)));
    }

    System.out.println("region.size() = " + region.size());
  }
}
