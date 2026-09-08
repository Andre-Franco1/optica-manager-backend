package com.optica.manager.domain.services;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.optica.manager.domain.entities.Frame;
import com.optica.manager.domain.entities.Sale;
import com.optica.manager.domain.entities.SaleItem;
import com.optica.manager.domain.entities.User;
import com.optica.manager.domain.enums.DeliveryStatus;
import com.optica.manager.domain.mappers.SaleMapper;
import com.optica.manager.domain.repositories.SaleRepository;
import com.optica.manager.domain.services.validators.SaleValidator;
import com.optica.manager.dto.SaleRequest;
import com.optica.manager.dto.SaleResponse;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class SaleService {

    @Autowired
    private SaleValidator createSaleUseCase;

    @Autowired
    private StockMovementService stockMovementService;

    @Autowired
    private SaleRepository saleRepository;

    @Autowired
    private SaleItemService saleItemService;

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private TenantService tenantService;

    @Transactional(readOnly = true)
    public Page<SaleResponse> findSales(int page, int size, DeliveryStatus status) {
        var pageRequest = PageRequest.of(page, size);
        Integer unitId = tenantService.getUnitId();
        var pageSale = saleRepository.findAllByDeliveryStatusAndUnitId(status, pageRequest, unitId);
        return pageSale.map(s -> SaleMapper.toSaleResponseDTO(s));
    }

    @Transactional
    public SaleResponse createSale(SaleRequest saleRequest) {

        Sale sale = SaleMapper.fromSaleRequestDTO(saleRequest);
        User user = tenantService.getAuthenticatedUser();

        sale.setUser(user);
        sale.setUnit(user.getUnit());

        sale.setIssueDate(LocalDateTime.now());
        
        if (sale.getDiscountPercentage() == null) {
            sale.setDiscountPercentage(BigDecimal.ZERO);
        }

        saleItemService.attachProductsInSaleItems(sale, saleRequest);
        sale.setSubtotal(calculateSubtotalValue(sale.getSaleItems()));
        sale.setTotalAmount(calculateDiscountedSubtotal(sale.getSubtotal(), sale.getDiscountPercentage()));
        
        paymentService.processPayments(sale, saleRequest.payments());

        createSaleUseCase.validateSale(sale);

        saleRepository.save(sale);

        for (SaleItem saleItem : sale.getSaleItems()) {
            if (saleItem.getProduct() instanceof Frame frame) {
                stockMovementService.decreaseStockInSale(frame, saleItem.getQuantity(), sale);
            }
        }
        return SaleMapper.toSaleResponseDTO(sale);
    }

    private BigDecimal calculateSubtotalValue(List<SaleItem> saleItems) {
        BigDecimal subtotal = BigDecimal.ZERO;

        for (SaleItem saleItem : saleItems) {
            subtotal = subtotal.add(saleItem.getSubtotal());
        }
        return subtotal;
    }

    private BigDecimal calculateDiscountedSubtotal(BigDecimal subtotal, BigDecimal discountPercentage) {
        BigDecimal discountAmount = subtotal.multiply(
                discountPercentage.divide(BigDecimal.valueOf(100), 4, RoundingMode.HALF_UP));

        return subtotal.subtract(discountAmount);
    }

}
