package com.blinkspace.springjpa.entities;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor // @AllArgsConstructor generates a constructor with 1 parameter for each field in your class.
@Entity //anotação para converter o objeto do modelo de domínio para o modelo relacional
@Table(name = "tb_order") //anotação para setar um nome para a tabela
public class Order implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Instant moment;

    @ManyToOne //anotação para mapear a CHAVE ESTRANGEIRA de muitos para um. Nesse caso, de MUITOS ORDER para UM USER.
    @JoinColumn(name = "client_id") // anotação para dizer qual nome da chave estrangeira que terá no banco de dados
    private User client;

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
