package com.blinkspace.springjpa.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor // @AllArgsConstructor generates a constructor with 1 parameter for each field in your class.
@Entity //anotação para converter o objeto do modelo de domínio para o modelo relacional
@Table(name = "tb_user")
public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id //anotação para informar que essa será a chave primária
    @GeneratedValue(strategy = GenerationType.IDENTITY) // como a chave primária é númerica, anotação para autoincrementar
    private Integer id;
    private String name;
    private String email;
    private String phone;
    private String password;

    @Getter // como é uma coleção, usa somente o Getter e não o Setter
    @OneToMany(mappedBy = "client") //tem que colocar o nome do atributo que esta do outro lado da associação
    @JsonIgnore //essa anotação é muito importante, pois como tem associação nas duas pontas, usando o @JsonIgnore, não fica naquele loop infinito, do user chamando order e vice versa.
    private List<Order> orders = new ArrayList<>(); // como é uma coleção, já foi instanciado no atributo.

    public User(Integer id, String name, String email, String phone, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        User user = (User) o;
        return id != null && Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
