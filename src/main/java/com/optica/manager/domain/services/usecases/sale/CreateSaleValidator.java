package com.optica.manager.domain.services.usecases.sale;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.optica.manager.domain.entities.Sale;
import com.optica.manager.domain.entities.SaleItem;
import com.optica.manager.domain.enums.CardBrand;
import com.optica.manager.domain.enums.PaymentMethod;
import com.optica.manager.domain.enums.SaleStatus;
import com.optica.manager.domain.repositories.ClientRepository;
import com.optica.manager.domain.repositories.UserRepository;
import com.optica.manager.domain.services.exceptions.BusinessException;

@Service
public class CreateSaleValidator {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ClientRepository clientRepository;

    public Sale validateSale(Sale sale) {

        validateClientExists(sale.getClient().getId());
        validateUserExists(sale.getUser().getId());
        validateSaleHasItems(sale.getSaleItems());

        validateSalePriceIsPositive(sale.getTotalAmount());
        validateSalePriceMatchesItems(sale.getTotalAmount(), sale.getSaleItems());
        validatePaymentRules(sale.getPaymentMethod(), sale.getCardBrand(), sale.getInstallments());
        validateIssueDateNotInFutureOrPast(sale.getIssueDate());
        validateEstimatedDeliveryDateAfterIssueDate(sale.getIssueDate(), sale.getEstimatedDeliveryDate());
        validateDeliveryDateIsNullOnCreation(sale.getDeliveryDate());

        validateSaleStatusIsPending(sale.getSaleStatus());

        return sale;
    }

    public void validateSaleItemHasProduct(List<SaleItem> saleItems) {
        for (SaleItem saleItem : saleItems) {
            if (saleItem.getProduct() == null) {
                throw new BusinessException("O item deve possuir um produto.");
            }
        }
    }

    private void validateClientExists(long clientId) {
        if (!clientRepository.existsById(clientId)) {
            throw new BusinessException("Cliente não encontrado.");
        }
    }

    private void validateUserExists(int userId) {
        if (!userRepository.existsById(userId)) {
            throw new BusinessException("Usuário não encontrado.");
        }
    }

    private void validateSaleHasItems(List<SaleItem> saleItems) {
        if (saleItems == null || saleItems.isEmpty()) {
            throw new BusinessException("Uma venda deve ter ao menos um item.");
        }
    }

    private void validateSalePriceIsPositive(BigDecimal totalAmount) {
        if (totalAmount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new BusinessException("O preço final deve ser positivo e maior que zero.");
        }
    }

    private void validateSalePriceMatchesItems(BigDecimal totalAmount, List<SaleItem> saleItems) {
        BigDecimal calculatedTotal = saleItems.stream().map(SaleItem::getPrice).reduce(BigDecimal.ZERO,
                BigDecimal::add);

        if (calculatedTotal.compareTo(totalAmount) != 0) {
            throw new BusinessException(
                    "O valor total da venda deve ser equivalente ao valor da soma do preço de cada item.");
        }
    }

    private void validatePaymentRules(PaymentMethod paymentMethod, CardBrand cardBrand, Integer installments) {
        if (paymentMethod == PaymentMethod.CREDIT_CARD) {
            if (cardBrand == null) {
                throw new BusinessException("A bandeira do cartão é necessária para pagamentos com cartão de crédito.");
            }
            if (installments == null) {
                throw new BusinessException("O número de parcelas é necessário para pagamentos com cartão de crédito.");
            }
            if (installments < 1 || installments > 12) {
                throw new BusinessException("O número de parcelas deve ser de 1 a 12.");
            }
        } else {
            if (cardBrand != null || installments != null) {
                throw new BusinessException(
                        "A bandeira do cartão e número de parcelas deve ser 'null' para pagamentos que não sejam feitos com cartão de crédito.");
            }
        }
    }

    private void validateIssueDateNotInFutureOrPast(LocalDate issueDate) {
        if (issueDate.isAfter(LocalDate.now()) || issueDate.isBefore(LocalDate.now())) {
            throw new BusinessException("A data de criação da venda deve ser no dia atual.");
        }
    }

    private void validateEstimatedDeliveryDateAfterIssueDate(LocalDate issueDate, LocalDate estimatedDeliveryDate) {
        if (issueDate.isAfter(estimatedDeliveryDate)) {
            throw new BusinessException("A data de entrega estimada deve ser depois da data de criação da venda.");
        }
    }

    private void validateDeliveryDateIsNullOnCreation(LocalDate deliveryDate) {
        if (deliveryDate != null) {
            throw new BusinessException("A data de entrega deve ser 'null' ao criar a venda.");
        }
    }

    private void validateSaleStatusIsPending(SaleStatus saleStatus) {
        if (saleStatus != SaleStatus.PENDING) {
            throw new BusinessException("A venda deve ser criada com o status 'pendente'.");
        }

    }
}
