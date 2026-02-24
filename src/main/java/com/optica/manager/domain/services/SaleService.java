package com.optica.manager.domain.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.optica.manager.domain.entities.Frame;
import com.optica.manager.domain.entities.Product;
import com.optica.manager.domain.entities.Sale;
import com.optica.manager.domain.entities.SaleItem;
import com.optica.manager.domain.enums.SaleStatus;
import com.optica.manager.domain.mappers.SaleMapper;
import com.optica.manager.domain.repositories.ProductRepository;
import com.optica.manager.domain.repositories.SaleRepository;
import com.optica.manager.domain.services.exceptions.BusinessException;
import com.optica.manager.domain.services.usecases.sale.CreateSaleValidator;
import com.optica.manager.dto.request.SaleItemRequest;
import com.optica.manager.dto.request.SaleRequest;
import com.optica.manager.dto.response.SaleResponse;

@Service
public class SaleService {

    @Autowired
    private CreateSaleValidator createSaleUseCase;

    @Autowired
    private StockMovementService stockMovementService;

    @Autowired
    private SaleRepository saleRepository;

    @Autowired
    private ProductRepository productRepository;

    @Transactional(readOnly = true)
    public Page<SaleResponse> findSales(int page, int size, SaleStatus status) {
        var pageRequest = PageRequest.of(page, size);
        var pageSale = saleRepository.findAllBySaleStatus(status, pageRequest);
        return pageSale.map(s -> SaleMapper.toSaleResponseDTO(s));
    }

    @Transactional
    public SaleResponse createSale(SaleRequest saleRequest) {

        Sale sale = createSaleUseCase.validateSale(SaleMapper.fromSaleRequestDTO(saleRequest));

        setProductInSaleItem(sale, saleRequest);
        createSaleUseCase.validateSaleItemHasProduct(sale.getSaleItems());

        saleRepository.save(sale);

        for (SaleItem saleItem : sale.getSaleItems()) {
            if (saleItem.getProduct() instanceof Frame frame) {
                stockMovementService.decreaseStockInSale(frame, 1, sale); // TODO change quantity value when sale items accept quantity too
            }
        }
        return SaleMapper.toSaleResponseDTO(sale);
    }

    private void setProductInSaleItem(Sale sale, SaleRequest saleRequest) {
        for (int i = 0; i < sale.getSaleItems().size(); i++) {
            SaleItem saleItem = sale.getSaleItems().get(i);
            SaleItemRequest saleItemReq = saleRequest.saleItems().get(i);

            Product product = productRepository.findById(saleItemReq.product().id())
                    .orElseThrow(() -> new BusinessException("Produto não encontrado"));

            saleItem.setProduct(product);
        }
    }
}
