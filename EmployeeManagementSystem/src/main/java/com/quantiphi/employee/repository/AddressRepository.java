package com.quantiphi.employee.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.quantiphi.employee.model.Address;


@Repository
public interface AddressRepository extends CrudRepository<Address, Integer>{

}
