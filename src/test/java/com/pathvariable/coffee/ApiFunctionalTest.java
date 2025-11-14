package com.pathvariable.coffee;

import io.javalin.Javalin;
import io.javalin.testtools.JavalinTest;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ApiFunctionalTest {

    Javalin app = new JavalinApp().create();

    @Test
    void postOrders_returnsCreated() {
        JavalinTest.test(app, (_, client) -> {
            var resp = client.post("/orders");
            assertThat(resp.code()).isEqualTo(201);
        });
    }

    @Test
    void getOrders_returnsEmptyList() {
        JavalinTest.test(app, (_, client) -> {
            var resp = client.get("/orders");
            assertThat(resp.code()).isEqualTo(200);
            assertThat(resp.body().string().trim()).isEqualTo("[]");
        });
    }

    @Test
    void getOrderById_returnsOrder() {
        JavalinTest.test(app, (_, client) -> {
            var resp = client.get("/orders/123");
            assertThat(resp.code()).isEqualTo(200);
        });
    }

    @Test
    void patchOrderReady_returnsSuccess() {
        JavalinTest.test(app, (_, client) -> {
            var resp = client.patch("/orders/123/ready");
            assertThat(resp.code()).isEqualTo(200);
        });
    }

    @Test
    void deleteOrder_returnsNoContent() {
        JavalinTest.test(app, (_, client) -> {
            var resp = client.delete("/orders/123");
            assertThat(resp.code()).isEqualTo(204);
        });
    }
}
