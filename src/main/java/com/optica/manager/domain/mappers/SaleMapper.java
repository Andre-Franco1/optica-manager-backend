package com.optica.manager.domain.mappers;

import java.util.List;

import org.springframework.beans.BeanUtils;

import com.optica.manager.domain.entities.Client;
import com.optica.manager.domain.entities.Sale;
import com.optica.manager.domain.entities.SaleItem;
import com.optica.manager.domain.entities.User;
import com.optica.manager.dto.IntegerDTO;
import com.optica.manager.dto.LongDTO;
import com.optica.manager.dto.SaleItemResponse;
import com.optica.manager.dto.SaleRequest;
import com.optica.manager.dto.SaleResponse;

public class SaleMapper {

    public static SaleResponse toSaleResponseDTO(Sale sale) {

        List<SaleItemResponse> saleItems = sale.getSaleItems().stream().map(saleItem -> new SaleItemResponse(
            new LongDTO(saleItem.getProduct().getId()),
            saleItem.getPrice()
        )).toList();

        return new SaleResponse(
            sale.getId(),
            sale.getIssueDate(),
            sale.getEstimatedDeliveryDate(),
            sale.getDeliveryDate(),
            sale.getTotalAmount(),
            sale.getPaymentMethod(),
            sale.getCardBrand(),
            sale.getInstallments(),
            sale.getComments(),
            sale.getSaleStatus(),
            sale.getClient().getId(),
            sale.getClient().getCpf(),
            sale.getClient().getName(),
            new IntegerDTO(sale.getUser().getId()),
            saleItems
        );
    }

    
    public static Sale fromSaleRequestDTO(SaleRequest saleRequest) {
        Sale sale = new Sale();

        BeanUtils.copyProperties(saleRequest, sale);
        sale.setClient(new Client(saleRequest.client().id()));
        sale.setUser(new User(saleRequest.user().id()));
        
        List<SaleItem> saleItems = saleRequest.saleItems().stream().map(SaleItemRequest -> {
            SaleItem saleItem = new SaleItem();
            saleItem.setPrice(SaleItemRequest.price());
            saleItem.setSale(sale);

            return saleItem;
        }).toList();

        sale.setSaleItems(saleItems);

        return sale;
    }

}
