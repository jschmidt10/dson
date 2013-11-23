package org.dson.glowtoe.invalidator

/**
 * Adds an extra open brace to the beginning of DSON
 */
class OpenBraceInvalidator extends Invalidator {

	@Override
	public String apply(String validDson) {
		return '{' + validDson;
	}
}
