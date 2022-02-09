package com.blinkspace.springjpa.entities.pk;

import com.blinkspace.springjpa.entities.Order;
import com.blinkspace.springjpa.entities.Product;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.util.Objects;


@Embeddable
public class OrderItemPK implements Serializable { //essa classe, em especial, não terá construtor
    private static final long serialVersionUID = 1L;

    @ManyToOne //relação de muitos para um com o objeto order.
    @JoinColumn(name = "order_id") //nome da chave estrangeira na tabela de banco de dados relacional
    private Order order;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OrderItemPK)) return false;
        OrderItemPK that = (OrderItemPK) o;
        return getOrder().equals(that.getOrder()) && getProduct().equals(that.getProduct());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getOrder(), getProduct());
    }
}
