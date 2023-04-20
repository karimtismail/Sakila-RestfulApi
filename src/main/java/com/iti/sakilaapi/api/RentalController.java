package com.iti.sakilaapi.api;

import com.iti.sakilaapi.model.dto.requests.RentalDTOReq;
import com.iti.sakilaapi.model.dto.response.RentalDTOResp;
import com.iti.sakilaapi.service.RentalService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;

import java.time.Instant;
import java.util.Arrays;
import java.util.List;

@Path("rentals")
public class RentalController {
    private final RentalService rentalService = new RentalService();
    private @Context UriInfo uriInfo;

    public RentalController() {
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getRentalById(@PathParam("id") Integer id) {
        RentalDTOResp rentalDTOResp = rentalService.findById(id);
        if (rentalDTOResp == null) {
            throw new NotFoundException("Rental with ID: " + id + " Not Found");
        }
        Link self = Link.fromUriBuilder(uriInfo.getAbsolutePathBuilder()).rel("self").build();
        rentalDTOResp.setLinks(Arrays.asList(self));
        return Response.ok(rentalDTOResp).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllRentals() {
        try {
            List<RentalDTOResp> rentals = rentalService.findAll();
            for (RentalDTOResp rental : rentals) {
                Link self = Link.fromUriBuilder(uriInfo.getAbsolutePathBuilder().path(String.valueOf(rental.getId()))).rel("self").build();
                rental.setLinks(Arrays.asList(self));
            }
            return Response.ok(rentals).build();
        } catch (Exception ex) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DELETE
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteRentalById(@PathParam("id") Integer id) {
        RentalDTOResp deletedRentalDto = rentalService.deleteById(id);
        if (deletedRentalDto == null) {
            throw new NotFoundException("Rental with ID: " + id + " not found");
        }
        Link self = Link.fromUriBuilder(uriInfo.getAbsolutePathBuilder()).rel("self").build();
        deletedRentalDto.setLinks(Arrays.asList(self));
        return Response.ok(deletedRentalDto).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createRental(RentalDTOReq rental) {
        rental.setRentalDate(Instant.now());
        rental.setReturnDate(Instant.now());
        rental.setLastUpdate(Instant.now());
        RentalDTOResp createdRentalDto = rentalService.save(rental);
        if (createdRentalDto == null) {
            throw new InternalServerErrorException("Failed to create rental");
        }
        Link self = Link.fromUriBuilder(uriInfo.getAbsolutePathBuilder()).rel("self").build();
        createdRentalDto.setLinks(Arrays.asList(self));
        return Response.ok(createdRentalDto).build();
    }

    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateRental(@PathParam("id") Integer id, RentalDTOReq rentalDTOReq) {
        rentalDTOReq.setRentalDate(Instant.now());
        rentalDTOReq.setReturnDate(Instant.now());
        rentalDTOReq.setLastUpdate(Instant.now());
        RentalDTOResp updatedRentalDto = rentalService.update(id, rentalDTOReq);
        if (updatedRentalDto == null) {
            throw new InternalServerErrorException("Failed to update rentalDTOReq");
        }
        Link self = Link.fromUriBuilder(uriInfo.getAbsolutePathBuilder()).rel("self").build();
        updatedRentalDto.setLinks(Arrays.asList(self));
        return Response.ok(updatedRentalDto).build();
    }
}