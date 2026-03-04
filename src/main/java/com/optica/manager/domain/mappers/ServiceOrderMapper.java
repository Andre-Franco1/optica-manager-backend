package com.optica.manager.domain.mappers;

import java.util.List;

import com.optica.manager.domain.entities.Sale;
import com.optica.manager.dto.SaleItemPdfDTO;
import com.optica.manager.dto.ServiceOrderPdfDTO;

public class ServiceOrderMapper {

    public static ServiceOrderPdfDTO toServiceOrderDto(Sale sale) {

        List<SaleItemPdfDTO> items = sale.getSaleItems()
                .stream().map(item -> new SaleItemPdfDTO(
                        item.getPrice(),
                        item.getProduct().getId(),
                        item.getProduct().getName()))
                .toList();

        return new ServiceOrderPdfDTO(
                sale.getId(),
                sale.getClient().getId(),
                sale.getClient().getName(),
                sale.getIssueDate(),
                sale.getSaleStatus(),
                sale.getEstimatedDeliveryDate(),
                sale.getTotalAmount(),
                sale.getUser().getName(),
                sale.getUnit().getName(),
                sale.getUnit().getAddress(),
                sale.getUnit().getLogoPath(),
                items);
    }

     /*
     * sale.seller()
     * sale.deliveryStatus()
     * sale.client.prescription...
     
     * sale.paymentStatus()
     * sale.List<Installment>
     sale.cardBrand
     * sale.entryPayment
     * sale.resultPrice (totalAmount - entryPayment)
     * item.quantity
     */

}
