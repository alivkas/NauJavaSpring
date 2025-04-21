package ru.matveyelovskikh.naujavaspring.mapstruct;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.matveyelovskikh.naujavaspring.dto.ReportDto;
import ru.matveyelovskikh.naujavaspring.entity.ReportEntity;

/**
 * Маппер отчета
 */
@Mapper(componentModel = "spring")
public interface ReportMapper {

    /**
     * Маппинг в сущность
     * @param dto дто
     * @return сущность
     */
    @Mapping(target = "status", constant = "CREATED")
    ReportEntity toEntity(ReportDto dto);

    /**
     * Маппинг в дто
     * @param entity сущность
     * @return дто
     */
    ReportDto toDto(ReportEntity entity);
}
