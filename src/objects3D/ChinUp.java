package objects3D;

import org.lwjgl.opengl.GL11;

public class ChinUp {
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
	
	public ChinUp() {
		
	}
	
	public void DrawChinUp() {
		Cylinder cylinder=new Cylinder();
		GL11.glPushMatrix();{
			GL11.glTranslatef(-300,900, 0);
			GL11.glRotatef(90, 1, 0, 0);
			GL11.glScalef(30, 30, 30);
			GL11.glColor3f(yellow[0],yellow[1],yellow[2]);
			cylinder.DrawCylinder(1, 100, 50);
			
		}
		GL11.glPopMatrix();
		GL11.glPushMatrix();{
			GL11.glTranslatef(300, 900, 0);
			GL11.glRotatef(90, 1, 0, 0);
			GL11.glScalef(30, 30, 30);
			GL11.glColor3f(yellow[0],yellow[1],yellow[2]);
			cylinder.DrawCylinder(1, 100, 50);
		}
		GL11.glPopMatrix();
		
		GL11.glPushMatrix();{
			GL11.glTranslatef(-330, 900, 0);
			GL11.glRotatef(90, 0, 1, 0);
			GL11.glScalef(30, 30, 30);
			GL11.glColor3f(spot[0],spot[1],spot[2]);
			cylinder.DrawCylinder(1, 22, 50);
		}
		GL11.glPopMatrix();
	}
	
}
