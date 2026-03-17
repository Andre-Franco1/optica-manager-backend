package com.optica.manager.domain.services.validators;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.optica.manager.domain.entities.Sale;
import com.optica.manager.domain.entities.SaleItem;
import com.optica.manager.domain.repositories.ClientRepository;
import com.optica.manager.domain.repositories.UserRepository;
import com.optica.manager.domain.services.SaleItemService;
import com.optica.manager.domain.services.exceptions.BusinessException;

@Service
public class SaleValidator {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private SaleItemService saleItemService;

    public void validateSale(Sale sale) {
        saleItemService.validateSaleItemHasProduct(sale.getSaleItems());
        validateClientExists(sale.getClient().getId());
        validateUserExists(sale.getUser().getId());
        validateSaleHasItems(sale.getSaleItems());
        validateSalePriceIsPositive(sale.getTotalAmount());
        //validateIssueDateNotInFutureOrPast(sale.getIssueDate());
        validateEstimatedDeliveryDateAfterIssueDate(sale.getIssueDate(), sale.getEstimatedDeliveryDate());
        validateDeliveryDateIsNullOnCreation(sale.getDeliveryDate());

    }

    private void validateClientExists(long clientId) {
        if (!clientRepository.existsById(clientId)) {
            throw new BusinessException("Cliente não encontrado");
        }
    }

    private void validateUserExists(int userId) {
        if (!userRepository.existsById(userId)) {
            throw new BusinessException("Usuário não encontrado");
        }
    }

    private void validateSaleHasItems(List<SaleItem> saleItems) {
        if (saleItems == null || saleItems.isEmpty()) {
            throw new BusinessException("Uma venda deve ter ao menos um item");
        }
    }

    private void validateSalePriceIsPositive(BigDecimal totalAmount) {
        if (totalAmount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new BusinessException("O preço final deve ser positivo e maior que zero");
        }
    }
    
    /*
    private void validateIssueDateNotInFutureOrPast(LocalDateTime issueDate) {
        if (issueDate == null) {
        throw new BusinessException("A data de criação da venda é obrigatória");
    }
        
        LocalDate today = LocalDate.now();

        if (!issueDate.toLocalDate().equals(today)) {
            throw new BusinessException("A data de criação da venda deve ser no dia atual");
        }
    }
     */
    
    private void validateEstimatedDeliveryDateAfterIssueDate(LocalDateTime issueDate, LocalDate estimatedDeliveryDate) {
        if (estimatedDeliveryDate.isBefore(issueDate.toLocalDate())) {
            throw new BusinessException("A data de entrega estimada não pode ser anterior à data da venda");
        }
    }

    private void validateDeliveryDateIsNullOnCreation(LocalDate deliveryDate) {
        if (deliveryDate != null) {
            throw new BusinessException("A data de entrega deve ser 'null' ao criar a venda");
        }
    }
}
