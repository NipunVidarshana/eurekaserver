/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.logic.springjwt.repository;

import com.logic.springjwt.models.LogTransaction;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LogTransactionRepository extends CrudRepository<LogTransaction, String> {

    @Override
    LogTransaction save(LogTransaction TransactionLog);


    @Query ("select u from LogTransaction u where u.userName = :userName")
    List<LogTransaction> findByUserName(@Param("userName") String userName);

}
