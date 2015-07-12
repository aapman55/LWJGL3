import static org.lwjgl.glfw.GLFW.*;
import org.lwjgl.glfw.*;
import java.nio.DoubleBuffer;

import org.lwjgl.BufferUtils;


public class Mouse extends GLFWCursorPosCallback{
	
	private double X, Y, dX = 0, dY = 0;
	
	DoubleBuffer mouseX = BufferUtils.createDoubleBuffer(1);
	DoubleBuffer mouseY = BufferUtils.createDoubleBuffer(1);
	private long windowID;
	mouseButton mousebutton;
	mouseScroll mousescroll;
	
	
		public Mouse(long windowID){
			this.setWindowID(windowID);
			glfwSetCursorPosCallback(windowID, this);
			mousebutton = new mouseButton(windowID);
			mousescroll = new mouseScroll(windowID);
		}
		
		public double getX(){
			return this.X;
		}
		
		public double getY(){
			return this.Y;
		}
		
		public double getdX(){
			double outdX = this.dX;
			this.dX = 0;
			return outdX;
		}
		
		public double getdY(){
			double outdY = this.dY;
			this.dY = 0;
			return outdY;
		}	
		
		public long getWindowID() {
			return windowID;
		}

		public void setWindowID(long windowID) {
			this.windowID = windowID;
		}

		@Override
		public void invoke(long window, double xpos, double ypos) {
			dX = xpos - this.X;
			dY = ypos - this.Y;
			this.X = xpos;
			this.Y = ypos;			
		}
	
}
