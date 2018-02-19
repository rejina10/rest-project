package com.trizic.repository;


import com.trizic.modal.Model;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ModelRepository extends CrudRepository<Model, Long> {

    Model findDistinctByName(String modelName);

}
