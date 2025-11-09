package com.pathvariable.coffee;

import io.javalin.Javalin;
import io.javalin.http.staticfiles.Location;

import java.util.List;

public class JavalinApp {

    public static Javalin create() {
        return Javalin.create(cfg -> {
                    cfg.staticFiles.add(staticFiles -> {
                        staticFiles.hostedPath = "/"; // root
                        staticFiles.directory = "public"; // src/main/resources/public
                        staticFiles.location = Location.CLASSPATH;
                    });
                })
                // Serve the frontend. Static files will serve /index.html;
                // keep root path to redirect explicitly for convenience.
                .get("/", ctx -> ctx.redirect("/index.html"))

                // POST /orders — Place a new order (expects JSON body)
                // Note: Only handler scaffolding; actual creation logic is intentionally omitted.
                .post("/orders", ctx -> {
                    // Example of reading JSON (implementation to be added):
                    // var request = ctx.bodyAsClass(OrderRequest.class);
                    ctx.status(501); // Not Implemented
                })

                // GET /orders — See all active orders (returns JSON list)
                // Returning an empty list as a placeholder scaffold.
                .get("/orders", ctx -> {
                    ctx.json(List.of());
                })

                // GET /orders/{id} — Check status of one order (uses path param)
                .get("/orders/{id}", ctx -> {
                    String id = ctx.pathParam("id");
                    // TODO: Lookup order by id and return its status as JSON
                    ctx.status(501); // Not Implemented
                })

                // PATCH /orders/{id}/ready — Mark an order as complete (partial update)
                .patch("/orders/{id}/ready", ctx -> {
                    String id = ctx.pathParam("id");
                    // TODO: Update order state to ready
                    ctx.status(501); // Not Implemented
                })

                // DELETE /orders/{id} — Cancel an order (standard deletion)
                .delete("/orders/{id}", ctx -> {
                    String id = ctx.pathParam("id");
                    // TODO: Cancel/delete the order
                    ctx.status(204); // No Content
                });
    }
}
