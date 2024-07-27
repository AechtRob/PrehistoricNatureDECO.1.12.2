
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
import net.prehistoricnaturedeco.block.base.BlockPlanter;

@net.prehistoricnaturedeco.ElementsPrehistoricNatureDecoMod.ModElement.Tag
public class BlockChestnutPlanter2Peat extends net.prehistoricnaturedeco.ElementsPrehistoricNatureDecoMod.ModElement {
	@GameRegistry.ObjectHolder("lepidodendron:chestnut_planter2_peat")
	public static final Block block = null;
	public BlockChestnutPlanter2Peat(net.prehistoricnaturedeco.ElementsPrehistoricNatureDecoMod instance) {
		super(instance, LepidodendronSorter.chestnut_planter2_peat);
	}

	@Override
	public void initElements() {
		elements.blocks.add(() -> new BlockCustom().setRegistryName("lepidodendron:chestnut_planter2_peat"));
		elements.items.add(() -> new ItemBlock(block).setRegistryName(block.getRegistryName()));
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void registerModels(ModelRegistryEvent event) {
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block), 0,
				new ModelResourceLocation("lepidodendron:chestnut_planter2_peat", "inventory"));
	}
	
	public static class BlockCustom extends BlockPlanter {
		public BlockCustom() {
			setTranslationKey("pf_chestnut_planter2_peat");
		}
	}

}