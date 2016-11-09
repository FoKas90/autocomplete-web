package ru.kontur.service;

import org.springframework.stereotype.Service;
import ru.kontur.autocomplete.KonturAutocomplete;
import ru.kontur.util.ValueResult;

import java.io.BufferedReader;
import java.util.Optional;

@Service
public class AutocompliteServiceImpl implements AutocompliteService {

    private KonturAutocomplete autocompleteEngine = null;

    @Override
    public boolean loadDictionary(BufferedReader reader) {
        ValueResult<KonturAutocomplete> result = KonturAutocomplete.from(reader);
        if (result.isSuccess()) {
            autocompleteEngine = result.getValue();
            return true;
        }
        return false;
    }

    @Override
    public String[] complite(String prefix) {
        return Optional.ofNullable(autocompleteEngine).map(engine -> engine.getBests(prefix)).
                map(result -> result.isSuccess() ? result.getValue() : new String[0]).orElse(new String[0]);
    }

    @Override
    public void resetDictionary() {
        autocompleteEngine = null;
    }
}
