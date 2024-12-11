//pretend Call

package ca.gbc.orderservice.stub;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

public class InventoryClientStub {

    public static void stubInventoryClient(String skuCode, int quantity) {
        stubFor(get(urlEqualTo("/inventory/?skuCode=" + skuCode + "&quantity=" + quantity))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        //.withBody("true")
                        .withBody("{ \"message\": \"Inventory check successful\" }")));
    }
}
