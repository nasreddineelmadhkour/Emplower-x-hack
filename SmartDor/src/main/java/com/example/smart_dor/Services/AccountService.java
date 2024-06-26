package com.example.smart_dor.Services;

import com.example.smart_dor.Dto.AccountLoginDto;
import com.example.smart_dor.Entities.Role;
import com.example.smart_dor.Entities.User;
import com.example.smart_dor.Repository.UserRepository;
import com.hedera.hashgraph.sdk.AccountId;
import com.hedera.hashgraph.sdk.PrecheckStatusException;
import com.hedera.hashgraph.sdk.ReceiptStatusException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.beans.Encoder;
import java.util.List;
import java.util.concurrent.TimeoutException;

@Service
public class AccountService implements IAccountService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private HederaService hederaService;
    @Override
    public List<AccountLoginDto> getAllUser() {
        return null;
    }

    @Override
    public User signup(User user) throws PrecheckStatusException, TimeoutException, ReceiptStatusException {

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        AccountId hederaAccountId = hederaService.createHederaAccount();

        user.setRole(Role.EMPLOYE);
        // Store Hedera account ID and private key
        user.setHederaAccountId(hederaAccountId.toString());
        user.setHederaPrivateKey(hederaAccountId.toString());
        return userRepository.save(user);
    }

    @Override
    public void deleteEmploye(Long idAccount) {

    }
}
