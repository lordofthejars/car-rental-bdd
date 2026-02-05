## Skill: Quarkus REST Endpoints

### Purpose
Enable Bob to scaffold and maintain REST endpoints using **Quarkus REST**.

### Overview
Quarkus REST provides a modern, high-performance implementation of Jakarta RESTful Web Services (JAX-RS). It replaces the legacy RESTEasy Reactive extension and integrates tightly with Quarkus Dev Mode for fast reloads and build-time optimization.

### Core Rules
1. Always depend on `io.quarkus:quarkus-rest`.
2. Annotate resource classes with `@Path`, and methods with HTTP verbs such as `@GET`, `@POST`, `@PUT`, and `@DELETE`.
3. Use `@Produces` and `@Consumes` to specify supported media types. Default to `application/json`.
4. Define the global base path via `@ApplicationPath(”/api”)` or the `quarkus.rest.path` configuration property.
5. Classes must use Jakarta packages (`jakarta.ws.rs.*`), not the old `javax` ones.
6. Always confirm that endpoints are live using Quarkus Dev Mode’s MCP endpoint (`/q/dev-mcp`).
7. When adding REST functionality, **use the `quarkus-rest` extension only**—avoid older RESTEasy or RESTEasy Reactive variants.

### Example Resource

```java
package org.acme.rest;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

@Path("/hello")
public class HelloResource {

    @GET
    public String hello() {
        return "Hello, Quarkus REST!";
    }

    @POST
    public String echo(String message) {
        return "Received: " + message;
    }
}
```

### Notes
- Quarkus REST runs on Vert.x, offering both reactive and blocking I/O models.
- Resources are automatically discovered during build and registered at runtime.
- Use Quarkus Dev Mode (./mvnw quarkus:dev) to live-reload REST resources after edits.
- You can test endpoints using cURL or the built-in Dev UI “REST Endpoints” view.
- Avoid mixing REST frameworks—keep Quarkus REST as the single implementation source.

### Validation Check
After generating or modifying REST resources, Bob should:
- Re-query the /q/dev-mcp endpoint.
- Confirm new endpoints appear in the live application state.
- Verify that the HTTP methods and media types match the defined annotations.
