package net.runelite.client.plugins.Util;

import java.lang.reflect.Proxy;
import net.runelite.api.HeadIcon;
import net.runelite.api.NPC;
import net.runelite.api.gameval.SpriteID;
import org.junit.Test;
import static org.junit.Assert.*;

public class NPCCompositionHeadIconTest {
    private NPC npc(int[] archives, short[] sprites) {
        return (NPC) Proxy.newProxyInstance(NPC.class.getClassLoader(), new Class<?>[]{NPC.class},
            (proxy, method, args) -> {
                if (method.getName().equals("getOverheadArchiveIds")) return archives;
                if (method.getName().equals("getOverheadSpriteIds")) return sprites;
                throw new UnsupportedOperationException(method.getName());
            });
    }

    @Test public void readsSparseOverheadIconsAndIgnoresOtherArchives() {
        NPC npc = npc(new int[]{-1, SpriteID.HEADICONS_PK, SpriteID.HEADICONS_PRAYER}, new short[]{-1, 0, 2});
        assertEquals(HeadIcon.MAGIC, new NPCCompositionHeadIcon(npc).getNPCHeadIcon());
    }

    @Test public void handlesMissingAndUnknownIcons() {
        assertNull(new NPCCompositionHeadIcon(null).getNPCHeadIcon());
        assertNull(new NPCCompositionHeadIcon(npc(null, null)).getNPCHeadIcon());
        assertNull(new NPCCompositionHeadIcon(npc(new int[]{440}, new short[]{})).getNPCHeadIcon());
        assertNull(new NPCCompositionHeadIcon(npc(new int[]{440}, new short[]{99})).getNPCHeadIcon());
    }

    @Test public void doesNotRetainAnIconWhenTheNpcChangesStyle() {
        short[] sprites = {0};
        NPCCompositionHeadIcon icons = new NPCCompositionHeadIcon(npc(new int[]{440}, sprites));
        assertEquals(HeadIcon.MELEE, icons.getNPCHeadIcon());
        sprites[0] = 1;
        assertEquals(HeadIcon.RANGED, icons.getNPCHeadIcon());
        sprites[0] = -1;
        assertNull(icons.getNPCHeadIcon());
    }
}
