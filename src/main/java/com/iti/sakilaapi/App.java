package com.iti.sakilaapi;

import com.iti.sakilaapi.service.CustomerService;
import com.iti.sakilaapi.service.FilmActorService;

public class App {
    public static void main(String[] args) {

        FilmActorService customerService = new FilmActorService();
        System.out.println(customerService.findById(2));

    }

}