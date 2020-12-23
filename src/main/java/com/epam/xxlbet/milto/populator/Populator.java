package com.epam.xxlbet.milto.populator;

/**
 * Transfers data from source to target.
 *
 * @author Aliaksei Milto
 */
public interface Populator<SOURCE, TARGET> {
    void populate(SOURCE source, TARGET target);
}
