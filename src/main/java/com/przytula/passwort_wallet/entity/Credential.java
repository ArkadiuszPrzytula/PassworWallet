package com.przytula.passwort_wallet.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@Entity
@Getter
@Setter
@Table(name = "crediential")
public class Credential {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "address")
    private String webAddress;

    @Column(name = "login")
    private String webLogin;

    @Column(name = "password")
    private String webPassword;

    @Column(name = "description")
    private String webDescription;


    public Credential(String webAddress, String webLogin, String webPassword) {
        this.id = null;
        this.webAddress = webAddress;
        this.webLogin = webLogin;
        this.webPassword = webPassword;
    }

    public Credential(String webAddress, String webLogin, String webPassword, String webDescription) {
        this.webAddress = webAddress;
        this.webLogin = webLogin;
        this.webPassword = webPassword;
        this.webDescription = webDescription;
    }


}
