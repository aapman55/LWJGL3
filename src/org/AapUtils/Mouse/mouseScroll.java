package org.AapUtils.Mouse;
import org.lwjgl.glfw.GLFWScrollCallback;
import static org.lwjgl.glfw.GLFW.*;

public class mouseScroll extends GLFWScrollCallback{
	
	private long windowID;
	private double xScroll, yScroll;
	
	public mouseScroll(long windowID){
		this.setWindowID(windowID);
		glfwSetScrollCallback(windowID, this);
	}
		
	public long getWindowID() {
		return windowID;
	}

	public double getxScroll() {
		double outXScroll = this.xScroll;
		this.xScroll = 0;
		return outXScroll;
	}

	public double getyScroll() {
		double outYScroll = this.yScroll;
		this.yScroll = 0;
		return outYScroll;
	}

	public void setyScroll(double yScroll) {
		this.yScroll = yScroll;
	}

	public void setxScroll(double xScroll) {
		this.xScroll = xScroll;
	}

	public void setWindowID(long windowID) {
		this.windowID = windowID;
	}

	@Override
	public void invoke(long window, double xoffset, double yoffset) {
		this.setxScroll(xoffset);
		this.setyScroll(yoffset);
		System.out.println(yoffset);
	}

}
