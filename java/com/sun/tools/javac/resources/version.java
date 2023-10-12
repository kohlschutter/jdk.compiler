package com.sun.tools.javac.resources;

public final class version extends java.util.ListResourceBundle {
    protected final Object[][] getContents() {
        return new Object[][] {
            { "full", "21+35-LTS" },
            { "jdk", "21" },
            { "release", "21" },
        };
    }
}
