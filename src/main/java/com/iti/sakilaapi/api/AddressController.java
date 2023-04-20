package com.iti.sakilaapi.api;

import com.iti.sakilaapi.model.dto.requests.AddressDTOReq;
import com.iti.sakilaapi.model.dto.response.AddressDTOResp;
import com.iti.sakilaapi.service.AddressService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;

import java.time.Instant;
import java.util.Arrays;
import java.util.List;

@Path("addresses")
public class AddressController {
    private final AddressService addressService = new AddressService();
    private @Context UriInfo uriInfo;

    public AddressController() {
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAddressById(@PathParam("id") Integer id) {
        AddressDTOResp addressDTOResp = addressService.findById(id);
        if (addressDTOResp == null) {
            throw new NotFoundException("Address with ID: " + id + " Not Found");
        }
        Link self = Link.fromUriBuilder(uriInfo.getAbsolutePathBuilder()).rel("self").build();
        addressDTOResp.setLinks(Arrays.asList(self));
        return Response.ok(addressDTOResp).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllAddresses() {
        try {
            List<AddressDTOResp> addresses = addressService.findAll();
            for (AddressDTOResp address : addresses) {
                Link self = Link.fromUriBuilder(uriInfo.getAbsolutePathBuilder().path(String.valueOf(address.getId()))).rel("self").build();
                address.setLinks(Arrays.asList(self));
            }
            return Response.ok(addresses).build();
        } catch (Exception ex) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DELETE
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteAddressById(@PathParam("id") Integer id) {
        AddressDTOResp deletedAddressDto = addressService.deleteById(id);
        if (deletedAddressDto == null) {
            throw new NotFoundException("Address with ID: " + id + " not found");
        }
        Link self = Link.fromUriBuilder(uriInfo.getAbsolutePathBuilder()).rel("self").build();
        deletedAddressDto.setLinks(Arrays.asList(self));
        return Response.ok(deletedAddressDto).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createAddress(AddressDTOReq address) {
        address.setLastUpdate(Instant.now());
        AddressDTOResp createdAddressDto = addressService.save(address);
        if (createdAddressDto == null) {
            throw new InternalServerErrorException("Failed to create address");
        }
        Link self = Link.fromUriBuilder(uriInfo.getAbsolutePathBuilder()).rel("self").build();
        createdAddressDto.setLinks(Arrays.asList(self));
        return Response.ok(createdAddressDto).build();
    }

    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateAddress(@PathParam("id") Integer id, AddressDTOReq address) {
        address.setLastUpdate(Instant.now());
        AddressDTOResp updatedAddressDto = addressService.update(id, address);
        if (updatedAddressDto == null) {
            throw new InternalServerErrorException("Failed to update address");
        }
        Link self = Link.fromUriBuilder(uriInfo.getAbsolutePathBuilder()).rel("self").build();
        updatedAddressDto.setLinks(Arrays.asList(self));
        return Response.ok(updatedAddressDto).build();
    }
}