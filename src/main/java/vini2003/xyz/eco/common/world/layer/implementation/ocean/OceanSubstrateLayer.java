package vini2003.xyz.eco.common.world.layer.implementation.ocean;

import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.chunk.Chunk;
import vini2003.xyz.eco.common.world.layer.base.HeightLayer;
import vini2003.xyz.eco.common.world.layer.base.SubstrateLayer;

public class OceanSubstrateLayer extends SubstrateLayer {
	public OceanSubstrateLayer(HeightLayer heightLayer) {
		super(heightLayer);
	}
	
	@Override
	public void build(Chunk chunk) {
		for (int x = chunk.getPos().getStartX(); x <= chunk.getPos().getEndX(); ++x) {
			for (int z = chunk.getPos().getStartZ(); z <= chunk.getPos().getEndZ(); ++z) {
				int height = heightLayer.getHeight(x, z);
				
				for (int y = height; y > 0 && chunk.getBlockState(new BlockPos(x, y, z)).isAir(); --y) {
					chunk.setBlockState(new BlockPos(x, y, z), Blocks.WATER.getDefaultState(), false);
				}
			}
		}
	}
}