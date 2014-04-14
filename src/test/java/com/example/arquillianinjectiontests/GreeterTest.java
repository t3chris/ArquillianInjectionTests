package com.example.arquillianinjectiontests;

import javax.inject.Inject;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.junit.InSequence;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.junit.Assert.*;

@RunWith(Arquillian.class)
public class GreeterTest {

    @Deployment
    public static WebArchive createDeployment() {
        WebArchive deployment = ShrinkWrap.create(WebArchive.class)
                .addClass(Greeter.class)
                .addClass(GreetingProvider.class)
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml") ;

        return deployment;
    }

    @Inject
    private Greeter greeter;

    @Test
    @InSequence(1)
    public void testGreeterIsInjectedProperly() {
        assertNotNull(greeter);
    }

    @Test
    @InSequence(2)
    public void getGreetingTest() {
        String name = "John";
        String expected = "Hello, " + name + "!";
        String actual = greeter.doGreeting(name);
        assertEquals(expected, actual);
    }

    @Test
    @InSequence(3)
    public void testGreetingProviderInGreeterIsNotNull() {
        assertNotNull(greeter.greetingProvider);
    }


}
