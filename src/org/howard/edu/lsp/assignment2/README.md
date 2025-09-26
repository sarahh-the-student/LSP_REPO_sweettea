# LSP Assignment 2:  CSV ETL Pipeline In Java

## Design Notes
The program is structured as a classic ETL (Extract, Transform, Load) pipeline, with the primary logic separated into three methods: extract(), transform(), and load().

Data Representation: A nested Product class, using objecct oriented programming, is used to model each row of data. 

File I/O: BufferedReader and PrintWriter are used for efficient reading and writing of CSV data.

Data Transformation: BigDecimal is used for all price-related calculations and rounding for precision.

Error Handling: A try-catch block is implemented to handle missing files.

## Assumptions
This program operates assumes:

- The input products.csv file is a comma-separated value file with no commas or quotes contained within the fields.

- The column order in the input file is consistent: ProductID, Name, Price, and Category.

- The program has the necessary permissions to read from and write to the data/ directory relative to the project's root.

## Testing Strategy
The program was tested using the three provided sample cases to ensure its robustness and correctness.

Case A (Normal Input): A products.csv file containing multiple rows was used to test all transformation rules. The output was verified to match the golden output transformed_products_normal.csv.

Case B (Empty Input): A products.csv file containing only the header row was used to test the program's ability to handle empty input. The program successfully created an output file with only the header row, matching the golden output transformed_products_empty.csv.

Case C (Missing Input File): The products.csv file was deleted to test error handling. The program correctly caught the FileNotFoundException, printed a clear error message to the console, and exited gracefully without crashing.

## AI Usage Summary
I used Google Gemini to help with the development and debugging of this assignment. This included:

Initial Program Structure: Generating the initialcode with a clear extract, transform, and load method structure.

Debugging: Providing explanations and solutions for common Java errors, specifically NoClassDefFoundError, by explaining the correct package and file structure, and the proper way to compile and run the program from the command line.

Transcript Excerpt:

The following excerpt demonstrates a key debugging session related to a common Java error:

User: sarahcole@Sarahs-Air-5 assignment2 % java ETL
Error: Could not find or load main class ETL
Caused by: java.lang.NoClassDefFoundError: ETL (wrong name: org/howard/edu/lsp/assignment2/ETL)

AI: This is a very clear error message! It's a classic NoClassDefFoundError, and it's happening because of how Java handles packages. The message (wrong name: org/howard/edu/lsp/assignment2/ETL) is telling you exactly what's wrong. You have a package declaration at the top of your ETL.java file...

This specific interaction helped diagnose and solve a critical issue with the file and package structure, allowing the program to run as intended.

## External Sources
W3schools for read/write logic: https://www.w3schools.com/java/java_files_read.asp

