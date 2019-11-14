package objects3D;

import org.lwjgl.opengl.GL11;

public class Table {
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
	
	public Table() {
		
	}
	
	public void drawTable(float tableLength, float tableHeight, float legRadius,float tableThickness) {
		Cube cube= new Cube();
		Cylinder cylinder= new Cylinder();
		GL11.glPushMatrix();{
			GL11.glColor3f(brown[0], brown[1], brown[2]);
			GL11.glScalef(tableLength,tableThickness,tableLength);
			cube.DrawCube();
			
			GL11.glPushMatrix();{
				GL11.glScalef(1/tableLength,1/tableThickness,1/tableLength);
				GL11.glTranslatef(tableLength/2, 0, tableLength/2);
				GL11.glRotatef(90, 1, 0, 0);
				GL11.glColor3f(white[0], white[1], white[2]);
				cylinder.DrawCylinder(legRadius, tableHeight, 50);
			}
			GL11.glPopMatrix();
			
			GL11.glPushMatrix();{
				GL11.glScalef(1/tableLength,1/tableThickness,1/tableLength);
				GL11.glTranslatef(-tableLength/2, 0, tableLength/2);
				GL11.glRotatef(90, 1, 0, 0);
				GL11.glColor3f(white[0], white[1], white[2]);
				cylinder.DrawCylinder(legRadius, tableHeight, 50);
			}
			GL11.glPopMatrix();
			
			GL11.glPushMatrix();{
				GL11.glScalef(1/tableLength,1/tableThickness,1/tableLength);
				GL11.glTranslatef(-tableLength/2, 0, -tableLength/2);
				GL11.glRotatef(90, 1, 0, 0);
				GL11.glColor3f(white[0], white[1], white[2]);
				cylinder.DrawCylinder(legRadius, tableHeight, 50);
			}
			GL11.glPopMatrix();
			
			GL11.glPushMatrix();{
				GL11.glScalef(1/tableLength,1/tableThickness,1/tableLength);
				GL11.glTranslatef(tableLength/2, 0, -tableLength/2);
				GL11.glRotatef(90, 1, 0, 0);
				GL11.glColor3f(white[0], white[1], white[2]);
				cylinder.DrawCylinder(legRadius, tableHeight, 50);
			}
			GL11.glPopMatrix();
			
			
			
			
		}
		GL11.glPopMatrix();
	}
	

}
