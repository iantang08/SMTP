public class Incident {
    private String location;
    private String time;
    private String description;
    private int severity;

    public Incident(String location, String time, String description, int severity) {
        this.location = location;
        this.time = time;
        this.description = description;
        this.severity = severity;
    }

}