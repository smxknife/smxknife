package com.smxknife.java2.bloom;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * @author smxknife
 * 2021/4/13
 */
public abstract class AbstractBloomHashFilter implements ConfigurableBloomHash, BloomFilter {

	protected static final int DEFAULT_SIZE = 2 << 24;

	private List<BloomHash> bloomHashList;

	public AbstractBloomHashFilter(BloomHash[] bloomHashes) {
		if (Objects.isNull(bloomHashes) || bloomHashes.length == 0) {
			throw new IllegalArgumentException("BloomHash is not exist");
		}
		this.bloomHashList = new ArrayList<>(Arrays.asList(bloomHashes));
	}

	@Override
	public void add(Object object) {
		this.bloomHashList.forEach(hash -> setBit(hash.hash(object)));
	}

	@Override
	public final boolean filter(Object object) {
		if (Objects.isNull(object)) {
			return false;
		}
		return !bloomHashList.stream().filter(bloomHash -> 0 == getBit(bloomHash.hash(object))).findAny().isPresent();
	}

	@Override
	public BloomHash[] getBloomHashes() {
		return bloomHashList.toArray(new BloomHash[bloomHashList.size()]);
	}
}
