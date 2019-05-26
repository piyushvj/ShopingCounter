package adp.shoping.counter;

import adp.shoping.counter.model.Item;
import adp.shoping.counter.model.Shop;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CounterApplication {

	public static void main(String[] args) {
		SpringApplication.run(CounterApplication.class, args);
	}

	@Bean
	public Shop makeShopReady(){
		Shop shop = Shop.getInstance();

		shop.addItem("A001", new Item("Jeans", 3000, "A"));
		shop.addItem("A002", new Item("Shirt", 1500, "A"));
		shop.addItem("A003", new Item("Lower", 700, "A"));
		shop.addItem("A004", new Item("Chinos", 1000, "A"));

		shop.addItem("B001", new Item("Potato", 20,"B"));
		shop.addItem("B002", new Item("Cabage", 30,"B"));
		shop.addItem("B003", new Item("Cauliflower", 40,"B"));
		shop.addItem("B004", new Item("Onions", 15,"B"));

		shop.addItem("C001", new Item("Guitars", 20000,"C"));
		shop.addItem("C002", new Item("Violins", 15000,"C"));
		shop.addItem("C003", new Item("Trumpet", 10000,"C"));
		shop.addItem("C004", new Item("Drum", 30000,"C"));
		return shop;
	}
}
