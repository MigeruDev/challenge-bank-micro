package com.acme.bank.service;

/**
 * Se lanza cuando el cliente intenta un débito sin saldo suficiente.
 */
public class SaldoNoDisponibleException extends RuntimeException {
    public SaldoNoDisponibleException(String mensaje) {
        super(mensaje);
    }
}
