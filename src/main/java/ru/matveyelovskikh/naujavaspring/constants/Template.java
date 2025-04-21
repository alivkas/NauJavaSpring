package ru.matveyelovskikh.naujavaspring.constants;

/**
 * Константы для шаблонов
 */
public class Template {

    public static final String REPORT_TEMPLATE = """
    <!DOCTYPE html>
    <html>
    <head>
        <title>Статистика приложения</title>
    </head>
    <body>
    <h1>Статистика приложения</h1>
                    
    <h2>Общая информация</h2>
    <p>Количество пользователей: %d</p>
    <p>Время подсчета пользователей: %d мс</p>
                    
    <h2>Список сущностей</h2>
    <p>Время получения списка: %d мс</p>
    <table>
        <tr>
            <th>Дата</th>
            <th>Сообщение</th>
            <th>ID пользователя</th>
            <th>Категория</th>
            <th>Локация</th>
        </tr>
        %s
    </table>
                    
    <p><strong>Общее время формирования отчета: %d мс</strong></p>
    </body>
    </html>
    """;
}
