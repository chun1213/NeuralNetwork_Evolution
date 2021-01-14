import processing.core.PApplet;
import java.util.ArrayList;
public class Boss2 extends Enemy{
	ArrayList<Float> enembY;
	ArrayList<Float> enembX;
	ArrayList<Float> enemaccbY;
	ArrayList<Float> enemaccbX;
	float length=40;
	int phase=1;
	int timer=0;
	int ratio;
	int once=1;
	int once2=1;
	public Boss2(PApplet pa,int t,float x1,float y1 ) {
		super(pa,t,x1,y1);
		
		enembX= new ArrayList<Float>();
		enembY= new ArrayList<Float>();
		enemaccbX= new ArrayList<Float>();
		enemaccbY= new ArrayList<Float>();
		// TODO Auto-generated constructor stub
		for (float x=0;x<length;x++) {
			enembX.add(-100-x*3);
			enembY.add((float)0);
		}
		for (float x=0;x<length;x++) {
			enemaccbX.add((float)0);
			enemaccbY.add((float)0);
		
		}
	}
	
	public void Display2(Player p) {
		
		if (phase==2) {
			d.tint(255, 126);
		}
		for (int x=0;x<enembX.size()-1;x++) {
			if (x==0) {
				d.pushMatrix();
				angle= (float) (d.atan2(enemaccbY.get(x),enemaccbX.get(x))+d.PI/2);
				
				d.translate(enembX.get(x),enembY.get(x));
				
				d.rotate( (angle));
				d.image(main.DOGHead,0,0);
				
				d.popMatrix();
			}
			else if (x<enembX.size()-2){
				d.pushMatrix();
				angle= (float) (d.atan2(enemaccbY.get(x),enemaccbX.get(x))+d.PI/2);
				d.translate(enembX.get(x),enembY.get(x));
				d.rotate( (angle));
			d.image(main.DOGBody,0,0);
			d.popMatrix();
			}
			else {
				d.pushMatrix();
				angle= (float) (d.atan2(enemaccbY.get(x),enemaccbX.get(x))+d.PI/2);
				
				d.translate(enembX.get(x),enembY.get(x));
				
				d.rotate( (angle));
				d.image(main.DOGTail,0,0);
				
				d.popMatrix();
			}
		}
		d.noTint();
	}
	
	public void Action2(Map m) {
		
		timer++;
		if (hitcd>0) {
			hitcd--;
		}
		
		if (enemhealth<1000 ) {
			enemacc=(float) 2;
			//main.Boss2theme1.stop();
			
			if (!(main.Boss2theme2.isPlaying())){
				main.CurrentTrack.stop();
				main.CurrentTrack=main.Boss2theme2;
				main.CurrentTrack.loop();
			}
			
			if (once==1) {
				m.boss2alive=3;
				length=60;
				once=0;
				phase=1;
				timer=0;
				m.popups.add(new Text(d,(float)d.width/2, d.height/2,"You WIN",500,60,1));
				enembX.clear();
				enembY.clear();
				enemaccbX.clear();
				enemaccbY.clear();
				for (float x=0;x<length;x++) {
					enembX.add(31000+x*3);
					enembY.add((float)0);
				}
				for (float x=0;x<length;x++) {
					enemaccbX.add((float)0);
					enemaccbY.add((float)0);
				
				}
			}
			if (timer==600&&once2==1) {
				once2=0;
				m.boss2alive=2;
				m.popups.add(new Text(d,(float)d.width/2, d.height/2,"Its Not Over Yet Kid",500,60,1));
			}
		}
		if (timer==600) {
			int ran =(int) (d.random(0,3));
			if (ran==0||ran==2) {
				phase=1;
				
			}
			else if (ran==1) {
				phase=2;
			}
				
			timer=0;
		}
		if (phase==2) {
			if (timer<100) {
				ratio=60;
			}
			else if (timer<200) {
				ratio=30;
			}
			else if (timer<300) {
				ratio=15;
			}
			else if (timer<400) {
				ratio=10;
			}
			if (timer%ratio==0) {
			float[] cords=BarrierGen();
			m.bullets.add(new Bullets(d,cords[0],cords[1],Player.x,Player.y,100,3));
			m.popups.add(new Text(d,cords[0],cords[1],Player.x,Player.y,80,2));
			}
			
		}
	}
	public void Move2(){
		
		for (int x=0;x<enembX.size()-1;x++) {
			 enembX.set(x,enembX.get(x)+enemacc*(enemaccbX.get(x)));
			 enembY.set(x,enembY.get(x)+enemacc*(enemaccbY.get(x)));
       
		}
    }
	public void Hitbox(ArrayList<Bullets> bullets ,Hitbox h, Player p, Map m){
		for (int x=enembX.size()-1; x >= 0; x--) {
		if (h.Contact(p.combo, p.x, p.y, enembX.get(x), enembY.get(x),p.inv.plength+50,r,p.pdefence,p,m)&& GetHitcd()==0 ) {
			if (x==0||x==enembX.size()-1) {
				Hit(p.pdamage*2);
        	
			}
			else {
    		Hit(p.pdamage/2);
    		
			}
		}
		}
		for (Bullets bullet : new ArrayList<Bullets>(bullets)) {
			for (int x=enembX.size()-1; x >= 0; x--) {
        	if (bullet.getType()==4) {
        		if (h.Contact(-4,bullet.getX(), bullet.getY(),  enembX.get(x), enembY.get(x), r,bullet.getrad(),p.pdefence,p,m)==true){
        			if (x==0||x==enembX.size()-1) {
        				Hit(bullet.GetDam()/2);
                		bullets.remove(bullet);
        			}
        			else {
            		Hit(bullet.GetDam()/4);
            		bullets.remove(bullet);
        			}
        		}
        	}
			}
		}
	}
	public void Pathfind2(){
		
		enemaccbX.remove(enemaccbX.size()-1);
		float mult;
		if (enembX.get(0)>d.width || enembY.get(0)>d.height ||enembX.get(0)<0 || enembY.get(0)<0) {
			mult=(float) 4;
			enemacc=(float) 0.9;
		}
		else {
			enemacc=(float) 1;
			mult=(float) 2;
		}
			 if (enembX.get(0)>Player.x) {
				 enemaccbX.add(0,(float)-mult+enemaccbX.get(0));
			 }
			 else if (enembX.get(0)<=Player.x) {
				 enemaccbX.add(0,(float)mult+enemaccbX.get(0));
			 }
			 
			 enemaccbY.remove(enemaccbY.size()-1);
			 if (enembY.get(0)>Player.y) {
				 enemaccbY.add(0,(float)-0.8+enemaccbY.get(0));
			 }
			 else if (enembY.get(0)<=Player.y) {
				 enemaccbY.add(0,(float)0.8+enemaccbY.get(0));
			 }
			
			 if (enemaccbX.get(0)>10) {
				 enemaccbX.set(0,(float)10);
				}
			 if (enemaccbX.get(0)<-10) {
				 enemaccbX.set(0,(float)-10);
				}
			 if (enemaccbY.get(0)<-10) {
				 enemaccbY.set(0,(float)-10);
				}
			 if (enemaccbY.get(0)>10) {
				 enemaccbY.set(0,(float)10);
				}
    }
	public void Boss2Melee(Player p) {
		
		for (int x=0;x<enembX.size()-1;x++) {
			if (Hitbox.MeleeContact(enembX.get(x), enembY.get(x), p.x, p.y, r-20)) {
				if (x==0) {
				p.Hit(2000);
				
				}
				else {
					p.Hit(100);
				}
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
		}
		else if (rando==2) {
			w=d.random(0,1920);
		}
		else if (rando==3) {
			w=d.random(0,1920);
		}
		float[] data= {w,h};
		return data;
	}
}
