package objects3D;

import org.lwjgl.opengl.GL11;
import GraphicsObjects.Point4f;
import GraphicsObjects.Vector4f;
import java.math.*;

public class Cylinder {

	
	public Cylinder() {
		
	}
	
	public void DrawCylinder(float radius, float height, int nSegments ) 
	{
		for(float i=0.0f;i<nSegments;i=i+1.0f){

			float angle= (float) (Math.PI*i*2.0/nSegments);
			float nextAngle=(float) (Math.PI*(i+1)*2.0/nSegments);

			float x1= (float) (radius*Math.sin(angle));
			float y1= (float) (radius*Math.cos(angle));

			float x2= (float) (radius*Math.sin(nextAngle));
			float y2= (float) (radius*Math.cos(nextAngle));

			//draw the upper triangle
			drawTriangle(new Point4f[]{
					new Point4f(x1,y1,0.0f,0.0f),
					new Point4f(x2,y2,height,0.0f),
					new Point4f(x1,y1,height,0.0f),
			});

			//draw the downside triangle
			drawTriangle(new Point4f[]{
					new Point4f(x1,y1,0.0f,0.0f),
					new Point4f(x2,y2,0.0f,0.0f),
					new Point4f(x2,y2,height,0.0f),
			});

			//draw one triangle for the top of cylinder
			drawTriangle(new Point4f[]{
					new Point4f(x1,y1,0.0f,0.0f),
					new Point4f(x2,y2,0.0f,0.0f),
					new Point4f(0.0f,0.0f,0.0f,0.0f)
			});

			//draw one triangle for the bottom of cylinder
			drawTriangle(new Point4f[]{
					new Point4f(x1,y1,height,0.0f),
					new Point4f(x2,y2,height,0.0f),
					new Point4f(0.0f,0.0f,height,0.0f)
			});



		}
	}
	
	public void drawTriangle(Point4f[] point4fs){
		//draw the triangle
		GL11.glBegin(GL11.GL_TRIANGLES);
		{
			Point4f first_point=point4fs[0];
			Point4f second_point=point4fs[1];
			Point4f third_point=point4fs[2];

			Vector4f v1=first_point.MinusPoint(second_point);
			Vector4f v2=first_point.MinusPoint(third_point);
			Vector4f normal=v1.cross(v2).Normal();
			//calculate the normal vector
			GL11.glNormal3f(normal.x,normal.y,normal.z);
			//use the normal vector to determine the shade effect

			Vector4f v3=second_point.MinusPoint(first_point);
			Vector4f v4=second_point.MinusPoint(third_point);
			Vector4f normal2=v3.cross(v4).Normal();

			GL11.glNormal3f(normal2.x,normal2.y,normal2.z);

			Vector4f v5=third_point.MinusPoint(first_point);
			Vector4f v6=third_point.MinusPoint(second_point);
			Vector4f normal3=v5.cross(v6).Normal();
			GL11.glNormal3f(normal3.x,normal3.y,normal3.z);

			GL11.glVertex3f(first_point.x,first_point.y,first_point.z);
			GL11.glVertex3f(second_point.x,second_point.y,second_point.z);
			GL11.glVertex3f(third_point.x,third_point.y,third_point.z);
		}
		GL11.glEnd();
	}
	
	
}
