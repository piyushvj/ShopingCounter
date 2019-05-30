package adp.shoping.counter;

import adp.shoping.counter.repository.ShopDB;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CounterApplication {

	@Value("${itemlist.path}")
	private String itemsJsonFilePath;

	@Value("${categorylist.path}")
	private String categoryJsonFilePath;

	public static void main(String[] args) {
		SpringApplication.run(CounterApplication.class, args);
	}

	@Bean
	public ShopDB getShopData(){
		return new ShopDB(categoryJsonFilePath, itemsJsonFilePath);
	}
}
