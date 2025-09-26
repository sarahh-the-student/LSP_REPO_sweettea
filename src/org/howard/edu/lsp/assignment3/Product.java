package org.howard.edu.lsp.assignment3;

import java.math.BigDecimal;

/**
 * Represents a product with its properties.
 * This class serves as a data holder for product information.
 */
public class Product {
    int productID;
    String name;
    BigDecimal price;
    String category;
    String priceRange;

    /**
     * Constructs a new Product object.
     *
     * @param productID The unique identifier for the product.
     * @param name      The name of the product.
     * @param price     The price of the product.
     * @param category  The category of the product.
     */
    public Product(int productID, String name, BigDecimal price, String category) {
        this.productID = productID;
        this.name = name;
        this.price = price;
        this.category = category;
        this.priceRange = "Uncategorized"; // Default value
    }
}