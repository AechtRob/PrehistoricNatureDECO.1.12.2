
package net.prehistoricnaturedeco.block.base;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.prehistoricnaturedeco.creativetab.TabPrehistoricNatureDeco;

public class BlockPlanter extends Block implements ITileEntityProvider {

	public static final PropertyInteger VARIANT = PropertyInteger.create("variant", 0, 15);

	public BlockPlanter() {
		super(Material.GROUND);
		setSoundType(SoundType.WOOD);
		setHarvestLevel("axe", 1);
		setHardness(2F);
		setResistance(3F);
		setLightOpacity(0);
		setCreativeTab(TabPrehistoricNatureDeco.tab);
	}

	@Override
	public boolean canSustainPlant(IBlockState state, IBlockAccess world, BlockPos pos, EnumFacing direction, IPlantable plantable) {
		net.minecraftforge.common.EnumPlantType plantType = plantable.getPlantType(world, pos.offset(direction));
		if (plantable == Blocks.MELON_STEM || plantable == Blocks.PUMPKIN_STEM)
		{
			return true;
		}
		switch (plantType)
		{
			case Cave:   return state.isSideSolid(world, pos, EnumFacing.UP);
			case Plains: return true;
			case Beach:
				boolean hasWater = (world.getBlockState(pos.east()).getMaterial() == Material.WATER ||
						world.getBlockState(pos.west()).getMaterial() == Material.WATER ||
						world.getBlockState(pos.north()).getMaterial() == Material.WATER ||
						world.getBlockState(pos.south()).getMaterial() == Material.WATER);
				return hasWater;
		}

		return super.canSustainPlant(state, world, pos, direction, plantable);
	}

	@Override
	public MapColor getMapColor(IBlockState state, IBlockAccess blockAccess, BlockPos pos) {
		return MapColor.WOOD;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public BlockRenderLayer getRenderLayer() {
		return BlockRenderLayer.CUTOUT;
	}

	@Override
	public boolean canRenderInLayer(IBlockState state, BlockRenderLayer layer) {
		return layer == BlockRenderLayer.CUTOUT_MIPPED;
	}

	@Override
	public boolean doesSideBlockRendering(IBlockState state, IBlockAccess world, BlockPos pos, EnumFacing face) {
		return true;
	}

	@Override
	public boolean isFullBlock(IBlockState state) {
		return true;
	}

	@Override
	public boolean isFullCube(IBlockState state) {
		return true;
	}

	@Override
	public boolean isOpaqueCube(IBlockState state)
	{
		return true;
	}
	
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		if (!playerIn.capabilities.allowEdit)
		{
			return super.onBlockActivated(worldIn, pos, state, playerIn, hand, facing, hitX, hitY, hitZ);
		}
		else if (hand == EnumHand.MAIN_HAND) {
			int enumUsed = util.getOreID(playerIn.getHeldItem(hand));

			if (enumUsed > 0) {
				ItemStack itemstack = playerIn.getHeldItem(hand);
				if (!playerIn.isCreative()) {
					itemstack.shrink(1);
				}

				worldIn.setBlockState(pos, this.getDefaultState().withProperty(VARIANT, enumUsed));
				TileEntity te = worldIn.getTileEntity(pos);
				if (te !=  null) {
					if (te instanceof TileEntityPlanter) {
						te.getTileData().setInteger("variant", enumUsed);
						worldIn.notifyBlockUpdate(pos, state, this.getActualState(state, worldIn, pos), 3);
					}
				}
				return true;
			}
		}
		return super.onBlockActivated(worldIn, pos, state, playerIn, hand, facing, hitX, hitY, hitZ);
	}

	@Override
	public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
		//0. Wood base
		//1. Iron
		//2. Gold
		//3. Zircon
		//4. Emerald
		//5. Diamond
		//6. Baltic Amber
		//7. Dominican Amber
		//8. Quartz
		//9. Lapis
		//10. Coal
		//11. Petrified Wood
		//12. Redstone
		int variant = new Object() {
			public int getValue(BlockPos pos1, String tag) {
				TileEntity tileEntity = worldIn.getTileEntity(pos1);
				if (tileEntity != null)
					return tileEntity.getTileData().getInteger(tag);
				return 0;
			}
		}.getValue(pos, "variant");

		return state.withProperty(VARIANT, variant);
	}

	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[]{VARIANT});
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		return 0;
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		return this.getDefaultState();
	}



	@Override
	public void breakBlock(World world, BlockPos pos, IBlockState state) {
		TileEntity tileentity = world.getTileEntity(pos);
		world.removeTileEntity(pos);
		super.breakBlock(world, pos, state);
	}

	@Override
	protected boolean canSilkHarvest() {
		return false;
	}

	@Override
	public boolean removedByPlayer(IBlockState state, World world, BlockPos pos, EntityPlayer player, boolean willHarvest) {
		if (!world.isRemote && !player.isCreative()) {
			int variant = this.getActualState(state, world, pos).getValue(VARIANT);
			if (variant > 0) {
				EntityItem entityToSpawn = new EntityItem(world, pos.getX(), pos.getY(), pos.getZ(), util.getItemToDrop(variant));
				entityToSpawn.setPickupDelay(10);
				world.spawnEntity(entityToSpawn);
			}
		}
		return super.removedByPlayer(state, world, pos, player, willHarvest);
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileEntityPlanter();
	}

	@Override
	public boolean eventReceived(IBlockState state, World worldIn, BlockPos pos, int eventID, int eventParam) {
		super.eventReceived(state, worldIn, pos, eventID, eventParam);
		TileEntity tileentity = worldIn.getTileEntity(pos);
		return tileentity == null ? false : tileentity.receiveClientEvent(eventID, eventParam);
	}


	public static class TileEntityPlanter extends TileEntity {

		private int binTick;

		@Override
		public boolean shouldRefresh(World world, BlockPos pos, IBlockState oldState, IBlockState newSate) {
			return (oldState.getBlock() != newSate.getBlock());
		}

		@Override
		public SPacketUpdateTileEntity getUpdatePacket() {
			return new SPacketUpdateTileEntity(this.pos, 0, this.getUpdateTag());
		}

		@Override
		public NBTTagCompound getUpdateTag() {
			return this.writeToNBT(new NBTTagCompound());
		}


		@Override
		public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity pkt) {
			this.readFromNBT(pkt.getNbtCompound());
		}

		@Override
		public void handleUpdateTag(NBTTagCompound tag) {
			this.readFromNBT(tag);
		}

	}
}


