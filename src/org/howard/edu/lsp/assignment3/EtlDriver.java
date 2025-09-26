package org.howard.edu.lsp.assignment3;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/**
 * The main driver class for the ETL (Extract, Transform, Load) process.
 * It orchestrates the entire workflow by initializing and calling the extractor,
 * transformer, and loader components.
 */
public class EtlDriver {

    private static final String DATA_DIR = "data/";
    private static final String INPUT_FILE_PATH = DATA_DIR + "products.csv";
    private static final String OUTPUT_FILE_PATH = DATA_DIR + "transformed_products.csv";

    /**
     * The main entry point for the application.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        System.out.println("Starting ETL process...");

        // Ensure the data directory exists.
        try {
            Files.createDirectories(Paths.get(DATA_DIR));
        } catch (IOException e) {
            System.err.println("Error creating directory: " + e.getMessage());
            return;
        }

        RunSummary summary = new RunSummary(OUTPUT_FILE_PATH);
        CsvExtractor extractor = new CsvExtractor();
        ProductTransformer transformer = new ProductTransformer();
        CsvLoader loader = new CsvLoader();

        // Step 1: Extract data from the CSV file.
        List<Product> extractedProducts = extractor.extract(INPUT_FILE_PATH, summary);

        // Step 2: Transform the extracted data.
        List<Product> transformedProducts = transformer.transform(extractedProducts, summary);

        // Step 3: Load the transformed data into a new CSV file.
        loader.load(transformedProducts, OUTPUT_FILE_PATH, summary);

        // Step 4: Print the run summary.
        summary.print();

        System.out.println("ETL process completed.");
    }
}