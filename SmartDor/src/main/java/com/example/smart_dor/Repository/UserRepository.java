package com.example.smart_dor.Repository;

import com.example.smart_dor.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User , Long> {

  //  User findByMatricule(Long matricule);
     User findByMail(String mail);

}
