package co.za.coin.machine.controller;

import co.za.coin.machine.mapper.Coin;
import co.za.coin.machine.service.CoinMachineService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CoinMachineController {

    private CoinMachineService coinMachineService;

    public CoinMachineController(CoinMachineService coinMachineService) {
        this.coinMachineService = coinMachineService;
    }

    @RequestMapping(value = "/coin1", method = RequestMethod.POST)
    public ResponseEntity<?> coin1(@RequestParam(value = "change", required = true) String change, @RequestParam(value = "denominations", required = true) String denominations) {

        String[] denominationsArray = denominations.split(",");
        if (StringUtils.isEmpty(change)) {
            throw new RuntimeException("Please provide change value! ");
        }
        int answer = 0;

        //if the user does not pass the denominations, we create a default array of denominations
        if (StringUtils.isEmpty(denominations)) {
            int[] deno = new int[]{9, 6, 5, 1};
            answer = coinMachineService.deno(Integer.parseInt(change), deno);

        } else {
            int[] denoArray = new int[denominationsArray.length];
            for (int i = 0; i < denominationsArray.length; i++) {
                denoArray[i] = Integer.parseInt(denominationsArray[i]);
            }
             answer = coinMachineService.deno(Integer.parseInt(change), denoArray);

        }

        return ResponseEntity.ok().body(answer);
    }

    @RequestMapping(value = "/coin")
    public ModelAndView coin(@ModelAttribute("coin") Coin coin, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return new ModelAndView("error");
        }
        String[] denominations = coin.getDenominations().split(",");

        int[] deno = new int[denominations.length];

        for (int i = 0; i < denominations.length; i++) {
            Integer.parseInt(denominations[i]);
        }
        ModelAndView modelAndView = new ModelAndView();
        try {
            Integer changeObject = new Integer(coin.getChange());
            if (changeObject == null) {
                throw new RuntimeException("Please provide change value! ");
            }
            if (coin.getDenominations() == null) {
                deno = new int[]{9, 6, 5, 1};
            }
            int answer = coinMachineService.deno(coin.getChange(), deno);
            modelAndView.addObject("response", answer);
            return modelAndView;
        } catch (Exception e) {
            modelAndView.addObject("error", e.getMessage());
        }

        return modelAndView;
    }
}
