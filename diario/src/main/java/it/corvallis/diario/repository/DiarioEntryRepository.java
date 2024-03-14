package it.corvallis.diario.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import it.corvallis.diario.models.DiarioEntry;

public interface DiarioEntryRepository extends JpaRepository<DiarioEntry, Long> {
    
    List<DiarioEntry> findByData(String data);
    
    void deleteByData(String data);
}