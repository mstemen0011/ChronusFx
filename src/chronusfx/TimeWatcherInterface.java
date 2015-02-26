/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package chronusfx;

import java.util.Observer;

/**
 *
 * @author Matt
 */
public interface TimeWatcherInterface extends Observer {

    public void showChange();

    public void showTime();

    public void update( String msg );
}
