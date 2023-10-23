package ch2;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * 검사할 규칙은 세가지
 * 1. 길이가 8글자 이상
 * 2. 0~9 사이의 숫자를 포함
 * 3. 대문자 포함
 * > 규칙 3개 통과 강함 2개 통과 보통 1개 통과 약함
 */
public class PasswordStrengthMeterTest {

	private PasswordStrengthMeter meter = new PasswordStrengthMeter();

	private void assertPasswordStrength(String password, PasswordStrengthType exp) {
		PasswordStrengthType result = meter.evaluate(password);
		assertEquals(exp, result);
	}

	@Test
	void meetsAllCriteria_Then_Strong(){
		assertPasswordStrength("1aS@#AB1", PasswordStrengthType.STRONG);
		assertPasswordStrength("35@ASSq7", PasswordStrengthType.STRONG);
	}

	@Test
	void meetsOtherCriteria_except_for_Length_Then_Normal() {
		assertPasswordStrength("11AA@@", PasswordStrengthType.NORMAL);
		assertPasswordStrength("aA11@@", PasswordStrengthType.NORMAL);
	}


	@Test
	void meetsOtherCriteria_except_for_Number_Then_Normal() {
		assertPasswordStrength("AAQQ!!!!SS", PasswordStrengthType.NORMAL);
	}


	@Test
	void nullInput_Then_Invalid(){
		assertPasswordStrength(null, PasswordStrengthType.INVALID);
	}

	@Test
	void emptyInput_Then_Invalid() {
		assertPasswordStrength("", PasswordStrengthType.INVALID);
	}

	@Test
	void meetsOtherCriteria_except_for_Uppercase_Then_Normal() {
		assertPasswordStrength("sdf929dk!!", PasswordStrengthType.NORMAL);
	}

	@Test
	void meetsOnlyLengthCriteria_Then_Weak() {
		assertPasswordStrength("askskskkkkkqiwi", PasswordStrengthType.WEAK);
	}

	@Test
	void meetsOnlyNumCriteria_Then_Weak() {
		assertPasswordStrength("1231", PasswordStrengthType.WEAK);
	}

	@Test
	void meetsOnlyUpperCriteria_Then_Weak() {
		assertPasswordStrength("ASWE", PasswordStrengthType.WEAK);
	}

	@Test
	void meetsNoCriteria_Then_Weak() {
		assertPasswordStrength("asd", PasswordStrengthType.WEAK);
	}
}
