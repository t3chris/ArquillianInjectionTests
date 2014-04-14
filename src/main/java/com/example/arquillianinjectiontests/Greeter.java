package com.example.arquillianinjectiontests;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;


@RequestScoped
public class Greeter {

    @Inject
    protected GreetingProvider greetingProvider;

    public String doGreeting(String greet) {
        System.out.println("Information: greetingProvider=" + greetingProvider);

        return String.format("%s, %s!", greetingProvider.getGreetingWord(), greet);
    }

}
