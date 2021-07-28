package org.via.gymbookingsystemsecond.util;

import java.util.Random;
import java.util.UUID;

import org.via.gymbookingsystemsecond.domain.Account;
import org.via.gymbookingsystemsecond.domain.Gym;

public class RandomUtils {

	private static Random rand = new Random();

	public static Random getRandom() {
		return rand;
	}

	public static String randStr() {
		return UUID.randomUUID().toString();
	}

	public static Account randAccount() {
		return new Account(randStr(), randStr(), randStr());
	}

	public static Gym randGym() {
		return new Gym(randStr(), rand.nextInt());
	}
}
