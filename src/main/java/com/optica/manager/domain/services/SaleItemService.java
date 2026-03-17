package com.optica.manager.domain.services;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.optica.manager.domain.entities.Product;
import com.optica.manager.domain.entities.Sale;
import com.optica.manager.domain.entities.SaleItem;
import com.optica.manager.domain.repositories.ProductRepository;
import com.optica.manager.domain.services.exceptions.BusinessException;
import com.optica.manager.dto.SaleRequest;

@Service
public class SaleItemService {

    @Autowired
    private ProductRepository productRepository;

    public void attachProductsInSaleItems(Sale sale, SaleRequest saleRequest) {
        List<SaleItem> saleItems = saleRequest.saleItems().stream().map(request -> {
            Product product = productRepository.findById(request.productId())
                    .orElseThrow(() -> new BusinessException("Produto não encontrado"));

            SaleItem saleItem = new SaleItem();
            saleItem.setProduct(product);
            saleItem.setUnitPrice(request.price());
            saleItem.setQuantity(request.quantity());

            BigDecimal subtotal = saleItem.getUnitPrice()
                .multiply(BigDecimal.valueOf(saleItem.getQuantity()));

            saleItem.setSubtotal(subtotal);
            saleItem.setSale(sale);

            return saleItem;
        }).toList();

        sale.setSaleItems(saleItems);
    }

    public void validateSaleItemHasProduct(List<SaleItem> saleItems) {
        for (SaleItem saleItem : saleItems) {
            if (saleItem.getProduct() == null) {
                throw new BusinessException("O item deve possuir um produto");
            }
        }
    }
}
