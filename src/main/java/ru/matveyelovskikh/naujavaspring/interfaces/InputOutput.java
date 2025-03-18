package ru.matveyelovskikh.naujavaspring.interfaces;

/**
 * Интерфейс ввода и вывода данных
 */
public interface InputOutput {

    /**
     * Вводить данные
     * @return введенная строка
     */
    String input();

    /**
     * Выводить данные
     * @param message сообщение для вывода
     */
    void output(String message);
}
