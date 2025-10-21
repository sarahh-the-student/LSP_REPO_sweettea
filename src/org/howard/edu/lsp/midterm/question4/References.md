# Reference: Design and Implementation for Question 4

Date: October 20, 2025

Conversation Transcript: Google Gemini

User: I am working on question 4, which gives an abstract class for 'device' but wants us to make interfaces for 'networked' and 'batterypowered.' They both seem to have methods without bodies. what's the actual difference? why isn't Device just another interface, or why aren't the other two abstract classes?

Gemini:
That's the key conceptual question of this problem! The difference is about "is-a" versus "can-do."

An abstract class like Device is a blueprint for what an object is. It says that a DoorLock is-a Device. It provides shared state (fields like id and location) and shared implemented methods (like heartbeat()). It's a base identity.

An interface like Networked is a contract for what an object can-do. It defines a capability. A DoorLock isn't a Networked, but it can do networking. Interfaces don't have state (fields) or implemented methods; they just define a set of required behaviors.

So, Device is the core identity for all your objects, while Networked and BatteryPowered are like optional feature packages you can add to them.

User:
Thanks, I am also working on the Thermostat class now. the prompt gives these instructions for it:

B) Thermostat — extends Device, implements Networked
Private fields
private double temperatureC;

Constructor
public Thermostat(String id, String location, double initialTempC)
•	Call super(id, location).
•	Initialize temperatureC to initialTempC.

Accessors
public double getTemperatureC();
public void setTemperatureC(double temperatureC);

I'm not totally sure how to write the constructor and the accessor methods based on that. can you show me?

Gemini:
Absolutely. Based on those requirements, you need to call the parent Device constructor using super() and then handle its own specific field. The constructor and the required accessors (getter and setter) would look like this:

// Constructor for Thermostat
public Thermostat(String id, String location, double initialTempC) {
    // Call the parent constructor first
    super(id, location);
    // Initialize its own specific field
    this.temperatureC = initialTempC;
}

// Accessor method (getter)
public double getTemperatureC() {
    return temperatureC;
}

// Accessor method (setter)
public void setTemperatureC(double temperatureC) {
    this.temperatureC = temperatureC;
}


User:
perfect, that's what i needed. now im on the DoorLock and i have the constructor and the interface methods done but im not sure how to build the string for the getStatus() method. the format has to be exactly like what's shown here:

// Status
@Override public String getStatus() {
String connStatus = isConnected() ? "up" : "down";
return "DoorLock[id=" + getId() + ", loc=" + getLocation() + ", conn=" + connStatus + ", batt=" + batteryPercent + "%]";
}

can you help me write just that method?

Gemini:
You're right, the format is very specific. You need to get the connection status, then combine it with the ID, location, and battery from the other methods. Here's the implementation for just the getStatus() method in your DoorLock class:

@Override
public String getStatus() {
    String connStatus = isConnected() ? "up" : "down";
    return "DoorLock[id=" + getId() + ", loc=" + getLocation() +
           ", conn=" + connStatus + ", batt=" + batteryPercent + "%]";
}


You can use a similar structure for the other classes, just swapping out the last part for temperature or battery as needed.

User:
that's great, thanks. okay i've done the DoorLock and Thermostat classes but i'm getting an error when i try to run the Main file. the error points to this line in the driver:

Device cam = new Camera("CA-303", "Quad-North", 72);

It says the constructor is undefined. I think my Camera.java constructor is wrong. Can you just give me the full, correct code for Camera.java so i can compare it with mine and see what i did wrong?

Gemini:
Of course. That's a very common issue—a mismatch between how the driver calls a constructor and how the class defines it. Based on that line from Main, the Camera constructor needs to accept a String, a String, and an int.

Here is the complete and correct implementation for Camera.java that will work with the provided Main.java file.

package org.howard.edu.lsp.midterm.question4;

public class Camera extends Device implements Networked, BatteryPowered {
    private int batteryPercent;

    // Constructor for Camera
    public Camera(String id, String location, int initialBattery) {
        super(id, location);
        // Use the setter to enforce the 0..100 validation logic
        this.setBatteryPercent(initialBattery);
    }

    // Networked interface methods
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

    // BatteryPowered interface methods
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

    // Device abstract method implementation
    @Override
    public String getStatus() {
        String connStatus = isConnected() ? "up" : "down";
        return "Camera[id=" + getId() + ", loc=" + getLocation() +
               ", conn=" + connStatus + ", batt=" + batteryPercent + "%]";
    }
}


Compare this with your file, especially the constructor signature, the call to setBatteryPercent, and the getStatus() format. That should clear up any issues.