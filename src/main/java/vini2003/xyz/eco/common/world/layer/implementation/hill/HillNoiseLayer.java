package vini2003.xyz.eco.common.world.layer.implementation.hill;

import Auburn.FastNoiseLite.Java.FastNoiseLite;
import vini2003.xyz.eco.common.util.NoiseUtils;
import vini2003.xyz.eco.common.world.layer.base.NoiseLayer;

public class HillNoiseLayer extends NoiseLayer {
	private final FastNoiseLite hillNoise;
	
	public HillNoiseLayer(long seed) {
		super(seed);
		
		this.hillNoise = new FastNoiseLite((int) seed);
		this.hillNoise.SetNoiseType(FastNoiseLite.NoiseType.OpenSimplex2);
		this.hillNoise.SetFrequency(0.005F);
		this.hillNoise.SetDomainWarpType(FastNoiseLite.DomainWarpType.BasicGrid);
		this.hillNoise.SetDomainWarpAmp(30.0F);
		this.hillNoise.SetFractalType(FastNoiseLite.FractalType.None);
	}
	
	@Override
	public float getNoise(int x, int z) {
		return NoiseUtils.normalize(NoiseUtils.getNoise(hillNoise, x * 2.0F, z * 2.0F, 8));
	}
}