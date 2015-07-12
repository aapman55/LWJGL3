
import org.lwjgl.glfw.GLFWMouseButtonCallback;
import static org.lwjgl.glfw.GLFW.*;


public class mouseButton extends GLFWMouseButtonCallback{
	
	int[] mouseButtonStates = new int[8];
	private long windowID;
	
	public mouseButton(long windowID){
		this.setWindowID(windowID);
		glfwSetMouseButtonCallback(windowID, this);
	}
	
	
	public long getWindowID() {
		return windowID;
	}

	public void setWindowID(long windowID) {
		this.windowID = windowID;
	}
	
	@Override
	public void invoke(long window, int button, int action, int mods) {
		mouseButtonStates[button] = action;
		System.out.println(button);
	}

}
