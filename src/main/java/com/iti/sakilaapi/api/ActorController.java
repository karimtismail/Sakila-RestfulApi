package com.iti.sakilaapi.api;

import com.iti.sakilaapi.model.dto.requests.ActorDTOReq;
import com.iti.sakilaapi.model.dto.response.ActorDTOResp;
import com.iti.sakilaapi.service.ActorService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;

import java.util.Arrays;
import java.util.List;

@Path("actors")
public class ActorController {
    private final ActorService actorService = new ActorService();

    public ActorController() {
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getActorById(@PathParam("id") Integer id, @Context UriInfo uriInfo) {
        ActorDTOResp actorDTOResp = actorService.findById(id);
        if (actorDTOResp == null) {
            throw new NotFoundException("Actor with ID: " + id + " Not Found");
        }
        Link self = Link.fromUriBuilder(uriInfo.getAbsolutePathBuilder()).rel("self").build();
        actorDTOResp.setLinks(Arrays.asList(self));
        return Response.ok(actorDTOResp).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllActors(@Context UriInfo uriInfo) {
        try {
            List<ActorDTOResp> actors = actorService.findAll();
            for (ActorDTOResp actor : actors) {
                Link self = Link.fromUriBuilder(uriInfo.getAbsolutePathBuilder().path(String.valueOf(actor.getId()))).rel("self").build();
                actor.setLinks(Arrays.asList(self));
            }
            return Response.ok(actors).build();
        } catch (Exception ex) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DELETE
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteActorById(@PathParam("id") Integer id, @Context UriInfo uriInfo) {
        ActorDTOResp deletedActorDto = actorService.deleteById(id);
        if (deletedActorDto == null) {
            throw new NotFoundException("Actor with ID: " + id + " not found");
        }
        Link self = Link.fromUriBuilder(uriInfo.getAbsolutePathBuilder()).rel("self").build();
        deletedActorDto.setLinks(Arrays.asList(self));
        return Response.ok(deletedActorDto).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createActor(ActorDTOReq actor, @Context UriInfo uriInfo) {
        ActorDTOResp createdActorDto = actorService.save(actor);
        if (createdActorDto == null) {
            throw new InternalServerErrorException("Failed to create actor");
        }
        Link self = Link.fromUriBuilder(uriInfo.getAbsolutePathBuilder()).rel("self").build();
        createdActorDto.setLinks(Arrays.asList(self));
        return Response.ok(createdActorDto).build();
    }

    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateActor(@PathParam("id") Integer id, ActorDTOReq actorDTOReq, @Context UriInfo uriInfo) {
        ActorDTOResp updatedActorDto = actorService.update(id, actorDTOReq);
        if (updatedActorDto == null) {
            throw new InternalServerErrorException("Failed to update actorDTOReq");
        }
        Link self = Link.fromUriBuilder(uriInfo.getAbsolutePathBuilder()).rel("self").build();
        updatedActorDto.setLinks(Arrays.asList(self));
        return Response.ok(updatedActorDto).build();
    }
}