package com.craftaro.skyblock.utils.world.block;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.Skull;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Locale;

public final class legacyBlockRelfection {

    private legacyBlockRelfection() {
    }

    // Reflectively call block.getData() (deprecated since 1.13)
    public static byte getLegacyBlockData(Block block) {
        try {
            Method getDataMethod = block.getClass().getMethod("getData");
            Object data = getDataMethod.invoke(block);
            if (data instanceof Number) {
                return ((Number) data).byteValue();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    // Reflectively call block.setData(byte) (deprecated since 1.13)
    public static void setLegacyBlockData(Block block, byte data) {
        try {
            Method setDataMethod = block.getClass().getMethod("setData", byte.class);
            setDataMethod.invoke(block, data);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Reflectively call getFacing() on legacy Stairs
    public static BlockFace getLegacyStairsFacing(Object legacyStairs) {
        try {
            Method getFacingMethod = legacyStairs.getClass().getMethod("getFacing");
            Object face = getFacingMethod.invoke(legacyStairs);
            if (face instanceof BlockFace) {
                return (BlockFace) face;
            } else {
                return BlockFace.valueOf(face.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return BlockFace.NORTH; // default fallback
    }

    // Reflectively call setFacingDirection(BlockFace) on legacy Stairs
    public static void setLegacyStairsFacing(Object legacyStairs, BlockFace facing) {
        try {
            Method setFacingMethod = legacyStairs.getClass().getMethod("setFacingDirection", BlockFace.class);
            setFacingMethod.invoke(legacyStairs, facing);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Reflectively create a legacy FlowerPot instance given a Material and data value
    public static Object createLegacyFlowerPot(Material material, byte data) {
        try {
            Class<?> flowerPotClass = Class.forName("org.bukkit.material.FlowerPot");
            Constructor<?> constructor = flowerPotClass.getConstructor(Material.class, byte.class);
            return constructor.newInstance(material, data);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // Reflectively get the contents of a legacy FlowerPot (if needed)
    public static Object getLegacyFlowerPotContents(Object legacyFlowerPot) {
        try {
            Method getContentsMethod = legacyFlowerPot.getClass().getMethod("getContents");
            return getContentsMethod.invoke(legacyFlowerPot);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // Reflectively call getSkullType() on a Skull (deprecated since 1.13)
    public static String getLegacySkullType(Skull skull) {
        try {
            Method getSkullTypeMethod = skull.getClass().getMethod("getSkullType");
            Object type = getSkullTypeMethod.invoke(skull);
            return type.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "PLAYER"; // default fallback
    }

    // Reflectively call setSkullType(SkullType) on a Skull
    public static void setLegacySkullType(Skull skull, String typeName) {
        try {
            Class<?> skullTypeClass = Class.forName("org.bukkit.SkullType");
            @SuppressWarnings("unchecked")
            Object enumConstant = Enum.valueOf((Class<Enum>) skullTypeClass, typeName.toUpperCase());
            Method setSkullTypeMethod = skull.getClass().getMethod("setSkullType", skullTypeClass);
            setSkullTypeMethod.invoke(skull, enumConstant);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    // Reflectively obtains an instance of PatternType given a string name.
    public static Object getPatternType(String patternName) {
        try {
            Class<?> patternTypeClass = Class.forName("org.bukkit.block.banner.PatternType");
            Method valueOfMethod = patternTypeClass.getMethod("valueOf", String.class);
            return valueOfMethod.invoke(null, patternName.toUpperCase());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public static Object getLegacyMaterialData(org.bukkit.block.BlockState state) {
        try {
            return state.getClass().getMethod("getData").invoke(state);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
