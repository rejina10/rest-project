package com.trizic.repository;


import com.trizic.modal.Advisor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdvisorRepository extends CrudRepository<Advisor, Long> {

    Advisor findByUserNameAndPassword(String userName, String password);


}