

import processing.core.*;
import processing.event.MouseEvent;
import processing.event.KeyEvent;
import processing.sound.*;
import java.io.*;
import static processing.core.PApplet.dist;

import java.util.ArrayList;
import java.util.Arrays;
import sprites.*;
import sprites.maths.*;
import sprites.utils.*;
import java.util.prefs.*;

public class main extends PApplet {
	Loot l;
	Bullets b;
	Evolution ev;
	
	public static String[] lines;
	static File storage=new File("brain.txt");
	ArrayList<Player> players;
	Sound s;
	int init=0;
	int profile=0;
	public static PImage weapon;
	public static PImage Mace;
	public static PImage Gun;
	PImage Legendary;
	PImage Mythical;
	PImage Rare;
	PImage Uncommon;
	PImage Common;
	PImage StoneSwords;
	PImage Elucidator;
	PImage Falcion;
	PImage LightSaber;
	PImage Triforce;
	PImage MasterSword;
	PImage ExaltedFalcion;
	PImage ThePeople;
	PImage Avalon;
	PImage TheWill;
	PImage TheMeans;
	PImage TheWay;
	PImage OcarinaOfTime;
	PImage MossySword;
	PImage KaisersJudgement;
	PImage TriforceOfPower;
	PImage TriforceOfWisdom;
	PImage TriforceOfCourage;
	PImage FireEmblem;
	PImage Falcion1;
	PImage Elucidator1;
	PImage DiamondSword;
	PImage TerraBlade;
	PImage MilasBlessing;
	PImage ReaperScythe;
	PImage Masamune;
	PImage TitaniumSword;
	PImage BloodiedBlade;
	PImage KingsBlade;
	PImage KnightsHonor;
	PImage Helmet;
	PImage IronSword;
	PImage AprenticeSword;
	PImage StoneSword;
	PImage NintendoLabo;
	PImage Shirt;
	PImage WoodSword;
	
	static double fitness = 0;
	PImage Shot1;
	PImage Shot2;
	PImage Shot3;
	PImage Shot4;
	PImage Shot5;
	
	public static PImage MainBack;
	public static PImage ShrineBack;
	public static PImage SpaceBack;
	
	/////////////////////enemies
	public static PImage DOGHead;
	public static PImage DOGBody;
	public static PImage DOGTail;
	////////////////////////////////

	
	///////////////SOUND
	public static SoundFile CurrentTrack;
	public static SoundFile Boss1theme1;
	public static SoundFile Boss2theme1;
	public static SoundFile Boss2theme2;
	public static SoundFile theme1;
	public static SoundFile theme2;
	public static SoundFile theme3;
	public static SoundFile theme4;
	///////////////////
	
	///////////SPRITESHEET
	static Sprite spritesheet;
	static Sprite spritesheetlaser;
	static Sprite spritesheetarrow;
	static Sprite spritesheetDr;
	static Sprite spritesheetDl;
	////////////////////
	
	
	Player p;
	Enemy e;
	Hitbox h;
	Inventory i;
	Map m;
	Map Mp;
	 public static PImage[][] images;
	 public static SoundFile[] Soundtracks;
	 PFont custom;
	
	
	public static int game=0;
	
    // The argument passed to main must match the class name
	
	
	
	
	
	
	//AI????????????????????????????
	int init2=1;
	ArrayList<NeuralNetwork> population = new ArrayList<NeuralNetwork>();
	private static int generationSize = 200;
	int nOfInputs=6; 
	int nOfHiddenLayers=2;
	int nOfHiddenNodes= 8;
	int nOfOutputs=6;
	static int deathcounter=0;
	

    int numberOfGenerations = 200;
    
    
     
	
    int Pop=0;
	int Evo=0;
	
	int avgcounter=0;
	float psum=0;
	int surviving=50; //recommend top 25%
	double mutationRate =0.02;
	
	
	
    public static void main(String[] args) {
        PApplet.main("main");
        
    }

    // method for setting the size of the window
    @Override
    public void settings(){
    	
    
    	smooth(3);
    	e= new Enemy(this);
    	ev=new Evolution();
//    	w= new Weapon(this);
    	size(1920, 1080,P2D);
    	//fullScreen();
        
    }

    // identical use to setup in Processing IDE except for size()
    @Override
    public void setup(){
    	
    	frameRate(400);
        background(0);
        stroke(255);
        strokeWeight(1);
        fill(255);
        lines = loadStrings("Wep_Dis.txt");
        printArray(lines);
       custom = createFont("A Love of Thunder.ttf",20);
        textFont(custom);
        
        System.out.println(lines);
        spritesheetDr=new Sprite(this, "Dashright.png", 6, 1,0);
        spritesheetDr.setFrameSequence(0, 3, 0.3);
        spritesheetDl=new Sprite(this, "Dashleft.png", 6, 1,0);
        spritesheetDl.setFrameSequence(0, 3, 0.3);
        spritesheet=new Sprite(this, "Spite.png", 9, 1,0);
        spritesheet.setFrameSequence(0, 3, 0.1);
        spritesheetlaser=new Sprite(this, "Laser.png", 12, 1,0);
        spritesheetlaser.setFrameSequence(0, 3, 0.3);
        spritesheetarrow=new Sprite(this, "Arrow.png", 7, 1,0);
        spritesheetarrow.setFrameSequence(0, 3, 0.1);
        Boss1theme1 = new SoundFile(this, "Boss1Theme1.wav");
        Boss2theme1 = new SoundFile(this, "Boss2Theme1.wav");
        Boss2theme2 = new SoundFile(this, "Boss2Theme2.wav");
        theme1 = new SoundFile(this, "Theme1.wav");
        theme2 = new SoundFile(this, "Theme2.wav");
        theme3 = new SoundFile(this, "Theme3.wav");
        theme4 = new SoundFile(this, "Theme4.wav");
        s = new Sound(this);
        s.volume((float) 0.2);
        players= new ArrayList<Player>();
        
        textAlign(CENTER);
        
        //////////////BACKGROUNDS
        MainBack=loadImage("mainback.jpg");
        ShrineBack=loadImage("forestarena.png");
        SpaceBack=loadImage("Space.png");
        ///////////////////////
        
        
        
        Legendary=loadImage("legend.png");
        Mythical=loadImage("mythical.jpg");
        Rare=loadImage("Rare.png");
        Uncommon=loadImage("uncommon.png");
        Common=loadImage("common.png");
        StoneSwords=loadImage("Stone_Sword.png");
        
        Elucidator=loadImage("Elucidator.png");
        Falcion=loadImage("Falcion.png");
        LightSaber=loadImage("Lightsaber.png");
        Triforce=loadImage("Triforce.jpg");
        MasterSword=loadImage("MasterSword.png");
        ExaltedFalcion=loadImage("ExaltedFalcion.jpg");
        ThePeople=loadImage("ThePeople.png");
        Avalon=loadImage("Avalon.png");
        TheWay=loadImage("TheWay.jpg");
        TheMeans=loadImage("TheMeans.jpg");
        TheWill=loadImage("TheWill.jpg");
        OcarinaOfTime=loadImage("OcarinaOfTime.jpg");
        MossySword=loadImage("MossySword.jpg");
        KaisersJudgement=loadImage("KaisersJudgement.png");
        TriforceOfPower=loadImage("TriforceOfPower.jpg");
        TriforceOfWisdom=loadImage("TriforceOfWisdom.jpg");
        TriforceOfCourage=loadImage("TriforceOfCourage.jpg");
        FireEmblem=loadImage("FireEmblem.png");
        Falcion1=loadImage("Falcion1.png");
        Elucidator1=loadImage("Elucidator1.png");
        DiamondSword=loadImage("DiamondSword.png");
        TerraBlade=loadImage("TerraBlade.png");
        MilasBlessing=loadImage("MilasBlessing.png");
        ReaperScythe=loadImage("ReaperScythe.png");
        Masamune=loadImage("Masamune.jpg");
        TitaniumSword=loadImage("TitaniumSword.jpg");
        BloodiedBlade=loadImage("BloodiedBlade.jpg");
        KingsBlade=loadImage("KingsBlade.png");
        KnightsHonor=loadImage("KnightsHonor.jpg");
        Helmet=loadImage("Helmet.jpg");
        IronSword=loadImage("IronSword.png");
        AprenticeSword=loadImage("AprenticeSword.jpg");
        StoneSword=loadImage("StoneSword.png");
        NintendoLabo=loadImage("NintendoLabo.png");
        WoodSword=loadImage("WoodSword.png");
        Shirt=loadImage("Shirt.png");
        
        DOGHead=loadImage("DOGHead.png");
        DOGBody=loadImage("DOGBody.png");
        DOGTail=loadImage("DOGTail.png");
        
        Shot1=loadImage("Screenshot_1.png");
        Shot2=loadImage("Screenshot_2.png");
        Shot3=loadImage("Screenshot_3.png");
        Shot4=loadImage("Screenshot_4.png");
        Shot5=loadImage("Loop.png");
        
        
        
        Mace=loadImage("mace.png");
        Gun=loadImage("gun.png");
        
        Gun.resize(0, 50);
        Mace.resize(0,180);
        Legendary.resize(50,50);
        Mythical.resize(50,50);
        Rare.resize(50,50);
        Uncommon.resize(50,50);
        Common.resize(50,50);
        StoneSword.resize(0,80);
        Elucidator.resize(0,120);
        Falcion.resize(0,120);
        LightSaber.resize(0,120);
        
        MainBack.resize(width,height);
        ShrineBack.resize(width,height);
        SpaceBack.resize(width,height);
        Shot1.resize(500,350);
        Shot2.resize(500,350);
        Shot3.resize(500,350);
        Shot4.resize(500,350);
        Shot5.resize(500,350);
        
        images = new PImage[][]{
    		{Triforce,MasterSword,ExaltedFalcion,ThePeople,Avalon},	
    		{TheWill, TheWay,TheMeans,OcarinaOfTime,MossySword,KaisersJudgement,TriforceOfWisdom,TriforceOfCourage,TriforceOfPower,FireEmblem,Falcion1},
    		{DiamondSword,TerraBlade ,ReaperScythe,MilasBlessing,Elucidator1},
    		{Masamune,TitaniumSword,BloodiedBlade,KingsBlade},
    		{KnightsHonor,Helmet,IronSword,AprenticeSword,StoneSword,NintendoLabo},
    		{WoodSword,Shirt},
        };
    Soundtracks = new SoundFile[]{
    theme1,theme2,theme3,theme4		
    };
    players.add(new Player(this));
        for (int row = 0; row < images.length; row++) {
            for (int col = 0; col < images[row].length; col++) {
               if (images[row][col] != null) {
            	images[row][col].resize(50,50);
               }
            }
         }
        
        
//        
        
       
        
        weapon=Falcion;
        imageMode(CENTER);
        rectMode(CENTER);
        textAlign(CENTER);
        
    }

    // identical use to draw in Processing IDE
    @Override
    public void draw(){
    	
    	if (CurrentTrack==null) {
	    CurrentTrack=Soundtracks[(int) random(0,Soundtracks.length)];
	    CurrentTrack.play();
    	}
    	if (!CurrentTrack.isPlaying()) {
    		CurrentTrack=Soundtracks[(int) random(0,Soundtracks.length)];
    		CurrentTrack.play();
    	}
    	if (init2==1) {
    		for (int i=0;i<generationSize;i++) {
    			population.add(new NeuralNetwork(nOfInputs,  nOfHiddenLayers, nOfHiddenNodes,  nOfOutputs,1));
    	    	}
    		init2=2;
    	}
    	if (game==0) {
    		
    		textSize(20);
    		strokeWeight(1);
    		stroke(1);
    		background(MainBack);
    		
    		fill(255);
    		rect(width/2-700,height/2-200,200,50);
    		fill(0);
    		text("Play",width/2-700,height/2-200 );
    		fill(255);
    		rect(width/2-700,height/2-100,200,50);
    		fill(0);
    		text("Inventory",width/2-700,height/2-100 );
    		fill(255);
    		rect(width/2-700,height/2,200,50);
    		fill(0);
    		text("Controls",width/2-700,height/2 );
    		main.Boss2theme1.stop();
			main.Boss2theme2.stop();
			
    	}
    	else if(game==1) {
    		players.get(profile).Inv();
    	
    	}
    	else if (game==2) {
    		if (init==0) {
    			init=1;
         		Mp= new Map(this); 
         		
         	}
    		
    		
    		
           
            
            
        	
            if (Evo<numberOfGenerations){
            	
               if (Pop <generationSize) {
            	  
                	Mp.Display(players.get(profile),Mp);
                    NeuralNetwork n = (population.get(Pop));
                    text("Generation:"+ Evo,200,300);
                    text("Index:"+ Pop,200,330);
        			/////////////////////////////
        			                
                              
        			//GAME
                    double[] outputs=n.Evaluate(Mp.generateInputs(players.get(profile)));
                    for (int i=0;i<outputs.length;i++) {
                    	
                    }
               
                    
                    if (outputs[0]>0.5) {
                    	//LEFT
                    	players.get(profile).accxl=1;
                    }
                    else {
                    	players.get(profile).accxl=0;
                    }
                    if (outputs[1]>0.5) {
                    	//RIGHT
                    	players.get(profile).accxr=1;
                    }
                    else {
                    	players.get(profile).accxr=0;
                    }
                    if (outputs[2]>0.5) {
                    	//UP
                    	players.get(profile).accyt=1;
                    }
                    else {
                    	players.get(profile).accyt=0;
                    }
                    if (outputs[3]>0.5) {
                    	//DOWN
                    	players.get(profile).accyd=1;
                    }
                    else {
                    	players.get(profile).accyd=0;
                    }
                    if (outputs[4]>0.5) {
                    	//LEFTCLICK
                    	if (players.get(profile).combo==-1 && Mp!=null ) {
            				Mp.s.ClickPassive();
            			}
            			if (players.get(profile).clicked==0) {
            				
            				players.get(profile).combo=0;
            				players.get(profile).duration=0;
            				players.get(profile).newClick=0;
        	    			players.get(profile).Fire();
        	    			players.get(profile).clicked=1;
        	    			
            			}
            			
            			
            			if (players.get(profile).duration > 2 && players.get(profile).combo==1) {
            				players.get(profile).combo=2;
            				players.get(profile).duration=0;
            				
            				
            			}
                    }
                    if (outputs[5]>0.5) {
                    	//RIGHTCLICK
                    	if ( players.get(profile).combo==0 &&players.get(profile).clicked!=0) { //basic second swing
            				players.get(profile).combo=1;
            				players.get(profile).duration=0;
            				players.get(profile).newClick=0;
            			}
                		
                		else if ( players.get(profile).combo==1) { //Lunge
            				
            				players.get(profile).combo=3;
            				players.get(profile).duration=0;
            				players.get(profile).newClick=0;
            				players.get(profile).Fire();
            				players.get(profile).clicked=1;
            			}
                		else if (players.get(profile).combo==-1) { //block
                			players.get(profile).combo=4;
                		}
                    }
                    
                    
                    if (Mp.end==1) {
                    	psum+=Mp.points;
                    	avgcounter++;
                    	if (avgcounter==3) {
                    		population.get(Pop).setfitness(psum/3);
                    	 	Pop=Pop+1;
                    	 	System.out.println(psum/3);
                    	 	psum=0;
                    	 	avgcounter=0;
                    	 	
                    	}
                    	 
                     	Mp= new Map(this); 
                     		
                     	
                    	 game=2;
                    }
                    
        			
                    
                    
                }
               else {
            	   System.out.println("Generation "+Evo);
            	   
                ArrayList<NeuralNetwork> newpopulation = ev.Evolve(population,surviving,generationSize,mutationRate);
                population.clear();
                population=newpopulation;
                System.out.println("Done");
                Pop=0;
                Evo++;
               }
            }
            else {
            System.out.println("Done");
            }
    	}
    	else if(game==3) {
    		textSize(40);
    		text("Points: "+Mp.points,width/2,height/2-100);
    		text("Coins Obtained: "+Mp.points/100,width/2,height/2);
    		text("Press Spacebar to Continue",width/2,height/2+100);
    		players.get(profile).inv.coins=(float) (players.get(profile).inv.coins+Mp.points/100);
    	}
    	else if(game==4) {
    		textSize(20);
    		background(255);
    		fill(255);
    		rect(100,height-100,200,50);
    		fill(0);
    		text("Back",100,height-100 );
    		image(Shot1,300,250);
    		text("Defeat Enemies using both Mouse Buttons to Attack!",300,450);
    		image(Shot2,width/2,250);
    		text("Block enemy Attacks",width/2,450);
    		image(Shot3,width-300,250);
    		text("Obtain and equip more Powerful Weapons!",width-300,450);
    		image(Shot4,500,700);
    		text("Some weapons have special abilities to be used with SpaceBar when your Mana is full!",500,930);
    		image(Shot5,width-500,700);
    		text("Repeat for the Highest Score!",width-500,930);
    	}
}
    @Override 
    public void mousePressed() {
    	
    	if (mouseButton == RIGHT) {
    		if ( players.get(profile).combo==0 &&players.get(profile).clicked!=0) { //basic second swing
				players.get(profile).combo=1;
				players.get(profile).duration=0;
				players.get(profile).newClick=0;
			}
    		
    		else if ( players.get(profile).combo==1) { //Lunge
				
				players.get(profile).combo=3;
				players.get(profile).duration=0;
				players.get(profile).newClick=0;
				players.get(profile).Fire();
				players.get(profile).clicked=1;
			}
    		else if (players.get(profile).combo==-1) { //block
    			players.get(profile).combo=4;
    		}
			
    	}
    	if (mouseButton == LEFT) {
    		if (game==0) {
    			if (dist(mouseX,mouseY,width/2-700,height/2-200)<30){
    				game=2;
    			}
    			else if (dist(mouseX,mouseY,width/2-700,height/2-100)<30){
    				game=1;
    			}
    			else if (dist(mouseX,mouseY,width/2-700,height/2)<30){
    				game=4;
    			}
    		}
    		if (game==1) {
    			players.get(profile).Roll();
	    
    	}
    		else if(game==2) {
    			if (players.get(profile).combo==-1 && Mp!=null ) {
    				Mp.s.ClickPassive();
    			}
    			if (players.get(profile).clicked==0) {
    				
    				players.get(profile).combo=0;
    				players.get(profile).duration=0;
    				players.get(profile).newClick=0;
	    			players.get(profile).Fire();
	    			players.get(profile).clicked=1;
	    			
    			}
    			
    			
    			if (players.get(profile).duration > 2 && players.get(profile).combo==1) {
    				players.get(profile).combo=2;
    				players.get(profile).duration=0;
    				
    				
    			}
    			
    		}
    		else if(game==4) {
    			if (dist(mouseX,mouseY,100,height-100)<50){
    				game=0;
    				
    			}
    		}
    		
    	}
    
    
    }
    public void mouseReleased() {
    	if (mouseButton == RIGHT && players.get(profile).combo==4) {
    		players.get(profile).combo=-1;
    	}
    }
    public void mouseHeld() {
    	if (mouseButton == RIGHT ) {
    		players.get(profile).combo=4;
    	}
    }
    @Override
    public void mouseWheel(MouseEvent event) {
    	  float e = event.getCount();
    	  if (e==1.0) {
    		  players.get(profile).inv.offsety= players.get(profile).inv.offsety-20;
    	  }
    	  else if (e==-1.0) {
    		  players.get(profile).inv.offsety= players.get(profile).inv.offsety+20;
    	  }
    	}
 
    public void keyPressed(KeyEvent event) {
    	
    	if ((key == 'a')&& (keyPressed==true)&& game==2) {
    		
            players.get(profile).accxl=1;

        } else { // Otherwise, draw an ellipse

        }

        if ((key == 'd')&&(keyPressed==true)&& game==2) {

            players.get(profile).accxr=1;
        } else { // Otherwise, draw an ellipse

        }

        if ((key == 'w')&&(keyPressed==true)&& game==2) {
            players.get(profile).accyt=1;

        } else { // Otherwise, draw an ellipse

        }
        if ((key == 's')&&(keyPressed==true)&& game==2) {
            players.get(profile).accyd=1;

        }
        if ((key == ' ')&& (keyPressed==true)&& game==2) {
    		
            Mp.checkSkill=1;
            frameRate(60);
        }
        if ((key == 'z')&& (keyPressed==true)&& game==2) {
    		
            Mp.checkSkill=1;
            frameRate(300);
        }
        if ((key == ' ')&& (keyPressed==true)&& game==3) {
    		
            game=0;

        } 
        if ((key == 'p')&& (keyPressed==true)&& game==2) {
        	players.get(profile).phealth=1000000;
            

        } 
        if ((key == 'b')&& (keyPressed==true)&& game==2) {
        	writeToFile(storage,population);
            

        } 
        if ((key == 'p')&& (keyPressed==true)&& game==1) {
        	players.get(profile).inv.coins=players.get(profile).inv.coins+1000000;
            

        } 
        if ((key == 'b')&& (keyPressed==true)&& game==0) {
        	population=readFromFile(storage);
            

        } 
        
    }
    @Override
    public void keyReleased(KeyEvent event) {
        if ((key == 'a')) {

            players.get(profile).accxl=0;
        } else { // Otherwise, draw an ellipse

        }

        if ((key == 'd')) {

            players.get(profile).accxr=0;
        } else { // Otherwise, draw an ellipse

        }

        if ((key == 'w')) {

            players.get(profile).accyt=0;
        } else { // Otherwise, draw n ellipse

        }
        if ((key == 's')) {

            players.get(profile).accyd=0;
        } else { // Otherwise, draw an ellipse

        }

}
   
    public static void writeToFile(File path, ArrayList<NeuralNetwork> data)
	 {
	     try(ObjectOutputStream write= new ObjectOutputStream (new FileOutputStream(path)))
	     {
	         write.writeObject(data);
	     }
	     catch(NotSerializableException nse)
	     {
	         //do something
	    	 System.out.print("help");
	     }
	     catch(IOException eio)
	     {
	         //do something
	    	 
	     }
	 }


	 public static ArrayList<NeuralNetwork> readFromFile(File path)
	 {
		 ArrayList<NeuralNetwork> data = null;

	     try(ObjectInputStream inFile = new ObjectInputStream(new FileInputStream(path)))
	     {
	         data = (ArrayList<NeuralNetwork>) inFile.readObject();
	         return data;
	     }
	     catch(ClassNotFoundException cnfe)
	     {
	         //do something
	    	 System.out.print("help");
	     }
	     catch(FileNotFoundException fnfe)
	     {
	         //do something
	    	 System.out.print("help");
	     }
	     catch(IOException e)
	     {
	         //do something
	    	 System.out.print("help");
	     }
	     return data;
	 }
    }
    