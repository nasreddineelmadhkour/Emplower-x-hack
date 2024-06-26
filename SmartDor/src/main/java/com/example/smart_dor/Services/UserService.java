package com.example.smart_dor.Services;


import com.example.smart_dor.Entities.*;
import com.example.smart_dor.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService implements IUserService{


    @Autowired
    UserRepository userRepository;




}
