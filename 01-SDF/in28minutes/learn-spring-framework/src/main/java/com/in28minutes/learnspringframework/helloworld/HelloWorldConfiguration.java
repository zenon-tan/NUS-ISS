package com.in28minutes.learnspringframework.helloworld;

import org.springframework.aop.framework.adapter.AfterReturningAdviceInterceptor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

record Person(String name, int age, Address address) {};
record Address(String firstLine, String city) {};

@Configuration
public class HelloWorldConfiguration {


    @Bean
    public String name() {
        return "Jesus";
    }

    @Bean
    public int age() {
        return 29;
    }

    @Bean
    public Person person() {
        var person = new Person("Judas", 666, new Address("Simei", "Singapore"));
        return person;
    }

    @Bean(name = "address2")
    @Primary
    public Address address() {
        return(new Address("Simei", "Singapore"));
    }

    @Bean(name = "address3")
    @Qualifier("address3qualifier")
    public Address address3() {
        return(new Address("Mulholland Drive", "Los Angeles"));
    }

    @Bean
    public Person person2MethodCall() {
        var person = new Person(name(), age(), address());
        return person;
    }

    @Bean
    @Primary
    public Person person3Parameters(String name, int age, Address address2) { //name,age,address2
        var person = new Person(name, age, address2);
        return person;
    }

    @Bean
    public Person person4Parameters(String name, int age, Address address) { //name,age,address2
        var person = new Person(name, age, address);
        return person;
    }

    @Bean
    public Person person5Parameters(String name, int age,     @Qualifier("address3qualifier")
    Address address) { //name,age,address2
        var person = new Person(name, age, address);
        return person;
    }


    
}
