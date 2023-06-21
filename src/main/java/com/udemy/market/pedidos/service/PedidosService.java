package com.udemy.market.pedidos.service;

import java.util.List;

import com.udemy.market.pedidos.model.Pedido;
import com.udemy.market.pedidos.model.ProductoPedido;

public interface PedidosService {

    List<Pedido> pedidosByCliente(int clienteId);
    Pedido createPedido(int clienteId, List<ProductoPedido> productoPedido);

}
