package com.example.demo.Interface;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.models.DemoModel;

@Repository
public interface demoInterface extends CrudRepository<DemoModel , Integer>{

}