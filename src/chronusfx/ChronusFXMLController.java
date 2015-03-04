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
      long createTimeStampInMillis = 0;
      long startTimeStampInMillis = 0;
      String timeSlotName = "";
      boolean isStarted = false;
      
      public TimeSlot()
      {
          createTimeStampInMillis = System.currentTimeMillis();
      }
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
    
    MultiSegmentLED secOneLED;
    MultiSegmentLED secTenLED;
    MultiSegmentLED minOneLED;
    MultiSegmentLED minTenLED;
    MultiSegmentLED hourOneLED;
    MultiSegmentLED hourTenLED;
    MultiSegmentLED dayOneLED;
    MultiSegmentLED dayTenLED;
    
    MultiSegmentLEDFx secOneLEDFx = null;
    MultiSegmentLEDFx secTenLEDFx = null;
    MultiSegmentLEDFx minOneLEDFx = null;
    MultiSegmentLEDFx minTenLEDFx = null;
    MultiSegmentLEDFx hourOneLEDFx = null;
    MultiSegmentLEDFx hourTenLEDFx = null;
    MultiSegmentLEDFx dayOneLEDFx = null;
    MultiSegmentLEDFx dayTenLEDFx = null;    
    
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
    private ComboBox<String> segmentTypeChoice;
    
    @FXML
    private TextField timeSlotName;
    
    Timeline timeline;
    
    TimeSlot timeSlotInFocus;
    
    private static final Integer STARTTIME = 0;
    
    private Integer timeSeconds;
            
    private ObservableList<String> timeSlotKeys;
    
    private HashMap<String, TimeSlot> timeSlotMap;
            
    private boolean timeIsRunning = false;
            
    LED.type LedSegType = LED.type.Seven;
    
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
        int dayTenVal = (int) days/10;
        int dayOneVal = (int) days%10;
        this.dayTenLEDFx.setLEDTo(dayTenVal);
        this.dayTenLEDFx.setLEDTo(dayOneVal);
        // calc and set the hours fields
        long hours = baseTime / 3600000;
        hours = hours % 60;
        int hourTenValue = (int) hours/10;
        int hourOneValue = (int) hours%10;
        this.hourTenLEDFx.setLEDTo(hourTenValue);
        this.hourOneLEDFx.setLEDTo(hourOneValue);
       // calc and set the minutes fields
        long mins = baseTime / 60000;
        mins = mins % 60;
        int minsTenValue = (int) mins/10;
        int minsOneValue = (int) mins%10;
        this.minTenLEDFx.setLEDTo(minsTenValue);
        this.minOneLEDFx.setLEDTo(minsOneValue);
        // calc and set the seconds fields
        long secs = baseTime / 1000;
        secs = secs % 60;
        int secsTenValue = (int) secs/10;
        int secsOneValue = (int) secs%10;
        this.secTenLEDFx.setLEDTo(secsTenValue);
        this.secOneLEDFx.setLEDTo(secsOneValue);
        
        
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
            this.timeSlotChoice.getSelectionModel().select(newTimeSlotName);
        }
        
        
        
        
    }
    
    @FXML
    private void selectTimeSlot(ActionEvent event)
    {
        String key = this.timeSlotChoice.getSelectionModel().getSelectedItem();
        TimeSlot ts = this.timeSlotMap.get(key);
        System.out.println("Time slot name is: " + ts.timeSlotName);
        System.out.println("Create time is: " + ts.createTimeStampInMillis );
        this.timeSlotInFocus = ts;
        this.timeIsRunning = ts.isStarted;
        
    }
    
    private void focusCurrentTimeSlot()
    {
        // basic on the state of the current time slot, show the elasped time and 
        // the accumulating time
        if( this.timeSlotInFocus.isStarted )
        {
            
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
    private void handleTypeChoice(ActionEvent event)
    {
        
        String typeAsString = this.segmentTypeChoice.getValue();
        System.out.println("Setting Type: " + typeAsString );
        LED.type newType = LED.type.valueOf(typeAsString);
        if( newType != null )
        {
            this.dayTenLED.setLEDType(newType);
            this.dayOneLED.setLEDType(newType);
            this.hourTenLED.setLEDType(newType);
            this.hourOneLED.setLEDType(newType);
            this.minTenLED.setLEDType(newType);
            this.minOneLED.setLEDType(newType);
            this.secTenLED.setLEDType(newType);
            this.secOneLED.setLEDType(newType);
        }
        
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
            "limegreen", "orangered","darkorange", "goldenrod", "coral", "darkgoldenrod", "lightsalmon", "black", "rosybrown", "blue",
            "blueviolet", "white");  
        
        ObservableList<String> segmentTypes = FXCollections.observableArrayList( 
        "Seven", "Nine");
        timeSlotKeys = this.timeSlotChoice.getItems();
        colorChoice.setItems(data);
        segmentTypeChoice.setItems(segmentTypes);
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
        
        secOneLED = new MultiSegmentLED(0, LedSegType);
        secTenLED = new MultiSegmentLED(0, LedSegType);                
        minOneLED = new MultiSegmentLED(0, LedSegType);
        minTenLED = new MultiSegmentLED(0, LedSegType);
        hourOneLED = new MultiSegmentLED(0, LedSegType);
        hourTenLED = new MultiSegmentLED(0, LedSegType);
        dayOneLED = new MultiSegmentLED(0, LedSegType);
        dayTenLED = new MultiSegmentLED(0, LedSegType);           
        secOneLEDFx = new MultiSegmentLEDFx( secOne, secOneLED );
        secOneLED.setLEDTo(8);
        secOneLEDFx.render();
        secTenLEDFx = new MultiSegmentLEDFx( secTen, secTenLED );
        secTenLED.setLEDTo(7);
        secTenLEDFx.render();
        
        minOneLEDFx = new MultiSegmentLEDFx( minOne, minOneLED );
        minOneLED.setLEDTo(6);
        minOneLEDFx.render();
        minTenLEDFx = new MultiSegmentLEDFx( minTen, minTenLED );
        minTenLED.setLEDTo(5);
        minTenLEDFx.render();
        
        hourOneLEDFx = new MultiSegmentLEDFx( hourOne, hourOneLED );
        hourOneLED.setLEDTo(4);
        hourOneLEDFx.render();
        hourTenLEDFx = new MultiSegmentLEDFx( hourTen, hourTenLED );
        hourTenLED.setLEDTo(3);
        hourTenLEDFx.render();
        
        dayOneLEDFx = new MultiSegmentLEDFx( dayOne, dayOneLED );
        dayOneLED.setLEDTo(2);
        dayOneLEDFx.render();
        dayTenLEDFx = new MultiSegmentLEDFx( dayTen, dayTenLED );
        dayTenLED.setLEDTo(1);
        dayTenLEDFx.render();
        
        this.mainButton.setText( TimerAction.Start.getAction());
        
        
    }    
    
}
