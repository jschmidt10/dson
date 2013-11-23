package org.dson.glowtoe.generator

/**
 * Converts objects (domain objects, maps, etc) to DSON
 */
class DsonGenerator {

	/**
	 * Generates a DSON string from an object
	 * @param obj
	 * @return dson
	 */
	String generate(Object obj) {
		def values = [:]

		if (obj instanceof Map) {
			values = obj
		}
		else {
			values = obj.getProperties().findAll { !'class'.equals(it.key) && !'metaClass'.equals(it.key) }.collect()
		}

		return toDson(values)
	}

	def private toDson(values) {
		def sb = new StringBuilder().with {
			append('{')
			append('{dson=\"true\"}')

			if (values.size() > 0) {
				append(',')
			}

			append(values.collect { entry -> "{${entry.key}=\"${entry.value}\"}" }.join(','))
			append('}')
		}

		return sb.toString()
	}
}