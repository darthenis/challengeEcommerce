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

			Client Luisito = new Client("Luis","pirulo","1234444","emi.acevedo@gmail.com", passwordEncoder.encode("123"));
			Luisito.setEnabled(true);
			Client admin = new Client("Admin","Admin","4444444","admin@mindhub.com", passwordEncoder.encode("123"));

			Product ledTv = new Product("tv Samsung","tv led 65'",800.50,5,5, LocalDate.now(), List.of(CategoriesEnum.VIDEO) );

			Product speaker = new Product("Speaker sony","torre de sonido de gran potencia 3000W",450.0,0,10, LocalDate.now(), List.of(CategoriesEnum.AUDIO) );
			Product laptop= new Product("laptop lenovo mtr550","Lenovo - Flex 3 Chromebook 11.6, HD Touch-screen Laptop - Mediatek MT8183 - 4GB - 64GB eMMC - Abyss Blue",180.0,3,23, LocalDate.now(), List.of(CategoriesEnum.TECNOLOGY) );
			Product cableHdmi = new Product("HDMI Cable - Black","18000 megabytes per second",20.0,0,30, LocalDate.now(), List.of(CategoriesEnum.APPLIANCES) );
			Product bloodPressure = new Product("Blood Pressure","Omron - 10 Series - Wireless Upper Arm Blood Pressure Monitor - Black/White",100.50,8,5, LocalDate.now(), List.of(CategoriesEnum.HEALTHNBEAUTY) );
			Product cuutopiaPlush = new Product("Star Wars - 7' Cuutopia Plush - Styles May Vary",
					"Star Wars fans of all ages will fall in love with Grogu all over again! " +
							"At 11 inches tall, Squeeze & Blink Grogu has a soft plush body and detailed vinyl head, " +
							"with details authentic to the popular series The Mandalorian and The Book of Boba Fett. " +
							"Bring Grogu to life with cuddles -- squeeze one side of the soft doll's belly to see his eyes blink and hear soft life-like vocals; " +
							"push down on the other side to raise his hand in a force-like pose; " +
							"press both sides together to activate all movements and sounds at the same time for the full effect! " +
							"An affectionate and heart-warming plush, Grogu makes it easy to reenact favorite moments from the series or create " +
							"new adventures and makes a great gift for fans and collectors of all ages.",10.99,0,16, LocalDate.now(), List.of(CategoriesEnum.KIDS) );
			cuutopiaPlush.setImgsUrls(List.of("https://pisces.bbystatic.com/image2/BestBuy_US/images/products/6514/6514490_sd.jpg","https://pisces.bbystatic.com/image2/BestBuy_US/images/products/6514/6514490cv12d.jpg;maxHeight=2000;maxWidth=2000", "https://pisces.bbystatic.com/image2/BestBuy_US/images/products/6514/6514490cv16d.jpg;maxHeight=2000;maxWidth=2000","https://pisces.bbystatic.com/image2/BestBuy_US/images/products/6514/6514490_rd.jpg;maxHeight=2000;maxWidth=2000","https://pisces.bbystatic.com/image2/BestBuy_US/images/products/6514/6514490cv11d.jpg;maxHeight=2000;maxWidth=2000"));
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
			clientRepository.save(admin);

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
