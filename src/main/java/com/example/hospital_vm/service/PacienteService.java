package com.example.hospital_vm.service;

import com.example.hospital_vm.model.Paciente;
import com.example.hospital_vm.repository.PacienteRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PacienteService {

    @Autowired
    private PacienteRepository pacienteRepository;

    public List<Paciente> findAll() {
        return pacienteRepository.findAll();
    }

    public Optional<Paciente> getPacientePorId(int id) {
        return pacienteRepository.findById(id);
    }

    public Paciente getPacientePorId2(int id) {
        return pacienteRepository.findById(id).orElse(null); // Mejor manejo del Optional
    }

    public Paciente save(Paciente paciente) {
        return pacienteRepository.save(paciente);
    }

    public boolean existePacienteConRut(String rut, int idActual) {
        return pacienteRepository.existsByRutAndIdNot(rut, idActual);
    }
}