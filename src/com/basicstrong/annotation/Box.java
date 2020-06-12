package com.basicstrong.annotation;

public class Box<@NonEmpty T> {
    @NonEmpty int size;
    T type;

    public Box(int size, T type) {
        this.size = size;
        this.type = type;
    }

    class NestedBox extends Box{
        public NestedBox(int size, @NonEmpty T type) {
            super(size, type);
        }
    }
}
