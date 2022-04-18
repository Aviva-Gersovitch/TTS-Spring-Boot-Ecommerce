package com.tts.ecommerce;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tts.ecommerce.Model.ChargeRequest;
import com.tts.ecommerce.service.UserService;

@Controller
public class CheckoutController {
	
	@Autowired
	UserService userService;
	
	@Value("${STRIPE_PUBLIC_KEY}")
    private String stripePublicKey;

    @RequestMapping("/checkout")
    public String checkout(Model model, @RequestParam("amount") double amount) {
        model.addAttribute("amount", (int) (amount * 100)); // in cents
        model.addAttribute("stripePublicKey", stripePublicKey);
        model.addAttribute("currency", ChargeRequest.Currency.USD);
        return "checkout";
    }
}
