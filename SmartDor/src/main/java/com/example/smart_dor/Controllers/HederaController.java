package com.example.smart_dor.Controllers;

import com.example.smart_dor.Services.HederaService;
import com.hedera.hashgraph.sdk.AccountId;
import com.hedera.hashgraph.sdk.PrecheckStatusException;
import com.hedera.hashgraph.sdk.ReceiptStatusException;
import com.hedera.hashgraph.sdk.TokenId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.TimeoutException;

@RestController
@RequestMapping("/api/hedera")
public class HederaController {

    @Autowired
    private HederaService hederaService;

    @PostMapping("/create-account")
    public String createAccount() throws PrecheckStatusException, TimeoutException, ReceiptStatusException {
        AccountId accountId = hederaService.createHederaAccount();
        return "New Hedera account created: " + accountId.toString();
    }

    @PostMapping("/transfer-tokens")
    public String transferTokens(@RequestParam String toAccountId, @RequestParam long amount) throws PrecheckStatusException, TimeoutException, ReceiptStatusException {
        AccountId fromAccountId = AccountId.fromString("0.0.4478842");
        AccountId recipientAccountId = AccountId.fromString(toAccountId);
        hederaService.transferTokens(fromAccountId, recipientAccountId, amount);
        return "Transferred " + amount + " hbars to account: " + toAccountId;
    }
    @GetMapping("/account-balance")
    public String getAccountBalance(@RequestParam String accountId) throws TimeoutException {
        try {
            AccountId hederaAccountId = AccountId.fromString(accountId);

            // Fetch account balance from Hedera
            long balance = hederaService.getAccountBalance(hederaAccountId);

            return "Account " + accountId + " has a balance of " + balance + " tinybars";  // 1 hbar = 100,000,000 tinybars
        } catch (Exception e) {
            e.printStackTrace();
            return "Failed to fetch account balance: " + e.getMessage();
        }
    }

    @PostMapping("/create-token")
    public String createToken(@RequestParam String tokenName, @RequestParam String tokenSymbol, @RequestParam long initialSupply, @RequestParam int decimals, @RequestParam String treasuryAccountId) throws TimeoutException, PrecheckStatusException, ReceiptStatusException {
        AccountId treasuryAccount = AccountId.fromString(treasuryAccountId);
        TokenId tokenId = hederaService.createToken(tokenName, tokenSymbol, initialSupply, decimals, treasuryAccount);
        return "Token created: " + tokenId;
    }

    @PostMapping("/associate-token")
    public String associateToken(@RequestParam String accountId, @RequestParam String tokenId) throws PrecheckStatusException, TimeoutException, ReceiptStatusException {
        AccountId userAccountId = AccountId.fromString(accountId);
        TokenId tokenToAssociate = TokenId.fromString(tokenId);
        hederaService.associateToken(userAccountId, tokenToAssociate);
        return "Token " + tokenId + " associated with account " + accountId;
    }


}
