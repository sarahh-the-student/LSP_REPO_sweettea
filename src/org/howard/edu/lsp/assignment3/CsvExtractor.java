package org.howard.edu.lsp.assignment3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Handles the extraction of product data from a CSV file.
 */
public class CsvExtractor {

    /**
     * Extracts data from a specified CSV file and populates a list of Product objects.
     *
     * @param filePath The path to the input CSV file.
     * @param summary  The RunSummary object to update with extraction statistics.
     * @return A list of Product objects extracted from the file.
     */
    public List<Product> extract(String filePath, RunSummary summary) {
        System.out.println("Extracting data from " + filePath + "...");
        List<Product> products = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            // Read and skip the header row.
            reader.readLine();
            summary.incrementHeaderSkipped();

            String line;
            while ((line = reader.readLine()) != null) {
                summary.incrementRowsRead();
                if (line.trim().isEmpty()) {
                    continue; // Skip empty lines
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
                        System.err.println("Skipping malformed row (invalid number format): " + line);
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
}