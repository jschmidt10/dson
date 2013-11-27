package org.dson.saywhat

import groovy.json.JsonSlurper

/**
 * Utility for extracting RDF from dbpedia responses
 */
class DBpediaResultExtractor {

	/**
	 * extracts a mapping of query for predicate, object pairs
	 * @param json
	 * @return
	 */
	Map<String, String> extract(String json) {
		def slurper = new JsonSlurper().parseText(json)

		slurper.results.bindings.collectEntries { binding ->
			[binding.attr.value,
				binding.val.value]
		}
	}
}
