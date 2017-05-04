package com.andersen.repositories;

import com.andersen.entities.OrderT;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<OrderT,Integer> {}
