package com.iti.sakilaapi.api;

import com.iti.sakilaapi.model.dto.requests.StoreDTOReq;
import com.iti.sakilaapi.model.dto.response.StoreDTOResp;
import com.iti.sakilaapi.service.StoreService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;

import java.util.Arrays;
import java.util.List;

@Path("stores")
public class StoreController {
    private final StoreService storeService = new StoreService();

    public StoreController() {
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getStoreById(@PathParam("id") Integer id, @Context UriInfo uriInfo) {
        StoreDTOResp storeDTOResp = storeService.findById(id);
        if (storeDTOResp == null) {
            throw new NotFoundException("Store with ID: " + id + " Not Found");
        }
        Link self = Link.fromUriBuilder(uriInfo.getAbsolutePathBuilder()).rel("self").build();
        storeDTOResp.setLinks(Arrays.asList(self));
        return Response.ok(storeDTOResp).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllStores(@Context UriInfo uriInfo) {
        try {
            List<StoreDTOResp> stores = storeService.findAll();
            for (StoreDTOResp store : stores) {
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
    public Response deleteStoreById(@PathParam("id") Integer id, @Context UriInfo uriInfo) {
        StoreDTOResp deletedStoreDto = storeService.deleteById(id);
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
    public Response createStore(StoreDTOReq store, @Context UriInfo uriInfo) {
        StoreDTOResp createdStoreDto = storeService.save(store);
        if (createdStoreDto == null) {
            throw new InternalServerErrorException("Failed to create store");
        }
        Link self = Link.fromUriBuilder(uriInfo.getAbsolutePathBuilder()).rel("self").build();
        createdStoreDto.setLinks(Arrays.asList(self));
        return Response.ok(createdStoreDto).build();
    }

    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateStore(@PathParam("id") Integer id, StoreDTOReq store, @Context UriInfo uriInfo) {
        StoreDTOResp updatedStoreDto = storeService.update(id, store);
        if (updatedStoreDto == null) {
            throw new InternalServerErrorException("Failed to update store");
        }
        Link self = Link.fromUriBuilder(uriInfo.getAbsolutePathBuilder()).rel("self").build();
        updatedStoreDto.setLinks(Arrays.asList(self));
        return Response.ok(updatedStoreDto).build();
    }
}