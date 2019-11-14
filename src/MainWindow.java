
import java.io.IOException;
import java.nio.FloatBuffer;

import org.lwjgl.BufferUtils;
import org.lwjgl.LWJGLException;
import org.lwjgl.Sys;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;

import org.newdawn.slick.Color;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

import GraphicsObjects.Arcball;
import GraphicsObjects.Utils;
import objects3D.TexCube;
import objects3D.TexSphere;
import objects3D.Bed;
import objects3D.Chair;
import objects3D.Dog;
import objects3D.Grid;
import objects3D.House;
import objects3D.Human;
import objects3D.Photo;
import objects3D.RunningMachine;
import objects3D.Table;
import objects3D.Television; 

//Main windows class controls and creates the 3D virtual world , please do not change this class but edit the other classes to complete the assignment. 
// Main window is built upon the standard Helloworld LWJGL class which I have heavily modified to use as your standard openGL environment. 
// Do not touch this class, I will be making a version of it for your 3rd Assignment 

/*
 * �Ҷ�MainWindow�����������⣺
 *start��������
 *	-->�������ڣ�ͨ��Display��������Ĵ��ڣ����յ�Display.isCloseRequested()��Ϣ֮��ر�ҳ��
 *	-->��ȡϵͳ�ĵ�ǰʱ�䣬�洢��ȫ�ֱ���StartTime��
 *	-->����initGL()������ʼ����Ϸ����
 *		-->����Projectionģʽ
 *			-->����changeOrth()����
 *			-->����ArcBall
 *		-->����ModelViewģʽ
 *			-->����һϵ�е�����
 *			-->ͨ��init()������������
 *	-->����lastFPSΪϵͳ��ǰʱ��
 *	-->����δ�ر�ҳ���ʱ��ѭ�����²���
 *		-->ͨ��getDelta()������ȡѭ��ʱ������һ֡��ʱ���ֵ�������Ը���lastFrame��ֵ
 *		-->ͨ��update()�������û�������м���
 *			-->ͨ�����û�����ļ���ʵ�ֶԱ����ĸ���
 *			-->ʹ��updateFPS()����lastFPS
 *		-->ͨ��renderGL()��������Ʒ���л���(�������Ǹ���)
 *			-->����changeOrth()����
 *			-->��push-pop�л�������
 *		-->ͨ��Display.update()����ҳ�棬ʵ�ָ��µ���Ʒ��ҳ���ϵ��Ի���(����)
 * */
public class MainWindow {
	
	private boolean MouseOnepressed = true;
	private boolean dragMode=false;
	private boolean BadAnimation = true;
	private boolean Earth= false;
	/** position of pointer */
	float x = 400, y = 300;
	/** angle of rotation */
	float rotation = 0;
	/** time at last frame */
	long lastFrame;
	/** frames per second */
	int fps;
	/** last fps time */
	long lastFPS;
	
	long  myDelta =0 ; //to use for animation
	float Alpha =0 ; //to use for animation
	long StartTime; // beginAnimiation 
	
	Arcball MyArcball= new Arcball();
	
	boolean DRAWGRID =false;
	boolean waitForKeyrelease= true;
	/** Mouse movement */
	int LastMouseX = 419;
	int LastMouseY= 719;
	
	 float pullX = 0.0f; // arc ball  X cord. 
	 float pullY = 0.0f; // arc ball  Y cord. 
	 
	 float mouseX=0.0f;
	 float mouseY=0.0f;

	 
	int OrthoNumber = 3670;
			//3670;
			//6340
	int update=0;
	int last_update=0;
	float posn_x;
	float posn_y;
	float theta;
	float delta;
	
	/*����Configuration��
	 * �ݵأ�������:
	 * ���䳤��
	 * �����
	 * ����ߣ�
	 * --->��שǽ��
	 * --->��෽שǽ��
	 * --->�Ҳ෽שǽ��
	 * ��������λ�ã�
	 * 
	 * ���񳤣�
	 * �����
	 * 
	 * ���Ӹ߶ȣ�400
	 * ���ӳ�/��1000
	 * ����λ�ã�
	 * 
	 * ���Ӹ߶ȣ�
	 * ���ӳ�/��
	 * ����λ�ã�
	 * 
	 * 
	 * ���߶ȣ�
	 * ����/��
	 * ��λ�ã�
	 * 
	 * 
	 * */
	
	float human_posn_x=1500;
	float human_posn_y=-420;
	float human_posn_z=4200;
	
	float human_sit_distance=0f;
	float human_sit_step=0.001f;
	
	boolean riverBoolean=true;
	
	
	// using this for screen size, making a window of 1200 x 800 so aspect ratio 3:2 
	// do not change this for assignment 3 but you can change everything for your project 
	
	// basic colors
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
	
	int phase1=0;
	int phase2=0;
	int phase4=0;
	Texture texture;
	Texture faceTexture;
	Texture bodyTexture;
	Texture pelvisTexture;
	Texture redWallTexture;
	Texture floorTexture;
	Texture grassTexture;
	Texture skyTexture;
	Texture drawings1;
	Texture drawings2;
	Texture drawings3;
	Texture bedTexture;
	Texture dogHeadTexture;
	Texture dogBodyTexture;
	Texture dogPelvisTexture;
	Texture riverpast;
	Texture riverlast;
	Texture animation1;
	Texture animation2;
	Texture animation3;
	Texture animation4;
	Texture panel;
	Texture machineBottom;
	// static GLfloat light_position[] = {0.0, 100.0, 100.0, 0.0};
	//support method to aid in converting a java float array into a Floatbuffer which is faster for the opengl layer to process 
	

	public void start() {
		//��Ϸ��ʼ�����
		StartTime = getTime();//��ȡʱ��
		try {
			Display.setDisplayMode(new DisplayMode(1200, 800));
			Display.create();
			//չʾ����������Ϸ����
		} catch (LWJGLException e) {
			e.printStackTrace();
			System.exit(0);
		}

		initGL(); 
		//��ʼ����Ϸ����
		
		//getDelta(); // call once before loop to initialise lastFrame
		
		lastFPS = getTime(); // call before loop to initialise fps time
		while (!Display.isCloseRequested()) {
			int delta = getDelta();
			update(delta);
			renderGL();
			Display.update();
			Display.sync(120); // cap fps to 120fps
		}

		Display.destroy();
	}
	
	//������ʵ�ֶ�ҳ���ˢ�¹���
	//����ʵ�����Ƕ�ʵ�֡����������ܣ�Ҳ��ͨ����������������϶���������ز�����
	//ʹ����renderGL(���ݲ����Ĳ�ͬ��ѡ����Ⱦ�ķ���)
	public void update(int delta) {
		//rotate quad
		//rotation += 0.01f * delta;
		//���µĲ������Ƕ����ļ���
		int MouseX= Mouse.getX();
		int MouseY= Mouse.getY();
		int WheelPostion = Mouse.getDWheel();
		boolean MouseButonPressed= Mouse.isButtonDown(0);
		
		if(MouseButonPressed && !MouseOnepressed){
			MouseOnepressed =true;
			MyArcball.startBall( MouseX, MouseY, 1200, 800);
			dragMode=true;
		}else if(!MouseButonPressed){
			MouseOnepressed =false;
			dragMode=false;
		}
		if(dragMode){
			MyArcball.updateBall( MouseX  , MouseY  , 1200, 800);
		}
		if(WheelPostion>0){
			OrthoNumber += 10;
		}
		if(WheelPostion<0){
			OrthoNumber -= 10;
			if(OrthoNumber<610){
				OrthoNumber=610;
			}
		}
		  
		/** rest key is R*/
		if (Keyboard.isKeyDown(Keyboard.KEY_R))
			  MyArcball.reset();
		  
		/* bad animation can be turn on or off using A key)*/ 
		if (Keyboard.isKeyDown(Keyboard.KEY_A))
			BadAnimation=!BadAnimation;
		if (Keyboard.isKeyDown(Keyboard.KEY_D))
			x += 0.35f * delta;

		if (Keyboard.isKeyDown(Keyboard.KEY_W))
			y += 0.35f * delta;
		if (Keyboard.isKeyDown(Keyboard.KEY_S))
			y -= 0.35f * delta;

		if (Keyboard.isKeyDown(Keyboard.KEY_Q))
			rotation += 0.35f * delta;
		if (Keyboard.isKeyDown(Keyboard.KEY_E)){
			Earth=!Earth;
		} 
		
		// check done to see if key is released 
		if(waitForKeyrelease){
			if (Keyboard.isKeyDown(Keyboard.KEY_G)){
				DRAWGRID = !DRAWGRID;
				Keyboard.next();
				if(Keyboard.isKeyDown(Keyboard.KEY_G)){
					waitForKeyrelease=true;
				}else{
					waitForKeyrelease=false;
				}
			}
		 }
		 
		 /** to check if key is released */
		if(Keyboard.isKeyDown(Keyboard.KEY_G)==false){
			waitForKeyrelease=true;
		}else{
			waitForKeyrelease=false;	
		}
		 
		// keep quad on the screen
		if (x < 0)
			x = 0;
		if (x > 1200)
			x = 1200;
		if (y < 0)
			y = 0;
		if (y > 800)
			y = 800;

		updateFPS(); // update FPS Counter
		update++;
		
		LastMouseX= MouseX;
		LastMouseY= MouseY ;
	}

	/**
	 * Calculate how many milliseconds have passed since last frame.
	 * ���������֮֡���ʱ��ͬʱ����lastFrame������ֵΪ���øú�����ʱ��
	 * @return milliseconds passed since last frame
	 */
	public int getDelta() {
		long time = getTime();
		int delta = (int) (time - lastFrame);
		lastFrame = time;
		return delta;
	}

	/**
	 * Get the accurate system time
	 * 
	 * @return The system time in milliseconds
	 */
	//�ú������ص���ϵͳ�ĵ�ǰʱ��
	public long getTime() {
		return (Sys.getTime() * 1000) / Sys.getTimerResolution();
	}

	/**
	 * Calculate the FPS and set it in the title bar
	 */
	public void updateFPS() {
		if (getTime() - lastFPS > 1000) {
			int humanLoop=(int) delta;
			int humanPhase=humanLoop%7;
			if(humanPhase==0) {
				Display.setTitle("Daily Life: John is waking up and stretching himslef");
			}
			if(humanPhase==1||humanPhase==2) {
				Display.setTitle("Daily Life: John is walking to the table");
			}
			if(humanPhase==3) {
				Display.setTitle("Daily Life: John is enjoying Cartoon");
			}
			if(humanPhase==4) {
				Display.setTitle("Daily Life: John is ready for excerise");
			}
			if(humanPhase==5||humanPhase==6) {
				Display.setTitle("Daily Life:John is running on the running machine");
			}
			//Display.setTitle("FPS: " + fps+"---"+"---"+this.OrthoNumber);
			fps = 0;
			lastFPS += 1000;
		}
		fps++;
	}

	public void initGL() {
		GL11.glMatrixMode(GL11.GL_PROJECTION);
		//ͶӰ��������ͶӰ��һ��ƽ����
		GL11.glLoadIdentity();
		//��ǰ��������Ϊ��λ����
		
		//���øú���
		changeOrth();
		
		//����ArcBall
		MyArcball.startBall(0, 0, 1200,800);
		
		//��ģ���Ӿ��Ĳ�������������������һ����ģ��Ϊ��������Ӧ�����������ò���
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
		
		
		FloatBuffer lightPos = BufferUtils.createFloatBuffer(4);
		lightPos.put(10000f).put(1000f).put(1000).put(0).flip();

		FloatBuffer lightPos2 = BufferUtils.createFloatBuffer(4);
		lightPos2.put(0f).put(1000f).put(0).put(-1000f).flip();

		FloatBuffer lightPos3 = BufferUtils.createFloatBuffer(4);
		lightPos3.put(-10000f).put(1000f).put(1000).put(0).flip();

		FloatBuffer lightPos4 = BufferUtils.createFloatBuffer(4);
		lightPos4.put(1000f).put(1000f).put(1000f).put(0).flip();
		//���Ͻ��ǶԹ��ղ������޶�
		
		/*
		 * ��glLight������˵�������
		 * glLightf(GLenum light,GLenum pname,GLfloat param)
		 * 1.light��ָ�����ա����յ���Ŀȡ����ʵ�֣������ٿ���֧��8�����ա��ֱ�ΪGL_LIGHT1��GL_LIGHT8
		 * 2.pname��ָ��light�Ĺ��ղ���������ѡ���ֵ��
		 * 	GL_AMBIENT 
		 * 	GL_DIFFUSE 
		 * 	GL_SPECULAR 
		 * 	GL_POSITION:ָ����Դλ��
		 * 	GL_SPOT_DIRECTION
		 * 	GL_SPOT_EXPONENT 
		 * 	GL_SPOT_CUTOFF
		 * 	GL_CONSTANT_ATTENUATION
		 * 	GL_LINEAR_ATTENUATION
		 *  GL_QUADRATIC_ATTENUATION
		 * 3.param��ָ����Դlight������ֵָ��
		 * */
		GL11.glLight(GL11.GL_LIGHT0, GL11.GL_POSITION, lightPos); 
		// specify the position of the light
		
		//GL11.glEnable(GL11.GL_LIGHT0); 
		//switch light #0 on 
		//I've setup specific materials so in real light it will look a bit strange 

		GL11.glLight(GL11.GL_LIGHT1, GL11.GL_POSITION, lightPos); 
		// specify the position of the light
		GL11.glEnable(GL11.GL_LIGHT1); // switch light #0 on
		GL11.glLight(GL11.GL_LIGHT1, GL11.GL_DIFFUSE, Utils.ConvertForGL(spot));

		GL11.glLight(GL11.GL_LIGHT2, GL11.GL_POSITION, lightPos3); 
		// specify the position of the light
		GL11.glEnable(GL11.GL_LIGHT2); 
		//switch light #0 on
		GL11.glLight(GL11.GL_LIGHT2, GL11.GL_DIFFUSE, Utils.ConvertForGL(grey));

		GL11.glLight(GL11.GL_LIGHT3, GL11.GL_POSITION, lightPos4); 
		// specify the position of the light
		GL11.glEnable(GL11.GL_LIGHT3); 
		// switch light #0 on
		 GL11.glLight(GL11.GL_LIGHT3, GL11.GL_DIFFUSE, Utils.ConvertForGL(grey));
		 
		//�����￪����һϵ�еĿ���
		
		 //1.�򿪵ƹ⣬ʹ֮ǰ�򿪵�4���ƹ��
		GL11.glEnable(GL11.GL_LIGHTING); 
		
		//2.������֮��OpenGL�ڻ��Ƶ�ʱ��ͻ��飬��ǰ����ǰ���Ƿ��б�����أ�
		//���������ص��������������Ͳ�����ƣ�Ҳ����˵��OpenGL��ֻ������ǰ���һ�㡣
		GL11.glEnable(GL11.GL_DEPTH_TEST); 
		
		//3.glEnable(GL_NORMALIZE)��ʹopenGL�Զ���λ��������
		/*��ΪopenGL�Լ��Ĺ��ռ���Ҫ��������λ�������Բ����µ�λ�������������Ǵ���ġ�
		 *��ˣ�����ʹ��glEnable(GL_NORMALIZE), openGL�ڽ��й��ռ���֮ǰ���Զ���λ��������
		 * */
		GL11.glEnable(GL11.GL_NORMALIZE); 
	 	
		//4.glColorMaterial�������������ڻ�ͼ�Ĺ�����ʵʱ�����ò���
	 	GL11.glEnable(GL11.GL_COLOR_MATERIAL);
	 	
	 	//5.glEnable(GL_BLEND)�������˻�Ͽ���-->�����ĵ�glDepthTest����������෴
	 	GL11.glEnable(GL11.GL_BLEND);
	 	/*Դ��ɫ���������alphaֵ��
	 	 *Ŀ����ɫ����1.0��ȥԴ��ɫ��alphaֵ��
	 	 *����һ����Դ��ɫ��alphaֵԽ�������������ɫ��Դ��ɫ��ռ������Խ��
	 	 *��Ŀ����ɫ��ռ������� С��
	 	 *��������£����ǿ��Լ򵥵Ľ�Դ��ɫ��alphaֵ���Ϊ����͸���ȡ�����Ҳ�ǻ��ʱ��õķ�ʽ��
	 	 * */
	 	GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
	 	
	 	try {
			init();
			//���������������Ϣ
		}catch (IOException e) {
			e.printStackTrace();
		}
          

	}

	//��initGL(),renderGL()�ж��е���
	public void changeOrth() {
		GL11.glMatrixMode(GL11.GL_PROJECTION);
		//ͶӰ��������ͶӰ��һ��ƽ����
		GL11.glLoadIdentity();
		//��ǰ��������Ϊ��λ����
		GL11.glOrtho(1200 -  OrthoNumber , OrthoNumber, (800 - (OrthoNumber  * 0.66f)) , (OrthoNumber * 0.66f), 100000, -100000);
		//����һ������ƽ�е��Ӿ��塣 һ���������岻����Ϊ����Ļ��Զ����������С�ı任�����
		//����������������������һ����������������ôȥ��ȡ��
		
		GL11.glMatrixMode(GL11.GL_MODELVIEW); 
		//��ģ���Ӿ��Ĳ�������������������һ����ģ��Ϊ��������Ӧ�����������ò���
		
		/*
		 * ���µķ����Ҳ�û��Ū��������Ǵ����ǣ�
		 * ����Camera���Ǿ�ֹ������
		 * Ϊ��ʵ�����ƶ���Ч����ʵ�����ƶ����ǳ���
		 * */
		FloatBuffer CurrentMatrix = BufferUtils.createFloatBuffer(16); 
		GL11.glGetFloat(GL11.GL_MODELVIEW_MATRIX, CurrentMatrix);
		MyArcball.getMatrix(CurrentMatrix); 
		GL11.glLoadMatrix(CurrentMatrix);
	}

	//����������У�ʵ�ֵ���������Ƶ�Model����ض���
	//ע�⣺���е�Texture��Ӧ����init()�����м���ʵ�ֵģ��������������ڴ�
	public void renderGL() { 
		changeOrth();
		//����֮ǰ�Ļ���
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT); 
		GL11.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);  
		GL11.glColor3f(0.5f, 0.5f, 1.0f); 
		
		myDelta =   getTime() - StartTime; 
		this.delta =((float) myDelta)/10000;
		//System.out.println(delta);
		theta = (float) (delta * 2 * Math.PI);
		float thetaDeg = delta * 360; 
		
		posn_x = (float) Math.cos(theta-Math.PI/2); 
		posn_y  = (float) Math.sin(theta-Math.PI/2);

		//�����Ƿ�դ��ͼ��
		if(DRAWGRID) {
			GL11.glPushMatrix();
				Grid MyGrid = new Grid();
				GL11.glTranslatef(0, 400, 0); 
				GL11.glScalef(200f, 200f,  200f); 
				MyGrid.DrawGrid();
			GL11.glPopMatrix();
		}
		
		//���Ʊ���ͼ���ݵ�
		GL11.glPushMatrix();{
			TexCube grass=new TexCube();
			GL11.glTranslatef(-200, -1020, 1000);
			GL11.glScalef(10000f, 50f, 10000f);
			GL11.glTexParameteri(GL11.GL_TEXTURE_2D,GL11.GL_TEXTURE_WRAP_T,GL11.GL_CLAMP);
			Color.white.bind();
			grassTexture.bind();
			GL11.glEnable(GL11.GL_TEXTURE_2D);    
		    GL11.glTexParameteri( GL11.GL_TEXTURE_2D,  GL11.GL_TEXTURE_MAG_FILTER,  GL11.GL_NEAREST);
	        GL11.glClearColor(1.0f, 1.0f, 1.0f, 1.0f);   
			grass.DrawTexCube(grassTexture);
			GL11.glDisable(GL11.GL_TEXTURE_2D);
		}
		GL11.glPopMatrix();
		
		//����
		GL11.glPushMatrix();{
			House house=new House();
			GL11.glScalef(3, 1, 3);
			house.DrawHouse(redWallTexture, floorTexture);
			
		}
		GL11.glPopMatrix();
		
		//��Ƭ
		GL11.glPushMatrix();{
			Photo photo=new Photo();
			GL11.glTranslatef(150, 500, 5950);
			GL11.glRotatef(-90, 0, 0, 1);
			photo.drawPhoto(200f,200f,drawings1);
		}
		GL11.glPopMatrix();
		
		GL11.glPushMatrix();{
			Photo photo=new Photo();
			GL11.glTranslatef(150, 500, 5950);
			GL11.glTranslatef(0, -1000, 0);
			GL11.glRotatef(90, 0, 0, 1);
			photo.drawPhoto(200f,200f,drawings2);
		}
		GL11.glPopMatrix();
		
		GL11.glPushMatrix();{
			Photo photo=new Photo();
			GL11.glTranslatef(150, 500, 5950);
			GL11.glTranslatef(700, -100, 0);
			GL11.glRotatef(	180, 0, 0, 1);
			photo.drawPhoto(200f,200f,drawings3);
		}
		GL11.glPopMatrix();
		
		//����
		GL11.glPushMatrix();{ 
			Table table=new Table();
			GL11.glTranslatef(-3000,0,6000);
			GL11.glTranslatef(+600, -600, -800);
			
			table.drawTable(600f,400,60,30);
		}
		GL11.glPopMatrix();
		
		//����
		GL11.glPushMatrix();{
			GL11.glTranslatef(-3000,0,6000);
			GL11.glTranslatef(1900,-700,-750);
			GL11.glScalef(1,2,1.2f);
			Chair chair=new Chair();
			chair.drawChair();
		}
		GL11.glPopMatrix();
		
		//��
		GL11.glPushMatrix();{
			Bed bed=new Bed();
			GL11.glTranslatef(3000, -1000, 6000);
			GL11.glTranslatef(-800, 200,-750);
			GL11.glScalef(2,4,3);
			bed.drawBed(bedTexture);
		}
		GL11.glPopMatrix();
		
		//�ܲ���
		GL11.glPushMatrix();{
			RunningMachine rm=new RunningMachine();
			GL11.glTranslatef(-3000,0,3000);
			GL11.glTranslatef(1500, -900,-750);
			
			rm.DrawRunningMachine(this.panel,this.machineBottom);
			
		}
		GL11.glPopMatrix();
		
		//����
		GL11.glPushMatrix();{
			Television tv=new Television();
			GL11.glTranslatef(-3000,0,6000);
			GL11.glTranslatef(+600, -400, -800);
			GL11.glRotatef(-45, 0, 1, 0);
			GL11.glRotatef(-90, 0, 0, 1);
			int animationLoop=(int) delta;
			float animationPhase=delta-animationLoop;
			if(animationPhase<0.25) {
				tv.DrawTelevision(200, 200, 200, animation1);	
			}
			else if(animationPhase>=0.25&&animationPhase<0.5) {
				tv.DrawTelevision(200, 200, 200, animation2);
			}else if(animationPhase>=0.5&&animationPhase<0.75) {
				tv.DrawTelevision(200, 200, 200, animation3);
			}
			else {
				tv.DrawTelevision(200, 200, 200, animation4);
			}	
		}
		GL11.glPopMatrix();
		
//		GL11.glPushMatrix();{
//			Dog dog=new Dog();
//			GL11.glTranslatef(0, -900,5000); 
//			GL11.glScalef(180f, 180f,180f);
//			GL11.glTranslatef(posn_x*4.0f, 0.0f, posn_y*4.0f);
//			dog.drawDog(true, thetaDeg/30, true, this.dogHeadTexture, this.dogBodyTexture, dogPelvisTexture);
//		}
//		GL11.glPopMatrix();
		
		GL11.glPushMatrix();{
			Human human=new Human();
			float human_step_x=0;
			float human_step_y=0;
			float human_step_z=0;
			
			GL11.glTranslatef(this.human_posn_x, this.human_posn_y, this.human_posn_z);
			int humanLoop=(int) delta;
			int humanPhase=humanLoop%7;
			if(humanPhase==0) {
				this.human_sit_distance=0;
				this.human_posn_x=1500;
				this.human_posn_y=-420;
				this.human_posn_z=3800;
				GL11.glPushMatrix();{
					GL11.glRotatef(90, 1, 0, 0);
					GL11.glScalef(250f, 250f, 250f);
					float tempDelta=delta-7*(humanLoop/7);
					human.DrawWakingHuman(tempDelta,false,faceTexture, bodyTexture, dogPelvisTexture);
				}
				GL11.glPopMatrix();
			}
			if(humanPhase==1) {
				GL11.glPushMatrix();{
					GL11.glTranslatef(350, -200, -200);
					GL11.glScalef(250f, 250f, 250f);
					GL11.glRotatef(90, 0, 1, 0);
					human_step_x=3.2f;
					human.DrawRotateWalkingHuman(delta,true,faceTexture, bodyTexture, dogPelvisTexture);
					this.human_posn_x=this.human_posn_x-human_step_x;
					this.phase1++;
				}
				GL11.glPopMatrix();
			}
			if(humanPhase==2) {
				GL11.glPushMatrix();{
					GL11.glTranslatef(110,-150,-200);
					if(this.phase1!=0) {
						System.out.println(this.phase1);
						if(this.phase1!=980) {
							float dv=(float) (3.6*(this.phase1-980));
							this.human_posn_x=this.human_posn_x+dv;
						}
						this.phase1=0;
					}
					GL11.glScalef(250f, 250f, 250f);
					GL11.glRotatef(180, 0, -1, 0);
					human_step_z=1.5f;
					human.DrawRotateWalkingHuman(delta,true,faceTexture, bodyTexture, dogPelvisTexture);
					this.human_posn_z=this.human_posn_z+human_step_z;
					this.phase2++;
				}
				GL11.glPopMatrix();
			}
			if(humanPhase==3) {
				GL11.glPushMatrix();{
					if(this.phase2!=0) {
						System.out.println(this.phase2);
						if(this.phase2!=1010) {
							float dv=(float) (1.5*(this.phase2-1010));
							this.human_posn_z=this.human_posn_z-dv;
						}
						this.phase2=0;
					}
					GL11.glTranslatef(450,-150, -120);
					GL11.glScalef(250f, 250f, 250f);
					//GL11.glRotatef(90, 0, 1, 0);
					float tempDelta=delta-3-7*(humanLoop/7);
					human.DrawSittingHuman(tempDelta,false,faceTexture, bodyTexture, dogPelvisTexture,this.human_sit_distance);
					this.human_sit_distance=this.human_sit_distance+this.human_sit_step;
				}
				GL11.glPopMatrix();
			}
			if(humanPhase==4) {
				GL11.glPushMatrix();{
					GL11.glTranslatef(500, -150, -100);
					GL11.glScalef(220f, 220f, 220f);
					human_step_z=3.6f;
					human.DrawRotateWalkingHuman(delta,true,faceTexture, bodyTexture, dogPelvisTexture);
					this.human_posn_z=this.human_posn_z-human_step_z;
					this.phase4++;
				}
				GL11.glPopMatrix();
			}
			if(humanPhase==5||humanPhase==6) {
				GL11.glPushMatrix();{
					if(this.phase4!=0) {
						System.out.println(this.phase4);
						if(this.phase4!=930) {
							float dv=(float) (3.6*(this.phase4-930));
							this.human_posn_z=this.human_posn_z+dv;
						}
						this.phase4=0;
					}
					GL11.glTranslatef(500, -150, 100);
					GL11.glScalef(220f, 220f, 220f);
					//GL11.glTranslatef(posn_x*3.0f, 0.0f, posn_y*3.0f);
					GL11.glRotatef(90, 0, 1, 0);
					human.DrawHuman(delta,true,faceTexture, bodyTexture, dogPelvisTexture);
					//human.DrawWithdrawHuman(delta,true,faceTexture, bodyTexture, dogPelvisTexture, 90);
				}
				GL11.glPopMatrix();
			}
			
			GL11.glClearColor(1.0f, 1.0f, 1.0f, 1.0f); 	
		}
		GL11.glPopMatrix();	
	}
		  
	public static void main(String[] argv) {
		MainWindow hello = new MainWindow();
		hello.start();
	}
	 
	
	//�ú������������к��ڻ���õ�����
	public void init() throws IOException {      
	  texture = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("res/android.png"));
	  faceTexture=TextureLoader.getTexture("PNG",ResourceLoader.getResourceAsStream( "res/HumanHead.png"));
	  bodyTexture=TextureLoader.getTexture("PNG",ResourceLoader.getResourceAsStream( "res/body.png"));
	  pelvisTexture=TextureLoader.getTexture("PNG",ResourceLoader.getResourceAsStream( "res/underwear.png"));
	  redWallTexture=TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream( "res/RedWall.png"));
	  floorTexture=TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream( "res/floor.png"));
	  grassTexture=TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream( "res/grass.png"));
	  skyTexture=TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream( "res/sky.png"));
	  drawings1=TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream( "res/sign.png"));
	  drawings2=TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream( "res/Mona.png"));
	  drawings3=TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream( "res/flower.png"));
	  bedTexture=TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream( "res/bed.png"));
	  dogHeadTexture=TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream( "res/dogHead.png"));
	  this.dogBodyTexture=TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream( "res/dogBody.png"));
	  this.dogPelvisTexture=TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream( "res/dogPelvis.png"));
	  this.riverpast=TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream( "res/riverlater.png"));
	  this.riverlast=TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream( "res/riverpast.png"));
	  this.animation1=TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream( "res/JOJO1.png"));
	  this.animation2=TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream( "res/JOJO2.png"));
	  this.animation3=TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream( "res/JOJO3.png"));
	  this.animation4=TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream( "res/JOJO4.png"));
	  this.panel=TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream( "res/panel.png"));
	  this.machineBottom=TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream( "res/machineBottom.png"));
	}

	
}
