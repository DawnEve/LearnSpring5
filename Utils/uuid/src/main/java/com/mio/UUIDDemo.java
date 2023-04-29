package com.mio;

import java.util.UUID;

public class UUIDDemo {
    public static void main(String[] args) {
        String uuid=UUID.randomUUID().toString();
        System.out.println("uuid=" + uuid);
        //uuid=9c9faa5e-cd2c-4003-bf7e-a9ac422d2d22
        //uuid=f70f9aea-5cd6-4c3a-b470-884075122327
        // 每次都不一样
    }
}