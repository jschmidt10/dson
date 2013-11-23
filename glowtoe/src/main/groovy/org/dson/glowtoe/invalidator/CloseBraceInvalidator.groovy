package org.dson.glowtoe.invalidator

/**
 * Adds an extra trailing brace
 */
class CloseBraceInvalidator extends Invalidator {
	@Override
	public String apply(String validDson) {
		return validDson + '}';
	}
}
