package com.blinkspace.springjpa.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

@Getter
@Setter
@Entity //anotação para converter o objeto do modelo de domínio para o modelo relacional
@Table(name = "tb_payment") //anotação para setar um nome para a tabela
public class Payment implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Instant moment;

    @OneToOne //associação de um para um com a classe Order. A classe PAYMENT é a classe dependente.
    @MapsId
    private Order order; //associação com Order. Um pagamento (Payment) tem um pedido (order)

    public Payment() {
    }

    public Payment(Integer id, Instant moment, Order order) {
        this.id = id;
        this.moment = moment;
        this.order = order;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Payment)) return false;
        Payment payment = (Payment) o;
        return getId().equals(payment.getId()) && Objects.equals(getMoment(), payment.getMoment()) && Objects.equals(getOrder(), payment.getOrder());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getMoment(), getOrder());
    }
}
