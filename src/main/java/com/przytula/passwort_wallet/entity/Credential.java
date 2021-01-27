package com.przytula.passwort_wallet.entity;

import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Entity
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
