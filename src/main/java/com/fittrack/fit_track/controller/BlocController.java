package com.fittrack.fit_track.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fittrack.fit_track.dto.BlocDTO;
import com.fittrack.fit_track.mapper.BlocMapper;
import com.fittrack.fit_track.model.Bloc;
import com.fittrack.fit_track.repository.BlocRepository;

@RestController
@RequestMapping("/api/blocs")
public class BlocController {

    @Autowired
    private BlocRepository blocRepository;

    @GetMapping
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<List<BlocDTO>> getAllBlocs() {
        List<Bloc> blocs = blocRepository.findAll();
        List<BlocDTO> blocDTOs = blocs.stream()
                                        .map(BlocMapper.INSTANCE::blocToBlocDTO)
                                        .toList();

        return ResponseEntity.ok(blocDTOs);
    }

    @PostMapping
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<BlocDTO> createBloc(@RequestBody BlocDTO blocDTO) {
        Bloc bloc = BlocMapper.INSTANCE.blocDTOToBloc(blocDTO);
        Bloc savedBloc = blocRepository.save(bloc);
        BlocDTO savedBlocDTO = BlocMapper.INSTANCE.blocToBlocDTO(savedBloc);
        return new ResponseEntity<>(savedBlocDTO, HttpStatus.CREATED);
    }
}