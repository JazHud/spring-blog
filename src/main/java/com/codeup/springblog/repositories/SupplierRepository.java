package com.codeup.springblog.repositories;

import com.codeup.springblog.models.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SupplierRepository extends JpaRepository <Supplier, Long> {
Supplier findById(long id);

}
