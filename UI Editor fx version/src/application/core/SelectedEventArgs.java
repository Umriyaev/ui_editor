package application.core;

public class SelectedEventArgs {
	IComponent selectedComponent;

	public IComponent getSelectedComponent() {
		return this.selectedComponent;
	}

	public SelectedEventArgs(IComponent selectedComponent) {
		this.selectedComponent = selectedComponent;
	}
}
