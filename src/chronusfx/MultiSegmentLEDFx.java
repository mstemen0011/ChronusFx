/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chronusfx;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

/**
 *
 * @author matthew.g.stemen
 */
public class MultiSegmentLEDFx  {
    
    MultiSegmentLED led = null;
    Canvas canvas = null;
    Color LEDColor = Color.LIME;
    
    
    MultiSegmentLEDFx( Canvas myCanvas, MultiSegmentLED myLED )
    {
        this.led = myLED;
        this.canvas = myCanvas;
        double sizex = myCanvas.getWidth();
        double sizey = myCanvas.getHeight();
        
        System.out.println("Canvas size is: width = "  + sizex + " , height = " + sizey );
    }
    
    public void setLEDColor(Color color )
    {
        this.LEDColor = color;
        this.canvas.getGraphicsContext2D().clearRect(0, 0, 300, 200);
        this.render();
    }
    
    public void setLEDTo( int value )
    {
        this.canvas.getGraphicsContext2D().clearRect(0, 0, 300, 200);
        this.led.setLEDTo(value);
        this.render();
    }
    
    public Image render()
    {
        Image myImage = null;
        
        GraphicsContext g = canvas.getGraphicsContext2D();
        
                // System.out.println("Updating...");
        // led.status();
//        int i = LEDChipElement.getValue();
//        int si = LEDChipElement.LEDElement.element.getValue();
//        System.out.println("LED Cell size is: " + this.getHeight() + " X " + this.getWidth() );
//        System.out.println("The LED Number is: " + i );
//        int ssi = LEDChipElement.LEDElement.ordinal();
//        System.out.println("The Seven Seg LED is set to: " + si  + " (" + ssi + ")");
//        System.out.println("LED");
//        g.setColor(Color.ORANGE);
        // if( LEDChipElement.LEDElement.element.isTopBarOn())
        g.setFill(Color.LIME);
        // g.fillRect(0, 0, this.getHeight(), this.getWidth());
        // g.clearRect(0, 0, this.getHeight(), this.getWidth());
        g.setFill(this.LEDColor);
              
        g.setStroke(this.LEDColor);
        g.setLineWidth(5);
        
        if( led.isOn( MultiSegmentLED.Segment.TopBar))
        {
            
            
            // g.drawLine( VLineStart.x, VLineStart.y, VLineStart.x , VLineEnd.y );
            g.strokeLine(5, 5, 46, 5);
        }
        if( led.isOn( MultiSegmentLED.Segment.TopLeftBar))
        {
            
            // g.setColor(Color.CYAN);
            g.strokeLine(5, 8, 5, 65 );
            //g.drawLine(0, 50, 50, 10);
        }
        if( led.isOn( MultiSegmentLED.Segment.TopRightBar))
        {            
            // g.setColor( Color.GREEN );
            g.strokeLine(48, 8, 48, 65 );
            //g.drawLine(50, 10, 0, 50);
        }
        if( led.isOn( MultiSegmentLED.Segment.MiddleBar))
        {         
            // g.setColor( Color.RED );
            g.strokeLine(5,70,46,70);
        }
        if( led.isOn( MultiSegmentLED.Segment.LowerLeftBar))
        {
            g.strokeLine(5, 75, 5, 128 );
            
        }
        if( led.isOn( MultiSegmentLED.Segment.LowerRightBar))
        {
            g.strokeLine(48, 75, 48, 128 );
        }
        if( led.isOn( MultiSegmentLED.Segment.BottomBar))
        {
            //g.drawLine(0, 10, 0, 25);
            g.strokeLine(5, 130, 46, 130);
        }
        
        if( led.isOn( MultiSegmentLED.Segment.DiaTopBar))
        {
            //g.drawLine(0, 10, 0, 25);
            g.strokeLine(46, 5, 5, 70);
        }
        
        if( led.isOn( MultiSegmentLED.Segment.DiaBottomBar))
        {
            //g.drawLine(0, 10, 0, 25);
            g.strokeLine(46, 70, 5, 130);
        }
        
        if( led.isOn( MultiSegmentLED.Segment.DP))
        {            
            //g.drawLine(0, 10, 0, 25);
            g.strokeLine(50, 130, 50, 135);
        }

        
        return myImage;                                         
    }
}
