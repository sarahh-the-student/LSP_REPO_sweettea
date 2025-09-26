package org.howard.edu.lsp.assignment3;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * Handles loading transformed product data into a new CSV file.
 */
public class CsvLoader {

    /**
     * Loads the transformed data into a new CSV file.
     *
     * @param products The list of transformed products to write.
     * @param filePath The path to the output CSV file.
     * @param summary  The RunSummary object to update with load statistics.
     */
    public void load(List<Product> products, String filePath, RunSummary summary) {
        System.out.println("Loading data to " + filePath + "...");

        try (PrintWriter writer = new PrintWriter(new FileWriter(filePath))) {
            // Write the header for the output file.
            writer.println("ProductID,Name,Price,Category,PriceRange");

            for (Product product : products) {
                writer.printf("%d,%s,%.2f,%s,%s%n",
                              product.productID,
                              product.name,
                              product.price,
                              product.category,
                              product.priceRange);
                summary.incrementRowsWritten();
            }

        } catch (IOException e) {
            System.err.println("Error writing to output file: " + e.getMessage());
        }
    }
}