package trex0225.trexs.mod.managers;

import net.minecraft.nbt.NbtCompound;

public class StatManager {
    private int meleeDamageBonus = 0;
    private int rangedDamageBonus = 0;
    private int magicDamageBonus = 0;

    public void addMelee(int melee) {
        this.meleeDamageBonus+=melee;
    }
    public void addRanged(int ranged) {
        this.rangedDamageBonus+=ranged;
    }
    public void addMagic(int magic) {
        this.magicDamageBonus+=magic;
    }
    
    public void removeMelee(int melee) {
        this.meleeDamageBonus-=melee;
    }
    public void removeRanged(int ranged) {
        this.rangedDamageBonus-=ranged;
    }
    public void removeMagic(int magic) {
        this.magicDamageBonus-=magic;
    }

    public void setMelee(int melee) {
        this.meleeDamageBonus=melee;
    }
    public void setRanged(int ranged) {
        this.rangedDamageBonus=ranged;
    }
    public void setMagic(int magic) {
        this.magicDamageBonus=magic;
    }

    public void readNbt(NbtCompound tag) {
        if (tag.contains("meleeBonus", 99)) {
            this.meleeDamageBonus = tag.getInt("meleeBonus");
        }
        if (tag.contains("rangedBonus", 99)) {
            this.meleeDamageBonus = tag.getInt("rangedBonus");
        }
        if (tag.contains("magicBonus", 99)) {
            this.meleeDamageBonus = tag.getInt("magicBonus");
        }
    }

    public void writeNbt(NbtCompound tag) {
        tag.putInt("meleeBonus", this.meleeDamageBonus);
        tag.putInt("rangedBonus", this.rangedDamageBonus);
        tag.putInt("magicBonus", this.magicDamageBonus);
    }

    public int getMelee() {
         return meleeDamageBonus;
    }
    public int getRanged() {
        return rangedDamageBonus;
    }
    public int getMagic() {
        return magicDamageBonus;
    }

}
