package org.dson.glowtoe.parser

/**
 * Parses a Dson string into a map
 */
class DsonParser {

	private String dson
	private int index

	DsonParser(String dson) {
		this.dson = dson;
		this.index = 0;
	}

	/**
	 * Parses a Dson string
	 * @param dson
	 * @return map
	 * @throws DsonParseException
	 */
	def parse() throws DsonParseException {
		dson = dson.trim()
		matchChar('{')
		def map = pairs()
		matchChar('}')
		finished()

		return map
	}

	def private pairs() {
		def map = [:]

		def hasMore = (dson[index] == '{')

		while (hasMore) {
			matchChar('{')
			def name = key()
			matchChar('=')
			def val = value()
			matchChar('}')

			map[name] = val

			hasMore = optionalMatchChar(',')
		}

		return map
	}

	def private value() {
		try {
			def quoted = optionalMatchChar('"')

			def endValueIndex = dson.indexOf(quoted ? '"' : '}', index)
			def value = dson.substring(index, endValueIndex)
			index = endValueIndex

			if (quoted) {
				matchChar('"')
			}

			return value.trim()
		}
		catch(Exception e) {
			throw new DsonParseException("expected value at index ${index}")
		}
	}

	def private key() {
		try {
			def equalsSignIndex = dson.indexOf('=', index)
			def key = dson.substring(index, equalsSignIndex)
			index = equalsSignIndex
			return key.trim()
		}
		catch(Exception e) {
			throw new DsonParseException("expected value at index ${index}")
		}
	}

	def private hasMorePairs() {
		dson[index] == '{'
	}

	private void finished() {
		if (dson.length() != index) {
			throw new DsonParseException("expected end of dson at index ${index}")
		}
	}

	private void matchChar(chr) {
		ignoreWhitespace()
		if (dson[index] != chr) {
			throw new DsonParseException("expected ${chr} at index ${index}");
		}
		index++
	}

	private boolean optionalMatchChar(chr) {
		ignoreWhitespace()
		if (dson[index] == chr) {
			index++
			return true
		}
		else {
			return false
		}
	}

	private void ignoreWhitespace() {
		while(dson[index] ==~ /\s/) {
			index++;
		}
	}
}
