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
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;


@RestController
@Transactional
public class AdministratorRestController {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    GoodRepository goodRepository;

    @Autowired
    OrderRepository orderRepository;

    //--------------Customers--------------

    @GetMapping("customerAdminList")
    public List<Customer> customersList() {
        return customerRepository.findAll();
    }

    @PostMapping("/customerAdmin/add/")
    public ResponseEntity<Customer> addCustomer(@RequestBody Customer customer) {
        if (customer.getId() == 0) {
            this.customerRepository.save(customer);
        }
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }

    @PostMapping(value = "/customerAdmin/remove/")
    public ResponseEntity<Customer> removeCustomer(@RequestBody Customer customer) {
        if (customer.getOrderTs() != null && customer.getOrderTs().size() > 0) {
            customer.getOrderTs().forEach(orderRepository::delete);
        }
        customerRepository.delete(customer);
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }

    //-------------------------------------

    //--------------Goods------------------

    @GetMapping("goodAdminList")
    public
    @ResponseBody
    List<Good> goodsList() {
        return goodRepository.findAll();
    }

    @PostMapping("/goodAdmin/add/")
    public ResponseEntity<Good> addCustomer(@RequestBody Good good) {
        System.out.println(good);
        if (good.getId() == 0) {
            goodRepository.save(good);
        }
        return new ResponseEntity<>(good, HttpStatus.OK);
    }

    @PostMapping(value = "/goodAdmin/remove/")
    public ResponseEntity<Good> removeCustomer(@RequestBody Good good) {
        goodRepository.delete(good);
        return new ResponseEntity<>(good, HttpStatus.OK);
    }

    //-------------------------------------

    //--------------Order------------------

    @GetMapping("orderAdminList")
    public
    @ResponseBody
    List<OrderT> ordersList() {
        return orderRepository.findAll();
    }

    @PostMapping(value = "/orderAdmin/remove/")
    public ResponseEntity<OrderT> removeCustomer(@RequestBody OrderT orderT) {
        System.err.println(orderT);
        orderRepository.delete(orderT);
        return new ResponseEntity<>(orderT, HttpStatus.OK);
    }

    //-------------------------------------*/


}