package org.dson.glowtoe.validator

import org.dson.glowtoe.parser.DsonParseException
import org.dson.glowtoe.parser.DsonParser

/**
 * Checks if DSON is valid or not
 * 
 * Valid DSON requires: <br/>
 * - starting curly brace <br/>
 * - pairs <br/>
 * - ending curly brace <br/>
 * 
 * Where each pair consists of
 * - starting curly brace <br/>
 * - key <br/>
 * - equals sign <br/>
 * - value enclosed in quotes <br/>
 * - closing curly brace <br/>
 * 
 * In addition, valid DSON must contain the pair: {dson="true"}
 */
class Validator {

	/**
	 * Checks if dson is valid or not
	 * @param dson
	 * @return true if this dson is valid, false, otherwise
	 */
	boolean isValid(String dson) {
		try {
			def map = new DsonParser(dson).parse()
			return map.dson != null && 'true'.equals(map.dson.toLowerCase())
		}
		catch(DsonParseException e) {
			return false
		}
	}
}
