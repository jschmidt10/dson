package org.dson.glowtoe.generator

import org.dson.glowtoe.parser.DsonParser
import org.dson.glowtoe.validator.Validator
import org.junit.Assert
import org.junit.Test

class DsonGeneratorTest {
	DsonGenerator generator = new DsonGenerator()

	class Person {
		String firstName
		String lastName
		int age
	}

	@Test
	public void testDsonGeneration() {
		def p = new Person(firstName: 'tom', lastName: 'test', age: 20)
		def dson = generator.generate(p)

		def map = new DsonParser(dson).parse()

		Assert.assertEquals('tom', map.firstName)
		Assert.assertEquals('test', map.lastName)
		Assert.assertEquals('20', map.age)
		Assert.assertTrue(new Validator().isValid(dson))
	}

	@Test
	public void testDsonGeneration_emptyMap() {
		def map = [:]
		def dson = generator.generate(map)

		Assert.assertEquals('{{dson=\"true\"}}', dson)
	}
}
