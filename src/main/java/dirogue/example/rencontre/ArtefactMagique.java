package dirogue.example.rencontre;

import dirogue.example.code_squelette.RencontreType;

public class ArtefactMagique extends Tresor {

    @Override
    public String rencontre() {
        return "Artefact Magique! Quelle chance!";
    }

    @Override
    public String getSprite() {
        return "src/main/resources/images/artefact.png";
    }

    @Override
    public RencontreType essence() {
        return RencontreType.TRESOR;
    }

}
