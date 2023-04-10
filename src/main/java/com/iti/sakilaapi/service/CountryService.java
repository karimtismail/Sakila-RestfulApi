package com.iti.sakilaapi.service;

import com.iti.sakilaapi.dto.CountryDto;
import com.iti.sakilaapi.entity.Country;
import com.iti.sakilaapi.repository.implementation.CountryRepositoryImpl;
import com.iti.sakilaapi.repository.interfaces.CountryRepository;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

public class CountryService {
    private final CountryRepository countryRepository;
    private final ModelMapper modelMapper;

    /**
     * Constructs a new ActorService instance with a default ActorRepository implementation and a new ModelMapper instance.
     */
    public CountryService() {
        this.countryRepository = new CountryRepositoryImpl();
        this.modelMapper = new ModelMapper();
    }

    public CountryDto findById(Short countryId) {
        Country country = countryRepository.findById(countryId);
        return modelMapper.map(country, CountryDto.class);
    }

    public List<CountryDto> findAll() {
        List<Country> countries = countryRepository.findAll();
        List<CountryDto> countryDtos = new ArrayList<>();
        for (Country country : countries) {
            countryDtos.add(modelMapper.map(country, CountryDto.class));
        }
        return countryDtos;
    }

    public CountryDto save(Country country) {
        Country savedCountry = countryRepository.save(country);
        return modelMapper.map(savedCountry, CountryDto.class);
    }

    public CountryDto update(Country country) {
        Country updatedCountry = countryRepository.update(country);
        return modelMapper.map(updatedCountry, CountryDto.class);
    }

    public CountryDto deleteById(Short countryId) {
        Country deleteCountry = countryRepository.deleteById(countryId);
        return modelMapper.map(deleteCountry, CountryDto.class);
    }
}
