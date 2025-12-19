package com.optica.manager.domain.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.optica.manager.domain.entities.Product;
import com.optica.manager.domain.entities.Sale;
import com.optica.manager.domain.entities.SaleItem;
import com.optica.manager.domain.mappers.SaleMapper;
import com.optica.manager.domain.repositories.ProductRepository;
import com.optica.manager.domain.repositories.SaleRepository;
import com.optica.manager.domain.services.usecases.sale.CreateSaleUseCase;
import com.optica.manager.dto.SaleItemRequest;
import com.optica.manager.dto.SaleRequest;
import com.optica.manager.dto.SaleResponse;

@Service
public class SaleService {

    @Autowired
    private CreateSaleUseCase createSaleUseCase;

    @Autowired
    private SaleRepository saleRepository;

    @Autowired
    private ProductRepository productRepository;
    
    public SaleResponse createSale(SaleRequest saleRequest) {

        Sale sale = createSaleUseCase.executeUseCase(SaleMapper.fromSaleRequestDTO(saleRequest));
        
        setProductInSaleItem(sale, saleRequest);
        createSaleUseCase.validateSaleItemHasProduct(sale.getSaleItems());

        saleRepository.save(sale);
        return SaleMapper.toSaleResponseDTO(sale);
    }

    private void setProductInSaleItem(Sale sale, SaleRequest saleRequest){
        for (int i = 0; i < sale.getSaleItems().size(); i++){
            SaleItem saleItem = sale.getSaleItems().get(i);
            SaleItemRequest saleItemReq = saleRequest.saleItems().get(i);

            Product product = productRepository.getReferenceById(saleItemReq.product().id());
            saleItem.setProduct(product);
        }
    }
}
