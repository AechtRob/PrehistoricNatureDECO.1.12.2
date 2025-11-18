
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
import net.prehistoricnaturedeco.block.base.BlockBenchPF;

@net.prehistoricnaturedeco.ElementsPrehistoricNatureDecoMod.ModElement.Tag
public class BlockWoodworthiaBench extends net.prehistoricnaturedeco.ElementsPrehistoricNatureDecoMod.ModElement {
	@GameRegistry.ObjectHolder("lepidodendron:woodworthia_bench")
	public static final Block block = null;
	public BlockWoodworthiaBench(net.prehistoricnaturedeco.ElementsPrehistoricNatureDecoMod instance) {
		super(instance, LepidodendronSorter.woodworthia_bench);
	}

	@Override
	public void initElements() {
		elements.blocks.add(() -> new BlockCustom().setRegistryName("lepidodendron:woodworthia_bench"));
		elements.items.add(() -> new ItemBlock(block).setRegistryName(block.getRegistryName()));
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void registerModels(ModelRegistryEvent event) {
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block), 0,
				new ModelResourceLocation("lepidodendron:woodworthia_bench", "inventory"));
	}
	public static class BlockCustom extends BlockBenchPF {
		public BlockCustom() {
			setTranslationKey("pf_woodworthia_bench");
		}
	}
}
