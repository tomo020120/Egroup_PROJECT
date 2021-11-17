package cmd.UUID;

import java.util.UUID;

public abstract class UUIDCreator {
	private static UUID uuid;

	public static String getUUID() {
		uuid = UUID.randomUUID();
		return uuid.toString();
	}
}
