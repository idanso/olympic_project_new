package sample;
public class Stadium {
    private String name;
    private String location;
    private int numberOfSeats;
    public Stadium (String name, String location, int numberOfSeats){
        setName(name);
        setLocation(location);
        setNumberOfSeats(numberOfSeats);
    }

    public String getName() {
        return name;
    }
    public String getLocation() {
        return location;
    }
    public int getNumberOfSeats() {
        return numberOfSeats;
    }
    public void setName(String newName){
        this.name=newName;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setNumberOfSeats(int numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }

    @Override
    public String toString() {
        return "Stadium{" +
                "Name='" + name + '\'' +
                ", Location='" + location + '\'' +
                ", Number of seats=" + numberOfSeats +
                '}';
    }
}