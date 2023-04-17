package com.iti.sakilaapi.api;

import com.iti.sakilaapi.model.dto.StoreDto;
import com.iti.sakilaapi.model.entity.Store;
import com.iti.sakilaapi.service.StoreService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;

import java.util.Arrays;
import java.util.List;

@Path("stores")
public class StoreController {
    private final StoreService storeService = new StoreService(Store.class, StoreDto.class);

    public StoreController() {
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getStoreById(@PathParam("id") Short id, @Context UriInfo uriInfo) {
        StoreDto storeDto = storeService.findById(id);
        if (storeDto == null) {
            throw new NotFoundException("Store with ID: " + id + " Not Found");
        }
        Link self = Link.fromUriBuilder(uriInfo.getAbsolutePathBuilder()).rel("self").build();
        storeDto.setLinks(Arrays.asList(self));
        return Response.ok(storeDto).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllStores(@Context UriInfo uriInfo) {
        try {
            List<StoreDto> stores = storeService.findAll();
            for (StoreDto store : stores) {
                Link self = Link.fromUriBuilder(uriInfo.getAbsolutePathBuilder().path(String.valueOf(store.getId()))).rel("self").build();
                store.setLinks(Arrays.asList(self));
            }
            return Response.ok(stores).build();
        } catch (Exception ex) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DELETE
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteStoreById(@PathParam("id") Short id, @Context UriInfo uriInfo) {
        StoreDto deletedStoreDto = storeService.deleteById(id);
        if (deletedStoreDto == null) {
            throw new NotFoundException("Store with ID: " + id + " not found");
        }
        Link self = Link.fromUriBuilder(uriInfo.getAbsolutePathBuilder()).rel("self").build();
        deletedStoreDto.setLinks(Arrays.asList(self));
        return Response.ok(deletedStoreDto).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createStore(Store store, @Context UriInfo uriInfo) {
        StoreDto createdStoreDto = storeService.save(store);
        if (createdStoreDto == null) {
            throw new InternalServerErrorException("Failed to create store");
        }
        Link self = Link.fromUriBuilder(uriInfo.getAbsolutePathBuilder()).rel("self").build();
        createdStoreDto.setLinks(Arrays.asList(self));
        return Response.ok(createdStoreDto).build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateStore(Store store, @Context UriInfo uriInfo) {
        StoreDto updatedStoreDto = storeService.update(store);
        if (updatedStoreDto == null) {
            throw new InternalServerErrorException("Failed to update store");
        }
        Link self = Link.fromUriBuilder(uriInfo.getAbsolutePathBuilder()).rel("self").build();
        updatedStoreDto.setLinks(Arrays.asList(self));
        return Response.ok(updatedStoreDto).build();
    }
}