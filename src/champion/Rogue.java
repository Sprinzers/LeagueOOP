package champion;

import util.Constants;

public class Rogue extends Champion {

    Rogue(int newPosX, int newPosY) {
        setPosX(newPosX);
        setPosY(newPosY);
        setPreferredTerrain(Constants.PYRO_PREFERRED_TERRAIN);
        setTerrainModifier(Constants.PYRO_TERRAIN_MODIFIER);
        setHp(Constants.PYROMANCER_HP);
        setHpStart(Constants.PYROMANCER_HP);
        setHpGrowth(Constants.PYROMANCER_HP_GROWTH);
        setXp(0);
        setLevel(0);
        setIncapacitated(0);
        setFoughtThisRound(false);
        setFirstAbilityBase(Constants.PYRO_ABILITY_1_BASE);
        setFirstAbilityGrowth(Constants.PYRO_ABILITY_1_GROWTH);
        setSecondAbilityBase(Constants.PYRO_ABILITY_2_BASE);
        setSecondAbilityGrowth(Constants.PYRO_ABILITY_2_GROWTH);
    }

    @Override
    public void isAttackedBy(Champion champion) {
        champion.attack(this);
    }

    @Override
    public void attack(Knight knight) {

    }

    @Override
    public void attack(Pyromancer pyromancer) {

    }

    @Override
    public void attack(Rogue rogue) {

    }

    @Override
    public void attack(Wizard wizard) {

    }
}
