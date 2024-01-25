package net.prehistoricnaturedeco.render;

import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.prehistoricnaturedeco.block.BlockRopeBarrier;
import net.prehistoricnaturedeco.render.tile.RenderRopeBarrier;

public class RenderHandler {

    public static void RegisterEntityRenders() {

        //Rope barrier:
        ClientRegistry.bindTileEntitySpecialRenderer(BlockRopeBarrier.TileEntityRopeBarrier.class, new RenderRopeBarrier());

    }
}
