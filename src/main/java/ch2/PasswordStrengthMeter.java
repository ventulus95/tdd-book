package ch2;

import java.util.List;

public class PasswordStrengthMeter {

	public PasswordStrengthType evaluate(String password) {
		if (password == null || password.isEmpty() ) {
			return PasswordStrengthType.INVALID;
		}
		int conditionCnt = getCriteriaConditionCounts(password); //
		if(conditionCnt <= 1 ) {
			return PasswordStrengthType.WEAK;
		}
		else if (conditionCnt == 2) {
			return PasswordStrengthType.NORMAL;
		}

		return PasswordStrengthType.STRONG;
	}

	private int getCriteriaConditionCounts(String password) {
		int conditionCnt = 0;
		boolean lengthCondition = passwordLengthCondition(password);
		boolean upperLetterCondition = upperLetterCondition(password);
		boolean containsNumbers = isContainsNumbers(password);
		List<Boolean> conditionList = List.of(lengthCondition, upperLetterCondition, containsNumbers);
		for (Boolean flag: conditionList) {
			if (flag)
				conditionCnt++;
		}
		return conditionCnt;
	}

	private boolean passwordLengthCondition(String password) {
		return password.length() >= 8;
	}

	private boolean upperLetterCondition(String password) {
		int upperLetterCount = 0;
		for (char ch: password.toCharArray()) {
			if (ch >= 'A' && ch <= 'Z') {
				upperLetterCount++;
			}
		}
		return upperLetterCount > 0;
	}

	private boolean isContainsNumbers(String password) {
		boolean containsNumbers = false;
		for (char ch: password.toCharArray()) {
			if (ch>= '0' & ch <='9') {
				containsNumbers = true;
				break;
			}
		}
		return containsNumbers;
	}
}
