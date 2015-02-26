/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chronusfx;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.util.Duration;

/**
 *
 * @author matthew.g.stemen
 */
public class ChronusFXMLController implements Initializable {
    
    public enum TimerAction
    {
        Start( "Start"),
        Stop("Stop")
        ;
        
        String myAction;
        
        TimerAction(String s )
        {
            myAction = s;
        }
        
        public String getAction()
        {
            return this.myAction;
        }
    }
    // the virtual LED:
    
    SevenSegmentLED secOneLED;
    SevenSegmentLED secTenLED;
    SevenSegmentLED minOneLED;
    SevenSegmentLED minTenLED;
    SevenSegmentLED hourOneLED;
    SevenSegmentLED hourTenLED;
    SevenSegmentLED dayOneLED;
    SevenSegmentLED dayTenLED;
    
    LEDFx secOneLEDFx = null;
    LEDFx secTenLEDFx = null;
    LEDFx minOneLEDFx = null;
    LEDFx minTenLEDFx = null;
    LEDFx hourOneLEDFx = null;
    LEDFx hourTenLEDFx = null;
    LEDFx dayOneLEDFx = null;
    LEDFx dayTenLEDFx = null;    
    
    @FXML
    private Button mainButton;
    
    @FXML
    private Label label;
    
    @FXML
    private Canvas secOne; 
    
    @FXML
    private Canvas secTen;
    
    @FXML
    private Canvas minOne;
    
    @FXML
    private Canvas minTen;
    
    @FXML
    private Canvas hourOne;
    
    @FXML
    private Canvas hourTen;
    
    @FXML
    private Canvas dayOne;
    
    @FXML
    private Canvas dayTen;
    
    Timeline timeline;
    
    private static final Integer STARTTIME = 0;
    
    private Integer timeSeconds;
    
    @FXML
    private void startAction()
    {
        String currentAction = this.mainButton.getText();
        if( currentAction.equals( TimerAction.Stop.getAction()))
        {
            reset();
            this.timeline.stop();
            this.mainButton.setText(TimerAction.Start.getAction());
        }
        else
        {  
            reset();
            this.mainButton.setText(TimerAction.Stop.getAction());
            timeSeconds = STARTTIME;
 
            // update time
            this.setTime( this.timeSeconds.longValue() * 1000 );
            timeline = new Timeline();
            timeline.setCycleCount(Timeline.INDEFINITE);
            timeline.getKeyFrames().add(                
                    new KeyFrame(Duration.seconds(1),                  
                            new EventHandler() {
                            // KeyFrame event handler
                                @Override
                                public void handle(Event event) {
                                    timeSeconds++;
                                    // update timer
                                    setTime( timeSeconds.longValue() * 1000 );
                                }
                            }));
            timeline.playFromStart();                    
           }   
    }
    
    private void setTime(long timeInMillis )
    {
        // calc and set the days fields
        long baseTime = timeInMillis;
        long days = baseTime / 86400000;
        days = days % 24;
        int dayTen = (int) days/10;
        int dayOne = (int) days%10;
        this.dayTenLEDFx.setLEDTo(dayTen);
        this.dayTenLEDFx.setLEDTo(dayOne);
        // calc and set the hours fields
        long hours = baseTime / 3600000;
        hours = hours % 60;
        int hourTen = (int) hours/10;
        int hourOne = (int) hours%10;
        this.hourTenLEDFx.setLEDTo(hourTen);
        this.hourOneLEDFx.setLEDTo(hourOne);
       // calc and set the minutes fields
        long mins = baseTime / 60000;
        mins = mins % 60;
        int minsTen = (int) mins/10;
        int minsOne = (int) mins%10;
        this.minTenLEDFx.setLEDTo(minsTen);
        this.minOneLEDFx.setLEDTo(minsOne);
        // calc and set the seconds fields
        long secs = baseTime / 1000;
        secs = secs % 60;
        int secsTen = (int) secs/10;
        int secsOne = (int) secs%10;
        this.secTenLEDFx.setLEDTo(secsTen);
        this.secOneLEDFx.setLEDTo(secsOne);
        
        
    }
    
    private void reset()
    {
        this.dayOneLEDFx.setLEDTo(0);
        this.dayTenLEDFx.setLEDTo(0);
        this.hourOneLEDFx.setLEDTo(0);
        this.hourTenLEDFx.setLEDTo(0);
        this.minOneLEDFx.setLEDTo(0);
        this.minTenLEDFx.setLEDTo(0);
        this.secOneLEDFx.setLEDTo(0);
        this.secTenLEDFx.setLEDTo(0);
      
    }
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        timeline = new Timeline();
        
        secOneLED = new SevenSegmentLED(0);
        secTenLED = new SevenSegmentLED(0);                
        minOneLED = new SevenSegmentLED(0);
        minTenLED = new SevenSegmentLED(0);
        hourOneLED = new SevenSegmentLED(0);
        hourTenLED = new SevenSegmentLED(0);
        dayOneLED = new SevenSegmentLED(0);
        dayTenLED = new SevenSegmentLED(0);   
        
        secOneLEDFx = new LEDFx( secOne, secOneLED );
        secOneLED.setLEDTo(8);
        secOneLEDFx.render();
        secTenLEDFx = new LEDFx( secTen, secTenLED );
        secTenLED.setLEDTo(7);
        secTenLEDFx.render();
        
        minOneLEDFx = new LEDFx( minOne, minOneLED );
        minOneLED.setLEDTo(6);
        minOneLEDFx.render();
        minTenLEDFx = new LEDFx( minTen, minTenLED );
        minTenLED.setLEDTo(5);
        minTenLEDFx.render();
        
        hourOneLEDFx = new LEDFx( hourOne, hourOneLED );
        hourOneLED.setLEDTo(4);
        hourOneLEDFx.render();
        hourTenLEDFx = new LEDFx( hourTen, hourTenLED );
        hourTenLED.setLEDTo(3);
        hourTenLEDFx.render();
        
        dayOneLEDFx = new LEDFx( dayOne, dayOneLED );
        dayOneLED.setLEDTo(2);
        dayOneLEDFx.render();
        dayTenLEDFx = new LEDFx( dayTen, dayTenLED );
        dayTenLED.setLEDTo(1);
        dayTenLEDFx.render();
        
        this.mainButton.setText( TimerAction.Start.getAction());
        
        
    }    
    
}
