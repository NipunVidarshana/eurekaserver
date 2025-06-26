/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.logic.springjwt.ExceptionHandler;

/**
 * @author fernando.wwjpnv
 */
public class ExceptionEmployeeInactiveMode extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public ExceptionEmployeeInactiveMode(String msg) {
        super(msg);
    }
}