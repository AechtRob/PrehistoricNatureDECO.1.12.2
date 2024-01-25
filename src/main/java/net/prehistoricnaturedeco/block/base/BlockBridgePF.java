
package net.prehistoricnaturedeco.block.base;

import net.minecraft.block.*;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.*;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.prehistoricnaturedeco.creativetab.TabPrehistoricNatureDeco;

import javax.annotation.Nullable;
import java.util.List;

public class BlockBridgePF extends BlockCompressedPowered implements ITileEntityProvider {

	public static final PropertyDirection FACING = BlockDirectional.FACING;
	public static final PropertyBool FENCE = PropertyBool.create("fence");
	public static final PropertyBool LEFT = PropertyBool.create("left");
	public static final PropertyBool RIGHT = PropertyBool.create("right");
	public static final PropertyBool FRONT = PropertyBool.create("front");
	public static final PropertyBool BACK = PropertyBool.create("back");
	public static final PropertyInteger VARIANT = PropertyInteger.create("variant", 0, 15);

	public BlockBridgePF() {
		super(Material.WOOD, MapColor.WOOD);
		setHarvestLevel("axe", 1);
		setSoundType(SoundType.WOOD);
		setHardness(2F);
		setResistance(3F);
		setLightOpacity(0);
		//setLightLevel(0.5F);
		setCreativeTab(TabPrehistoricNatureDeco.tab);
	}

	@SideOnly(Side.CLIENT)
	public int getPackedLightmapCoords(IBlockState state, IBlockAccess source, BlockPos pos) {
		int i = source.getCombinedLight(pos, state.getLightValue(source, pos));

		if (i == 0)
		{
			BlockPos pos1 = pos.up();
			state = source.getBlockState(pos1);
			int ii = source.getCombinedLight(pos1, state.getLightValue(source, pos1));
			if (ii == 0) {
				pos1 = pos.down();
				state = source.getBlockState(pos1);
				ii = source.getCombinedLight(pos1, state.getLightValue(source, pos1));
				if (ii == 0) {
					pos1 = pos.north();
					state = source.getBlockState(pos1);
					ii = source.getCombinedLight(pos1, state.getLightValue(source, pos1));
					if (ii == 0) {
						pos1 = pos.east();
						state = source.getBlockState(pos1);
						ii = source.getCombinedLight(pos1, state.getLightValue(source, pos1));
						if (ii == 0) {
							pos1 = pos.south();
							state = source.getBlockState(pos1);
							ii = source.getCombinedLight(pos1, state.getLightValue(source, pos1));
							if (ii == 0) {
								pos1 = pos.west();
								state = source.getBlockState(pos1);
								ii = source.getCombinedLight(pos1, state.getLightValue(source, pos1));
								return ii;
							}
							return ii;
						}
						return ii;
					}
					return ii;
				}
				return ii;
			}
			else {
				return ii;
			}
		}
		else
		{
			return i;
		}
	}

	@Nullable
	@Override
	public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos) {
		return super.getCollisionBoundingBox(blockState, worldIn, pos);
	}

	@Override
	public void addCollisionBoxToList(IBlockState state, World worldIn, BlockPos pos, AxisAlignedBB entityBox, List<AxisAlignedBB> collidingBoxes, @Nullable Entity entityIn, boolean isActualState) {

		if (!isActualState)
		{
			state = state.getActualState(worldIn, pos);
		}

		addCollisionBoxToList(pos, entityBox, collidingBoxes, new AxisAlignedBB(0, 0.9, 0, 1, 1, 1));

		if (state.getValue(FACING) == EnumFacing.WEST) {
			if (this.getActualState(state, worldIn, pos).getValue(RIGHT)) {
				addCollisionBoxToList(pos, entityBox, collidingBoxes, new AxisAlignedBB(0,1,-0.1,1,2.75,00));
			}
			if (this.getActualState(state, worldIn, pos).getValue(LEFT)) {
				addCollisionBoxToList(pos, entityBox, collidingBoxes, new AxisAlignedBB(0,1,1.0,1,2.75,1.1));
			}
			if (this.getActualState(state, worldIn, pos).getValue(FRONT)) {
				addCollisionBoxToList(pos, entityBox, collidingBoxes, new AxisAlignedBB(-0.1,1,0,0,2.75,1));
			}
			if (this.getActualState(state, worldIn, pos).getValue(BACK)) {
				addCollisionBoxToList(pos, entityBox, collidingBoxes, new AxisAlignedBB(1,1,0,1.1,2.75,1));
			}

		}
		if (this.getActualState(state, worldIn, pos).getValue(FACING) == EnumFacing.EAST) {
			if (state.getValue(LEFT)) {
				addCollisionBoxToList(pos, entityBox, collidingBoxes, new AxisAlignedBB(0,1,-0.1,1,2.75,0));
			}
			if (this.getActualState(state, worldIn, pos).getValue(RIGHT)) {
				addCollisionBoxToList(pos, entityBox, collidingBoxes, new AxisAlignedBB(0,1,1.0,1,2.75,1.1));
			}
			if (this.getActualState(state, worldIn, pos).getValue(BACK)) {
				addCollisionBoxToList(pos, entityBox, collidingBoxes, new AxisAlignedBB(-0.1,1,0,0,2.75,1));
			}
			if (this.getActualState(state, worldIn, pos).getValue(FRONT)) {
				addCollisionBoxToList(pos, entityBox, collidingBoxes, new AxisAlignedBB(1,1,0,1.1,2.75,1));
			}
		}

		if (state.getValue(FACING) == EnumFacing.NORTH) {
			if (this.getActualState(state, worldIn, pos).getValue(LEFT)) {
				addCollisionBoxToList(pos, entityBox, collidingBoxes, new AxisAlignedBB(-0.1,1,0,0,2.75,1));
			}
			if (this.getActualState(state, worldIn, pos).getValue(RIGHT)) {
				addCollisionBoxToList(pos, entityBox, collidingBoxes, new AxisAlignedBB(1.0,1,0,1.1,2.75,1));
			}
			if (this.getActualState(state, worldIn, pos).getValue(FRONT)) {
				addCollisionBoxToList(pos, entityBox, collidingBoxes, new AxisAlignedBB(0,1,-0.1,1,2.75,0));
			}
			if (this.getActualState(state, worldIn, pos).getValue(BACK)) {
				addCollisionBoxToList(pos, entityBox, collidingBoxes, new AxisAlignedBB(0,1,1,1,2.75,1.1));
			}
		}
		if (state.getValue(FACING) == EnumFacing.SOUTH) {
			if (this.getActualState(state, worldIn, pos).getValue(RIGHT)) {
				addCollisionBoxToList(pos, entityBox, collidingBoxes, new AxisAlignedBB(-0.1,1,0,0,2.75,1));
			}
			if (this.getActualState(state, worldIn, pos).getValue(LEFT)) {
				addCollisionBoxToList(pos, entityBox, collidingBoxes, new AxisAlignedBB(1.0,1,0,1.1,2.75,1));
			}
			if (this.getActualState(state, worldIn, pos).getValue(BACK)) {
				addCollisionBoxToList(pos, entityBox, collidingBoxes, new AxisAlignedBB(0,1,-0.1,1,2.75,0));
			}
			if (this.getActualState(state, worldIn, pos).getValue(FRONT)) {
				addCollisionBoxToList(pos, entityBox, collidingBoxes, new AxisAlignedBB(0,1,1,1,2.75,1.1));
			}
		}
	}

	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		return new AxisAlignedBB(0, 0.0, 0, 1, 1.0, 1);
	}

	@Override
	public int getFlammability(IBlockAccess world, BlockPos pos, EnumFacing face) {
		return 20;
	}

	@Override
	public int getFireSpreadSpeed(IBlockAccess world, BlockPos pos, EnumFacing face) {
		return 5;
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
				EnumFacing bsFacing = this.getActualState(state, worldIn, pos).getValue(FACING);
				boolean left = this.getActualState(state, worldIn, pos).getValue(LEFT);
				boolean right = this.getActualState(state, worldIn, pos).getValue(RIGHT);

				worldIn.setBlockState(pos, this.getDefaultState().withProperty(FACING, bsFacing).withProperty(LEFT, left).withProperty(RIGHT, right).withProperty(VARIANT, enumUsed));
				TileEntity te = worldIn.getTileEntity(pos);
				if (te !=  null) {
					if (te instanceof BlockBridgePF.TileEntityBridge) {
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
	public int getWeakPower(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos, EnumFacing side)
	{
		if (blockAccess instanceof World) {
			World worldIn = (World) blockAccess;
			int variant = new Object() {
				public int getValue(BlockPos pos1, String tag) {
					TileEntity tileEntity = worldIn.getTileEntity(pos1);
					if (tileEntity != null)
						return tileEntity.getTileData().getInteger(tag);
					return 0;
				}
			}.getValue(pos, "variant");
			if (side == EnumFacing.DOWN && variant == 12) {
				return 15;
			}
		}
		return 0;
	}

	@Override
	public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
		EnumFacing facing = state.getValue(FACING);
		if (facing == EnumFacing.UP || facing == EnumFacing.DOWN) {
			facing = EnumFacing.NORTH;
		}

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

		boolean left = false;
		boolean right = false;
		boolean front = false;
		boolean back = false;

		boolean fence = ((worldIn.getBlockState(pos.down()).getBlock() instanceof BlockFence)
				|| (worldIn.getBlockState(pos.down()).getBlock() instanceof BlockWall)
				|| (worldIn.getBlockState(pos.down()).getBlockFaceShape(worldIn, pos.down(), EnumFacing.UP) == BlockFaceShape.CENTER)
				|| (worldIn.getBlockState(pos.down()).getBlockFaceShape(worldIn, pos.down(), EnumFacing.UP) == BlockFaceShape.CENTER_BIG)
			);

		if (state.getValue(FACING) == EnumFacing.NORTH) {
			if (worldIn.getBlockState(pos.east()).getBlock() instanceof BlockBridgePF) {
				right = true;
			}
			if (worldIn.getBlockState(pos.west()).getBlock() instanceof BlockBridgePF) {
				left = true;
			}
			if ((!worldIn.getBlockState(pos.north().up()).isFullBlock()) && (!(worldIn.getBlockState(pos.north()).getBlock() instanceof BlockBridgePF))) {
				if (worldIn.getBlockState(pos.north()).getBlockFaceShape(worldIn, pos.north(), EnumFacing.UP) != BlockFaceShape.SOLID
						&& (!(worldIn.getBlockState(pos.north()).getBlock() instanceof BlockStairs))) {
					front = true;
				}
			}
			if ((!worldIn.getBlockState(pos.south().up()).isFullBlock()) && (!(worldIn.getBlockState(pos.south()).getBlock() instanceof BlockBridgePF))) {
				if (worldIn.getBlockState(pos.south()).getBlockFaceShape(worldIn, pos.south(), EnumFacing.UP) != BlockFaceShape.SOLID
						&& (!(worldIn.getBlockState(pos.south()).getBlock() instanceof BlockStairs))) {
					back = true;
				}
			}
		} else if (state.getValue(FACING) == EnumFacing.SOUTH) {
			if (worldIn.getBlockState(pos.east()).getBlock() instanceof BlockBridgePF) {
				left = true;
			}
			if (worldIn.getBlockState(pos.west()).getBlock() instanceof BlockBridgePF) {
				right = true;
			}
			if ((!worldIn.getBlockState(pos.south().up()).isFullBlock()) && (!(worldIn.getBlockState(pos.south()).getBlock() instanceof BlockBridgePF))) {
				if (worldIn.getBlockState(pos.south()).getBlockFaceShape(worldIn, pos.south(), EnumFacing.UP) != BlockFaceShape.SOLID
						&& (!(worldIn.getBlockState(pos.south()).getBlock() instanceof BlockStairs))) {
					front = true;
				}
			}
			if ((!worldIn.getBlockState(pos.north().up()).isFullBlock()) && (!(worldIn.getBlockState(pos.north()).getBlock() instanceof BlockBridgePF))) {
				if (worldIn.getBlockState(pos.north()).getBlockFaceShape(worldIn, pos.north(), EnumFacing.UP) != BlockFaceShape.SOLID
						&& (!(worldIn.getBlockState(pos.north()).getBlock() instanceof BlockStairs))) {
					back = true;
				}
			}
		} else if (state.getValue(FACING) == EnumFacing.WEST) {
			if (worldIn.getBlockState(pos.north()).getBlock() instanceof BlockBridgePF) {
				right = true;
			}
			if (worldIn.getBlockState(pos.south()).getBlock() instanceof BlockBridgePF) {
				left = true;
			}
			if ((!worldIn.getBlockState(pos.west().up()).isFullBlock()) && (!(worldIn.getBlockState(pos.west()).getBlock() instanceof BlockBridgePF))) {
				if (worldIn.getBlockState(pos.west()).getBlockFaceShape(worldIn, pos.west(), EnumFacing.UP) != BlockFaceShape.SOLID
						&& (!(worldIn.getBlockState(pos.west()).getBlock() instanceof BlockStairs))) {
					front = true;
				}
			}
			if ((!worldIn.getBlockState(pos.east().up()).isFullBlock()) && (!(worldIn.getBlockState(pos.east()).getBlock() instanceof BlockBridgePF))) {
				if (worldIn.getBlockState(pos.east()).getBlockFaceShape(worldIn, pos.east(), EnumFacing.UP) != BlockFaceShape.SOLID
						&& (!(worldIn.getBlockState(pos.east()).getBlock() instanceof BlockStairs))) {
					back = true;
				}
			}
		} else if (state.getValue(FACING) == EnumFacing.EAST) {
			if (worldIn.getBlockState(pos.north()).getBlock() instanceof BlockBridgePF) {
				left = true;
			}
			if (worldIn.getBlockState(pos.south()).getBlock() instanceof BlockBridgePF) {
				right = true;
			}
			if ((!worldIn.getBlockState(pos.east().up()).isFullBlock()) && (!(worldIn.getBlockState(pos.east()).getBlock() instanceof BlockBridgePF))) {
				if (worldIn.getBlockState(pos.east()).getBlockFaceShape(worldIn, pos.east(), EnumFacing.UP) != BlockFaceShape.SOLID
						&& (!(worldIn.getBlockState(pos.east()).getBlock() instanceof BlockStairs))) {
					front = true;
				}
			}
			if ((!worldIn.getBlockState(pos.west().up()).isFullBlock()) && (!(worldIn.getBlockState(pos.west()).getBlock() instanceof BlockBridgePF))) {
				if (worldIn.getBlockState(pos.west()).getBlockFaceShape(worldIn, pos.west(), EnumFacing.UP) != BlockFaceShape.SOLID
						&& (!(worldIn.getBlockState(pos.west()).getBlock() instanceof BlockStairs))) {
					back = true;
				}
			}
		}
		return state.withProperty(FENCE, fence).withProperty(VARIANT, variant).withProperty(FACING, facing).withProperty(LEFT, !left).withProperty(RIGHT, !right).withProperty(FRONT, front).withProperty(BACK, back);
	}

	@Override
	public IBlockState getStateForPlacement(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer) {
		//System.err.println("Placed by: " + placer);
		return this.getDefaultState().withProperty(FACING, placer.getHorizontalFacing().getOpposite());
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		EnumFacing enumfacing = EnumFacing.byIndex(meta);

		if (enumfacing.getAxis() == EnumFacing.Axis.Y) {
			enumfacing = EnumFacing.NORTH;
		}

		return this.getDefaultState().withProperty(FACING, enumfacing);
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		return ((EnumFacing) state.getValue(FACING)).getIndex();
	}

	@Override
	public IBlockState withRotation(IBlockState state, Rotation rot) {
		return state.withProperty(FACING, rot.rotate(state.getValue(FACING)));
	}

	@SuppressWarnings("deprecation")
	@Override
	public IBlockState withMirror(IBlockState state, Mirror mirrorIn) {
		return state.withRotation(mirrorIn.toRotation(state.getValue(FACING)));
	}

	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[]{FACING, FENCE, LEFT, RIGHT, FRONT, BACK, VARIANT});
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
		return false;
	}

	@Override
	public boolean isFullBlock(IBlockState state) {
		return false;
	}

	@Override
	public boolean isFullCube(IBlockState state) {
		return false;
	}

	@Override
	public boolean isOpaqueCube(IBlockState state)
	{
		return false;
	}

	@Override
	public BlockFaceShape getBlockFaceShape(IBlockAccess worldIn, IBlockState state, BlockPos pos, EnumFacing face) {
		if (face == EnumFacing.UP) {
			return BlockFaceShape.SOLID;
		}
		return BlockFaceShape.UNDEFINED;
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
		return new BlockBridgePF.TileEntityBridge();
	}

	@Override
	public boolean eventReceived(IBlockState state, World worldIn, BlockPos pos, int eventID, int eventParam) {
		super.eventReceived(state, worldIn, pos, eventID, eventParam);
		TileEntity tileentity = worldIn.getTileEntity(pos);
		return tileentity == null ? false : tileentity.receiveClientEvent(eventID, eventParam);
	}


	public static class TileEntityBridge extends TileEntity {

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


