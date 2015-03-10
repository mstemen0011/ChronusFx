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
import javafx.scene.shape.Line;

/**
 *
 * @author matthew.g.stemen
 */
public class MultiSegmentLEDFx  {
    
    MultiSegmentLED led = null;
    Canvas canvas = null;
    Color LEDColor = Color.LIME;
    
    Line topBar =  new Line(10,10,60,10);
    Line topLeftBar = new Line(5, 15, 5, 55);
    Line topRightBar = new Line(65, 15, 65, 55);
    Line middleBar = new Line( 10,65,55,65 );
    Line middleBarLeft = new Line( 10,65,30,65 );
    Line middleBarRight = new Line(45,65,65,65);
    Line centerTopBar = new Line( 35, 15, 35, 60 );
    Line centerBottomBar = new Line( 35,70,35,120 );
    Line lowerLeftBar = new Line(3, 70, 3, 120);
    Line lowerRightBar = new Line( 65, 70, 65, 120 );
    Line bottomBar = new Line( 10, 130, 60, 130 );
    Line diaTopBar = new Line( 65, 15, 10, 65 );
    Line diaTopRightBar = new Line(65, 10, 40, 65); //46, 5, 5, 46
    Line diaTopLeftBar = new Line(10, 10, 30, 65); //46, 5, 5, 46
    Line diaBottomLeftBar = new Line(10, 130, 30, 65);
    Line diaBottomRightBar = new Line(60, 130, 40, 65);
    Line diaBottomBar = new Line( 65, 70, 5, 130 );
    Line decPoint = new Line( 70, 130, 70, 135 );
    
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
        if( value == -1 )
            System.out.println("Setting LED to Test...");
        this.canvas.getGraphicsContext2D().clearRect(0, 0, 300, 200);
        this.led.setLEDTo(value);
        this.render();
    }
    
    public void test()
    {
        this.led.setLEDTo(-1);
        render();
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
        
        
        /* 
            top point (5,5)    top right point (50, 5)
        
        
        */
        if( led.isOn( MultiSegmentLED.Segment.TopBar))
        {
            
            
            // g.drawLine( VLineStart.x, VLineStart.y, VLineStart.x , VLineEnd.y );
            g.strokeLine(topBar.getStartX(), topBar.getStartY(), topBar.getEndX(), topBar.getEndY());
        }
        if( led.isOn( MultiSegmentLED.Segment.TopLeftBar))
        {
            
            // g.setColor(Color.CYAN);
            g.strokeLine(topLeftBar.getStartX(), topLeftBar.getStartY(), topLeftBar.getEndX(), topLeftBar.getEndY() );
            //g.drawLine(0, 50, 50, 10);
        }
        if( led.isOn( MultiSegmentLED.Segment.TopRightBar))
        {            
            // g.setColor( Color.GREEN );
            g.strokeLine(topRightBar.getStartX(), topRightBar.getStartY(), topRightBar.getEndX(), topRightBar.getEndY() );
            //g.drawLine(50, 10, 0, 50);
        }
        if( led.isOn( MultiSegmentLED.Segment.MiddleBar))
        {         
            // g.setColor( Color.RED );
            g.strokeLine(middleBar.getStartX(),middleBar.getStartY(), middleBar.getEndX(),middleBar.getEndY());
        }
        if( led.isOn( MultiSegmentLED.Segment.CenterTopBar))
        {         
            // g.setColor( Color.RED );
            g.strokeLine(centerTopBar.getStartX(),centerTopBar.getStartY(), centerTopBar.getEndX(),centerTopBar.getEndY());
        }   
        if( led.isOn( MultiSegmentLED.Segment.CenterBottomBar))
        {         
            // g.setColor( Color.RED );
            g.strokeLine(centerBottomBar.getStartX(),centerBottomBar.getStartY(), centerBottomBar.getEndX(),centerBottomBar.getEndY());
        }           
        if( led.isOn(MultiSegmentLED.Segment.MiddleBarLeft))
        {
            g.strokeLine(middleBarLeft.getStartX(),middleBarLeft.getStartY(), middleBarLeft.getEndX(),middleBarLeft.getEndY());
        }
        if( led.isOn(MultiSegmentLED.Segment.MiddleBarRight))
        {
            g.strokeLine(middleBarRight.getStartX(),middleBarRight.getStartY(), middleBarRight.getEndX(),middleBarRight.getEndY());
        }
        if( led.isOn( MultiSegmentLED.Segment.LowerLeftBar))
        {
            g.strokeLine(lowerLeftBar.getStartX(),lowerLeftBar.getStartY(), lowerLeftBar.getEndX(),lowerLeftBar.getEndY());
            
        }
        if( led.isOn( MultiSegmentLED.Segment.LowerRightBar))
        {
            g.strokeLine(lowerRightBar.getStartX(),lowerRightBar.getStartY(), lowerRightBar.getEndX(),lowerRightBar.getEndY() );
        }
        if( led.isOn( MultiSegmentLED.Segment.BottomBar))
        {
            //g.drawLine(0, 10, 0, 25);
            g.strokeLine(bottomBar.getStartX(),bottomBar.getStartY(), bottomBar.getEndX(),bottomBar.getEndY());
        }
        
        if( led.isOn( MultiSegmentLED.Segment.DiaTopBar))
        {
            //g.drawLine(0, 10, 0, 25);
            g.strokeLine(diaTopBar.getStartX(), diaTopBar.getStartY(),diaTopBar.getEndX(), diaTopBar.getEndY() );
        }
        
        if( led.isOn( MultiSegmentLED.Segment.DiaBottomBar))
        {
            //g.drawLine(0, 10, 0, 25);
            g.strokeLine(diaBottomBar.getStartX(),diaBottomBar.getStartY(), diaBottomBar.getEndX(), diaBottomBar.getEndY());
        }
        
        if( led.isOn(MultiSegmentLED.Segment.DiaTopRightBar))
        {
            g.strokeLine(diaTopRightBar.getStartX(),diaTopRightBar.getStartY(), diaTopRightBar.getEndX(), diaTopRightBar.getEndY());
        }
              
        if( led.isOn(MultiSegmentLED.Segment.DiaTopLeftBar))
        {
            g.strokeLine(diaTopLeftBar.getStartX(),diaTopLeftBar.getStartY(), diaTopLeftBar.getEndX(), diaTopLeftBar.getEndY());
        }
      
        if( led.isOn(MultiSegmentLED.Segment.DiaBottomRightBar))
        {
            g.strokeLine(diaBottomRightBar.getStartX(),diaBottomRightBar.getStartY(), diaBottomRightBar.getEndX(), diaBottomRightBar.getEndY());
        }
              
        if( led.isOn(MultiSegmentLED.Segment.DiaBottomLeftBar))
        {
            g.strokeLine(diaBottomLeftBar.getStartX(),diaBottomLeftBar.getStartY(), diaBottomLeftBar.getEndX(), diaBottomLeftBar.getEndY());
        }        
        if( led.isOn( MultiSegmentLED.Segment.DP))
        {            
            //g.drawLine(0, 10, 0, 25);
            g.strokeLine(decPoint.getStartX(), decPoint.getStartY(), decPoint.getEndX(), decPoint.getEndY());
        }

        
        return myImage;                                         
    }
}
