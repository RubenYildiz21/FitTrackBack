package com.fittrack.fit_track.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.fittrack.fit_track.dto.ExerciceDTO;
import com.fittrack.fit_track.model.Exercice;

@Mapper
public interface ExerciceMapper {
    ExerciceMapper INSTANCE = Mappers.getMapper(ExerciceMapper.class);
    
    ExerciceDTO exerciceToExerciceDTO(Exercice exercice);
    Exercice exerciceDTOToExercice(ExerciceDTO exerciceDTO);
}
