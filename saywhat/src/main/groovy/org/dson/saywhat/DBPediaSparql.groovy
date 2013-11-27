package org.dson.saywhat

/**
 * Builder for DBPedia SPARQL
 */
class DBPediaSparql {

	private static final String DBPEDIA_NS = 'http://dbpedia.org/resource/'
	private static final String WIKIPEDIA_NS = 'http://wikipedia.org/wiki/'

	/**
	 * builds a sparql query 
	 * @param entity
	 * @param numAttributes
	 * @return query
	 */
	String build(entity, numAttributes) {
		entity = entity.replaceAll(WIKIPEDIA_NS, DBPEDIA_NS)

		def sparql = """
            PREFIX db: <http://dbpedia.org/resource/>
            PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>

            SELECT ?attr ?val
            WHERE
            {
              db:${entity} ?attr ?val .
              FILTER (REGEX(?attr, 'http://dbpedia.org/ontology/.*') && 
                       ((isLiteral(?val) && (datatype(?val) != xsd:string || langMatches(lang(?val), "en"))) ||
                        (!isLiteral(?val))))
            }
            LIMIT ${numAttributes}
            """

		return sparql
	}
}
