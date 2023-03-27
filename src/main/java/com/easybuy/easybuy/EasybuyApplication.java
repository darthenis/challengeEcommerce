package com.easybuy.easybuy;

import com.easybuy.easybuy.models.*;
import com.easybuy.easybuy.repositories.ClientRepository;
import com.easybuy.easybuy.repositories.ProductRepository;
import com.easybuy.easybuy.repositories.TicketProductRepository;
import com.easybuy.easybuy.repositories.TicketRepository;
import com.easybuy.easybuy.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@SpringBootApplication
public class EasybuyApplication {

	@Autowired
	PasswordEncoder passwordEncoder;


	public static void main(String[] args) {
		SpringApplication.run(EasybuyApplication.class, args);
	}

	@Bean
	public CommandLineRunner initData(ClientRepository clientRepository, ProductRepository productRepository, TicketProductRepository ticketProductRepository, TicketRepository ticketRepository) {
		return (args) ->{
			Client Luisito = new Client("Luis","pirulo","1234444","luis@mindhub.com", passwordEncoder.encode("123"));

			Product television = new Product("tv Samsung","tv moderna led , 65'",800.50,5, List.of("https://images.samsung.com/is/image/samsung/p6pim/ar/qn55q65bagczb/gallery/ar-qled-q60b-qn55q65bagczb-534377581?$650_519_PNG$"),5, LocalDate.now(), List.of(CategoriesEnum.VIDEO));


			Ticket newTicket = new Ticket("001-000001",800.50, LocalDateTime.now());

			TicketProduct newTicketProduct = new TicketProduct(850.50, 1);




			Luisito.addTicket(newTicket);
			newTicket.addTicketProduct(newTicketProduct);
			television.addTicketProduct(newTicketProduct);


			clientRepository.save(Luisito);
			productRepository.save(television);
			ticketRepository.save(newTicket);
			ticketProductRepository.save(newTicketProduct);
		};
	}
}
