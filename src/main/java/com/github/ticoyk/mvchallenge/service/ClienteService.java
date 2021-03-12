package com.github.ticoyk.mvchallenge.service;

public interface ClienteService<T> {

    T registrarNovoCliente(T cliente);
    T atualizarCliente(T cliente);
    
}