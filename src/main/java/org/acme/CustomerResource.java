package org.acme;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import org.jboss.resteasy.reactive.RestPath;

@Path("/customers")
public class CustomerResource {

    @Inject
    CustomerRepository customerRepository;

    @GET
    @Path("{id}")
    public Customer findCustomerById(@RestPath Long id) {
        return customerRepository.findById(id);
    }

}
