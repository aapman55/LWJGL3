
import org.AapUtils.Mouse.Mouse;
import org.lwjgl.glfw.Callbacks;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.opengl.GLContext;
import org.lwjgl.system.MemoryUtil;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;

public class SimpleWindow {

	// The GLFW error callback: this tells GLFW what to do if things go wrong
	private static GLFWErrorCallback errorCallback;
	// The handle of the GLFW window
	private static long windowID;
	private static int windowHeight = 600;
	private static int windowWidth = 800;
	private static Mouse mouse1;

/**
 * Method called to start the whole process
 */
	public void start(){
		// Set the error handling code: all GLFW errors will be printed to the system error stream (just like println)
		errorCallback = Callbacks.errorCallbackPrint(System.err);
		glfwSetErrorCallback(errorCallback);
		initWindow();
		mainLoop();
		terminate();
	}
	
	/**
	 * Initialize glfw window to display openGL
	 */
	public void initWindow(){
		// Initialize GLFW:
		int glfwInitializationResult = glfwInit(); // initialize GLFW and store the result (pass or fail)
		if (glfwInitializationResult == GL_FALSE)
			throw new IllegalStateException("GLFW initialization failed");

		// Configure the GLFW window
		windowID = glfwCreateWindow(
				windowWidth, windowHeight,   // Width and height of the drawing canvas in pixels
				"Display",     // Title of the window
				MemoryUtil.NULL, // Monitor ID to use for fullscreen mode, or NULL to use windowed mode (LWJGL JavaDoc)
				MemoryUtil.NULL); // Window to share resources with, or NULL to not share resources (LWJGL JavaDoc)

		if (windowID == MemoryUtil.NULL)
			throw new IllegalStateException("GLFW window creation failed");

		glfwMakeContextCurrent(windowID); // Links the OpenGL context of the window to the current thread (GLFW_NO_CURRENT_CONTEXT error)
		glfwSwapInterval(1); // Enable VSync, which effective caps the frame-rate of the application to 60 frames-per-second		
		glfwShowWindow(windowID);

		// If you don't add this line, you'll get the following exception:
		//  java.lang.IllegalStateException: There is no OpenGL context current in the current thread.
		GLContext.createFromCurrent(); // Links LWJGL to the OpenGL context
		
		// create Mouse
		mouse1 = new Mouse(windowID);
			
		
		// Define window viewport

		
		glViewport(0, 0, windowWidth, windowHeight);
		
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		glOrtho(0, windowWidth, windowHeight, 0,-1,1);
		glMatrixMode(GL_MODELVIEW);
		
		glClearColor(0,0,1,0);
		mouse1.setGrabbed();
	}

	/**
	 * The mainloop in which everything is rendered
	 */
	public void mainLoop(){
		int x = 0;
		int y = 0;
		while(glfwWindowShouldClose(windowID)== GL_FALSE){
			
			// Clear the contents of the window (try disabling this and resizing the window – fun guaranteed)
			glClear(GL_COLOR_BUFFER_BIT);
			
			// Polls the user input. This is very important, because it prevents your application from becoming unresponsive
			glfwPollEvents();
			
			x = (glfwGetKey(windowID, GLFW_KEY_RIGHT) == 1) ? x+5 : x;
			x = (glfwGetKey(windowID, GLFW_KEY_LEFT) == 1) ? x-5 : x;
			
			if (glfwGetKey(windowID, GLFW_KEY_ESCAPE)==1){
				mouse1.setMouseFree();			
			};
				
			
			
			x += mouse1.getdX();
			y += (mouse1.getdY()+mouse1.getYScroll()*15);
			
			x = (x < 0)? 0 : x;
			x = (x > windowWidth)? windowWidth : x;
			y = (y < 0)? 0 : y;
			y = (y > windowHeight)? windowHeight : y;

			// Draw a Triangle
			glColor3d(1, 1, 1);
			glBegin(GL_QUADS);
				glVertex2d(x,y);
				glVertex2d(x, y+100);
				glVertex2d(x+100, y+100);				
				glVertex2d(x+100, y);
			glEnd();			
			
			if (mouse1.getMouseButtonState(0)) {
				glClearColor(0, 0, 0, 0);
			}else if (mouse1.getMouseButtonState(1)) {
				glClearColor(1, 0, 0, 0);
			}
			
			// Swaps the front and back framebuffers, this is a very technical process which you don't necessarily
						// need to understand. You can simply see this method as updating the window contents.
						glfwSwapBuffers(windowID);					

			
			
		}
	}
/**
 * Cleans up after closing the window
 */
	public void terminate(){
		// It's important to release the resources when the program has finished to prevent dreadful memory leaks
		glfwDestroyWindow(windowID);
		// Destroys all remaining windows and cursors (LWJGL JavaDoc)
		glfwTerminate();
	}

	public static void main(String[] args){
		SimpleWindow sw = new SimpleWindow();
		sw.start();
	}
}
