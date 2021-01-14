import processing.core.*;
import sprites.utils.StopWatch;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import sprites.*;
import sprites.maths.*;
import sprites.utils.*;

public class Enemy {
	protected final PApplet d;
	int type;
	float enemX;
	float enemY;
	 float enemaccX;//x accel for enemy
	    float enemaccY;//y accel for enemy
	    float enemacc;//base pixel movement multiplier
	    int hitcd=0;
	    float enemhealth;
	    int r;
	    int range;
	    int actcd;
	    float weppos;
	    int action=0;
	    boolean danger= false;
	    int wepoffsetx;
	    int wepoffsety;
	    float prevangle=0;
	    float damage;
	    boolean wepup= true;
	    int rando;
	    int once=1;
	    
	    float ra;
	    float x21;
	    float y21;
	    float y22;
	    float x22;
	    float angle=-2;
	    float lspeed;
	    float angle2=-2;
	    int pathexempt=0;
	    int movexempt=0;
	    int superarmor=0;
	    
	    StopWatch sw = new StopWatch();
	    
	public Enemy(PApplet pa) {
		d = pa;
               
	}
	
	 public Enemy(PApplet pa,int t,float x1,float y1){
	        d=pa;
	        type=t;
	        enemX=x1;
	        enemY=y1;
	        actcd=0;
	        if (type==1) {
	        	r=50;
	        	enemhealth=300;
	        	enemacc=(float) 1.5;
	        	range=100;
	        	weppos=0;
	        	wepoffsetx=-20;
	        	wepoffsety=45;
	        	damage=50;
	        }
	        else if (type==2) {
	        	r=90;
	        	enemhealth=800;
	        	enemacc=(float) 0.6;
	        	range=150;
	        	weppos=-20;
	        	wepoffsetx=-30;
	        	wepoffsety=35;
	        	damage=150;
	        	superarmor=1;
	        	
	        }
	        else if (type==3) {
	        	r=50;
	        	enemhealth=120;
	        	enemacc=(float) 2;
	        	range=999;
	        	weppos=30;
	        	wepoffsetx=-20;
	        	wepoffsety=32;
	        	damage=30;
	        }
	        else if (type==4) {
	        	r=50;
	        	enemhealth=2000;
	        	enemacc=(float) 2;
	        	range=999;
	        	weppos=30;
	        	wepoffsetx=-20;
	        	wepoffsety=32;
	        	damage=30;
	        	superarmor=1;
	        }
	        else if (type==5) {
	        	r=100;
	        	enemhealth=3000;
	        	enemacc=(float) 1.2;
	        	range=999;
	        	weppos=30;
	        	wepoffsetx=-20;
	        	wepoffsety=32;
	        	damage=30;
	        	superarmor=1;
	        }
	    }
	 public void Display() {
		 
		 if (type==4) {
			 float elapsedTime = (float) sw.getElapsedTime();
			 S4P.updateSprites(elapsedTime);
			d.pushMatrix();
			  d.translate(enemX, enemY);
			  if (rando==1&&action==1) {
				  main.spritesheetlaser.draw();
			  }
			  else if (rando==0&&action==1&& actcd<50) {
				  main.spritesheetarrow.draw();
			  }
			  else if (rando==2&&action==1) {
				  if (enemX>Player.x) {
					  main.spritesheetDl.draw();
				  }
				  else {
					  main.spritesheetDr.draw();
				  }
			  }
			  // Draw all 'registered' sprites
			  else {
			  main.spritesheet.draw();
			  }
			  d.popMatrix();
		 }
		 else {
		 d.ellipse(enemX,enemY,r,r);
		 }
	}
	 public void Barrier(){
	        if (enemX>1900){
	            enemX=1900;
	        }
	        if (enemX<0){
	            enemX=0;
	        }
	        if (enemY>1050){
	            enemY=1050;
	        }
	        if (enemY<0){
	            enemY=0;
	        }
	            }
	 public float GetDam() {
		 return damage;
	 }
	 public void Move(){
	        
	        
	        if (action!=1|| hitcd!=0 || movexempt==1) {
	        	
	        	enemX=enemX+(enemaccX-0)*-enemacc;
	        	enemY=enemY+(enemaccY-0)*-enemacc;
	        	
	        	
	        }
	        
	        	
	        
	    }
	 public void Pathfind(){
		 if (hitcd==0 &&action!=2 && pathexempt==0 ) {
		 	float theta_radiansb = (float) Math.atan2(Player.x-enemX,Player.y-enemY);
	        this.enemaccX   = (float) (0 - 2 * Math.sin(theta_radiansb));
	       
	        this.enemaccY   = (float) (0 - 2 * Math.cos(theta_radiansb));
	        }
		 else if (hitcd>0){
			 hitcd=hitcd-1;
		 }
	 }
	 public void kb(float x2 ,float  y2) {
		 if (!(type==4&&rando==4)) {
		 float theta_radianskb = (float) Math.atan2(enemX-x2, enemY-y2);
         float endXkb   = (float) (enemX - 0.1 * Math.sin(theta_radianskb));
        float endYkb   = (float) (enemY - 0.1 * Math.cos(theta_radianskb));
        
        //knocksback enemys based on weapon power property
        
        enemaccY=enemaccY-(endYkb-enemY)*-150;
        enemaccX=enemaccX-(endXkb-enemX)*-150;
        enemY=enemY-(endYkb-enemY)*700;
        enemX=enemX-(endXkb-enemX)*700;
	 }
	 }
	 public float GetX(){
		 return enemX;
	 }
	 public int GetSwing(){
		 if (type==1) {
			 return (int)(-2);
		 }
		 if (type==2) {
			 return (int)(-3);
		 }
		 if (type==3) {
			 return -2;
		 }
		 return 0;
	 }
	 public int GetRange(){
		 if (type==1) {
			 return range;
		 }
		 else if (type==2) {
			 return range;
		 }
		 return 0;
	 }
	 public float GetY(){
		 return enemY;
	 }
	 public int GetActcd(){
		 return actcd;
	 }
	 public int Getwepoffsetx(){
		 return wepoffsetx;
	 }
	 public int Getwepoffsety(){
		 return wepoffsety;
	 }
	 public float GetRad(){
		 return r;
	 }
	 public void Hit(float d) {
		 if (rando!=4) {
		 enemhealth=enemhealth-d;
		 hitcd=25;
		 enemaccX=0;
		 enemaccY=0;
		 danger=false;
		 }
	 }
	 public int GetHitcd() {
		 return hitcd;
	 }
	 public float GetWeppos() {
		 return weppos;
	 }
	 public boolean GetDanger() {
		 return danger;
	 }
	 public float GetHealth() {
		 return enemhealth;
	 }
	 public void Action(Player p,Map m) {
		 
//		 System.out.println((d.dist(enemX,enemY,Player.x,Player.y)));
		 if (d.dist(enemX,enemY,Player.x,Player.y)<range && action==0) {
			 action=1;
		 }
		 
		 if (action==1||action==2) {
			 actcd=actcd+1;
			 //type 1 enemy ACTIONS
			 if (type==1) {
				 if (actcd<20) {
					weppos=-90; 
				 }
				 else if (actcd>=20 && actcd<28) {
						weppos=weppos-2; 
						danger=true;
					 }
				 else if (actcd>=28 && actcd<50) {
						weppos=0; 
						danger=false;
						
					 }
				 else if (actcd==50) {
					 actcd=0;
					 action=0;
				 }
				 
			 }
			 //////////////////////////////////
			 //Type 2 Enemy ACTIONS
			 else if (type==2) {
				 if (actcd<60) {
					weppos=(float) 40;
					wepoffsetx=0;
					wepoffsety=100;
					wepup=true;
				 }
				 else if (actcd==60) {
					 weppos=181;
					danger=true;
					
				 }
			 else if (actcd>60 && actcd<100) {
				 danger=false;
				 wepup=false;
			 }
				 else if (actcd==100) {
					 
					 	weppos=-20;
						danger=false;
						wepoffsetx=-30;
						wepoffsety=35;
				 }
				 else if (actcd==140) {
					 actcd=0;
					 action=0;
				 }
				 
			 }
			//type 3 enemy ACTIONS
			 else if (type==3) {
				 if (actcd<20) {
					weppos=(float) -8; 
					wepoffsetx=0;
					wepoffsety=40;
				 }
				 else if (actcd==20) {
					 wepoffsety=35;
						
						danger=true;
					 }
				 else if (actcd==30) {
					 wepoffsety=35;
						danger=true;
					 }
				 else if (actcd==40) {
					 wepoffsety=35;
						danger=true;
					 }
				 else if (actcd>20 && actcd<50) {
					 wepoffsety=40;
						
						danger=false;
						
					 }
				 else if (actcd==50) {
					 weppos=30;
					 wepoffsetx=-20;
			        	wepoffsety=32;
				 }
				 else if (actcd>50&& actcd<=180) {
					 if (d.dist(enemX,enemY,Player.x,Player.y)<300) {
						 float theta_radianskb = (float) Math.atan2(enemX-Player.x, enemY-Player.y);
				         
					    	
				           enemaccX   = (float) -(2* Math.sin(theta_radianskb));
				          enemaccY   = (float) -(2* Math.cos(theta_radianskb));
				         action=2;
					 }
					 
				 }
				 else  {
					 actcd=0;
					 action=0;
				 }
				 
			 }
			 
			 //Type 4 BOSS////////////////////////////////////
			 else if (type==4) {
				 if (actcd==1) {
				 rando=(int)d.random(0,4);
				
				 }
				 if (enemX<100||enemY>1000||enemY<100||enemX>1600) {
					 rando=2;
					 
				 }
				 if (once==1&& type==4) {
					 once=0;
					 rando=4;
				 }
				 if (rando==0) {
				 if (actcd<20) {
					weppos=(float) -8; 
					wepoffsetx=0;
					wepoffsety=40;
				 }
				 else if (actcd==20) {
					 wepoffsety=35;
						
						danger=true;
					 }
				 else if (actcd==25) {
					 wepoffsety=35;
						danger=true;
					 }
				 else if (actcd==30) {
					 wepoffsety=35;
						danger=true;
					 }
				 else if (actcd==40) {
					 wepoffsety=35;
						danger=true;
					 }
				 else if (actcd==45) {
					 wepoffsety=35;
						danger=true;
					 }
				 else if (actcd>20 && actcd<50) {
					 wepoffsety=40;
						
						danger=false;
						
					 }
				 else if (actcd==50) {
					 weppos=30;
					 wepoffsetx=-20;
			        	wepoffsety=32;
				 }
				 else if (actcd>50&& actcd<=180) {
					 if (d.dist(enemX,enemY,Player.x,Player.y)<300) {
						 float theta_radianskb = (float) Math.atan2(enemX-Player.x, enemY-Player.y);
				         
					    	
				           enemaccX   = (float) -(2* Math.sin(theta_radianskb));
				          enemaccY   = (float) -(2* Math.cos(theta_radianskb));
				         action=2;
					 }
					 
				 }
				 else  {
					 actcd=0;
					 action=0;
				 }
				 }
				 
				 
				
				 else if (rando==1) {
					 float x1 = enemX;    // coordinates of line
					 float y1 = enemY;
					 float cx = Player.x;      // circle position (set by mouse)
					 float cy = Player.y;
					 float linerad=1900;
					 
					ra=30;
					if (actcd<10) {
						
					}
					
					else if (actcd<93) {
						 
						 lspeed=(float) (lspeed+0.002);
						 angle=(float) (angle+lspeed);
						 angle2=(float) (angle2-lspeed);
							x21=x1+linerad*d.cos(angle);
							y21=y1+linerad*d.sin(angle);
							x22=x1+linerad*d.cos(angle2);
							y22=y1+linerad*d.sin(angle2);
							d.strokeWeight (20);
							 boolean hit = lineCircle(x1,y1, x21,y21, cx,cy,ra);
							
							 
							 
							 
							 if (hit && p.combo!=4) { 
								  p.Hit(100);
							  }
							 d.stroke(255, 255, 3);
							 d.line(x1,y1, x21,y21);
							  
							 boolean hit2 = lineCircle(x1,y1, x22,y22, cx,cy,ra);
							 d.line(x1,y1, x22,y22);
							 d.strokeWeight(1);
							 
							 
							 if (hit2 && p.combo!=4) { 
								  p.Hit(100);
							  }
							  else d.stroke(0,150,255, 150);
							  
							 
						 }
					 

					 else {
						 ;
						 actcd=0;
						 action=0;
						 lspeed=0;
						 angle=-2;
						 angle2=-2;
					 }
					
					 
					  
					  
				 
				 }
				 else if (rando==2) {
					 if (actcd<=20) {
						 movexempt=0;
					 }
					 else if (actcd<40 && actcd>20) {
						 movexempt=1;
						 pathexempt=1;
					 enemacc=10;
					 if (Hitbox.MeleeContact(enemX,enemY,p.x,p.y,60)==true) {
						 p.Hit(50);
					 }
					 }
					 else if (actcd>55 && actcd<75) {
						 movexempt=1;
						 pathexempt=1;
					 enemacc=20;
					 if (Hitbox.MeleeContact(enemX,enemY,p.x,p.y,60)==true) {
						 p.Hit(50);
					 }
					 }
					 else if (actcd>85 && actcd<105) {
						 movexempt=1;
						 pathexempt=1;
					 enemacc=30;
					 if (Hitbox.MeleeContact(enemX,enemY,p.x,p.y,60)==true) {
						 p.Hit(50);
					 }
					 }
					 else if (actcd<120) {
						
						
						pathexempt=0;
						 movexempt=0;
						 enemacc=2;		
						
							 
					 }
					 else {
						 action=0; 
						 actcd=0;
						 
					 }
				 }
				 
				 else if (rando==3) {
					 if (actcd==20) {
						 m.EnemCreate(1,1);
						 action=0;
						 actcd=0;
					 }
					 
					 
				 }
				 else if (rando==4) {
					 enemY=(float) (enemY+0.5);
					 
					 if (actcd==610) {
						 action=0;
						 actcd=0;
						 
					 }
					 
					 
				 }


					
					
				 }
				 
			 }
		 }
	 boolean lineCircle(float x1, float y1, float x2, float y2, float cx, float cy, float r) {

		  // is either end INSIDE the circle?
		  // if so, return true immediately
		  boolean inside1 = pointCircle(x1,y1, cx,cy,r);
		  boolean inside2 = pointCircle(x2,y2, cx,cy,r);
		  if (inside1 || inside2) return true;

		  // get length of the line
		  float distX = x1 - x2;
		  float distY = y1 - y2;
		  float len = d.sqrt( (distX*distX) + (distY*distY) );

		  // get dot product of the line and circle
		  float dot = ( ((cx-x1)*(x2-x1)) + ((cy-y1)*(y2-y1)) ) / d.pow(len,2);

		  // find the closest point on the line
		  float closestX = x1 + (dot * (x2-x1));
		  float closestY = y1 + (dot * (y2-y1));

		  // is this point actually on the line segment?
		  // if so keep going, but if not, return false
		  boolean onSegment = linePoint(x1,y1,x2,y2, closestX,closestY);
		  if (!onSegment) return false;

		  // optionally, draw a circle at the closest
		  // point on the line
		 

		  // get distance to closest point
		  distX = closestX - cx;
		  distY = closestY - cy;
		  float distance = d.sqrt( (distX*distX) + (distY*distY) );

		  if (distance <= r) {
		    return true;
		  }
		  return false;
		}


		// POINT/CIRCLE
		boolean pointCircle(float px, float py, float cx, float cy, float r) {

		  // get distance between the point and circle's center
		  // using the Pythagorean Theorem
		  float distX = px - cx;
		  float distY = py - cy;
		  float distance = d.sqrt( (distX*distX) + (distY*distY) );

		  // if the distance is less than the circle's
		  // radius the point is inside!
		  if (distance <= r) {
		    return true;
		  }
		  return false;
		}


		// LINE/POINT
		boolean linePoint(float x1, float y1, float x2, float y2, float px, float py) {

		  // get distance from the point to the two ends of the line
		  float d1 = d.dist(px,py, x1,y1);
		  float d2 = d.dist(px,py, x2,y2);

		  // get the length of the line
		  float lineLen = d.dist(x1,y1, x2,y2);

		  // since floats are so minutely accurate, add
		  // a little buffer zone that will give collision
		  float buffer = (float) 0.1;    // higher # = less accurate

		  // if the two distances are equal to the line's
		  // length, the point is on the line!
		  // note we use the buffer here to give a range,
		  // rather than one #
		  if (d1+d2 >= lineLen-buffer && d1+d2 <= lineLen+buffer) {
		    return true;
		  }
		  return false;
		}
		 
	 }

