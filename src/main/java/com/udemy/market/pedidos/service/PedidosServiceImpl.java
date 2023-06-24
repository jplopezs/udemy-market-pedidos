package com.udemy.market.pedidos.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.udemy.market.pedidos.model.Pedido;
import com.udemy.market.pedidos.model.ProductoPedido;
import com.udemy.market.pedidos.repository.PedidosRepository;
import com.udemy.market.pedidos.repository.ProductoPedidoRepository;

@Service
public class PedidosServiceImpl implements PedidosService {

    String urlProductosAPI = "http://localhost:8001/market/productos/producto";

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    PedidosRepository pedidosRepository;

    @Autowired
    ProductoPedidoRepository productoPedidoRepository;

    @Override
    public List<Pedido> pedidosByCliente(int clienteId) {

        return pedidosRepository.findByCliente(clienteId);

    }

    @Override
    public Pedido createPedido(int clienteId, List<ProductoPedido> productoPedido) {

        try {
            // Create Pedido object
            Pedido pedido = new Pedido(0, clienteId, new Date(), "Pendiente", null);
            pedido = pedidosRepository.save(pedido);
            int pedidoId = pedido.getId();
        
            // Create each ProductoPedido
            productoPedido.forEach(item -> {
                item.setPedido(pedidoId);
                productoPedidoRepository.save(item);
                UriComponentsBuilder url = UriComponentsBuilder.fromHttpUrl(urlProductosAPI)
                .queryParam("id", item.getProducto().getId())
                .queryParam("unidades", item.getCantidad());
                restTemplate.put(url.toUriString(), null);
            });

            return pedido;

        }
        catch (Exception e) {

            return null;

        }
    
    }

}
