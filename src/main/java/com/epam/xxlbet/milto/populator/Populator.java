package com.epam.xxlbet.milto.populator;

public interface Populator<SOURCE, TARGET> {
    void populate(SOURCE source, TARGET target);
}
