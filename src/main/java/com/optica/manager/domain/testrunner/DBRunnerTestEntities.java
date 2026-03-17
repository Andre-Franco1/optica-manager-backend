package com.optica.manager.domain.testrunner;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.optica.manager.domain.entities.Client;
import com.optica.manager.domain.entities.Frame;
import com.optica.manager.domain.entities.Lens;
import com.optica.manager.domain.entities.Sale;
import com.optica.manager.domain.entities.SaleItem;
import com.optica.manager.domain.entities.User;
import com.optica.manager.domain.repositories.ClientRepository;
import com.optica.manager.domain.repositories.FrameRepository;
import com.optica.manager.domain.repositories.LensRepository;
import com.optica.manager.domain.repositories.SaleRepository;
import com.optica.manager.domain.repositories.UserRepository;

@Component
@Profile("runner")
public class DBRunnerTestEntities implements ApplicationRunner{
    
    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SaleRepository saleRepository;

    // @Autowired
    // private SaleItemRepository saleItemRepository;

    @Autowired
    private FrameRepository frameRepository;

    @Autowired
    private LensRepository lensRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {

        Client client = clientRepository.findById(2L).get();
        System.out.println(client);

        User user = userRepository.findById(1).get();
        System.out.println(user);
        
        Frame frame = frameRepository.findById(1L).get();
        System.out.println(frame);

        Lens lens = lensRepository.findById(2L).get();
        System.out.println(lens);

        Sale sale = new Sale();
        //sale.setIssueDate(LocalDate.of(2025, 12, 15));
        sale.setEstimatedDeliveryDate(LocalDate.of(2025, 12, 24));
        sale.setDeliveryDate(null);
        //sale.setPaymentMethod(PaymentMethod.CREDIT_CARD);
        //sale.setCardBrand(CardBrand.ELO);
        //sale.setInstallments(10);
        //sale.setSaleStatus(DeliveryStatus.PENDING);
        sale.setComments("Comentários sobre a venda");
        sale.setClient(client);
        sale.setUser(user);

        SaleItem saleItem1 = new SaleItem();
        saleItem1.setProduct(frame);
        saleItem1.setUnitPrice(BigDecimal.valueOf(150.00));

        SaleItem saleItem2 = new SaleItem();
        saleItem2.setProduct(lens);
        saleItem2.setUnitPrice(BigDecimal.valueOf(420.00));

        sale.addSaleItem(saleItem1);
        sale.addSaleItem(saleItem2);

        BigDecimal total = BigDecimal.ZERO;

        for (SaleItem saleItem : sale.getSaleItems()) {
            total = total.add(saleItem.getUnitPrice());
        }
        
        sale.setTotalAmount(total);

        saleRepository.save(sale);
    }
}
