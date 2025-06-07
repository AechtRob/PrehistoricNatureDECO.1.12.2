package net.prehistoricnaturedeco;

import net.minecraft.block.BlockStairs;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.prehistoricnaturedeco.block.base.BlockBenchPF;
import net.prehistoricnaturedeco.block.entity.EntitySittableBench;

import java.util.List;

public class DecoModEventHandler {

        @SubscribeEvent
        public void onInteractWithBlock(PlayerInteractEvent.RightClickBlock event)
        {
            EntityPlayer player = event.getEntityPlayer();
            if (player.getRidingEntity() != null)
            {
                return;
            }

            World worldIn = event.getWorld();
            BlockPos pos = event.getPos();
            Vec3d vec = new Vec3d(pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5);
            double maxDist = 3.0D;
            if ((vec.x - player.posX) * (vec.x - player.posX) + (vec.y - player.posY) * (vec.y - player.posY) + (vec.z - player.posZ) * (vec.z - player.posZ) > maxDist * maxDist)
            {
                return;
            }

            IBlockState state = worldIn.getBlockState(pos);

            if (state.getBlock() instanceof BlockBenchPF)
            {
                //List<SeatStair> seats = worldIn.getEntitiesWithinAABB(SeatStair.class, new AxisAlignedBB(pos, pos.add(1, 1, 1)));
                EntitySittableBench seat = new EntitySittableBench(worldIn, pos);
                worldIn.spawnEntity(seat);
                player.startRiding(seat);
                event.setCanceled(true);
            }
        }
    }

