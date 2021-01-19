import processing.core.*;
import processing.event.MouseEvent;
import sprites.utils.StopWatch;
import processing.event.KeyEvent;

import static processing.core.PApplet.dist;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;



public class Map {
PImage weapon;
int init=1;
int checkSkill=0;
protected final PApplet d;
ArrayList<Enemy> enem;
ArrayList<Boss2> enemb;
ArrayList<Bullets> bullets;
ArrayList<Text> popups;
Hitbox h;
skill s;
PImage enemwep;
float storeangle;
float angle;
int block=0;
int timer=0;
double points=0;
int beaten=0;
int beaten2=0;
int boss1alive=0;
int boss2alive=0;
float tintred=0;
float tintgreen=0;
float tintblue=0;
float AIMX=0;
float AIMY=0;
int end;

	public Map(PApplet pa){
		d=pa;
		enem= new ArrayList<Enemy>();
		enemb= new ArrayList<Boss2>();
		bullets= new ArrayList<Bullets>();
		popups=new ArrayList<Text>();
	}
		
	public void Display(Player p, Map m) {
		if (p.x<1800 && p.x>100 && p.y>100&&p.y<1000) {
			
		}
		
		points+=0.01;
		timer=timer+1;
		if (init==1) {
			p.pReset();
			enem.clear();
			bullets.clear();
			popups.clear();
			enemb.clear();
			EnemCreate(3,1);//type then amount
			EnemCreate(3,1);
			init=0;
			p.init();
			points=0;
			weapon=main.weapon;
			 h= new Hitbox(d);
			 s= new skill(d,p,m);
			 s.GetSkill(p.inv.equiped);
			 beaten=0;
			 boss1alive=0;
			 tintred=0;
			 tintgreen=0;
			 tintblue=0;
			 boss2alive=0;
			 d.textSize(10);
			 checkSkill=0;
		}
		
		/////////////AIII
		
		float prevdis=2000;
		
		
		for (int i=0;i<enem.size();i++) {
			
		
			if (dist(enem.get(i).GetX(), enem.get(i).GetY(), p.x, p.y)<prevdis) {
				prevdis=dist(enem.get(i).GetX(), enem.get(i).GetY(), p.x, p.y);
				AIMX=enem.get(i).GetX();
				AIMY=enem.get(i).GetY();
			}
		} 
		for (int i=0;i<bullets.size();i++) {
			
    		
			if (dist(bullets.get(i).getX(), bullets.get(i).getY(), p.x, p.y)<prevdis) {
				prevdis=dist(bullets.get(i).getX(), bullets.get(i).getY(), p.x, p.y);
				AIMX=bullets.get(i).getX();
				AIMY=bullets.get(i).getY();
				
			}
		}
		
		
		
		 
		
		s.ActPassive();
		
		if (p.phealth<=0) {
			
			main.CurrentTrack.stop();
			d.textSize(10);
			end=1;
			
		}
		
//		w.GetStats(5, 4);
		if (boss1alive==1) {
			if (tintred<200) {
				tintred++;
			}
			if (tintgreen<100) {
				tintgreen++;
			}
			d.tint(tintred,tintgreen,tintblue);
		}
		if (boss1alive==0) {
			tintred=0;
			tintgreen=0;
		}
		if (boss2alive==2) {
			
			if (tintred<200) {
				tintred=(float) (tintred+0.2);
			}
			if (tintblue<100) {
				tintblue=(float) (tintblue+0.1);
			}
			d.tint(tintred,tintgreen,tintblue);
		}
		if (boss2alive!=1) {
			d.image(main.ShrineBack,d.width/2,d.height/2);
		}
		
		if (boss2alive==1) {
			d.image(main.SpaceBack,d.width/2,d.height/2);
		}
		d.noTint();
    	p.Move();
    	p.Accel();
        p.Barrier();
        
        d.fill(0);
        d.ellipse(p.x,p.y,70,70);
        if (p.mana<100) {
			p.mana=(float) (p.mana+0.1);
		}
		if (p.mana>=100&&checkSkill==1) {
			checkSkill=0;
			s.SkillActive(enem);
		}
		else {
			checkSkill=0;
		}
        
        //ENEMY ACTIONS
        for (int i = enem.size()-1; i >= 0; i--) {
        	if (enem.get(i).GetHealth()<0) {
        		main.deathcounter=0;
        		
        		s.KillPassive();
        		if (enem.get(i).type==4) {
        			points=points+1000;
        			beaten=1;
        			boss1alive=0;
        			main.CurrentTrack.stop();
        		}
        		
        		else {
        		points=points+100;
        		}
        		
        		enem.remove(i);
        		if (points<2000&&enem.size()<7||beaten==1&&points<5000) {
        			EnemCreate((int)d.random(1,4),1);
        			EnemCreate((int)d.random(1,4),1);
        		}
        		else if (points>=2000&&enem.size()==0&&beaten==0){
        			EnemCreate(4,1);
        			main.CurrentTrack.stop();
        			main.CurrentTrack=main.Boss1theme1;
        			main.CurrentTrack.loop();
        			
        		}
        		else if (points>=5000&&enem.size()<10&&points<10000){
        			EnemCreate((int)d.random(1,4),1);
        			EnemCreate((int)d.random(1,4),1);
        		}
        		else if (points>10000 && enem.size()==0&& beaten2==0){
        			main.CurrentTrack.stop();
        			main.CurrentTrack=main.Boss2theme1;
        			main.CurrentTrack.loop();
        			EnemCreate(5,1);
        		}
        		else if (points>10000 && enem.size()<15&& beaten2==1){
        			EnemCreate((int)d.random(1,4),1);
        			EnemCreate((int)d.random(1,4),1);
        			EnemCreate((int)d.random(1,4),1);
        			
        		}
        		
        	}
        }
        for (Bullets bullet : new ArrayList<Bullets>(bullets)) {
            
        	if (bullet.getType()==4) {
            	for (int x=enem.size()-1; x >= 0; x--) {
            	if (h.Contact(-4,bullet.getX(), bullet.getY(),  enem.get(x).GetX(), enem.get(x).GetY(), enem.get(x).GetRad(),bullet.getrad(),p.pdefence,p,this)==true){
            		enem.get(x).Hit(bullet.GetDam());
            		bullets.remove(bullet);
            	}
            	}
        	}
        	
        }
        if (enemb.size()>=1) {
       // d.text(enemb.get(0).enemhealth,500,500);
        if (enemb.get(0).GetHealth()<0) {
        	enemb.remove(0);
    		s.KillPassive();
    		points=points+10000;
    		main.CurrentTrack.stop();
    		beaten2=1;
    		boss2alive=0;
    		EnemCreate((int)d.random(1,4),1);
    		tintred=0;
			tintgreen=0;
			tintblue=0;
        }
        }
        
        for (int i = enemb.size()-1; i >= 0; i--) {
        	if (timer%9==0) {
        		enemb.get(i).Pathfind2();
        	}
        	d.fill(0);
    		d.strokeWeight(1);
    		enemb.get(i).Action2(m);
        	
        	enemb.get(i).Move2();
        	enemb.get(i).Display2(p);
        	if (enemb.get(i).phase==1) {
        	enemb.get(i).Hitbox(bullets,h,p,m);
        	enemb.get(i).Boss2Melee(p);
        	}
        }
        for (int i = enem.size()-1; i >= 0; i--) {
        	
//        	if (h.MeleeContact( p.x, p.y, enem.get(i).GetX(), enem.get(i).GetY(),enem.get(i).GetRad())){
//        		p.MeleeHit();
//        	}
        	
        	
        	
        	
        	if (h.Contact(p.combo, p.x, p.y, enem.get(i).GetX(), enem.get(i).GetY(),p.inv.plength,enem.get(i).GetRad(),p.pdefence,p,this)&& enem.get(i).GetHitcd()==0 ) {
        	
        		float damage= p.pdamage;
        		
        		if (d.random(0,100)<10) {
        			damage=damage*2;
        		}
        		damage=damage*(d.random((float)0.9,(float)1.1));
        		if (p.combo==0||p.combo==1) {
        			p.phealth+=20;
        			enem.get(i).Hit(damage);
        			popups.add(new Text(d,enem.get(i).GetX(), enem.get(i).GetY(),Float.toString(damage),120,20,1));
        		}
        		if (p.combo==2) {
        			p.phealth+=20;
        			enem.get(i).Hit(damage*2);
        			enem.get(i).kb(p.x, p.y);
        			popups.add(new Text(d,enem.get(i).GetX(), enem.get(i).GetY(),Float.toString(damage*2),150,30,1));
        		}
        		
        	}
        	else {
        		
        			
        		
        		enem.get(i).Pathfind();
        		//enem.get(i).Move();
        		enem.get(i).Barrier();
        		
        		
        	}
        	if (enem.get(i).GetHitcd()==0|| enem.get(i).superarmor==1) {
        		
        		//enem.get(i).Action(p,m);
        		d.fill(0);
        	}
        	else {
        		d.fill(255,0,0);
        	}
        	enem.get(i).Display();
//        	d.ellipse(enem.get(i).GetX(),enem.get(i).GetY(),enem.get(i).GetRad(),enem.get(i).GetRad());
        	d.pushMatrix();
        	if (enem.get(i).wepup==true) {
        		angle=(float) (d.atan2(enem.get(i).GetY() - p.y, enem.get(i).GetX() - p.x)+Math.PI/4);
    		 	enem.get(i).prevangle= angle;
        	}
        	else {
        		 angle=enem.get(i).prevangle;
        	}
    		 d.translate(enem.get(i).GetX(),enem.get(i).GetY());
 	      
    		 d.rotate( angle+18+(enem.get(i).GetWeppos())/5);
			if (enem.get(i).type==1) {
				enemwep=main.weapon;
			}
			else if (enem.get(i).type==2) {
				enemwep=main.Mace;
			}
			else if (enem.get(i).type==3) {
				enemwep=main.Gun;
			}
			
			if (enem.get(i).type!=4) {
				d.image(enemwep,enem.get(i).Getwepoffsetx(),-enem.get(i).Getwepoffsety());
			}
    		 d.popMatrix();
				
				//Enemy swing hitbox
    		 
				if (h.Contact(enem.get(i).GetSwing(),enem.get(i).GetX(), enem.get(i).GetY(),  p.x, p.y, enem.get(i).GetRange(),60,p.pdefence,p,this)&& enem.get(i).GetHitcd()==0 && enem.get(i).GetDanger()==true&&enem.get(i).GetDam()!=0) {
				
				
				p.Hit(enem.get(i).GetDam());
				
				}
				else {
					if (h.blocked==1 && enem.get(i).GetDanger()==true) {
//						enem.get(i).Hit();
						enem.get(i).kb(p.x,p.y);
						h.blocked=0;
						
						p.kb(enem.get(i).GetX(), enem.get(i).GetY(),enem.get(i).GetDam()*10);
					}
					h.blocked=0;
				}
				
					
				if (enem.get(i).type==3 && enem.get(i).GetDanger()==true){
                    int enembul=1;//number of bullets shot per salvo
                   
                    
                    for (int x=0;x<enembul;x++){

                        
                        float enemgunx=enem.get(i).GetX();
                        float enemguny=enem.get(i).GetY();
                        bullets.add(new Bullets(d,enemgunx,enemguny,p.x,p.y,250,2));
                        
                    }
                    
				}
				if (enem.get(i).type==4 && enem.get(i).GetDanger()==true && enem.get(i).rando==0){
					float[] cords=BarrierGen();
					bullets.add(new Bullets(d,cords[0],cords[1],p.x,p.y,100,3));
					popups.add(new Text(d,cords[0],cords[1],p.x,p.y,80,2));
				}
                }
     //////////////////////////////////////////////////////////////////////////////

            
        
	for (int x = bullets.size()-1; x >= 0; x--){
        bullets.get(x).Move();
    }
    
    for (int x = bullets.size()-1; x >= 0; x--){
        float Xb=bullets.get(x).getX();
        float Yb=bullets.get(x).getY();
        if (Xb>2000||Xb<-50||Yb>1100||Yb<-50){
            bullets.remove(x);
            


        }

    }
    for (int x = bullets.size()-1; x >= 0; x--){

        if (bullets.get(x).getType()==1){
       d.fill(247, 116, 9);


   }
        if (bullets.get(x).getType()==4){
        d.fill(32, 252, 3);
        }
   if (bullets.get(x).getType()==2){
       d.fill(15, 13, 13);
       
   }
   if (bullets.get(x).getType()==3){
       d.fill(226, 14, 11);
   }
   
   bullets.get(x).Display();
   d.strokeWeight(1);
   }
    for (int i = bullets.size()-1; i >= 0; i--){
        float bx=bullets.get(i).getX();
        float by=bullets.get(i).getY();
        //if enemeybullets (type 2) hit the player
        
        
        if (bullets.get(i).getType()==3||(bullets.get(i).getType()==2)) {
	    if (h.Contact(-4,bx, by,  p.x, p.y, bullets.get(i).getrad(),30,p.pdefence,p,this)){
	    	 p.Hit(bullets.get(i).GetDam());
	    	bullets.remove(i);
	        
	       
	    } 
	    else {
	        	if (block==1) {
	            	block=0;
	            
	            	
	            	
	            	
	            	p.kb(bullets.get(i).getX(),bullets.get(i).getY(),bullets.get(i).GetDam()*10);
	            	bullets.remove(i);
	            	
	               
	            }
	        }
       
        
        //if the player health is less than 0
       
        
    }
        
        }
    
    
        
        
        d.pushMatrix();
        if (p.duration<15 &&p.combo==0) { //BASIC LEFT SWING 1
	    	p.Fire();
	        p.duration=p.duration+1;	
	        float angle=(float) (Math.atan2(AIMY - p.y, AIMX - p.x)+Math.PI/4);
	        d.translate(p.x,p.y);
	       
	        d.rotate( angle+18+(p.duration)/5);
			
	        d.image(weapon,-20,-30);
	       
	    }
        else if (p.duration <15 &&p.combo==1) { //BASIC RIGHT SWING 2
        	p.Fire();
	        p.duration=p.duration+1;	
	        float angle=(float) (Math.atan2(AIMY - p.y, AIMX - p.x)+Math.PI/4);
	        d.translate(p.x,p.y);
	       
	        d.rotate( (angle-16-(p.duration)/4));
	        d.image(weapon,-30,-30);
			
        }
        else if (p.duration <20 &&p.combo==2) { //SPIN KNOWCKBACK 3
        	p.Fire();
	        p.duration=p.duration+1;	
	        float angle=(float) (Math.atan2(AIMY - p.y, AIMX - p.x)+Math.PI/4);
	        d.translate(p.x,p.y);
	       
	        d.rotate( (angle-16+(p.duration/2)));
	        d.image(weapon,0,-60);
			
        }
        else if (p.duration<15 &&p.combo==3) {//DASH SLICE 3
	    	p.Fire();
	        p.duration=p.duration+1;	
	        float angle=(float) (Math.atan2(AIMY - p.y, AIMX - p.x)+Math.PI/4- 0.25);
	        d.translate(p.x,p.y);
	       
	        d.rotate( (float) (angle+Math.PI/4));
			
			d.image(weapon,15,-50);
			
	    }
        else if (p.combo==4) { //BLOCK 1
        	
	        float angle=(float) (Math.atan2(AIMY - p.y, AIMX - p.x)+Math.PI/4 - 0.35);
	        d.translate(p.x,p.y);
	       
	        d.rotate( (angle-16));
	        d.image(weapon,-30,0);
			
        }
        else {
        	p.clicked=0;
        	float angle=(float) (Math.atan2(AIMY - p.y, AIMX - p.x)+Math.PI/4- 0.25);
        	d.translate(p.x,p.y);
	       
        	d.rotate( (float)(angle+Math.PI/4+0.6));
			
        	d.image(weapon,-35,-30);
        	 
        }
        d.popMatrix();
        d.fill(0);
        d.textSize(20);
        d.textAlign(d.LEFT);
        d.text("Health: ",100,100);
        d.rectMode(d.CORNER);
        d.fill(212, 19, 19);
        d.rect(190,85,150,20);
        d.fill(16, 181, 41);
        d.rect(190,85,150*(p.phealth/p.Maxhealth),20);
        d.fill(0);
        d.rect(190,130,150,20);
        d.fill(50, 243, 250);
        d.rect(190,130,150*(p.mana/100),20);
        d.rectMode(d.CENTER);
        d.fill(0);
        d.text("Points: "+points,100,200);
        d.text("Mana: ",100,150);
        d.textAlign(d.CENTER);
        for (int i = popups.size()-1; i >= 0; i--){
        	popups.get(i).display();
        	if (popups.get(i).GetTimer()==0) {
        		popups.remove(i);
        		
        		
        	}
         }
	}


public float[] BarrierGen() {
	float w=0;
	float h=0;
	int rando=(int)d.random(0,4);
	if (rando==0) {
		h=d.random(0,1080);
	}
	else if (rando==1) {
		h=d.random(0,1080);
		w=1920;
	}
	else if (rando==2) {
		w=d.random(0,1920);
	}
	else if (rando==3) {
		w=d.random(0,1920);
		h=1080;
	}
	
	float[] data= {w,h};
	return data;
}
public void EnemCreate(int t,int n){
	if (enem.size()>8) {
		return;
	}
	for (int i=0;i<n;i++) {
	float[] cords=BarrierGen();
	if (t==5) {
		enemb.add(new Boss2(d,t,cords[0],cords[1]));
		boss2alive=1;
	}
	else if (t==4) {
		enem.add(new Enemy(d,t,d.width/2+25,d.height/2-400));
		boss1alive=1;
	}
	else {
	enem.add(new Enemy(d,t,cords[0],cords[1]));
	}
	}
	}
public double[] generateInputs(Player p){
    double[] inputs = new double[6];
   //RETURN INPUTS
//    	inputs[0]=p.x;
//    	inputs[1]=p.y;
    //biasnode	
    inputs[5]=1;
    	
    	
    	if (enem.size()>0) {
    		float prevdis=2000;
    		int index=0;
    		for (int i=0;i<enem.size();i++) {
    			
    		
    			if (dist(enem.get(i).GetX(), enem.get(i).GetY(), p.x, p.y)<prevdis) {
    				prevdis=dist(enem.get(i).GetX(), enem.get(i).GetY(), p.x, p.y);
    				index=i;
    				
    			}
    		}
    		
    		inputs[0] = (p.x-enem.get(index).GetX() - 0) / (2000 - 0);
    		
    		inputs[1] = (p.y-enem.get(index).GetY() - 0) / (2000 - 0);
    		inputs[2]=enem.get(index).type;
    	}
    	else {
    		
    		inputs[0]=0;
    		inputs[1]=0;
    		inputs[2]=0;
    	}
    	if (bullets.size()>0) {
    		float prevdis=2000;
    		int index=0;
    		for (int i=0;i<bullets.size();i++) {
    			
    		
    			if (dist(bullets.get(i).getX(), bullets.get(i).getY(), p.x, p.y)<prevdis) {
    				prevdis=dist(bullets.get(i).getX(), bullets.get(i).getY(), p.x, p.y);
    				index=i;
    				
    			}
    		}
    		
    		inputs[3]=p.x-bullets.get(index).getX();
    		inputs[4]=p.y-bullets.get(index).getY();
    		
    	}
    	else {
    		inputs[3]=2000;
    		inputs[4]=2000;
    		
    	}
    	inputs[1]=0;
		inputs[2]=0;
		inputs[3]=0;
		inputs[4]=0;
    //bulletx
    //bullety
    return inputs;
}
}
