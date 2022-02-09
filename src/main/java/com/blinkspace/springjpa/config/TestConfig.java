package com.blinkspace.springjpa.config;

import com.blinkspace.springjpa.entities.*;
import com.blinkspace.springjpa.entities.enums.OrderStatus;
import com.blinkspace.springjpa.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.time.Instant;
import java.util.Arrays;
import java.util.Set;

@Configuration //anotação para dizer que é uma classe de configuração
@Profile("test") //anotação para rodar somente quando estivermos trabalhando com o modelo teste. Quando no arquivo application.properties estiver 'spring.profiles.active=test'
public class TestConfig implements CommandLineRunner { //CommandLineRunner indica que quando iniciar a aplicação, essa classe será rodada, através do método run

    @Autowired //injecação de dependência da classe UserRepository
    private UserRepository userRepository;

    @Autowired //injeção da classe OrderRepository, para fazer seed de alguns pedidos
    private OrderRepository orderRepository;

    @Autowired //injecação de dependência da classe CategoryRepository
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Override
    public void run(String... args) throws Exception {

        Category category1 = new Category(null, "Electronics");
        Category category2 = new Category(null, "Books");
        Category category3 = new Category(null, "Computers");

        Product product1 = new Product(null, "O Senhor dos Aneis", "A história narra o conflito contra o mal que se alastra pela Terra-média, através da luta de várias raças", 45.99, "");
        Product product2 = new Product(null, "SmartTv LG 60", "TV OLED última geração de 60 polegadas", 3549.99, "");
        Product product3 = new Product(null, "PC Gamer", "PC configuração inicial para jogadores amadores", 5950.00, "");
        Product product4 = new Product(null, "Biblia Sagrada", "Biblia em capa dura com gravuras em alguns trechos", 52.00, "");
        Product product5 = new Product(null, "IPAD Pro", "Lançamento com recursos inovadores e tela 8k", 8565.99, "");
        Product product6 = new Product(null, "MacBook Pro", "Máquina com interface gráfica para quem trabalha com imagens", 16950.00, "");

        categoryRepository.saveAll(Arrays.asList(category1, category2, category3)); //usando o JpaRepository para popular o banco de dados, com o método saveAll. Para salvar vários dados, foi usado Arrays.asList
        productRepository.saveAll(Arrays.asList(product1, product2, product3, product4, product5, product6)); //

        product1.getCategories().add(category2);
        product2.getCategories().add(category1);
        product3.getCategories().add(category1);
        product3.getCategories().add(category3);
        product4.getCategories().add(category2);
        product5.getCategories().add(category1);
        product6.getCategories().add(category1);
        product6.getCategories().add(category3);

        productRepository.saveAll(Arrays.asList(product1, product2, product3, product4, product5, product6)); //

        User user1 = new User(null, "Marcelo Fuhr", "marcelinhofuhr@gmail.com", "45991011614", "123456");
        User user2 = new User(null, "Dayane Oenning", "dayoenning@gmail.com", "45991061985", "456789");

        // a data esta no formato da ISO8601. O 'Z' no final diz que esta no formato UTC de Greenwich
        Order order1 = new Order(null, Instant.parse("2022-01-30T20:02:33Z"), OrderStatus.PAID,user1);
        Order order2 = new Order(null, Instant.parse("2022-02-01T10:05:33Z"), OrderStatus.WAITING_PAYMENT ,user2);
        Order order3 = new Order(null, Instant.parse("2022-02-01T10:10:45Z"), OrderStatus.WAITING_PAYMENT,user1);

        userRepository.saveAll(Arrays.asList(user1, user2)); //usando o JpaRepository para popular o banco de dados, com o método saveAll. Para salvar vários dados, foi usado Arrays.asList
        orderRepository.saveAll(Arrays.asList(order1, order2, order3)); //usando o JpaRepository para popular o banco de dados, com o método saveAll. Para salvar vários dados, foi usado Arrays.asList

        OrderItem oi1 = new OrderItem(order1, product1, 2, product1.getPrice());
        OrderItem oi2 = new OrderItem(order2, product5, 1, product5.getPrice());
        OrderItem oi3 = new OrderItem(order3, product2, 3, product2.getPrice());
        OrderItem oi4 = new OrderItem(order3, product4, 5, product4.getPrice());

        orderItemRepository.saveAll(Arrays.asList(oi1, oi2, oi3, oi4));

        Payment payment1 = new Payment(null, Instant.parse("2022-01-30T20:15:33Z"), order1);
        order1.setPayment(payment1);
        orderRepository.save(order1);

    }

}
