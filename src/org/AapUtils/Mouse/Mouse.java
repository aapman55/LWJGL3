package org.AapUtils.Mouse;
import static org.lwjgl.glfw.GLFW.*;

import org.lwjgl.glfw.*;

import java.nio.DoubleBuffer;

import org.lwjgl.BufferUtils;

/**
 * Utility function that takes care of all mouse events.
 * 
 * @author ZL
 *
 */

public class Mouse extends GLFWCursorPosCallback{
	
	private double X, Y, dX = 0, dY = 0;
	
	DoubleBuffer mouseX = BufferUtils.createDoubleBuffer(1);
	DoubleBuffer mouseY = BufferUtils.createDoubleBuffer(1);
	private long windowID;
	mouseButton mousebutton;
	mouseScroll mousescroll;
	
		/**
		 * Initializes a mouse. This mouse is attached to just 1 window.
		 * 
		 * @param windowID The identifier of the window to which this Mouse listens to.
		 */
		public Mouse(long windowID){
			this.setWindowID(windowID);
			glfwSetCursorPosCallback(windowID, this);
			mousebutton = new mouseButton(windowID);
			mousescroll = new mouseScroll(windowID);
		}
		
		/**
		 * Trap the mouse inside the window. 
		 * The mouse is hidden and it can be moved around freely without being limited by the borders.
		 */
		public void setGrabbed(){
			glfwSetInputMode(windowID, GLFW_CURSOR, GLFW_CURSOR_DISABLED);
		}
		
		/**
		 * Do not trap the mouse inside the window. The normal OS mouse is shown in all times.
		 */
		public void setMouseFree(){
			glfwSetInputMode(windowID, GLFW_CURSOR, GLFW_CURSOR_NORMAL);
		}
		
		/**
		 * Grabs the mouse and hides it when it is over the window. Otherwise the OS mouse cursor is shown.
		 */
		public void setGrabbedOnWindow(){
			glfwSetInputMode(windowID, GLFW_CURSOR, GLFW_CURSOR_HIDDEN);
		}
		/**
		 * Returns the direction of scrolling and the magnitude. Harder scrolling results in a higher number
		 * @return The amount scrolled in X-direction
		 */
		public double getXScroll(){
			return mousescroll.getxScroll();
		}
		
		/**
		 * Returns the direction of scrolling and the magnitude. Harder scrolling results in a higher number
		 * @return The amount scrolled in Y-direction
		 */
		public double getYScroll(){
			return mousescroll.getyScroll();
		}
		
		/**
		 * Returns if a button is pressed (1) or not (0). The mouse button to check is indicated by the integers 0 to 7.
		 * Normally, 0 == left mouse button, 1 == right mouse button and 2 == middel mouse button
		 * 
		 * @param button The integer identifier of the mouse button
		 * @return The boolean if the button is pressed or not.
		 */
		public boolean getMouseButtonState(int button){
			return mousebutton.getButtonState(button);
		}
		
		/**
		 * The X-location of the mouse
		 * @return The X-location of the mouse.
		 */
		public double getX(){
			return this.X;
		}
		
		/**
		 * The Y-location of the mouse
		 * @return The Y-location of the mouse
		 */
		public double getY(){
			return this.Y;
		}
		
		/**
		 * The amount moved in X direction, since the previous mouse move event.
		 * @return The mount moved in X-direction.
		 */
		public double getdX(){
			double outdX = this.dX;
			this.dX = 0;
			return outdX;
		}
		
		/**
		 * The amount moved in Y direction, since the previous mouse move event.
		 * @return The mount moved in Y-direction.
		 */
		public double getdY(){
			double outdY = this.dY;
			this.dY = 0;
			return outdY;
		}	
		
		/**
		 * The window ID the Mouse is watching.
		 * @return The window ID
		 */
		public long getWindowID() {
			return windowID;
		}
		
		/**
		 * Sets which window the mouse is following.
		 * @param windowID The ID of the desired window to follow
		 */
		public void setWindowID(long windowID) {
			this.windowID = windowID;
		}
		
		/**
		 * When the mouse is moved, this function will be called.
		 */
		@Override
		public void invoke(long window, double xpos, double ypos) {
			dX = xpos - this.X;
			dY = ypos - this.Y;
			this.X = xpos;
			this.Y = ypos;			
		}
	
}
