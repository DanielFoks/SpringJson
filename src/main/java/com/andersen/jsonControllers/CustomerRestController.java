package com.andersen.jsonControllers;

import com.andersen.entities.Customer;
import com.andersen.entities.Good;
import com.andersen.entities.OrderT;
import com.andersen.repositories.CustomerRepository;
import com.andersen.repositories.GoodRepository;
import com.andersen.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
public class CustomerRestController {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    GoodRepository goodRepository;

    @Autowired
    OrderRepository orderRepository;

    private Customer customer;


    @PostMapping("/customer/check/")
    public ResponseEntity<Customer> checkCustomer(@RequestBody Customer customer, HttpServletResponse response) throws IOException, URISyntaxException {

        List<Customer> customers = customerRepository.findAll();

        for (Customer customer1 : customers) {
            if (customer.getFio() != null && customer.getFio().equals(customer1.getFio())
                    && customer.getPassword() != null && customer.getPassword().equals(customer1.getPassword())) {

                this.customer = customer1;

                return new ResponseEntity<>(customer, HttpStatus.OK);
            }

        }
        return new ResponseEntity<>(customer, HttpStatus.BAD_REQUEST);
    }

    @PostMapping(value = "/customerSuccess/newOrder/")
    public ResponseEntity<List<Integer>> newOrder(@RequestBody List<Integer> goods) {

        OrderT orderT = new OrderT();

        Set<Good> goodSet = new HashSet<>();

        List<Good> goodList = goodRepository.findAll();

        outer:
        for (Integer integer : goods) {
            for (Good good : goodList) {
                if (good.getId() == integer) {
                    goodSet.add(good);
                    continue outer;
                }
            }
        }

        orderT.setGoods(goodSet);
        orderT.setCustomer(customer);

        orderRepository.save(orderT);

        return new ResponseEntity<>(goods, HttpStatus.OK);
    }

}