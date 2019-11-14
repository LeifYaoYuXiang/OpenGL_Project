package objects3D;

import org.lwjgl.opengl.GL11;

public class Chair {
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
	
	public Chair() {
		
	}
	
	public void drawChair() {
		Cylinder cylinder=new Cylinder();
		Cube cube=new Cube();
		
		GL11.glPushMatrix();{
			GL11.glColor3f(cyan[0], cyan[1], cyan[2]);
			GL11.glScalef(100,10,100);
			cube.DrawCube();
		}
		GL11.glPopMatrix();
		
		GL11.glPushMatrix();{
			GL11.glTranslatef(-80, 0, 50);
			GL11.glRotatef(90, 1, 0, 0);
			GL11.glColor3f(orange[0], orange[1], orange[2]);
			cylinder.DrawCylinder(20, 100, 50);
		}
		GL11.glPopMatrix();
		
		GL11.glPushMatrix();{
			GL11.glTranslatef(80, 0, -50);
			GL11.glRotatef(90, 1, 0, 0);
			cylinder.DrawCylinder(20, 120, 50);
		}
		GL11.glPopMatrix();
		
		GL11.glPushMatrix();{
			GL11.glTranslatef(-80, 0, 50);
			GL11.glRotatef(90, 1, 0, 0);
			cylinder.DrawCylinder(20, 120, 50);
		}
		GL11.glPopMatrix();
		
		GL11.glPushMatrix();{
			GL11.glTranslatef(80, 0, 50);
			GL11.glRotatef(90, 1, 0, 0);
			cylinder.DrawCylinder(20, 120, 50);
		}
		GL11.glPopMatrix();
		
		GL11.glPushMatrix();{
			GL11.glTranslatef(-80, 0, -50);
			GL11.glRotatef(90, 1, 0, 0);
			cylinder.DrawCylinder(20, 120, 50);
		}
		GL11.glPopMatrix();
		
		GL11.glPushMatrix();{
			GL11.glTranslatef(80, 80, 0);
			GL11.glRotatef(90, 0, 0, 1);
			GL11.glScalef(100, 20, 100);
			GL11.glColor3f(cyan[0], cyan[1], cyan[2]);
			cube.DrawCube();
		}
		GL11.glPopMatrix();
		
		
		
	}
	
}
