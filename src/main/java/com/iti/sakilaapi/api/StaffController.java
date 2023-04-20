package com.iti.sakilaapi.api;

import com.iti.sakilaapi.model.dto.requests.StaffDTOReq;
import com.iti.sakilaapi.model.dto.response.StaffDTOResp;
import com.iti.sakilaapi.service.StaffService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;

import java.time.Instant;
import java.util.Arrays;
import java.util.List;

@Path("staffs")
public class StaffController {
    private final StaffService staffService = new StaffService();
    private @Context UriInfo uriInfo;

    public StaffController() {
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getStaffById(@PathParam("id") Integer id) {
        StaffDTOResp staffDTOResp = staffService.findById(id);
        if (staffDTOResp == null) {
            throw new NotFoundException("Staff with ID: " + id + " Not Found");
        }
        Link self = Link.fromUriBuilder(uriInfo.getAbsolutePathBuilder()).rel("self").build();
        staffDTOResp.setLinks(Arrays.asList(self));
        return Response.ok(staffDTOResp).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllStaffs() {
        try {
            List<StaffDTOResp> staffs = staffService.findAll();
            for (StaffDTOResp staff : staffs) {
                Link self = Link.fromUriBuilder(uriInfo.getAbsolutePathBuilder().path(String.valueOf(staff.getId()))).rel("self").build();
                staff.setLinks(Arrays.asList(self));
            }
            return Response.ok(staffs).build();
        } catch (Exception ex) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DELETE
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteStaffById(@PathParam("id") Integer id) {
        StaffDTOResp deletedStaffDto = staffService.deleteById(id);
        if (deletedStaffDto == null) {
            throw new NotFoundException("Staff with ID: " + id + " not found");
        }
        Link self = Link.fromUriBuilder(uriInfo.getAbsolutePathBuilder()).rel("self").build();
        deletedStaffDto.setLinks(Arrays.asList(self));
        return Response.ok(deletedStaffDto).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createStaff(StaffDTOReq staff) {
        staff.setLastUpdate(Instant.now());
        StaffDTOResp createdStaffDto = staffService.save(staff);
        if (createdStaffDto == null) {
            throw new InternalServerErrorException("Failed to create staff");
        }
        Link self = Link.fromUriBuilder(uriInfo.getAbsolutePathBuilder()).rel("self").build();
        createdStaffDto.setLinks(Arrays.asList(self));
        return Response.ok(createdStaffDto).build();
    }

    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateStaff(@PathParam("id") Integer id, StaffDTOReq staffDTOReq) {
        staffDTOReq.setLastUpdate(Instant.now());
        StaffDTOResp updatedStaffDto = staffService.update(id, staffDTOReq);
        if (updatedStaffDto == null) {
            throw new InternalServerErrorException("Failed to update staffDTOReq");
        }
        Link self = Link.fromUriBuilder(uriInfo.getAbsolutePathBuilder()).rel("self").build();
        updatedStaffDto.setLinks(Arrays.asList(self));
        return Response.ok(updatedStaffDto).build();
    }
}