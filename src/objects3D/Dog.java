package objects3D;

import java.io.IOException;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.opengl.Texture;

import GraphicsObjects.Utils;

public class Dog {

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
	
	public Dog() {
		
	}
	
	public void drawDog(boolean rotateSelf,float delta,boolean GoodAnimation, Texture faceTexture, Texture bodyTexture, Texture pelvisTexture) {
		float theta = (float) (delta * 10 * Math.PI);
		float LimbRotation;
		float thetaDeg = delta * 360; 
		 if (GoodAnimation){
			 LimbRotation = (float) Math.cos(theta)*45;
		 }else{
			 LimbRotation =0;
		 } 
		 
		 //the primitives used to build the human body
		 Sphere sphere= new Sphere();
		 Cylinder cylinder= new Cylinder();
		 
		 
		 //Start to draw one human being body 
		 GL11.glPushMatrix();{
			 
			 //pelvis
			 /*
			  * this function is used to implement the self-rotation
			  * theory behind it: each draw one human being you rotate along y-axis "thetaDeg" degree
			  * */
			 GL11.glRotatef(90, 0, -1, 0);
			 if(rotateSelf) {
				 GL11.glRotatef(-thetaDeg/10,0.0f,1.0f, 0.0f);
			 }else {
				 
			 }
			 //GL11.glRotatef(-thetaDeg+180,0.0f,1.0f, 0.0f);
			 
			 
			 GL11.glTranslatef(0.0f,0.5f,0.0f);//translate the coordinate to +0.5y 
			 //temp
			 //GL11.glRotatef(-90, 1, 0, 0);
			 try {
				//GL11.glRotatef(90, 1, 0, 0);
				sphere.DrawTexSphere(0.5f, 16, 16,pelvisTexture);
				GL11.glRotatef(-90, 1, 0, 0);
				 
			} catch (IOException e) {
				e.printStackTrace();
			} //draw the sphere standing for the pelvis
			
			 GL11.glPushMatrix();{
				 GL11.glScalef(0.3f, 1.5f, 0.3f);
				 GL11.glTranslatef(0f,-0.3f, 0.55f);
				 GL11.glRotatef(60, 1, 0, 0);
				 sphere.DrawSphere(0.25f, 32, 32);
				 
			 }
			 GL11.glPopMatrix();
			 
			 //chest
			 //configure the pen (color & material)
			 GL11.glColor3f(brown[0], brown[1],brown[2]);
			 GL11.glMaterial(GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(green));
			 GL11.glPushMatrix(); 
			 {
				 GL11.glTranslatef(0.0f,0.5f,0.0f);//translate the coordinate to +0.5y
				 
				 try {
						GL11.glRotatef(90, 1, 0, 0);
						sphere.DrawTexSphere(0.5f, 32, 32,bodyTexture);
						GL11.glRotatef(-90, 1, 0, 0);
						 
					} catch (IOException e) {
						e.printStackTrace();
					}
				 
				 
				 //sphere.DrawSphere(0.5f, 32, 32); //draw the sphere standing for the chest
				 
				 // neck
				 //configure the pen (color & material)
				 GL11.glColor3f(brown[0], brown[1],brown[2]);
		         GL11.glMaterial(GL11.GL_FRONT,  GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(orange));
		         
		         GL11.glPushMatrix(); 
		         {
		        	 GL11.glTranslatef(0.0f,0.5f, 0.0f);//doesn't translate the coordinate
		        	 //GL11.glRotatef(-90.0f,1.0f,0.0f,0.0f);//rotate the coordinate -90 degree along (1,0,0)
		        	 //cylinder.DrawCylinder(0.15f,0.3f,32);//draw the cylinder standing for the neck
		        	 
		        	 // head
		        	 //configure the pen (color & material)
		        	 GL11.glColor3f(brown[0], brown[1],brown[2]);	
		             GL11.glMaterial( GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(red));
		             GL11.glPushMatrix(); 
		             {
		            	 
		            	 GL11.glTranslatef(0.0f,0.0f,0.5f);//translate the coordinate +1z
//		            	 sphere.DrawSphere(0.5f, 32, 32);//draw the sphere standing for the head
		            	 try {
		            		 GL11.glRotatef(60, 0.0f, 0.0f, 1.0f);
							sphere.DrawTexSphere(0.4f, 32, 32, faceTexture);
						} catch (IOException e2) {
							// TODO Auto-generated catch block
							e2.printStackTrace();
						}
		            	 
		            	 
		            	 GL11.glPopMatrix();//pop matrix -->back to neck
		             } 
		             GL11.glPopMatrix();//pop matrix -->back to chest


		             //left shoulder
		             //configure the pen (color & material)
		             GL11.glColor3f(brown[0], brown[1],brown[2]);
		             GL11.glMaterial( GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(blue));
		             GL11.glPushMatrix(); 
		             {
		            	 GL11.glTranslatef(0.5f,0.4f,0.0f);//translate the coordinate (+0.5x,+0.4y)
		            	 sphere.DrawSphere(0.25f, 32, 32);//draw the sphere standing for the left shoulder 
		            	 
		            	 // left arm
		            	 //configure the pen (color & material)
		            	 GL11.glColor3f(brown[0], brown[1],brown[2]);
		            	 GL11.glMaterial( GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(orange));
		            	 GL11.glPushMatrix(); 
		            	 {
		            		 GL11.glTranslatef(0.0f,0.0f,0.0f);//doesn't translate the coordinate
		            		 GL11.glRotatef(90.0f,1.0f,0.0f,0.0f);//rotate the coordinate 90 degree along (1,0,0)
		                     GL11.glRotatef(LimbRotation+90,1.0f,0.0f,0.0f);//rotate the coordinate "LimbRotation" degree along (1,0,0)
		                     if(LimbRotation>0) {
		                    	 GL11.glRotatef(-LimbRotation, 0, 0, 1);
		                     }
		                     cylinder.DrawCylinder(0.15f,0.75f,32);//draw the cylinder standing for the left arm
		                     

		                     // left elbow
		                     //configure the pen (color & material)
		                     GL11.glColor3f(brown[0], brown[1],brown[2]);
		                   	 GL11.glMaterial(GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(blue));
		                   	 GL11.glPushMatrix(); 
		                   	 {
		                   		 GL11.glTranslatef(0.0f,0.0f,0.75f);//translate the coordinate (+0.75z)
		                   		 
		                   		 sphere.DrawSphere(0.2f, 32, 32); //draw the sphere standing for the left elbow
		                   		 
		                   	 } 
		                   	 GL11.glPopMatrix();//pop matrix --> back to arm
		            	 } 
		            	 GL11.glPopMatrix ();//pop matrix --> back to shoulder
		             } 
		             GL11.glPopMatrix ();//pop matrix--> back to chest

		             // right shoulder
		             //configure the pen (color & material)
		             GL11.glColor3f(brown[0], brown[1],brown[2]);
		             GL11.glMaterial( GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(blue));
		             GL11.glPushMatrix();
		             {
		            	 GL11.glTranslatef(-0.5f,0.4f,0.0f);//translate the matrix along(0.5 0.4 0.0)
		            	 sphere.DrawSphere(0.25f, 32, 32); //draw the sphere standing for shoulder
		            	 
		            	 // right arm
		            	 //configure the pen (color & material)
		            	 GL11.glColor3f(brown[0], brown[1],brown[2]);
		            	 GL11.glMaterial( GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(orange));
		            	 GL11.glPushMatrix();
		            	 {
		            		 GL11.glTranslatef(0.0f,0.0f,0.0f);//doesn't translate the coordinate system  
		            		 GL11.glRotatef(90.0f,1.0f,0.0f,0.0f);//rotate the coordinate system 90 degree along (1,0,0)
		            		 GL11.glRotatef(-LimbRotation+90,1.0f,0.0f,0.0f); //rotate the coordinate system 90 degree along (1,0,0)
		            		 if(LimbRotation<0) {
		                    	 GL11.glRotatef(-LimbRotation, 0, 0, 1);
		                     }
		            		 cylinder.DrawCylinder(0.15f,0.7f,32);//draw the cylinder standing for the right arm
		            		 
		            		 // right elbow
		            		 //configure the pen (color & material)
		            		 GL11.glColor3f(brown[0], brown[1],brown[2]);
		            		 GL11.glMaterial(GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(blue));
		            		 
		            		 GL11.glPushMatrix(); 
		            		 {
		            			 GL11.glTranslatef(0.0f,0.0f,0.75f);//translate the coordinate system (+0.75z)
		            			 sphere.DrawSphere(0.2f, 32, 32); //draw the sphere standing for elbow
		            		 }
		            		 GL11.glPopMatrix();//pop matrix --> back to right arm
		            	 }
		            	 GL11.glPopMatrix();//pop matrix --> back to right shoulder
		             }
		             GL11.glPopMatrix();//pop matrix --> back to chest
		         } 
		         GL11.glPopMatrix();//pop matrix --> back to pelvis
		         
		         // left hip
		         //configure the pen (color & material)
		         GL11.glColor3f(brown[0], brown[1],brown[2]);
		       	 GL11.glMaterial( GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(blue));
		       	 GL11.glPushMatrix(); 
		       	 {
		       		 GL11.glTranslatef(-0.5f,-0.5f,0.0f);//translate the coordinate system along (-0.5f,-0.2f)
		       		 sphere.DrawSphere(0.25f, 32, 32); //draw the sphere standing for hip 
		       		 
		       		 //left high leg
		       		 //configure the pen (color & material)
		       		 GL11.glColor3f(brown[0], brown[1],brown[2]);
		           	 GL11.glMaterial( GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(orange));
		           	 GL11.glPushMatrix(); 
		           	 {
		                    GL11.glTranslatef(0.0f,0.0f,0.0f);//doesn't translate the coordinate system
		                    GL11.glRotatef(90,1,0.0f,0.0f);//doesn't rotate the cylinder 
		                    
		                    GL11.glRotatef((-LimbRotation)+90,1.0f,0.0f,0.0f); //rotate the cylinder to simulate the action of running legs
		                    cylinder.DrawCylinder(0.15f,0.7f,32);//draw the cylinder standing for high legs

		                    // left knee
		                    //configure the pen (color & material)
		                    GL11.glColor3f(brown[0], brown[1],brown[2]);
		                    GL11.glMaterial( GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(blue));
		                    GL11.glPushMatrix(); 
		                    {
		                    	GL11.glTranslatef(0.0f,0.0f,0.75f);//translate the coordinate along (0.75z)
		                        GL11.glRotatef(90,1,0.0f,0.0f); //doesn't rotate the coordinate system
		                        sphere.DrawSphere(0.25f, 32, 32); //draw the sphere standing for the knee
		                    } 
		                    GL11.glPopMatrix();//pop matrix --> back to high leg
		           	 } 
		           	 GL11.glPopMatrix();//pop matrix --> back to left hip
		       	 } 
		       	 GL11.glPopMatrix();//pop matrix --> back to pelvis
		       	 
		       	 //right hip
		       	 //configure the pen (color & material)
		       	 GL11.glColor3f(brown[0], brown[1],brown[2]);
		       	 GL11.glMaterial( GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(blue));
		       	 GL11.glPushMatrix(); 
		       	 {
		       		 
		       		 GL11.glTranslatef(0.5f,-0.5f,0.0f);//translate the coordinate along (0.5,-0.2,0.0)
		       		 sphere.DrawSphere(0.25f, 32, 32);//draw the sphere standing for the right hip
		       		 
		       		 //right high leg
		       		 //configure the pen (color & material)
		       		 GL11.glColor3f(brown[0], brown[1],brown[2]);
		       		 GL11.glMaterial( GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(orange));
		       		 GL11.glPushMatrix(); 
		       		 {
		       			 GL11.glTranslatef(0.0f,0.0f,0.0f);//doesn't translate the coordinate system
		       			 GL11.glRotatef(90,1,0.0f,0.0f);//doesn't rotate the coordinate system
		       			 GL11.glRotatef((LimbRotation)+90,1.0f,0.0f,0.0f); //rotate the coordinate system (LimbRotation+90) degree along (1.0,0.0,0.0) w
		       			 cylinder.DrawCylinder(0.15f,0.7f,32);//draw the cylinder standing for right high pen 
				         
		       			 //right knee
		       			 //configure the pen (color & material)
		       			 GL11.glColor3f(brown[0], brown[1],brown[2]);
		       			 GL11.glMaterial( GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(blue));
		       			 GL11.glPushMatrix(); 
		       			 {
		       				 GL11.glTranslatef(0.0f,0.0f,0.75f);//translate the coordinate (0.75z)
		       				 GL11.glRotatef(0.0f,0.0f,0.0f,0.0f); 
		       				 sphere.DrawSphere(0.25f, 32, 32); //draw the sphere standing for knee
		       			 }
		       			 GL11.glPopMatrix();//pop matrix --> back to high leg 
		       		 }
		       		 GL11.glPopMatrix();//pop matrix --> back to hip 
		       	 }
		       	 GL11.glPopMatrix();//pop matrix --> back to pelvis 
			 } 
			 GL11.glPopMatrix();//pop matrix --> empty stack !
		 } 
	}

	public void drawWalkingDog(boolean rotateSelf,float delta,boolean GoodAnimation, Texture faceTexture, Texture bodyTexture, Texture pelvisTexture) {
		
	}
}
