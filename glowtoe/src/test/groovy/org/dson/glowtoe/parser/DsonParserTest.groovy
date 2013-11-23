package org.dson.glowtoe.parser

import org.junit.Assert
import org.junit.Test

class DsonParserTest {

	@Test
	public void testValidParse() {
		def dson = '{{name="Tom Test"},{age="20"},{hourlyRate="10.50"}}'
		def map = new DsonParser(dson).parse()

		Assert.assertEquals(3, map.size())
		Assert.assertEquals('Tom Test', map.name)
		Assert.assertEquals('20', map.age)
		Assert.assertEquals('10.50', map.hourlyRate)
	}

	@Test
	public void testValidParse_quotesInValue() {
		def dson = '{{name="Tom \\\"Unit\\\" Test"},{age="20"},{hourlyRate="10.50"}}'
		println dson
		def map = new DsonParser(dson).parse()

		Assert.assertEquals(3, map.size())
		Assert.assertEquals('Tom \"Unit\" Test', map.name)
		Assert.assertEquals('20', map.age)
		Assert.assertEquals('10.50', map.hourlyRate)
	}

	@Test(expected=DsonParseException.class)
	public void testInvalidParse_missingBrace() {
		def dson = '{{name="Tom Test"},age="20"},{hourlyRate="10.50"}}'
		new DsonParser(dson).parse()
	}

	@Test(expected=DsonParseException.class)
	public void testInvalidParse_missingComma() {
		def dson = '{{name="Tom Test"}{age="20"},{hourlyRate="10.50"}}'
		new DsonParser(dson).parse()
	}

	@Test(expected=DsonParseException.class)
	public void testInvalidParse_missingQuotes() {
		def dson = '{{name="Tom Test"},{age=20},{hourlyRate="10.50"}}'
		new DsonParser(dson).parse()
	}
}
