    package com.example.smart_dor.Entities;

    import jakarta.persistence.*;
    import lombok.AllArgsConstructor;
    import lombok.Getter;
    import lombok.NoArgsConstructor;
    import lombok.Setter;

    import java.io.Serializable;

    @Getter @Setter @Entity @AllArgsConstructor
    @NoArgsConstructor
    public class User implements Serializable {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        Long idUser ;
        String nom;
        String prenom;
        String mail;
        String password;
        @Enumerated(EnumType.STRING)
        Role role;
        @Column(name = "hedera_account_id", nullable = true, unique = true)
        private String hederaAccountId;

        @Column(name = "hedera_private_key", nullable = true)
        private String hederaPrivateKey;


    }
