
package net.prehistoricnaturedeco.block;

import net.lepidodendron.LepidodendronSorter;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.prehistoricnaturedeco.block.base.BlockBridgePF;

@net.prehistoricnaturedeco.ElementsPrehistoricNatureDecoMod.ModElement.Tag
public class BlockCordaitesBridge extends net.prehistoricnaturedeco.ElementsPrehistoricNatureDecoMod.ModElement {
	@GameRegistry.ObjectHolder("lepidodendron:cordaites_bridge")
	public static final Block block = null;
	public BlockCordaitesBridge(net.prehistoricnaturedeco.ElementsPrehistoricNatureDecoMod instance) {
		super(instance, LepidodendronSorter.cordaites_bridge);
	}

	@Override
	public void initElements() {
		elements.blocks.add(() -> new BlockCustom().setRegistryName("lepidodendron:cordaites_bridge"));
		elements.items.add(() -> new ItemBlock(block).setRegistryName(block.getRegistryName()));
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void registerModels(ModelRegistryEvent event) {
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block), 0,
				new ModelResourceLocation("lepidodendron:cordaites_bridge", "inventory"));
	}
	public static class BlockCustom extends BlockBridgePF {
		public BlockCustom() {
			setTranslationKey("pf_cordaites_bridge");
		}
	}
}
