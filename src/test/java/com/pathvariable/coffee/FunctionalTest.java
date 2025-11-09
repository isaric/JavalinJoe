package com.pathvariable.coffee;

import io.javalin.Javalin;
import io.javalin.testtools.JavalinTest;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class FunctionalTest {

    Javalin app = JavalinApp.create();

    @Test
    void rootRedirectsAndServesIndex() {
        JavalinTest.test(app, (_, client) ->
                assertThat(client.get("/").code()).isEqualTo(200));
    }

}
