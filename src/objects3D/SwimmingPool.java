package objects3D;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.Color;
import org.newdawn.slick.opengl.Texture;

public class SwimmingPool {
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
	
	public SwimmingPool() {
		
	}
	
	public void drawSwimmingPool(int width,int length,int height, Texture waterTexture,Texture wallTexture) {
		TexCube texCube=new TexCube();		
		Cube cube=new Cube();
		GL11.glPushMatrix();{
			GL11.glScalef(width, 1.05f*height, length);
			GL11.glTexParameteri(GL11.GL_TEXTURE_2D,GL11.GL_TEXTURE_WRAP_S,GL11.GL_CLAMP);
			Color.white.bind();
		    waterTexture.bind();
		    GL11.glEnable(GL11.GL_TEXTURE_2D);    
		    GL11.glTexParameteri( GL11.GL_TEXTURE_2D,  GL11.GL_TEXTURE_MAG_FILTER,  GL11.GL_NEAREST);
	        GL11.glClearColor(1.0f, 1.0f, 1.0f, 1.0f);
			texCube.DrawTexCube(waterTexture);
	        GL11.glDisable(GL11.GL_TEXTURE_2D);
		}
		GL11.glPopMatrix();
		
		GL11.glPushMatrix();{
			GL11.glTranslatef(0, 0, length);
			GL11.glScalef(1.05f*width, 1.5f*height, 30f);
			GL11.glTexParameteri(GL11.GL_TEXTURE_2D,GL11.GL_TEXTURE_WRAP_S,GL11.GL_CLAMP);
			Color.white.bind();
		    wallTexture.bind();
		    GL11.glEnable(GL11.GL_TEXTURE_2D);    
		    GL11.glTexParameteri( GL11.GL_TEXTURE_2D,  GL11.GL_TEXTURE_MAG_FILTER,  GL11.GL_NEAREST);
	        GL11.glClearColor(1.0f, 1.0f, 1.0f, 1.0f);
			texCube.DrawTexCube(wallTexture);
	        GL11.glDisable(GL11.GL_TEXTURE_2D);
		}
		GL11.glPopMatrix();
		
		GL11.glPushMatrix();{
			GL11.glTranslatef(0 ,0, -length);
			GL11.glScalef(1.05f*width, 1.5f*height, 30f);
			GL11.glRotatef(90, 0, 0, 1);
			GL11.glTexParameteri(GL11.GL_TEXTURE_2D,GL11.GL_TEXTURE_WRAP_S,GL11.GL_CLAMP);
			Color.white.bind();
		    wallTexture.bind();
		    GL11.glEnable(GL11.GL_TEXTURE_2D);    
		    GL11.glTexParameteri( GL11.GL_TEXTURE_2D,  GL11.GL_TEXTURE_MAG_FILTER,  GL11.GL_NEAREST);
	        GL11.glClearColor(1.0f, 1.0f, 1.0f, 1.0f);
			texCube.DrawTexCube(wallTexture);
	        GL11.glDisable(GL11.GL_TEXTURE_2D);
		}
		GL11.glPopMatrix();
		
		GL11.glPushMatrix();{
			GL11.glRotatef(90, 0, 1, 0);
			GL11.glTranslatef(0, 0, -length);
			GL11.glScalef(1.05f*width, 1.5f*height, 30f);
			GL11.glTexParameteri(GL11.GL_TEXTURE_2D,GL11.GL_TEXTURE_WRAP_S,GL11.GL_CLAMP);
			Color.white.bind();
		    wallTexture.bind();
		    GL11.glEnable(GL11.GL_TEXTURE_2D);    
		    GL11.glTexParameteri( GL11.GL_TEXTURE_2D,  GL11.GL_TEXTURE_MAG_FILTER,  GL11.GL_NEAREST);
	        GL11.glClearColor(1.0f, 1.0f, 1.0f, 1.0f);
			texCube.DrawTexCube(wallTexture);
	        GL11.glDisable(GL11.GL_TEXTURE_2D);
		}
		GL11.glPopMatrix();
		
		GL11.glPushMatrix();{
			GL11.glRotatef(90, 0, 1, 0);
			GL11.glTranslatef(0, 0.4f*height, -length-100);
			GL11.glColor3f(white[0], white[1], white[2]);
			GL11.glScalef(1.05f*width,0.20f*height , 200f);
//			cube.DrawCube();
			GL11.glTexParameteri(GL11.GL_TEXTURE_2D,GL11.GL_TEXTURE_WRAP_S,GL11.GL_CLAMP);
			Color.white.bind();
			wallTexture.bind();
		    GL11.glEnable(GL11.GL_TEXTURE_2D);    
		    GL11.glTexParameteri( GL11.GL_TEXTURE_2D,  GL11.GL_TEXTURE_MAG_FILTER,  GL11.GL_NEAREST);
	        GL11.glClearColor(1.0f, 1.0f, 1.0f, 1.0f);
			texCube.DrawTexCube(wallTexture);
	        GL11.glDisable(GL11.GL_TEXTURE_2D);
		}
		GL11.glPopMatrix();
		
		GL11.glPushMatrix();{
			GL11.glRotatef(90, 0, 1, 0);
			GL11.glTranslatef(0, 0.8f*height, -length);
			GL11.glColor3f(white[0], white[1], white[2]);
			GL11.glScalef(0.5f*width,0.20f*height , 200f);
			GL11.glTexParameteri(GL11.GL_TEXTURE_2D,GL11.GL_TEXTURE_WRAP_S,GL11.GL_CLAMP);
			Color.white.bind();
			wallTexture.bind();
		    GL11.glEnable(GL11.GL_TEXTURE_2D);    
		    GL11.glTexParameteri( GL11.GL_TEXTURE_2D,  GL11.GL_TEXTURE_MAG_FILTER,  GL11.GL_NEAREST);
	        GL11.glClearColor(1.0f, 1.0f, 1.0f, 1.0f);
			texCube.DrawTexCube(wallTexture);
	        GL11.glDisable(GL11.GL_TEXTURE_2D);
		}
		GL11.glPopMatrix();
		
		GL11.glPushMatrix();{
			GL11.glRotatef(90, 0, 1, 0);
			GL11.glTranslatef(0, 1.31f*height, -length+100);
			GL11.glColor3f(white[0], white[1], white[2]);
			GL11.glScalef(0.5f*width,0.2f*height , 200f);
			GL11.glTexParameteri(GL11.GL_TEXTURE_2D,GL11.GL_TEXTURE_WRAP_S,GL11.GL_CLAMP);
			Color.white.bind();
			wallTexture.bind();
		    GL11.glEnable(GL11.GL_TEXTURE_2D);    
		    GL11.glTexParameteri( GL11.GL_TEXTURE_2D,  GL11.GL_TEXTURE_MAG_FILTER,  GL11.GL_NEAREST);
	        GL11.glClearColor(1.0f, 1.0f, 1.0f, 1.0f);
			texCube.DrawTexCube(wallTexture);
	        GL11.glDisable(GL11.GL_TEXTURE_2D);
		}
		GL11.glPopMatrix();
		
	}
}
