package com.andersen.jsonControllers;

import com.andersen.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.transaction.Transactional;


@Controller
@Transactional
public class RedirectController {

    @Autowired
    CustomerRepository customerService;

    @RequestMapping(value = "customer", method = RequestMethod.GET)
    public String customersPage() {
        return "customer";
    }

    @RequestMapping(value = "administrator", method = RequestMethod.GET)
    public String administratorsPage() {
        return "administrator";
    }

    @RequestMapping(value = "customerAdmin", method = RequestMethod.GET)
    public String customerAdminPage() {
        return "customerAdmin";
    }

    @RequestMapping(value = "customerSuccess", method = RequestMethod.GET)
    public String customerSuccessPage() {
        return "customerSuccess";
    }

    @RequestMapping(value = "goodAdmin", method = RequestMethod.GET)
    public String goodAdminPage() {
        return "goodAdmin";
    }

    @RequestMapping(value = "orderAdmin", method = RequestMethod.GET)
    public String orderAdminPage() {
        return "orderAdmin";
    }

}