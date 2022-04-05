package com.smxknife.game;

import com.google.common.collect.Queues;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author smxknife
 * 2020/3/6
 */
public class _01_ShuDouZi {
	public static void main(String[] args) {
		// 玩家
		List<Player> players = IntStream.iterate(0, idx -> idx + 1).limit(2).mapToObj(idx -> {
			Player player = new Player("p_" + (idx + 1));
			if (idx == 0) {
				player.setWinner(true);
			}
			return player;
		}).collect(Collectors.toList());
		System.out.println("===玩家========================================");
		System.out.println(players);

		// 豆子数
		int target = 30;
		ArrayBlockingQueue<Integer> douZis = Queues.newArrayBlockingQueue(target);
		IntStream.iterate(0, idx -> idx + 1).limit(target).forEach(idx -> douZis.offer(idx + 1));
		System.out.println("===豆子=======================================");
		System.out.println(douZis);
		System.out.println("===规则=======================================");
		int min = 2, max = 5;
		System.out.println(String.format("Note: 每次最少取走 %s个，最多 %s个，谁拿走最后一个谁输！", min, max));

		int temp = target - 1;
		ConcurrentLinkedQueue<Integer> winQueue = Queues.newConcurrentLinkedQueue();
		List<Integer> winList = new ArrayList<>();
		while (temp > 0) {
			winList.add(temp);
			temp = temp - (min + max);
		}
		Collections.sort(winList);
		winQueue.addAll(winList);
		System.out.println("===胜利关键点====================================");
		System.out.println(winQueue);
		System.out.println("===开始游戏====================================");

		int playerIdx = 0;
		Player currentPlayer = players.get(playerIdx);
		int lastNum = 0;

		int step = 0;
		while (douZis.size() > 0) {
			System.out.printf("(%s) ------------\r\n", ++step);
			int num = 0;
			if (currentPlayer.isWinner()) {
				num = winQueue.poll();
				//System.out.println("winner num = " + num);
			} else {
				num = ThreadLocalRandom.current().nextInt(min, max + 1) + lastNum;
				//System.out.println("非winner num = " + num);
			}
			num = num > target ? target : num;

			for (;lastNum < num; lastNum++) {
				Integer douZi = douZis.poll();
				currentPlayer.setNo(douZi);
				System.out.println(String.format("%s 取出 %s 豆子", currentPlayer, douZi));
			}
			currentPlayer = nextPlayerIdx(players, ++playerIdx);
		}

		System.out.println("===结果========================");
		players.forEach(player -> {
			System.out.printf("Player [name = %s, no = %s] \r\n", player.name, player.no);
		});

	}

	static Player nextPlayerIdx(final List<Player> players, int playerIdx) {
		playerIdx = playerIdx % players.size();
		return players.get(playerIdx);
	}

	static class Player {
		@Getter
		@Setter
		private int no = -1;

		@Getter
		private String name;

		@Getter
		@Setter
		private boolean winner;

		public Player(String name) {
			this.name = name;
		}

		@Override
		public String toString() {
			return name;
		}
	}
}
