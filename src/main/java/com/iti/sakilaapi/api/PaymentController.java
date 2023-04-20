package com.iti.sakilaapi.api;

import com.iti.sakilaapi.model.dto.requests.PaymentDTOReq;
import com.iti.sakilaapi.model.dto.response.PaymentDTOResp;
import com.iti.sakilaapi.service.PaymentService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;

import java.time.Instant;
import java.util.Arrays;
import java.util.List;

@Path("payments")
public class PaymentController {
    private final PaymentService paymentService = new PaymentService();
    private @Context UriInfo uriInfo;

    public PaymentController() {
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPaymentById(@PathParam("id") Integer id) {
        PaymentDTOResp paymentDTOResp = paymentService.findById(id);
        if (paymentDTOResp == null) {
            throw new NotFoundException("Payment with ID: " + id + " Not Found");
        }
        Link self = Link.fromUriBuilder(uriInfo.getAbsolutePathBuilder()).rel("self").build();
        paymentDTOResp.setLinks(Arrays.asList(self));
        return Response.ok(paymentDTOResp).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllPayments() {
        try {
            List<PaymentDTOResp> payments = paymentService.findAll();
            for (PaymentDTOResp payment : payments) {
                Link self = Link.fromUriBuilder(uriInfo.getAbsolutePathBuilder().path(String.valueOf(payment.getId()))).rel("self").build();
                payment.setLinks(Arrays.asList(self));
            }
            return Response.ok(payments).build();
        } catch (Exception ex) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DELETE
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deletePaymentById(@PathParam("id") Integer id) {
        PaymentDTOResp deletedPaymentDto = paymentService.deleteById(id);
        if (deletedPaymentDto == null) {
            throw new NotFoundException("Payment with ID: " + id + " not found");
        }
        Link self = Link.fromUriBuilder(uriInfo.getAbsolutePathBuilder()).rel("self").build();
        deletedPaymentDto.setLinks(Arrays.asList(self));
        return Response.ok(deletedPaymentDto).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createPayment(PaymentDTOReq payment) {
        payment.setLastUpdate(Instant.now());
        payment.setPaymentDate(Instant.now());
        PaymentDTOResp createdPaymentDto = paymentService.save(payment);
        if (createdPaymentDto == null) {
            throw new InternalServerErrorException("Failed to create payment");
        }
        Link self = Link.fromUriBuilder(uriInfo.getAbsolutePathBuilder()).rel("self").build();
        createdPaymentDto.setLinks(Arrays.asList(self));
        return Response.ok(createdPaymentDto).build();
    }

    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updatePayment(@PathParam("id") Integer id, PaymentDTOReq paymentDTOReq) {
        paymentDTOReq.setLastUpdate(Instant.now());
        paymentDTOReq.setPaymentDate(Instant.now());
        PaymentDTOResp updatedPaymentDto = paymentService.update(id, paymentDTOReq);
        if (updatedPaymentDto == null) {
            throw new InternalServerErrorException("Failed to update paymentDTOReq");
        }
        Link self = Link.fromUriBuilder(uriInfo.getAbsolutePathBuilder()).rel("self").build();
        updatedPaymentDto.setLinks(Arrays.asList(self));
        return Response.ok(updatedPaymentDto).build();
    }
}