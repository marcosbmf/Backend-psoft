package br.com.edu.ufcg.cccfarma.api.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.validation.constraints.NotNull;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Entity
public class Conta implements Serializable {

    @Id
    private String username;

    @NotNull
    private String password;

    @Column(updatable = false)
    @NotNull
    private boolean admin;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public boolean isAdmin() {
        return admin;
    }

    @PrePersist
    public void setPassword() {
        this.password = (new BCryptPasswordEncoder()).encode(this.password);
    }
}
