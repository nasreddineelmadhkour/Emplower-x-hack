package com.example.smart_dor;

import com.example.smart_dor.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class SmartDorApplication implements CommandLineRunner {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    UserRepository userRepository;

    public static void main(String[] args) {
        SpringApplication.run(SmartDorApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
/*
        Currency currency1 = new Currency();
        currency1.setId(1L);
        currency1.setName("USD");

        Currency currency2 = new Currency();
        currency2.setId(2L);
        currency2.setName("EUR");

        Currency currency3 = new Currency();
        currency3.setId(3L);
        currency3.setName("TND");

        currencyRepository.save(currency1);
        currencyRepository.save(currency2);
        currencyRepository.save(currency3);

        Officer officer1 = new Officer();
        officer1.setId(1L);
        officer1.setName("Agence Ariana");

        Officer officer2 = new Officer();
        officer2.setId(2L);
        officer2.setName("Agence Centre ville");

        Officer officer3 = new Officer();
        officer3.setId(3L);
        officer3.setName("Agence Ben arous");

        officerRepository.save(officer1);
        officerRepository.save(officer2);
        officerRepository.save(officer3);

        Country country1 = new Country();
        country1.setId(1L);
        country1.setName("TN");

        Country country2 = new Country();
        country2.setId(2L);
        country2.setName("FR");

        Country country3 = new Country();
        country3.setId(3L);
        country3.setName("US");

        countryRepository.save(country1);
        countryRepository.save(country2);
        countryRepository.save(country3);


        User user = new User();
        user.setMail("employe@employe.employe");
        user.setMatricule(112233L);
        user.setNom("Madhkour");
        user.setPrenom("Nasreddine");
        user.setRole(Role.EMPLOYE);
        user.setPassword(passwordEncoder.encode("112233"));
        userRepository.save(user);


        Officer officer = officerRepository.findById(1L).orElse(null);
        if(officer!=null){
            User user1 = userRepository.findById(112233L).orElse(null);
            if(user1!=null) {
                officer.getEmployes().add(user1);
                officerRepository.save(officer);
            }
        }
*/
        System.out.println("Inserted persons into the database.");
    }
}
