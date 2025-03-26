package ru.matveyelovskikh.naujavaspring.dto;

/**
 * Дто категории события
 * @param name имя категории
 * @param description описание категории
 */
public record EventCategoryDto(String name,
                               String description) {
}
