package objects3D;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.Color;
import org.newdawn.slick.opengl.Texture;

public class House {
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
	
	public House() {
		
	}
	
	public void DrawHouse(Texture redWallTexture,Texture floorTexture) {
		GL11.glPushMatrix();{
			GL11.glPushMatrix();{
				//∫Û≤‡«Ω
				TexCube redWall=new TexCube();
				GL11.glTranslatef(0, 0, 2000);
				GL11.glScalef(1000f, 1000f, 5f);
				GL11.glTexParameteri(GL11.GL_TEXTURE_2D,GL11.GL_TEXTURE_WRAP_T,GL11.GL_CLAMP);
				Color.white.bind();
				redWallTexture.bind();
				GL11.glEnable(GL11.GL_TEXTURE_2D);    
			    GL11.glTexParameteri( GL11.GL_TEXTURE_2D,  GL11.GL_TEXTURE_MAG_FILTER,  GL11.GL_NEAREST);
		        GL11.glClearColor(1.0f, 1.0f, 1.0f, 1.0f);   
				redWall.DrawTexCube(redWallTexture);
				GL11.glDisable(GL11.GL_TEXTURE_2D);
			}
			GL11.glPopMatrix();
			//◊Û≤‡«Ω
			GL11.glPushMatrix();{
				TexCube redWall=new TexCube();
				GL11.glTranslatef(-1000, 0, 1000);
				GL11.glScalef(5f, 1000f, 1000f);
				GL11.glTexParameteri(GL11.GL_TEXTURE_2D,GL11.GL_TEXTURE_WRAP_T,GL11.GL_CLAMP);
				Color.white.bind();
				redWallTexture.bind();
				GL11.glEnable(GL11.GL_TEXTURE_2D);    
			    GL11.glTexParameteri( GL11.GL_TEXTURE_2D,  GL11.GL_TEXTURE_MAG_FILTER,  GL11.GL_NEAREST);
		        GL11.glClearColor(1.0f, 1.0f, 1.0f, 1.0f);   
				redWall.DrawTexCube(redWallTexture);
				GL11.glDisable(GL11.GL_TEXTURE_2D);
			}
			GL11.glPopMatrix();
			//”“≤‡«Ω
			GL11.glPushMatrix();{
				TexCube redWall=new TexCube();
				GL11.glTranslatef(1000, 0, 1000);
				GL11.glScalef(5f, 1000f, 1000f);
				GL11.glTexParameteri(GL11.GL_TEXTURE_2D,GL11.GL_TEXTURE_WRAP_T,GL11.GL_CLAMP);
				Color.white.bind();
				redWallTexture.bind();
				GL11.glEnable(GL11.GL_TEXTURE_2D);    
			    GL11.glTexParameteri( GL11.GL_TEXTURE_2D,  GL11.GL_TEXTURE_MAG_FILTER,  GL11.GL_NEAREST);
		        GL11.glClearColor(1.0f, 1.0f, 1.0f, 1.0f);   
				redWall.DrawTexCube(redWallTexture);
				GL11.glDisable(GL11.GL_TEXTURE_2D);
			}
			GL11.glPopMatrix();
			
			//µÿ∞Â
			GL11.glPushMatrix();{
				TexCube floor=new TexCube();
				GL11.glTranslatef(1, -1000, 1000);
				GL11.glScalef(1000f, 50f, 1000f);
				GL11.glTexParameteri(GL11.GL_TEXTURE_2D,GL11.GL_TEXTURE_WRAP_T,GL11.GL_CLAMP);
				Color.white.bind();
				floorTexture.bind();
				GL11.glEnable(GL11.GL_TEXTURE_2D);    
			    GL11.glTexParameteri( GL11.GL_TEXTURE_2D,  GL11.GL_TEXTURE_MAG_FILTER,  GL11.GL_NEAREST);
		        GL11.glClearColor(1.0f, 1.0f, 1.0f, 1.0f);   
				floor.DrawTexCube(floorTexture);
				GL11.glDisable(GL11.GL_TEXTURE_2D);
				
			}
			GL11.glPopMatrix();
		}
		GL11.glPopMatrix();	
	}
	
}
