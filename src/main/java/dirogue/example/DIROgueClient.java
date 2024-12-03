package dirogue.example;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

/**
 * Classe représentant un client pour l'application DIROgue. Ce client se
 * connecte à un serveur spécifique et peut envoyer des commandes pour charger,
 * sauvegarder des fichiers ou quitter l'application.
 */
public class DIROgueClient {
	public static void main(String[] args) {
		String serverAddress = "127.0.0.1";
		int serverPort = 1370;

		// On crée un socket prenant en argument serverAddress ("127.0.0.1") et serverPort (1370).
		// Ensuite, on imprime un message laissant savoir à l'usager qu'il est connecté.
        try {

            Socket socket = new Socket(serverAddress, serverPort);
            PrintWriter out = new PrintWriter(socket.getOutputStream()); // utilisé pour écrire dans le socket avec des commandes comme println()
			Scanner scanner = new Scanner(System.in);

			System.out.println("Connecté au serveur " + serverAddress + ":" + serverPort);

			String input;

		while (true) {
			System.out.println("Entrer une commande (load, save, exit):");
			input = scanner.nextLine().trim();


			// On demande à l'usager d'entrer le chemin absolu vers le fichier. Le chemin entré est enregistré
			// dans fileName, puis lu par un BufferedReadeer, puis imprimé.
			if (input.equals("load")) {
					System.out.println("Veuilez entrer le file path");
					String fileName = scanner.nextLine().trim();
				try (BufferedReader fileReader = new BufferedReader(new FileReader(fileName))) {
					String line;
					while ((line = fileReader.readLine()) != null) {
						System.out.println(line);
					}
					System.out.println("Fichier envoyé au serveur.");
				} catch (IOException e) {
					System.out.println("Erreur lors de la lecture du fichier : " + e.getMessage());
				}

			} else if (input.equals("save")) {
				System.out.println(" Entrez le chemin où vous voulez sauvegarder le rapport :");
				var reportPath = scanner.nextLine().trim();
				out.println(input + " " + reportPath);

			} else if (input.equals("exit")) {
				out.println(input);
				break;
			} else {
				System.out.println("Commande non valide. Veuillez entrer 'load', 'save' ou 'exit'.");
			}
		}

		System.out.println("Sortie du programme.");
        scanner.close();
        if (out != null) {
            out.close();
        }
        if (socket != null) {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
