package com.iti.sakilaapi.api;

import com.iti.sakilaapi.model.dto.requests.FilmTextDTOReq;
import com.iti.sakilaapi.model.dto.response.FilmTextDTOResp;
import com.iti.sakilaapi.service.FilmTextService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;

import java.util.Arrays;
import java.util.List;

@Path("film_texts")
public class FilmTextController {
    private final FilmTextService filmService = new FilmTextService();
    private @Context UriInfo uriInfo;

    public FilmTextController() {
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getFilmById(@PathParam("id") Integer id) {
        FilmTextDTOResp filmTextDTOResp = filmService.findById(id);
        if (filmTextDTOResp == null) {
            throw new NotFoundException("Film with ID: " + id + " Not Found");
        }
        Link self = Link.fromUriBuilder(uriInfo.getAbsolutePathBuilder()).rel("self").build();
        filmTextDTOResp.setLinks(Arrays.asList(self));
        return Response.ok(filmTextDTOResp).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllFilms() {
        try {
            List<FilmTextDTOResp> categories = filmService.findAll();
            for (FilmTextDTOResp film : categories) {
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
        FilmTextDTOResp deletedFilmDto = filmService.deleteById(id);
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
    public Response createFilm(FilmTextDTOReq film) {
        FilmTextDTOResp createdFilmDto = filmService.save(film);
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
    public Response updateFilm(@PathParam("id") Integer id, FilmTextDTOReq filmDTOReq) {
        FilmTextDTOResp updatedFilmDto = filmService.update(id, filmDTOReq);
        if (updatedFilmDto == null) {
            throw new InternalServerErrorException("Failed to update filmDTOReq");
        }
        Link self = Link.fromUriBuilder(uriInfo.getAbsolutePathBuilder()).rel("self").build();
        updatedFilmDto.setLinks(Arrays.asList(self));
        return Response.ok(updatedFilmDto).build();
    }
}