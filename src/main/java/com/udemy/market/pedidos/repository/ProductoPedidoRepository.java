package com.udemy.market.pedidos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.udemy.market.pedidos.model.ProductoPedido;

public interface ProductoPedidoRepository extends JpaRepository<ProductoPedido, Integer> {
    
}
