package com.github.ticoyk.mvchallenge.service;

import java.util.List;

public interface ClienteCrudService<T> {

    T registrarOuAtualizarCliente(T cliente);
    T atualizarCliente(Long id, T cliente);
    List<T> trazerTodosClientes();
    T encontrarClientePorId(Long id);
    void deletarClientePorId(Long id);

}