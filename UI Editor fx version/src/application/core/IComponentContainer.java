package application.core;

import java.util.List;

public interface IComponentContainer {
	public List<IComponent> getComponents();

	public void addComponent(IComponent component);

	public void removeComponent(IComponent component);
}
