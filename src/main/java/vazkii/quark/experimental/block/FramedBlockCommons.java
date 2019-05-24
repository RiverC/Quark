package vazkii.quark.experimental.block;

import net.minecraft.block.Block;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.common.property.ExtendedBlockState;
import net.minecraftforge.common.property.IExtendedBlockState;
import net.minecraftforge.common.property.IUnlistedProperty;
import vazkii.arl.block.property.PropertyBlockState;
import vazkii.quark.experimental.tile.TileFramed;

public class FramedBlockCommons {

	public static final PropertyBlockState STATE = new PropertyBlockState();

	public static BlockStateContainer createStateContainer(Block block, BlockStateContainer normalContainer) {
		return new ExtendedBlockState(block, normalContainer.getProperties().toArray(new IProperty[0]), new IUnlistedProperty[] { STATE });
	}
	
	public static IBlockState getExtendedState(Block block, IBlockState state, IBlockAccess world, BlockPos pos) {
		IBlockState actualState = block.getActualState(state, world, pos);
		TileEntity tile = world.getTileEntity(pos);
		if(tile instanceof TileFramed && actualState instanceof IExtendedBlockState) {
			TileFramed frame = (TileFramed) tile;
			IExtendedBlockState extend = (IExtendedBlockState) actualState;
			return extend.withProperty(STATE, frame.getState());
		}
		
		return state;
	}
	
}
