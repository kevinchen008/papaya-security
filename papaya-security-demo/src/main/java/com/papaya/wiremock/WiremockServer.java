package com.papaya.wiremock;

import com.github.tomakehurst.wiremock.client.WireMock;

public class WiremockServer {

    public static void main(String[] args){
        WireMock.configureFor(9999);
        WireMock.removeAllMappings();

        WireMock.stubFor(WireMock.get(WireMock.urlEqualTo("/order/1"))
                .willReturn(WireMock.aResponse().withBody("{'id':123}")
                .withStatus(200)));

    }
}