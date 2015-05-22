package application.core;

public class Event {
	String eventName;
	String eventHandler;

	public String getEventName() {
		return this.eventName;
	}

	public String getEventHandler() {
		return this.eventHandler;
	}

	public Event(String eventName, String eventHandler) {
		this.eventName = eventName;
		this.eventHandler = eventHandler;
	}
}
