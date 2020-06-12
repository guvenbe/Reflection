package com.basicstrong.spring;

import java.util.List;

public class ProductRepository {
    public List<Product> getPrice(List<Product> items) {
        for (Product product : items) {
            //let's assume reading from database
            Double price = (double) Math.round((30 * Math.random()));
            System.out.println("Original Price of " + product.getName() + " is " + price +" $.");
            product.setPrice(price);
        }
        return items;
    }
}
