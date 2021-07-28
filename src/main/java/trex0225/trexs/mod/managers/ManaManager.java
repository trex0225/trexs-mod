package trex0225.trexs.mod.managers;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.world.Difficulty;

public class ManaManager {
    private int manaLevel = 20;
    private int manaTimer = 0;
    
    public void add(int mana) {
        this.manaLevel = Math.min(mana + manaLevel, 20);
    }

    public void sutract(int mana) {
        this.manaLevel = Math.max(manaLevel-mana, 0);
    }

    public void readNbt(NbtCompound tag) {
        if (tag.contains("manaLevel", 99)) {
            this.manaLevel = tag.getInt("manaLevel");
        }
    }

    public void writeNbt(NbtCompound tag) {
        tag.putInt("manaLevel", this.manaLevel);
    }

    public int getManaLevel() {
        return this.manaLevel;
    }

    public boolean isNotFull() {
        return this.manaLevel < 20;
    }

    public void setManaLevel(int manaLevel) {
        this.manaLevel = manaLevel;
    }

    public void update(PlayerEntity player) {
        Difficulty difficulty = player.world.getDifficulty();
        manaTimer++;
        if(manaTimer==40) {
            this.add(1);
            manaTimer = 0;
            //System.out.println(this.getManaLevel());
        }
    }
}
