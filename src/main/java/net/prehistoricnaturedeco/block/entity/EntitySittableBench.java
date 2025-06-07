package net.prehistoricnaturedeco.block.entity;

import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.prehistoricnaturedeco.block.base.BlockBenchPF;

import java.util.List;

public class EntitySittableBench extends Entity {

    public EntitySittableBench(World worldIn, BlockPos pos)
    {
        this (worldIn);
        setPosition(pos.getX() + 0.5D, pos.getY() + 0.14D, pos.getZ() + 0.5D);
    }

    public EntitySittableBench(World worldIn)
    {
        super(worldIn);
        setSize(0.0F, 0.0F);
    }

    @Override
    public void onUpdate()
    {
        super.onUpdate();
        BlockPos pos = getPosition();
        if (!(getEntityWorld().getBlockState(pos).getBlock() instanceof BlockBenchPF))
        {
            setDead();
            return;
        }

        List<Entity> passengers = getPassengers();
        if(passengers.isEmpty())
        {
            setDead();
        }
        for (Entity entity : passengers)
        {
            if (entity.isSneaking())
            {
                setDead();
            }
        }
    }

    @Override
    protected void entityInit()
    {

    }

    @Override
    protected void readEntityFromNBT(NBTTagCompound compound)
    {

    }

    @Override
    protected void writeEntityToNBT(NBTTagCompound compound)
    {

    }
}
