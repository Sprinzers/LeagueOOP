package champion;

import util.Constants;

import java.util.ArrayList;

public abstract class Champion {
    private char preferredTerrain;
    private float terrainModifier;
    private int hp;
    private int hpStart;
    private int hpGrowth;
    private int xp;
    private int level;
    private boolean applyTerrainModifier;
    private ArrayList<Integer> damageOverTime = new ArrayList<Integer>();
    private int posX;
    private int posY;
    private int id;
    private int incapacitated;
    private boolean foughtThisRound;
    private float firstAbilityBase;
    private float firstAbilityGrowth;
    private float secondAbilityBase;
    private float secondAbilityGrowth;
    private int roundCounter;

    /**
     *  Method is used to move the champion
     * @param newMove direction of the move
     */
    public void makeMove(char newMove) {
        switch (newMove) {
            case 'U':
                --posX;
                break;
            case 'D':
                ++posX;
                break;
            case 'R':
                ++posY;
                break;
            case 'L':
                --posY;
                break;
            case '_':
                break;
            default:
                break;
        }
    }

    public String printFinalStats() {
        if (isAlive()) {
            return (getName() + " " + level + " " + xp + " " + hp + " "
                    + posX + " " + posY);
        } else {
            return (getName() + " dead");
        }
    }

    /**
     *  Method is used to determine if champion is a valid opponent.
     * @param champion opponent that "this" will fight
     * @return true if the fight can take place, false otherwise
     */
    public boolean verifyOpponent(Champion champion) {
        return this.posX == champion.getPosX() && this.posY == champion.getPosY()
                && this.id != champion.getId() && !this.foughtThisRound
                && !champion.getFoughtThisRound();
    }

    /**
     * Method is used to award XP to the winner after a fight and level him up, if
     * necessary.
     * @param levelLoser level of the champion that died after the fight
     * @return true if the winner accumulated enough XP to level up, false if otherwise
     */
    public boolean awardXp(int levelLoser) {
        int levelDiff = getLevel() - levelLoser;
        int xpWinner = Math.max(0, 200 - levelDiff * 40);
        xp += xpWinner;

        boolean leveledUp = false;
        while (xp > Constants.LEVEL_UP_XP + Constants.LEVEL_UP_XP_GROWTH * level) {
            ++level;
            leveledUp = true;
        }
        return leveledUp;
    }

    /**
     *  Method is used to test if the champion is alive or dead.
     * @return true if the champion is alive, false if he is dead
     */
    public boolean isAlive() {
        return hp > 0;
    }

    /**
     *  Method is used to determine if the champion is incapacitated.
     *  The field "incapacitated" stores the number of rounds he will be under the effect.
     * @return true if the champion is under an incapacitation effect
     */
    public boolean isIncapacitated() {
        if (incapacitated > 0) {
            --incapacitated;
            return true;
        }
        return false;
    }

    /**
     *  Method is used to apply the effect of the abilities that have a damage over time
     *  (DOT - how is called in MOBAs) effect.
     */
    public void applyDamageOverTime() {
        if (damageOverTime.size() > 0) {
            reduceHP(damageOverTime.get(0));
            damageOverTime.remove(0);
        }
    }

    /**
     *  Method is used to store the damage over time from the specific abilites.
     * @param damage damage to be taken over time
     * @param rounds the number of rounds the DOT effect will be active
     */
    public void addDamageOverTime(int damage, int rounds) {
        for (int i = 0; i < rounds; ++i) {
            damageOverTime.add(damage);
        }
    }

    /**
     *  Method is used to overwrite the current damage over time effects when the champion is hit
     *  with a new ability that has a damage over time effect.
     */
    public void resetDamageOverTime() {
        damageOverTime.clear();
    }

    /**
     *  Method is used to determine if the terrain the champion is sitting on will give him
     *  a bonus.
     * @param terrainType the type of the current location of the champion
     */
    public void hasTerrainModifier(char terrainType) {
        if (preferredTerrain == terrainType) {
            applyTerrainModifier = true;
        } else {
            applyTerrainModifier = false;
        }
    }

    public int calculateTeoreticalHp() {
        return hpStart + hpGrowth * level;
    }

    public ArrayList<Integer> getDamageOverTime() {
        return damageOverTime;
    }

    public char getName() {
        return getClass().getName().charAt(Constants.NAME_INDEX);
    }

    public boolean getApplyTerrainModifier() {
        return applyTerrainModifier;
    }

    public boolean getFoughtThisRound() {
        return foughtThisRound;
    }

    public void setFoughtThisRound(boolean fightStatus) {
        foughtThisRound = fightStatus;
    }

    public void setIncapacitated(int incapacitated) {
        this.incapacitated = incapacitated;
    }

    public int getRoundCounter() {
        return roundCounter;
    }

    public void setRoundCounter(int newRoundCounter) {
        roundCounter = newRoundCounter;
    }

    public void increaseRoundCounter() {
        ++roundCounter;
    }

    public void setXp(int newXP) {
        xp = newXP;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getHpGrowth() {
        return hpGrowth;
    }

    public void setHpGrowth(int newHpGrowth) {
        this.hpGrowth = newHpGrowth;
    }

    public void setHpStart(int newHpStart) {
        this.hpStart = newHpStart;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    private int getId() {
        return id;
    }

    public void setId(int newId) {
        id = newId;
    }

    public char getPreferredTerrain() {
        return preferredTerrain;
    }

    public void setPreferredTerrain(char preferredTerrain) {
        this.preferredTerrain = preferredTerrain;
    }

    public float getTerrainModifier() {
        return terrainModifier;
    }

    public void setTerrainModifier(float terrainModifier) {
        this.terrainModifier = terrainModifier;
    }

    public void setFirstAbilityBase(float firstAbilityBase) {
        this.firstAbilityBase = firstAbilityBase;
    }

    public void setFirstAbilityGrowth(float firstAbilityGrowth) {
        this.firstAbilityGrowth = firstAbilityGrowth;
    }

    public void setSecondAbilityBase(float secondAbilityBase) {
        this.secondAbilityBase = secondAbilityBase;
    }

    public void setSecondAbilityGrowth(float secondAbilityGrowth) {
        this.secondAbilityGrowth = secondAbilityGrowth;
    }

    public float firstAbility() {
        return firstAbilityBase + firstAbilityGrowth * level;
    }

    public float secondAbility() {
        return secondAbilityBase + secondAbilityGrowth * level;
    }

    public void reduceHP(int damage) {
        hp -= damage;
    }

    /**
     *  Method is used to restore HP to 100% after level up.
     *  Method is abstract because every champion has different initial HP values
     *  and different HP per level growth.
     */
    public void restoreHP() {
        hp = hpStart + hpGrowth * level;
    }

    public abstract void isAttackedBy(Champion champion);

    public abstract void attack(Knight knight);

    public abstract void attack(Pyromancer pyromancer);

    public abstract void attack(Rogue rogue);

    public abstract void attack(Wizard wizard);
}
