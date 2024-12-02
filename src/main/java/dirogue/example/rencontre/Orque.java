package dirogue.example.rencontre;

import dirogue.example.code_squelette.RencontreType;

public class Orque extends Monstre {

    @Override
    public String rencontre() {
        return "Un Orque affreux!";
    }

    @Override
    public String getSprite() {
        return "src/main/resources/images/orque.png";
    }

    @Override
    public RencontreType essence() {
        return RencontreType.MONSTRE;
    }
    
}
