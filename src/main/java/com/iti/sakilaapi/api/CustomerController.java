package com.iti.sakilaapi.api;

import com.iti.sakilaapi.model.dto.requests.CustomerDTOReq;
import com.iti.sakilaapi.model.dto.response.CustomerDTOResp;
import com.iti.sakilaapi.service.CustomerService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;

import java.time.Instant;
import java.util.Arrays;
import java.util.List;

@Path("customers")
public class CustomerController {
    private final CustomerService customerService = new CustomerService();
    private @Context UriInfo uriInfo;

    public CustomerController() {
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCustomerById(@PathParam("id") Integer id) {
        CustomerDTOResp customerDTOResp = customerService.findById(id);
        if (customerDTOResp == null) {
            throw new NotFoundException("Customer with ID: " + id + " Not Found");
        }
        Link self = Link.fromUriBuilder(uriInfo.getAbsolutePathBuilder()).rel("self").build();
        customerDTOResp.setLinks(Arrays.asList(self));
        return Response.ok(customerDTOResp).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllCustomers() {
        try {
            List<CustomerDTOResp> customers = customerService.findAll();
            for (CustomerDTOResp customer : customers) {
                Link self = Link.fromUriBuilder(uriInfo.getAbsolutePathBuilder().path(String.valueOf(customer.getId()))).rel("self").build();
                customer.setLinks(Arrays.asList(self));
            }
            return Response.ok(customers).build();
        } catch (Exception ex) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DELETE
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteCustomerById(@PathParam("id") Integer id) {
        CustomerDTOResp deletedCustomerDto = customerService.deleteById(id);
        if (deletedCustomerDto == null) {
            throw new NotFoundException("Customer with ID: " + id + " not found");
        }
        Link self = Link.fromUriBuilder(uriInfo.getAbsolutePathBuilder()).rel("self").build();
        deletedCustomerDto.setLinks(Arrays.asList(self));
        return Response.ok(deletedCustomerDto).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createCustomer(CustomerDTOReq customer) {
        customer.setCreateDate(Instant.now());
        customer.setLastUpdate(Instant.now());
        CustomerDTOResp createdCustomerDto = customerService.save(customer);
        if (createdCustomerDto == null) {
            throw new InternalServerErrorException("Failed to create customer");
        }
        Link self = Link.fromUriBuilder(uriInfo.getAbsolutePathBuilder()).rel("self").build();
        createdCustomerDto.setLinks(Arrays.asList(self));
        return Response.ok(createdCustomerDto).build();
    }

    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateCustomer(@PathParam("id") Integer id, CustomerDTOReq customer) {
        customer.setCreateDate(Instant.now());
        customer.setLastUpdate(Instant.now());
        CustomerDTOResp updatedCustomerDto = customerService.update(id, customer);
        if (updatedCustomerDto == null) {
            throw new InternalServerErrorException("Failed to update customer");
        }
        Link self = Link.fromUriBuilder(uriInfo.getAbsolutePathBuilder()).rel("self").build();
        updatedCustomerDto.setLinks(Arrays.asList(self));
        return Response.ok(updatedCustomerDto).build();
    }
}