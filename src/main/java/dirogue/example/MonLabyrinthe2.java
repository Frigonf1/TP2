package dirogue.example;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import dirogue.example.code_squelette.*;

/**
 * La classe MonLabyrinthe2 implémente l'interface Labyrinthe et Serializable.
 * Elle représente un labyrinthe composé de pièces et de corridors qui les
 * relient.
 * Cette classe gère les pièces, les corridors, et fournit des méthodes pour
 * ajouter
 * de nouvelles pièces, relier des pièces par des corridors, obtenir des
 * informations
 * sur les pièces connectées et vérifier l'existence de corridors entre des
 * pièces.
 */
public class MonLabyrinthe2 implements Labyrinthe, Serializable {
    private List<Piece> pieces;
    private Map<Integer, List<Integer>> adjList;

     /**
     * Constructeur de la classe MonLabyrinthe2 initialisant la liste des pièces et l'adjacence.
     */
    public MonLabyrinthe2() {
        pieces = new ArrayList<>();
        adjList = new HashMap<>();
    }

    public Piece[] getPieces() {
        return pieces.toArray(new Piece[0]);
    }

    public int nombreDePieces() {
        return pieces.size();
    }

    public void ajouteEntree(Exterieur out, Piece e) {
        pieces.add(out);
        pieces.add(e);
        addEdge(out, e);
    }

    public void ajouteCorridor(Piece e1, Piece e2) {
        if (getPieceByID(e1.getID()) == null)
            pieces.add(e1);

        if (getPieceByID(e2.getID()) == null)
            pieces.add(e2);

        addEdge(e1, e2);
    }

    // On utilise le Map adjList pour ajouter une e2ID comme adjacent à e1ID, et vice-versa. On appelle
    // aussi la fonction ajouteCorridor(Piece e1, Piece e2), qui ajoute les pièces si elles n'existent pas.
    public void ajouteCorridor(int e1ID, int e2ID) throws PieceNotFoundException {
        ajouteCorridor(pieces.get(e1ID), pieces.get(e2ID));
        this.adjList.get(e1ID).add(e2ID);
        ajouteCorridor(pieces.get(e1ID), pieces.get(e2ID));
        this.adjList.get(e2ID).add(e1ID);
    }

    /**
     * Méthode pour ajouter une nouvelle pièce au labyrinthe.
     *
     * @param e La pièce à ajouter.
     * @return L'index de la pièce ajoutée.
     */
    public int ajoutePiece(Piece e) {
        if (!pieces.contains(e)) {
            pieces.add(e);
        }
        return pieces.indexOf(e);
    }

    public boolean existeCorridorEntre(Piece e1, Piece e2) {
        return adjList.containsKey(e1.getID()) && adjList.get(e1.getID()).contains(e2.getID());
    }

    /**
     * Méthode pour obtenir les pièces connectées à une pièce spécifique.
     *
     * @param e La pièce dont les pièces connectées sont recherchées.
     * @return Un tableau de pièces connectées à la pièce donnée.
     */

    // On crée un nouveau ArrayList de Piece, puis, pour chaque pièce connectée à la Piece e
    // selon le Map ajdList, on ajoute la pièce connectée au ArrayList.
    public Piece[] getPiecesConnectees(Piece e) {
        ArrayList<Piece> connectees = new ArrayList<>(); {
            for(int i: adjList.get(e.getID())) {
                connectees.add(pieces.get(i));
            }
        }
        return (Piece[])connectees.toArray();
    }

    private void addEdge(Piece e1, Piece e2) {
        adjList.computeIfAbsent(e1.getID(), k -> new ArrayList<>()).add(e2.getID());
        adjList.computeIfAbsent(e2.getID(), k -> new ArrayList<>()).add(e1.getID());
    }

    private Piece getPieceByID(int ID) {
        for (Piece piece : pieces) {
            if (piece.getID() == ID) {
                return piece;
            }
        }
        return null;
    }
}
