package ch3;

import java.time.LocalDate;
import java.util.Objects;

public class ExpiryDateCalculator {
	public LocalDate calculatorExpiryDate(PayData payData) { // 아래형태로 리펙토링하고...
		LocalDate billingDate = payData.getBillingDate();
		LocalDate firstBillingDate = payData.getFirstBillingDate();
		int addMonth = 1;
		if (payData.getPayAmount() >= 20000)
			addMonth = payData.getPayAmount()/10000;
		if (Objects.nonNull(firstBillingDate)){
			LocalDate candidateDate = billingDate.plusMonths(addMonth);
			if (firstBillingDate.getDayOfMonth() != billingDate.getDayOfMonth()) {
				return candidateDate.withDayOfMonth(firstBillingDate.getDayOfMonth());
			}
		}
		return billingDate.plusMonths(addMonth);
	}
}
