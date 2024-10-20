package com.fittrack.fit_track.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fittrack.fit_track.model.Bloc;
import com.fittrack.fit_track.repository.BlocRepository;

@RestController
@RequestMapping("/api/blocs")
public class BlocController {

    @Autowired
    private BlocRepository blocRepository;

    @GetMapping
    public List<Bloc> getAllBlocs() {
        return blocRepository.findAll();
    }

    @PostMapping
    public Bloc createBloc(@RequestBody Bloc bloc) {
        return blocRepository.save(bloc);
    }

    // Ajoutez d'autres méthodes CRUD ici...
}