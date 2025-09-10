package org.howard.edu.lsp.assignment2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;


 // Main class for the ETL pipeline
public class ETL {

    // Define the paths for the input and output files, making sure its in data directory.
    private static final String DATA_DIR = "data/";
    private static final String INPUT_FILE_PATH = DATA_DIR + "products.csv";
    private static final String OUTPUT_FILE_PATH = DATA_DIR + "transformed_products.csv";

    private static int rowsRead = 0;
    private static int rowsTransformed = 0;
    private static int headerSkipped = 0;
    private static int rowsWritten = 0;

    public static void main(String[] args) {

        // For debugging purposes, print the start of the ETL process.
        System.out.println("Starting ETL process...");

        // Ensure the data directory exists.
        try {
            Files.createDirectories(Paths.get(DATA_DIR));
        } catch (IOException e) {
            System.err.println("Error creating directory: " + e.getMessage());
            return;
        }

        // Step 1: Extract data from the CSV file.
        List<Product> products = extract(INPUT_FILE_PATH);

        // Step 2: Transform the extracted data.
        List<Product> transformedProducts = transform(products);
        rowsTransformed = transformedProducts.size();

        // Step 3: Load the transformed data into a new CSV file.
        load(transformedProducts, OUTPUT_FILE_PATH);

        // Step 4: Print the run summary.
        printRunSummary();

        // Debugging statement to indicate the end of the ETL process.
        System.out.println("ETL process completed.");
    }

     // Extracts data from a CSV file and populates a list of Product objects.
    private static List<Product> extract(String filePath) {
        System.out.println("Extracting data from " + filePath + "...");
        List<Product> products = new ArrayList<>();
        
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            // Read and skip the header row.
            reader.readLine();
            headerSkipped = 1;

            String line;
            while ((line = reader.readLine()) != null) {
                rowsRead++;
                if (line.trim().isEmpty()) {
                    continue;
                }
                
                String[] fields = line.split(",");
                
                // Ensure the line has the correct number of fields.
                if (fields.length == 4) {
                    try {
                        int productID = Integer.parseInt(fields[0].trim());
                        String name = fields[1].trim();
                        BigDecimal price = new BigDecimal(fields[2].trim());
                        String category = fields[3].trim();
                        
                        products.add(new Product(productID, name, price, category));
                    } catch (NumberFormatException e) {
                        System.err.println("Skipping malformed row: " + line);
                    }
                } else {
                    System.err.println("Skipping malformed row (incorrect number of fields): " + line);
                }
            }
        } catch (IOException e) {
            // Case handling for missing input file.
            System.err.println("Error: Missing input file. " + e.getMessage());
        }
        
        return products;
    }


    // Transforms the list of Product objects
    private static List<Product> transform(List<Product> products) {
        System.out.println("Transforming data...");
        List<Product> transformedProducts = new ArrayList<>();
        
        for (Product product : products) {
            // Store the original category before potential changes.
            String originalCategory = product.category;
            product.name = product.name.toUpperCase();

            // Apply a 10% discount to products in the Electronics category and round price up.
            if (originalCategory.equalsIgnoreCase("Electronics")) {
                BigDecimal discountFactor = new BigDecimal("0.90");
                product.price = product.price.multiply(discountFactor)
                                          .setScale(2, RoundingMode.HALF_UP);
            }

            // If the post-discount price is over $500.00 category goes from "Electronics" to "Premium Electronics".
            if (product.price.compareTo(new BigDecimal("500.00")) > 0 &&
                originalCategory.equalsIgnoreCase("Electronics")) {
                product.category = "Premium Electronics";
            }

            // Defines price range based on the price.  
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
        
        return transformedProducts;
    }

    // Loads the transformed data into a new CSV file.
    private static void load(List<Product> products, String filePath) {
        System.out.println("Loading data to " + filePath + "...");
        
        try (PrintWriter writer = new PrintWriter(new FileWriter(filePath))) {
            writer.println("ProductID,Name,Price,Category,PriceRange");
            
            for (Product product : products) {
                writer.printf("%d,%s,%.2f,%s,%s%n",
                              product.productID,
                              product.name,
                              product.price,
                              product.category,
                              product.priceRange);
                rowsWritten++;
            }
            
        } catch (IOException e) {
            System.err.println("Error writing to output file: " + e.getMessage());
        }
    }

    // Prints a summary of the ETL run.
    private static void printRunSummary() {
        System.out.println("\n--- Run Summary ---");
        System.out.println("Rows read: " + rowsRead);
        System.out.println("Rows transformed: " + rowsTransformed);
        System.out.println("Header skipped: " + headerSkipped);
        System.out.println("Rows written: " + rowsWritten);
        System.out.println("Output path: " + OUTPUT_FILE_PATH);
        System.out.println("-------------------\n");
    }

    // Define a product class to hold product data.
    private static class Product {
        int productID;
        String name;
        BigDecimal price;
        String category;
        String priceRange;

        public Product(int productID, String name, BigDecimal price, String category) {
            this.productID = productID;
            this.name = name;
            this.price = price;
            this.category = category;
        }
    }
}
