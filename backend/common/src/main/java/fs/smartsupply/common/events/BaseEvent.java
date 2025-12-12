package fs.smartsupply.common.events;


import java.time.Instant;

public abstract class BaseEvent {
    private String eventId;
    private Instant createdAt;

    public BaseEvent() {
        this.eventId = java.util.UUID.randomUUID().toString();
        this.createdAt = Instant.now();
    }

    public String getEventId() { return eventId; }
    public Instant getCreatedAt() { return createdAt; }
}
