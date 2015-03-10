/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package chronusfx;

import java.util.EnumSet;

/**
 *
 * @author Matt
 */
public final class MultiSegmentLED extends LED {

    TimeWatcherInterface timeWatcher;
    @Override
    void setWatcher(TimeWatcherInterface tw) {
        this.timeWatcher = tw;
    }

    public enum Segment {
        TopBar,
        TopLeftBar,
        TopRightBar,
        MiddleBar,
        LowerLeftBar,
        LowerRightBar,
        BottomBar,
        DiaTopBar,
        DiaBottomBar,
        DiaTopLeftBar,
        DiaTopRightBar,
        CenterTopBar,
        CenterBottomBar,
        DiaBottomLeftBar,        
        DiaBottomRightBar,
        MiddleBarLeft,
        MiddleBarRight,
        TopBarLeft,
        TopBarRight,
        BottomBarLeft,
        BottomBarRight,
        DP;
    }
    private final EnumSet<Segment> segments = EnumSet.noneOf(Segment.class);
    int current;
    LED.type type = LED.type.Nine;

    public MultiSegmentLED(int initValue, LED.type ledType) {
        current = initValue;
        type = ledType;
        this.setLEDTo(current);
    }

    public void setLEDType( LED.type newType )
    {
        this.type = newType;
    }
    
    public void setLEDOff()
    {
        segments.removeAll(EnumSet.range(Segment.TopBar, Segment.DP));
    }

    public boolean isOn(Segment s)
    {
//        System.out.println("Checking to see if segment (" + s + ") is on");
//        System.out.println("segments are now: " + segments.toString());
        return segments.contains(s);
    }

    public void setLEDTo(int i) {
        // System.out.println("Setting LED to: " + i );
        if( timeWatcher != null )
            timeWatcher.update("Setting LED to: " + i);
        if (i < -1 || i > 9) {
            return;
        }

        setLEDOff();
        switch(this.type)
        {
            case Nine:
                
                switch (i) {
                case -1: // test all value
                    System.out.println("Setting all bits");
                    segments.addAll(EnumSet.of(Segment.TopBar, Segment.TopLeftBar, Segment.TopRightBar, Segment.DiaTopBar, Segment.MiddleBar, Segment.LowerLeftBar, Segment.LowerLeftBar, Segment.DiaBottomBar, Segment.LowerRightBar, Segment.BottomBar, Segment.DP ));
                    break;    
                case 0:
                    segments.addAll(EnumSet.of(Segment.TopBar, Segment.TopLeftBar, Segment.TopRightBar, Segment.LowerLeftBar, Segment.LowerRightBar, Segment.BottomBar));
                    break;
                case 1:
                    segments.addAll(EnumSet.of( Segment.DiaTopBar, Segment.TopRightBar, Segment.LowerRightBar ));
                    break;
                case 2:
                    segments.addAll(EnumSet.of(Segment.TopBar, Segment.TopRightBar, Segment.DiaBottomBar, Segment.BottomBar));
                    break;
                case 3:
                    segments.addAll(EnumSet.of(Segment.TopBar, Segment.DiaTopBar, Segment.MiddleBar, Segment.DiaBottomBar));
                    break;
                case 4:
                    segments.addAll(EnumSet.of(Segment.TopLeftBar, Segment.TopRightBar, Segment.MiddleBar, Segment.LowerRightBar));
                    break;
                case 5:
                    segments.addAll(EnumSet.of(Segment.TopBar, Segment.TopLeftBar, Segment.MiddleBar, Segment.LowerRightBar, Segment.BottomBar));
                    break;
                case 6:
                    segments.addAll(EnumSet.of(Segment.DiaTopBar, Segment.MiddleBar, Segment.LowerLeftBar, Segment.LowerRightBar, Segment.BottomBar));
                    break;
                case 7:
                    segments.addAll(EnumSet.of(Segment.TopBar, Segment.DiaTopBar ,Segment.LowerLeftBar ));
                    break;
                case 8:
                    segments.addAll(EnumSet.of(Segment.TopBar, Segment.TopLeftBar, Segment.TopRightBar, Segment.MiddleBar, Segment.LowerLeftBar, Segment.LowerRightBar, Segment.BottomBar));
                    break;
                case 9:
                    segments.addAll(EnumSet.of(Segment.TopBar, Segment.TopLeftBar, Segment.TopRightBar, Segment.MiddleBar, Segment.DiaBottomBar ));
                    break;
                } // end value switch
            break;
                
            case Seven:
                       
            switch (i) {
            case -1:  // test all value
                System.out.println("Setting all bits");
                segments.addAll(EnumSet.of(Segment.TopBar, Segment.TopLeftBar, Segment.TopRightBar, Segment.MiddleBar, Segment.LowerLeftBar, Segment.LowerRightBar, Segment.BottomBar, Segment.DP )); 
                break;
            case 0:
                segments.addAll(EnumSet.of(Segment.TopBar, Segment.TopLeftBar, Segment.TopRightBar, Segment.LowerLeftBar, Segment.LowerRightBar, Segment.BottomBar));
                break;
            case 1:
                segments.addAll(EnumSet.of( Segment.TopRightBar, Segment.LowerRightBar ));
                break;
            case 2:
                segments.addAll(EnumSet.of(Segment.TopBar, Segment.TopRightBar, Segment.MiddleBar, Segment.LowerLeftBar, Segment.BottomBar));
                break;
            case 3:
                segments.addAll(EnumSet.of(Segment.TopBar, Segment.TopRightBar, Segment.MiddleBar, Segment.LowerRightBar, Segment.BottomBar));
                break;
            case 4:
                segments.addAll(EnumSet.of(Segment.TopLeftBar, Segment.TopRightBar, Segment.MiddleBar, Segment.LowerRightBar));
                break;
            case 5:
                segments.addAll(EnumSet.of(Segment.TopBar, Segment.TopLeftBar, Segment.MiddleBar, Segment.LowerRightBar, Segment.BottomBar));
                break;
            case 6:
                segments.addAll(EnumSet.of(Segment.TopLeftBar, Segment.MiddleBar, Segment.LowerLeftBar, Segment.LowerRightBar, Segment.BottomBar));
                break;
            case 7:
                segments.addAll(EnumSet.of(Segment.TopBar, Segment.TopRightBar, Segment.LowerRightBar));
                break;

            case 8:
                segments.addAll(EnumSet.of(Segment.TopBar, Segment.TopLeftBar, Segment.TopRightBar, Segment.MiddleBar, Segment.LowerLeftBar, Segment.LowerRightBar, Segment.BottomBar));
                break;
            case 9:
                segments.addAll(EnumSet.of(Segment.TopBar, Segment.TopLeftBar, Segment.TopRightBar, Segment.MiddleBar, Segment.LowerRightBar));
                break;
        } 
            break;
            case Fourteen:
                
                switch (i) {

                          
                case -1:  // test all value
                    System.out.println("Setting all bits");
                    segments.addAll(EnumSet.of(Segment.TopBar, Segment.TopLeftBar, Segment.TopRightBar, Segment.MiddleBarLeft, Segment.MiddleBarRight, Segment.LowerLeftBar, Segment.LowerRightBar, Segment.BottomBar, Segment.CenterTopBar, Segment.CenterBottomBar, Segment.DiaTopLeftBar, Segment.DiaTopRightBar, Segment.DiaBottomLeftBar, Segment.DiaBottomRightBar, Segment.DP )); 
                    break;
                case 0:
                    segments.addAll(EnumSet.of(Segment.TopBar, Segment.TopLeftBar, Segment.TopRightBar, Segment.LowerLeftBar, Segment.LowerRightBar, Segment.BottomBar));
                    break;
                case 1:
                    segments.addAll(EnumSet.of( Segment.DiaTopRightBar, Segment.TopRightBar, Segment.LowerRightBar ));
                    break;
                case 2:
                    segments.addAll(EnumSet.of(Segment.TopBar, Segment.TopRightBar, Segment.MiddleBarLeft, Segment.MiddleBarRight, Segment.LowerLeftBar, Segment.BottomBar));
                    break;
                case 3:
                    segments.addAll(EnumSet.of(Segment.TopBar, Segment.TopRightBar, Segment.MiddleBarRight, Segment.LowerRightBar, Segment.BottomBar));
                    break;
                case 4:
                    segments.addAll(EnumSet.of(Segment.TopLeftBar, Segment.TopRightBar, Segment.MiddleBarLeft, Segment.MiddleBarRight,  Segment.LowerRightBar));
                    break;
                case 5:
                    segments.addAll(EnumSet.of(Segment.TopBar, Segment.TopLeftBar, Segment.MiddleBarLeft, Segment.MiddleBarRight, Segment.LowerRightBar, Segment.BottomBar));
                    break;
                case 6:
                    segments.addAll(EnumSet.of(Segment.TopBar, Segment.TopLeftBar, Segment.MiddleBarLeft, Segment.MiddleBarRight, Segment.LowerLeftBar, Segment.LowerRightBar, Segment.BottomBar));                    
                    break;
                case 7:
                    segments.addAll(EnumSet.of(Segment.TopBar, Segment.DiaTopRightBar ,Segment.CenterBottomBar ));
                    break;
                case 8:
                    segments.addAll(EnumSet.of(Segment.TopBar, Segment.TopLeftBar, Segment.TopRightBar, Segment.MiddleBarLeft, Segment.MiddleBarRight, Segment.LowerLeftBar, Segment.LowerRightBar, Segment.BottomBar));
                    break;
                case 9:
                    segments.addAll(EnumSet.of(Segment.TopBar, Segment.TopLeftBar, Segment.TopRightBar, Segment.MiddleBarLeft, Segment.MiddleBarRight, Segment.LowerRightBar));
                    break;
                } // end value switch
            break;
                
        
        } // end type switch
        // System.out.println("segments are now: " + segments.toString());
    }
}
