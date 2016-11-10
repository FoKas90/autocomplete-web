package ru.kontur.service;

import java.io.BufferedReader;

/**
 * Сервис автозаполнения
 */
public interface AutocompliteService {

    /**
     * Загружает словарь, на котором производится поиск слов
     * @param reader Символьный поток, из котого читается словарь
     * @return true - если загрузка словаря прошла умпешно
     */
    boolean loadDictionary(BufferedReader reader);

    /**
     * Возвращает варианты для автозаполнения
     * @param prefix Начало искомого слова
     * @return Варианты заполнения. Пустой массив возвращается в случаях, если
     * {@code prefix} null или пустая, словарь пуст или не загружен, варианты заполнения не найдены
     */
    String[] complite(String prefix);

    /**
     * Сброс словаря. После вызова этого метода, метод complite
     * будет возвращать пустой массив.
     */
    void resetDictionary();
}
