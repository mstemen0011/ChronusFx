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
public final class SevenSegmentLED extends LED {

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
        DP;
    }
    private final EnumSet<Segment> segments = EnumSet.noneOf(Segment.class);
    int current;
    LED.type type = LED.type.Seven;

    public SevenSegmentLED(int initValue) {
        current = initValue;
        this.setLEDTo(current);
    }

    public void setLEDOff()
    {
        segments.removeAll(EnumSet.range(Segment.TopBar, Segment.DP));
    }

    public void status()
    {
        StringBuilder sb = new StringBuilder("\n\nStatus of LED: " + this.hashCode() );
        sb.append("\n\nLED Type: ").append(type);
        // if( EnumSet.of( Segment.TopBar) == segments.contains(sb))
        if( segments.contains(EnumSet.of(Segment.TopBar)))
        {
            sb.append("\n\nTop bar is on\n");
        }
        if( segments.contains(EnumSet.of(Segment.TopLeftBar)))
        {
            sb.append("\n\nTop Left bar is on\n");
        }
        if( segments.contains(EnumSet.of(Segment.TopRightBar)))
        {
            sb.append("\n\nTop Right bar is on\n");
        }
        if( segments.contains(EnumSet.of(Segment.MiddleBar)))
        {
            sb.append("\n\nMiddle bar is on\n");
        }
        if( segments.contains(EnumSet.of(Segment.LowerLeftBar)))
        {
            sb.append("\n\nLower Left bar is on\n");
        }
        if( segments.contains(EnumSet.of(Segment.LowerRightBar)))
        {
            sb.append("\n\nLower Right bar is on\n");
        }
        if( segments.contains(EnumSet.of(Segment.BottomBar)))
        {
            sb.append("\n\nBottom bar is on\n");
        }

        System.out.println(sb.toString());


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
        if (i < 0 || i > 9) {
            return;
        }

        setLEDOff();
        switch (i) {
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
                segments.addAll(EnumSet.of(Segment.TopBar, Segment.TopLeftBar, Segment.TopRightBar, Segment.MiddleBar, Segment.LowerRightBar, Segment.BottomBar));
                break;
        }
        // System.out.println("segments are now: " + segments.toString());
    }
}
