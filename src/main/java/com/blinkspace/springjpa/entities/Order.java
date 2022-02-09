package com.blinkspace.springjpa.entities;

import com.blinkspace.springjpa.entities.enums.OrderStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity //anotação para converter o objeto do modelo de domínio para o modelo relacional
@Table(name = "tb_order") //anotação para setar um nome para a tabela
public class Order implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT") //anotação para formtar a data
    private Instant moment;

    // internamento, foi colocado o atributo orderStatus como Integer, para dizer explicitamente que esta gravando do banco de dados um número inteiro
    //entretanto, esse tratamento é somente interno na classe. Para o mundo externo será mantido o OrderStatus
    private Integer orderStatus;

    @ManyToOne //anotação para mapear a CHAVE ESTRANGEIRA de muitos para um. Nesse caso, de MUITOS ORDER para UM USER.
    @JoinColumn(name = "client_id") // anotação para dizer qual nome da chave estrangeira que terá no banco de dados
    private User client;

    @OneToMany(mappedBy = "id.order")//apesar de associar a OrderItem, será mapeado pelo id.order, da classe OrderItemPK
    private Set<OrderItem> items = new HashSet<>(); //como é uma collection, já instancia direto no atributo

    @OneToOne(mappedBy = "order", cascade = CascadeType.ALL)
    private Payment payment; //associação com Payment. Um pedido (Order) tem um pagamento (payment)

    public Order() {
    }

    //criado um construtor para resolver o problema da instanciação do Order no testConfig
    public Order(Integer id, Instant moment, OrderStatus orderStatus, User client) {
        super();
        this.id = id;
        this.moment = moment;
        setOrderStatus(orderStatus);
        this.client = client;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Instant getMoment() {
        return moment;
    }

    public void setMoment(Instant moment) {
        this.moment = moment;
    }
    //como o orderStatus é Integer na classe, mas o retorno tem que ser OrderStatus, pega através da forma criado na classe OrderStatus, que tem como parametro um int
    public OrderStatus getOrderStatus() {
        return OrderStatus.fromValue(orderStatus);
    }

    //da mesma forma, recebe um OrderStatus, mas transforma em int usando o método getCode, atribuindo como valor para o orderStatus da classe
    public void setOrderStatus(OrderStatus orderStatus) {
        if (orderStatus != null) {
            this.orderStatus = orderStatus.getCode();
        }
    }

    public User getClient() {
        return client;
    }

    public void setClient(User client) {
        this.client = client;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public Set<OrderItem> getItems(){
        return items;
    }

    public Double getTotal(){
        Double sum = 0.0;
        for (OrderItem item : items) {
            sum += item.getSubTotal();
        }
        return sum;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Order order = (Order) o;
        return id != null && Objects.equals(id, order.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
