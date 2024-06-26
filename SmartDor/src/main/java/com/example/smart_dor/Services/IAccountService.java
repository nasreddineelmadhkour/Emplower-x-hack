package com.example.smart_dor.Services;

import com.example.smart_dor.Dto.AccountLoginDto;
import com.example.smart_dor.Entities.User;
import com.hedera.hashgraph.sdk.PrecheckStatusException;
import com.hedera.hashgraph.sdk.ReceiptStatusException;

import java.util.List;
import java.util.concurrent.TimeoutException;

public interface IAccountService {
    public List<AccountLoginDto> getAllUser();
    public User signup(User user)throws PrecheckStatusException, TimeoutException, ReceiptStatusException;
    //public Account addEmploye(User account);


    void deleteEmploye(Long idAccount);
}
