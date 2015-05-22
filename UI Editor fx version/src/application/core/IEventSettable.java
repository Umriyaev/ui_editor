package application.core;

import java.util.List;

public interface IEventSettable {
	public List<Event> getEvents();

	public void addEvent(Event event);

	public void removeEvent(Event event);
}
