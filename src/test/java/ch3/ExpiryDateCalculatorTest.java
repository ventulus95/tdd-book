package ch3;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

/**
 * 전제 조건
 * 서비스 사용하려면 매달 1만원을 선불로 납부한다. 납부일 기준으로 한달뒤 서비스 만료일이 됨.
 * 2개월 이상 요금을 납부할 수 있다.
 * 10만원을 납부하면 서비스 1년제공
 */
public class ExpiryDateCalculatorTest {

	@Test
	void 만원을_납부하면_한달_뒤가_만료일이_됨() {
		LocalDate billingDate = LocalDate.of(2023, 10, 24);
		int payAmount = 10000;

		ExpiryDateCalculator calculator = new ExpiryDateCalculator();
		LocalDate expiredDate = calculator.calculatorExpireDate(payAmount, billingDate);

		assertEquals(LocalDate.of(2023, 11,24), expiredDate);
	}

}
