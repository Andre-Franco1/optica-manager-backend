package com.optica.manager.domain.mappers;

import com.optica.manager.domain.entities.SaleItem;
import com.optica.manager.dto.SaleItemResponse;

public class SaleItemMapper {

    public static SaleItemResponse toSaleItemResponseDTO(SaleItem saleItem) {

        SaleItemResponse saleItemResponse = new SaleItemResponse(
                saleItem.getProduct().getId(),
                saleItem.getUnitPrice(),
                saleItem.getQuantity());
        return saleItemResponse;
    }

}
