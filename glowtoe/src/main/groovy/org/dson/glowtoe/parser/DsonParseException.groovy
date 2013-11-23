package org.dson.glowtoe.parser

/**
 * Exception thrown when a dson parse error occurs
 */
class DsonParseException extends Exception {
	DsonParseException(String err) {
		super(err)
	}
}
