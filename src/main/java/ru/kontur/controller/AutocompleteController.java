package ru.kontur.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.kontur.service.AutocompliteService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

@RequestMapping("/")
@Controller
public class AutocompleteController {

    private static final Logger _logger = LoggerFactory.getLogger(AutocompleteController.class);

    @Autowired
    private AutocompliteService service;

    @GetMapping("/kontur")
    public String showAutocompletePage() {
        return "autocomplete";
    }

    @ResponseBody
    @GetMapping("/doAutocomplete")
    public String[] searchBests(@RequestParam("query") String prefix) {
        return Arrays.stream(service.complite(prefix)).filter(s -> s != null).toArray(String[]::new);
    }

    @PostMapping("/kontur")
    public String setDictionary(@RequestParam("data") MultipartFile file) {
        try {
            service.loadDictionary(new BufferedReader(new InputStreamReader(file.getInputStream())));
            _logger.info("Dictionary is loaded");
        } catch (IOException e) {
            service.resetDictionary();
            _logger.error("Dictionary doesn't loaded", e);
        }
        return "autocomplete";
    }
}
