package com.mytechtip.mongodb.bigdecimal.demo;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import org.bson.types.Decimal128;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.mongodb.core.convert.CustomConversions;

/**
 * A sample spring boot application to demonstrate the custom mapping
 * of BigDecimal for MongoDB (version >= 3.4)
 * 
 * @author mytechtip
 */
@SpringBootApplication
public class Application  implements CommandLineRunner {
    
    @Autowired
    private ProductRepository repo;
    
    public static void main(String[] args) {
            SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... strings) throws Exception {
        // Create a list of products with different prices
        Product p1 = new Product("P1", new BigDecimal("3.21"));
        Product p2 = new Product("P2", new BigDecimal("42.90"));
        Product p3 = new Product("P3", new BigDecimal("101.20"));
        Product p4 = new Product("P4", new BigDecimal("22.12"));
        
        // Save the list of products to mongodb
        repo.save(Arrays.asList(p1, p2, p3, p4));
        
        // Retrieve and print the list ordered by the price
        List<Product> list = repo.findAllByOrderByPrice();
        list.forEach(p -> System.out.println(p));
        
    }
    
    /**
     * Inject a CustomConversions bean to overwrite the default mapping
     * of BigDecimal.
     * 
     * @return a new instance of CustomConversons
     */
    @Bean
    CustomConversions customConverions() {
        Converter<Decimal128, BigDecimal> decimal128ToBigDecimal = new Converter<Decimal128, BigDecimal>() {
            @Override
            public BigDecimal convert(Decimal128 s) {
                return s==null ? null : s.bigDecimalValue();
            }
        };
        
        Converter<BigDecimal, Decimal128> bigDecimalToDecimal128 = new Converter<BigDecimal, Decimal128>() {
            @Override
            public Decimal128 convert(BigDecimal s) {
                return s==null ? null : new Decimal128(s);
            }
        };
        
        return new CustomConversions(Arrays.asList(decimal128ToBigDecimal, bigDecimalToDecimal128));
    }
    
    
    
}
