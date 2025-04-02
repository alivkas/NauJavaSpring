package ru.matveyelovskikh.naujavaspring.dto;

/**
 * Дто места события
 * @param place место события
 * @param address адрес события
 * @param isOnline онлайн ли проводится событие
 * @param isAvailable доступно ли событие
 */
public record LocationDto(String place,
                          String address,
                          Boolean isOnline,
                          Boolean isAvailable) {
}
