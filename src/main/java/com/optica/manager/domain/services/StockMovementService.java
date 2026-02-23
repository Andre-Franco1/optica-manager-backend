package com.optica.manager.domain.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.optica.manager.domain.entities.Frame;
import com.optica.manager.domain.entities.Product;
import com.optica.manager.domain.entities.StockMovement;
import com.optica.manager.domain.enums.MovementType;
import com.optica.manager.domain.repositories.FrameRepository;
import com.optica.manager.domain.repositories.StockMovementRepository;
import com.optica.manager.domain.services.exceptions.BusinessException;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class StockMovementService {
    
    @Autowired
    private StockMovementRepository stockMovementRepository;

    @Autowired
    private FrameRepository frameRepository;

    public void decreaseStockInSale(Product product, Integer quantity) {

        //log.info("Classe real: {}", product.getClass().getName());
        if (!(product instanceof Frame frame)) {
           return;
        }

        if (frame.getStockQuantity() < quantity) {
            throw new BusinessException("Estoque insuficiente");
        }

        frame.setStockQuantity(frame.getStockQuantity() - quantity);

        stockMovementRepository.save(new StockMovement(product, quantity, MovementType.EXIT));
    }

    @Transactional
    public void decreaseStock(Long id, Integer quantity) {

        Frame frame = frameRepository.findById(id).orElseThrow(() -> new BusinessException("Armação não encontrada"));

        if (frame.getStockQuantity() < quantity) {
            throw new BusinessException("Estoque insuficiente");
        }

        frame.setStockQuantity(frame.getStockQuantity() - quantity);

        stockMovementRepository.save(new StockMovement(frame, quantity, MovementType.EXIT));
    }

    @Transactional
    public void increaseStock(Long id, Integer quantity) {

        Frame frame = frameRepository.findById(id).orElseThrow(() -> new BusinessException("Armação não encontrada"));

        if (frame.getStockQuantity() < 0) {
            throw new BusinessException("Inconsistência no estoque");
        }

        frame.setStockQuantity(frame.getStockQuantity() + quantity);

        stockMovementRepository.save(new StockMovement(frame, quantity, MovementType.ENTRY));
    }
}
