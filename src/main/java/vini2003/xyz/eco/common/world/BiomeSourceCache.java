package vini2003.xyz.eco.common.world;/*
 * MIT License
 *
 * Copyright (c) 2020 Chainmail Studios
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.source.BiomeSource;

import it.unimi.dsi.fastutil.HashCommon;

import java.util.Arrays;

/**
 * A non-threadsafe cache used to lossily store biome data. Best used with thread locals.
 *
 * @author gegy1000, SuperCoder79
 */
public class BiomeSourceCache {
	private static final int CACHE_SIZE = 65536;
	
	private final int mask;
	private final long[] keys;
	private final Biome[] values;
	private final BiomeSource source;
	
	public BiomeSourceCache(BiomeSource source) {
		this.source = source;
		this.mask = CACHE_SIZE - 1;
		
		// Initialize default values
		this.keys = new long[CACHE_SIZE];
		Arrays.fill(this.keys, Long.MIN_VALUE);
		this.values = new Biome[CACHE_SIZE];
	}
	
	public Biome getBiome(int x, int z) {
		long key = ChunkPos.toLong(x, z);
		int idx = (int) HashCommon.mix(key) & this.mask;
		
		if (this.keys[idx] == key) {
			return this.values[idx];
		}
		
		Biome biome = source.getBiomeForNoiseGen(x, 0, z);
		
		this.keys[idx] = key;
		this.values[idx] = biome;
		
		return biome;
	}
}