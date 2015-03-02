/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chronusfx;

import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Callback;
import javafx.util.Duration;

/**
 *
 * @author matthew.g.stemen
 */
public class ChronusFXMLController implements Initializable {
  /* 
    Cnceptually, a time slot is a constantly updating time seqment with a frozen start time that is named buy some 
    relevent unique event ("lunch", "work start", etc ). At any given time a particular time slot can be shown and will
    continue to elapse (progressively) as long as it in focus of the main LED. The event can be  
    */  
  class TimeSlot 
  {
      long startTimeStampInMillis = 0;
      String timeSlotName = "";
  }
          
  static class ColorRectCell extends ListCell<String>{
      @Override
      public void updateItem(String item, boolean empty){
          super.updateItem(item, empty);
          Rectangle rect = new Rectangle(120,18);
          if(item != null){
              rect.setFill(Color.web(item));
              setGraphic(rect);
      }
  }
  }   

    
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
    
    @FXML
    private ComboBox<String> colorChoice;
    
    @FXML
    private ComboBox<String> timeSlotChoice;
    
    
    @FXML
    private TextField timeSlotName;
    
    Timeline timeline;
    
    private static final Integer STARTTIME = 0;
    
    private Integer timeSeconds;
            
    private ObservableList<String> timeSlotKeys;
    
    private HashMap<String, TimeSlot> timeSlotMap;
    
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
            this.timeSlotName.setText("Default");
            this.addTime(null);
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
    
    @FXML
    private void addTime(ActionEvent event)
    {
        String newTimeSlotName = this.timeSlotName.getText();
        if( ! this.timeSlotKeys.contains(newTimeSlotName))
        {
            timeSlotKeys.add(newTimeSlotName);
            TimeSlot newTimeSlot = new TimeSlot();
            newTimeSlot.timeSlotName = newTimeSlotName;
            newTimeSlot.startTimeStampInMillis = System.currentTimeMillis();
            this.timeSlotMap.put(newTimeSlotName, newTimeSlot );
        }
        
        
        
        
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
    
    @FXML
    private void handleColorChoice(ActionEvent event)
    {
        System.out.println("Setting Color");
        String colorAsString = this.colorChoice.getValue();
        Color newColor = Color.valueOf(colorAsString);
        if( newColor != null )
        {
            this.dayTenLEDFx.setLEDColor(newColor);
            this.dayOneLEDFx.setLEDColor(newColor);
            this.hourTenLEDFx.setLEDColor(newColor);
            this.hourOneLEDFx.setLEDColor(newColor);
            this.minTenLEDFx.setLEDColor(newColor);
            this.minOneLEDFx.setLEDColor(newColor);
            this.secTenLEDFx.setLEDColor(newColor);
            this.secOneLEDFx.setLEDColor(newColor);
        }
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        // set the color combo
        ObservableList<String> data = FXCollections.observableArrayList(
            "lime", "orange", "gold", "coral", "darkgoldenrod", "lightsalmon", "black", "rosybrown", "blue",
            "blueviolet", "white");  
        timeSlotKeys = this.timeSlotChoice.getItems();
        colorChoice.setItems(data);
        timeSlotMap = new HashMap<>();

        Callback<ListView<String>, ListCell<String>> factory = new Callback<ListView<String>, ListCell<String>>() {
        @Override
        public ListCell<String> call(ListView<String> list) {
            return new ColorRectCell();
        }
        };

        colorChoice.setCellFactory(factory);
        colorChoice.setButtonCell(factory.call(null));
        
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
