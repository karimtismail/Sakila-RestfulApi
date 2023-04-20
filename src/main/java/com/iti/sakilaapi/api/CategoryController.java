package com.iti.sakilaapi.api;

import com.iti.sakilaapi.model.dto.requests.CategoryDTOReq;
import com.iti.sakilaapi.model.dto.response.CategoryDTOResp;
import com.iti.sakilaapi.service.CategoryService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;

import java.time.Instant;
import java.util.Arrays;
import java.util.List;

@Path("categories")
public class CategoryController {
    private final CategoryService categoryService = new CategoryService();
    private @Context UriInfo uriInfo;

    public CategoryController() {
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCategoryById(@PathParam("id") Integer id) {
        CategoryDTOResp categoryDTOResp = categoryService.findById(id);
        if (categoryDTOResp == null) {
            throw new NotFoundException("Category with ID: " + id + " Not Found");
        }
        Link self = Link.fromUriBuilder(uriInfo.getAbsolutePathBuilder()).rel("self").build();
        categoryDTOResp.setLinks(Arrays.asList(self));
        return Response.ok(categoryDTOResp).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllCategorys() {
        try {
            List<CategoryDTOResp> categories = categoryService.findAll();
            for (CategoryDTOResp category : categories) {
                Link self = Link.fromUriBuilder(uriInfo.getAbsolutePathBuilder().path(String.valueOf(category.getId()))).rel("self").build();
                category.setLinks(Arrays.asList(self));
            }
            return Response.ok(categories).build();
        } catch (Exception ex) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DELETE
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteCategoryById(@PathParam("id") Integer id) {
        CategoryDTOResp deletedCategoryDto = categoryService.deleteById(id);
        if (deletedCategoryDto == null) {
            throw new NotFoundException("Category with ID: " + id + " not found");
        }
        Link self = Link.fromUriBuilder(uriInfo.getAbsolutePathBuilder()).rel("self").build();
        deletedCategoryDto.setLinks(Arrays.asList(self));
        return Response.ok(deletedCategoryDto).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createCategory(CategoryDTOReq category) {
        category.setLastUpdate(Instant.now());
        CategoryDTOResp createdCategoryDto = categoryService.save(category);
        if (createdCategoryDto == null) {
            throw new InternalServerErrorException("Failed to create category");
        }
        Link self = Link.fromUriBuilder(uriInfo.getAbsolutePathBuilder()).rel("self").build();
        createdCategoryDto.setLinks(Arrays.asList(self));
        return Response.ok(createdCategoryDto).build();
    }

    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateCategory(@PathParam("id") Integer id, CategoryDTOReq categoryDTOReq) {
        categoryDTOReq.setLastUpdate(Instant.now());
        CategoryDTOResp updatedCategoryDto = categoryService.update(id, categoryDTOReq);
        if (updatedCategoryDto == null) {
            throw new InternalServerErrorException("Failed to update categoryDTOReq");
        }
        Link self = Link.fromUriBuilder(uriInfo.getAbsolutePathBuilder()).rel("self").build();
        updatedCategoryDto.setLinks(Arrays.asList(self));
        return Response.ok(updatedCategoryDto).build();
    }
}