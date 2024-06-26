package com.example.smart_dor.Services;

import com.hedera.hashgraph.sdk.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.concurrent.TimeoutException;

@Service
public class HederaService {

    @Autowired
    private Client hederaClient;
    private final AccountId operatorId;
    private final PrivateKey operatorKey;


    private Client client;





    public HederaService() {
        operatorId = AccountId.fromString("0.0.4478842");
        operatorKey = PrivateKey.fromString("3030020100300706052b8104000a04220420babb631b7bb42fc25229f50fba172e5c5547e5d3cc4a7451061dc509166daf9d");

        hederaClient = Client.forTestnet();
        hederaClient.setOperator(operatorId, operatorKey);

        this.client = Client.forTestnet();  // Change to Client.forMainnet() if you are using the mainnet
        client.setOperator(AccountId.fromString("0.0.4478842"), PrivateKey.fromString("3030020100300706052b8104000a04220420babb631b7bb42fc25229f50fba172e5c5547e5d3cc4a7451061dc509166daf9d"));

    }

    public AccountId createHederaAccount() throws PrecheckStatusException, TimeoutException, ReceiptStatusException {
        PrivateKey privateKey = PrivateKey.generate();
        PublicKey publicKey = privateKey.getPublicKey();

        TransactionResponse response = new AccountCreateTransaction()
                .setKey(publicKey)
                .setInitialBalance(new Hbar(10)) // initial balance for the new account
                .execute(hederaClient);

        TransactionReceipt receipt = response.getReceipt(hederaClient);
        AccountId newAccountId = receipt.accountId;

        // Store the new account ID and private key securely

        return newAccountId;
    }
    public TransactionReceipt transferTokens(AccountId fromAccountId, AccountId toAccountId, long amount) throws PrecheckStatusException, TimeoutException, ReceiptStatusException {
        TransactionResponse response = new TransferTransaction()
                .addHbarTransfer(fromAccountId, new Hbar(-amount))
                .addHbarTransfer(toAccountId, new Hbar(amount))
                .execute(hederaClient);

        return response.getReceipt(hederaClient);
    }

    public long getAccountBalance(AccountId accountId) throws TimeoutException, InterruptedException, ReceiptStatusException, PrecheckStatusException {
        AccountBalanceQuery query = new AccountBalanceQuery()
                .setAccountId(accountId);

        return query.execute(hederaClient).hbars.getValue().longValue();
    }

    public AccountId createNewAccount(long initialBalance) throws PrecheckStatusException, TimeoutException, ReceiptStatusException {
        PrivateKey newKey = PrivateKey.generate();
        PublicKey newPublicKey = newKey.getPublicKey();

        TransactionResponse response = new AccountCreateTransaction()
                .setKey(newPublicKey)
                .setInitialBalance(new Hbar(initialBalance))
                .execute(hederaClient);

        TransactionReceipt receipt = response.getReceipt(hederaClient);
        AccountId newAccountId = receipt.accountId;
        System.out.println("New account created: " + newAccountId);
        return newAccountId;
    }

    public TokenId createToken(String tokenName, String tokenSymbol, long initialSupply, int decimals, AccountId treasuryAccountId) throws PrecheckStatusException, TimeoutException, ReceiptStatusException {
        TransactionResponse response = new TokenCreateTransaction()
                .setTokenName(tokenName)
                .setTokenSymbol(tokenSymbol)
                .setTreasuryAccountId(treasuryAccountId)
                .setDecimals(decimals)
                .setInitialSupply(initialSupply)
                .execute(hederaClient);

        TransactionReceipt receipt = response.getReceipt(hederaClient);
        TokenId tokenId = receipt.tokenId;
        System.out.println("Token created: " + tokenId);
        return tokenId;
    }

    public void transferTokens(AccountId fromAccountId, AccountId toAccountId, TokenId tokenId, long amount) throws PrecheckStatusException, TimeoutException, ReceiptStatusException {
        new TransferTransaction()
                .addTokenTransfer(tokenId, fromAccountId, -amount)
                .addTokenTransfer(tokenId, toAccountId, amount)
                .execute(hederaClient)
                .getReceipt(hederaClient); // Use getReceipt() to obtain the transaction receipt
        System.out.println("Transferred " + amount + " tokens from " + fromAccountId + " to " + toAccountId);
    }

    public Hbar getBalance(AccountId accountId) throws PrecheckStatusException, TimeoutException {
        AccountBalance balance = new AccountBalanceQuery()
                .setAccountId(accountId)
                .execute(hederaClient);
        System.out.println("Balance for account " + accountId + ": " + balance.hbars);
        return balance.hbars;
    }


    public void associateToken(AccountId accountId, TokenId tokenId) throws PrecheckStatusException, TimeoutException, ReceiptStatusException {
        new TokenAssociateTransaction()
                .setAccountId(accountId)
                .setTokenIds(Collections.singletonList(tokenId)) // Pass a list of TokenId
                .execute(hederaClient)
                .getReceipt(hederaClient);
    }

}
