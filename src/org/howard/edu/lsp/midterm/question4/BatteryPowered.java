package org.howard.edu.lsp.midterm.question4;

public interface BatteryPowered {
     // Gets the current battery percentage.
     // @return an integer between 0 and 100, inclusive.
    int getBatteryPercent();

    // Sets the current battery percentage.
    // @throws IllegalArgumentException if the percentage is not between 0 and 100.

    void setBatteryPercent(int percent);
}
