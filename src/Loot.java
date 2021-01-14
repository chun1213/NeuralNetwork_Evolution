import processing.core.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Loot {
	protected final PApplet d;
	int num;
	int rare;
	
	public static float length;
	public static float damage;
	public static float health;
	public static float defence;
	String[] names = new String[] {"Triforce","Master Sword","Exalted Falcion","The People", "Door to Avalon"};
	String[] names2 = new String[] {"The Will","The Way","The Means","Ocarina of Time","Mossy Sword","Kaiser's Judgement","Triforce of Wisdom","Triforce of Courage","Triforce of Power","Fire Emblem","Falcion"};
	String[] names3 = new String[] {"Diamond Sword","Terra Blade","Reaper's Scyth","Mila's Blessing","Elucidator"};
	String[] names4 = new String[] {"Masamune","Titanium Sword","Bloodied Blade","King's Blade"};
	String[] names5 = new String[] {"Knight's Honor","Helmet","Iron Sword","Aprentice Sword","Stone Sword","Nintendo Labo"};
	float ldamage;
	float llength;
	float ldefence;
	float lhealth;
	int equip=0;
	String na;
	PImage img;
	public static float type=1;
	float ltype=1;
	public Loot(PApplet pa) {
			d = pa;
	               
		}
	 public Loot(PApplet pa,String name,float l,float da,float de,float h,PImage i,int eq,float t,int r,int n){
	        d=pa;
	        
	        na=name;
	        llength=l;
	        ldamage=da;
	        ldefence=de;
	        lhealth=h;
	        img=i;
	        equip=eq;
	        ltype=t;
	        rare=r;
	     	num=n;
	       
	       
	    } 
	 
	 public PImage getImg() {
		 return img; 
	 }
	 public int GetNum() {
		 return num;
	 }
	 
	 public int getrare() {
		 return rare;
	 }
	 public int getIndex() {
		 if (rare==1) {
			 return num;
		 }
		 else if (rare==2) {
			 return names.length+num;
		 }
		 else if (rare==3) {
			 return names.length+names2.length+num;
		 } 
		 else if (rare==4) {
			 return names.length+names2.length+names3.length+num;
		 }
		 else if (rare==5){
			 return names.length+names2.length+names3.length+names4.length+num;
		 }
		 else if (rare==0){
			 return names.length+names2.length+names3.length+names4.length+names5.length+num;
		 }
		 else {
			 return-1;
		 }
		 
	 }
	
	
	 public String getname() {
		
		 return na;
	 }
	 
	 public static float[] getStats(float rare, float num) {
		// length, damage defence health
		 length=0;
		 damage=0;
		 defence=0;
		 health=0;
		 type=1;
		 if (rare==1) {
			 
	   		 if (num==0) {//triforce
	   			health=500;
	   			type=2;
	   			damage=50;
	   			defence=20;
	   		 }
	   		 if (num==1) {//Mastersword
	   			 
	   			 length=80;
	   			 damage=150;
	   			 defence=50;
	   			
	   		 }
	   		 if (num==2) { //Exalted falcion
	   			 length=90; 
	   			 damage=200;
	   			 defence=40;
	   		 }
	   		 if (num==3) {//The people
	   			 length=120;
	   			 damage=120;
	   			 defence=35;
	   		 }
	   		 if (num==4) {//The avalon
	   			 type=2;
	   			 health=200;
	   			 defence=50;
	   		 }
	   		 
	   	 }
	   	 //"The Will","The Way","The Means","Ocarina of Time","Mossy Sword","Kaiser's Judgement","Triforce of Wisdom","Triforce of Courage","Triforce of Power","Fire Emblem","Falcion"
	   	 //"Triforce of Wisdom","Triforce of Courage","Triforce of Power","Fire Emblem","Falcion"
	   	 else if (rare==2) {
	   		if (num==0) { //The Will
	 			 length=70;
	 			 damage=140;
	 			 defence=35;
	 		 }
	   		else if (num==1) { //The Way
	 			 length=70;
	 			 damage=60;
	 			 defence=40;
	 			 
	 		 }
	   		else if (num==2) { //The Means
	 			 length=120;
	 			 damage=60;
	 			 defence=30;
	 		 }
	   		else if (num==3) { //Ocarina of Time
	   			type=2;
	   		}
	   		else if (num==4) { //Mossy Sword
				 length=80;
				 damage=50;
				 defence=40;
			 }
	   		else if (num==5) { //Kaiser's Judgement
				 length=90;
				 damage=90;
				 defence=45;
			 }
	   		else if (num==6) { //Triforce of Wisdom
	   			health=100;
	   			type=2;
			 }
	   		else if (num==7) { //Triforce of Courage
	   			health=200;
	   			type=2;
	   		}
	   		else if (num==8) { //Triforce of Power
	   			health=200;
	   			type=2;
			 }
	   		else if (num==9) { //Fire Emblem
	   			type=2;
			 }
	   		else if (num==10) {//Falcion
	   			length=90;
	   			damage=110;
	   			defence=40;
	   		}
	   	 }
	   	//"Diamond Sword","Terra Blade","Reaper's Scyth","Mila's Blessing","Elucidator"};
	   	 else if (rare==3) {
	   		 if (num==0) { //Diamond Sword
	  			 length=65;
	  			 damage=90;
	  			 defence=25;
	  		 }
	   		 else if (num==1) { //Terra Blade
	  			 length=70;
	  			 damage=80;
	  			 defence=25;
	  		 }
	   		 else if (num==2) { //Reaper's Scyth
	  			 length=120;
	  			 damage=70;
	  			 defence=20;
	  		 }
	   		 else if (num==3) { //Mila's Blessing
	  			 health=100;
	  			type=2;
	  		 }
	   		 else if (num==4) { //Elucidator
	 			 length=90;
	 			 damage=75;
	 			 defence=40;
	 		 }
	  		 
	  		 
	  	 }
	   	 else if (rare==4) {
	   		//"Masamune","Titanium Sword","Bloodied Blade","King's Blade"};
	   		if (num==0) { //Masamune
	 			 length=65;
	 			 damage=40;
	 			 defence=50;
	 		 }
	  		 else if (num==1) { //Titanium Sword
	 			 length=80;
	 			 damage=70;
	 			 defence=35;
	 		 }
	  		 else if (num==2) { //Bloodied Blade
	 			 length=80;
	 			 damage=60;
	 			 defence=35;
	 		 }
	  		 else if (num==3) { //Kings Blade
	  			 length=100;
	  			 damage=80;
	  			 defence=30;
	 		 }
	  		 
	   	 }
	   	else if (rare==5) {
	   		//"Knight's Honor","Helmet","Iron Sword","Aprentice Sword","Stone Sword","Nintendo Labo"
	   		if (num==0) { //Knight's Honor
				 length=75;
				 damage=50;
				 defence=40;
			 }
	 		 else if (num==1) { //Helmet
				 health=50;
				 type=2;
			 }
	 		 else if (num==2) { //Iron Sword
				 length=65;
				 damage=40;
				 defence=25;
			 }
	 		 else if (num==3) { //Aprentice Sword
	 			 length=70;
	 			 damage=30;
	 			 defence=20;
			 }
	 		 else if (num==4) { //StoneSword
	 			 length=65;
	 			 damage=30;
	 			 defence=25;
	 		 }
	 		 else if (num==5) {//Nintendo Labo
	 			 defence=10;
	 			type=2;
	 		 }
	 		 
	 	 }
		 
		 float[] arr= {length,damage,defence,health,type};
			 return arr;
	 }
	 public float[] setStats() {
		 float[] arr= {llength,ldamage,ldefence,lhealth};
		 return arr;
	 }
	 public int GetEquip() {
		 return equip;
	 }
	 public void Equip() {
		 equip=1;
	 }
	 public void Equip2() {
		 equip=2;
	 }
	 public void UnEquip() {
		 equip=0;
	 }
	 }
