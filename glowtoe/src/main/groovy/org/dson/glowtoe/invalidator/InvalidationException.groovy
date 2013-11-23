package org.dson.glowtoe.invalidator

/**
 * An InvalidationException is thrown when an invalidation fails
 */
class InvalidationException extends Exception {
	InvalidationException(String err) {
		super(err)
	}
}
