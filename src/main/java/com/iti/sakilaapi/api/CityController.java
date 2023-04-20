package com.iti.sakilaapi.api;

import com.iti.sakilaapi.model.dto.requests.CityDTOReq;
import com.iti.sakilaapi.model.dto.response.CityDTOResp;
import com.iti.sakilaapi.service.CityService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;

import java.time.Instant;
import java.util.Arrays;
import java.util.List;

@Path("cities")
public class CityController {
    private final CityService cityService = new CityService();
    private @Context UriInfo uriInfo;

    public CityController() {
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCityById(@PathParam("id") Integer id) {
        CityDTOResp cityDTOResp = cityService.findById(id);
        if (cityDTOResp == null) {
            throw new NotFoundException("City with ID: " + id + " Not Found");
        }
        Link self = Link.fromUriBuilder(uriInfo.getAbsolutePathBuilder()).rel("self").build();
        cityDTOResp.setLinks(Arrays.asList(self));
        return Response.ok(cityDTOResp).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllCitys() {
        try {
            List<CityDTOResp> cities = cityService.findAll();
            for (CityDTOResp city : cities) {
                Link self = Link.fromUriBuilder(uriInfo.getAbsolutePathBuilder().path(String.valueOf(city.getId()))).rel("self").build();
                city.setLinks(Arrays.asList(self));
            }
            return Response.ok(cities).build();
        } catch (Exception ex) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DELETE
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteCityById(@PathParam("id") Integer id) {
        CityDTOResp deletedCityDto = cityService.deleteById(id);
        if (deletedCityDto == null) {
            throw new NotFoundException("City with ID: " + id + " not found");
        }
        Link self = Link.fromUriBuilder(uriInfo.getAbsolutePathBuilder()).rel("self").build();
        deletedCityDto.setLinks(Arrays.asList(self));
        return Response.ok(deletedCityDto).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createCity(CityDTOReq city) {
        city.setLastUpdate(Instant.now());
        CityDTOResp createdCityDto = cityService.save(city);
        if (createdCityDto == null) {
            throw new InternalServerErrorException("Failed to create city");
        }
        Link self = Link.fromUriBuilder(uriInfo.getAbsolutePathBuilder()).rel("self").build();
        createdCityDto.setLinks(Arrays.asList(self));
        return Response.ok(createdCityDto).build();
    }

    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateCity(@PathParam("id") Integer id, CityDTOReq cityDTOReq) {
        cityDTOReq.setLastUpdate(Instant.now());
        CityDTOResp updatedCityDto = cityService.update(id, cityDTOReq);
        if (updatedCityDto == null) {
            throw new InternalServerErrorException("Failed to update city");
        }
        Link self = Link.fromUriBuilder(uriInfo.getAbsolutePathBuilder()).rel("self").build();
        updatedCityDto.setLinks(Arrays.asList(self));
        return Response.ok(updatedCityDto).build();
    }
}