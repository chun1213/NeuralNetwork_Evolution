
import processing.core.*;
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author 333687820
 */
public class Bullets {
    protected final PApplet d;
    float x;
    float y;
    float bulletx;
    float bullety;
    float bulletacx;
    float bulletacy;
    float endXb; 
    float endYb;
    int weapon=1;
    int numbul;
    float targX;
    float targY;
    float startX;
    float startY;
    int type;
    int bace;
    float damage=0;
    float r=10;
    
    
   

    public Bullets(PApplet pa,float xb,float yb,float tarx,float tary,int ace,int t){
        d=pa;
        bulletx=xb;
        bullety=yb;
//        startX=x;
//        startY=y;
        targX=tarx;
        targY=tary;
        bace=ace;
        type=t;
        if (type==2) {
        	damage=30;
        }
        if (type==3) {
        	damage=80;
        	r=40;
        }
        
        Fire();
        
        
        
        
    }
    public Bullets(PApplet pa,float xb,float yb,float tarx,float tary,int ace,float dam,float rad){
    	d=pa;
        bulletx=xb;
        bullety=yb;
//        startX=x;
//        startY=y;
        targX=tarx;
        targY=tary;
        bace=ace;
        damage=dam;
        r=rad;
        type=4;
        Fire();
    }
    public float getrad() {
    	return r;
    }
    public float WeaponType(){
        if (weapon==1){
            numbul=1;
            return (numbul);
        }
        if (weapon==2){
            numbul=4;
            return (numbul);
        }
        else{
            return 0;
        }
    }
//    public void Blood(){
//        type=3;
//    }
    
    public void setLocation(float x, float y){
      x=bulletx;
      y=bullety;
    }
    
    public void Move(){
        
       
        if (type==1){
        bulletx=bulletx+(endXb-x)*bace;
       
        bullety=bullety+(endYb-y)*bace;
        }
        if (type==2){
        bulletx=bulletx+(endXb-x)*bace;
        
        bullety=bullety+(endYb-y)*bace;
        }
         if (type==4){
        bulletx=bulletx+(endXb-x)*bace;
        
        bullety=bullety+(endYb-y)*bace;
        }
        if (type==3){
            bace=bace+5;
            
            
            
        bulletx=bulletx+(endXb-x)*bace;
        
        bullety=bullety+(endYb-y)*bace;
        }
    }
    
    public float getX() {
		return bulletx;

	}

	public float getY() {
		return bullety;

	}
	public float GetDam() {
		return damage;
	}
    public int getType() {
		return type;

	}
    public void Display(){
        
        if (type==2) {
            d.ellipse(bulletx,bullety,r,r);    
        }
        else if (type==3) {
            d.ellipse(bulletx,bullety,r,r);    
        }
        else if (type==4) {
            d.ellipse(bulletx,bullety,r,r);    
        }
        
    }
    
    public void Fire(){
        
        float theta_radiansb = (float) Math.atan2(bulletx-targX,bullety-targY);
        double a;
        double c;
//       if (type==1){
//             a = Math.random() * Weapons.spread / 100;
//             c = Math.random() * Weapons.spread / 100;
//             a *= Math.floor(Math.random()*2) == 1 ? 1 : -1;
//            c *= Math.floor(Math.random()*2) == 1 ? 1 : -1;
//            this.endXb   = (float) (x - 0.1 * Math.sin(theta_radiansb)+a);
//        this.endYb   = (float) (y - 0.1 * Math.cos(theta_radiansb)+c);
//        }
       if(type==2){
             a = Math.random() * 1 / 100;
             c = Math.random() * 1 / 100;
            a *= Math.floor(Math.random()*2) == 1 ? 1 : -1;
            c *= Math.floor(Math.random()*2) == 1 ? 1 : -1;
            this.endXb   = (float) (x - 0.1 * Math.sin(theta_radiansb)+a);
        this.endYb   = (float) (y - 0.1 * Math.cos(theta_radiansb)+c);
       } 
       if(type==3){
           
          this.endXb   = (float) (x - 0.1 * Math.sin(theta_radiansb));
      this.endYb   = (float) (y - 0.1 * Math.cos(theta_radiansb));
     } 
       if(type==4){
           
           this.endXb   = (float) (x - 0.1 * Math.sin(theta_radiansb));
       this.endYb   = (float) (y - 0.1 * Math.cos(theta_radiansb));
      } 
//       else if(type==4){
//             a = Math.random() * 3 / 100;
//             c = Math.random() * 3 / 100;
//            a *= Math.floor(Math.random()*2) == 1 ? 1 : -1;
//            c *= Math.floor(Math.random()*2) == 1 ? 1 : -1;
//            this.endXb   = (float) (x - 0.1 * Math.sin(theta_radiansb)+a);
//        this.endYb   = (float) (y - 0.1 * Math.cos(theta_radiansb)+c);
//       } 
        
        
       
       
        
    }
//    public String Delete(String ret){
//        if (this.bulletx>1920||this.bulletx<0||this.bullety>1080||this.bullety<0){
//          return("del"); 
//    }
//        else{
//            return("keep");
//        }
              
//}
}