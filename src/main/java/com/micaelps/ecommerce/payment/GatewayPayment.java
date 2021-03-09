package com.micaelps.ecommerce.payment;

import org.springframework.web.util.UriComponentsBuilder;

enum GatewayPayment {

    pagseguro {
        @Override
        String urlReturn(Purchase compra,
                              UriComponentsBuilder uriComponentsBuilder) {
            String urlRetornoPagseguro = uriComponentsBuilder
                    .path("/retorno-pagseguro/{id}")
                    .buildAndExpand(compra.getId()).toString();

            return "pagseguro.com/" + compra.getId() + "?redirectUrl="
                    + urlRetornoPagseguro;
        }
    },
    paypal {
        @Override
        String urlReturn(Purchase compra,
                              UriComponentsBuilder uriComponentsBuilder) {
            String urlRetornoPaypal = uriComponentsBuilder
                    .path("/retorno-paypal/{id}").buildAndExpand(compra.getId())
                    .toString();

            return "paypal.com/" + compra.getId() + "?redirectUrl=" + urlRetornoPaypal;
        }
    };

    abstract String urlReturn(Purchase compra,
                                   UriComponentsBuilder uriComponentsBuilder);
}
