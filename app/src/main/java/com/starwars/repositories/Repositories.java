package com.starwars.repositories;


import com.starwars.repositories.implementation.RoomCharacterRepository;
import com.starwars.repositories.interfaces.CharacterRepository;

public class Repositories {
    private static Repositories ourInstance;

    private final CharacterRepository characterRepository = new RoomCharacterRepository();

    private Repositories() {
    }

    public static Repositories getInstance() {
        if (ourInstance == null) {
            ourInstance = new Repositories();
        }
        return ourInstance;
    }

    public CharacterRepository getCharacterRepository() {
        return characterRepository;
    }
}
