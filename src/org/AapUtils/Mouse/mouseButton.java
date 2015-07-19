package org.AapUtils.Mouse;

import org.lwjgl.glfw.GLFWMouseButtonCallback;
import static org.lwjgl.glfw.GLFW.*;


public class mouseButton extends GLFWMouseButtonCallback{
	
	boolean[] mouseButtonStates = new boolean[8];
	private long windowID;
	
	public mouseButton(long windowID){
		this.setWindowID(windowID);
		glfwSetMouseButtonCallback(windowID, this);
	}
	
	public boolean getButtonState(int button){
		return mouseButtonStates[button];		
	}
	
	
	public long getWindowID() {
		return windowID;
	}

	public void setWindowID(long windowID) {
		this.windowID = windowID;
	}
	
	@Override
	public void invoke(long window, int button, int action, int mods) {
		mouseButtonStates[button] = action==1;
		System.out.println(button);
	}

}
