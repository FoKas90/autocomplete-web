package ru.kontur.initializer;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;
import ru.kontur.AutocompleteWebApplication;

public class KonturAutocompleteInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    private static final String MAPPING_URL = "/";

    @Override
    protected String[] getServletMappings() {
        return new String[]{MAPPING_URL};
    }

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return null;
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class<?>[]{AutocompleteWebApplication.class};
    }
}
