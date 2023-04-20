package com.iti.sakilaapi.api;

import com.iti.sakilaapi.model.dto.requests.FilmDTOReq;
import com.iti.sakilaapi.model.dto.response.FilmDTOResp;
import com.iti.sakilaapi.service.FilmService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;

import java.time.Instant;
import java.util.Arrays;
import java.util.List;

@Path("films")
public class FilmController {
    private final FilmService filmService = new FilmService();
    private @Context UriInfo uriInfo;

    public FilmController() {
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getFilmById(@PathParam("id") Integer id) {
        FilmDTOResp filmDTOResp = filmService.findById(id);
        if (filmDTOResp == null) {
            throw new NotFoundException("Film with ID: " + id + " Not Found");
        }
        Link self = Link.fromUriBuilder(uriInfo.getAbsolutePathBuilder()).rel("self").build();
        filmDTOResp.setLinks(Arrays.asList(self));
        return Response.ok(filmDTOResp).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllFilms() {
        try {
            List<FilmDTOResp> categories = filmService.findAll();
            for (FilmDTOResp film : categories) {
                Link self = Link.fromUriBuilder(uriInfo.getAbsolutePathBuilder().path(String.valueOf(film.getId()))).rel("self").build();
                film.setLinks(Arrays.asList(self));
            }
            return Response.ok(categories).build();
        } catch (Exception ex) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DELETE
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteFilmById(@PathParam("id") Integer id) {
        FilmDTOResp deletedFilmDto = filmService.deleteById(id);
        if (deletedFilmDto == null) {
            throw new NotFoundException("Film with ID: " + id + " not found");
        }
        Link self = Link.fromUriBuilder(uriInfo.getAbsolutePathBuilder()).rel("self").build();
        deletedFilmDto.setLinks(Arrays.asList(self));
        return Response.ok(deletedFilmDto).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createFilm(FilmDTOReq film) {
        film.setLastUpdate(Instant.now());
        FilmDTOResp createdFilmDto = filmService.save(film);
        if (createdFilmDto == null) {
            throw new InternalServerErrorException("Failed to create film");
        }
        Link self = Link.fromUriBuilder(uriInfo.getAbsolutePathBuilder()).rel("self").build();
        createdFilmDto.setLinks(Arrays.asList(self));
        return Response.ok(createdFilmDto).build();
    }

    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateFilm(@PathParam("id") Integer id, FilmDTOReq filmDTOReq) {
        filmDTOReq.setLastUpdate(Instant.now());
        FilmDTOResp updatedFilmDto = filmService.update(id, filmDTOReq);
        if (updatedFilmDto == null) {
            throw new InternalServerErrorException("Failed to update filmDTOReq");
        }
        Link self = Link.fromUriBuilder(uriInfo.getAbsolutePathBuilder()).rel("self").build();
        updatedFilmDto.setLinks(Arrays.asList(self));
        return Response.ok(updatedFilmDto).build();
    }
}