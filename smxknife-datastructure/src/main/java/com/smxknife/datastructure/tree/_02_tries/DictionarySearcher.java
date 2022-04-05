package com.smxknife.datastructure.tree._02_tries;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * @author smxknife
 * 2021/6/27
 */
public class DictionarySearcher {

	static class Trie {
		private Node root = new Node(null);

		public void insert(String word) {
			if (Objects.isNull(word) || word.trim().equals("")) {
				return;
			}

			root.count++;
			Node node = root;
			final char[] chars = word.toCharArray();
			for (int i = 0; i < chars.length; i++) {
				char code = chars[i];
				if (!node.nexts.containsKey(code)) {
					node.nexts.put(code, new Node(code));
				}
				node = node.nexts.get(code);
				node.count++;
			}
			node.end++;
		}

		public void search(String word, final Map<String, ArrayList<Integer>> wordPos) {
			if (Objects.isNull(word) || word.trim().length() == 0) {
				return;
			}

			Node node = root;
			int idx = 0, curr = 0;
			final char[] chars = word.toCharArray();
			for (; curr < chars.length;) {
				final char code = chars[curr];

				// 下一个节点不包含字符
				if (!node.nexts.containsKey(code)) {

					// 如果node是头节点，说明没有匹配，从下一个字符开始匹配
					if (node == root) {
						curr++;
						idx = curr;
						continue;
					} else if (node.end > 0){
						// 如果不是头节点，说明有匹配到部分，但是要看看是否匹配到字典里面的单词
						int headIdx = idx;
						wordPos.merge(new String(Arrays.copyOfRange(chars, idx, curr)).intern(),
								new ArrayList<Integer>(Arrays.asList(idx)),  (l1, l2) -> {
									l1.add(headIdx);
									return l1;
								});

						idx = curr;
						node = root;
						continue;
					} else {
						idx = curr;
						node = root;
						continue;
					}
				}

				if (node.end > 0) {
					int headIdx = idx;
					wordPos.merge(new String(Arrays.copyOfRange(chars, idx, curr)).intern(),
							new ArrayList<Integer>(Arrays.asList(idx)),  (l1, l2) -> {
								l1.add(headIdx);
								return l1;
							});
				}

				node = node.nexts.get(code);
				curr++;
			}


			if (node.end != 0) {
				int headIdx = idx;
				wordPos.merge(new String(Arrays.copyOfRange(chars, idx, curr)).intern(),
						new ArrayList<Integer>(Arrays.asList(idx)),  (l1, l2) -> {
							l1.add(headIdx);
							return l1;
						});
			}
		}
	}

	static class Node {
		int count = 0;
		int end = 0;
		Character val;

		public Node(Character val) {
			this.val = val;
		}

		Map<Character, Node> nexts = new HashMap<>();

	}

	private Trie trie = new Trie();

	public DictionarySearcher(String filename) {

		try(BufferedReader reader = new BufferedReader(
				new InputStreamReader(
						this.getClass().getClassLoader().getResourceAsStream(filename)))) {
			String word = null;
			while ((word = reader.readLine()) != null) {
				trie.insert(word);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public HashMap<String, ArrayList<Integer>> search(String document) {
		HashMap<String, ArrayList<Integer>> wordPos = new HashMap<>();
		trie.search(document, wordPos);
		return wordPos ;
	}

	public static void main(String[] args) {
		/*
		input:
		1. input_file
		每一行有一个词汇，如“浙江”, “浙江大学”, “美国”, “美国政府”。该文件可能有100万词

		2. a document，字符串。一般有2000字左右。如 “美国规划协会中国办公室揭牌仪式及美国规划领
		域合作研讨会在浙江大学城乡规划设计研究院208会议室举行。美国规划协会CEO James Drinan，
		国际项目及外联主任Jeffrey Soule先生，浙江大学党委副书记任少波，浙江大学控股集团领导杨其和，
		西湖区政府代表应权英副主任....”
		 */

		/**
		 * 分析：
		 * 1. input:
		 *      以文件格式为utf8为编码格式
		 *      按照每个汉字3字节进行计算
		 *      每个单词按照4个汉字进行计算
		 *      占用的空间：（4 * 3 + 2（换行））* 1000000 约等于13M
		 *      即使扩充100倍，内存依然放的下，所以忽略内存问题
		 *
		 * 2. document
		 *      遍历document的每个字符，从树中查找是否存在对应的路径，每个单词查找的时间复杂度为O(1)，总共2000个字符，所以总的时间复杂度O(N)
		 */
		String document = "美国规划协会中国办公室揭牌仪式及美国规划领域合作研讨会在浙江大学城乡规划设计研究院208会议室举行。美国规划协会CEO James Drinan，国际项目及外联主任Jeffrey Soule先生，浙江大学党委副书记任少波，浙江大学控股集团领导杨其和，西湖区政府代表应权英副主任....";

		final DictionarySearcher dict = new DictionarySearcher("dict");
		final HashMap<String, ArrayList<Integer>> hashMap = dict.search(document);
		System.out.println(hashMap);

	}
}
