package com.iti.sakilaapi.api;

import com.iti.sakilaapi.model.dto.ActorDto;
import com.iti.sakilaapi.model.entity.Actor;
import com.iti.sakilaapi.service.ActorService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;

import java.util.Arrays;
import java.util.List;

@Path("actors")
public class ActorController {
    private final ActorService actorService = new ActorService(Actor.class, ActorDto.class);

    public ActorController() {
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getActorById(@PathParam("id") Short id, @Context UriInfo uriInfo) throws Exception {
        ActorDto actorDto = actorService.findById(id);
        if (actorDto == null) {
            throw new Exception("Actor with ID: " + id + " Not Found");
        }
        Link self = Link.fromUriBuilder(uriInfo.getAbsolutePathBuilder()).rel("self").build();
        actorDto.setLinks(Arrays.asList(self));
        return Response.ok(actorDto).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllActors(@Context UriInfo uriInfo) {
        try {
            List<ActorDto> actors = actorService.findAll();
            for (ActorDto actor : actors) {
                Link self = Link.fromUriBuilder(uriInfo.getAbsolutePathBuilder().path(String.valueOf(actor.getActorId()))).rel("self").build();
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
    public Response deleteActorById(@PathParam("id") Short id, @Context UriInfo uriInfo) {
        ActorDto deletedActorDto = actorService.deleteById(id);
        if (deletedActorDto == null) {
            throw new NotFoundException("Actor with ID: " + id + " not found");
        }
        Link self = Link.fromUriBuilder(uriInfo.getAbsolutePathBuilder()).rel("self").build();
        deletedActorDto.setLinks(Arrays.asList(self));
        return Response.ok(deletedActorDto).build();
    }

//    @POST
//    @Consumes(MediaType.APPLICATION_JSON)
//    public Response createActor(Actor actor, @Context UriInfo uriInfo) {
//        ActorDto createdActorDto = actorService.save(actor);
//
//        if (createdActorDto == null) {
//            throw new InternalServerErrorException("Failed to create actor");
//        }
//        URI createdActorUri = uriInfo.getAbsolutePathBuilder().path(String.valueOf(createdActorDto.getId())).build();
//        Link self = Link.fromUriBuilder(UriBuilder.fromUri(createdActorUri)).rel("self").build();
//        createdActorDto.setLinks(Arrays.asList(self));
//        return Response.created(createdActorUri).entity(createdActorDto).build();
//    }

//    @PUT
//    @Produces(MediaType.APPLICATION_JSON)
//    public Response updateActor(Actor actor, @Context UriInfo uriInfo) {
//        ActorDto updatedActorDto = actorService.update(actor);
//        Link self = Link.fromUriBuilder(uriInfo.getAbsolutePathBuilder()).rel("self").build();
//        updatedActorDto.setLinks(Arrays.asList(self));
//        return Response.ok(updatedActorDto).build();
//    }
}