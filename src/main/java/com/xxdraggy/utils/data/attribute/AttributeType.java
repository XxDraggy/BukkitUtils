package com.xxdraggy.utils.data.attribute;

import org.bukkit.attribute.Attribute;

public class AttributeType {
    public static class Generic {
        Attribute Health = Attribute.GENERIC_MAX_HEALTH;
        Attribute FollowRange = Attribute.GENERIC_FOLLOW_RANGE;
        Attribute Luck = Attribute.GENERIC_LUCK;

        Attribute HorseJumpStrength = Attribute.HORSE_JUMP_STRENGTH;
        Attribute ZombieSpawnReinforcements = Attribute.ZOMBIE_SPAWN_REINFORCEMENTS;

        public static class Armor {
            Attribute Protection = Attribute.GENERIC_ARMOR;
            Attribute Durability = Attribute.GENERIC_ARMOR_TOUGHNESS;
        }

        public static class Speed {
            Attribute Attack = Attribute.GENERIC_ATTACK_SPEED;
            Attribute Fly = Attribute.GENERIC_FLYING_SPEED;
            Attribute Movement = Attribute.GENERIC_MOVEMENT_SPEED;
        }

        public static class Combat {
            Attribute Damage = Attribute.GENERIC_ATTACK_DAMAGE;
            Attribute Knockback = Attribute.GENERIC_ATTACK_KNOCKBACK;
            Attribute KnockbackResistance = Attribute.GENERIC_KNOCKBACK_RESISTANCE;
            Attribute Speed = Attribute.GENERIC_ATTACK_SPEED;
        }
    }
}
