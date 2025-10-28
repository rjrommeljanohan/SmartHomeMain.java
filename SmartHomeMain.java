public class SmartHomeMain {
    static class Device {
        String deviceName;
        String status;
        String location;
        static int deviceCount = 0;

        Device() {
            deviceCount++;
        }

        Device(String deviceName, String status, String location) {
            this.deviceName = deviceName;
            setStatus(status);
            this.location = location;
            deviceCount++;
        }

        void setStatus(String status) {
            if (status.equalsIgnoreCase("ON") || status.equalsIgnoreCase("OFF")) {
                this.status = status.toUpperCase();
            } else {
                this.status = "OFF";
            }
        }

        void turnOn() { status = "ON"; }
        void turnOff() { status = "OFF"; }

        void displayInfo() {
            System.out.println("Device Name: " + deviceName);
            System.out.println("Status: " + status);
            System.out.println("Location: " + location);
        }

        static void showDeviceCount() {
            System.out.println("Total Devices: " + deviceCount);
        }
    }

    static class LightBulb extends Device {
        int brightnessLevel;
        String color;

        LightBulb(String deviceName, String status, String location, int brightnessLevel, String color) {
            super(deviceName, status, location);
            this.brightnessLevel = brightnessLevel;
            this.color = color;
        }

        LightBulb(String deviceName, String location) {
            super(deviceName, "OFF", location);
            brightnessLevel = 50;
            color = "Warm White";
        }

        void adjustBrightness(int level) { brightnessLevel = level; }

        @Override
        void displayInfo() {
            super.displayInfo();
            System.out.println("Brightness Level: " + brightnessLevel);
            System.out.println("Color: " + color);
        }
    }

    static class Thermostat extends Device {
        double temperature;
        String mode;

        Thermostat(String deviceName, String status, String location, double temperature, String mode) {
            super(deviceName, status, location);
            this.temperature = temperature;
            this.mode = mode;
        }

        void setTemperature(double temp) { temperature = temp; }

        void setTemperature(double temp, String mode) {
            temperature = temp;
            this.mode = mode;
        }

        @Override
        void displayInfo() {
            super.displayInfo();
            System.out.println("Temperature: " + temperature + "°C");
            System.out.println("Mode: " + mode);
        }
    }

    static class SmartHomeController {
        Device[] devices;

        SmartHomeController(Device[] devices) {
            this.devices = devices;
        }

        void turnAllOn() {
            for (Device d : devices) {
                d.turnOn();
            }
        }

        void turnAllOff() {
            for (Device d : devices) {
                d.turnOff();
            }
        }
    }

    public static void main(String[] args) {
        LightBulb bulb = new LightBulb("Living Room Bulb", "OFF", "Living Room", 75, "Cool White");
        Thermostat t1 = new Thermostat("Main Thermostat", "OFF", "Hallway", 24.5, "Cooling");
        Thermostat t2 = new Thermostat("Bedroom Thermostat", "on", "Bedroom", 22.0, "Heating");

        Device[] devices = { bulb, t1, t2 };

        SmartHomeController controller = new SmartHomeController(devices);

        controller.turnAllOn();

        for (Device d : devices) {
            d.displayInfo();
            System.out.println();
        }

        Device.showDeviceCount();
    }
}
