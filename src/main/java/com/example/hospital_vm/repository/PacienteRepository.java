package com.example.hospital_vm.repository;

import com.example.hospital_vm.model.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Integer> {
    boolean existsByRut(String rut);
    boolean existsByRutAndIdNot(String rut, int id);  // Corregido: añadido parámetro id
}