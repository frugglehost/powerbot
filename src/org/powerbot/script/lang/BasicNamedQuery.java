package org.powerbot.script.lang;

import org.powerbot.script.methods.MethodContext;
import org.powerbot.script.wrappers.Area;
import org.powerbot.script.wrappers.Identifiable;
import org.powerbot.script.wrappers.Locatable;
import org.powerbot.script.wrappers.Nameable;

public abstract class BasicNamedQuery<K extends Locatable & Identifiable & Nameable> extends AbstractQuery<BasicNamedQuery<K>, K>
		implements Locatable.Query<BasicNamedQuery<K>>, Identifiable.Query<BasicNamedQuery<K>>,
		Nameable.Query<BasicNamedQuery<K>> {
	public BasicNamedQuery(final MethodContext factory) {
		super(factory);
	}

	@Override
	protected BasicNamedQuery<K> getThis() {
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public BasicNamedQuery<K> at(final Locatable l) {
		return select(new Locatable.Matcher(l));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public BasicNamedQuery<K> within(final double distance) {
		return within(ctx.players.local(), distance);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public BasicNamedQuery<K> within(final Locatable target, final double distance) {
		return select(new Locatable.WithinRange(target, distance));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public BasicNamedQuery<K> within(final Area area) {
		return select(new Locatable.WithinArea(area));
	}


	/**
	 * {@inheritDoc}
	 */
	@Override
	public BasicNamedQuery<K> nearest() {
		return nearest(ctx.players.local());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public BasicNamedQuery<K> nearest(final Locatable target) {
		return sort(new Locatable.NearestTo(target));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public BasicNamedQuery<K> id(final int... ids) {
		return select(new Identifiable.Matcher(ids));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public BasicNamedQuery<K> id(final int[]... ids) {
		int z = 0;

		for (final int[] x : ids) {
			z += x.length;
		}

		final int[] a = new int[z];
		int i = 0;

		for (final int[] x : ids) {
			for (final int y : x) {
				a[i++] = y;
			}
		}

		return select(new Identifiable.Matcher(a));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public BasicNamedQuery<K> id(final Identifiable... identifiables) {
		return select(new Identifiable.Matcher(identifiables));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public BasicNamedQuery<K> name(final String... names) {
		return select(new Nameable.Matcher(names));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public BasicNamedQuery<K> name(final Nameable... names) {
		return select(new Nameable.Matcher(names));
	}
}
