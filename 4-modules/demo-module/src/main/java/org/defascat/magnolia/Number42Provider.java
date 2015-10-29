package org.defascat.magnolia;

import javax.inject.Singleton;

@Singleton
public class Number42Provider implements NumberProvider {
    @Override
    public int getValue() {
        return 42;
    }
}