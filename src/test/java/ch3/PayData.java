package ch3;

import java.time.LocalDate;

//파라미터가 3개 생기면서, 줄여야함.
public class PayData {

	private LocalDate billingDate;
	private int payAmount;
	private LocalDate firstBillingDate;

	public PayData() {
	}

	public PayData(LocalDate billingDate, int payAmount, LocalDate firstBillingDate) {
		this.billingDate = billingDate;
		this.payAmount = payAmount;
		this.firstBillingDate = firstBillingDate;
	}

	public LocalDate getBillingDate() {
		return billingDate;
	}

	public int getPayAmount() {
		return payAmount;
	}

	public LocalDate getFirstBillingDate() {
		return firstBillingDate;
	}

	public static Builder builder(){
		return new Builder();
	}

	public static class Builder {

		private PayData payData = new PayData();

		public Builder payAmount(int payAmount) {
			payData.payAmount = payAmount;
			return this;
		}

		public Builder billingDate(LocalDate billingDate) {
			payData.billingDate = billingDate;
			return this;
		}

		public PayData build() {
			return payData;
		}

		public Builder firstBillingDate(LocalDate date) {
			payData.firstBillingDate = date;
			return this;
		}
	}
}
