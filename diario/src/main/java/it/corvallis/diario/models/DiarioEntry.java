package it.corvallis.diario.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import io.swagger.v3.oas.annotations.media.Schema;

@Entity
@Table(name = "frasi_diario")
public class DiarioEntry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Schema(description = "Formato richiesto: yyyy-MM-dd_HH:mm", 
    		example = "2024-03-10_20:37")
    private String data;
    private String frase;
    
    public String getData() {
        return data;
    }

    public String getFrase() {
        return frase;
    }

    public void setData(String data) {
        this.data = data;
    }

    public void setFrase(String frase) {
        this.frase = frase;
    }
}
