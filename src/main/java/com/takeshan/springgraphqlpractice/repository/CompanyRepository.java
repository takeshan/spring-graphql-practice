package com.takeshan.springgraphqlpractice.repository;

import com.takeshan.springgraphqlpractice.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Integer> {
}
