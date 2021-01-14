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


public class Blood {
    protected final PApplet d;//allows the displaying outside main class
    int bodyx; //////xpos of bloodimg
     int bodyy;////ypos of bloodimg
     
   
    /**
*Method that initializes this class
*@param pa the papplet

*/  
    public Blood(PApplet pa) {
		this.d = pa;
		
		
    }
    /**
*Method that initializes a blood object
*@param pa the papplet
*@param x the xpos of bloodimg
*@param y the ypos of bloodimg
*/  
    public Blood(PApplet pa,int x,int y) {
        //sets each parameter into blood position and details
		this.d = pa;
                bodyx=x;
                bodyy=y;
                
               
		
		
    }
/**
*Method that returns the x pos of the blood object
*@return bodyx the x position of the current blood object
*/
    public int getX(){
        return bodyx;
    }
    
/**
*Method that returns the y pos of the blood object
*@return bodyy the y position of the current blood object
*/

    public int getY(){
        return bodyy;
    }
    

}
