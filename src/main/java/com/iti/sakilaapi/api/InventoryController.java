package com.iti.sakilaapi.api;

import com.iti.sakilaapi.model.dto.requests.InventoryDTOReq;
import com.iti.sakilaapi.model.dto.response.InventoryDTOResp;
import com.iti.sakilaapi.service.InventoryService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;

import java.time.Instant;
import java.util.Arrays;
import java.util.List;

@Path("inventories")
public class InventoryController {
    private final InventoryService inventoryService = new InventoryService();
    private @Context UriInfo uriInfo;

    public InventoryController() {
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getInventoryById(@PathParam("id") Integer id) {
        InventoryDTOResp inventoryDTOResp = inventoryService.findById(id);
        if (inventoryDTOResp == null) {
            throw new NotFoundException("Inventory with ID: " + id + " Not Found");
        }
        Link self = Link.fromUriBuilder(uriInfo.getAbsolutePathBuilder()).rel("self").build();
        inventoryDTOResp.setLinks(Arrays.asList(self));
        return Response.ok(inventoryDTOResp).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllInventories() {
        try {
            List<InventoryDTOResp> inventories = inventoryService.findAll();
            for (InventoryDTOResp inventory : inventories) {
                Link self = Link.fromUriBuilder(uriInfo.getAbsolutePathBuilder().path(String.valueOf(inventory.getId()))).rel("self").build();
                inventory.setLinks(Arrays.asList(self));
            }
            return Response.ok(inventories).build();
        } catch (Exception ex) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DELETE
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteInventoryById(@PathParam("id") Integer id) {
        InventoryDTOResp deletedInventoryDto = inventoryService.deleteById(id);
        if (deletedInventoryDto == null) {
            throw new NotFoundException("Inventory with ID: " + id + " not found");
        }
        Link self = Link.fromUriBuilder(uriInfo.getAbsolutePathBuilder()).rel("self").build();
        deletedInventoryDto.setLinks(Arrays.asList(self));
        return Response.ok(deletedInventoryDto).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createInventory(InventoryDTOReq inventory) {
        inventory.setLastUpdate(Instant.now());
        InventoryDTOResp createdInventoryDto = inventoryService.save(inventory);
        if (createdInventoryDto == null) {
            throw new InternalServerErrorException("Failed to create inventory");
        }
        Link self = Link.fromUriBuilder(uriInfo.getAbsolutePathBuilder()).rel("self").build();
        createdInventoryDto.setLinks(Arrays.asList(self));
        return Response.ok(createdInventoryDto).build();
    }

    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateInventory(@PathParam("id") Integer id, InventoryDTOReq inventoryDTOReq) {
        inventoryDTOReq.setLastUpdate(Instant.now());
        InventoryDTOResp updatedInventoryDto = inventoryService.update(id, inventoryDTOReq);
        if (updatedInventoryDto == null) {
            throw new InternalServerErrorException("Failed to update inventoryDTOReq");
        }
        Link self = Link.fromUriBuilder(uriInfo.getAbsolutePathBuilder()).rel("self").build();
        updatedInventoryDto.setLinks(Arrays.asList(self));
        return Response.ok(updatedInventoryDto).build();
    }
}