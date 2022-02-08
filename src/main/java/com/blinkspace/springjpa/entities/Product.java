package com.blinkspace.springjpa.entities;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_product")
public class Product implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String description;
    private Double price;
    private String imgUrl;

    //@Transient //impede que o JPA interprete. Apenas uma anotação transitória
    @ManyToMany
    @JoinTable(name = "tb_product_category", joinColumns = @JoinColumn(name = "product_id"),
    inverseJoinColumns = @JoinColumn(name = "category_id")) //qual será o nome da tabela e quais serão as chaves estrangeiras que irão associar a tablea de produtos com a tabela de categorias
    private Set<Category> categories = new HashSet<>(); //Associação entre produtos e categorias. Foi usado Set ao invés de List. Para garantir que o mesmo produto não posso ter uma categoria mais de uma vez.
    // Também já foi instanciado, para não valer nulo. O conjunto set já inicia vazio.

    public Product(Integer id, String name, String description, double price, String imgUrl) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.imgUrl = imgUrl;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Product product = (Product) o;
        return id != null && Objects.equals(id, product.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
