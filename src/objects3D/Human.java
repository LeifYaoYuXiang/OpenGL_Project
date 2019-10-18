package objects3D;

import org.lwjgl.opengl.GL11;
import GraphicsObjects.Point4f;
import GraphicsObjects.Utils;
import GraphicsObjects.Vector4f;

public class Human {

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
	
	
	public Human() {
	}
	
	public void DrawHuman(float delta,boolean GoodAnimation){ 
		//the angle used in the the method glRotatef()
		float theta = (float) (delta * 50 * Math.PI);
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
			 GL11.glRotatef(thetaDeg,0.0f,1.0f, 0.0f);
			 GL11.glTranslatef(0.0f,0.5f,0.0f);//translate the coordinate to +0.5y 
			 sphere.DrawSphere(0.5f, 32, 32); //draw the sphere standing for the pelvis
			 
			 
			 //chest
			 //configure the pen (color & material)
			 GL11.glColor3f(green[0], green[1], green[2]);
			 GL11.glMaterial(GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(green));
			 GL11.glPushMatrix(); 
			 {
				 GL11.glTranslatef(0.0f,0.5f,0.0f);//translate the coordinate to +0.5y 
				 sphere.DrawSphere(0.5f, 32, 32); //draw the sphere standing for the chest
				 
				 // neck
				 //configure the pen (color & material)
		       	 GL11.glColor3f(orange[0], orange[1], orange[2]);
		         GL11.glMaterial(GL11.GL_FRONT,  GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(orange));
		         
		         GL11.glPushMatrix(); 
		         {
		        	 GL11.glTranslatef(0.0f,0.0f, 0.0f);//doesn't translate the coordinate
		        	 GL11.glRotatef(-90.0f,1.0f,0.0f,0.0f);//rotate the coordinate -90 degree along (1,0,0)
		        	 cylinder.DrawCylinder(0.15f,0.7f,32);//draw the cylinder standing for the neck
		        	 
		        	 // head
		        	 //configure the pen (color & material)
		           	 GL11.glColor3f(red[0], red[1], red[2]);
		             GL11.glMaterial( GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(red));
		             GL11.glPushMatrix(); 
		             {
		            	 GL11.glTranslatef(0.0f,0.0f,1.0f);//translate the coordinate +1z
		            	 sphere.DrawSphere(0.5f, 32, 32);//draw the sphere standing for the head
		            	 GL11.glPopMatrix();//pop matrix -->back to neck
		             } 
		             GL11.glPopMatrix();//pop matrix -->back to chest


		             //left shoulder
		             //configure the pen (color & material)
		             GL11.glColor3f(blue[0],blue[1], blue[2]);
		             GL11.glMaterial( GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(blue));
		             GL11.glPushMatrix(); 
		             {
		            	 GL11.glTranslatef(0.5f,0.4f,0.0f);//translate the coordinate (+0.5x,+0.4y)
		            	 sphere.DrawSphere(0.25f, 32, 32);//draw the sphere standing for the left shoulder 
		            	 
		            	 // left arm
		            	 //configure the pen (color & material)
		            	 GL11.glColor3f(orange[0], orange[1], orange[2]);
		            	 GL11.glMaterial( GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(orange));
		            	 GL11.glPushMatrix(); 
		            	 {
		            		 GL11.glTranslatef(0.0f,0.0f,0.0f);//doesn't translate the coordinate
		            		 GL11.glRotatef(90.0f,1.0f,0.0f,0.0f);//rotate the coordinate 90 degree along (1,0,0)
		                     GL11.glRotatef(LimbRotation,1.0f,0.0f,0.0f);//rotate the coordinate "LimbRotation" degree along (1,0,0)
		                     cylinder.DrawCylinder(0.15f,0.7f,32);//draw the cylinder standing for the left arm


		                     // left elbow
		                     //configure the pen (color & material)
		                   	 GL11.glColor3f(blue[0], blue[1], blue[2]);
		                   	 GL11.glMaterial(GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(blue));
		                   	 GL11.glPushMatrix(); 
		                   	 {
		                   		 GL11.glTranslatef(0.0f,0.0f,0.75f);//translate the coordinate (+0.75z)
		                   		 sphere.DrawSphere(0.2f, 32, 32); //draw the sphere standing for the left elbow
		                   		 
		                   		 //left forearm
		                   		 //configure the pen (color & material)
		                       	 GL11.glColor3f(orange[0], orange[1], orange[2]);
		                       	 GL11.glMaterial( GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(orange));
		                       	 
		                       	 GL11.glPushMatrix(); 
		                       	 {
		                       		 GL11.glTranslatef(0.0f,0.0f,0.0f);//doesn't translate the coordinate system
		                       		 GL11.glRotatef(90.0f,1.0f,0.0f,0.0f);//rotate the system 90 degree along (1,0,0) 
		                       		 cylinder.DrawCylinder(0.1f,0.7f,32);//drawing the cylinder standing for the left forearm
		                       		 
		                       		 // left hand
		                       		 //configure the pen (color & material)
		                       		 GL11.glColor3f(blue[0], blue[1], blue[2]);
		                       		 GL11.glMaterial( GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(blue));
		                       		 
		                       		 GL11.glPushMatrix(); 
		                       		 {
		                       			 GL11.glTranslatef(0.0f,0.0f,0.75f);//translate the coordinate system (o.75z)
		                       			 sphere.DrawSphere(0.2f, 32, 32); //drawing the sphere standing for the left hand
		                       		 } 
		                       		 GL11.glPopMatrix();//pop matrix --> back to forearm
		                       	 } 
		                       	 GL11.glPopMatrix();//pop matrix --> back to elbow
		                   	 } 
		                   	 GL11.glPopMatrix();//pop matrix --> back to arm
		            	 } 
		            	 GL11.glPopMatrix ();//pop matrix --> back to shoulder
		             } 
		             GL11.glPopMatrix ();//pop matrix--> back to chest

		             // right shoulder
		             //configure the pen (color & material)
		             GL11.glColor3f(blue[0],blue[1], blue[2]);
		             GL11.glMaterial( GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(blue));
		             GL11.glPushMatrix();
		             {
		            	 GL11.glTranslatef(-0.5f,0.4f,0.0f);//translate the matrix along(0.5 0.4 0.0)
		            	 sphere.DrawSphere(0.25f, 32, 32); //draw the sphere standing for shoulder
		            	 
		            	 // right arm
		            	 //configure the pen (color & material)
		            	 GL11.glColor3f(orange[0], orange[1], orange[2]);
		            	 GL11.glMaterial( GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(orange));
		            	 GL11.glPushMatrix();
		            	 {
		            		 GL11.glTranslatef(0.0f,0.0f,0.0f);//doesn't translate the coordinate system  
		            		 GL11.glRotatef(90.0f,1.0f,0.0f,0.0f);//rotate the coordinate system 90 degree along (1,0,0)
		            		 GL11.glRotatef(-LimbRotation,1.0f,0.0f,0.0f); //rotate the coordinate system 90 degree along (1,0,0)
		            		 cylinder.DrawCylinder(0.15f,0.7f,32);//draw the cylinder standing for the right arm
		            		 
		            		 // right elbow
		            		 //configure the pen (color & material)
		            		 GL11.glColor3f(blue[0], blue[1], blue[2]);
		            		 GL11.glMaterial(GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(blue));
		            		 
		            		 GL11.glPushMatrix(); 
		            		 {
		            			 GL11.glTranslatef(0.0f,0.0f,0.75f);//translate the coordinate system (+0.75z)
		            			 sphere.DrawSphere(0.2f, 32, 32); //draw the sphere standing for elbow
		            			 
		            			 //right forearm
		            			 //configure the pen (color & material)
		            			 GL11.glColor3f(orange[0], orange[1], orange[2]);
		            			 GL11.glMaterial(GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(orange));
		            			 GL11.glPushMatrix(); 
		            			 {
		            				 GL11.glTranslatef(0.0f,0.0f,0.0f);//doesn't translate the coordinate system
		            				 GL11.glRotatef(90.0f,1.0f,0.0f,0.0f);//rotate the coordinate system 90 degree along (1,0,0)
		            				 cylinder.DrawCylinder(0.1f,0.7f,32);//draw the cylinder standing for forearm
		            				 
		            				 // right hand
		            				 //configure the pen (color & material)
		            				 GL11.glColor3f(blue[0], blue[1], blue[2]);
		            				 GL11.glMaterial( GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(blue));
		            				 GL11.glPushMatrix(); 
		            				 {
		            					 GL11.glTranslatef(0.0f,0.0f,0.75f);//translate the coordinate system (+0.75z)
		            					 sphere.DrawSphere(0.2f, 32, 32); //draw the sphere standing for the hand
		            				 }
		            				 GL11.glPopMatrix();//pop matrix --> back to right forearm
		            			 }
		            			 GL11.glPopMatrix();//pop matrix --> back to right elbow
		            		 }
		            		 GL11.glPopMatrix();//pop matrix --> back to right arm
		            	 }
		            	 GL11.glPopMatrix();//pop matrix --> back to right shoulder
		             }
		             GL11.glPopMatrix();//pop matrix --> back to chest
		         } 
		         GL11.glPopMatrix();//pop matrix --> back to pelvis
		         
		         // left hip
		       	 GL11.glColor3f(blue[0], blue[1], blue[2]);
		       	 GL11.glMaterial( GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(blue));
		       	 GL11.glPushMatrix(); 
		       	 {
		       		 GL11.glTranslatef(-0.5f,-0.2f,0.0f);
		       		 sphere.DrawSphere(0.25f, 32, 32); 
		       		 
		       		 // left high leg
		           	 GL11.glColor3f(orange[0], orange[1], orange[2]);
		           	 GL11.glMaterial( GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(orange));
		           	 GL11.glPushMatrix(); 
		           	 {
		                    GL11.glTranslatef(0.0f,0.0f,0.0f);
		                    GL11.glRotatef(0.0f,0.0f,0.0f,0.0f);
		                    
		                    GL11.glRotatef((-LimbRotation)+90,1.0f,0.0f,0.0f); 
		                    cylinder.DrawCylinder(0.15f,0.7f,32);

		                    // left knee
		                    GL11.glColor3f(blue[0], blue[1], blue[2]);
		                    GL11.glMaterial( GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(blue));
		                    GL11.glPushMatrix(); 
		                    {
		                    	GL11.glTranslatef(0.0f,0.0f,0.75f);
		                        GL11.glRotatef(0.0f,0.0f,0.0f,0.0f); 
		                        sphere.DrawSphere(0.25f, 32, 32); 

		                        //left low leg
		                   	 	GL11.glColor3f(orange[0], orange[1], orange[2]);
		                   	 	GL11.glMaterial( GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(orange));
		                        GL11.glPushMatrix(); 
		                        {
		                        	if(LimbRotation>0) {
			       						 GL11.glRotatef((-LimbRotation),1.0f,0.0f,0.0f);
			       					 }
		                        	GL11.glTranslatef(0.0f,0.0f,0.0f);
		                            //GL11.glRotatef(LimbRotation-90,1.0f,0.0f,0.0f);
		                            cylinder.DrawCylinder(0.15f,0.7f,32);

		                            // left foot
		                       	 	GL11.glColor3f(blue[0], blue[1], blue[2]);
		                       	 	GL11.glMaterial( GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(blue));
		                            GL11.glPushMatrix(); 
		                            {
		                                GL11.glTranslatef(0.0f,0.0f,0.75f);
		                                sphere.DrawSphere(0.3f, 32, 32);  
		                            } 
		                            GL11.glPopMatrix();
		                        } 
		                        GL11.glPopMatrix();
		                    } 
		                    GL11.glPopMatrix();
		           	 } 
		           	 GL11.glPopMatrix();
		       	 } 
		       	 GL11.glPopMatrix();
		       	 // pelvis
		       	 // right hip
		       	 GL11.glColor3f(blue[0], blue[1], blue[2]);
		       	 GL11.glMaterial( GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(blue));
		       	 GL11.glPushMatrix(); 
		       	 {
		       		 GL11.glTranslatef(0.5f,-0.2f,0.0f);
		       		 sphere.DrawSphere(0.25f, 32, 32);
		       		 GL11.glColor3f(orange[0], orange[1], orange[2]);
		       		 GL11.glMaterial( GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(orange));
		       		 GL11.glPushMatrix(); 
		       		 {
		       			 GL11.glTranslatef(0.0f,0.0f,0.0f);
		       			 GL11.glRotatef(0.0f,0.0f,0.0f,0.0f);
		       			 GL11.glRotatef((LimbRotation)+90,1.0f,0.0f,0.0f); 
		       			 cylinder.DrawCylinder(0.15f,0.7f,32);
				                    
		       			 GL11.glColor3f(blue[0], blue[1], blue[2]);
		       			 GL11.glMaterial( GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(blue));
		       			 GL11.glPushMatrix(); 
		       			 {
		       				 GL11.glTranslatef(0.0f,0.0f,0.75f);
		       				 GL11.glRotatef(0.0f,0.0f,0.0f,0.0f); 
		       				 sphere.DrawSphere(0.25f, 32, 32); 
		       				 
		       				 GL11.glColor3f(orange[0], orange[1], orange[2]);
		       				 GL11.glMaterial( GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(orange));
		       				 GL11.glPushMatrix(); 
		       				 {
		       					 GL11.glTranslatef(0.0f,0.0f,0.0f);
		       					 if(LimbRotation<0) {
		       						 GL11.glRotatef((LimbRotation),1.0f,0.0f,0.0f);
		       					 }
		       					 cylinder.DrawCylinder(0.15f,0.7f,32);
		       					 
		       					 GL11.glColor3f(blue[0], blue[1], blue[2]);
		       					 GL11.glMaterial( GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(blue));
		       					 GL11.glPushMatrix(); 
		       					 {
		       						 GL11.glTranslatef(0.0f,0.0f,0.75f);
		       						 sphere.DrawSphere(0.3f, 32, 32); 
		       					 }
		       					 GL11.glPopMatrix();
		       				 }
		       				 GL11.glPopMatrix();
		       			 }
		       			 GL11.glPopMatrix();
		       		 }
		       		 GL11.glPopMatrix();
		       	 }
		       	 GL11.glPopMatrix();
			 } 
			 GL11.glPopMatrix();
		 } 

		
	}
		
}
 

	 