package com.blinkspace.springjpa.config;

import com.blinkspace.springjpa.entities.User;
import com.blinkspace.springjpa.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.Arrays;

@Configuration //anotação para dizer que é uma classe de configuração
@Profile("test") //anotação para rodar somente quando estivermos trabalhando com o modelo teste. Quando no arquivo application.properties estiver 'spring.profiles.active=test'
public class TestConfig implements CommandLineRunner { //CommandLineRunner indica que quando iniciar a aplicação, essa classe será rodada, através do método run

    @Autowired //injecação de dependência da classe userRepository
    private UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {

        User user1 = new User(null, "Marcelo Fuhr", "marcelinhofuhr@gmail.com", "45991011614", "123456");
        User user2 = new User(null, "Dayane Oenning", "dayoenning@gmail.com", "45991061985", "456789");

        userRepository.saveAll(Arrays.asList(user1, user2)); //usando o JpaRepository para popular o banco de dados, com o método saveAll. Para salvar vários dados, foi usado Arrays.asList
    }

}
