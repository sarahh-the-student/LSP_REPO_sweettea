package org.howard.edu.lsp.assignment3;

/**
 * Encapsulates the statistics of an ETL run, such as rows read and written.
 */
public class RunSummary {
    private int rowsRead = 0;
    private int rowsTransformed = 0;
    private int headerSkipped = 0;
    private int rowsWritten = 0;
    private final String outputPath;

    /**
     * Constructs a new RunSummary object.
     *
     * @param outputPath The path where the final output file will be stored.
     */
    public RunSummary(String outputPath) {
        this.outputPath = outputPath;
    }

    /** Increments the count of rows read from the source file. */
    public void incrementRowsRead() {
        this.rowsRead++;
    }

    /** Increments the count of header rows skipped. */
    public void incrementHeaderSkipped() {
        this.headerSkipped++;
    }

    /**
     * Sets the total number of rows transformed.
     * @param count The number of rows transformed.
     */
    public void setRowsTransformed(int count) {
        this.rowsTransformed = count;
    }

    /** Increments the count of rows written to the destination file. */
    public void incrementRowsWritten() {
        this.rowsWritten++;
    }

    /**
     * Prints the formatted summary of the ETL process to the console.
     */
    public void print() {
        System.out.println("\n--- Run Summary ---");
        System.out.println("Rows read: " + rowsRead);
        System.out.println("Rows transformed: " + rowsTransformed);
        System.out.println("Header skipped: " + headerSkipped);
        System.out.println("Rows written: " + rowsWritten);
        System.out.println("Output path: " + outputPath);
        System.out.println("-------------------\n");
    }
}
