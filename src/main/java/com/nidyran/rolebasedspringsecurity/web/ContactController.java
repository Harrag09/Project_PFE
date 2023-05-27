package com.nidyran.rolebasedspringsecurity.web;

import com.nidyran.rolebasedspringsecurity.service.ContactService;
import com.nidyran.rolebasedspringsecurity.service.model.contact.AddContactDto;
import com.nidyran.rolebasedspringsecurity.service.model.contact.ContactDto;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Tag(name = "Contact Resource")
@RequestMapping("/Contact-management")
public class ContactController {

    private final ContactService contactService;


    @PostMapping("/Contact/createContact")
    public ResponseEntity<AddContactDto> createContact(@RequestBody AddContactDto addContactDto) {
        AddContactDto createdContact = contactService.createContact(addContactDto);
        return ResponseEntity.ok(createdContact);
    }

    @GetMapping("/Contact/getByRestaurantId/{restaurantId}")
    public ResponseEntity<ContactDto> getContactByRestaurantId(@PathVariable long restaurantId) {
        ContactDto contact = contactService.getContactByRestaurantId(restaurantId);
        return ResponseEntity.ok(contact);
    }



}
