package ch3;

import java.time.LocalDate;

public class ExpiryDateCalculator {
	public LocalDate calculatorExpireDate(int payAmount, LocalDate billingDate) {
		return billingDate.plusMonths(1); // 처음에는 상수 잡았다가, 차후에 형태를 틀어내버림
	}
}
