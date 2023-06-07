package com.nidyran.rolebasedspringsecurity.service;

import com.nidyran.rolebasedspringsecurity.Exeption.ContactNotFoundException;
import com.nidyran.rolebasedspringsecurity.dao.entity.Contact;
import com.nidyran.rolebasedspringsecurity.dao.repository.ContactRepository;
import com.nidyran.rolebasedspringsecurity.service.model.contact.AddContactDto;
import com.nidyran.rolebasedspringsecurity.service.model.contact.ContactDto;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;


@Service
@Transactional
@RequiredArgsConstructor
public class ContactService {

    private final ContactRepository contactRepository;
    private final ModelMapper modelMapper;

    public AddContactDto createContact(AddContactDto addContactDto){
        Contact contact = modelMapper.map(addContactDto,Contact.class);
        Contact saveContact = contactRepository.save(contact);
        return modelMapper.map(saveContact,AddContactDto.class);
    }


    public ContactDto getContactByRestaurantId(long restaurantId) {
        Contact contact = contactRepository.findByRestaurantId(restaurantId);
        if (contact == null) {
            throw new ContactNotFoundException(restaurantId);
        }
        return modelMapper.map(contact, ContactDto.class);
    }



}
