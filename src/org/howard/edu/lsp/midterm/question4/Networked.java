package org.howard.edu.lsp.midterm.question4;

public interface Networked {
    void connect();

    // disconnect from network
    void disconnect();

     // Checks if the device is currently connected.
     // @return true if connected, false otherwise.
    boolean isConnected();
}
