/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vivienda.venta.errors;

/**
 *
 * @author geron
 */
public class ErrorNullPointerException extends NullPointerException {
    
      public ErrorNullPointerException() {
    }

    public ErrorNullPointerException(String string) {
        super(string);
    }
}
