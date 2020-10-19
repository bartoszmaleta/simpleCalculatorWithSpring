package com.company.controller;

import com.company.model.HelloMessage;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
public class CalculatorController {

    @RequestMapping(value = "/calculator", method = RequestMethod.GET)
    public String calculatorForm() {
        return "calculatorform";
    }

    // localhost:8081/calculator
    @RequestMapping(value = "/calculator", method = RequestMethod.POST)
    public String helloWithForm(HttpServletRequest httpServletRequest, Model model) {
        // get name parameter from request;
        // will be null if no parameter passed in
        String firstNumber = httpServletRequest.getParameter("firstnumber");
        String secondNumber = httpServletRequest.getParameter("secondnumber");
        String sign = httpServletRequest.getParameter("sign");

        if (firstNumber == null || firstNumber.equals("")
                || secondNumber == null || secondNumber.equals("")
                || sign == null || sign.equals("")) {
            model.addAttribute("message", HelloMessage.getMessage("Wrong parameter provided!"));
            return "result"; // return file path, e.q. folder/fileName
        }

        String result = String.valueOf(calculate(firstNumber, secondNumber, sign));
        model.addAttribute("message", HelloMessage.getMessage(result));
        return "result"; // return file path, e.q. folder/fileName
    }

    private int calculate(String firstNumber, String secondNumber, String sign) {
        int first = Integer.parseInt(firstNumber);
        int second = Integer.parseInt(secondNumber);
        switch (sign) {
            case "+":
                return first + second;
            case "-":
                return first - second;
            case "/":
                return first / second;
            case "*":
                return first * second;
            default:
                return 0;
        }
    }
}
