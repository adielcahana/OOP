package gameobjects;

import listeners.ShootListener;

/**
 * @author Ori Engelberg <turht50@gmail.com>
 * @version 1.0
 * @since 2016-04-10 */
public interface ShootNotifier extends Sprite{

    /**
     * Add the hit listener to the list of listeners to hit events.
     * <p>
     * @param hl - the HitListener that listend.*/
    void addShootListener(ShootListener sl);

    /**
     * remove the hit listener from the list of listeners to hit events.
     * <p>
     * @param hl - the HitListener that listend.*/
    void removeShootListener(ShootListener sl);
}