package objects3D;

import java.io.IOException;


import org.lwjgl.opengl.GL11;
import org.newdawn.slick.Color;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

public class Sphere {

	
	public Sphere() {

	}
	// Implement using notes and examine Tetrahedron to aid in the coding  look at lecture  7 , 7b and 8 
	// 7b should be your primary source, we will cover more about circles in later lectures to understand why the code works 
	public void DrawSphere(float radius,float nSlices,float nSegments) {
		
		final float pi=(float) Math.PI;
		//the step of theta
		float inctheta= (2.0f*pi/nSlices);
		//the step of phi
		float incphi=(pi/nSegments);

		GL11.glBegin(GL11.GL_QUADS);
		{
			for(float theta = -pi; theta<pi;theta=theta+inctheta){
				for(float phi = -(pi/2);phi<pi/2;phi=phi+incphi){
					/*
					* Basic Idea:
					* 	x=r*cos(phi)*cod(theta)
					* 	y=r*cos(phi)*sin(theta)
					* 	z=r*sin(phi)
					* */

					//Draw the first point
					float x= (float) (radius*Math.cos(phi)*Math.cos(theta));
					float y=(float) (radius*Math.cos(phi)*Math.sin(theta));
					float z= (float) (radius*Math.sin(phi));
					GL11.glNormal3f(x,y,z);
					GL11.glVertex3f(x,y,z);

					//Draw the second point
					float x_step_phi=(float) (radius*Math.cos(phi+incphi)*Math.cos(theta));
					float y_step_phi=(float) (radius*Math.cos(phi+incphi)*Math.sin(theta));
					float z_step_phi=(float) (radius*Math.sin(phi+incphi));
					GL11.glNormal3f(x_step_phi,y_step_phi,z_step_phi);
					GL11.glVertex3f(x_step_phi,y_step_phi,z_step_phi);

					//Draw the third point
					float x_step_theta_phi=(float) (radius*Math.cos(phi+incphi)*Math.cos(theta+inctheta));
					float y_step_theta_phi=(float) (radius*Math.cos(phi+incphi)*Math.sin(theta+inctheta));
					float z_step_theta_phi=(float) (radius*Math.sin(phi+incphi));
					GL11.glNormal3f(x_step_theta_phi,y_step_theta_phi,z_step_theta_phi);
					GL11.glVertex3f(x_step_theta_phi,y_step_theta_phi,z_step_theta_phi);

					//Draw the forth point
					float x_step_theta=(float) (radius*Math.cos(phi)*Math.cos(theta+inctheta));
					float y_step_theta=(float) (radius*Math.cos(phi)*Math.sin(theta+inctheta));
					float z_step_theta=(float) (radius*Math.sin(phi));
					GL11.glNormal3f(x_step_theta,y_step_theta,z_step_theta);
					GL11.glVertex3f(x_step_theta,y_step_theta,z_step_theta);
				}
			}

		}
		//Finish Drawing
		GL11.glEnd();
	}

	public void DrawTexSphere(float radius,float nSlices,float nSegments,Texture texture) throws IOException {
		GL11.glTexParameteri(GL11.GL_TEXTURE_2D,GL11.GL_TEXTURE_WRAP_T,GL11.GL_CLAMP);
		Color.white.bind();
		texture.bind();
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		GL11.glTexParameteri( GL11.GL_TEXTURE_2D,  GL11.GL_TEXTURE_MAG_FILTER,  GL11.GL_NEAREST);
		TexSphere texSphere=new TexSphere();
		texSphere.DrawTexSphere(radius, nSlices, nSegments);
		GL11.glDisable(GL11.GL_TEXTURE_2D);	
	}
}

 