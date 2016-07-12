package pl.tomaja.atbackup.task;

import pl.tomaja.atbackup.events.Event;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Adam Tomaja
 */
public class TaskResult {

    private final List<Event> events = new ArrayList<Event>();

    public void addEvent(Event event) {
        events.add(event);
    }

    public List<Event> getEvents() {
        return events;
    }

    public void addEvents(TaskResult result) {
        events.addAll(result.getEvents());
    }

    @Override
    public String toString() {
        return "TaskResult{" +
                "events=" + events +
                '}';
    }
}
