package objects3D;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.opengl.Texture;

import GraphicsObjects.Point4f;
import GraphicsObjects.Vector4f;

public class TexCube {
	static float red[] = { 1.0f, 0.0f, 0.0f, 1.0f };
	static float green[] = { 0.0f, 1.0f, 0.0f, 1.0f };
	static float blue[] = { 0.0f, 0.0f, 1.0f, 1.0f };
	static float white[] = { 1.0f, 1.0f, 1.0f, 1.0f };
	
	public TexCube() {
		
	}
	
	// Implement using notes  and looking at TexSphere
	public void DrawTexCube(Texture texture)  {
		Point4f[] vertices={
				new Point4f(1.0f,1.0f,1.0f,0.0f),//0
				new Point4f(1.0f,1.0f,-1.0f,0.0f),//1
				new Point4f(1.0f,-1.0f,1.0f,0.0f),//2
				new Point4f(1.0f,-1.0f,-1.0f,0.0f),//3
				new Point4f(-1.0f,1.0f,1.0f,0.0f),//4
				new Point4f(-1.0f,1.0f,-1.0f,0.0f),//5
				new Point4f(-1.0f,-1.0f,1.0f,0.0f),//6
				new Point4f(-1.0f,-1.0f,-1.0f,0.0f),//7
		};

		//information of faces
		/*
		* Mention:
		* 	! each face contains two triangles, then put these two triangles together
		* */
		int[][] faces={
				{0,4,1},
				{5,4,1},//Left Face
				{2,6,3},
				{7,6,3},//Right Face
				{6,4,2},
				{0,4,2},//Up Face
				{7,5,3},
				{1,5,3},//Bottom Face
				{2,0,3},
				{1,0,3},//Front Face
				{6,4,7},
				{5,4,7},//Back Face
		};

		//begin to draw
		GL11.glBegin(GL11.GL_TRIANGLES);
		{
			for (int face = 0; face < faces.length; face++) {
				// per face
				Vector4f v = vertices[faces[face][1]].MinusPoint(vertices[faces[face][0]]);
				Vector4f w = vertices[faces[face][2]].MinusPoint(vertices[faces[face][0]]);
				Vector4f normal = v.cross(w).Normal();
				//calculate the normal vector of one triangle plane
				GL11.glNormal3f(normal.x, normal.y, normal.z);
				//calculate the shade effect

				//draw one triangle of the face
				GL11.glTexCoord2f(0, 0);
				GL11.glVertex3f(vertices[faces[face][0]].x, vertices[faces[face][0]].y, vertices[faces[face][0]].z);
				GL11.glTexCoord2f(1, 0);
				GL11.glVertex3f(vertices[faces[face][1]].x, vertices[faces[face][1]].y, vertices[faces[face][1]].z);
				GL11.glTexCoord2f(0, 1);
				GL11.glVertex3f(vertices[faces[face][2]].x, vertices[faces[face][2]].y, vertices[faces[face][2]].z);
				
				//move to next face
				face++;

				//draw another triangle
				/*
				* ! mention:
				* 	OpenGL is a state machine, it means that we don't need to calculate the normal vector again,
				* 	since two triangles of one face is places together in the array, and we have calculated the normal
				* 	vector of first triangles already, the normal vector information has been preserved in OpenGL
				* */
				GL11.glTexCoord2f(1, 1);
				GL11.glVertex3f(vertices[faces[face][0]].x, vertices[faces[face][0]].y, vertices[faces[face][0]].z);
				GL11.glTexCoord2f(1, 0);
				GL11.glVertex3f(vertices[faces[face][1]].x, vertices[faces[face][1]].y, vertices[faces[face][1]].z);
				GL11.glTexCoord2f(0, 1);
				GL11.glVertex3f(vertices[faces[face][2]].x, vertices[faces[face][2]].y, vertices[faces[face][2]].z);

			} // per face

		}
		GL11.glEnd();
	 
		
		

	}
	
	
	
}
 
