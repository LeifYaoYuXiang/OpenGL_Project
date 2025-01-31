package objects3D;
import org.newdawn.slick.opengl.Texture;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.Color;

public class Photo {
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
	
	public Photo() {
		
	}
	
	public void drawPhoto(float width, float length,Texture drawings) {
		Cube cube=new Cube();
		TexCube texCube=new TexCube();
		GL11.glPushMatrix();{
			GL11.glScalef(width, length, 1f);
			GL11.glTexParameteri(GL11.GL_TEXTURE_2D,GL11.GL_TEXTURE_WRAP_S,GL11.GL_CLAMP);
			Color.white.bind();
		    drawings.bind();
		    GL11.glEnable(GL11.GL_TEXTURE_2D);    
		    GL11.glTexParameteri( GL11.GL_TEXTURE_2D,  GL11.GL_TEXTURE_MAG_FILTER,  GL11.GL_NEAREST);
	        GL11.glClearColor(1.0f, 1.0f, 1.0f, 1.0f);
			texCube.DrawTexCube(drawings);
	        GL11.glDisable(GL11.GL_TEXTURE_2D);
	        
			GL11.glPushMatrix();{
				GL11.glTranslatef(0, 0, 3);
				GL11.glColor3f(white[0], white[1],white[2]);
				GL11.glScalef(1.2f, 1.2f, 1f);
				cube.DrawCube();
			}
			GL11.glPopMatrix();
		}
		GL11.glPopMatrix();
	}
	
	
}
