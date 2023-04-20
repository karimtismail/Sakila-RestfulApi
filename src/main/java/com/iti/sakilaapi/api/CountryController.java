package com.iti.sakilaapi.api;

import com.iti.sakilaapi.model.dto.requests.CountryDTOReq;
import com.iti.sakilaapi.model.dto.response.CountryDTOResp;
import com.iti.sakilaapi.service.CountryService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;

import java.time.Instant;
import java.util.Arrays;
import java.util.List;

@Path("countries")
public class CountryController {
    private final CountryService countryService = new CountryService();
    private @Context UriInfo uriInfo;

    public CountryController() {
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCountryById(@PathParam("id") Integer id) {
        CountryDTOResp countryDTOResp = countryService.findById(id);
        if (countryDTOResp == null) {
            throw new NotFoundException("Country with ID: " + id + " Not Found");
        }
        Link self = Link.fromUriBuilder(uriInfo.getAbsolutePathBuilder()).rel("self").build();
        countryDTOResp.setLinks(Arrays.asList(self));
        return Response.ok(countryDTOResp).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllCountries() {
        try {
            List<CountryDTOResp> cities = countryService.findAll();
            for (CountryDTOResp country : cities) {
                Link self = Link.fromUriBuilder(uriInfo.getAbsolutePathBuilder().path(String.valueOf(country.getId()))).rel("self").build();
                country.setLinks(Arrays.asList(self));
            }
            return Response.ok(cities).build();
        } catch (Exception ex) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DELETE
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteCountryById(@PathParam("id") Integer id) {
        CountryDTOResp deletedCountryDto = countryService.deleteById(id);
        if (deletedCountryDto == null) {
            throw new NotFoundException("Country with ID: " + id + " not found");
        }
        Link self = Link.fromUriBuilder(uriInfo.getAbsolutePathBuilder()).rel("self").build();
        deletedCountryDto.setLinks(Arrays.asList(self));
        return Response.ok(deletedCountryDto).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createCountry(CountryDTOReq country) {
        country.setLastUpdate(Instant.now());
        CountryDTOResp createdCountryDto = countryService.save(country);
        if (createdCountryDto == null) {
            throw new InternalServerErrorException("Failed to create country");
        }
        Link self = Link.fromUriBuilder(uriInfo.getAbsolutePathBuilder()).rel("self").build();
        createdCountryDto.setLinks(Arrays.asList(self));
        return Response.ok(createdCountryDto).build();
    }

    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateCountry(@PathParam("id") Integer id, CountryDTOReq countryDTOReq) {
        countryDTOReq.setLastUpdate(Instant.now());
        CountryDTOResp updatedCountryDto = countryService.update(id, countryDTOReq);
        if (updatedCountryDto == null) {
            throw new InternalServerErrorException("Failed to update country");
        }
        Link self = Link.fromUriBuilder(uriInfo.getAbsolutePathBuilder()).rel("self").build();
        updatedCountryDto.setLinks(Arrays.asList(self));
        return Response.ok(updatedCountryDto).build();
    }
}