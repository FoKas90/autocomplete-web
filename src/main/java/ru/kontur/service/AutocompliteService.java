package ru.kontur.service;

import java.io.BufferedReader;

public interface AutocompliteService {
    boolean loadDictionary(BufferedReader reader);

    String[] complite(String prefix);

    void resetDictionary();
}
