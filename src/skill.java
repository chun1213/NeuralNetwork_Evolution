import processing.core.PApplet;
import java.util.ArrayList;

public class skill {
	protected final PApplet d;
	Player p;
	Map m;
	int passivet1=0;
	int passivet2=0;
	int activet1=0;
	int activet2=0;
	int invince =0;
	float defencebuff1=0;
	float defencebuff2=0;
	float damagebuff1=0;
	float damagebuff2=0;
	int timer1=-1;
	int timer2=-1;
	int actTimer1=-1;
	int actTimer2=-1;
	float olddamge;
	float olddefence;
	int Stacks=0;
	int heal;
	int bulspeed=0;
	float buldam=0;
	float oldhealth;
	float bulletrad;
	public skill(PApplet pa, Player pl,Map ma){
		d=pa;
		p=pl;
		m=ma;
	}
	public void GetSkill(ArrayList<Loot> equip) {
		oldhealth=p.phealth;
		olddamge=p.pdamage;
		olddefence=p.pdefence;
		
		
		if (equip.get(0).getname()=="Reaper's Scyth"||equip.get(1).getname()=="Reaper's Scyth") {
			passivet1=1;
			damagebuff1=1;
			
		}
		if (equip.get(0).getname()=="Bloodied Blade"||equip.get(1).getname()=="Bloodied Blade") {
			passivet1=2;
			heal=1;
			
		}
		if (equip.get(0).getname()=="Master Sword"||equip.get(1).getname()=="Master Sword") {
			passivet1=3;
			bulspeed=150;
			buldam=90;
			bulletrad=30;
		}
		if (equip.get(0).getname()=="The People"||equip.get(1).getname()=="The People") {
			passivet1=3;
			bulspeed=50;
			buldam=400;
			bulletrad=90;
		}
		if (equip.get(0).getname()=="Terra Blade"||equip.get(1).getname()=="Terra Blade") {
			passivet1=3;
			bulspeed=200;
			buldam=20;
			bulletrad=30;
		}
		
		if (equip.get(0).getname()=="Exalted Falcion"||equip.get(1).getname()=="Exalted Falcion") {
			passivet1=4;
			damagebuff1=20;
			activet1=1;
			actTimer1=10;
		}
		if (equip.get(0).getname()=="Falcion"||equip.get(1).getname()=="Falcion") {
			passivet1=4;
			damagebuff1=5;
		}
		if (equip.get(0).getname()=="Kaiser's Judgement"||equip.get(1).getname()=="Kaiser's Judgement") {
			activet1=2;
		}
		
		
		
		
		
		
		
		
		if (equip.get(0).getname()=="Ocarina of Time"||equip.get(1).getname()=="Ocarina of Time") {
			passivet2=1;
			invince=invince+180;
			
		}
		else if (equip.get(0).getname()=="Door to Avalon"||equip.get(1).getname()=="Door to Avalon") {
			passivet2=1;
			invince=invince+360;
			damagebuff2=damagebuff2+100;
		
			
		}
	}
	public void ActPassive() {
		p.pdamage=olddamge;
		p.pdefence=olddefence;
		if (passivet1==1) {
			p.pdamage=p.pdamage+damagebuff1*Stacks;
		}
		
		
		
		
		
		if (passivet2==1) {
			
			if (p.phealth<=0) {
				
				p.phealth=1;
				if (timer2<0) {	
				timer2=invince;	
				p.pinvince=p.pinvince+invince;
				
				}
				}
			if (timer2>0) {
				
				p.pdamage=p.pdamage+damagebuff2;
				timer2=timer2-1;
				
			}
			else if (timer2==0){
				passivet2=0;
				
			}
		}
		if (passivet1==4) {
			p.pdamage=p.pdamage+damagebuff1*(oldhealth/p.phealth+1);
		}
		
	}
	
	public void KillPassive() {
		if (passivet1==1) {
			Stacks=Stacks+1;
		}
		if (passivet1==2) {
			if (p.phealth<oldhealth) {
			p.phealth=p.phealth+heal;;
			}
		}
		
	}
	public void ClickPassive() {
		if (passivet1==3) {
			if (p.phealth==oldhealth) {
			m.bullets.add(new Bullets(d,p.x,p.y,d.mouseX,d.mouseY,bulspeed,buldam,bulletrad));
						}
		}
	}
	public void SkillActive(ArrayList<Enemy> enem) {
		
		if (activet1==1) {
			p.mana=0;
			float slope;
			if (d.mouseX==p.x) {
				slope=0;
			}
			
			else {
				slope=-d.pow(((d.mouseY-p.y)/(d.mouseX-p.x)),-1);
			}
			System.out.println(slope);
			if (slope>20||slope<-20) {
				slope=10;
			}
			
			float y1=d.mouseY-2000*slope;
			float y2=d.mouseY+2000*slope;
			float x1=d.mouseX-2000;
			float x2=d.mouseX+2000;
			
			m.popups.add(new Text(d,x1,y1,x2,y2,20,3,200));
;
			
			for (int i=0;i<enem.size();i++) {
				if (lineCircle(x1,y1,x2,y2,enem.get(i).GetX(),enem.get(i).GetY(),enem.get(i).GetRad()+100)) {
					enem.get(i).Hit(1200);
				}
			}
		}
		else if (activet1==2) {
			p.mana=0;
			float slope;
			if (d.mouseX==p.x) {
				slope=10;
			}
			
			else {
				slope=((d.mouseY-p.y)/(d.mouseX-p.x));
			}
			System.out.println(slope);
			if (slope>15) {
				slope=15;
			}
			else if(slope<-15) {
				slope=-15;
			}
			float y1=p.y;
			
			float x1=p.x;
			float x2;
			float y2;
			if (d.mouseX-p.x<0) {
				x2=d.mouseX-2000;
				y2=d.mouseY+2000*-slope;
			}
			else {
				x2=d.mouseX+2000;
				y2=d.mouseY+2000*slope;
			}
			m.popups.add(new Text(d,x1,y1,x2,y2,20,3,100));
;
			
			for (int i=0;i<enem.size();i++) {
				if (lineCircle(x1,y1,x2,y2,enem.get(i).GetX(),enem.get(i).GetY(),enem.get(i).GetRad()+50)) {
					enem.get(i).Hit(850);
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
