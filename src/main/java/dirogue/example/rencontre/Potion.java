package dirogue.example.rencontre;

import dirogue.example.code_squelette.RencontreType;

public class Potion extends Tresor {

    @Override
    public String rencontre() {
        return "Potion! Quelle chance!";
    }

    @Override
    public String getSprite() {
        return "src/main/resources/images/potion.png";
    }
    
    @Override
    public RencontreType essence() {
        return RencontreType.TRESOR;
    }
}
