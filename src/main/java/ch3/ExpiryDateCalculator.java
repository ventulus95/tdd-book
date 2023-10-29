package ch3;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.Objects;

public class ExpiryDateCalculator {
	public LocalDate calculatorExpiryDate(PayData payData) { // 아래형태로 리펙토링하고...
		LocalDate billingDate = payData.getBillingDate();
		LocalDate firstBillingDate = payData.getFirstBillingDate();
		int addMonth = payData.getPayAmount() == 100_000 ? 12 : payData.getPayAmount() / 10000;
		if (Objects.nonNull(firstBillingDate))  {
			LocalDate candidateDate = billingDate.plusMonths(addMonth);
			int firstBillingDateDayOfMonth = firstBillingDate.getDayOfMonth();
			if (firstBillingDateDayOfMonth != billingDate.getDayOfMonth()) {
				int lengthOfMonth = YearMonth.from(candidateDate).lengthOfMonth();
				if (lengthOfMonth < firstBillingDateDayOfMonth) {
					return candidateDate.withDayOfMonth(lengthOfMonth);
				}
				return candidateDate.withDayOfMonth(firstBillingDateDayOfMonth);
			}
		}
		return billingDate.plusMonths(addMonth);
	}
}
