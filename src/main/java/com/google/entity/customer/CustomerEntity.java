package com.google.entity.customer;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

@Entity
@Table(name = "customer", schema = "db01", catalog = "db01")
public class CustomerEntity {
    private String id;
    private String username;
    private String passwd;
    private byte isAccountNonExpired;
    private byte isAccountNonLocked;
    private byte isEnabled;
    private byte isCredentialsNonExpired;

    List<AuthorityEntity> authorities=new LinkedList<>();

    @Id
    @Column(name = "id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "username")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Basic
    @Column(name = "passwd")
    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    @Basic
    @Column(name = "isAccountNonExpired")
    public byte getIsAccountNonExpired() {
        return isAccountNonExpired;
    }

    public void setIsAccountNonExpired(byte isAccountNonExpired) {
        this.isAccountNonExpired = isAccountNonExpired;
    }

    @Basic
    @Column(name = "isAccountNonLocked")
    public byte getIsAccountNonLocked() {
        return isAccountNonLocked;
    }

    public void setIsAccountNonLocked(byte isAccountNonLocked) {
        this.isAccountNonLocked = isAccountNonLocked;
    }

    @Basic
    @Column(name = "isEnabled")
    public byte getIsEnabled() {
        return isEnabled;
    }

    public void setIsEnabled(byte isEnabled) {
        this.isEnabled = isEnabled;
    }

    @Basic
    @Column(name = "isCredentialsNonExpired")
    public byte getIsCredentialsNonExpired() {
        return isCredentialsNonExpired;
    }

    public void setIsCredentialsNonExpired(byte isCredentialsNonExpired) {
        this.isCredentialsNonExpired = isCredentialsNonExpired;
    }

    @OneToMany(cascade = {CascadeType.ALL}, fetch=FetchType.LAZY)
    @JoinColumn(name = "custom_id", referencedColumnName = "id", unique = true)
    public List<AuthorityEntity> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(List<AuthorityEntity> authorities) {
        this.authorities = authorities;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CustomerEntity that = (CustomerEntity) o;

        if (isAccountNonExpired != that.isAccountNonExpired) return false;
        if (isAccountNonLocked != that.isAccountNonLocked) return false;
        if (isEnabled != that.isEnabled) return false;
        if (isCredentialsNonExpired != that.isCredentialsNonExpired) return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (username != null ? !username.equals(that.username) : that.username != null) return false;
        if (passwd != null ? !passwd.equals(that.passwd) : that.passwd != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (username != null ? username.hashCode() : 0);
        result = 31 * result + (passwd != null ? passwd.hashCode() : 0);
        result = 31 * result + (int) isAccountNonExpired;
        result = 31 * result + (int) isAccountNonLocked;
        result = 31 * result + (int) isEnabled;
        result = 31 * result + (int) isCredentialsNonExpired;
        return result;
    }
}
