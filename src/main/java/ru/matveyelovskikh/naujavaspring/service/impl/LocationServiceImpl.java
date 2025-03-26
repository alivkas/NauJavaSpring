package ru.matveyelovskikh.naujavaspring.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.matveyelovskikh.naujavaspring.dto.LocationDto;
import ru.matveyelovskikh.naujavaspring.entity.LocationEntity;
import ru.matveyelovskikh.naujavaspring.mapstruct.LocationMapper;
import ru.matveyelovskikh.naujavaspring.repository.LocationCrud;
import ru.matveyelovskikh.naujavaspring.service.LocationService;

/**
 * Реализация сервиса локации
 */
@Service
public class LocationServiceImpl implements LocationService {

    private final LocationCrud locationCrud;
    private final LocationMapper locationMapper;

    /**
     * Внедрение зависимостей LocationCrud, LocationMapper
     * @param locationCrud crud локации
     * @param locationMapper маппер локации
     */
    @Autowired
    public LocationServiceImpl(LocationCrud locationCrud,
                               LocationMapper locationMapper) {
        this.locationCrud = locationCrud;
        this.locationMapper = locationMapper;
    }

    @Transactional
    @Override
    public LocationEntity getOrCreateCategory(LocationDto locationDto) {
        return locationCrud.findByPlace(locationDto.place())
                .orElseGet(() -> {
                    LocationEntity location = locationMapper.toEntity(locationDto);
                    return locationCrud.save(location);
                });
    }
}
