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

			Client Luisito = new Client("Luis","pirulo","1234444","emi.acevedo.letras@gmail.com", passwordEncoder.encode("123"));

			Product ledTv = new Product("tv Samsung","tv led 65'",800.50,5,5, LocalDate.now(), List.of(CategoriesEnum.VIDEO) );
			Product speaker = new Product("Speaker sony","torre de sonido de gran potencia 3000W",450.0,0,10, LocalDate.now(), List.of(CategoriesEnum.AUDIO) );
			Product laptop= new Product("laptop lenovo mtr550","Lenovo - Flex 3 Chromebook 11.6, HD Touch-screen Laptop - Mediatek MT8183 - 4GB - 64GB eMMC - Abyss Blue",180.0,3,23, LocalDate.now(), List.of(CategoriesEnum.TECNOLOGY) );
			Product cableHdmi = new Product("HDMI Cable - Black","18000 megabytes per second",20.0,0,30, LocalDate.now(), List.of(CategoriesEnum.APPLIANCES) );
			Product bloodPressure = new Product("Blood Pressure","Omron - 10 Series - Wireless Upper Arm Blood Pressure Monitor - Black/White",100.50,8,5, LocalDate.now(), List.of(CategoriesEnum.HEALTHNBEAUTY) );
			Product cuutopiaPlush = new Product("Cuutopia Plush","Star Wars - 7' Cuutopia Plush - Styles May Vary",10.99,0,16, LocalDate.now(), List.of(CategoriesEnum.KIDS) );
			Product sofa = new Product("sofa","Lifestyle Solutions - Hamburg Rolled Arm Sofa - Dark Grey",395.99,5,3, LocalDate.now(), List.of(CategoriesEnum.FURNITURE) );

			Ticket newTicket = new Ticket("001-000001", ledTv.getPrice()+speaker.getPrice()+ laptop.getPrice()+ cableHdmi.getPrice()+ bloodPressure.getPrice()+ cuutopiaPlush.getPrice()+ sofa.getPrice(), LocalDateTime.now());

			TicketProduct ledTvTicketProduct = new TicketProduct(800.50, 1);
			TicketProduct speakerTicketProduct = new TicketProduct(450.0,1);
			TicketProduct laptopTicketProduct = new TicketProduct(180.0,1);
			TicketProduct cablehdmiTicketProduct = new TicketProduct(20.0,1);
			TicketProduct bloodPressureTicketProduct = new TicketProduct(100.50,1);
			TicketProduct cuutopiaTicketProduct = new TicketProduct(10.99,1);
			TicketProduct sofaTicketProduct = new TicketProduct(395.99,1);



			Luisito.addTicket(newTicket);
			newTicket.addTicketProduct(ledTvTicketProduct);
			ledTv.addTicketProduct(ledTvTicketProduct);

			newTicket.addTicketProduct(speakerTicketProduct);
			speaker.addTicketProduct(speakerTicketProduct);

			newTicket.addTicketProduct(laptopTicketProduct);
			laptop.addTicketProduct(laptopTicketProduct);

			newTicket.addTicketProduct(cablehdmiTicketProduct);
			cableHdmi.addTicketProduct(cablehdmiTicketProduct);

			newTicket.addTicketProduct(bloodPressureTicketProduct);
			bloodPressure.addTicketProduct(bloodPressureTicketProduct);

			newTicket.addTicketProduct(cuutopiaTicketProduct);
			cuutopiaPlush.addTicketProduct(cuutopiaTicketProduct);

			newTicket.addTicketProduct(sofaTicketProduct);
			sofa.addTicketProduct(sofaTicketProduct);


			clientRepository.save(Luisito);

			productRepository.save(ledTv);
			productRepository.save(speaker);
			productRepository.save(laptop);
			productRepository.save(cableHdmi);
			productRepository.save(bloodPressure);
			productRepository.save(cuutopiaPlush);
			productRepository.save(sofa);

			ticketRepository.save(newTicket);

			ticketProductRepository.save(ledTvTicketProduct);
			ticketProductRepository.save(speakerTicketProduct);
			ticketProductRepository.save(laptopTicketProduct);
			ticketProductRepository.save(cablehdmiTicketProduct);
			ticketProductRepository.save(bloodPressureTicketProduct);
			ticketProductRepository.save(cuutopiaTicketProduct);
			ticketProductRepository.save(sofaTicketProduct);



		};
	}
}
