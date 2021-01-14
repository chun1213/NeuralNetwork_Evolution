import processing.core.*;
public class Text {
	protected final PApplet d;
	float x;
	float y;
	int timer;
	String damage;
	int Size;
	int type;
	float b;
	float m;
	float width;
	float color; 
	float x2,y2;
	 public Text(PApplet pa,float x1,float y1,String dam,int time,int size,int t){
	        d=pa;
	        damage=dam;
	        x=x1+d.random(-25,25);
	        y=y1+d.random(-25,25);
	        timer=time;
	        Size=size;
	        type=t;
	 }
	 public Text(PApplet pa,float x1,float y1,float x2,float y2,int time,int t){
	        d=pa;
	        type=t;
	        timer=time;
	        if (x1 != x2) {
	        	
	        m = (y2 - y1) / (x2 - x1); // rise / run, gives us the line slope
	        b = y1 - m * x1; // solves offset by assuming it's 0 and accounting for the error at X=x1
	        }
	        
	 }
	 public Text(PApplet pa,float x1,float y1,float x22,float y22,int time,int t,float w){
	        d=pa;
	        type=t;
	        timer=time;
	        
	        width=w;
	        x=x1;
	        y=y1;
	        x2=x22;
	        y2=y22;
	        
	 }
	 public void display() {
		 timer=timer-1;
		 if (type==1) {
		 d.fill(250, 0, 0);
 		d.textSize(Size);
 		d.text(damage,x,y);
		 }
		 else if (type==2) {
			 d.stroke(1);
			 d.line(0, b, d.width, m * d.width + b); //draw a line starting at (0, f(0)) and ending at (width, f(width)) for the line equation
			 d.stroke(0);
			 d.strokeWeight(1);
		 }
		 else if (type==3) {
			 
			 d.stroke(204, 102, 0);
			 d.strokeWeight(width);
			 d.line(x,y,x2,y2); //draw a line starting at (0, f(0)) and ending at (width, f(width)) for the line equation
			 d.stroke(0);
			 d.strokeWeight(1);
		 }
	 }
	 public int GetTimer() {
		 return timer;
	 }
}
