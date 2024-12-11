package ca.gbc.orderservice.config;

import ca.gbc.orderservice.client.InventoryClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
public class RestClientConfig {

    @Value("${inventoryServiceUrl}")
    private String inventoryServiceUrl;

    @Bean
    public InventoryClient inventoryClient() {
        // Create RestClient with baseUrl
        RestClient restClient = RestClient.builder()
                .baseUrl(inventoryServiceUrl)
                .build();

        // Create RestClientAdapter
        var restClientAdapter = RestClientAdapter.create(restClient);

        // Build HttpServiceProxyFactory
        var httpServiceProxyFactory = HttpServiceProxyFactory.builderFor(restClientAdapter).build();

        // Create and return InventoryClient
        return httpServiceProxyFactory.createClient(InventoryClient.class);
    }
}
