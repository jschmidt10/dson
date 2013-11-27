package org.dson.saywhat


/**
 * Queries wikipedia for a random link
 */
class WikipediaQuery {
	private static final String RANDOM_LINK = 'http://en.wikipedia.org/wiki/Special:Random'

	/**
	 * @return a random link from wikipedia
	 */
	String randomLink() {
		HttpURLConnection conn = new URL(RANDOM_LINK).openConnection() as HttpURLConnection
		conn.doInput = true
		conn.readTimeout = 10000
		conn.instanceFollowRedirects = false

		conn.connect()
		assert conn.getResponseCode() == HttpURLConnection.HTTP_MOVED_TEMP
		def url = conn.getHeaderField('Location')
		conn.disconnect()

		return url
	}
}