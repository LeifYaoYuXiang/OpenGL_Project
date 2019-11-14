package objects3D;

import java.io.IOException;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.opengl.Texture;

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
//	float sitDistance=0f;
//	float sitStep=0.20f;
	
	public Human() {
	}
	
	
	
	public void DrawHuman(float delta,boolean GoodAnimation, Texture faceTexture, Texture bodyTexture, Texture pelvisTexture ){ 
		//the angle used in the the method glRotatef()
		float theta = (float) (delta * 25 * Math.PI);
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
			 //GL11.glRotatef(-thetaDeg+180,0.0f,1.0f, 0.0f);
			 
			 
			 GL11.glTranslatef(0.0f,0.5f,0.0f);//translate the coordinate to +0.5y 
			 try {
				GL11.glRotatef(90, 1, 0, 0);
				sphere.DrawTexSphere(0.5f, 16, 16,pelvisTexture);
				GL11.glRotatef(-90, 1, 0, 0);
				 
			} catch (IOException e) {
				e.printStackTrace();
			} //draw the sphere standing for the pelvis
			 
			 
			 //chest
			 //configure the pen (color & material)
			 GL11.glColor3f(green[0], green[1], green[2]);
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
//		            	 sphere.DrawSphere(0.5f, 32, 32);//draw the sphere standing for the head
		            	 try {
		            		 GL11.glRotatef(60, 0.0f, 0.0f, 1.0f);
							sphere.DrawTexSphere(0.5f, 32, 32, faceTexture);
						} catch (IOException e2) {
							// TODO Auto-generated catch block
							e2.printStackTrace();
						}
		            	 
		            	 
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
		                     if(LimbRotation>0) {
		                    	 GL11.glRotatef(-LimbRotation, 0, 0, 1);
		                     }
		                     cylinder.DrawCylinder(0.15f,0.75f,32);//draw the cylinder standing for the left arm
		                     

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
		            		 if(LimbRotation<0) {
		                    	 GL11.glRotatef(-LimbRotation, 0, 0, 1);
		                     }
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
		         //configure the pen (color & material)
		       	 GL11.glColor3f(blue[0], blue[1], blue[2]);
		       	 GL11.glMaterial( GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(blue));
		       	 GL11.glPushMatrix(); 
		       	 {
		       		 GL11.glTranslatef(-0.5f,-0.5f,0.0f);//translate the coordinate system along (-0.5f,-0.2f)
		       		 sphere.DrawSphere(0.25f, 32, 32); //draw the sphere standing for hip 
		       		 
		       		 //left high leg
		       		 //configure the pen (color & material)
		           	 GL11.glColor3f(orange[0], orange[1], orange[2]);
		           	 GL11.glMaterial( GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(orange));
		           	 GL11.glPushMatrix(); 
		           	 {
		                    GL11.glTranslatef(0.0f,0.0f,0.0f);//doesn't translate the coordinate system
		                    GL11.glRotatef(0.0f,0.0f,0.0f,0.0f);//doesn't rotate the cylinder 
		                    
		                    GL11.glRotatef((-LimbRotation)+90,1.0f,0.0f,0.0f); //rotate the cylinder to simulate the action of running legs
		                    cylinder.DrawCylinder(0.15f,0.7f,32);//draw the cylinder standing for high legs

		                    // left knee
		                    //configure the pen (color & material)
		                    GL11.glColor3f(blue[0], blue[1], blue[2]);
		                    GL11.glMaterial( GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(blue));
		                    GL11.glPushMatrix(); 
		                    {
		                    	GL11.glTranslatef(0.0f,0.0f,0.75f);//translate the coordinate along (0.75z)
		                        GL11.glRotatef(0.0f,0.0f,0.0f,0.0f); //doesn't rotate the coordinate system
		                        sphere.DrawSphere(0.25f, 32, 32); //draw the sphere standing for the knee

		                        //left low leg
		                        //configure the pen (color & material)
		                   	 	GL11.glColor3f(orange[0], orange[1], orange[2]);
		                   	 	GL11.glMaterial( GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(orange));
		                        GL11.glPushMatrix(); 
		                        {
		                        	if(LimbRotation>0) {
		                        		//if the left low leg is behind the body, then we need to rotate it up
			       						GL11.glRotatef((-LimbRotation),1.0f,0.0f,0.0f);
			       					 }
		                        	GL11.glTranslatef(0.0f,0.0f,0.0f);//doesn't translate the cylinder
		                            cylinder.DrawCylinder(0.15f,0.7f,32);//draw the cylinder standing for left low leg

		                            //left foot
		                            //configure the pen (color & material)
		                       	 	GL11.glColor3f(blue[0], blue[1], blue[2]);
		                       	 	GL11.glMaterial( GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(blue));
		                            GL11.glPushMatrix(); 
		                            {
		                                GL11.glTranslatef(0.0f,0.0f,0.75f);//translate the coordinate system (0.75z)
		                                sphere.DrawSphere(0.3f, 32, 32);  //draw the sphere standing for the left foot
		                            } 
		                            GL11.glPopMatrix();//pop matrix --> back to low leg
		                        } 
		                        GL11.glPopMatrix();//pop matrix --> back to knee
		                    } 
		                    GL11.glPopMatrix();//pop matrix --> back to high leg
		           	 } 
		           	 GL11.glPopMatrix();//pop matrix --> back to left hip
		       	 } 
		       	 GL11.glPopMatrix();//pop matrix --> back to pelvis
		       	 
		       	 //right hip
		       	 //configure the pen (color & material)
		       	 GL11.glColor3f(blue[0], blue[1], blue[2]);
		       	 GL11.glMaterial( GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(blue));
		       	 GL11.glPushMatrix(); 
		       	 {
		       		 
		       		 GL11.glTranslatef(0.5f,-0.5f,0.0f);//translate the coordinate along (0.5,-0.2,0.0)
		       		 sphere.DrawSphere(0.25f, 32, 32);//draw the sphere standing for the right hip
		       		 
		       		 //right high leg
		       		 //configure the pen (color & material)
		       		 GL11.glColor3f(orange[0], orange[1], orange[2]);
		       		 GL11.glMaterial( GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(orange));
		       		 GL11.glPushMatrix(); 
		       		 {
		       			 GL11.glTranslatef(0.0f,0.0f,0.0f);//doesn't translate the coordinate system
		       			 GL11.glRotatef(0.0f,0.0f,0.0f,0.0f);//doesn't rotate the coordinate system
		       			 GL11.glRotatef((LimbRotation)+90,1.0f,0.0f,0.0f); //rotate the coordinate system (LimbRotation+90) degree along (1.0,0.0,0.0) w
		       			 cylinder.DrawCylinder(0.15f,0.7f,32);//draw the cylinder standing for right high pen 
				         
		       			 //right knee
		       			 //configure the pen (color & material)
		       			 GL11.glColor3f(blue[0], blue[1], blue[2]);
		       			 GL11.glMaterial( GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(blue));
		       			 GL11.glPushMatrix(); 
		       			 {
		       				 GL11.glTranslatef(0.0f,0.0f,0.75f);//translate the coordinate (0.75z)
		       				 GL11.glRotatef(0.0f,0.0f,0.0f,0.0f); 
		       				 sphere.DrawSphere(0.25f, 32, 32); //draw the sphere standing for knee
		       				 
		       				 //right low leg
		       				 //configure the pen (color & material)
		       				 GL11.glColor3f(orange[0], orange[1], orange[2]);
		       				 GL11.glMaterial( GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(orange));
		       				 GL11.glPushMatrix(); 
		       				 {
		       					 GL11.glTranslatef(0.0f,0.0f,0.0f);//doesn't rotate the coordinate system
		       					 if(LimbRotation<0) {
		       						 //if the right leg is behind the body
		       						 GL11.glRotatef((LimbRotation),1.0f,0.0f,0.0f);//rotate the right leg up
		       					 }
		       					 cylinder.DrawCylinder(0.15f,0.7f,32);//draw the cylinder standing for leg
		       					 
		       					 //right foot
		       					 //configure the pen (color & material)
		       					 GL11.glColor3f(blue[0], blue[1], blue[2]);
		       					 GL11.glMaterial( GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(blue));
		       					 GL11.glPushMatrix(); 
		       					 {
		       						 GL11.glTranslatef(0.0f,0.0f,0.75f);//translate the coordinate (0.75z)
		       						 sphere.DrawSphere(0.3f, 32, 32); //draw the sphere standing for the foot 
		       					 }
		       					 GL11.glPopMatrix();//pop matrix --> back to low leg
		       				 }
		       				 GL11.glPopMatrix();//pop matrix --> back to knee
		       			 }
		       			 GL11.glPopMatrix();//pop matrix --> back to high leg 
		       		 }
		       		 GL11.glPopMatrix();//pop matrix --> back to hip 
		       	 }
		       	 GL11.glPopMatrix();//pop matrix --> back to pelvis 
			 } 
			 GL11.glPopMatrix();//pop matrix --> empty stack !
		 } 
		 //finish drawing human being

		
	}
	
	public void DrawWakingHuman(float delta,boolean GoodAnimation, Texture faceTexture, Texture bodyTexture, Texture pelvisTexture) {
		//the angle used in the the method glRotatef()
				float theta = (float) (delta * 25 * Math.PI);
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
					 //GL11.glRotatef(-thetaDeg+180,0.0f,1.0f, 0.0f);
					 
					 
					 GL11.glTranslatef(0.0f,0.5f,0.0f);//translate the coordinate to +0.5y 
					 try {
						GL11.glRotatef(90, 1, 0, 0);
						if(thetaDeg<90) {
							GL11.glRotatef(-thetaDeg,1.0f,0.0f, 0.0f);
						}else {
							GL11.glRotatef(-90, 1, 0, 0);
						}
						sphere.DrawTexSphere(0.5f, 16, 16,pelvisTexture);
						GL11.glRotatef(-90, 1, 0, 0);
						
						 
					} catch (IOException e) {
						e.printStackTrace();
					} //draw the sphere standing for the pelvis
					 
					 
					 //chest
					 //configure the pen (color & material)
					 GL11.glColor3f(green[0], green[1], green[2]);
					 GL11.glMaterial(GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(green));
					 GL11.glPushMatrix(); 
					 {
						 GL11.glTranslatef(0.0f,0.5f,0.0f);//translate the coordinate to +0.5y
						 
						 try {
								GL11.glRotatef(90, 1, 0, 0);
								sphere.DrawTexSphere(0.5f, 32, 32,bodyTexture);//chest
								GL11.glRotatef(-90, 1, 0, 0);
								 
							} catch (IOException e) {
								e.printStackTrace();
							}
						 
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
				            	 try {
				            		 GL11.glRotatef(60, 0.0f, 0.0f, 1.0f);
									sphere.DrawTexSphere(0.5f, 32, 32, faceTexture);
								} catch (IOException e2) {
									// TODO Auto-generated catch block
									e2.printStackTrace();
								}
				            	 
				            	 
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
				                     if(thetaDeg<90) {
				                    	 //GL11.glRotatef(thetaDeg, 0, 1, 0);
				                     }else if(thetaDeg>=90&&thetaDeg<180) {
				                    	 GL11.glRotatef(thetaDeg-90, 0, 1, 0); 
				                     }else if(thetaDeg>=180&&thetaDeg<270) {
				                    	 GL11.glRotatef(90, 0, 1, 0);
				                     }else if(thetaDeg>270) {
				                    	 GL11.glRotatef(thetaDeg-360, 0, -1, 0);
				                     }
				                     if(LimbRotation>0) {
				                    	 GL11.glRotatef(-LimbRotation, 0, 0, 1);
				                     }
				                     cylinder.DrawCylinder(0.15f,0.75f,32);//draw the cylinder standing for the left arm
				                     

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
				                       		 //GL11.glRotatef(90.0f,1.0f,0.0f,0.0f);//rotate the system 90 degree along (1,0,0)
				                       		 if(thetaDeg<90) {
				                       			 GL11.glRotatef(thetaDeg, 1, 0, 0);
				                       		 }else if(thetaDeg>=90&&thetaDeg<180) {
				                       			GL11.glRotatef(90, 1, 0, 0);
				                       		 }else if(thetaDeg>=180&&thetaDeg<270) {
				                       			GL11.glRotatef(-thetaDeg+270, 1, 0, 0); 
				                       		 }else if(thetaDeg>270) {
				                       			//GL11.glRotatef(thetaDeg-180, 1, 0, 0); 
				                       		 }
				                       		 
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
				            		 if(LimbRotation<0) {
				                    	 GL11.glRotatef(-LimbRotation, 0, 0, 1);
				                     }
				            		 if(thetaDeg<90) {
				                    	 //GL11.glRotatef(thetaDeg, 0, 1, 0);
				                     }else if(thetaDeg>=90&&thetaDeg<180) {
				                    	 GL11.glRotatef(thetaDeg-90, 0, -1, 0); 
				                     }else if(thetaDeg>=180&&thetaDeg<270) {
				                    	 GL11.glRotatef(90, 0, -1, 0);
				                     }else if(thetaDeg>270) {
				                    	 GL11.glRotatef(thetaDeg-360, 0, 1, 0);
				                     }
				                     if(LimbRotation>0) {
				                    	 GL11.glRotatef(-LimbRotation, 0, 0, 1);
				                     }
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
				            				 //GL11.glRotatef(90.0f,1.0f,0.0f,0.0f);//rotate the coordinate system 90 degree along (1,0,0)
				            				 if(thetaDeg<90) {
				                       			 GL11.glRotatef(thetaDeg, 1, 0, 0);
				                       		 }else if(thetaDeg>=90&&thetaDeg<180) {
				                       			GL11.glRotatef(90, 1, 0, 0);
				                       		 }else if(thetaDeg>=180&&thetaDeg<270) {
				                       			GL11.glRotatef(-thetaDeg+270, 1, 0, 0); 
				                       		 }else if(thetaDeg>270) {
				                       			//GL11.glRotatef(thetaDeg-180, 1, 0, 0); 
				                       		 }
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
				         //configure the pen (color & material)
				       	 GL11.glColor3f(blue[0], blue[1], blue[2]);
				       	 GL11.glMaterial( GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(blue));
				       	 GL11.glPushMatrix(); 
				       	 {
				       		 GL11.glTranslatef(-0.5f,-0.5f,0.0f);//translate the coordinate system along (-0.5f,-0.2f)
				       		 sphere.DrawSphere(0.25f, 32, 32); //draw the sphere standing for hip 
				       		 
				       		 //left high leg
				       		 //configure the pen (color & material)
				           	 GL11.glColor3f(orange[0], orange[1], orange[2]);
				           	 GL11.glMaterial( GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(orange));
				           	 GL11.glPushMatrix(); 
				           	 {
				                    GL11.glTranslatef(0.0f,0.0f,0.0f);//doesn't translate the coordinate system
				                    GL11.glRotatef(0.0f,0.0f,0.0f,0.0f);//doesn't rotate the cylinder 
				                    
				                    GL11.glRotatef((-LimbRotation)+90,1.0f,0.0f,0.0f); //rotate the cylinder to simulate the action of running legs
				                    if(thetaDeg<90) {
			       						 GL11.glRotatef(-thetaDeg, -1, 0, 0);
			       					 }else {
			       						 GL11.glRotatef(-90, -1, 0, 0);
			       					 }
				                    cylinder.DrawCylinder(0.15f,0.7f,32);//draw the cylinder standing for high legs

				                    // left knee
				                    //configure the pen (color & material)
				                    GL11.glColor3f(blue[0], blue[1], blue[2]);
				                    GL11.glMaterial( GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(blue));
				                    GL11.glPushMatrix(); 
				                    {
				                    	GL11.glTranslatef(0.0f,0.0f,0.75f);//translate the coordinate along (0.75z)
				                        GL11.glRotatef(0.0f,0.0f,0.0f,0.0f); //doesn't rotate the coordinate system
				                        sphere.DrawSphere(0.25f, 32, 32); //draw the sphere standing for the knee

				                        //left low leg
				                        //configure the pen (color & material)
				                   	 	GL11.glColor3f(orange[0], orange[1], orange[2]);
				                   	 	GL11.glMaterial( GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(orange));
				                        GL11.glPushMatrix(); 
				                        {
				                        	if(LimbRotation>0) {
				                        		//if the left low leg is behind the body, then we need to rotate it up
					       						GL11.glRotatef((-LimbRotation),1.0f,0.0f,0.0f);
					       					 }
				                        	if(thetaDeg<90) {
					       						 GL11.glRotatef(-thetaDeg, 1, 0, 0);
					       					 }else {
					       						 GL11.glRotatef(-90, 1, 0, 0);
					       					 }
				                        	GL11.glTranslatef(0.0f,0.0f,0.0f);//doesn't translate the cylinder
				                            cylinder.DrawCylinder(0.15f,0.7f,32);//draw the cylinder standing for left low leg

				                            //left foot
				                            //configure the pen (color & material)
				                       	 	GL11.glColor3f(blue[0], blue[1], blue[2]);
				                       	 	GL11.glMaterial( GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(blue));
				                            GL11.glPushMatrix(); 
				                            {
				                                GL11.glTranslatef(0.0f,0.0f,0.75f);//translate the coordinate system (0.75z)
				                                sphere.DrawSphere(0.3f, 32, 32);  //draw the sphere standing for the left foot
				                            } 
				                            GL11.glPopMatrix();//pop matrix --> back to low leg
				                        } 
				                        GL11.glPopMatrix();//pop matrix --> back to knee
				                    } 
				                    GL11.glPopMatrix();//pop matrix --> back to high leg
				           	 } 
				           	 GL11.glPopMatrix();//pop matrix --> back to left hip
				       	 } 
				       	 GL11.glPopMatrix();//pop matrix --> back to pelvis
				       	 
				       	 //right hip
				       	 //configure the pen (color & material)
				       	 GL11.glColor3f(blue[0], blue[1], blue[2]);
				       	 GL11.glMaterial( GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(blue));
				       	 GL11.glPushMatrix(); 
				       	 {
				       		 
				       		 GL11.glTranslatef(0.5f,-0.5f,0.0f);//translate the coordinate along (0.5,-0.2,0.0)
				       		 sphere.DrawSphere(0.25f, 32, 32);//draw the sphere standing for the right hip
				       		 
				       		 //right high leg
				       		 //configure the pen (color & material)
				       		 GL11.glColor3f(orange[0], orange[1], orange[2]);
				       		 GL11.glMaterial( GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(orange));
				       		 GL11.glPushMatrix(); 
				       		 {
				       			 GL11.glTranslatef(0.0f,0.0f,0.0f);//doesn't translate the coordinate system
				       			 GL11.glRotatef(0.0f,0.0f,0.0f,0.0f);//doesn't rotate the coordinate system
				       			 GL11.glRotatef((LimbRotation)+90,1.0f,0.0f,0.0f); //rotate the coordinate system (LimbRotation+90) degree along (1.0,0.0,0.0) w
				       			 if(thetaDeg<90) {
		       						 GL11.glRotatef(-thetaDeg, -1, 0, 0);
		       					 }else {
		       						 GL11.glRotatef(-90, -1, 0, 0);
		       					 }
				       			 cylinder.DrawCylinder(0.15f,0.7f,32);//draw the cylinder standing for right high pen 
						         
				       			 //right knee
				       			 //configure the pen (color & material)
				       			 GL11.glColor3f(blue[0], blue[1], blue[2]);
				       			 GL11.glMaterial( GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(blue));
				       			 GL11.glPushMatrix(); 
				       			 {
				       				 GL11.glTranslatef(0.0f,0.0f,0.75f);//translate the coordinate (0.75z)
				       				 GL11.glRotatef(0.0f,0.0f,0.0f,0.0f); 
				       				 sphere.DrawSphere(0.25f, 32, 32); //draw the sphere standing for knee
				       				 
				       				 //right low leg
				       				 //configure the pen (color & material)
				       				 GL11.glColor3f(orange[0], orange[1], orange[2]);
				       				 GL11.glMaterial( GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(orange));
				       				 GL11.glPushMatrix(); 
				       				 {
				       					 GL11.glTranslatef(0.0f,0.0f,0.0f);//doesn't rotate the coordinate system
				       					 if(LimbRotation<0) {
				       						 //if the right leg is behind the body
				       						 GL11.glRotatef((LimbRotation),1.0f,0.0f,0.0f);//rotate the right leg up
				       					 }
				       					 if(thetaDeg<90) {
				       						 GL11.glRotatef(-thetaDeg, 1, 0, 0);
				       					 }else {
				       						 GL11.glRotatef(-90, 1, 0, 0);
				       					 }
				       					 //GL11.glRotatef(thetaDeg, 1, 0, 0);
				       					 cylinder.DrawCylinder(0.15f,0.7f,32);//draw the cylinder standing for leg
				       					 
				       					 //right foot
				       					 //configure the pen (color & material)
				       					 GL11.glColor3f(blue[0], blue[1], blue[2]);
				       					 GL11.glMaterial( GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(blue));
				       					 GL11.glPushMatrix(); 
				       					 {
				       						 GL11.glTranslatef(0.0f,0.0f,0.75f);//translate the coordinate (0.75z)
				       						 sphere.DrawSphere(0.3f, 32, 32); //draw the sphere standing for the foot 
				       					 }
				       					 GL11.glPopMatrix();//pop matrix --> back to low leg
				       				 }
				       				 GL11.glPopMatrix();//pop matrix --> back to knee
				       			 }
				       			 GL11.glPopMatrix();//pop matrix --> back to high leg 
				       		 }
				       		 GL11.glPopMatrix();//pop matrix --> back to hip 
				       	 }
				       	 GL11.glPopMatrix();//pop matrix --> back to pelvis 
					 } 
					 GL11.glPopMatrix();//pop matrix --> empty stack !
				 } 
				 //finish drawing human being		
	}

	public void DrawRotateWalkingHuman(float delta,boolean GoodAnimation, Texture faceTexture, Texture bodyTexture, Texture pelvisTexture) {
		//the angle used in the the method glRotatef()
				float theta = (float) (delta * 25 * Math.PI);
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
					 
					 GL11.glTranslatef(0.0f,0.5f,0.0f);//translate the coordinate to +0.5y 
					 try {
						GL11.glRotatef(90, 1, 0, 0);
						sphere.DrawTexSphere(0.5f, 16, 16,pelvisTexture);
						GL11.glRotatef(-90, 1, 0, 0);
						 
					} catch (IOException e) {
						e.printStackTrace();
					} //draw the sphere standing for the pelvis
					 
					 
					 //chest
					 //configure the pen (color & material)
					 GL11.glColor3f(green[0], green[1], green[2]);
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
//				            	 sphere.DrawSphere(0.5f, 32, 32);//draw the sphere standing for the head
				            	 try {
				            		 GL11.glRotatef(60, 0.0f, 0.0f, 1.0f);
									sphere.DrawTexSphere(0.5f, 32, 32, faceTexture);
								} catch (IOException e2) {
									// TODO Auto-generated catch block
									e2.printStackTrace();
								}
				            	 
				            	 
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
				                     if(LimbRotation>0) {
				                    	 GL11.glRotatef(-LimbRotation, 0, 0, 1);
				                     }
				                     cylinder.DrawCylinder(0.15f,0.75f,32);//draw the cylinder standing for the left arm
				                     

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
				                       		//GL11.glRotatef(90.0f,1.0f,0.0f,0.0f);//rotate the system 90 degree along (1,0,0)
				                       		 
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
				            		 if(LimbRotation<0) {
				                    	 GL11.glRotatef(-LimbRotation, 0, 0, 1);
				                     }
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
				            				 //GL11.glRotatef(90.0f,1.0f,0.0f,0.0f);//rotate the coordinate system 90 degree along (1,0,0)
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
				         //configure the pen (color & material)
				       	 GL11.glColor3f(blue[0], blue[1], blue[2]);
				       	 GL11.glMaterial( GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(blue));
				       	 GL11.glPushMatrix(); 
				       	 {
				       		 GL11.glTranslatef(-0.5f,-0.5f,0.0f);//translate the coordinate system along (-0.5f,-0.2f)
				       		 sphere.DrawSphere(0.25f, 32, 32); //draw the sphere standing for hip 
				       		 
				       		 //left high leg
				       		 //configure the pen (color & material)
				           	 GL11.glColor3f(orange[0], orange[1], orange[2]);
				           	 GL11.glMaterial( GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(orange));
				           	 GL11.glPushMatrix(); 
				           	 {
				                    GL11.glTranslatef(0.0f,0.0f,0.0f);//doesn't translate the coordinate system
				                    GL11.glRotatef(0.0f,0.0f,0.0f,0.0f);//doesn't rotate the cylinder 
				                    
				                    GL11.glRotatef((-LimbRotation)+90,1.0f,0.0f,0.0f); //rotate the cylinder to simulate the action of running legs
				                    cylinder.DrawCylinder(0.15f,0.7f,32);//draw the cylinder standing for high legs

				                    // left knee
				                    //configure the pen (color & material)
				                    GL11.glColor3f(blue[0], blue[1], blue[2]);
				                    GL11.glMaterial( GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(blue));
				                    GL11.glPushMatrix(); 
				                    {
				                    	GL11.glTranslatef(0.0f,0.0f,0.75f);//translate the coordinate along (0.75z)
				                        GL11.glRotatef(0.0f,0.0f,0.0f,0.0f); //doesn't rotate the coordinate system
				                        sphere.DrawSphere(0.25f, 32, 32); //draw the sphere standing for the knee

				                        //left low leg
				                        //configure the pen (color & material)
				                   	 	GL11.glColor3f(orange[0], orange[1], orange[2]);
				                   	 	GL11.glMaterial( GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(orange));
				                        GL11.glPushMatrix(); 
				                        {
				                        	if(LimbRotation>0) {
				                        		//if the left low leg is behind the body, then we need to rotate it up
					       						GL11.glRotatef((-LimbRotation),1.0f,0.0f,0.0f);
					       					 }
				                        	GL11.glTranslatef(0.0f,0.0f,0.0f);//doesn't translate the cylinder
				                            cylinder.DrawCylinder(0.15f,0.7f,32);//draw the cylinder standing for left low leg

				                            //left foot
				                            //configure the pen (color & material)
				                       	 	GL11.glColor3f(blue[0], blue[1], blue[2]);
				                       	 	GL11.glMaterial( GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(blue));
				                            GL11.glPushMatrix(); 
				                            {
				                                GL11.glTranslatef(0.0f,0.0f,0.75f);//translate the coordinate system (0.75z)
				                                sphere.DrawSphere(0.3f, 32, 32);  //draw the sphere standing for the left foot
				                            } 
				                            GL11.glPopMatrix();//pop matrix --> back to low leg
				                        } 
				                        GL11.glPopMatrix();//pop matrix --> back to knee
				                    } 
				                    GL11.glPopMatrix();//pop matrix --> back to high leg
				           	 } 
				           	 GL11.glPopMatrix();//pop matrix --> back to left hip
				       	 } 
				       	 GL11.glPopMatrix();//pop matrix --> back to pelvis
				       	 
				       	 //right hip
				       	 //configure the pen (color & material)
				       	 GL11.glColor3f(blue[0], blue[1], blue[2]);
				       	 GL11.glMaterial( GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(blue));
				       	 GL11.glPushMatrix(); 
				       	 {
				       		 
				       		 GL11.glTranslatef(0.5f,-0.5f,0.0f);//translate the coordinate along (0.5,-0.2,0.0)
				       		 sphere.DrawSphere(0.25f, 32, 32);//draw the sphere standing for the right hip
				       		 
				       		 //right high leg
				       		 //configure the pen (color & material)
				       		 GL11.glColor3f(orange[0], orange[1], orange[2]);
				       		 GL11.glMaterial( GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(orange));
				       		 GL11.glPushMatrix(); 
				       		 {
				       			 GL11.glTranslatef(0.0f,0.0f,0.0f);//doesn't translate the coordinate system
				       			 GL11.glRotatef(0.0f,0.0f,0.0f,0.0f);//doesn't rotate the coordinate system
				       			 GL11.glRotatef((LimbRotation)+90,1.0f,0.0f,0.0f); //rotate the coordinate system (LimbRotation+90) degree along (1.0,0.0,0.0) w
				       			 cylinder.DrawCylinder(0.15f,0.7f,32);//draw the cylinder standing for right high pen 
						         
				       			 //right knee
				       			 //configure the pen (color & material)
				       			 GL11.glColor3f(blue[0], blue[1], blue[2]);
				       			 GL11.glMaterial( GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(blue));
				       			 GL11.glPushMatrix(); 
				       			 {
				       				 GL11.glTranslatef(0.0f,0.0f,0.75f);//translate the coordinate (0.75z)
				       				 GL11.glRotatef(0.0f,0.0f,0.0f,0.0f); 
				       				 sphere.DrawSphere(0.25f, 32, 32); //draw the sphere standing for knee
				       				 
				       				 //right low leg
				       				 //configure the pen (color & material)
				       				 GL11.glColor3f(orange[0], orange[1], orange[2]);
				       				 GL11.glMaterial( GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(orange));
				       				 GL11.glPushMatrix(); 
				       				 {
				       					 GL11.glTranslatef(0.0f,0.0f,0.0f);//doesn't rotate the coordinate system
				       					 if(LimbRotation<0) {
				       						 //if the right leg is behind the body
				       						 GL11.glRotatef((LimbRotation),1.0f,0.0f,0.0f);//rotate the right leg up
				       					 }
				       					 cylinder.DrawCylinder(0.15f,0.7f,32);//draw the cylinder standing for leg
				       					 
				       					 //right foot
				       					 //configure the pen (color & material)
				       					 GL11.glColor3f(blue[0], blue[1], blue[2]);
				       					 GL11.glMaterial( GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(blue));
				       					 GL11.glPushMatrix(); 
				       					 {
				       						 GL11.glTranslatef(0.0f,0.0f,0.75f);//translate the coordinate (0.75z)
				       						 sphere.DrawSphere(0.3f, 32, 32); //draw the sphere standing for the foot 
				       					 }
				       					 GL11.glPopMatrix();//pop matrix --> back to low leg
				       				 }
				       				 GL11.glPopMatrix();//pop matrix --> back to knee
				       			 }
				       			 GL11.glPopMatrix();//pop matrix --> back to high leg 
				       		 }
				       		 GL11.glPopMatrix();//pop matrix --> back to hip 
				       	 }
				       	 GL11.glPopMatrix();//pop matrix --> back to pelvis 
					 } 
					 GL11.glPopMatrix();//pop matrix --> empty stack !
				 } 
				 //finish drawing human being
	}
	
	public void DrawSittingHuman(float delta,boolean GoodAnimation, Texture faceTexture, Texture bodyTexture, Texture pelvisTexture,float SittingDistance) {
		//the angle used in the the method glRotatef()
		float theta = (float) (delta * 25 * Math.PI);
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
			 if(thetaDeg<90) {
				 GL11.glRotatef(thetaDeg, 0, 1, 0);
			 }else if(thetaDeg>=90&&thetaDeg<270) {
				 GL11.glRotatef(90, 0, 1, 0);
			 }else {
				 GL11.glRotatef(360-thetaDeg, 0, 1, 0); 
			 }
			 
			 GL11.glTranslatef(0.0f,0.5f,0.0f);//translate the coordinate to +0.5y 
			 try {
				GL11.glRotatef(90, 1, 0, 0);
				if(thetaDeg>=90&&thetaDeg<180) {
					GL11.glTranslatef(0f, 0f, SittingDistance);
				 }
				else if(thetaDeg>=180&&thetaDeg<270){
					 GL11.glTranslatef(0f, 0f, 0.3f);
				 }
				sphere.DrawTexSphere(0.5f, 16, 16,pelvisTexture);
				GL11.glRotatef(-90, 1, 0, 0);
				 
			} catch (IOException e) {
				e.printStackTrace();
			} //draw the sphere standing for the pelvis
			 
			 
			 //chest
			 //configure the pen (color & material)
			 GL11.glColor3f(green[0], green[1], green[2]);
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
//		            	 sphere.DrawSphere(0.5f, 32, 32);//draw the sphere standing for the head
		            	 try {
		            		 GL11.glRotatef(60, 0.0f, 0.0f, 1.0f);
							sphere.DrawTexSphere(0.5f, 32, 32, faceTexture);
						} catch (IOException e2) {
							// TODO Auto-generated catch block
							e2.printStackTrace();
						}
		            	 
		            	 
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
		                     if(LimbRotation>0) {
		                    	 GL11.glRotatef(-LimbRotation, 0, 0, 1);
		                     }
		                     cylinder.DrawCylinder(0.15f,0.75f,32);//draw the cylinder standing for the left arm
		                     

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
		            		 if(LimbRotation<0) {
		                    	 GL11.glRotatef(-LimbRotation, 0, 0, 1);
		                     }
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
		         //configure the pen (color & material)
		       	 GL11.glColor3f(blue[0], blue[1], blue[2]);
		       	 GL11.glMaterial( GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(blue));
		       	 GL11.glPushMatrix(); 
		       	 {
		       		 GL11.glTranslatef(-0.5f,-0.5f,0.0f);//translate the coordinate system along (-0.5f,-0.2f)
		       		 sphere.DrawSphere(0.25f, 32, 32); //draw the sphere standing for hip 
		       		 
		       		 //left high leg
		       		 //configure the pen (color & material)
		           	 GL11.glColor3f(orange[0], orange[1], orange[2]);
		           	 GL11.glMaterial( GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(orange));
		           	 GL11.glPushMatrix(); 
		           	 {
		                    /**!!!**/
		           		 	GL11.glTranslatef(0.0f,0.0f,0.0f);//doesn't translate the coordinate system
		                    GL11.glRotatef(0.0f,0.0f,0.0f,0.0f);//doesn't rotate the cylinder 
		                    if(thetaDeg<90) {
		                    	GL11.glRotatef(-thetaDeg, -1, 0, 0);
		                    }else if(thetaDeg>=90&&thetaDeg<270){
		                    	GL11.glRotatef(-90, -1, 0, 0);
		                    }else {
		                    	GL11.glRotatef(thetaDeg-360, -1, 0, 0);
		                    }
		                    GL11.glRotatef((-LimbRotation)+90,1.0f,0.0f,0.0f); //rotate the cylinder to simulate the action of running legs
		                    cylinder.DrawCylinder(0.15f,0.7f,32);//draw the cylinder standing for high legs

		                    // left knee
		                    //configure the pen (color & material)
		                    GL11.glColor3f(blue[0], blue[1], blue[2]);
		                    GL11.glMaterial( GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(blue));
		                    GL11.glPushMatrix(); 
		                    {
		                    	GL11.glTranslatef(0.0f,0.0f,0.75f);//translate the coordinate along (0.75z)
		                        GL11.glRotatef(0.0f,0.0f,0.0f,0.0f); //doesn't rotate the coordinate system
		                        sphere.DrawSphere(0.25f, 32, 32); //draw the sphere standing for the knee

		                        //left low leg
		                        //configure the pen (color & material)
		                   	 	GL11.glColor3f(orange[0], orange[1], orange[2]);
		                   	 	GL11.glMaterial( GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(orange));
		                        GL11.glPushMatrix(); 
		                        {
		                        	if(LimbRotation>0) {
		                        		//if the left low leg is behind the body, then we need to rotate it up
			       						GL11.glRotatef((-LimbRotation),1.0f,0.0f,0.0f);
			       					 }
		                        	if(thetaDeg<90) {
		                        		GL11.glRotatef(thetaDeg, -1, 0, 0);
		                        	}else if(thetaDeg>=90&&thetaDeg<270){
		                        		GL11.glRotatef(90, -1, 0, 0);
		                        	}else {
		                        		GL11.glRotatef(thetaDeg, 1, 0, 0);
		                        	}
		                        	GL11.glTranslatef(0.0f,0.0f,0.0f);//doesn't translate the cylinder
		                            cylinder.DrawCylinder(0.15f,0.7f,32);//draw the cylinder standing for left low leg

		                            //left foot
		                            //configure the pen (color & material)
		                       	 	GL11.glColor3f(blue[0], blue[1], blue[2]);
		                       	 	GL11.glMaterial( GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(blue));
		                            GL11.glPushMatrix(); 
		                            {
		                                GL11.glTranslatef(0.0f,0.0f,0.75f);//translate the coordinate system (0.75z)
		                                sphere.DrawSphere(0.3f, 32, 32);  //draw the sphere standing for the left foot
		                            } 
		                            GL11.glPopMatrix();//pop matrix --> back to low leg
		                        } 
		                        GL11.glPopMatrix();//pop matrix --> back to knee
		                    } 
		                    GL11.glPopMatrix();//pop matrix --> back to high leg
		           	 } 
		           	 GL11.glPopMatrix();//pop matrix --> back to left hip
		       	 } 
		       	 GL11.glPopMatrix();//pop matrix --> back to pelvis
		       	 
		       	 //right hip
		       	 //configure the pen (color & material)
		       	 GL11.glColor3f(blue[0], blue[1], blue[2]);
		       	 GL11.glMaterial( GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(blue));
		       	 GL11.glPushMatrix(); 
		       	 {
		       		 
		       		 GL11.glTranslatef(0.5f,-0.5f,0.0f);//translate the coordinate along (0.5,-0.2,0.0)
		       		 sphere.DrawSphere(0.25f, 32, 32);//draw the sphere standing for the right hip
		       		 
		       		 //right high leg
		       		 //configure the pen (color & material)
		       		 GL11.glColor3f(orange[0], orange[1], orange[2]);
		       		 GL11.glMaterial( GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(orange));
		       		 GL11.glPushMatrix(); 
		       		 {
		       			 GL11.glTranslatef(0.0f,0.0f,0.0f);//doesn't translate the coordinate system
		       			 GL11.glRotatef(0.0f,0.0f,0.0f,0.0f);//doesn't rotate the coordinate system
		       			 if(thetaDeg<90) {
		       				GL11.glRotatef(thetaDeg, 1, 0, 0);
		       			 }else if(thetaDeg>=90&&thetaDeg<270) {
		       				GL11.glRotatef(90, 1, 0, 0); 
		       			 }else {
		       				GL11.glRotatef(thetaDeg, -1, 0, 0);
		       			 }
		       			 GL11.glRotatef((LimbRotation)+90,1.0f,0.0f,0.0f); //rotate the coordinate system (LimbRotation+90) degree along (1.0,0.0,0.0) w
		       			 
		       			 
		       			 cylinder.DrawCylinder(0.15f,0.7f,32);//draw the cylinder standing for right high pen 
				         
		       			 //right knee
		       			 //configure the pen (color & material)
		       			 GL11.glColor3f(blue[0], blue[1], blue[2]);
		       			 GL11.glMaterial( GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(blue));
		       			 GL11.glPushMatrix(); 
		       			 {
		       				 GL11.glTranslatef(0.0f,0.0f,0.75f);//translate the coordinate (0.75z)
		       				 GL11.glRotatef(0.0f,0.0f,0.0f,0.0f); 
		       				 sphere.DrawSphere(0.25f, 32, 32); //draw the sphere standing for knee
		       				 
		       				 //right low leg
		       				 //configure the pen (color & material)
		       				 GL11.glColor3f(orange[0], orange[1], orange[2]);
		       				 GL11.glMaterial( GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(orange));
		       				 GL11.glPushMatrix(); 
		       				 {
		       					 GL11.glTranslatef(0.0f,0.0f,0.0f);//doesn't rotate the coordinate system
		       					 if(thetaDeg<90) {
		 		       				GL11.glRotatef(thetaDeg, -1, 0, 0);
		 		       			 }else if(thetaDeg>=90&&thetaDeg<270){
		 		       				GL11.glRotatef(90, -1, 0, 0); 
		 		       			 }else {
		 		       				GL11.glRotatef(thetaDeg, 1, 0, 0);
		 		       			 }
		       					 if(LimbRotation<0) {
		       						 //if the right leg is behind the body
		       						 GL11.glRotatef((LimbRotation),1.0f,0.0f,0.0f);//rotate the right leg up
		       					 }
		       					 cylinder.DrawCylinder(0.15f,0.7f,32);//draw the cylinder standing for leg
		       					 
		       					 //right foot
		       					 //configure the pen (color & material)
		       					 GL11.glColor3f(blue[0], blue[1], blue[2]);
		       					 GL11.glMaterial( GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(blue));
		       					 GL11.glPushMatrix(); 
		       					 {
		       						 GL11.glTranslatef(0.0f,0.0f,0.75f);//translate the coordinate (0.75z)
		       						 sphere.DrawSphere(0.3f, 32, 32); //draw the sphere standing for the foot 
		       					 }
		       					 GL11.glPopMatrix();//pop matrix --> back to low leg
		       				 }
		       				 GL11.glPopMatrix();//pop matrix --> back to knee
		       			 }
		       			 GL11.glPopMatrix();//pop matrix --> back to high leg 
		       		 }
		       		 GL11.glPopMatrix();//pop matrix --> back to hip 
		       	 }
		       	 GL11.glPopMatrix();//pop matrix --> back to pelvis 
			 } 
			 GL11.glPopMatrix();//pop matrix --> empty stack !
		 } 
		 //finish drawing human being
		
	}
	
	public void DrawWithdrawHuman(float delta,boolean GoodAnimation, Texture faceTexture, Texture bodyTexture, Texture pelvisTexture,float withDraw ) {
		//the angle used in the the method glRotatef()
		float theta = (float) (delta * 25 * Math.PI);
		float LimbRotation;
		float thetaDeg = delta * 360-withDraw; 
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
			 GL11.glRotatef(-thetaDeg+180,0.0f,1.0f, 0.0f);
			 
			 
			 GL11.glTranslatef(0.0f,0.5f,0.0f);//translate the coordinate to +0.5y 
			 try {
				GL11.glRotatef(90, 1, 0, 0);
				sphere.DrawTexSphere(0.5f, 16, 16,pelvisTexture);
				GL11.glRotatef(-90, 1, 0, 0);
				 
			} catch (IOException e) {
				e.printStackTrace();
			} //draw the sphere standing for the pelvis
			 
			 
			 //chest
			 //configure the pen (color & material)
			 GL11.glColor3f(green[0], green[1], green[2]);
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
//		            	 sphere.DrawSphere(0.5f, 32, 32);//draw the sphere standing for the head
		            	 try {
		            		 GL11.glRotatef(60, 0.0f, 0.0f, 1.0f);
							sphere.DrawTexSphere(0.5f, 32, 32, faceTexture);
						} catch (IOException e2) {
							// TODO Auto-generated catch block
							e2.printStackTrace();
						}
		            	 
		            	 
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
		                     if(LimbRotation>0) {
		                    	 GL11.glRotatef(-LimbRotation, 0, 0, 1);
		                     }
		                     cylinder.DrawCylinder(0.15f,0.75f,32);//draw the cylinder standing for the left arm
		                     

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
		            		 if(LimbRotation<0) {
		                    	 GL11.glRotatef(-LimbRotation, 0, 0, 1);
		                     }
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
		         //configure the pen (color & material)
		       	 GL11.glColor3f(blue[0], blue[1], blue[2]);
		       	 GL11.glMaterial( GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(blue));
		       	 GL11.glPushMatrix(); 
		       	 {
		       		 GL11.glTranslatef(-0.5f,-0.5f,0.0f);//translate the coordinate system along (-0.5f,-0.2f)
		       		 sphere.DrawSphere(0.25f, 32, 32); //draw the sphere standing for hip 
		       		 
		       		 //left high leg
		       		 //configure the pen (color & material)
		           	 GL11.glColor3f(orange[0], orange[1], orange[2]);
		           	 GL11.glMaterial( GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(orange));
		           	 GL11.glPushMatrix(); 
		           	 {
		                    GL11.glTranslatef(0.0f,0.0f,0.0f);//doesn't translate the coordinate system
		                    GL11.glRotatef(0.0f,0.0f,0.0f,0.0f);//doesn't rotate the cylinder 
		                    
		                    GL11.glRotatef((-LimbRotation)+90,1.0f,0.0f,0.0f); //rotate the cylinder to simulate the action of running legs
		                    cylinder.DrawCylinder(0.15f,0.7f,32);//draw the cylinder standing for high legs

		                    // left knee
		                    //configure the pen (color & material)
		                    GL11.glColor3f(blue[0], blue[1], blue[2]);
		                    GL11.glMaterial( GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(blue));
		                    GL11.glPushMatrix(); 
		                    {
		                    	GL11.glTranslatef(0.0f,0.0f,0.75f);//translate the coordinate along (0.75z)
		                        GL11.glRotatef(0.0f,0.0f,0.0f,0.0f); //doesn't rotate the coordinate system
		                        sphere.DrawSphere(0.25f, 32, 32); //draw the sphere standing for the knee

		                        //left low leg
		                        //configure the pen (color & material)
		                   	 	GL11.glColor3f(orange[0], orange[1], orange[2]);
		                   	 	GL11.glMaterial( GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(orange));
		                        GL11.glPushMatrix(); 
		                        {
		                        	if(LimbRotation>0) {
		                        		//if the left low leg is behind the body, then we need to rotate it up
			       						GL11.glRotatef((-LimbRotation),1.0f,0.0f,0.0f);
			       					 }
		                        	GL11.glTranslatef(0.0f,0.0f,0.0f);//doesn't translate the cylinder
		                            cylinder.DrawCylinder(0.15f,0.7f,32);//draw the cylinder standing for left low leg

		                            //left foot
		                            //configure the pen (color & material)
		                       	 	GL11.glColor3f(blue[0], blue[1], blue[2]);
		                       	 	GL11.glMaterial( GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(blue));
		                            GL11.glPushMatrix(); 
		                            {
		                                GL11.glTranslatef(0.0f,0.0f,0.75f);//translate the coordinate system (0.75z)
		                                sphere.DrawSphere(0.3f, 32, 32);  //draw the sphere standing for the left foot
		                            } 
		                            GL11.glPopMatrix();//pop matrix --> back to low leg
		                        } 
		                        GL11.glPopMatrix();//pop matrix --> back to knee
		                    } 
		                    GL11.glPopMatrix();//pop matrix --> back to high leg
		           	 } 
		           	 GL11.glPopMatrix();//pop matrix --> back to left hip
		       	 } 
		       	 GL11.glPopMatrix();//pop matrix --> back to pelvis
		       	 
		       	 //right hip
		       	 //configure the pen (color & material)
		       	 GL11.glColor3f(blue[0], blue[1], blue[2]);
		       	 GL11.glMaterial( GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(blue));
		       	 GL11.glPushMatrix(); 
		       	 {
		       		 
		       		 GL11.glTranslatef(0.5f,-0.5f,0.0f);//translate the coordinate along (0.5,-0.2,0.0)
		       		 sphere.DrawSphere(0.25f, 32, 32);//draw the sphere standing for the right hip
		       		 
		       		 //right high leg
		       		 //configure the pen (color & material)
		       		 GL11.glColor3f(orange[0], orange[1], orange[2]);
		       		 GL11.glMaterial( GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(orange));
		       		 GL11.glPushMatrix(); 
		       		 {
		       			 GL11.glTranslatef(0.0f,0.0f,0.0f);//doesn't translate the coordinate system
		       			 GL11.glRotatef(0.0f,0.0f,0.0f,0.0f);//doesn't rotate the coordinate system
		       			 GL11.glRotatef((LimbRotation)+90,1.0f,0.0f,0.0f); //rotate the coordinate system (LimbRotation+90) degree along (1.0,0.0,0.0) w
		       			 cylinder.DrawCylinder(0.15f,0.7f,32);//draw the cylinder standing for right high pen 
				         
		       			 //right knee
		       			 //configure the pen (color & material)
		       			 GL11.glColor3f(blue[0], blue[1], blue[2]);
		       			 GL11.glMaterial( GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(blue));
		       			 GL11.glPushMatrix(); 
		       			 {
		       				 GL11.glTranslatef(0.0f,0.0f,0.75f);//translate the coordinate (0.75z)
		       				 GL11.glRotatef(0.0f,0.0f,0.0f,0.0f); 
		       				 sphere.DrawSphere(0.25f, 32, 32); //draw the sphere standing for knee
		       				 
		       				 //right low leg
		       				 //configure the pen (color & material)
		       				 GL11.glColor3f(orange[0], orange[1], orange[2]);
		       				 GL11.glMaterial( GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(orange));
		       				 GL11.glPushMatrix(); 
		       				 {
		       					 GL11.glTranslatef(0.0f,0.0f,0.0f);//doesn't rotate the coordinate system
		       					 if(LimbRotation<0) {
		       						 //if the right leg is behind the body
		       						 GL11.glRotatef((LimbRotation),1.0f,0.0f,0.0f);//rotate the right leg up
		       					 }
		       					 cylinder.DrawCylinder(0.15f,0.7f,32);//draw the cylinder standing for leg
		       					 
		       					 //right foot
		       					 //configure the pen (color & material)
		       					 GL11.glColor3f(blue[0], blue[1], blue[2]);
		       					 GL11.glMaterial( GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(blue));
		       					 GL11.glPushMatrix(); 
		       					 {
		       						 GL11.glTranslatef(0.0f,0.0f,0.75f);//translate the coordinate (0.75z)
		       						 sphere.DrawSphere(0.3f, 32, 32); //draw the sphere standing for the foot 
		       					 }
		       					 GL11.glPopMatrix();//pop matrix --> back to low leg
		       				 }
		       				 GL11.glPopMatrix();//pop matrix --> back to knee
		       			 }
		       			 GL11.glPopMatrix();//pop matrix --> back to high leg 
		       		 }
		       		 GL11.glPopMatrix();//pop matrix --> back to hip 
		       	 }
		       	 GL11.glPopMatrix();//pop matrix --> back to pelvis 
			 } 
			 GL11.glPopMatrix();//pop matrix --> empty stack !
		 } 
		 //finish drawing human being
	}

	public void DrawHumanSitOnBed(float delta,boolean GoodAnimation, Texture faceTexture, Texture bodyTexture, Texture pelvisTexture,float SittingDistance) {
		//the angle used in the the method glRotatef()
		float theta = (float) (delta * 25 * Math.PI);
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
			 if(thetaDeg<90) {
				 GL11.glRotatef(thetaDeg, 0, 1, 0);
			 }else{
				 GL11.glRotatef(90, 0, 1, 0); 
			 }
			 
			 GL11.glTranslatef(0.0f,0.5f,0.0f);//translate the coordinate to +0.5y 
			 try {
				GL11.glRotatef(90, 1, 0, 0);
				if(thetaDeg<90) {
					GL11.glTranslatef(0f, 0f, SittingDistance);
				 }else{
					 GL11.glTranslatef(0f, 0f, 1f);
				 }
				sphere.DrawTexSphere(0.5f, 16, 16,pelvisTexture);
				GL11.glRotatef(-90, 1, 0, 0);
				 
			} catch (IOException e) {
				e.printStackTrace();
			} //draw the sphere standing for the pelvis
			 
			 
			 //chest
			 //configure the pen (color & material)
			 GL11.glColor3f(green[0], green[1], green[2]);
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
//		            	 sphere.DrawSphere(0.5f, 32, 32);//draw the sphere standing for the head
		            	 try {
		            		 GL11.glRotatef(60, 0.0f, 0.0f, 1.0f);
							sphere.DrawTexSphere(0.5f, 32, 32, faceTexture);
						} catch (IOException e2) {
							// TODO Auto-generated catch block
							e2.printStackTrace();
						}
		            	 
		            	 
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
		                     if(LimbRotation>0) {
		                    	 GL11.glRotatef(-LimbRotation, 0, 0, 1);
		                     }
		                     cylinder.DrawCylinder(0.15f,0.75f,32);//draw the cylinder standing for the left arm
		                     

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
		            		 if(LimbRotation<0) {
		                    	 GL11.glRotatef(-LimbRotation, 0, 0, 1);
		                     }
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
		         //configure the pen (color & material)
		       	 GL11.glColor3f(blue[0], blue[1], blue[2]);
		       	 GL11.glMaterial( GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(blue));
		       	 GL11.glPushMatrix(); 
		       	 {
		       		 GL11.glTranslatef(-0.5f,-0.5f,0.0f);//translate the coordinate system along (-0.5f,-0.2f)
		       		 sphere.DrawSphere(0.25f, 32, 32); //draw the sphere standing for hip 
		       		 
		       		 //left high leg
		       		 //configure the pen (color & material)
		           	 GL11.glColor3f(orange[0], orange[1], orange[2]);
		           	 GL11.glMaterial( GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(orange));
		           	 GL11.glPushMatrix(); 
		           	 {
		                    /**!!!**/
		           		 	GL11.glTranslatef(0.0f,0.0f,0.0f);//doesn't translate the coordinate system
		                    GL11.glRotatef(0.0f,0.0f,0.0f,0.0f);//doesn't rotate the cylinder 
		                    if(thetaDeg<90) {
		                    	GL11.glRotatef(-thetaDeg, -1, 0, 0);
		                    }else{
		                    	GL11.glRotatef(-90, -1, 0, 0);
		                    }
		                    GL11.glRotatef((-LimbRotation)+90,1.0f,0.0f,0.0f); //rotate the cylinder to simulate the action of running legs
		                    cylinder.DrawCylinder(0.15f,0.7f,32);//draw the cylinder standing for high legs

		                    // left knee
		                    //configure the pen (color & material)
		                    GL11.glColor3f(blue[0], blue[1], blue[2]);
		                    GL11.glMaterial( GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(blue));
		                    GL11.glPushMatrix(); 
		                    {
		                    	GL11.glTranslatef(0.0f,0.0f,0.75f);//translate the coordinate along (0.75z)
		                        GL11.glRotatef(0.0f,0.0f,0.0f,0.0f); //doesn't rotate the coordinate system
		                        sphere.DrawSphere(0.25f, 32, 32); //draw the sphere standing for the knee

		                        //left low leg
		                        //configure the pen (color & material)
		                   	 	GL11.glColor3f(orange[0], orange[1], orange[2]);
		                   	 	GL11.glMaterial( GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(orange));
		                        GL11.glPushMatrix(); 
		                        {
		                        	if(LimbRotation>0) {
		                        		//if the left low leg is behind the body, then we need to rotate it up
			       						GL11.glRotatef((-LimbRotation),1.0f,0.0f,0.0f);
			       					 }
		                        	if(thetaDeg<90) {
		                        		GL11.glRotatef(thetaDeg, -1, 0, 0);
		                        	}else{
		                        		GL11.glRotatef(90, -1, 0, 0);
		                        	}
		                        	GL11.glTranslatef(0.0f,0.0f,0.0f);//doesn't translate the cylinder
		                            cylinder.DrawCylinder(0.15f,0.7f,32);//draw the cylinder standing for left low leg

		                            //left foot
		                            //configure the pen (color & material)
		                       	 	GL11.glColor3f(blue[0], blue[1], blue[2]);
		                       	 	GL11.glMaterial( GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(blue));
		                            GL11.glPushMatrix(); 
		                            {
		                                GL11.glTranslatef(0.0f,0.0f,0.75f);//translate the coordinate system (0.75z)
		                                sphere.DrawSphere(0.3f, 32, 32);  //draw the sphere standing for the left foot
		                            } 
		                            GL11.glPopMatrix();//pop matrix --> back to low leg
		                        } 
		                        GL11.glPopMatrix();//pop matrix --> back to knee
		                    } 
		                    GL11.glPopMatrix();//pop matrix --> back to high leg
		           	 } 
		           	 GL11.glPopMatrix();//pop matrix --> back to left hip
		       	 } 
		       	 GL11.glPopMatrix();//pop matrix --> back to pelvis
		       	 
		       	 //right hip
		       	 //configure the pen (color & material)
		       	 GL11.glColor3f(blue[0], blue[1], blue[2]);
		       	 GL11.glMaterial( GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(blue));
		       	 GL11.glPushMatrix(); 
		       	 {
		       		 
		       		 GL11.glTranslatef(0.5f,-0.5f,0.0f);//translate the coordinate along (0.5,-0.2,0.0)
		       		 sphere.DrawSphere(0.25f, 32, 32);//draw the sphere standing for the right hip
		       		 
		       		 //right high leg
		       		 //configure the pen (color & material)
		       		 GL11.glColor3f(orange[0], orange[1], orange[2]);
		       		 GL11.glMaterial( GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(orange));
		       		 GL11.glPushMatrix(); 
		       		 {
		       			 GL11.glTranslatef(0.0f,0.0f,0.0f);//doesn't translate the coordinate system
		       			 GL11.glRotatef(0.0f,0.0f,0.0f,0.0f);//doesn't rotate the coordinate system
		       			 if(thetaDeg<90) {
		       				GL11.glRotatef(thetaDeg, 1, 0, 0);
		       			 }else{
		       				GL11.glRotatef(90, 1, 0, 0); 
		       			 }
		       			 GL11.glRotatef((LimbRotation)+90,1.0f,0.0f,0.0f); //rotate the coordinate system (LimbRotation+90) degree along (1.0,0.0,0.0) w
		       			 
		       			 
		       			 cylinder.DrawCylinder(0.15f,0.7f,32);//draw the cylinder standing for right high pen 
				         
		       			 //right knee
		       			 //configure the pen (color & material)
		       			 GL11.glColor3f(blue[0], blue[1], blue[2]);
		       			 GL11.glMaterial( GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(blue));
		       			 GL11.glPushMatrix(); 
		       			 {
		       				 GL11.glTranslatef(0.0f,0.0f,0.75f);//translate the coordinate (0.75z)
		       				 GL11.glRotatef(0.0f,0.0f,0.0f,0.0f); 
		       				 sphere.DrawSphere(0.25f, 32, 32); //draw the sphere standing for knee
		       				 
		       				 //right low leg
		       				 //configure the pen (color & material)
		       				 GL11.glColor3f(orange[0], orange[1], orange[2]);
		       				 GL11.glMaterial( GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(orange));
		       				 GL11.glPushMatrix(); 
		       				 {
		       					 GL11.glTranslatef(0.0f,0.0f,0.0f);//doesn't rotate the coordinate system
		       					 if(thetaDeg<90) {
		 		       				GL11.glRotatef(thetaDeg, -1, 0, 0);
		 		       			 }else{
		 		       				GL11.glRotatef(90, -1, 0, 0); 
		 		       			 }
		       					 if(LimbRotation<0) {
		       						 //if the right leg is behind the body
		       						 GL11.glRotatef((LimbRotation),1.0f,0.0f,0.0f);//rotate the right leg up
		       					 }
		       					 cylinder.DrawCylinder(0.15f,0.7f,32);//draw the cylinder standing for leg
		       					 
		       					 //right foot
		       					 //configure the pen (color & material)
		       					 GL11.glColor3f(blue[0], blue[1], blue[2]);
		       					 GL11.glMaterial( GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(blue));
		       					 GL11.glPushMatrix(); 
		       					 {
		       						 GL11.glTranslatef(0.0f,0.0f,0.75f);//translate the coordinate (0.75z)
		       						 sphere.DrawSphere(0.3f, 32, 32); //draw the sphere standing for the foot 
		       					 }
		       					 GL11.glPopMatrix();//pop matrix --> back to low leg
		       				 }
		       				 GL11.glPopMatrix();//pop matrix --> back to knee
		       			 }
		       			 GL11.glPopMatrix();//pop matrix --> back to high leg 
		       		 }
		       		 GL11.glPopMatrix();//pop matrix --> back to hip 
		       	 }
		       	 GL11.glPopMatrix();//pop matrix --> back to pelvis 
			 } 
			 GL11.glPopMatrix();//pop matrix --> empty stack !
		 } 
		 //finish drawing human being		
	}

}
 

	 