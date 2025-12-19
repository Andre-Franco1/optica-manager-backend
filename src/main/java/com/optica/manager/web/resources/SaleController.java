package com.optica.manager.web.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.optica.manager.domain.services.SaleService;
import com.optica.manager.dto.SaleRequest;
import com.optica.manager.dto.SaleResponse;

@RestController
@RequestMapping("sales")
public class SaleController {

    @Autowired
    private SaleService saleService;

    @PostMapping
    public ResponseEntity<SaleResponse> createSale(
            @Validated @RequestBody SaleRequest saleRequest) {

        var saleResponse = saleService.createSale(saleRequest);
        var location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(saleResponse.id())
                .toUri();

        return ResponseEntity.created(location).body(saleResponse);
    }

}
