package com.easybuy.easybuy;

import com.easybuy.easybuy.models.*;
import com.easybuy.easybuy.repositories.ClientRepository;
import com.easybuy.easybuy.repositories.ProductRepository;
import com.easybuy.easybuy.repositories.PurchaseOrderProductRepository;
import com.easybuy.easybuy.repositories.PurchaseOrderRepository;
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
	public CommandLineRunner initData(ClientRepository clientRepository, ProductRepository productRepository, PurchaseOrderProductRepository purchaseOrderProductRepository, PurchaseOrderRepository purchaseOrderRepository) {
		return (args) ->{

			Client luisito = new Client("Luis","pirulo","1234444","emi.acevedo@gmail.com", passwordEncoder.encode("123"));
			Client admin = new Client("Admin","Admin","4444444","admin@mindhub.com", passwordEncoder.encode("123"));

			luisito.setEnabled(true);

			Product ledTv = new Product("tv Samsung","tv led 65'",800.50,5,5, LocalDate.now(), List.of(CategoriesEnum.VIDEO) );
			Product speaker = new Product("Speaker sony","torre de sonido de gran potencia 3000W",450.0,0,10, LocalDate.now(), List.of(CategoriesEnum.AUDIO) );
			Product laptop= new Product("laptop lenovo mtr550","Lenovo - Flex 3 Chromebook 11.6, HD Touch-screen Laptop - Mediatek MT8183 - 4GB - 64GB eMMC - Abyss Blue",180.0,3,23, LocalDate.now(), List.of(CategoriesEnum.TECNOLOGY) );
			Product cableHdmi = new Product("HDMI Cable - Black","18000 megabytes per second",20.0,0,30, LocalDate.now(), List.of(CategoriesEnum.APPLIANCES) );
			Product bloodPressure = new Product("Blood Pressure","Omron - 10 Series - Wireless Upper Arm Blood Pressure Monitor - Black/White",100.50,8,5, LocalDate.now(), List.of(CategoriesEnum.HEALTHNBEAUTY) );
			Product cuutopiaPlush = new Product("Cuutopia Plush","Star Wars - 7' Cuutopia Plush - Styles May Vary",10.99,0,16, LocalDate.now(), List.of(CategoriesEnum.KIDS) );
			Product sofa = new Product("sofa","Lifestyle Solutions - Hamburg Rolled Arm Sofa - Dark Grey",395.99,5,3, LocalDate.now(), List.of(CategoriesEnum.FURNITURE) );

			PurchaseOrder newPurchaseOrder = new PurchaseOrder("001-000001", ledTv.getPrice()+speaker.getPrice()+ laptop.getPrice()+ cableHdmi.getPrice()+ bloodPressure.getPrice()+ cuutopiaPlush.getPrice()+ sofa.getPrice(), LocalDateTime.now());

			PurchaseOrderProduct ledTvPurchaseOrderProduct = new PurchaseOrderProduct(800.50, 1);
			PurchaseOrderProduct speakerPurchaseOrderProduct = new PurchaseOrderProduct(450.0,1);
			PurchaseOrderProduct laptopPurchaseOrderProduct = new PurchaseOrderProduct(180.0,1);
			PurchaseOrderProduct cablehdmiPurchaseOrderProduct = new PurchaseOrderProduct(20.0,1);
			PurchaseOrderProduct bloodPressurePurchaseOrderProduct = new PurchaseOrderProduct(100.50,1);
			PurchaseOrderProduct cuutopiaPurchaseOrderProduct = new PurchaseOrderProduct(10.99,1);
			PurchaseOrderProduct sofaPurchaseOrderProduct = new PurchaseOrderProduct(395.99,1);



			luisito.addTicket(newPurchaseOrder);
			newPurchaseOrder.addTicketProduct(ledTvPurchaseOrderProduct);
			ledTv.addTicketProduct(ledTvPurchaseOrderProduct);

			newPurchaseOrder.addTicketProduct(speakerPurchaseOrderProduct);
			speaker.addTicketProduct(speakerPurchaseOrderProduct);

			newPurchaseOrder.addTicketProduct(laptopPurchaseOrderProduct);
			laptop.addTicketProduct(laptopPurchaseOrderProduct);

			newPurchaseOrder.addTicketProduct(cablehdmiPurchaseOrderProduct);
			cableHdmi.addTicketProduct(cablehdmiPurchaseOrderProduct);

			newPurchaseOrder.addTicketProduct(bloodPressurePurchaseOrderProduct);
			bloodPressure.addTicketProduct(bloodPressurePurchaseOrderProduct);

			newPurchaseOrder.addTicketProduct(cuutopiaPurchaseOrderProduct);
			cuutopiaPlush.addTicketProduct(cuutopiaPurchaseOrderProduct);

			newPurchaseOrder.addTicketProduct(sofaPurchaseOrderProduct);
			sofa.addTicketProduct(sofaPurchaseOrderProduct);


			clientRepository.save(luisito);
			clientRepository.save(admin);

			productRepository.save(ledTv);
			productRepository.save(speaker);
			productRepository.save(laptop);
			productRepository.save(cableHdmi);
			productRepository.save(bloodPressure);
			productRepository.save(cuutopiaPlush);
			productRepository.save(sofa);

			purchaseOrderRepository.save(newPurchaseOrder);

			purchaseOrderProductRepository.save(ledTvPurchaseOrderProduct);
			purchaseOrderProductRepository.save(speakerPurchaseOrderProduct);
			purchaseOrderProductRepository.save(laptopPurchaseOrderProduct);
			purchaseOrderProductRepository.save(cablehdmiPurchaseOrderProduct);
			purchaseOrderProductRepository.save(bloodPressurePurchaseOrderProduct);
			purchaseOrderProductRepository.save(cuutopiaPurchaseOrderProduct);
			purchaseOrderProductRepository.save(sofaPurchaseOrderProduct);



		};
	}
}
