package by.trubetski.quick.solution.services.impl;

import by.trubetski.quick.solution.exception.DeliveryNotFoundException;
import by.trubetski.quick.solution.models.Delivery;
import by.trubetski.quick.solution.repositories.DeliveryRepositories;
import by.trubetski.quick.solution.services.DeliveryServices;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class DeliveryServicesImpl implements DeliveryServices {
    private final DeliveryRepositories deliveryRepositories;
    @Override
    @Transactional
    public void updateStatus(String status, int id) {
        Optional<Delivery> deliveryInfo = deliveryRepositories.findById(id);
        if(deliveryInfo.isPresent()){
            Delivery delivery = deliveryInfo.get();
            delivery.setStatus(status);
            deliveryRepositories.save(delivery);
        } else {
            log.error("delivery not found by id " + id);
            throw new DeliveryNotFoundException("delivery not found " + id);
        }
    }
}
