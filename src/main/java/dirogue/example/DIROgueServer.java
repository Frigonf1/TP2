package dirogue.example;

import java.io.IOException;

import dirogue.example.code_squelette.*;

/**
 * Classe représentant le serveur pour l'application DIROgue.
 * Ce serveur écoute les commandes provenant du client, telles que la création
 * de pièces, l'ajout de corridors, la fin de la création du labyrinthe, la
 * sauvegarde du rapport d'aventure, etc.
 */
public class DIROgueServer {
	static boolean exterieurAjoute = false;
	static MonLabyrinthe2 l = new MonLabyrinthe2();
	static MonAventure m = null;

	public static void main(String[] args) {

		try {
			Server s = new Server(1370);

			s.addEventHandler((cmd, cmdArgs) -> {
				if (cmd.equals("piece")) {
					if (cmdArgs.length == 2) {
						int id = Integer.parseInt(cmdArgs[0]);
						RencontreType type = RencontreType.valueOf(cmdArgs[1].toUpperCase());
						if (!exterieurAjoute) {
							l.ajouteEntree(Exterieur.getExterieur(), new Piece(id, type));
							exterieurAjoute = true;
						} else {
							l.ajoutePiece(new Piece(id, type));
						}
					}
				}
			});

			s.addEventHandler((cmd, cmdArgs) -> {
				if (cmd.equals("CORRIDORS")) {
					System.out.println("CORRIDORS commande recue...");
				}
			});

			// Lorsque la commande "corridor" est entrée, on ajoute un corridor entre les deux
			// pièces entrées en argument via la fonction ajouteCorridor. Il y a erreur si la pièce
			// n'est pas trouvée.
            s.addEventHandler((cmd, cmdArgs) -> {
                if (cmd.equals("corridor")) {
                    int id = Integer.parseInt(cmdArgs[0]);
                    int id2 = Integer.parseInt(cmdArgs[1]);
                    try {
                        l.ajouteCorridor(id, id2);
                    } catch (PieceNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                }
            });

			s.addEventHandler((cmd, cmdArgs) -> {
				if (cmd.equals("FIN")) {
					System.out.println("FIN commande recue...");
					m = new MonAventure(l);
				}
			});

			// Lorsque la commande "save" est entrée, le file est sauvegardé via la
			// fonction sauvegarderRapport.
			s.addEventHandler((cmd, cmdArgs) -> {
				if (cmd.equals("save")) {
					String path = cmdArgs[0];
                    try {
                        m.sauvegarderRapport(path);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
				}
			});

			s.listen();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
