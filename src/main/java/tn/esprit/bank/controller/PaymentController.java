package tn.esprit.bank.controller;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import com.stripe.model.checkout.SessionCollection;
import com.stripe.param.checkout.SessionCreateParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import tn.esprit.bank.service.StripeService;
import tn.esprit.bank.vo.ChargeRequestVO;
import tn.esprit.bank.vo.PaiementCheckOutVO;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/charge")
public class PaymentController {

    @Autowired
    private StripeService paymentsService;

    @Value("${STRIPE_SECRET_KEY}")
    private String secretKey;

    @PostConstruct
    public void init() {
        Stripe.apiKey = secretKey;
    }


    @PostMapping("/createCheckout")
    public String createCheckout(@RequestBody ChargeRequestVO chargeRequest)
            throws StripeException {
        SessionCreateParams params =
                SessionCreateParams.builder()
                        .setMode(SessionCreateParams.Mode.PAYMENT)
                        .setSuccessUrl("https://example.com/success")
                        .setCancelUrl("https://example.com/cancel")
                        .setClientReferenceId(chargeRequest.getUserAccountNo())
                        .addLineItem(
                                SessionCreateParams.LineItem.builder()
                                        .setQuantity(chargeRequest.getQuantity())
                                        .setPriceData(
                                                SessionCreateParams.LineItem.PriceData.builder()
                                                        .setCurrency(chargeRequest.getCurrency())
                                                        .setUnitAmount(chargeRequest.getAmount() * 100)
                                                        .setProductData(
                                                                SessionCreateParams.LineItem.PriceData.ProductData.builder()
                                                                        .setName(chargeRequest.getProductName())
                                                                        .build())
                                                        .build())
                                        .build())
                        .build();

        Session session = Session.create(params);

        return session.getUrl();
    }

    @PostMapping("/listCheckout")
    public List<PaiementCheckOutVO> listCheckout()
            throws StripeException {
        Map<String, Object> params = new HashMap<>();

        SessionCollection sessions = Session.list(params);
        return sessions.getData().stream().map(session ->
                new PaiementCheckOutVO(session.getId(), session.getPaymentIntent(), session.getPaymentStatus(), session.getStatus(), session.getClientReferenceId())
        ).collect(Collectors.toList());

    }


}
