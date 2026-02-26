package com.optica.manager.domain.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.optica.manager.domain.entities.Frame;
import com.optica.manager.domain.entities.Sale;
import com.optica.manager.domain.entities.StockMovement;
import com.optica.manager.domain.enums.MovementType;
import com.optica.manager.domain.repositories.FrameRepository;
import com.optica.manager.domain.repositories.StockMovementRepository;
import com.optica.manager.domain.services.exceptions.BusinessException;
import com.optica.manager.dto.StockRequest;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class StockMovementService {

    @Autowired
    private StockMovementRepository stockMovementRepository;

    @Autowired
    private FrameRepository frameRepository;

    public void decreaseStockInSale(Frame frame, Integer quantity, Sale sale) {

        /*
         * log.info("Classe real: {}", product.getClass().getName());
         * if (!(product instanceof Frame frame)) {
         * return;
         * }
         */

        if (frame.getStockQuantity() < quantity) {
            throw new BusinessException("Estoque insuficiente");
        }

        Integer previousQuantity = frame.getStockQuantity();
        Integer newQuantity = previousQuantity - quantity;
        frame.setStockQuantity(newQuantity);

        stockMovementRepository.save(new StockMovement(
                frame,
                quantity,
                MovementType.SALE,
                previousQuantity,
                newQuantity,
                "",
                null, // TODO set current user
                sale));
    }

    @Transactional
    public void decreaseStock(Long id, StockRequest stockRequest) {

        Frame frame = frameRepository.findById(id).orElseThrow(() -> new BusinessException("Armação não encontrada"));
        Integer quantity = stockRequest.quantity();

        if (quantity == null || quantity <= 0) {
            throw new BusinessException("Quantidade inválida");
        }

        if (frame.getStockQuantity() < quantity) {
            throw new BusinessException("Estoque insuficiente");
        }

        String comment = stockRequest.comment();
        Integer previousQuantity = frame.getStockQuantity();
        Integer newQuantity = previousQuantity - quantity;
        frame.setStockQuantity(newQuantity);

        stockMovementRepository
                .save(new StockMovement(
                        frame,
                        quantity,
                        MovementType.EXIT,
                        previousQuantity,
                        newQuantity,
                        comment,
                        null,
                        null));
    }

    @Transactional
    public void increaseStock(Long id, StockRequest stockRequest) {

        Frame frame = frameRepository.findById(id).orElseThrow(() -> new BusinessException("Armação não encontrada"));
        Integer quantity = stockRequest.quantity();

        if (quantity == null || quantity <= 0) {
            throw new BusinessException("Quantidade inválida");
        }

        String comment = stockRequest.comment();
        Integer previousQuantity = frame.getStockQuantity();
        Integer newQuantity = previousQuantity + quantity;
        frame.setStockQuantity(newQuantity);

        stockMovementRepository.save(new StockMovement(frame,
                quantity,
                MovementType.ENTRY,
                previousQuantity,
                newQuantity,
                comment,
                null,
                null));
    }
}
