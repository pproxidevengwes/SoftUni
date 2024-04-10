package org._17_json_.services;

import org.springframework.stereotype.Service;

@Service
public class PersonService {
    private AddressService addressService;

    public PersonService(AddressService addressService) {
        this.addressService = addressService;
    }
}
