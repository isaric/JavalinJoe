# JavalinJoe — Coffee Orders API (with tiny frontend)

JavalinJoe is a minimal Java (JDK 25) project that demonstrates a small REST API built with Javalin 6 and a static frontend served from the classpath. It’s intentionally scaffolded: handlers exist for common RESTful actions, with some left as `501 Not Implemented` so you can complete them as an exercise.


## Tech stack
- Java 25 (via Gradle Toolchains)
- Gradle (wrapper included)
- Javalin 6.7.0
- Jackson Databind 2.17
- SLF4J (simple logger)


## Project structure
```
.
├── build.gradle
├── gradlew / gradlew.bat
├── settings.gradle
└── src
    └── main
        ├── java
        │   └── com/pathvariable/coffee
        │       ├── JavalinApp.java               # Builds the Javalin app and routes
        │       ├── Main.java                     # Starts the server (see Run section)
        │       ├── model/Order.java              # Domain model
        │       ├── repository/InMemoryOrderRepository.java
        │       └── request/OrderRequest.java     # DTO for creating an order
        └── resources
            └── public
                └── index.html                    # Tiny UI served as a static file
```


## Build
Use the Gradle wrapper (recommended):

```
./gradlew clean build
```

Artifacts and compiled classes will be placed under `build/`.


## Run
This repository does not (yet) configure the Gradle Application plugin for a CLI `run` task, and the `Main` entry point uses a minimal signature. The simplest ways to run are:

1) From your IDE (recommended for now)
- Open the project in IntelliJ IDEA or another Java IDE.
- Locate `com.pathvariable.coffee.Main`.
- Run `Main.main()`; the app will start on port `7070`.

2) Configure a CLI run (optional, not committed)
If you prefer running from the terminal, you can either:
- Update `Main` to use a standard entry point and add the Application plugin.
  - Change `Main` to:
    ```java
    public class Main {
        public static void main(String[] args) {
            JavalinApp.create().start(7070);
        }
    }
    ```
  - In `build.gradle`, add:
    ```groovy
    plugins {
        id 'java'
        id 'application'
    }

    application {
        mainClass = 'com.pathvariable.coffee.Main'
    }
    ```
  - Then run:
    ```
    ./gradlew run
    ```

- Or build a jar with a `Main-Class` manifest (via the Application plugin or a shadow/fat jar) and run:
  ```
  java -jar build/libs/JavalinJoe-<version>.jar
  ```


## API overview
The server exposes a small set of endpoints under the root path. Some are scaffolds returning `501 Not Implemented` (left for you to implement with the provided model and repository):

- `GET /` → redirects to `/index.html` (static UI)
- `GET /index.html` → serves the frontend from `src/main/resources/public`

Order endpoints (JSON):
- `POST /orders` → place a new order (expects `OrderRequest` JSON)
- `GET /orders` → list active orders (currently returns an empty list as placeholder)
- `GET /orders/{id}` → get status of one order (scaffold, returns 501)
- `PATCH /orders/{id}/ready` → mark an order as ready (scaffold, returns 501)
- `DELETE /orders/{id}` → cancel an order (currently returns 204 No Content)

### Example requests
Create an order (once implemented):
```
curl -X POST http://localhost:7070/orders \
  -H 'Content-Type: application/json' \
  -d '{"customerName":"Ada","drink":"Latte"}' -i
```

List orders:
```
curl http://localhost:7070/orders -i
```

Get one order (scaffold):
```
curl http://localhost:7070/orders/123 -i
```

Mark ready (scaffold):
```
curl -X PATCH http://localhost:7070/orders/123/ready -i
```

Delete/cancel:
```
curl -X DELETE http://localhost:7070/orders/123 -i
```


## Implementation notes
- Static files are served from the classpath directory `public` (i.e., `src/main/resources/public`). Root path `/` redirects to `/index.html` for convenience.
- `OrderRequest` includes a `validate()` helper for basic field checks.
- The repository and model classes are present to help you finish the TODOs in the handlers inside `JavalinApp`.


## Development hints
- Consider adding proper error handling and returning meaningful status codes/messages.
- If you need CORS or other middleware, configure it in `Javalin.create {}`.

