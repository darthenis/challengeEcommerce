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
			luisito.setEnabled(true);
			Client admin = new Client("Admin","Admin","4444444","admin@mindhub.com", passwordEncoder.encode("123"));

			luisito.setEnabled(true);



			Product ledTv = new Product("Samsung - 75' Class Q70A",
					"Quantum Processor 4K" +
					"Powered by machine learning, our most powerful 4K processor enhances picture from any source into crisp 4K action in each and every scene."+
					"Motion Xcelerator Turbo Never miss a beat with minimized blur and enhanced motion clarity, and catch all the fast-moving action whether" +
					"you're watching sports or taking advantage of newer Next-Gen gaming capabilities." +
					"Dual LED Embrace every bold detail brought to life with dedicated warm and cool LED " +
					"backlights that optimize colors and dramatically enhance contrast." +
					"100% Color Volume with Quantum Dot" +
					"Quantum Dot powers over a billion brilliant shades of vivid and lifelike colors" +
					" that stay true without fading, regardless of the scene's brightness. Quantum HDR "+
					"Experience the full effect of every image with dynamic tone mapping that draws from a" +
					"wider range of color and brightness than standard HD. Deep blacks, vibrant imagery and " +
					"detail always shine through. Multiple Voice Assistants Control your TV with just your voice. " ,1299.99,5,5, LocalDate.now(), List.of(CategoriesEnum.VIDEO) );
			ledTv.setImgsUrls(List.of("https://pisces.bbystatic.com/image2/BestBuy_US/images/products/6452/6452064cv12d.jpg;maxHeight=640;maxWidth=550","https://pisces.bbystatic.com/image2/BestBuy_US/images/products/6452/6452064cv11d.jpg;maxHeight=640;maxWidth=550","https://pisces.bbystatic.com/image2/BestBuy_US/images/products/6452/6452064_sd.jpg;maxHeight=1000;maxWidth=1000","https://pisces.bbystatic.com/image2/BestBuy_US/images/products/6452/6452064cv20d.jpg;maxHeight=2000;maxWidth=2000"));

			Product speaker = new Product("Bose - S1 Pro Portable Bluetooth ",
					"Whether it's streaming music wirelessly or plugging in a microphone, " +
							"keyboard or guitar, the S1 Pro Portable Bluetooth Speaker and PA System is designed " +
							"to be your go-anywhere music system for nearly any occasion. With rugged, lightweight materials," +
							" the S1 Pro speaker is designed to travel. A convenient carry handle makes it easy to get to " +
							"the party, and once you're there, let the S1 Pro make it look easy. Place it on the ground," +
							"on its side, tilted back, or elevated. The speaker's unique multi-position design and built-in " +
							"Auto EQ ensure that the music always sounds the way it should and that everyone can hear it. " +
							"For the ultimate freedom, play for up to 11 hours using the internal rechargeable lithium-ion " +
							"battery and go wherever the fun is happening. So, when it's your turn to bring the music, " +
							"bring a pro. Bring the Bose S1 Pro.",599.99,0,10, LocalDate.now().minusDays(1), List.of(CategoriesEnum.AUDIO) );
			speaker.setImgsUrls(List.of("https://pisces.bbystatic.com/image2/BestBuy_US/images/products/6223/6223400_sd.jpg;maxHeight=640;maxWidth=550","https://pisces.bbystatic.com/image2/BestBuy_US/images/products/6223/6223400_rd.jpg;maxHeight=640;maxWidth=550","https://pisces.bbystatic.com/image2/BestBuy_US/images/products/6223/6223400ld.jpg;maxHeight=640;maxWidth=550","https://pisces.bbystatic.com/image2/BestBuy_US/images/products/6223/6223400cv11d.jpg;maxHeight=640;maxWidth=550","https://pisces.bbystatic.com/image2/BestBuy_US/images/products/6223/6223400cv12d.jpg;maxHeight=640;maxWidth=550","https://pisces.bbystatic.com/image2/BestBuy_US/images/products/6223/6223400cv17d.jpg;maxHeight=2000;maxWidth=2000"));

			Product laptop= new Product("Lenovo - Flex 3 Chromebook 11.6'",
					"Packs what makes Chromebook so amazing into one svelte 360-degree convertible. " +
					"Versatile and portable, the IdeaPad™ Flex 3 Chromebook measures just 19.6 mm thick and " +
					"weighs 1.2 kg with a full keyboard and spacious trackpad, and can be viewed in tent, tablet," +
					"laptop or stand modes. Watch crisp visuals in high definition with its 10-point touchscreen on " +
							"the 11.6' IPS display while being more productive with help from Google. Just say " +
							"“Hey Google,” to manage tasks, enjoy your entertainment, get answers, or control smart " +
							"home devices.",170.99,3,23, LocalDate.now().minusWeeks(2), List.of(CategoriesEnum.TECNOLOGY) );
			laptop.setImgsUrls(List.of("https://pisces.bbystatic.com/image2/BestBuy_US/images/products/6500/6500337_sd.jpg;maxHeight=640;maxWidth=550","https://pisces.bbystatic.com/image2/BestBuy_US/images/products/6500/6500337_rd.jpg;maxHeight=640;maxWidth=550","https://pisces.bbystatic.com/image2/BestBuy_US/images/products/6500/6500337ld.jpg;maxHeight=640;maxWidth=550","https://pisces.bbystatic.com/image2/BestBuy_US/images/products/6500/6500337cv1d.jpg;maxHeight=640;maxWidth=550","https://pisces.bbystatic.com/image2/BestBuy_US/images/products/6500/6500337cv4d.jpg;maxHeight=640;maxWidth=550","https://pisces.bbystatic.com/image2/BestBuy_US/images/products/6500/6500337cv16d.jpg;maxHeight=2000;maxWidth=2000","https://pisces.bbystatic.com/image2/BestBuy_US/images/products/6500/6500337cv17d.jpg;maxHeight=2000;maxWidth=2000"));

			Product cableHdmi = new Product("Rocketfish™ - 8' 4K UltraHD/HDR ",
					"Install your home theater system with this in-wall Rocketfish 4K UltraHD cable, " +
					"and enjoy the latest technology your television offers. " +
					"The triple-layer shielding on this cable defends against interference so" +
					" you get the clearest picture possible. This Rocketfish 4K UltraHD cable is " +
					"certified for in-wall installations, making it an ideal cable for home theaters and " +
					"entertainment centers.Enjoy stunning visuals and crisp, clear audio with this in-wall " +
					"certified Rocketfish™ HDMI cable which lets you connect an HDMI component to an HDTV or " +
					"projector and supports 4K Ultra HD & 1080P resolution as well as HDR and advanced audio " +
					"formats like Dolby Atmos.",20.0,0,30, LocalDate.now(), List.of(CategoriesEnum.APPLIANCES) );
			cableHdmi.setImgsUrls(List.of("https://pisces.bbystatic.com/image2/BestBuy_US/images/products/3720/3720011cv14d.jpg;maxHeight=2000;maxWidth=2000","https://pisces.bbystatic.com/image2/BestBuy_US/images/products/3720/3720011_sd.jpg;maxHeight=640;maxWidth=550","https://pisces.bbystatic.com/image2/BestBuy_US/images/products/3720/3720011_rd.jpg;maxHeight=640;maxWidth=550","https://pisces.bbystatic.com/image2/BestBuy_US/images/products/3720/3720011cv1d.jpg;maxHeight=120;maxWidth=120"));


			Product bloodPressure = new Product("Omron - 10 Series ",
					"The OMRON 10 Series Wireless Bluetooth® Upper Arm home blood pressure monitor has a " +
					"horizontally designed dual-display monitor and stores up to 200 readings for two users. " +
					"The dual LCD display shows previous and current readings for easier comparison, while the " +
					"advanced accuracy technology measures more data points for more consistent blood pressure readings. " +
					"This Omron wireless upper arm blood pressure monitor features TruRead technology that lets you take " +
					"three consecutive readings at customized intervals.",100.50,8,5, LocalDate.now().minusDays(5), List.of(CategoriesEnum.HEALTH) );
			bloodPressure.setImgsUrls(List.of("https://pisces.bbystatic.com/image2/BestBuy_US/images/products/6370/6370325cv12d.jpg;maxHeight=640;maxWidth=550","https://pisces.bbystatic.com/image2/BestBuy_US/images/products/6370/6370325cv13d.jpg;maxHeight=640;maxWidth=550","https://pisces.bbystatic.com/image2/BestBuy_US/images/products/6370/6370325_sd.jpg;maxHeight=640;maxWidth=550","https://pisces.bbystatic.com/image2/BestBuy_US/images/products/6370/6370325cv11d.jpg;maxHeight=640;maxWidth=550"));


			Product cuutopiaPlush = new Product("Star Wars - 7'",
					"Star Wars fans of all ages will fall in love with Grogu all over again! " +
							"At 11 inches tall, Squeeze & Blink Grogu has a soft plush body and detailed vinyl head, " +
							"with details authentic to the popular series The Mandalorian and The Book of Boba Fett. " +
							"Bring Grogu to life with cuddles -- squeeze one side of the soft doll's belly to see his eyes blink and hear soft life-like vocals; " +
							"push down on the other side to raise his hand in a force-like pose; " +
							"press both sides together to activate all movements and sounds at the same time for the full effect! " +
							"An affectionate and heart-warming plush, Grogu makes it easy to reenact favorite moments from the series or create " +
							"new adventures and makes a great gift for fans and collectors of all ages.",10.99,0,16, LocalDate.now(), List.of(CategoriesEnum.KIDS) );
			cuutopiaPlush.setImgsUrls(List.of("https://pisces.bbystatic.com/image2/BestBuy_US/images/products/6514/6514490_sd.jpg","https://pisces.bbystatic.com/image2/BestBuy_US/images/products/6514/6514490cv12d.jpg;maxHeight=2000;maxWidth=2000", "https://pisces.bbystatic.com/image2/BestBuy_US/images/products/6514/6514490cv16d.jpg;maxHeight=2000;maxWidth=2000","https://pisces.bbystatic.com/image2/BestBuy_US/images/products/6514/6514490_rd.jpg;maxHeight=2000;maxWidth=2000","https://pisces.bbystatic.com/image2/BestBuy_US/images/products/6514/6514490cv11d.jpg;maxHeight=2000;maxWidth=2000"));

			Product sofa = new Product("Lifestyle Solutions ",
					"The Hamburg Loveseat is the two seat option of this stationary set designed to fit your space and style. " +
							"Built for lasting quality, the Hamburg combines form, " +
							"function and ease of assembly designed for the eclectic life. " +
							"The design lines and scale of the Hamburg Loveseat brings a modern touch to spaces large and " +
							"small with versatile elegance. High density foam cushions with rolled arms and beautifully button " +
							"tufted back hug your body with comfort and is poised to transform any room into your favorite place to " +
							"let go of the day and relax in pure bliss. Combining Dark Grey premium microfiber upholstered fabric and " +
							"solid eucalyptus wooden frame, this durable plush loveseat invites you to rest in the knowledge of years of " +
							"enjoyment to come.",222.99,5,3, LocalDate.now().minusYears(1), List.of(CategoriesEnum.FURNITURE) );
			sofa.setImgsUrls(List.of("https://pisces.bbystatic.com/image2/BestBuy_US/images/products/6479/6479575_sd.jpg;maxHeight=640;maxWidth=550","https://pisces.bbystatic.com/image2/BestBuy_US/images/products/6479/6479575_rd.jpg;maxHeight=640;maxWidth=550","https://pisces.bbystatic.com/image2/BestBuy_US/images/products/6479/6479575cv11d.jpg;maxHeight=640;maxWidth=550","https://pisces.bbystatic.com/image2/BestBuy_US/images/products/6479/6479575cv13d.jpg;maxHeight=640;maxWidth=550","https://pisces.bbystatic.com/image2/BestBuy_US/images/products/6479/6479575cv12d.jpg;maxHeight=120;maxWidth=120"));


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
