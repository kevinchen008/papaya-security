package com.papaya.wiremock;

import com.github.tomakehurst.wiremock.client.WireMock;


public class WiremockServer {

    public static void main(String[] args){
        WireMock.configureFor(9999);
        WireMock.removeAllMappings();

        mock("/order/1","{'id':123}");
    }

    private static void mock(String url,String response){
        WireMock.stubFor(WireMock.get(WireMock.urlEqualTo(url))
                .willReturn(WireMock.aResponse().withBody(response)
                        .withStatus(200)));
    }
}