package com.iti.sakilaapi.api;

import com.iti.sakilaapi.model.dto.requests.LanguageDTOReq;
import com.iti.sakilaapi.model.dto.response.LanguageDTOResp;
import com.iti.sakilaapi.service.LanguageService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;

import java.time.Instant;
import java.util.Arrays;
import java.util.List;

@Path("languages")
public class LanguageController {
    private final LanguageService languageService = new LanguageService();
    private @Context UriInfo uriInfo;

    public LanguageController() {
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getLanguageById(@PathParam("id") Integer id) {
        LanguageDTOResp languageDTOResp = languageService.findById(id);
        if (languageDTOResp == null) {
            throw new NotFoundException("Language with ID: " + id + " Not Found");
        }
        Link self = Link.fromUriBuilder(uriInfo.getAbsolutePathBuilder()).rel("self").build();
        languageDTOResp.setLinks(Arrays.asList(self));
        return Response.ok(languageDTOResp).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllLanguages() {
        try {
            List<LanguageDTOResp> languages = languageService.findAll();
            for (LanguageDTOResp language : languages) {
                Link self = Link.fromUriBuilder(uriInfo.getAbsolutePathBuilder().path(String.valueOf(language.getId()))).rel("self").build();
                language.setLinks(Arrays.asList(self));
            }
            return Response.ok(languages).build();
        } catch (Exception ex) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DELETE
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteLanguageById(@PathParam("id") Integer id) {
        LanguageDTOResp deletedLanguageDto = languageService.deleteById(id);
        if (deletedLanguageDto == null) {
            throw new NotFoundException("Language with ID: " + id + " not found");
        }
        Link self = Link.fromUriBuilder(uriInfo.getAbsolutePathBuilder()).rel("self").build();
        deletedLanguageDto.setLinks(Arrays.asList(self));
        return Response.ok(deletedLanguageDto).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createLanguage(LanguageDTOReq language) {
        language.setLastUpdate(Instant.now());
        LanguageDTOResp createdLanguageDto = languageService.save(language);
        if (createdLanguageDto == null) {
            throw new InternalServerErrorException("Failed to create language");
        }
        Link self = Link.fromUriBuilder(uriInfo.getAbsolutePathBuilder()).rel("self").build();
        createdLanguageDto.setLinks(Arrays.asList(self));
        return Response.ok(createdLanguageDto).build();
    }

    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateLanguage(@PathParam("id") Integer id, LanguageDTOReq languageDTOReq) {
        languageDTOReq.setLastUpdate(Instant.now());
        LanguageDTOResp updatedLanguageDto = languageService.update(id, languageDTOReq);
        if (updatedLanguageDto == null) {
            throw new InternalServerErrorException("Failed to update languageDTOReq");
        }
        Link self = Link.fromUriBuilder(uriInfo.getAbsolutePathBuilder()).rel("self").build();
        updatedLanguageDto.setLinks(Arrays.asList(self));
        return Response.ok(updatedLanguageDto).build();
    }
}