package com.example.SimpleInventory;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
@Configuration
public class AppConfig {

    @Bean
    public Map<String, Product> inventory(){
        Map<String, Product> inventory = new HashMap<>();

        inventory.put ("Computer", new Product ( "Computer", new BigDecimal("1000"),  0L, Category.ELECTRONICS));
        inventory.put ("Skirt", new Product ( "Skirt", new BigDecimal("100"),  10L, Category.CLOTHING));
        return inventory;
    }
}
