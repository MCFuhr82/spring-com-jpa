package com.blinkspace.springjpa.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data // @Data is a convenient shortcut annotation that bundles the features of @ToString, @EqualsAndHashCode, @Getter / @Setter and @RequiredArgsConstructor together.
@NoArgsConstructor // @NoArgsConstructor will generate a constructor with no parameters.
@AllArgsConstructor // @AllArgsConstructor generates a constructor with 1 parameter for each field in your class.
public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;
    private String name;
    private String email;
    private String phone;
    private String password;
}
