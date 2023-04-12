package com.iti.sakilaapi.service;

import com.iti.sakilaapi.presentation.dto.AddressDto;
import com.iti.sakilaapi.data.entity.Address;
import com.iti.sakilaapi.data.repository.implementation.AddressRepositoryImpl;
import com.iti.sakilaapi.data.repository.interfaces.AddressRepository;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

public class AddressService {
    private final AddressRepository addressRepository;
    private final ModelMapper modelMapper;

    /**
     * Constructs a new ActorService instance with a default ActorRepository implementation and a new ModelMapper instance.
     */
    public AddressService() {
        this.addressRepository = new AddressRepositoryImpl();
        this.modelMapper = new ModelMapper();
    }

    public AddressDto findById(Short addressId) {
        Address address = addressRepository.findById(addressId);
        return modelMapper.map(address, AddressDto.class);
    }

    public List<AddressDto> findAll() {
        List<Address> addresses = addressRepository.findAll();
        List<AddressDto> addressDtos = new ArrayList<>();
        for (Address address : addresses) {
            addressDtos.add(modelMapper.map(address, AddressDto.class));
        }
        return addressDtos;
    }

    public AddressDto save(Address address) {
        Address savedAddress = addressRepository.save(address);
        return modelMapper.map(savedAddress, AddressDto.class);
    }

    public AddressDto update(Address address) {
        Address updatedAddress = addressRepository.update(address);
        return modelMapper.map(updatedAddress, AddressDto.class);
    }

    public AddressDto deleteById(Short addressId) {
        Address deleteAddress = addressRepository.deleteById(addressId);
        return modelMapper.map(deleteAddress, AddressDto.class);
    }
}
