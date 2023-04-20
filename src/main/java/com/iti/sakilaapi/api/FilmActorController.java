package com.iti.sakilaapi.api;

import com.iti.sakilaapi.model.dto.requests.FilmActorDTOReq;
import com.iti.sakilaapi.model.dto.response.FilmActorDTOResp;
import com.iti.sakilaapi.model.dto.response.FilmActorIdDTOResp;
import com.iti.sakilaapi.service.FilmActorService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;

import java.time.Instant;
import java.util.Arrays;
import java.util.List;

@Path("film_actors")
public class FilmActorController {
    private final FilmActorService filmActorService = new FilmActorService();
    private @Context UriInfo uriInfo;

    public FilmActorController() {
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getFilmActorById(@PathParam("id") Integer id) {
        FilmActorDTOResp filmActorDTOResp = filmActorService.findById(id);;
        if (filmActorDTOResp == null) {
            throw new NotFoundException("FilmActor with ID: " + id + " Not Found");
        }
        Link self = Link.fromUriBuilder(uriInfo.getAbsolutePathBuilder()).rel("self").build();
        filmActorDTOResp.setLinks(Arrays.asList(self));
        return Response.ok(filmActorDTOResp).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllFilmActors() {
        try {
            List<FilmActorDTOResp> filmActors = filmActorService.findAll();
            for (FilmActorDTOResp filmActor : filmActors) {
                Link self = Link.fromUriBuilder(uriInfo.getAbsolutePathBuilder().path(String.valueOf(filmActor.getId()))).rel("self").build();
                filmActor.setLinks(Arrays.asList(self));
            }
            return Response.ok(filmActors).build();
        } catch (Exception ex) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DELETE
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteFilmActorById(@PathParam("id") Integer id) {
        FilmActorDTOResp deletedFilmActorDto = filmActorService.deleteById(id);
        if (deletedFilmActorDto == null) {
            throw new NotFoundException("FilmActor with ID: " + id + " not found");
        }
        Link self = Link.fromUriBuilder(uriInfo.getAbsolutePathBuilder()).rel("self").build();
        deletedFilmActorDto.setLinks(Arrays.asList(self));
        return Response.ok(deletedFilmActorDto).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createFilmActor(FilmActorDTOReq filmActor) {
        filmActor.setLastUpdate(Instant.now());
        FilmActorDTOResp createdFilmActorDto = filmActorService.save(filmActor);
        if (createdFilmActorDto == null) {
            throw new InternalServerErrorException("Failed to create filmActor");
        }
        Link self = Link.fromUriBuilder(uriInfo.getAbsolutePathBuilder()).rel("self").build();
        createdFilmActorDto.setLinks(Arrays.asList(self));
        return Response.ok(createdFilmActorDto).build();
    }

    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateFilmActor(@PathParam("id") Integer id, FilmActorDTOReq filmActor) {
        filmActor.setLastUpdate(Instant.now());
        FilmActorDTOResp updatedFilmActorDto = filmActorService.update(id, filmActor);
        if (updatedFilmActorDto == null) {
            throw new InternalServerErrorException("Failed to update filmActor");
        }
        Link self = Link.fromUriBuilder(uriInfo.getAbsolutePathBuilder()).rel("self").build();
        updatedFilmActorDto.setLinks(Arrays.asList(self));
        return Response.ok(updatedFilmActorDto).build();
    }
}