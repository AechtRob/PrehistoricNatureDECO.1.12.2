
package net.prehistoricnaturedeco.creativetab;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.prehistoricnaturedeco.block.BlockArchaeopterisBridge;

@net.prehistoricnaturedeco.ElementsPrehistoricNatureDecoMod.ModElement.Tag
public class TabPrehistoricNatureDeco extends net.prehistoricnaturedeco.ElementsPrehistoricNatureDecoMod.ModElement {
	public TabPrehistoricNatureDeco(net.prehistoricnaturedeco.ElementsPrehistoricNatureDecoMod instance) {
		super(instance, 173);
	}

	@Override
	public void initElements() {
		tab = new CreativeTabs("tabprehistoricnaturedeco") {
			@SideOnly(Side.CLIENT)
			@Override
			public ItemStack createIcon() {
				return new ItemStack(BlockArchaeopterisBridge.block, (int) (1));
			}

			@SideOnly(Side.CLIENT)
			public boolean hasSearchBar() {
				return true;
			}
		};
	}
	public static CreativeTabs tab;
}
