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
		LocalDate billingDate2 = LocalDate.of(2023, 9, 24);
		int payAmount = 10000;

		assertExpiryDate(payAmount, billingDate, LocalDate.of(2023, 11, 24));
		assertExpiryDate(payAmount, billingDate2, LocalDate.of(2023, 10, 24));

	}

	//이러면 예제를 만드는 경우때문에 강재로 중복이 발생함.  중복 발생? -> 오 리팩토링할거리!
	private void assertExpiryDate(int payAmount, LocalDate billingDate, LocalDate resultDate) {
		ExpiryDateCalculator calculator = new ExpiryDateCalculator();
		LocalDate expiredDate = calculator.calculatorExpireDate(payAmount, billingDate);
		assertEquals(resultDate, expiredDate);
	}

	//이제는 예외를 만들어서 처리하면됨.
	@Test
	void 납부일과_한달_뒤_일자가_같지_않음() {
		assertExpiryDate(10000, LocalDate.of(2019, 1, 31), LocalDate.of(2019, 2, 28));
		assertExpiryDate(10000, LocalDate.of(2019, 5, 31), LocalDate.of(2019, 6, 30));
	}


}
