package com.easybuy.easybuy;

import com.easybuy.easybuy.models.*;
import com.easybuy.easybuy.repositories.*;
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
	public CommandLineRunner initData(ClientRepository clientRepository, ProductRepository productRepository, PurchaseOrderProductRepository purchaseOrderProductRepository, PurchaseOrderRepository purchaseOrderRepository, RateRepository rateRepository) {
		return (args) ->{

				Client luisito = new Client("Luis","pirulo","1234444","emi.acevedo.letras@gmail.com", passwordEncoder.encode("123"));
			luisito.setEnabled(true);
			Client client1 = new Client("Juan Antonio", "Pérez", "555-1234", "japerez@email.com", passwordEncoder.encode("123"));
			client1.setEnabled(true);
			Client client2 = new Client("María Fernanda", "Gómez", "555-5678", "mfgomez@email.com",  passwordEncoder.encode("123"));
			client2.setEnabled(true);
			Client client3 = new Client("Carlos Eduardo", "Ramírez", "555-9012", "ceramirez@email.com",  passwordEncoder.encode("123"));
			client3.setEnabled(true);
			Client client4 = new Client("Ana Gabriela", "Torres", "555-3456", "agtorres@email.com",  passwordEncoder.encode("123"));
			client4.setEnabled(true);
			Client client5 = new Client("Pedro José", "González", "555-7890", "pjgonzalez@email.com",  passwordEncoder.encode("123"));
			client5.setEnabled(true);
			Client client6 = new Client("Laura Isabel", "García", "555-2345", "ligarcia@email.com",  passwordEncoder.encode("123"));
			client6.setEnabled(true);
			Client client7 = new Client("José Luis", "Sánchez", "555-6789", "jlsanchez@email.com",  passwordEncoder.encode("123"));
			client7.setEnabled(true);
			Client client8 = new Client("Carmen Rosa", "Rodríguez", "555-0123", "crrodriguez@email.com",  passwordEncoder.encode("123"));
			client8.setEnabled(true);
			Client client9 = new Client("Miguel Ángel", "Ortiz", "555-4567", "maortiz@email.com",  passwordEncoder.encode("123"));
			client9.setEnabled(true);
			Client client10 = new Client("Isabel Cristina", "Vargas", "555-8901", "icvargas@email.com",  passwordEncoder.encode("123"));
			client10.setEnabled(true);

			Client admin = new Client("Admin","Admin","4444444","admin@mindhub.com",  passwordEncoder.encode("123"));
			admin.setEnabled(true);





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
					"detail always shine through. Multiple Voice Assistants Control your TV with just your voice. " ,1299.99,5,5, LocalDateTime.now().plusDays(12), List.of(CategoriesEnum.VIDEO) );
			ledTv.setImgsUrls(List.of("https://pisces.bbystatic.com/image2/BestBuy_US/images/products/6452/6452064cv12d.jpg;maxHeight=640;maxWidth=550","https://pisces.bbystatic.com/image2/BestBuy_US/images/products/6452/6452064cv11d.jpg;maxHeight=640;maxWidth=550","https://pisces.bbystatic.com/image2/BestBuy_US/images/products/6452/6452064_sd.jpg;maxHeight=1000;maxWidth=1000","https://pisces.bbystatic.com/image2/BestBuy_US/images/products/6452/6452064cv20d.jpg;maxHeight=2000;maxWidth=2000"));
			Rate rateTv = new Rate("Buying a television can be an important and exciting decision, particularly if you're looking to upgrade your home entertainment. It's important to consider factors such as screen size, picture quality, connectivity features, and budget before making a decision. Additionally, be sure to research brands and read reviews from other buyers to ensure you're making the best decision for your personal needs and preferences. With the right choice, a new television can provide an enhanced entertainment experience to enjoy with friends and family.",StarsEnum.FOUR);
			Rate rateTv2 = new Rate(" I recently bought a new 55-inch, 4K resolution television after researching brands and reading reviews from other buyers. I am very satisfied with the image quality, especially when watching movies or TV shows in high definition. The connectivity is excellent, allowing me to access a wide variety of online content, including my favorite streaming services. The installation was easy and quick, and the remote control is intuitive and easy to use. Overall, I would recommend this model to anyone looking for a high-quality television.",StarsEnum.FIVE);

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
							"bring a pro. Bring the Bose S1 Pro.",599.99,0,10, LocalDateTime.now().plusDays(1), List.of(CategoriesEnum.AUDIO));
			speaker.setImgsUrls(List.of("https://pisces.bbystatic.com/image2/BestBuy_US/images/products/6223/6223400_sd.jpg;maxHeight=640;maxWidth=550","https://pisces.bbystatic.com/image2/BestBuy_US/images/products/6223/6223400_rd.jpg;maxHeight=640;maxWidth=550","https://pisces.bbystatic.com/image2/BestBuy_US/images/products/6223/6223400ld.jpg;maxHeight=640;maxWidth=550","https://pisces.bbystatic.com/image2/BestBuy_US/images/products/6223/6223400cv11d.jpg;maxHeight=640;maxWidth=550","https://pisces.bbystatic.com/image2/BestBuy_US/images/products/6223/6223400cv12d.jpg;maxHeight=640;maxWidth=550","https://pisces.bbystatic.com/image2/BestBuy_US/images/products/6223/6223400cv17d.jpg;maxHeight=2000;maxWidth=2000"));
			Rate speakerRate = new Rate("I recently bought a compact and portable Bluetooth speaker that provides impressive sound quality and fast Bluetooth connectivity. The battery lasts for several hours and charges quickly. I recommend it to anyone looking for a high-quality portable option to enjoy their favorite music anytime, anywhere.",StarsEnum.FIVE);
			Rate speakerRate2= new Rate("I purchased a high-quality Bluetooth speaker with a modern and sleek design. The sound quality is excellent, with deep bass and clear treble, and the Bluetooth connectivity is fast and stable. The battery lasts for hours and charges quickly. I recommend it for anyone looking for an unparalleled audio experience anywhere, anytime.",StarsEnum.FOUR);

			Product airPods = new Product("Apple - AirPods Pro","AirPods Pro feature up to 2x more Active Noise Cancellation,¹ plus Adaptive Transparency, and Personalized Spatial Audio with dynamic head tracking for immersive sound.² Now with multiple ear tips (XS, S, M, L) and up to 6 hours of listening time.³",249.99,5,15,LocalDateTime.now().minusDays(5),List.of(CategoriesEnum.AUDIO));
			airPods.setImgsUrls(List.of("https://pisces.bbystatic.com/image2/BestBuy_US/images/products/4900/4900964_sd.jpg;maxHeight=640;maxWidth=550","https://pisces.bbystatic.com/image2/BestBuy_US/images/products/4900/4900964_rd.jpg;maxHeight=2000;maxWidth=2000","https://pisces.bbystatic.com/image2/BestBuy_US/images/products/4900/4900964ld.jpg;maxHeight=120;maxWidth=120","https://pisces.bbystatic.com/image2/BestBuy_US/images/products/4900/4900964cv11d.jpg;maxHeight=120;maxWidth=120","https://pisces.bbystatic.com/image2/BestBuy_US/images/products/4900/4900964cv12d.jpg;maxHeight=2000;maxWidth=2000"));
			Rate airpodsRate = new Rate("I recently purchased the Airpods and they are fantastic! The sound quality is amazing and the Bluetooth connection is seamless. They fit comfortably in my ears and the battery life is impressive. I highly recommend them to anyone in the market for wireless earbuds.", StarsEnum.FIVE);
			Rate airpodsRate2 = new Rate("I am so happy with my purchase of the Airpods! The sound quality is incredible and they are so easy to use. They fit perfectly in my ears and I can wear them for hours without any discomfort. The battery life is great too. I would definitely recommend them to anyone who wants high-quality wireless earbuds.", StarsEnum.FOUR);



			Product laptop= new Product("Lenovo - Flex 3 Chromebook 11.6'",
					"Packs what makes Chromebook so amazing into one svelte 360-degree convertible. " +
					"Versatile and portable, the IdeaPad™ Flex 3 Chromebook measures just 19.6 mm thick and " +
					"weighs 1.2 kg with a full keyboard and spacious trackpad, and can be viewed in tent, tablet," +
					"laptop or stand modes. Watch crisp visuals in high definition with its 10-point touchscreen on " +
							"the 11.6' IPS display while being more productive with help from Google. Just say " +
							"“Hey Google,” to manage tasks, enjoy your entertainment, get answers, or control smart " +
							"home devices.",170.99,3,23, LocalDateTime.now().plusWeeks(2), List.of(CategoriesEnum.TECNOLOGY) );
			laptop.setImgsUrls(List.of("https://pisces.bbystatic.com/image2/BestBuy_US/images/products/6500/6500337_sd.jpg;maxHeight=640;maxWidth=550","https://pisces.bbystatic.com/image2/BestBuy_US/images/products/6500/6500337_rd.jpg;maxHeight=640;maxWidth=550","https://pisces.bbystatic.com/image2/BestBuy_US/images/products/6500/6500337ld.jpg;maxHeight=640;maxWidth=550","https://pisces.bbystatic.com/image2/BestBuy_US/images/products/6500/6500337cv1d.jpg;maxHeight=640;maxWidth=550","https://pisces.bbystatic.com/image2/BestBuy_US/images/products/6500/6500337cv4d.jpg;maxHeight=640;maxWidth=550","https://pisces.bbystatic.com/image2/BestBuy_US/images/products/6500/6500337cv16d.jpg;maxHeight=2000;maxWidth=2000","https://pisces.bbystatic.com/image2/BestBuy_US/images/products/6500/6500337cv17d.jpg;maxHeight=2000;maxWidth=2000"));
			Rate laptopRate = new Rate("I recently purchased a new laptop and I am blown away by its speed and performance. The processor is lightning fast and can handle multiple tasks without slowing down. The display is vibrant and clear, making it perfect for streaming videos or working on graphics-intensive projects. The battery life is impressive, lasting for several hours even with heavy use. Overall, I highly recommend this laptop for anyone in need of a reliable and powerful machine.", StarsEnum.FIVE);
			Rate laptopRate2 = new Rate("I bought a budget-friendly laptop and while it gets the job done, it falls short in some areas. The processor is not as fast as I would like, which makes it difficult to multitask or run more demanding programs. The display is decent, but not as sharp or vibrant as higher-end models. However, the battery life is impressive and lasts for several hours on a single charge. If you're looking for a basic laptop for everyday use, this one could work for you, but if you need something more powerful and feature-rich, you may want to consider a higher-end model.", StarsEnum.THREE);

			Product pcApple = new Product("MacBook Air 13.3'",
					"Apple’s thinnest and lightest notebook gets supercharged with the Apple M1 chip." +
							" Tackle your projects with the blazing-fast 8-core CPU. " +
							"Take graphics-intensive apps and games to the next level with the 7-core GPU. " +
							"And accelerate machine learning tasks with the 16-core Neural Engine. All with a silent, " +
							"fanless design and the longest battery life ever — up to 18 hours.¹ MacBook Air. Still perfectly portable. " +
							"Just a lot more powerful.",799.99,0,20,LocalDateTime.now().minusDays(1),List.of(CategoriesEnum.TECNOLOGY));
			pcApple.setImgsUrls(List.of("https://pisces.bbystatic.com/image2/BestBuy_US/images/products/6418/6418599_sd.jpg;maxHeight=2000;maxWidth=2000","https://pisces.bbystatic.com/image2/BestBuy_US/images/products/6418/6418599cv11d.jpg;maxHeight=2000;maxWidth=2000","https://pisces.bbystatic.com/image2/BestBuy_US/images/products/6418/6418599cv12d.jpg;maxHeight=2000;maxWidth=2000","https://pisces.bbystatic.com/image2/BestBuy_US/images/products/6418/6418599cv13d.jpg;maxHeight=2000;maxWidth=2000","https://pisces.bbystatic.com/image2/BestBuy_US/images/products/6418/6418599cv15d.jpg;maxHeight=2000;maxWidth=2000"));
			Rate macbookRate = new Rate("I recently purchased a MacBook Air and I couldn't be happier with my choice. The computer is super lightweight and easy to carry around, making it perfect for both work and personal use. The battery life is impressive, lasting for several hours without needing a recharge. The speed and performance of the computer are top-notch, allowing me to easily run multiple applications at once without any lag. I highly recommend the MacBook Air to anyone in the market for a new computer.", StarsEnum.FIVE);
			Rate macbookRate2 = new Rate("I upgraded to the MacBook Air and it was the best decision I've made. The design is sleek and modern, and the computer is extremely fast and efficient. The battery life lasts for hours on end, so I never have to worry about running out of juice when I'm out and about. The keyboard is also very comfortable to type on, making it great for long typing sessions. Overall, I'm extremely satisfied with my purchase and would highly recommend the MacBook Air to anyone in need of a reliable, high-performance laptop.", StarsEnum.FOUR);




			Product cableHdmi = new Product("Rocketfish™ - 8' 4K UltraHD/HDR ",
					"Install your home theater system with this in-wall Rocketfish 4K UltraHD cable, " +
					"and enjoy the latest technology your television offers. " +
					"The triple-layer shielding on this cable defends against interference so" +
					" you get the clearest picture possible. This Rocketfish 4K UltraHD cable is " +
					"certified for in-wall installations, making it an ideal cable for home theaters and " +
					"entertainment centers.Enjoy stunning visuals and crisp, clear audio with this in-wall " +
					"certified Rocketfish™ HDMI cable which lets you connect an HDMI component to an HDTV or " +
					"projector and supports 4K Ultra HD & 1080P resolution as well as HDR and advanced audio " +
					"formats like Dolby Atmos.",20.0,0,30, LocalDateTime.now(), List.of(CategoriesEnum.APPLIANCE) );
			cableHdmi.setImgsUrls(List.of("https://pisces.bbystatic.com/image2/BestBuy_US/images/products/3720/3720011cv14d.jpg;maxHeight=2000;maxWidth=2000","https://pisces.bbystatic.com/image2/BestBuy_US/images/products/3720/3720011_sd.jpg;maxHeight=640;maxWidth=550","https://pisces.bbystatic.com/image2/BestBuy_US/images/products/3720/3720011_rd.jpg;maxHeight=640;maxWidth=550","https://pisces.bbystatic.com/image2/BestBuy_US/images/products/3720/3720011cv1d.jpg;maxHeight=120;maxWidth=120"));
			Rate hdmiRate = new Rate("This HDMI cable is amazing! It delivers crystal clear picture quality and sound, and supports high-speed data transfer. The cable is sturdy and durable, and the connectors fit snugly into the ports without any wobbling or disconnecting. The length of the cable is perfect for my setup, and I appreciate the included cable ties to keep it organized. Overall, I am extremely satisfied with my purchase and would recommend this HDMI cable to anyone looking for a reliable and high-quality option.", StarsEnum.FIVE);
			Rate hdmiRate2 = new Rate("I bought this HDMI cable hoping to upgrade my home theater setup, but unfortunately it fell short of my expectations. The picture quality was decent, but not as sharp or vibrant as I had hoped for. The sound quality was average at best, and I experienced some occasional drops in signal. The cable itself is thin and flimsy, and the connectors feel loose and wobbly in the ports. While it is a budget-friendly option, I would recommend spending a bit more for a higher-quality HDMI cable.", StarsEnum.TWO);


			Product bloodPressure = new Product("Omron - 10 Series ",
					"The OMRON 10 Series Wireless Bluetooth® Upper Arm home blood pressure monitor has a " +
					"horizontally designed dual-display monitor and stores up to 200 readings for two users. " +
					"The dual LCD display shows previous and current readings for easier comparison, while the " +
					"advanced accuracy technology measures more data points for more consistent blood pressure readings. " +
					"This Omron wireless upper arm blood pressure monitor features TruRead technology that lets you take " +
					"three consecutive readings at customized intervals.",100.50,8,5, LocalDateTime.now().plusDays(5), List.of(CategoriesEnum.HEALTH) );
			bloodPressure.setImgsUrls(List.of("https://pisces.bbystatic.com/image2/BestBuy_US/images/products/6370/6370325cv12d.jpg;maxHeight=640;maxWidth=550","https://pisces.bbystatic.com/image2/BestBuy_US/images/products/6370/6370325cv13d.jpg;maxHeight=640;maxWidth=550","https://pisces.bbystatic.com/image2/BestBuy_US/images/products/6370/6370325_sd.jpg;maxHeight=640;maxWidth=550","https://pisces.bbystatic.com/image2/BestBuy_US/images/products/6370/6370325cv11d.jpg;maxHeight=640;maxWidth=550"));
			Rate bpRate = new Rate("I recently purchased this blood pressure monitor and I am extremely satisfied with its performance. It is easy to use and provides accurate readings every time. The display is large and easy to read, and it stores previous readings for tracking purposes. The cuff is comfortable and fits snugly, and the device itself is lightweight and easy to store. Overall, I highly recommend this blood pressure monitor for anyone in need of a reliable and user-friendly option.", StarsEnum.FIVE);
			Rate bpRate2 = new Rate("I purchased this blood pressure monitor hoping to monitor my blood pressure at home, but unfortunately it was not a great fit for me. The cuff was uncomfortable and did not fit well, which led to inaccurate readings. The display was difficult to read, and I found it confusing to use. While it is a budget-friendly option, I would recommend spending more for a higher-quality blood pressure monitor with more reliable and accurate readings.", StarsEnum.TWO);

			Product cuutopiaPlush = new Product("Star Wars - 7'",
					"Star Wars fans of all ages will fall in love with Grogu all over again! " +
							"At 11 inches tall, Squeeze & Blink Grogu has a soft plush body and detailed vinyl head, " +
							"with details authentic to the popular series The Mandalorian and The Book of Boba Fett. " +
							"Bring Grogu to life with cuddles -- squeeze one side of the soft doll's belly to see his eyes blink and hear soft life-like vocals; " +
							"push down on the other side to raise his hand in a force-like pose; " +
							"press both sides together to activate all movements and sounds at the same time for the full effect! " +
							"An affectionate and heart-warming plush, Grogu makes it easy to reenact favorite moments from the series or create " +
							"new adventures and makes a great gift for fans and collectors of all ages.",10.99,0,16, LocalDateTime.now(), List.of(CategoriesEnum.KIDS) );
			cuutopiaPlush.setImgsUrls(List.of("https://pisces.bbystatic.com/image2/BestBuy_US/images/products/6514/6514490_sd.jpg","https://pisces.bbystatic.com/image2/BestBuy_US/images/products/6514/6514490cv12d.jpg;maxHeight=2000;maxWidth=2000", "https://pisces.bbystatic.com/image2/BestBuy_US/images/products/6514/6514490cv16d.jpg;maxHeight=2000;maxWidth=2000","https://pisces.bbystatic.com/image2/BestBuy_US/images/products/6514/6514490_rd.jpg;maxHeight=2000;maxWidth=2000","https://pisces.bbystatic.com/image2/BestBuy_US/images/products/6514/6514490cv11d.jpg;maxHeight=2000;maxWidth=2000"));
			Rate starWarsPlushRate = new Rate("I recently purchased this Star Wars plush and I am absolutely in love with it! The quality is amazing, with soft and durable materials that make it both cuddly and sturdy. The attention to detail is impressive, with accurate character designs and vivid colors. It's the perfect addition to any Star Wars fan's collection, and I highly recommend it to anyone looking for a high-quality and adorable plush toy.", StarsEnum.FIVE);
			Rate starWarsPlushRate2 = new Rate("I bought this Star Wars plush hoping to add to my collection, but unfortunately it did not meet my expectations. The quality was not as good as I had hoped, with thin and flimsy materials that didn't feel very durable. The character design was not as accurate as I had hoped, and the colors were not as vibrant as they appeared in the photos. While it's a budget-friendly option, I would recommend spending more for a higher-quality Star Wars plush.", StarsEnum.TWO);

			Product sofa = new Product("Lifestyle Solutions ",
					"The Hamburg Loveseat is the two seat option of this stationary set designed to fit your space and style. " +
							"Built for lasting quality, the Hamburg combines form, " +
							"function and ease of assembly designed for the eclectic life. " +
							"The design lines and scale of the Hamburg Loveseat brings a modern touch to spaces large and " +
							"small with versatile elegance. High density foam cushions with rolled arms and beautifully button " +
							"tufted back hug your body with comfort and is poised to transform any room into your favorite place to " +
							"let go of the day and relax in pure bliss. Combining Dark Grey premium microfiber upholstered fabric and " +
							"solid eucalyptus wooden frame, this durable plush loveseat invites you to rest in the knowledge of years of " +
							"enjoyment to come.",222.99,5,3, LocalDateTime.now().plusYears(1), List.of(CategoriesEnum.FURNITURE) );
			sofa.setImgsUrls(List.of("https://pisces.bbystatic.com/image2/BestBuy_US/images/products/6479/6479575_sd.jpg;maxHeight=640;maxWidth=550","https://pisces.bbystatic.com/image2/BestBuy_US/images/products/6479/6479575_rd.jpg;maxHeight=640;maxWidth=550","https://pisces.bbystatic.com/image2/BestBuy_US/images/products/6479/6479575cv11d.jpg;maxHeight=640;maxWidth=550","https://pisces.bbystatic.com/image2/BestBuy_US/images/products/6479/6479575cv13d.jpg;maxHeight=640;maxWidth=550","https://pisces.bbystatic.com/image2/BestBuy_US/images/products/6479/6479575cv12d.jpg;maxHeight=120;maxWidth=120"));
			Rate sofaRate = new Rate("I recently purchased this sofa and I am extremely satisfied with my purchase. The quality is amazing, with durable and comfortable materials that make it perfect for lounging or entertaining. The design is sleek and modern, and it fits perfectly with my home decor. The assembly was straightforward and easy to do on my own. I highly recommend this sofa to anyone in need of a high-quality and stylish addition to their home.", StarsEnum.FIVE);
			Rate sofaRate2 = new Rate("I purchased this sofa hoping to upgrade my living room, but unfortunately it fell short of my expectations. The quality was not as good as I had hoped, with thin and flimsy materials that didn't feel very durable. The design was not as comfortable as I had hoped, and the color was not as vibrant as it appeared in the photos. Assembly was difficult and required the help of a professional. While it's a budget-friendly option, I would recommend spending more for a higher-quality sofa.", StarsEnum.TWO);

			Product sofaXl = new Product("Lovesac - 4 Seats" , "The World’s Most Adaptable Couch – Sactionals were specially designed to provide style, comfort," +
					" and peace of mind. Not only do they easily change their shape to perfectly accommodate every occasion, " +
					"they can transform their color and style to always match your tastes. " +
					"The Sactionals product platform provides peace of mind where other couches can’t. " +
					"Spills, a growing family, or moving to a new home shouldn’t mean that your couch ends up in a landfill. " +
					"Instead, we designed Sactionals to evolve as your life changes, enabling you to enjoy even more little moments along the way. " +
					"Get Sactionals and experience Total Comfort. ***UPGRADE THIS SACTIONALS SETUP WITH LOVESAC STEALTHTECH***" +
					" The Sactionals StealthTech Sound + Charge System features immersive surround sound by Harman Kardon and " +
					"integrated wireless charging, elegantly embedded inside Sactionals and completely hidden from view.",239.00,20,2,LocalDateTime.now().minusDays(10),List.of(CategoriesEnum.FURNITURE));
			sofaXl.setImgsUrls(List.of("https://pisces.bbystatic.com/image2/BestBuy_US/images/products/6502/6502395_sd.jpg;maxHeight=640;maxWidth=550","https://pisces.bbystatic.com/image2/BestBuy_US/images/products/6502/6502395_rd.jpg;maxHeight=640;maxWidth=550","https://pisces.bbystatic.com/image2/BestBuy_US/images/products/6502/6502395cv1d.jpg;maxHeight=640;maxWidth=550","https://pisces.bbystatic.com/image2/BestBuy_US/images/products/6502/6502395ld.jpg;maxHeight=640;maxWidth=550","https://pisces.bbystatic.com/image2/BestBuy_US/images/products/6502/6502395cv3d.jpg;maxHeight=640;maxWidth=550"));
			Rate sofaXlRate1 = new Rate("I recently bought an XL sofa that has quickly become the centerpiece of my living room. The sofa is incredibly comfortable and the perfect size for my family to relax on together. I also love the sleek and modern design, which complements my home decor perfectly. Overall, I am extremely satisfied with my purchase and highly recommend this sofa to anyone looking for a comfortable and stylish seating option.", StarsEnum.FIVE);
			Rate sofaXlRate2 = new Rate("I was looking for a large and comfortable sofa to accommodate my growing family, and I found the perfect one with this XL sofa. The sofa is spacious and comfortable, with plenty of room for everyone to relax on. The design is also very modern and fits well with my home decor. I highly recommend this sofa to anyone looking for a high-quality, stylish, and comfortable seating option.", StarsEnum.FOUR);




			Product desk = new Product("Sauder - Desk",
					"It's time for a style upgrade. Get motivated to take on all your major projects with the charming good looks and " +
					"industrial-inspired design of this L-shaped desk from the Boulevard Café collection. Its spacious workspace provides " +
					"you with all the room you need for your must-have desk essentials like your laptop, collection of notepads and your " +
					"hot cup of coffee. It features two drawers with full extension slides for easy access storage for items like pencils " +
					"and pens. Behind its metal door featuring a wire mesh front is a storage area that includes an adjustable shelf that " +
					"can be moved to different heights to create flexible storage for items of all shapes and sizes. If that isn't enough, " +
					"it also features elevated shelving for additional storage options or a display area for your favorite décor items. ",469.99,20,2,LocalDateTime.now().minusYears(1),List.of(CategoriesEnum.FURNITURE));
			desk.setImgsUrls(List.of("http://localhost:8080/web/assets/img/sillon-emi.png"));
			Rate deskRate1 = new Rate("I recently purchased a desk for my home office and it has been a great addition to my workspace. The desk is sturdy and well-built, providing ample workspace for my computer, paperwork, and other office essentials. The design is sleek and modern, fitting in perfectly with the rest of my office decor. Overall, I highly recommend this desk to anyone looking for a functional and stylish workspace.", StarsEnum.FIVE);
			Rate deskRate2 = new Rate("I am extremely happy with my purchase of this desk. The assembly process was straightforward and the desk is very durable, standing up well to daily use. The desk also has a convenient built-in storage system that keeps my workspace organized and clutter-free. I highly recommend this desk to anyone looking for a functional and stylish addition to their home office or workspace.", StarsEnum.FOUR);



			Product bed = new Product("CorLiving - Nova Ridge Tufted ",
					"This beautifully upholstered fabric bed is the complete package, all delivered to you in 1 package. Complete your bedroom space with the stylish diamond button-tufted padded headboard with matching upholstered fabric bed frame. Equipped with durable slats and adjustable support legs designed with comfort and stability in mind, " +
					"enjoy a restful night's sleep on this trendy extension to your bedroom or guestroom.",421.99,15,5,LocalDateTime.now().minusWeeks(1),List.of(CategoriesEnum.FURNITURE));
			bed.setImgsUrls(List.of("http://localhost:8080/web/assets/img/camaa.png"));
			Rate bedRate1 = new Rate("I recently purchased a new bed and it has been a game changer for my sleep. The bed is incredibly comfortable and supportive, providing me with a great night's sleep every night. The design is modern and sleek, and the construction is very sturdy and durable. I highly recommend this bed to anyone in need of a comfortable and stylish sleeping solution.", StarsEnum.FIVE);
			Rate bedRate2 = new Rate("I am extremely satisfied with my purchase of this bed. The bed is well-constructed and provides a comfortable sleeping experience. The design is also very stylish and fits in well with my bedroom decor. Overall, I highly recommend this bed to anyone in the market for a new sleeping solution.", StarsEnum.FOUR);


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


			luisito.addRate(rateTv);
			ledTv.addRate(rateTv);
			client2.addRate(rateTv2);
			ledTv.addRate(rateTv2);

			client1.addRate(speakerRate);
			speaker.addRate(speakerRate);
			client3.addRate(speakerRate2);
			speaker.addRate(sofaRate2);

			client1.addRate(sofaRate);
			sofa.addRate(sofaRate);
			client4.addRate(sofaRate2);
			sofa.addRate(sofaRate2);


			client6.addRate(bpRate);
			bloodPressure.addRate(bpRate);
			client8.addRate(bpRate2);
			bloodPressure.addRate(bpRate2);

			client10.addRate(starWarsPlushRate);
			cuutopiaPlush.addRate(starWarsPlushRate);
			client7.addRate(starWarsPlushRate2);
			cuutopiaPlush.addRate(starWarsPlushRate2);

			client9.addRate(laptopRate);
			laptop.addRate(laptopRate);
			client5.addRate(laptopRate2);
			laptop.addRate(laptopRate2);

			client8.addRate(hdmiRate);
			cableHdmi.addRate(hdmiRate);
			client4.addRate(hdmiRate2);
			client10.addRate(hdmiRate2);

			client4.addRate(airpodsRate);
			airPods.addRate(airpodsRate);
			client6.addRate(airpodsRate2);
			airPods.addRate(airpodsRate2);

			client9.addRate(macbookRate);
			pcApple.addRate(macbookRate);
			client8.addRate(macbookRate2);
			pcApple.addRate(macbookRate2);

			client2.addRate(sofaXlRate1);
			sofaXl.addRate(sofaXlRate1);
			client8.addRate(sofaXlRate2);
			sofaXl.addRate(sofaRate2);


			client4.addRate(bedRate1);
			bed.addRate(bedRate1);
			client8.addRate(bedRate2);
			bed.addRate(bedRate2);

			client9.addRate(deskRate1);
			desk.addRate(deskRate1);
			client2.addRate(deskRate2);
			desk.addRate(deskRate2);

			clientRepository.save(luisito);
			clientRepository.save(admin);
			clientRepository.save(client1);
			clientRepository.save(client2);
			clientRepository.save(client3);
			clientRepository.save(client4);
			clientRepository.save(client5);
			clientRepository.save(client6);
			clientRepository.save(client7);
			clientRepository.save(client8);
			clientRepository.save(client9);
			clientRepository.save(client10);

			ledTv.setStatus(true);
			airPods.setStatus(true);
			pcApple.setStatus(true);
			sofaXl.setStatus(true);
			desk.setStatus(true);
			bed.setStatus(true);
			bloodPressure.setStatus(true);
			cuutopiaPlush.setStatus(true);
			speaker.setStatus(true);
			cableHdmi.setStatus(true);
			sofa.setStatus(true);
			laptop.setStatus(true);


			productRepository.save(ledTv);
			productRepository.save(airPods);
			productRepository.save(pcApple);
			productRepository.save(sofaXl);
			productRepository.save(desk);
			productRepository.save(bed);
			productRepository.save(bloodPressure);
			productRepository.save(cuutopiaPlush);
			productRepository.save(speaker);
			productRepository.save(cableHdmi);
			productRepository.save(sofa);
			productRepository.save(laptop);


			purchaseOrderRepository.save(newPurchaseOrder);

			purchaseOrderProductRepository.save(ledTvPurchaseOrderProduct);
			purchaseOrderProductRepository.save(speakerPurchaseOrderProduct);
			purchaseOrderProductRepository.save(laptopPurchaseOrderProduct);
			purchaseOrderProductRepository.save(cablehdmiPurchaseOrderProduct);
			purchaseOrderProductRepository.save(bloodPressurePurchaseOrderProduct);
			purchaseOrderProductRepository.save(cuutopiaPurchaseOrderProduct);
			purchaseOrderProductRepository.save(sofaPurchaseOrderProduct);

			rateRepository.save(bpRate);
			rateRepository.save(bpRate2);

			rateRepository.save(rateTv);
			rateRepository.save(rateTv2);

			rateRepository.save(starWarsPlushRate);
			rateRepository.save(starWarsPlushRate2);

			rateRepository.save(speakerRate);
			rateRepository.save(speakerRate2);

			rateRepository.save(sofaRate);
			rateRepository.save(sofaRate2);

			rateRepository.save(laptopRate);
			rateRepository.save(laptopRate2);

			rateRepository.save(hdmiRate);
			rateRepository.save(hdmiRate2);

			rateRepository.save(bedRate1);
			rateRepository.save(bedRate2);

			rateRepository.save(deskRate1);
			rateRepository.save(deskRate2);

			rateRepository.save(airpodsRate2);
			rateRepository.save(airpodsRate);

			rateRepository.save(macbookRate2);
			rateRepository.save(macbookRate);

			rateRepository.save(sofaXlRate2);
			rateRepository.save(sofaXlRate1);



		};
	}
}
