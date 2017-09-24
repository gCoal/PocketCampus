package com.google.loader.security;

import com.google.entity.customer.AuthorityEntity;
import com.google.entity.customer.CustomerEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class CustomDetails implements UserDetails
{
    private CustomerEntity customerEntity;

    public CustomDetails(CustomerEntity CustomerEntity)
    {
        this.customerEntity = CustomerEntity;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities()
    {
        List<SimpleGrantedAuthority> authorities = new LinkedList<>();
        for(AuthorityEntity authority: customerEntity.getAuthorities())
            authorities.add(new SimpleGrantedAuthority(authority.getRole()));
        return authorities;
    }

    @Override
    public String getPassword()
    {
        return customerEntity.getPasswd();
    }

    @Override
    public String getUsername()
    {
        return customerEntity.getUsername();
    }

    @Override
    public boolean isAccountNonExpired()
    {
        return customerEntity.getIsAccountNonExpired() != 0;
    }

    @Override
    public boolean isAccountNonLocked()
    {
        return customerEntity.getIsAccountNonLocked() !=0;
    }

    @Override
    public boolean isCredentialsNonExpired()
    {
        return customerEntity.getIsCredentialsNonExpired()!=0;
    }

    @Override
    public boolean isEnabled()
    {
        return customerEntity.getIsEnabled()!=0;
    }
}
