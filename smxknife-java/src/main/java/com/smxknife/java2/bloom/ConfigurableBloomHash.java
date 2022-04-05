package com.smxknife.java2.bloom;

/**
 * @author smxknife
 * 2021/4/13
 */
public interface ConfigurableBloomHash {

	/**
	 * getBloomHashes
	 * @return
	 */
	BloomHash[] getBloomHashes();

	/**
	 * setBit
	 * @param hash
	 */
	void setBit(int hash);

	/**
	 * getBit
	 * @param hash
	 * @return
	 */
	int getBit(int hash);
}
