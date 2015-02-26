/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package chronusfx;

/**
 *
 * @author Matt
 */
public abstract class LED {

    public enum type
    {
        Seven,
        Nine,     // Russian 70's era version of Seven Seq
        Fourteen, //  Union Jack or Starburst
        Sixteen,  //
        DotReg,   // 5x7
        DotSmall, // 3x5
        DotLarge, // 11 x 9
    }

    abstract void setWatcher( TimeWatcherInterface tw );


}
