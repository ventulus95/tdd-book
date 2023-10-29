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
		assertExpiryDate(PayData.builder()
			.payAmount(10000)
			.billingDate(LocalDate.of(2023, 10, 24))
			.build(), LocalDate.of(2023, 11, 24));
		assertExpiryDate(PayData.builder()
			.payAmount(10000)
			.billingDate(LocalDate.of(2023, 9, 24))
			.build(), LocalDate.of(2023, 10, 24));
	}

	//이러면 예제를 만드는 경우때문에 강재로 중복이 발생함.  중복 발생? -> 오 리팩토링할거리!
	private void assertExpiryDate(PayData payData, LocalDate resultDate) {
		ExpiryDateCalculator calculator = new ExpiryDateCalculator();
		LocalDate expiredDate = calculator.calculatorExpiryDate(payData);
		assertEquals(resultDate, expiredDate);
	}

	//이제는 예외를 만들어서 처리하면됨.
	@Test
	void 납부일과_한달_뒤_일자가_같지_않음() {
		assertExpiryDate(PayData.builder()
			.payAmount(10000)
			.billingDate(LocalDate.of(2019, 1, 31))
			.build(), LocalDate.of(2019, 2, 28));
		assertExpiryDate(PayData.builder()
				.payAmount(10000)
				.billingDate(LocalDate.of(2019, 5, 31))
				.build(), LocalDate.of(2019, 6, 30));
	}

	@Test
	void 첫_납부일과_만료일_일자가_다를때_만원_납부(){
		PayData payData = PayData.builder()
			.firstBillingDate(LocalDate.of(2023,1,31))
			.billingDate(LocalDate.of(2023, 2, 28))
			.payAmount(10000)
			.build();

		assertExpiryDate(payData, LocalDate.of(2023, 3, 31));

		PayData payData2 = PayData.builder()
			.firstBillingDate(LocalDate.of(2023,1,2))
			.billingDate(LocalDate.of(2023, 2, 28))
			.payAmount(10000)
			.build();

		assertExpiryDate(payData2, LocalDate.of(2023, 3, 2));
	}

	@Test
	void 이만원_이상_납부시_비례해서_만료일_계산(){
		assertExpiryDate(
			PayData.builder()
				.billingDate(LocalDate.of(2023, 3, 1))
				.payAmount(20000)
				.build(),
			LocalDate.of(2023, 5, 1)
		);
		assertExpiryDate(
			PayData.builder()
				.billingDate(LocalDate.of(2023, 3, 1))
				.payAmount(30000)
				.build(),
			LocalDate.of(2023, 6, 1)
		);
	}

	@Test
	void 첫_납부일과_만료일_일자가_다를때_이만원_이상_납부() {
		 assertExpiryDate(PayData.builder()
			 .firstBillingDate(LocalDate.of(2023,1, 31))
			 .billingDate(LocalDate.of(2023, 2, 28))
			 .payAmount(20000)
			 .build(),
			 LocalDate.of(2023, 4, 30));

		assertExpiryDate(PayData.builder()
				.firstBillingDate(LocalDate.of(2023,1, 31))
				.billingDate(LocalDate.of(2023, 2, 28))
				.payAmount(40000)
				.build(),
			LocalDate.of(2023, 6, 30));
	}

	@Test
	void 십만원을_납부하면_1_년_제공() {
		assertExpiryDate(PayData.builder()
			.billingDate(LocalDate.of(2023, 1, 28))
			.payAmount(100000)
			.build(),
			LocalDate.of(2024, 1, 28));

	}
}
