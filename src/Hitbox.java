import processing.core.*;
	import java.util.ArrayList;
	import java.util.Random;
	import static processing.core.PApplet.dist;
	import processing.event.KeyEvent;
	import java.lang.Math;
public class Hitbox {
	int blocked =0;

	protected final PApplet d;
	public Hitbox(PApplet pa) {
		d = pa;
               
	}
	
	public boolean Contact(int combo, float x1, float y1, float x2, float y2,float length,float rad,float defence,Player p,Map m){
		
		if (combo==-3 ) {
			float theta_radianskb = (float) Math.atan2(x1-x2, y1-y2);
	         
	    	
	          float endXkb   = (float) (x1 -length/2 * Math.sin(theta_radianskb));
	         float endYkb   = (float) (y1 - length/2 * Math.cos(theta_radianskb));
	         //d.ellipse(endXkb, endYkb, 30, 30);
	         
	 		if 	(dist(endXkb, endYkb, x2, y2)<(length/2+rad)) {
	 				return true;
	 		}
		}
	    else if (combo==-2 ) {
			float theta_radianskb = (float) Math.atan2(x1-x2, y1-y2);
	         
	    	
	          float endXkb   = (float) (x1 -length/2 * Math.sin(theta_radianskb));
	         float endYkb   = (float) (y1 - length/2 * Math.cos(theta_radianskb));
	        
         if (p.combo!=4) {
			if 	(dist(endXkb, endYkb, x2, y2)<(length/2+rad)) {
				
				return true;
				
			}
			else {
				return false;
			}
		}
         else {
			 theta_radianskb = (float) Math.atan2(x2-m.AIMX, y2-m.AIMY);
		     float endXkb2   = (float) (x2 -100 * Math.sin(theta_radianskb));
            float endYkb2   = (float) (y2 - 100 * Math.cos(theta_radianskb));
            if (dist(endXkb, endYkb, endXkb2, endYkb2)<(defence+12) && dist(endXkb, endYkb, x2, y2)<(length/2+rad)+10 || dist(endXkb2, endYkb2,x1,y1)<defence+50) {
            	
            	blocked=1;
            	return false;
            	
            }
            else if (dist(endXkb, endYkb, x2, y2)>=(length/2+rad)) {
            	return false;
            }
            else {
           
            	return true;
            }
	         }
		}
	    else if (combo==-4 ) {
			float theta_radianskb = (float) Math.atan2(x1-x2, y1-y2);
	         
	    	
	          float endXkb   = (float) (x1 -length/2 * Math.sin(theta_radianskb));
	         float endYkb   = (float) (y1 - length/2 * Math.cos(theta_radianskb));
	         
         if (p.combo!=4) {
			if 	(dist(endXkb, endYkb, x2, y2)<(length/2+rad)) {
				
				return true;
				
			}
			else {
				return false;
			}
		}
         else {
			 theta_radianskb = (float) Math.atan2(x2-m.AIMX, y2-m.AIMY);
		     float endXkb2   = (float) (x2 -50 * Math.sin(theta_radianskb));
            float endYkb2   = (float) (y2 - 50* Math.cos(theta_radianskb));
            
            if (dist(endXkb, endYkb, endXkb2, endYkb2)<(defence+10) && dist(endXkb, endYkb, x2, y2)<(length/2+rad) || dist(endXkb2, endYkb2,x1,y1)<defence+5) {
            	
            	
            	m.block=1;
            	return false;
            	
            }
            else if (dist(endXkb, endYkb, x2, y2)>=(length/2+rad)) {
            	
            	return false;
            }
            else {
           
            	return true;
            }
	         }
		}
		if (combo==-1) {
        	float theta_radianskb = (float) Math.atan2(x1-m.AIMX, y1-m.AIMY);
        float endXkb   = (float) (x1 -length/2 * Math.sin(theta_radianskb));
       float endYkb   = (float) (y1 - length/2 * Math.cos(theta_radianskb));
       //d.ellipse(endXkb, endYkb, 30, 30);
        }
		if (combo==0) {
			float theta_radianskb = (float) Math.atan2(x1-m.AIMX, y1-m.AIMY);
	         
	    	
	          float endXkb   = (float) (x1 -length/2 * Math.sin(theta_radianskb));
	         float endYkb   = (float) (y1 - length/2 * Math.cos(theta_radianskb));
	         //d.ellipse(endXkb, endYkb, 30, 30);
	        
			if 	(dist(endXkb, endYkb, x2, y2)<(length/2+rad)) {
				
				
				return true;
			}
			else {
			
				return false;
			}
		}
		else if (combo==1) {
			float theta_radianskb = (float) Math.atan2(x1-m.AIMX, y1-m.AIMY);
	         
	    	
	          float endXkb   = (float) (x1 -length/2 * Math.sin(theta_radianskb));
	         float endYkb   = (float) (y1 - length/2 * Math.cos(theta_radianskb));
	         //d.ellipse(endXkb, endYkb, 30, 30);
	
			if 	(dist(endXkb, endYkb, x2, y2)<(length/2+rad)) {
				
				return true;
				
			}
			else {
				
				return false;
			}
		}
		
		else if (combo==2) {
			
			if 	(dist(x1, y1, x2, y2)<(length+rad)) {
				return true;
			}
			else {
				return false;
			}
		}
		else if (combo==3) {
			
			if 	(dist(x1, y1, x2, y2)<75+rad) {
				return true;
			}
			else {
				return false;
			}
		}
		return false;
	}
	public static boolean MeleeContact(float x1, float y1, float x2, float y2,float rad) {
		if (dist(x1,y1,x2,y2)<(rad)) {
			return true;
		}
		else {
			return false;
		}
	}
}
