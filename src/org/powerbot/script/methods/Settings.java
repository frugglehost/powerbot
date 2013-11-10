package org.powerbot.script.methods;

import org.powerbot.client.Client;
import org.powerbot.client.PlayerMetaInfo;

/**
 * {@link Settings} is a utility which provides raw access to the game's varpbits.
 *
 * @author Timer
 */
public class Settings extends MethodProvider {
	public Settings(final MethodContext factory) {
		super(factory);
	}

	/**
	 * Returns the array of settings for the game.
	 *
	 * @return an array of the game's settings
	 */
	public int[] getArray() {
		final Client client = ctx.getClient();
		final PlayerMetaInfo info;
		if (client == null || (info = client.getPlayerMetaInfo()) == null) {
			return new int[0];
		}
		final org.powerbot.client.Settings settings;
		final int[] data;
		if ((settings = info.getSettings()) == null || (data = settings.getData()) == null) {
			return new int[0];
		}
		return data.clone();
	}

	/**
	 * Returns the array of a specified index.
	 *
	 * @param index the index of the setting
	 * @return the setting for the specified index
	 */
	public int get(final int index) {
		final int[] arr = getArray();
		return index < arr.length ? arr[index] : -1;
	}

	/**
	 * Gets the value at a given index and applies a given mask to the value.
	 *
	 * @param index the index in the settings array
	 * @param mask  the bitmask
	 * @return the masked value
	 */
	public int get(final int index, final int mask) {
		return get(index, 0, mask);
	}

	/**
	 * Gets the value at a given index, bit shifts it right by a given number of bits and applies a mask.
	 *
	 * @param index the index in the settings array
	 * @param shift the number of bits to right shift
	 * @param mask  the bitmask
	 * @return the masked value
	 */
	public int get(final int index, final int shift, final int mask) {
		return (get(index) >>> shift) & mask;
	}
}
