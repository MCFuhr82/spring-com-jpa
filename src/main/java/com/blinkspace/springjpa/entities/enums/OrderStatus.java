package com.blinkspace.springjpa.entities.enums;

// tipo enum que fará parte do Order
public enum OrderStatus {
    //foi atribuido manualmente um valor numérico para cada tipo enumerado
    WAITING_PAYMENT(1),
    PAID(2),
    SHIPPED(3),
    DELIVERED(4),
    CANCELED(5);


    private int code;

    //construtor para o tipo enumerado é private
    private OrderStatus(int code) {
        this.code = code;
    }

    // método público para acessar o código, já que o construtor é private
    public int getCode() {
        return code;
    }

    //método static, ou seja, não precisa ser instanciado, para buscar o tipo enumerado pelo código.
    public static OrderStatus fromValue(int code) {
        for (OrderStatus status : OrderStatus.values()) {
            if (status.code == code) {
                return status;
            }
        } //se não tiver o código, lança uma exception
        throw new IllegalArgumentException("Invalid OrderStatus code");
    }
}
