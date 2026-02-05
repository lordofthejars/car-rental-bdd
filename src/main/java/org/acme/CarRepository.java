package org.acme;


import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import org.jboss.resteasy.reactive.RestPath;

@ApplicationScoped
public class CarRepository implements PanacheRepository<Car> {
}
