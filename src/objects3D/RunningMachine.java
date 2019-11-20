package objects3D;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.Color;
import org.newdawn.slick.opengl.Texture;

public class RunningMachine {
	//basic colors
	static float black[] = { 0.0f, 0.0f, 0.0f, 1.0f };
	static float white[] = { 1.0f, 1.0f, 1.0f, 1.0f };
	static float grey[] = { 0.5f, 0.5f, 0.5f, 1.0f };
	static float spot[] = { 0.1f, 0.1f, 0.1f, 0.5f };

	// primary colors
	static float red[] = { 1.0f, 0.0f, 0.0f, 1.0f };
	static float green[] = { 0.0f, 1.0f, 0.0f, 1.0f };
	static float blue[] = { 0.0f, 0.0f, 1.0f, 1.0f };

	// secondary colors
	static float yellow[] = { 1.0f, 1.0f, 0.0f, 1.0f };
	static float magenta[] = { 1.0f, 0.0f, 1.0f, 1.0f };
	static float cyan[] = { 0.0f, 1.0f, 1.0f, 1.0f };

	// other colors
	static float orange[] = { 1.0f, 0.5f, 0.0f, 1.0f, 1.0f };
	static float brown[] = { 0.5f, 0.25f, 0.0f, 1.0f, 1.0f };
	static float dkgreen[] = { 0.0f, 0.5f, 0.0f, 1.0f, 1.0f };
	static float pink[] = { 1.0f, 0.6f, 0.6f, 1.0f, 1.0f };	
	
	public RunningMachine() {
		
	}
	
	public void DrawRunningMachine(Texture panel, Texture machineBottom) {
		Cylinder cylinder=new Cylinder();
		TexCube bottom=new TexCube();
		Sphere sphere=new Sphere();
		GL11.glPushMatrix();{
			GL11.glColor3f(blue[0], blue[1], blue[2]);
			GL11.glScalef(900,50,500);
			GL11.glTexParameteri(GL11.GL_TEXTURE_2D,GL11.GL_TEXTURE_WRAP_S,GL11.GL_CLAMP);
			Color.white.bind();
		    machineBottom.bind();
		    GL11.glEnable(GL11.GL_TEXTURE_2D);    
		    GL11.glTexParameteri( GL11.GL_TEXTURE_2D,  GL11.GL_TEXTURE_MAG_FILTER,  GL11.GL_NEAREST);
	        GL11.glClearColor(1.0f, 1.0f, 1.0f, 1.0f);
			bottom.DrawTexCube(machineBottom);
	        GL11.glDisable(GL11.GL_TEXTURE_2D);
		}
		GL11.glPopMatrix();
		GL11.glPushMatrix();{
			GL11.glScalef(10,100,5);
			GL11.glColor3f(black[0], black[1], black[0]);
			GL11.glRotatef(90, 1, 0, 0);
			GL11.glTranslatef(-70, 40, -8);
			GL11.glScalef(1,1,1);
			cylinder.DrawCylinder(10, 8f, 50);
		}
		GL11.glPopMatrix();
		
		GL11.glPushMatrix();{
			GL11.glScalef(10,100,5);
			GL11.glColor3f(black[0], black[1], black[0]);
			GL11.glRotatef(90, 1, 0, 0);
			GL11.glTranslatef(-70, -40, -8);
			GL11.glScalef(1,1,1);
			cylinder.DrawCylinder(10, 8f, 50);
		}
		GL11.glPopMatrix();
		
		GL11.glPushMatrix();{
			GL11.glColor3f(black[0], black[1], black[2]);
			GL11.glTranslatef(-600, 800, 0);
			GL11.glRotatef(-30, 0, 0, 1);
			GL11.glScalef(200,50,500);
			try {
				sphere.DrawTexSphere(1.2f, 50, 50, panel);
			} catch (Exception e) {
				// TODO: handle exception
			}
			//sphere.DrawSphere(1.2f, 50, 50);
		}
		GL11.glPopMatrix();
	}
}
