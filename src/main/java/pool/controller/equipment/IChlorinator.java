package pool.controller.equipment;

public interface IChlorinator {
	public enum Controller {
		VIRTUAL, INTELLITOUCH, INTELLICOM, NONE
	};

	Controller getControlledBy();

	void setControlledBy(Controller controller);

	String getName();

	void setName();

	int getSaltPPM();

	void setSaltPPM(int ppm);

	String getStatus();
}
