package dirogue.example.rencontre;

import dirogue.example.code_squelette.RencontreType;

public class Gargouille extends Monstre {

    @Override
    public String rencontre() {
        return "Un Gargouille affreux!";
    }

    @Override
    public String getSprite() {
        return "src/main/resources/images/gargouille.png";
    }

    @Override
    public RencontreType essence() {
        return RencontreType.MONSTRE;
    }
    
}
