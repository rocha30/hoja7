
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Clase principal que ejecuta el traductor Inglés-Español.
 * Lee un archivo de diccionario y utiliza sus definiciones para traducir un archivo de texto.
 */
public class Main {
    /**
     * El punto de entrada principal del programa.
     *
     * @param args Los argumentos de la línea de comandos.
     */
    public static void main(String[] args) {
        System.out.println("Bienvenido al Traductor Inglés-Español.");
        System.out.println("Este programa traducirá el contenido de 'texto.txt' usando el diccionario definido en 'diccionario.txt'.");

        // Crea un árbol binario para almacenar las asociaciones del diccionario.
        BinaryTree<Association<String, String>> dictionary = new BinaryTree<>();
        // Define las rutas a los archivos de diccionario y texto.
        final String dictionaryPath = "src\\main\\java\\uvg\\edu\\gt\\diccionario.txt";
        final String textPath = "src\\main\\java\\uvg\\edu\\gt\\texto.txt";

        // Carga las palabras y sus traducciones en el diccionario.
        System.out.println("\nCargando diccionario desde " + dictionaryPath + "...");
        try (BufferedReader br = new BufferedReader(new FileReader(dictionaryPath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.replaceAll("[()]", "").split(" ");
                if (parts.length == 2) {
                    dictionary.insert(new Association<>(parts[0].toLowerCase(), parts[1]));
                }
            }
            System.out.println("Diccionario cargado exitosamente.");
        } catch (IOException e) {
            System.err.println("Error al leer el archivo diccionario.txt: " + e.getMessage());
            return;
        }

        // Traduce las palabras del archivo texto.txt utilizando el diccionario.
        System.out.println("\nTraduciendo el archivo " + textPath + "...");
        try (BufferedReader br = new BufferedReader(new FileReader(textPath))) {
            String line;
            while ((line = br.readLine()) != null) {
                for (String word : line.split(" ")) {
                    String cleanedWord = word.toLowerCase().replaceAll("[^a-zA-Z]", "");
                    Association<String, String> searchResult = dictionary.find(new Association<>(cleanedWord, ""));
                    if (searchResult != null) {
                        System.out.print(searchResult.getValue() + " ");
                    } else {
                        System.out.print("*" + word + "* ");
                    }
                }
                System.out.println();
            }
        } catch (IOException e) {
            System.err.println("Error al leer el archivo texto.txt: " + e.getMessage());
        }

        // Indica que la traducción se ha completado.
        System.out.println("\nTraducción completada. ¡Gracias por utilizar el traductor!");
    }
}
