package com.andersen.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.base.Objects;

import javax.persistence.*;
import javax.validation.constraints.AssertTrue;
import java.io.Serializable;
import java.util.List;

@Entity
public class Customer implements Serializable {

    //serial version

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String fio;

    private String password;

    @OneToMany(mappedBy = "customer")
    @JsonIgnore
    private List<OrderT> orderTs;

    public Customer() {
    }

    public int getId() {
        return id;
    }

    public String getFio() {
        return fio;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public List<OrderT> getOrderTs() {
        return orderTs;
    }

    public void setOrderTs(List<OrderT> orderTs) {
        this.orderTs = orderTs;
    }

    @AssertTrue
    public boolean checkPassword(String password) {
        if (password.equals(this.password)) {
            return true;
        } else return false;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Customer)) return false;
        Customer customer = (Customer) o;
        return Objects.equal(id, customer.id) &&
                Objects.equal(fio, customer.fio) &&
                Objects.equal(password, customer.password);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id, fio, password);
    }

    @Override
    public String toString() {
        return "Customer{" +
                "fio='" + fio + '\'' +
                ", id=" + id +
                '}';
    }
}
