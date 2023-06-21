package com.udemy.market.pedidos.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Producto_Pedido")
public class ProductoPedido {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    int pedido;
    @ManyToOne @JoinColumn(name = "producto", referencedColumnName = "id")
    Producto producto;
    int cantidad;

}
