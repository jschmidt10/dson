package org.dson.saywhat


/**
 * Handles queries to dbpedia.org
 */
class DBPediaQuery {

	private DBPediaSparql sparql = new DBPediaSparql()
	private DBpediaResultExtractor extractor = new DBpediaResultExtractor()

	/**
	 * Queries dbpedia for an entity
	 * @param entity
	 * @param numAttributes
	 * @return map of attributes
	 */
	Map<String, String> query(String entity, int numAttributes) {
		def query = sparql.build(entity, numAttributes)
		def results = execute(query)

		return extractor.extract(results)
	}

	def private execute(sparql) {
		sparql = URLEncoder.encode(sparql, 'UTF-8')
		HttpURLConnection conn = new URL("http://dbpedia.org/sparql?query=${sparql}" as String).openConnection()
		conn.readTimeout = 20000
		conn.doInput = true
		conn.setRequestProperty('Accept', 'application/json')

		conn.connect()
		def response = conn.inputStream.newReader().readLines().join(' ')
		conn.disconnect()

		return response
	}
}
