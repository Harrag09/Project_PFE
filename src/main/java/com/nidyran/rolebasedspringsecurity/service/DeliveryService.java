package com.nidyran.rolebasedspringsecurity.service;

import com.nidyran.rolebasedspringsecurity.Exeption.DeliveryNotFoundException;
import com.nidyran.rolebasedspringsecurity.Exeption.RestaurantNotFoundException;
import com.nidyran.rolebasedspringsecurity.dao.entity.Category;
import com.nidyran.rolebasedspringsecurity.dao.entity.Delivery;
import com.nidyran.rolebasedspringsecurity.dao.entity.Restaurant;
import com.nidyran.rolebasedspringsecurity.dao.repository.DeliveryRepository;
import com.nidyran.rolebasedspringsecurity.service.model.category.CategoryDto;
import com.nidyran.rolebasedspringsecurity.service.model.delivery.AddDeliveryDTO;
import com.nidyran.rolebasedspringsecurity.service.model.delivery.DeliveryDTO;
import com.nidyran.rolebasedspringsecurity.service.model.restaurant.AddRestaurantDto;
import com.nidyran.rolebasedspringsecurity.service.model.restaurant.RestaurantDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Component
@RequiredArgsConstructor
@Service
public class DeliveryService {

    private final DeliveryRepository deliveryRepository;
    private final ModelMapper modelMapper;


    public AddDeliveryDTO createDelivery(AddDeliveryDTO addDeliveryDTO) {
        log.warn("Restaurant added by {}", SecurityContextHolder.getContext().getAuthentication().getName());
        Delivery delivery = modelMapper.map(addDeliveryDTO, Delivery.class);
        return modelMapper.map(deliveryRepository.save(delivery), AddDeliveryDTO.class);
    }

    public DeliveryDTO getDeliveryById(Long deliveryId) {
        Optional<Delivery> deliveryOptional = deliveryRepository.findById(deliveryId);
        if (deliveryOptional.isPresent()) {
            Delivery delivery = deliveryOptional.get();
            return modelMapper.map(delivery, DeliveryDTO.class);
        } else {
            throw new DeliveryNotFoundException(deliveryId);
        }
    }

    public List<DeliveryDTO> searchDelivery(String name) {
        List<Delivery> deliveries = deliveryRepository.findByNameContainingOrAddressContaining(name, name);
        return deliveries.stream().map(delivery -> modelMapper.map(delivery, DeliveryDTO.class)).collect(Collectors.toList());
    }

    public List<DeliveryDTO> getAllDeliver() {
        List<Delivery> deliveries = deliveryRepository.findAll();
        return deliveries.stream().map(delivery -> modelMapper.map(deliveries, DeliveryDTO.class)).collect(Collectors.toList());
    }




}
