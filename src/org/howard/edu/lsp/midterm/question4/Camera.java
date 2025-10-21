package org.howard.edu.lsp.midterm.question4;

public class Camera extends Device implements Networked, BatteryPowered {
    private int batteryPercent;


    // Constructor for Camera.
    public Camera(String id, String location, int initialBattery) {
        super(id, location);
        // Use the setter to enforce the 0..100 validation logic, as required
        this.setBatteryPercent(initialBattery);
    }

    @Override
    public void connect() {
        setConnected(true);
    }

    @Override
    public void disconnect() {
        setConnected(false);
    }

    @Override
    public boolean isConnected() {
        return super.isConnected();
    }

    @Override
    public int getBatteryPercent() {
        return this.batteryPercent;
    }

    @Override
    public void setBatteryPercent(int percent) {
        if (percent < 0 || percent > 100) {
            throw new IllegalArgumentException("battery 0..100");
        }
        this.batteryPercent = percent;
    }

    @Override
    public String getStatus() {
        String connStatus = isConnected() ? "up" : "down";
        // This format matches the required output from the prompt
        return "Camera[id=" + getId() + ", loc=" + getLocation() +
                ", conn=" + connStatus + ", batt=" + batteryPercent + "%]";
    }
}

