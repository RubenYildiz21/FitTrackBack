// src/main/java/com/fittrack/fit_track/mapper/SeriesMapper.java
package com.fittrack.fit_track.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.fittrack.fit_track.dto.SeriesDTO;
import com.fittrack.fit_track.model.Series;

@Mapper
public interface SeriesMapper {
    SeriesMapper INSTANCE = Mappers.getMapper(SeriesMapper.class);
    
    SeriesDTO seriesToSeriesDTO(Series series);
    Series seriesDTOToSeries(SeriesDTO seriesDTO);
}
