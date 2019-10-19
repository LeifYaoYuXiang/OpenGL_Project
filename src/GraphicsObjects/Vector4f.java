package GraphicsObjects;



public class Vector4f {

	public float x=0;
	public float y=0;
	public float z=0;
	public float a=0;
	
	public Vector4f() 
	{  
		x = 0.0f;
		y = 0.0f;
		z = 0.0f;
		a = 0.0f;
	}
	
	
	public Vector4f(float x, float y, float z,float a) 
	{ 
		this.x = x;
		this.y = y;
		this.z = z;
		this.a = a;
	}
	
	/*
	* Goal:
	* 	use this vector plus another one
	* Mechanism:
	* 	plus x,y,z,a position respectively
	* */
	public Vector4f PlusVector(Vector4f Additonal) 
	{ 
		return new Vector4f(this.x+Additonal.x, this.y+Additonal.y, this.z+Additonal.z,this.a+Additonal.a);
	} 
	
	/*
	 * Goal:
	 * 	use this vector minus another one
	 * Mechanism:
	 * 	minus x,y,z,a position respectively
	 * */
	public Vector4f MinusVector(Vector4f Minus) 
	{ 
		this.x=this.x-Minus.x;
		 this.y=this.y-Minus.y;
		 this.z=this.z-Minus.z;
		 this.a=this.a-Minus.a;
		 return this;
	}
	
	/*
	 * Goal:
	 * 	use this vector plus one point
	 * Mechanism:
	 * 	plus x,y,z,a position respectively
	 * */
	public Point4f PlusPoint(Point4f Additonal) 
	{ 
		return new Point4f(this.x+Additonal.x,this.y+Additonal.y,this.z+Additonal.z,this.a+Additonal.a);
	} 
	
	/*
	 * Goal:
	 * 	use this vector multiply one scale
	 * Mechanism:
	 * 	multiply x,y,z,a position respectively
	 * */
	public Vector4f byScalar(float scale )
	{
		this.x=this.x*scale;
		this.y=this.y*scale;
		this.z=this.z*scale;
		this.a=this.a*scale;
		return this;
	}
	
	/*
	 * Goal:
	 * 	make the direction of one vector negative
	 * Mechanism:
	 * 	negate x,y,z,a position respectively
	 * */
	public Vector4f  NegateVector()
	{
		this.x=-this.x;
		this.y=-this.y;
		this.z=-this.z;
		this.a=-this.a;
		return this;
	}
	
	/*
	 * Goal:
	 * 	calculate the length of one vector
	 * Mechanism:
	 * 	length=sqrt(x*x+y*y+z*z+a*a)
	 * */
	public float length()
	{
	    return (float) Math.sqrt(x*x + y*y + z*z+ a*a);
	}
	
	/*
	 * Goal:
	 * 	use this vector's normal vector
	 * Mechanism:
	 * 	divide the x,y,z,a position by the length of this vector respectively
	 * */
	public Vector4f Normal()
	{
		float LengthOfTheVector=  this.length();
		return this.byScalar(1.0f/ LengthOfTheVector); 
	} 
	
	
	/*
	 * Goal:
	 * 	dot cross this vector and another vector
	 * Mechanism:
	 *  dot_cross=this.x*v.x + this.y*v.y + this.z*v.z+ this.a*v.a
	 * */
	public float dot(Vector4f v)
	{ 
		return ( this.x*v.x + this.y*v.y + this.z*v.z+ this.a*v.a);
	}
	
	
	public Vector4f cross(Vector4f v)  
	{ 
    float u0 = (this.y*v.z - z*v.y);
    float u1 = (z*v.x - x*v.z);
    float u2 = (x*v.y - y*v.x);
    float u3 = 0; //ignoring this for now  
    return new Vector4f(u0,u1,u2,u3);
	}
 
}
	 
	   

/*

										MMMM                                        
										MMMMMM                                      
 										MM MMMM                                    
 										MMI  MMMM                                  
 										MMM    MMMM                                
 										MMM      MMMM                              
  										MM        MMMMM                           
  										MMM         MMMMM                         
  										MMM           OMMMM                       
   										MM             .MMMM                     
MMMMMMMMMMMMMMM                        MMM              .MMMM                   
MM   IMMMMMMMMMMMMMMMMMMMMMMMM         MMM                 MMMM                 
MM                  ~MMMMMMMMMMMMMMMMMMMMM                   MMMM               
MM                                  OMMMMM                     MMMMM            
MM                                                               MMMMM          
MM                                                                 MMMMM        
MM                                                                   ~MMMM      
MM                                                                     =MMMM    
MM                                                                        MMMM  
MM                                                                       MMMMMM 
MM                                                                     MMMMMMMM 
MM                                                                  :MMMMMMMM   
MM                                                                MMMMMMMMM     
MM                                                              MMMMMMMMM       
MM                             ,MMMMMMMMMM                    MMMMMMMMM         
MM              IMMMMMMMMMMMMMMMMMMMMMMMMM                  MMMMMMMM            
MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM               ZMMMMMMMM              
MMMMMMMMMMMMMMMMMMMMMMMMMMMMM          MM$             MMMMMMMMM                
MMMMMMMMMMMMMM                       MMM            MMMMMMMMM                  
  									MMM          MMMMMMMM                     
  									MM~       IMMMMMMMM                       
  									MM      DMMMMMMMM                         
 								MMM    MMMMMMMMM                           
 								MMD  MMMMMMMM                              
								MMM MMMMMMMM                                
								MMMMMMMMMM                                  
								MMMMMMMM                                    
  								MMMM                                      
  								MM                                        
                             GlassGiant.com */