package com.iti.sakilaapi.service;

import com.iti.sakilaapi.dto.CityDto;
import com.iti.sakilaapi.entity.City;
import com.iti.sakilaapi.repository.implementation.CityRepositoryImpl;
import com.iti.sakilaapi.repository.interfaces.CityRepository;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

public class CityService {
    private final CityRepository cityRepository;
    private final ModelMapper modelMapper;

    /**
     * Constructs a new ActorService instance with a default ActorRepository implementation and a new ModelMapper instance.
     */
    public CityService() {
        this.cityRepository = new CityRepositoryImpl();
        this.modelMapper = new ModelMapper();
    }

    public CityDto findById(Short cityId) {
        City city = cityRepository.findById(cityId);
        return modelMapper.map(city, CityDto.class);
    }

    public List<CityDto> findAll() {
        List<City> cities = cityRepository.findAll();
        List<CityDto> cityDtos = new ArrayList<>();
        for (City city : cities) {
            cityDtos.add(modelMapper.map(city, CityDto.class));
        }
        return cityDtos;
    }

    public CityDto save(City city) {
        City savedCity = cityRepository.save(city);
        return modelMapper.map(savedCity, CityDto.class);
    }

    public CityDto update(City city) {
        City updatedCity = cityRepository.update(city);
        return modelMapper.map(updatedCity, CityDto.class);
    }

    public CityDto deleteById(Short cityId) {
        City deleteCity = cityRepository.deleteById(cityId);
        return modelMapper.map(deleteCity, CityDto.class);
    }
}
