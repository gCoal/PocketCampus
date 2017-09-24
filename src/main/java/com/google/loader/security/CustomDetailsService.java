package com.google.loader.security;

import com.google.entity.customer.CustomerEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
public class CustomDetailsService implements UserDetailsService
{
    private static Logger log = LoggerFactory.getLogger(CustomDetailsService.class);

    @Resource
    private com.google.repository.CustomerRepository CustomerRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {
        log.debug("username: " + username);
        CustomerEntity customerEntity = CustomerRepository.findOneByUsername(username);
        log.debug("authorities: " + customerEntity.getAuthorities().size() + "");
        return new CustomDetails(customerEntity);
    }
}
