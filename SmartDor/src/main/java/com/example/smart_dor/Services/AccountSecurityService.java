package com.example.smart_dor.Services;


import com.example.smart_dor.Dto.AccountDetails;
import com.example.smart_dor.Dto.AccountLoginDto;
import com.example.smart_dor.Entities.User;
import com.example.smart_dor.Repository.UserRepository;
import com.hedera.hashgraph.sdk.AccountId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;


@Service
public class AccountSecurityService implements UserDetailsService {
    @Autowired
    private UserRepository userInfoRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtService jwtService;

    @Autowired
    private HederaService hederaService;
    @Override
    public AccountDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userInfoEmail = Optional.ofNullable(userInfoRepository.findByMail(username));
        return userInfoEmail.map(AccountDetails::new).orElseThrow(()-> new UsernameNotFoundException("User not found"+username));

    }

    public AccountLoginDto loginSucces(String username){
        AccountLoginDto accountLoginDto = new AccountLoginDto();
        //Long id= Long.valueOf(username);
        User UserLogin= userInfoRepository.findByMail(username);
        if(UserLogin!= null){
            accountLoginDto.setMatricule(UserLogin.getIdUser());
            accountLoginDto.setRole(UserLogin.getRole());
            accountLoginDto.setPrenom(UserLogin.getPrenom());
            accountLoginDto.setNom(UserLogin.getNom());
            accountLoginDto.setPassword(UserLogin.getPassword());
            accountLoginDto.setMail(UserLogin.getMail());
            accountLoginDto.setToken(jwtService.generateToken(username));

        }
        return accountLoginDto;
    }

    public String addUser(User userInfo){
        userInfo.setPassword(passwordEncoder.encode(userInfo.getPassword()));

        userInfoRepository.save(userInfo);
        return "User added successfully";
    }
    public List<User> getAllUser(){
        return userInfoRepository.findAll();
    }
    public User getUser(Long id){
        return userInfoRepository.findById(id).get();
    }




}