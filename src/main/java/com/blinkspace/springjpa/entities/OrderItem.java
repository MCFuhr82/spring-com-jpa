package com.blinkspace.springjpa.entities;

import com.blinkspace.springjpa.entities.pk.OrderItemPK;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "tb_order_item")
public class OrderItem implements Serializable {
    private static final long serialVersionUID = 1L;

    @EmbeddedId //anotação para uma chave primária com mais de um atributo
    private OrderItemPK id = new OrderItemPK(); //sempre que criar uma classe auxiliar, com id composto, sempre instanciar direto no atributo, senao da um NullPointerException

    private Integer quantity;
    private Double price;

    public OrderItem(){
    }

    //no construtor não consta o atributo OrderItemPK id. Será adicionado manualmente
    public OrderItem(Order order, Product product, Integer quantity, Double price) {
        super();
        id.setOrder(order); //adicionado manualmente
        id.setProduct(product); //adicionado manualmente
        this.quantity = quantity;
        this.price = price;
    }

    @JsonIgnore //como não tem o order como atributo, pode colocar direto no método getOrder, para não cair no loop infinito
    public Order getOrder() {
        return id.getOrder();
    }

    public void setOrder(Order order) {
        id.setOrder(order);
    }

    public Product getProduct() {
        return id.getProduct();
    }

    public void setProduct(Product product) {
        id.setProduct(product);
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getSubTotal() { //para aparecer no Json, tem que colocar getSubTotal() e não apenas subTotal()
        return quantity * price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OrderItem)) return false;
        OrderItem orderItem = (OrderItem) o;
        return id.equals(orderItem.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
