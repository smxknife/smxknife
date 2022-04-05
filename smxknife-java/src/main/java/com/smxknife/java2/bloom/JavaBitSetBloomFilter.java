package com.smxknife.java2.bloom;

import java.util.BitSet;

/**
 * @author smxknife
 * 2021/4/13
 */
public class JavaBitSetBloomFilter extends AbstractBloomHashFilter {

	private BitSet bitSet;

	public JavaBitSetBloomFilter(BloomHash[] bloomHashes) {
		this(bloomHashes, DEFAULT_SIZE);
	}

	@Override
	public void setBit(int hash) {
		this.bitSet.set(hash);
	}

	@Override
	public int getBit(int hash) {
		return this.bitSet.get(hash) ? 1 : 0;
	}

	public JavaBitSetBloomFilter(BloomHash[] bloomHashes, int length) {
		super(bloomHashes);
		this.bitSet = new BitSet(length);
	}

}
