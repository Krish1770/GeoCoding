package com.example.geoCoding.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.security.core.CredentialsContainer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@EqualsAndHashCode(callSuper = true)
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Company extends Address implements UserDetails, CredentialsContainer {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String companyId;

    @Length(max = 50,message = "size of companyName must be less than 50")
    @NotBlank(message = "companyName must not be empty")
    private String companyName;

    @Size(min = 15,max = 15,message = "length of gst must be 15")
    private String gstNumber;

    @Size(max = 10,min = 10,message = "length of phoneNumber must be 10")
    private String phoneNumber;

    private String password;


    @Override
    public void eraseCredentials() {

    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return companyName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
