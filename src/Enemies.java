import processing.core.*;
import static processing.core.PApplet.dist;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author 333687820
 */
public class Enemies {
    protected final PApplet d;
    float enemX;//xpos of enemy
    float enemY;//ypos of enemy
    float enemaccX;//x accel for enemy
    float enemaccY;//y accel for enemy
    float enemacc;//base pixel movement multiplier
    int health;//health of enemy
    int enemrad;//size of enemy
    int type;//type of enemy
    float endX1;////holds xpos of the gun barrel
    float endY1;//hold ypos of gun barrel
    int Cooldownfire;//holds cooldown on enemy firerate
    int Maxcdf;//holds mac firerate that the cooldownfire is reset to
    public static double statscale=0;//scales enemy hp
    
    /**
*Method that initializes this class
*@param pa the papplet

*/ 
    public Enemies(PApplet pa){
    d = pa;
		
    enemX = 0;
    enemY = 0;
    
    }
    /**
*Method that initializes an enemy object
*@param pa the papplet
*@param xb the xpos of enemy
*@param yb the ypos of enemy
* @param acc the acceleration of enemy
* @param h the health of enemy
* @param ace the accel of bullet
* @param r the radius of enemy 
* @param t the type of bullet
* 
*/ 
    public Enemies(PApplet pa,float xb,float yb,float acc,int h,int r,int t,int cool){
        d=pa;
        enemX=xb;
        enemY=yb;
        enemacc=acc;
        health=h;
        enemrad=r;
        type=t;
        Maxcdf=cool;
        Cooldownfire=Maxcdf;
        System.out.println("on");
        
    }
/**
*Method that resets enemy cooldown fire when they are hit by a bullet
*/
    public void Reset(){
        Cooldownfire=Maxcdf;
    }
    
/**
*Method that returns health of the current enemy object
* @return health the health of the enemy object
*/
    public int GetHealth(){
        return health;
    }
/**
*Method that returns the enemy's fireratecooldown
* @return Cooldownfire the timer that determine when the current enemy can shoot
*/
    
    public int getFire(){
        return this.Cooldownfire;
    }

/**
*Method that resets enemy cooldownfire when they shoot
*/  
    public void Reload(){
        Cooldownfire=Maxcdf;
    }
    
/**
*Method that returns what the enemy type does when their cooldown is done
* @return String the action that they will do
*/
    
    //this is mostly useful if I wanted to expand enemy movesets
    public String Action(){
        if (this.type==2||this.type==3||this.type==4||this.type==5){
            return "fire";
        }
        else {
            return "nul";
        }
    }
/**
*Method that checks to see if bullets collide with the current enemy object and applies knockback
* @param enemX current xpos of enemy object
* @param enemY current ypos of enemy object
* @param x2 current xpos of bullet
* @param y2 current ypos of bullet 
* @param typeb type of bullet
* @return Boolean true/false
*/
    
//    public boolean HitBox(float x2,float y2,int typeb){
//        if ((dist(enemX, enemY, x2, y2)<39)&&((typeb==2)||(typeb==1)||(typeb==4))){
//            
//            //player bullet
//            if (typeb==1){
//             float theta_radianskb = (float) Math.atan2(enemX-x2, enemY-y2);
//         float endXkb   = (float) (enemX - 0.1 * Math.sin(theta_radianskb));
//        float endYkb   = (float) (enemY - 0.1 * Math.cos(theta_radianskb));
//        
//        //kocksback enemys based on weapon power property
//        enemY=enemY+(endYkb-enemY)*-Weapons.power;
//        enemX=enemX+(endXkb-enemX)*-Weapons.power;
//            }
//            //enemy bullet
//            else if (typeb==2){
//                float theta_radianskb = (float) Math.atan2(enemX-x2, enemY-y2);
//         float endXkb   = (float) (enemX - 0.1 * Math.sin(theta_radianskb));
//        float endYkb   = (float) (enemY - 0.1 * Math.cos(theta_radianskb));
//        
//        //enemies have base knockback they can do for each other
//        enemY=enemY+(endYkb-enemY)*-80;
//        enemX=enemX+(endXkb-enemX)*-80;
//            }
//            //protobelt bullet
//            else if (typeb==4){
//                float theta_radianskb = (float) Math.atan2(enemX-x2, enemY-y2);
//         float endXkb   = (float) (enemX - 0.1 * Math.sin(theta_radianskb));
//        float endYkb   = (float) (enemY - 0.1 * Math.cos(theta_radianskb));
//        
//        //pThis only weapon that uses type 4 bullets is protobelt so the knockback value is set 
//        enemY=enemY+(endYkb-enemY)*-200;
//        enemX=enemX+(endXkb-enemX)*-200;
//            }
//            //returns if the bullet hits the enemy or not
//            return true;
//        }
//        else{
//            return false;
//        }
//        
//    }
    
/**
*Method that subtracts health from enemy based on weapon used and will return true if enemy dies
* @param typeb the type of bullet
* @return String the action that they will do
*/
    
//    public boolean Hit(int typeb){
//        if (typeb==1){
//        health=health-Weapons.damage;
//        }
//        if (typeb==2){
//        health=health-5;
//        }
//        if (typeb==4){
//        health=health-50;
//        }
//        //if the enemy health goes below 0 alongside the bonous hp
//        if ((health+statscale)<0){
//            
//            return true;
//        }
//        else{
//            return false;
//        }
//    }

/**
*Method that returns xpos of enemy object
* @return enemX the xpos of current enemy object
*/
    
    public float getX() {
		return enemX;

	}
/**
*Method that returns xpos of enemy object gun barrel
* @return endX1  xpos of enemy object gun barrel
*/
    
    public float getEndX() {
		return endX1;

	}
/**
*Method that returns ypos of enemy object gun barrel
* @return endY1  ypos of enemy object gun barrel
*/
    public float getEndY() {
		return endY1;

	}
/**
*Method that returns ypos of enemy object
* @return enemY  ypos of enemy object
*/

	public float getY() {
		return enemY;

	}
/**
*Method that returns type of enemy object
* @return type type of current enemy object
*/
        
        public int getType() {
		return type;

	}

        
/**
*Method that moves the current enemy object 
*/
        
        public void Move(){
        
       
        
        enemX=enemX+(enemaccX-0)*-enemacc;
        enemY=enemY+(enemaccY-0)*-enemacc;

    }
}
