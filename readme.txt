0. consiglio di scaricare Spring Tool Suite:
	- https://spring.io/tools
	
1. se non hai scaricato postgreSQL:
	- https://sbp.enterprisedb.com/getfile.jsp?fileid=1258781

2. cambia i percorsi nel codice Java:
	    File diarioPersonale = new File("C:\\Users\\LolloR\\Desktop\\diario.txt");
	    diarioPersonale.createNewFile();
	    FileWriter myWriter = new FileWriter("C:\\Users\\LolloR\\Desktop\\diario.txt");

3. elimina il file da_eliminare.txt dalla directory/cartella database

4. in crea_database.txt e riavvio_database.txt cambia i percorsi

5. esegui i comandi scritti in crea_database.txt in Spring Tool Suite:
	-> Ctrl + Alt + Shift + T
	
	SE hai fatto OPZIONALE esegui:
	- cd ..\crea_database.bat
	- crea_database.bat
	
	SE NON hai fatto OPZIONALE:
	- copia, incolla ed esegui uno ad uno i comandi
	
6. con pgAdmin4 esegui copiando, incollando e selezionando il contenuto di 
	modifica_tabella.txt in Query Tool della tabella frasi_diario, per espandere 
	la capacità del campo frase da massimo 255 caratteri a circa un 1 Gb di capienza

OPZIONALE:
4+. una volta cambiati i percorsi, puoi cambiare l'estensione dei file in:
	- crea_database.bat
	- riavvio_database.bat 
	
	per creare uno script
