///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package chronusfx;
//
//import java.awt.Dimension;
//import java.awt.Point;
//import java.beans.PropertyChangeListener;
//import java.beans.PropertyChangeSupport;
//import javafx.scene.canvas.Canvas;
//import javafx.scene.canvas.GraphicsContext;
//import javafx.scene.paint.Color;
//import javafx.scene.paint.Paint;
//
///**
// *
// * @author matthew.g.stemen
// */
//public class TimeChip extends Canvas {
//        private static final long serialVersionUID = -7275346284211083832L;
//    int w, h = 0;
//    Point VLineStart, VLineEnd;
//    Point HLineStart, HLineEnd;
//    SevenSegmentLED led;
//    TimeWatcherInterface myWatcher;
//    Color LEDColor = Color.GREEN;
//    GraphicsContext myGraphics = null;
//    // public LEDChip( LEDSevenSegmentElement myElement )
//    public static final String PROP_SAMPLE_PROPERTY = "sampleProperty";
//    private String sampleProperty;
//    private PropertyChangeSupport propertySupport;
//    private boolean hasChange = false;
//
//    public TimeChip()
//    {
//        
//        
//    }
//    public TimeChip( TimeWatcherInterface tw, int chipId, Dimension d ) {
//        propertySupport = new PropertyChangeSupport(this);
//        led = new SevenSegmentLED(tw, chipId);
//        myWatcher = tw;
//        // this.setBackground(Color.BLACK);
//        // this.setForeground(Color.ORANGE);
//        w = d.width;
//        h = d.height;
//        VLineStart = new Point(10, 10);
//        VLineEnd = new Point(w - 15, 0);
//        HLineStart = new Point(10, 10);
//        HLineEnd = new Point(0, h / 4);
//        led.setLEDTo(chipId);     
//    }
//    
//    /**
//     *
//     * @param g
//     */
//    public void update(GraphicsContext  g) 
//    {
//        super.paintComponent(g);
//        if(led == null )
//            return;
//        if(myGraphics == null )
//        {
//            myGraphics = g;
//        }
//        // System.out.println("Updating...");
//        // led.status();
////        int i = LEDChipElement.getValue();
////        int si = LEDChipElement.LEDElement.element.getValue();
////        System.out.println("LED Cell size is: " + this.getHeight() + " X " + this.getWidth() );
////        System.out.println("The LED Number is: " + i );
////        int ssi = LEDChipElement.LEDElement.ordinal();
////        System.out.println("The Seven Seg LED is set to: " + si  + " (" + ssi + ")");
////        System.out.println("LED");
////        g.setColor(Color.ORANGE);
//        // if( LEDChipElement.LEDElement.element.isTopBarOn())
//        g.setFill(Color.GREEN);
//        g.fillRect(0, 0, this.getHeight(), this.getWidth());
//        // g.clearRect(0, 0, this.getHeight(), this.getWidth());
//        g.setFill(this.LEDColor);
//        if( led.isOn( SevenSegmentLED.Segment.TopBar))
//        {
//            
//            
//            // g.drawLine( VLineStart.x, VLineStart.y, VLineStart.x , VLineEnd.y );
//            g.drawLine(5, 5, 46, 5);
//        }
//        if( led.isOn( SevenSegmentLED.Segment.TopLeftBar))
//        {
//            
//            // g.setColor(Color.CYAN);
//            g.drawLine(5, 8, 5, 65 );
//            //g.drawLine(0, 50, 50, 10);
//        }
//        if( led.isOn( SevenSegmentLED.Segment.TopRightBar))
//        {            
//            // g.setColor( Color.GREEN );
//            g.drawLine(48, 8, 48, 65 );
//            //g.drawLine(50, 10, 0, 50);
//        }
//        if( led.isOn( SevenSegmentLED.Segment.MiddleBar))
//        {         
//            // g.setColor( Color.RED );
//            g.drawLine(5,70,46,70);
//        }
//        if( led.isOn( SevenSegmentLED.Segment.LowerLeftBar))
//        {
//            g.drawLine(5, 75, 5, 128 );
//            
//        }
//        if( led.isOn( SevenSegmentLED.Segment.LowerRightBar))
//        {
//            g.drawLine(48, 75, 48, 128 );
//        }
//        if( led.isOn( SevenSegmentLED.Segment.BottomBar))
//        {
//            //g.drawLine(0, 10, 0, 25);
//            g.drawLine(5, 130, 46, 130);
//        }
//
//        if( led.isOn( SevenSegmentLED.Segment.DP))
//        {            
//            //g.drawLine(0, 10, 0, 25);
//            g.drawLine(50, 130, 50, 135);
//        }
//         
//        this.hasChange = false;
//        if( myWatcher != null )
//            myWatcher.showChange();
//
//    }
//    
//    private void clearLed()
//    {
//        this.setBackground(Color.BLACK);
//        if( myGraphics != null )
//        {
//            myGraphics.clearRect(0, 0, w, h);
//            // System.out.println("clearing rect");
//        }
//         this.hasChange = true;
//    }
//    public void setValue( int i )
//    {
//        clearLed();
//        led.setLEDTo(i);
//        this.hasChange = true;
//        // this.invalidate();
//        this.repaint();
//    }    
//
//    public String getSampleProperty() {
//        return sampleProperty;
//    }
//
//    public void setSampleProperty(String value) {
//        String oldValue = sampleProperty;
//        sampleProperty = value;
//        propertySupport.firePropertyChange(PROP_SAMPLE_PROPERTY, oldValue, sampleProperty);
//    }
//
//    @Override
//    public void addPropertyChangeListener(PropertyChangeListener listener) {
//        if( propertySupport != null  )
//            propertySupport.addPropertyChangeListener(listener);
//    }
//
//    @Override
//    public void removePropertyChangeListener(PropertyChangeListener listener) {
//        if( propertySupport != null  )
//            propertySupport.removePropertyChangeListener(listener);
//    }
//    
//    public void setChange()
//    {
//        this.hasChange = ! this.hasChange;
//    }
//    
//    public void setChange( boolean flag )
//    {
//        this.hasChange = flag;
//    }
//    
//    public boolean hasChange()
//    {
//        return this.hasChange;
//    }
//    
//}
