package org.dson.glowtoe.validator

import org.junit.Assert
import org.junit.Test

class ValidatorTest {
	Validator validator = new Validator()

	@Test
	public void testValidDson() {
		def validDson = '{{dson="true"},{name="something"}}'
		Assert.assertTrue(validator.isValid(validDson))
	}

	@Test
	public void testInvalidDson_badFormatting() {
		def invalidDson = '{{dson="true"},{name="something}}'
		Assert.assertFalse(validator.isValid(invalidDson))
	}

	@Test
	public void testInvalidDson_missingDsonAttribute() {
		def invalidDson = '{{dson="false"},{name="something"}}'
		Assert.assertFalse(validator.isValid(invalidDson))
	}
}
