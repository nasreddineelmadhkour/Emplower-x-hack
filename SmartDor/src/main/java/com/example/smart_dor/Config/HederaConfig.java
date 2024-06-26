package com.example.smart_dor.Config;

import com.hedera.hashgraph.sdk.AccountId;
import com.hedera.hashgraph.sdk.Client;
import com.hedera.hashgraph.sdk.PrivateKey;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HederaConfig {

    @Bean
    public Client hederaClient() {
        Client client = Client.forTestnet(); // or Client.forMainnet()
        client.setOperator(AccountId.fromString("0.0.4478842"), PrivateKey.fromString("3030020100300706052b8104000a04220420babb631b7bb42fc25229f50fba172e5c5547e5d3cc4a7451061dc509166daf9d"));
            return client;
    }
}
