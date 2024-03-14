package it.corvallis.diario.controller;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import it.corvallis.diario.models.DiarioEntry;
import it.corvallis.diario.repository.DiarioEntryRepository;

@RestController
@Tag(name = "Diario Personale")
public class DiarioPersonaleController {

	@Autowired
    private DiarioEntryRepository repository;
	
	@PostMapping("/scrivi")
    @Operation(
		summary = "Aggiungi del testo al database",
		operationId = "addPhrasePOST",
	    responses = {
            @ApiResponse(
            	responseCode = "200", 
            	description = "Ok", 
            	content = @Content(	mediaType = "application/json")),
            @ApiResponse(
        		responseCode = "400", 
        		description = "Bad Request", 
        		content = @Content(	mediaType = "application/json"))
        }
    )
	public void addPhrase(@RequestBody String fraseNuova) {
	    DiarioEntry entry = new DiarioEntry();
	    entry.setData(printData());
	    entry.setFrase(fraseNuova);
	    repository.save(entry);

	}	
	
	@DeleteMapping("/elimina")
    @Operation(
		summary = "Elimina del testo dal database",
		operationId = "deletePhraseDELETE",
	    responses = {
            @ApiResponse(
            	responseCode = "200", 
            	description = "Ok", 
            	content = @Content(	mediaType = "application/json")),
            @ApiResponse(
        		responseCode = "400", 
        		description = "Bad Request", 
        		content = @Content(	mediaType = "application/json"))
        }
    )
	public void deletePhrase(
			@RequestParam 
			@Parameter(description = "Data nel formato 2024-03-10_20:44") String data) {
	    List<DiarioEntry> entriesToDelete = new ArrayList<>();
	    entriesToDelete = repository.findByData(data.substring(0, 16));
	    
	    for (DiarioEntry entry : entriesToDelete) {
	        repository.delete(entry);
	    }

	}
	
	@PutMapping("/aggiorna")
    @Operation(
		summary = "Aggiorna del testo dal database",
		operationId = "updatePhrasePUT",
	    responses = {
            @ApiResponse(
            	responseCode = "200", 
            	description = "Ok", 
            	content = @Content(	mediaType = "application/json")),
            @ApiResponse(
        		responseCode = "400", 
        		description = "Bad Request", 
        		content = @Content(	mediaType = "application/json"))
        }
    )
	public void updatePhrase(	
			@RequestParam 
			@Parameter(description = "Data nel formato 2024-03-10_20:44") String dataVecchia, 
			@RequestParam String fraseNuova) {
	    List<DiarioEntry> entries = new ArrayList<>();
	    entries = repository.findByData(dataVecchia.substring(0, 16));
		if (entries.size() != 0) {
	        DiarioEntry entry = entries.get(0);
	        entry.setFrase(fraseNuova);
	        repository.save(entry);
	    }

	}

	@GetMapping("/ricerca")
    @Operation(
		summary = "Ricerca del testo dal database",
		operationId = "findPhrasesWithWordGET",
	    responses = {
            @ApiResponse(
            	responseCode = "200", 
            	description = "Ok", 
            	content = @Content(	mediaType = "application/json")),
            @ApiResponse(
        		responseCode = "400", 
        		description = "Bad Request", 
        		content = @Content(	mediaType = "application/json"))
        }
    )
	public List<String> phrasesWithWord(@RequestParam String parolaChiave) {
	    List<String> frasiTrovate = new ArrayList<>();
	    List<DiarioEntry> entries = repository.findAll();
	    for (DiarioEntry entry : entries) {
	        if (entry.getFrase().contains(parolaChiave)) {
	            frasiTrovate.add(entry.getData().substring(0, 16) + " : " + entry.getFrase());
	        }
	    }
	    
	    return frasiTrovate;
	}

	@GetMapping("/frasi-in-data")
	@Operation(
	    summary = "Visualizza le frasi scritte in una data specifica",
	    operationId = "getPhrasesInDateGET",
	    responses = {
	        @ApiResponse(
	            responseCode = "200",
	            description = "Ok",
	            content = @Content(mediaType = "application/json")),
	        @ApiResponse(
	            responseCode = "400",
	            description = "Bad Request",
	            content = @Content(mediaType = "application/json"))
	    }
	)
	public List<String> getPhrasesInDate(
		    @RequestParam 
		    @Parameter(description = "Data nel formato 2024-03-10") String data) {
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	    LocalDate searchDate = LocalDate.parse(data, formatter);

	    List<String> phrasesInDate = new ArrayList<>();
	    List<DiarioEntry> entries = repository.findAll();
	    for (DiarioEntry entry : entries) {
	        LocalDate entryDate = LocalDate.parse(entry.getData().substring(0, 10), formatter);
	        if (entryDate.equals(searchDate)) {
	            phrasesInDate.add(entry.getData().substring(0, 16) + " : " + entry.getFrase());
	        }
	    }

	    return phrasesInDate;
	}
	
	@PostMapping("/file-diario")
    @Operation(
		summary = "Crea file che contiene tutto il testo nel database",
		operationId = "createFilePOST",
	    responses = {
            @ApiResponse(
            	responseCode = "200", 
            	description = "Ok", 
            	content = @Content(	mediaType = "application/json")),
            @ApiResponse(
        		responseCode = "400", 
        		description = "Bad Request", 
        		content = @Content(	mediaType = "application/json"))
        }
    )
	public void createFileDiario() throws IOException { 
	    File diarioPersonale = new File("C:\\Users\\LolloR\\Desktop\\diario.txt");
	    diarioPersonale.createNewFile();
	    FileWriter myWriter = new FileWriter("C:\\Users\\LolloR\\Desktop\\diario.txt");

	    List<DiarioEntry> entries = repository.findAll();
	    for (DiarioEntry entry : entries) {
	        myWriter.write(entry.getData() + " : " + entry.getFrase() + "\n");
	    }
	    myWriter.close();

	}
	
    public static String printData() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH:mm");
        String dataDiScrittura = now.format(formatter);
        
        return dataDiScrittura;
    }
}