package com.example.demo.repository;

import com.example.demo.entity.Rental;
import com.example.demo.enums.RentalStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RentalRepository extends JpaRepository<Rental, Long> {

    List<Rental> findByUserIdOrderByLoanedAtDesc(Long userId);

    List<Rental> findAllByOrderByLoanedAtDesc();

    List<Rental> findByStatusOrderByLoanedAtDesc(RentalStatus status);

    List<Rental> findByUserIdAndStatusOrderByLoanedAtDesc(Long userId, RentalStatus status);
}
