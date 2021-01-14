
import processing.core.*;
	import java.util.ArrayList;
	import java.util.Random;
	import static processing.core.PApplet.dist;
	import processing.event.KeyEvent;
	import java.lang.Math;
	
	import java.util.Arrays;
public class Inventory {
	protected final PApplet d;
	int formx=0;
	int formy=0;

	int offsety=70;
	ArrayList<Loot> loot;
	ArrayList<Loot> equiped;
	
	PImage EqImage;
	PImage EqImage2;
	public static int count1;
	public static int count4;
	public static int count3;
	public static int count2;
	public static int count5;
	String[] names = new String[] {"Triforce","Master Sword","Exalted Falcion","The People", "Door to Avalon"};
	String[] names2 = new String[] {"The Will","The Way","The Means","Ocarina of Time","Mossy Sword","Kaiser's Judgement","Triforce of Wisdom","Triforce of Courage","Triforce of Power","Fire Emblem","Falcion"};
	String[] names3 = new String[] {"Diamond Sword","Terra Blade","Reaper's Scyth","Mila's Blessing","Elucidator"};
	String[] names4 = new String[] {"Masamune","Titanium Sword","Bloodied Blade","King's Blade"};
	String[] names5 = new String[] {"Knight's Honor","Helmet","Iron Sword","Aprentice Sword","Stone Sword","Nintendo Labo"};
	float plength=0;
	float pdamage=0;
	float pdefence=0;
	float phealth=0;
	float wlength=0;
	float wdamage=0;
	float wdefence=0;
	float whealth=0;
	int indexEq=0;
	int indexEq2=0;
	float plength2=0;
	float pdamage2=0;
	float pdefence2=0;
	float phealth2=0;
	float coins;
	public Inventory(PApplet pa){
		d=pa;
		loot= new ArrayList<Loot>();
		equiped= new ArrayList<Loot>();
		pdamage=30;
		plength=50;
		loot.add(new Loot(d,"Wood Sword",0,0,0,0,main.images[5][0],1,1,0,0));
		loot.add(new Loot(d,"Shirt",0,0,0,0,main.images[5][1],2,2,0,1));
		equiped.add(new Loot(d,"Wood Sword",0,0,0,0,main.images[5][0],1,1,0,0));
		equiped.add(new Loot(d,"Shirt",0,0,0,0,main.images[5][1],2,2,0,1));
		coins=50;
	}
		
	public void Display() {
		d.background(35, 16, 41);
    	d.fill(255,255,255);
    	d.text("Inventory",400,0+offsety);
    	d.fill(247, 234, 52);
    	d.text(count1,100,100);
    	d.text("Legendary",100,120);
    	d.fill(217, 40, 237);
    	d.text(count2,100,200);
    	d.text("Mythical",100,220);
    	d.fill(5, 133, 252);
    	d.text(count3,100,300);
    	d.text("Rare",100,320);
    	d.fill(26, 148, 7);
    	d.text(count4,100,400);
    	d.text("Uncommon",100,420);
    	d.fill(142, 156, 140);
    	d.text(count5,100,500);
    	d.text("Common",100,520);
    	d.fill(247, 234, 52);
    	d.text("Coins"+coins,100,700);
    	for (int i=0;i<loot.size();i++) {
    		if (i>=7 && i%7==0) {
    			formy=formy+100;
    			formx=0;
    		}
    		formx=formx+1;
    		d.image(loot.get(i).getImg(),150+100*formx,formy+70+offsety);
//    		
    		d.textSize(10);
    		d.fill(142, 156, 140);
    		d.text(loot.get(i).getname(),150+100*formx,formy+110+offsety);
    		if (loot.get(i).GetEquip()==1||loot.get(i).GetEquip()==2) {
    			d.fill(245, 221, 66);
    			d.text("Equipped",150+100*formx,formy+120+offsety);
    		}
    		d.textSize(20);
    		
    		
    	}
    	formy=0;
    	formx=0;
    	
    	d.fill(255);
		d.rect(100,d.height-100,200,50);
		d.fill(0);
		d.text("Back",100,d.height-100 );
		d.fill(255);
		d.rect(100,d.height-300,200,50);
		d.fill(0);
		d.text("Roll(10 Coins)",100,d.height-300 );
		
		
		//Equip
		if(indexEq!=-1) {
		d.image(equiped.get(0).getImg(),d.width-200,d.height-400);
		}
		if(indexEq2!=-1) {
			d.image(equiped.get(1).getImg(),d.width-200,d.height-600);
			}
		d.fill(0, 140, 40);
		d.text("Length "+(plength+plength2),d.width-200,d.height-300);
		d.text("Damage "+(pdamage+pdamage2),d.width-200,d.height-250);
		d.text("Endurance "+(phealth+100+phealth2),d.width-200,d.height-200);
		d.text("Parry "+(pdefence+pdefence2),d.width-200,d.height-150);
		
	}
	public void Roll() {
		System.out.println(equiped.size());
		for (int i=0;i<loot.size();i++) {
			if (i>=7 && i%7==0) {
    			formy=formy+100;
    			formx=0;
    		}
			formx=formx+1;
			
			if (dist(d.mouseX,d.mouseY,150+100*formx,70+offsety+formy)<50) {
				System.out.println("updated");
				float[] data= loot.get(i).setStats();
				if (loot.get(i).ltype==1) {
					for (int x=0;x<loot.size();x++) {
						if (loot.get(x).equip==1) {
							equiped.remove(0);
							loot.get(x).UnEquip();
						}
					}
					equiped.add(0,loot.get(i));
					loot.get(i).Equip();
					EqImage=loot.get(i).getImg().get();
					EqImage.resize(125, 125);
					plength=data[0];
					pdamage=data[1];
					pdefence=data[2];
					phealth=data[3];
					indexEq=i;
					
					
				}
				else if (loot.get(i).ltype==2) {
					for (int x=0;x<loot.size();x++) {
						if (loot.get(x).equip==2) {
							equiped.remove(1);
							loot.get(x).UnEquip();
						}
					}
					equiped.add(1,loot.get(i));
					loot.get(i).Equip2();
					
					EqImage2=loot.get(i).getImg().get();
					EqImage2.resize(125, 125);
					plength2=data[0];
					pdamage2=data[1];
					pdefence2=data[2];
					phealth2=data[3];
					indexEq2=i;
				}
				
				
				
			}
			
			
		}
		formy=0;
    	formx=0;
		if (dist(d.mouseX,d.mouseY,100,d.height-100)<50){
			main.game=0;
			
		}
		else if (dist(d.mouseX,d.mouseY,100,d.height-300)<50&& coins>=10){
			coins=coins-10;
			int rarity=Generate();
	    	int wep=Generate2(rarity);
	    	String name=getname(rarity,wep);
	    	float[] data= Loot.getStats(rarity,wep);
	    	for (int x =0;x<4;x++) {
	    		System.out.println(data[x]);
	    	}
	    
	    	loot.add(new Loot(d,name,data[0],data[1],data[2],data[3],main.images[rarity-1][wep],0,data[4],rarity,wep));
	    	
		}
		formx=0;
		formy=0;
    	
		
		
		
		
	}
	public int Generate() {
		float num=d.random(0,50);
		if (num>48) {
			count1=count1+1;
			return(1);
		}
		else if (num>45) {
			count2=count2+1;
			return(2);
		}
		else if (num>40) {
			count3=count3+1;
			return(3);
		}
		else if (num>30) {
			count4=count4+1;
			return(4);
		}
		else {
			count5=count5+1;
			return(5);
		}
	   
		 }
	 public int Generate2(int rare) {
		 if (rare==1) {
		 int num=(int) (d.random(0,names.length));
		 return num;
		 }
		 else if (rare==2) {
			 int num=(int) (d.random(0,names2.length));
			 return num;
		 }
		 else if (rare==3) {
			 int num=(int) (d.random(0,names3.length));
			 return num;
		 }
		 else if (rare==4) {
			 int num=(int) (d.random(0,names4.length));
			 return num;
		 }
		 else {
			 int num=(int) (d.random(0,names5.length));
			 return num;
		 }
	 }
	 public String getname(int rare,int num) {
		 if (rare==1) {
		 String name =names[num];
		 return name;
		 }
		 if (rare==2) {
			 String name =names2[num];
			 return name;
		 }
		 if (rare==3) {
			 String name =names3[num];
			 return name;
		 }
		 if (rare==4) {
			 String name =names4[num];
			 return name;
		 }
		 else {
			 String name =names5[num];
			 return name;
		 }
	 }
	 public float[] Equip(){
		 float[] data=new float[4];
			data[0]=plength+plength2;
			data[1]=pdamage+pdamage2;
			data[2]=pdefence+pdefence2;
			data[3]=phealth+phealth2;
			
			return data;
	 }
	 
	public void Hover() {
		for (int i=0;i<loot.size();i++) {
    		if (i>=7 && i%7==0) {
    			formy=formy+100;
    			formx=0;
    		}
    		formx=formx+1;
    		
    		
    		if (dist(d.mouseX,d.mouseY,150+100*formx,70+offsety+formy)<50) {
				
				float[] data= loot.get(i).setStats();
				wlength=data[0];
				wdamage=data[1];
				wdefence=data[2];
				whealth=data[3];
				d.fill(255);
				d.rect(150+100*formx,260+offsety+formy,400,320);
				d.fill(0);
				d.textAlign(d.LEFT);
				d.text(loot.get(i).getname(),100*formx-35,140+offsety+formy);
				d.textSize(15);
				int yblock=0;
				if (wlength!=0) {
				d.text("Length "+wlength,100*formx-35,170+offsety+formy+yblock);
				 yblock= yblock+25;
				}
				if (wdamage!=0) {
				d.text("Damage "+wdamage,100*formx-35,170+offsety+formy+yblock);
				 yblock= yblock+25;
				}
				if (whealth!=0) {
				d.text("Endurance "+whealth,100*formx-35,170+offsety+formy+yblock);
				 yblock= yblock+25;
				}
				if (wdefence!=0) {
				d.text("Parry "+wdefence,100*formx-35,170+offsety+formy+yblock);
				 yblock= yblock+25;
				}
				
				d.rectMode(d.CORNER);
				d.text(main.lines[loot.get(i).getIndex()],100*formx-35,170+offsety+formy+yblock+10,380,300);
				d.rectMode(d.CENTER);
				d.textAlign(d.CENTER);
				d.textSize(20);
				
				
				
			}
    	}
		formy=0;
    	formx=0;
	}
	
	
}
