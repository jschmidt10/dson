package org.dson.glowtoe.invalidator

import org.dson.glowtoe.parser.DsonParseException
import org.dson.glowtoe.parser.DsonParser

/**
 * An invalidator can invalidate valid DSON
 */
abstract class Invalidator {

	/**
	 * Takes a valid DSON string and converts it to an invalid one
	 * @param validDson
	 * @return invalidDson
	 * @throws InvalidationException
	 */
	final String invalidate(String validDson) throws InvalidationException {
		try {
			new DsonParser(validDson).parse()
			throw new InvalidationException("${validDson} is not valid DSON!")
		}
		catch(DsonParseException e) {
			return apply(validDson)
		}
	}

	/**
	 * applies an invalidation
	 * @param validDson
	 * @return invalidDson
	 */
	abstract String apply(String validDson)
}