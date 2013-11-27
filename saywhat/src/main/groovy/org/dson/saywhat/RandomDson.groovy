package org.dson.saywhat

import org.dson.glowtoe.generator.DsonGenerator

/**
 * Generates random DSON that describes some "real" object
 */
class RandomDson {

	private WikipediaQuery wpQuery = new WikipediaQuery()
	private DBPediaQuery dbQuery = new DBPediaQuery()

	/**
	 * @param numAttributes max number of attributes
	 * @param maxTries max number of tries
	 * @return next random dson block
	 */
	String nextDson(int numAttributes, int maxTries) {
		
		for (int i = 0; i < maxTries; ++i) {
			def entity = ''
			try {
				entity = wpQuery.randomLink()
				def attributes = dbQuery.query(lastResource(entity), numAttributes)
					
				attributes = prettify(attributes)
					
				return new DsonGenerator().generate(attributes)
			}
			catch(Exception e) {
				System.err.println("error fetching entity ${entity}")
			}
		}
	}
	
	def private prettify(attributes) {
		return attributes.collectEntries { entry ->
			[entry.key.replaceAll('http://dbpedia.org/ontology/', ''),
				entry.value.startsWith('http') ? lastResource(entry.value) : entry.value
			]
		}
	}

	def private lastResource(String url) {
		def tokens = url.split('/')
		return tokens[tokens.length - 1]
	}
}
