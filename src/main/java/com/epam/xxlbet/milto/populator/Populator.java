package com.epam.xxlbet.milto.populator;

/**
 * Transfers data from SOURCE to TARGET.
 *
 * @author Aliaksei Milto
 */
public interface Populator<SOURCE, TARGET> {

    /**
     *  Transfers data from source to target.
     *
     * @param source source
     * @param target target
     */
    void populate(SOURCE source, TARGET target);
}
