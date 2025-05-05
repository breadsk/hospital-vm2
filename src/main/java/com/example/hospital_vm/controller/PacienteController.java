package com.example.hospital_vm.controller;

import com.example.hospital_vm.model.Paciente;
import com.example.hospital_vm.service.PacienteService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Optional;
import java.net.URI;
import java.time.LocalDateTime;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/v1/pacientes")
public class PacienteController {

    private final PacienteService pacienteService;

    public PacienteController(PacienteService pacienteService){
        this.pacienteService = pacienteService;
    }

    
    
    @GetMapping("/listar-pacientes")
    public ResponseEntity<List<Paciente>> listar(){
        try{
            List<Paciente> pacientes = pacienteService.findAll();

            return pacientes.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(pacientes);

            // if(pacientes.isEmpty()){
            //     return ResponseEntity.noContent().build();
            // }
            //return ResponseEntity.ok(pacientes);
        }catch(Exception ex){
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody Paciente paciente){
        try{

            Paciente pacienteGuardado = pacienteService.save(paciente);
            //

            //Uri del nuevo recurso creado 
            URI location = ServletUriComponentsBuilder
                        .fromCurrentRequest()
                        .path("/{id}")
                        .buildAndExpand(pacienteGuardado.getId())
                        .toUri();

            return ResponseEntity
                        .created(location)//Código 201 created
                        .body(pacienteGuardado);
                        

        }catch(DataIntegrityViolationException e){
            Map<String,String> error = new HashMap<>();
            error.put("message","El email ya está registrado");
            return ResponseEntity.status(HttpStatus.CONFLICT).body(error);//404
        }
    }

    // @PostMapping
    // public ResponseEntity<Paciente> guardar(@RequestBody Paciente paciente){
    //     Paciente pacienteNuevo = pacienteService.save(paciente);

    //     return ResponseEntity.status(HttpStatus.CREATED).body(pacienteNuevo);
    // }
    
    


}
