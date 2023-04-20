package com.iti.sakilaapi.api;

import com.iti.sakilaapi.model.dto.requests.FilmCategoryDTOReq;
import com.iti.sakilaapi.model.dto.response.FilmCategoryDTOResp;
import com.iti.sakilaapi.service.FilmCategoryService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;

import java.time.Instant;
import java.util.Arrays;
import java.util.List;

@Path("film_categories")
public class FilmCategoryController {
    private final FilmCategoryService filmCategoryService = new FilmCategoryService();
    private @Context UriInfo uriInfo;

    public FilmCategoryController() {
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getFilmCategoryById(@PathParam("id") Integer id) {
        FilmCategoryDTOResp filmCategoryDTOResp = filmCategoryService.findById(id);
        if (filmCategoryDTOResp == null) {
            throw new NotFoundException("FilmCategory with ID: " + id + " Not Found");
        }
        Link self = Link.fromUriBuilder(uriInfo.getAbsolutePathBuilder()).rel("self").build();
        filmCategoryDTOResp.setLinks(Arrays.asList(self));
        return Response.ok(filmCategoryDTOResp).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllFilmCategorys() {
        try {
            List<FilmCategoryDTOResp> categories = filmCategoryService.findAll();
            for (FilmCategoryDTOResp filmCategory : categories) {
                Link self = Link.fromUriBuilder(uriInfo.getAbsolutePathBuilder().path(String.valueOf(filmCategory.getId()))).rel("self").build();
                filmCategory.setLinks(Arrays.asList(self));
            }
            return Response.ok(categories).build();
        } catch (Exception ex) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DELETE
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteFilmCategoryById(@PathParam("id") Integer id) {
        FilmCategoryDTOResp deletedFilmCategoryDto = filmCategoryService.deleteById(id);
        if (deletedFilmCategoryDto == null) {
            throw new NotFoundException("FilmCategory with ID: " + id + " not found");
        }
        Link self = Link.fromUriBuilder(uriInfo.getAbsolutePathBuilder()).rel("self").build();
        deletedFilmCategoryDto.setLinks(Arrays.asList(self));
        return Response.ok(deletedFilmCategoryDto).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createFilmCategory(FilmCategoryDTOReq filmCategory) {
        filmCategory.setLastUpdate(Instant.now());
        FilmCategoryDTOResp createdFilmCategoryDto = filmCategoryService.save(filmCategory);
        if (createdFilmCategoryDto == null) {
            throw new InternalServerErrorException("Failed to create filmCategory");
        }
        Link self = Link.fromUriBuilder(uriInfo.getAbsolutePathBuilder()).rel("self").build();
        createdFilmCategoryDto.setLinks(Arrays.asList(self));
        return Response.ok(createdFilmCategoryDto).build();
    }

    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateFilmCategory(@PathParam("id") Integer id, FilmCategoryDTOReq filmCategoryDTOReq) {
        filmCategoryDTOReq.setLastUpdate(Instant.now());
        FilmCategoryDTOResp updatedFilmCategoryDto = filmCategoryService.update(id, filmCategoryDTOReq);
        if (updatedFilmCategoryDto == null) {
            throw new InternalServerErrorException("Failed to update filmCategoryDTOReq");
        }
        Link self = Link.fromUriBuilder(uriInfo.getAbsolutePathBuilder()).rel("self").build();
        updatedFilmCategoryDto.setLinks(Arrays.asList(self));
        return Response.ok(updatedFilmCategoryDto).build();
    }
}