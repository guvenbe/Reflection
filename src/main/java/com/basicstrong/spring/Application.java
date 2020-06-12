package com.basicstrong.spring;

public class Application {
    public static void main(String[] args) {
        ApplicationContext context = new ApplicationContext(AppConfig.class);
        ProductService service = context.getBean(ProductService.class);
    }
}
