package com.optica.manager.web.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.optica.manager.domain.services.pdf.ServiceOrderPdfService;

@RestController
@RequestMapping("/sales")
public class ServiceOrderController {

    @Autowired
    private ServiceOrderPdfService serviceOrderPdfService;

    @GetMapping("/{saleId}/serviceorder/pdf")
    public ResponseEntity<byte[]> gerarServiceOrderPdf(@PathVariable Long saleId) {

        byte[] pdf = serviceOrderPdfService.generate(saleId);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "inline; filename=OS-" + saleId + ".pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .body(pdf);
    }
}
