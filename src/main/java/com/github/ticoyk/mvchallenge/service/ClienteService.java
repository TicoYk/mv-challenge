package com.github.ticoyk.mvchallenge.service;

import java.util.List;

public interface ClienteService<T> {

    T registrarNovoCliente(T cliente);
    T atualizarCliente(T cliente);
    List<T> trazerTodosClientes();
    T encontrarClientePorId(Long id);

}