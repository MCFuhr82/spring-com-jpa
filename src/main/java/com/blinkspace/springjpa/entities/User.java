package com.blinkspace.springjpa.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

@Data // @Data is a convenient shortcut annotation that bundles the features of @ToString, @EqualsAndHashCode, @Getter / @Setter and @RequiredArgsConstructor together.
@NoArgsConstructor // @NoArgsConstructor will generate a constructor with no parameters.
@AllArgsConstructor // @AllArgsConstructor generates a constructor with 1 parameter for each field in your class.
@Entity //anotação para converter o objeto do modelo de domínio para o modelo relacional
public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id //anotação para informar que essa será a chave primária
    @GeneratedValue(strategy = GenerationType.IDENTITY) // como a chave primária é númerica, anotação para autoincrementar
    private Integer id;
    private String name;
    private String email;
    private String phone;
    private String password;
}
