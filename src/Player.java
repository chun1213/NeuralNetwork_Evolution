
import processing.core.*;
	import java.util.ArrayList;
	import java.util.Random;
	import static processing.core.PApplet.dist;
	import processing.event.KeyEvent;
	import java.lang.Math;
	public class Player{
	    float endY1;
	    float endX1;
	    float duration;
	    int newClick=1;
	    public static float x;
	    float theta_radianskb=0;
	    float counterkb;
	    boolean keys;
	    public static float endXkb;
	    public static float endYkb;
	    float cooldown1;
	    float slope;
	    public static float mouseXD;
	    public static float mouseYD;
	    public static float y;
	    float ace;
	    float accxr;
	    float accxl;
	    float accyt;
	    float accyd;
	    float go;
	    float dash;
	    float dashdis;
	    float proto=0;
	    float speed=0;
	    int pinvince=0;
	    float pdefence;
	    Inventory inv;
	    float pdamage=0;
	     int combo=-1;
	    int clicked=0;
	    protected final PApplet d;
	    boolean keyPressed;
	    public float phealth;
	    PImage mainimg;
	    float mana;
	    
	    float Maxhealth;
	    public Player(PApplet pa){
	        x=900;
	        y=900;
	        ace=4;
	        d=pa;
	        cooldown1=0;
	        phealth=200;
	        duration=20;
	        mana=0;
	        inv=new Inventory(d);
	       
	        
	    }
	    public void pReset() {
	    	x=900;
	        y=900;
	        ace=4;
	        cooldown1=0;
	        phealth=200;
	        duration=20;
	        pinvince=0;
	        combo=-1;
	        pdamage=0;
	        pdefence=0;
	        mana=0;
	    }
	    
	     public boolean MeleeHit(){
	        phealth=phealth-2;
	        if (phealth<0){
	            return true;
	        }
	        else {
	            return false;
	        }
	        
	    }
	    public boolean Hit(float damage){
	        main.deathcounter=0;
	        
	        if (pinvince==0) {
	        	phealth=phealth-damage;
		        pinvince=20;
	        if (phealth<0){
	            return true;
	        }
	        else {
	            return false;
	        }
	        }
	        else return false;
	    }
	    public void kb(float x2 ,float  y2,float pow) {
	    	 float theta_radianskb = (float) Math.atan2(x-x2, y-y2);
	         float endXkb   = (float) (x - 0.1 * Math.sin(theta_radianskb));
	        float endYkb   = (float) (y - 0.1 * Math.cos(theta_radianskb));
	        
	        //knocksback enemys based on weapon power property
	        
	        
	        y=y-(endYkb-y)*pow;
	        x=x-(endXkb-x)*pow;
	    }
	    public void Fire(){
	    	
	    	if (newClick==0) {
	    		theta_radianskb = (float) Math.atan2(x-mouseXD, y-mouseYD);
	         newClick=1;
	    	}
	          endXkb   = (float) (x - 1 * Math.sin(theta_radianskb));
	         endYkb   = (float) (y - 1 * Math.cos(theta_radianskb));
	        
	        if (combo==0) {
	        	speed=0;
	        }
	        else if (combo==1) {
	        	speed=0;
	        }
	        else if (combo==2) {
	        	speed=3;
	        }
	        else if (combo==3) {
	        	speed=30;
	        	pinvince=1;
	        }
	       
	        y=y+(endYkb-y)*speed;
	        x=x+(endXkb-x)*speed;
	        if (duration>=14 && combo==0){
	        	
	        	combo=-1;
	        	clicked=0;
	        }
	        if (duration>=14 && combo==1){
	        	combo=-1;
	        	clicked=0;
	        	
	        }
	        if (duration>=19 && combo==2){
	        	combo=-1;
	        	clicked=0;
	        }
	        if (duration>=14 && combo==3){
	        	combo=-1;
	        	clicked=0;
	        }
	    }
	    
	    
	    public void Dash(){
	        
	        float theta_radians = (float) Math.atan2(x-mouseXD, y-mouseYD);
	        float endX   = (float) (x - dashdis * Math.sin(theta_radians));
	        float endY   = (float) (y - dashdis * Math.cos(theta_radians));
	        float interendX   = (float) (x - (dashdis/2) * Math.sin(theta_radians));
	        float interendY   = (float) (y - (dashdis/2) * Math.cos(theta_radians));
	        
	        if (dash==1){
	            d.ellipse(interendX,interendY,50,50);
	            y=endY;
	            x=endX;
	        
	        dash=0;
	        cooldown1=40;
	        }
	        if (dash==2){
	            d.ellipse(interendX,interendY,50,50);
	            y=endY;
	            x=endX;
	        
	        dash=0;
	        cooldown1=20;
	        
	        
	        }
	    }
	    public float getX() {
	    	return(x);
	    }
	    public float getY() {
	    	return(y);
	    }
	    public void Roll() {
	    	inv.Roll();
	    }
	    public void Inv() {
	    	inv.Display();
	    	inv.Hover();
	    }
	    public void init() {
	    	float[] data=inv.Equip();
	    	
	    	phealth=phealth+data[3];
	    	pdefence=pdefence+data[2];
	    	pdamage=pdamage+data[1];
	    	Maxhealth=phealth;
	    	
	    }
//	    public void Display(){
//	        if (Weapons.weapon==1){
//	            d.stroke(89, 64, 3);
//	            d.strokeWeight(Weapons.width);
//	        double theta_radians = Math.atan2(Math.round(x)-mouseXD, Math.round(y)-mouseYD);
//	        endX1   = (float) (x - Weapons.length * Math.sin(theta_radians));
//	        endY1   = (float) (y - Weapons.length * Math.cos(theta_radians));
//	        d.line(Math.round(x),Math.round(y),Math.round(endX1),Math.round(endY1));
//	     
//	        }
//	        
//	        if (Weapons.weapon==2){
//	        double theta_radians = Math.atan2(Math.round(x)-mouseXD, Math.round(y)-mouseYD);
//	        d.strokeWeight(4);
//	        d.stroke(1);
//	        endX1   = (float) (x - (Weapons.length+15) * Math.sin(theta_radians));
//	        endY1   = (float) (y - (Weapons.length+15) * Math.cos(theta_radians));
//	        d.line(Math.round(x),Math.round(y),Math.round(endX1),Math.round(endY1));
//	       
//	            
//	            d.strokeWeight(Weapons.width);
//	        
//	        endX1   = (float) (x - Weapons.length * Math.sin(theta_radians));
//	        endY1   = (float) (y - Weapons.length * Math.cos(theta_radians));
//	        d.line(Math.round(x),Math.round(y),Math.round(endX1),Math.round(endY1));
//	        endX1   = (float) (x - (Weapons.length+15) * Math.sin(theta_radians));
//	        endY1   = (float) (y - (Weapons.length+15) * Math.cos(theta_radians));
//	        }
//	        
//	        if (Weapons.weapon==3){
//	           
//	           d.strokeWeight(2);
//	        double theta_radians = Math.atan2(Math.round(x)-mouseXD, Math.round(y)-mouseYD);
//	        endX1   = (float) (x - (Weapons.length+20) * Math.sin(theta_radians));
//	        endY1   = (float) (y - (Weapons.length+20) * Math.cos(theta_radians));
//	        d.line(Math.round(x),Math.round(y),Math.round(endX1),Math.round(endY1));
//	        d.strokeWeight(Weapons.width);
//	      d.stroke(104, 66, 34);
//	        endX1   = (float) (x - Weapons.length * Math.sin(theta_radians));
//	        endY1   = (float) (y - Weapons.length * Math.cos(theta_radians));
//	        d.line(Math.round(x),Math.round(y),Math.round(endX1),Math.round(endY1));
//	        endX1   = (float) (x - (Weapons.length+20) * Math.sin(theta_radians));
//	        endY1   = (float) (y - (Weapons.length+20) * Math.cos(theta_radians));
//	        }
//	        
//	        if (Weapons.weapon==4){
//	            d.stroke(1);
//	            d.strokeWeight(5);
//	        double theta_radians = Math.atan2(Math.round(x)-mouseXD, Math.round(y)-mouseYD);
//	        endX1   = (float) (x - (Weapons.length+8) * Math.sin(theta_radians));
//	        endY1   = (float) (y - (Weapons.length+8) * Math.cos(theta_radians));
//	        d.line(Math.round(x),Math.round(y),Math.round(endX1),Math.round(endY1));
//	        d.strokeWeight(Weapons.width);
//	        endX1   = (float) (x - Weapons.length * Math.sin(theta_radians));
//	        endY1   = (float) (y - Weapons.length * Math.cos(theta_radians));
//	        d.line(Math.round(x),Math.round(y),Math.round(endX1),Math.round(endY1));
//	        endX1   = (float) (x - (Weapons.length+8) * Math.sin(theta_radians));
//	        endY1   = (float) (y - (Weapons.length+8) * Math.cos(theta_radians));
//	        
//	        }
//	        
//	        if (Weapons.weapon==5){
//	           
//	        d.strokeWeight(3);    
//	        double theta_radians = Math.atan2(Math.round(x)-mouseXD, Math.round(y)-mouseYD);
//	        endX1   = (float) (x - (Weapons.length+15)* Math.sin(theta_radians));
//	        endY1   = (float) (y - (Weapons.length+15) * Math.cos(theta_radians));
//	        d.line(Math.round(x),Math.round(y),Math.round(endX1),Math.round(endY1));
//	        d.strokeWeight(Weapons.width);
//	         d.stroke(49, 99, 8);
//	        endX1   = (float) (x - Weapons.length * Math.sin(theta_radians));
//	        endY1   = (float) (y - Weapons.length * Math.cos(theta_radians));
//	        d.line(Math.round(x),Math.round(y),Math.round(endX1),Math.round(endY1));
//	         endX1   = (float) (x - (Weapons.length+15)* Math.sin(theta_radians));
//	        endY1   = (float) (y - (Weapons.length+15) * Math.cos(theta_radians));
//	        }
//	        d.strokeWeight(4);
//	        d.stroke(1);
//	        d.image(d.loadImage("Main.png"),x,y);
//	        
//	        
//	    }
	    public void Barrier(){
	        if (x>1920){
	            x=1920;
	           
	        }
	        if (x<0){
	            x=0;
	            
	        }
	        if (y>1080){
	            y=1080;
	            
	        }
	        if (y<0){
	            y=0;
	            
	        }
	            }
	    public void Move(){
	    	if (pinvince>0) {
	    	pinvince=pinvince-1;
	    	}
	        x=x+ace*(accxr-accxl);
	            
	        y=y+ace*(accyd-accyt);
	        phealth-=(float) 0.6;
	    }
	    
	    public void Accel(){
	    	
	        if (d.keyPressed==true){
	            ace=(ace+0.2f);
	            
	        }
	        
	        else if((d.keyPressed!=true)&&(ace>4)){
	            ace=(ace-0.2f);
	        }
	        if (ace>10){
	            ace=10;
	        }
	    }
	    
	}


