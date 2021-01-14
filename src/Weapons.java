import processing.core.*;
import java.util.ArrayList;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author chun
 */
public class Weapons {
    //default is glock
    public  int weapon=1;//which weapon
    public  int maxammo=13;//max ammo of weapon
    public  int ammo=13;//current ammo of weapon
    int firerate=10;//max firerate of weapon that cooldownfire is set to
    int cooldownfire=10;//cureent weapon fireate cooldown
    int reloadcooldown=20;//cooldown used when reloading
    int numbullets=1;//number of bullet the weapon shoots
    public int damage=25;//damage of weapon
    public int kb=25;//knockback of weapon
    public int bulletace=200;//speed of bullet
    public  double spread = 0.5;//random spread of bullet
    public  String name="Glock-18";//name of gun
    public int length=40;//lenght of gun barrel
    public  int power=40;//knockback power on enemys
    public  int width=4;//width of gun
    
/**
*Method that initializes this class
*/  
    public Weapons(){
        
    }
/**
*Method that ticks down weapon firerates
*/  
    
    void WeaponCooldowns(){
        reloadcooldown=reloadcooldown-1;
        cooldownfire=cooldownfire-1;
    }
/**
*Method that initializes weapon properties
*/  
    
    void WeaponInit(){
        //Glock-18
        if (weapon==1){
            name="Glock-18";
            width=4;
            power=80;
            length=40;
            maxammo=13;
            ammo=maxammo;
            firerate=10;
            cooldownfire=10;
            reloadcooldown=20;
            numbullets=1;
            damage=25;
            kb=25;
            bulletace=200;
            spread=0.75;
            
        }
        //Nova
        if (weapon==2){
            name="Nova";
            width=6;
            power=150;
            length=45;
            maxammo=8;
            ammo=maxammo;
            firerate=50;
            cooldownfire=firerate;
            reloadcooldown=40;
            numbullets=9;
            damage=20;
            kb=250;
            bulletace=250;
            spread=1;
        }
        //Ak-47
        if (weapon==3){
            name="Ak-47";
            width=4;
            power=75;
            length=48;
            maxammo=30;
            ammo=maxammo;
            firerate=2;
            cooldownfire=firerate;
            reloadcooldown=60;
            numbullets=1;
            damage=20;
            kb=30;
            bulletace=250;
            spread=0.85;
        }
        //Minigun
        if (weapon==4){
            name="M249";
            width=10;
            power=60;
            length=52;
            maxammo=300;
            ammo=maxammo;
            firerate=1;
            cooldownfire=firerate;
            reloadcooldown=200;
            numbullets=4;
            damage=12;
            kb=30;
            bulletace=250;
            spread=1.2;
        }
        //AWP
        if (weapon==5){
            name="AWP";
            width=3;
            power=500;
            length=60;
            maxammo=10;
            ammo=maxammo;
            firerate=30;
            cooldownfire=firerate;
            reloadcooldown=60;
            numbullets=1;
            damage=200;
            kb=150;
            bulletace=500;
            spread=0.05;
        }
    }
/**
*Method that sets the cooldown for the weapon when reloading
*/ 
    
    void WeaponReload(){
        if (ammo==0) {
	    	ammo=maxammo;
	        if (weapon==1){
	            reloadcooldown=60;
	        }
	        else if (weapon==2){
	            reloadcooldown=40;
	        }
	        else if (weapon==3){
	            reloadcooldown=60;
	        }
	        else if (weapon==4){
	            reloadcooldown=200;
	        }
	        else if (weapon==5){
	            reloadcooldown=60;
	        }
        }
    }
    
    
}
