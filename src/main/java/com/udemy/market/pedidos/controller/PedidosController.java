package com.udemy.market.pedidos.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.udemy.market.pedidos.model.Pedido;
import com.udemy.market.pedidos.model.ProductoPedido;
import com.udemy.market.pedidos.service.PedidosService;

@RestController
@RequestMapping("market/pedidos")
public class PedidosController {

    @Autowired
    PedidosService pedidosService;

    @GetMapping(value="pedidos", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Pedido>> pedidosByCliente(@RequestParam("id") int clienteId) {

        return new ResponseEntity<List<Pedido>>(pedidosService.pedidosByCliente(clienteId), HttpStatus.OK);

    }

    @PostMapping(value = "pedido", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createPedido(@RequestParam("id") int clienteId, @RequestBody List<ProductoPedido> productosPedido) {

        Pedido pedido = pedidosService.createPedido(clienteId, productosPedido);
        if (pedido == null) {
            return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
        } else {
            return new ResponseEntity<>(HttpStatus.OK);
        }

    }

}
