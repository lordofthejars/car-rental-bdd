## Skill: Quarkus Panache for Database

### Purpose
Enable Bob to scaffold and maintain database access using **Quarkus Panache**.

### Overview
Quarkus REST provides a modern, high-performance implementation of Jakarta RESTful Web Services (JAX-RS). It replaces the legacy RESTEasy Reactive extension and integrates tightly with Quarkus Dev Mode for fast reloads and build-time optimization.

### Core Rules
1. Always depend on `io.quarkus:quarkus-hibernate-orm`.
2. Annotate if required entity model classes with JPA annotations.
3. Before creating a Data Repository check if there is already a repository for the given entity.
4. Use Panache to access database using Repository pattern extending the class from the `io.quarkus.hibernate.orm.panache.PanacheRepository` interface.
5. Classes must use Jakarta packages (`jakarta.persistence.*`), not the old `javax` ones.

### Example Resource

```java
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@Entity
public class Car {

    @Id
    @GeneratedValue
    private Long id;
    public String brand;
}

@ApplicationScoped
public class CarRepository implements PanacheRepository<Car> {

    public Car findCarByBrandAndModel(String brand, String model) {
        return find("brand = ?1 and model = ?2", brand, model).firstResult();
    }

}
```

### Notes
- Quarkus Panache uses Hibernate to access to database, use it instead of plain Hibernate code.
- Always check if there is already an existing entity or repository before creating it.

