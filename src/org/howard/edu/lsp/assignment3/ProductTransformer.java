package org.howard.edu.lsp.assignment3;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

/**
 * Handles the transformation logic for a list of Product objects.
 */
public class ProductTransformer {

    /**
     * Transforms a list of products by applying business rules.
     *
     * @param products The list of products to transform.
     * @param summary  The RunSummary object to update with transformation statistics.
     * @return A list of transformed Product objects.
     */
    public List<Product> transform(List<Product> products, RunSummary summary) {
        System.out.println("Transforming data...");
        List<Product> transformedProducts = new ArrayList<>();

        for (Product product : products) {
            // Store the original category before potential changes.
            String originalCategory = product.category;

            // Rule 1: Capitalize product name.
            product.name = product.name.toUpperCase();

            // Rule 2: Apply a 10% discount for "Electronics".
            if (originalCategory.equalsIgnoreCase("Electronics")) {
                BigDecimal discountFactor = new BigDecimal("0.90");
                product.price = product.price.multiply(discountFactor)
                                     .setScale(2, RoundingMode.HALF_UP);
            }

            // Rule 3: Re-categorize expensive electronics to "Premium Electronics".
            if (originalCategory.equalsIgnoreCase("Electronics") &&
                product.price.compareTo(new BigDecimal("500.00")) > 0) {
                product.category = "Premium Electronics";
            }

            // Rule 4: Assign a price range.
            if (product.price.compareTo(new BigDecimal("10.00")) <= 0) {
                product.priceRange = "Low";
            } else if (product.price.compareTo(new BigDecimal("100.00")) <= 0) {
                product.priceRange = "Medium";
            } else if (product.price.compareTo(new BigDecimal("500.00")) <= 0) {
                product.priceRange = "High";
            } else {
                product.priceRange = "Premium";
            }

            transformedProducts.add(product);
        }
        summary.setRowsTransformed(transformedProducts.size());
        return transformedProducts;
    }
}