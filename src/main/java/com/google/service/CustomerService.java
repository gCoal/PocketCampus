package com.google.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(path = "/Customer")
public class CustomerService {
    private final Logger Log = LoggerFactory.getLogger(CustomerService.class);

    @RequestMapping(value = "/SignIn",method = RequestMethod.GET)
    public String SignIn(){
        return "haha";
    }



}
