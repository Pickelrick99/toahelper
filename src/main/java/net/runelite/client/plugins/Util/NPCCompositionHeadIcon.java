package net.runelite.client.plugins.Util;

import net.runelite.api.HeadIcon;
import net.runelite.api.NPC;
import net.runelite.api.gameval.SpriteID;

/** Reads overhead icons through RuneLite's public API, including NPC overrides. */
public class NPCCompositionHeadIcon {
    private final NPC npc;

    public NPCCompositionHeadIcon(NPC npc) {
        this.npc = npc;
    }

    public HeadIcon getNPCHeadIcon() {
        if (npc == null) {
            return null;
        }
        short[] sprites = npc.getOverheadSpriteIds();
        int[] archives = npc.getOverheadArchiveIds();
        if (sprites == null || archives == null) {
            return null;
        }
        for (int i = 0; i < Math.min(sprites.length, archives.length); i++) {
            // Archive 440 contains the standard overhead prayer sprites.
            if (archives[i] == SpriteID.HEADICONS_PRAYER) {
                switch (sprites[i]) {
                    case 0: return HeadIcon.MELEE;
                    case 1: return HeadIcon.RANGED;
                    case 2: return HeadIcon.MAGIC;
                    case 3: return HeadIcon.RETRIBUTION;
                    case 4: return HeadIcon.SMITE;
                    case 5: return HeadIcon.REDEMPTION;
                    case 6: return HeadIcon.RANGE_MAGE;
                    case 7: return HeadIcon.RANGE_MELEE;
                    case 8: return HeadIcon.MAGE_MELEE;
                    case 9: return HeadIcon.RANGE_MAGE_MELEE;
                    case 10: return HeadIcon.WRATH;
                    case 11: return HeadIcon.SOUL_SPLIT;
                    case 12: return HeadIcon.DEFLECT_MELEE;
                    case 13: return HeadIcon.DEFLECT_RANGE;
                    case 14: return HeadIcon.DEFLECT_MAGE;
                    default: break;
                }
            }
        }
        return null;
    }
}
