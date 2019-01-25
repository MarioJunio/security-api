package br.com.security.rest.stub;

public class ResponseSms {

	private SmsResponse sendSmsResponse;

	public class SmsResponse {

		private String statusCode;
		private String statusDescription;
		private String detailCode;
		private String detailDescription;

		public String getStatusCode() {
			return statusCode;
		}

		public void setStatusCode(String statusCode) {
			this.statusCode = statusCode;
		}

		public String getStatusDescription() {
			return statusDescription;
		}

		public void setStatusDescription(String statusDescription) {
			this.statusDescription = statusDescription;
		}

		public String getDetailCode() {
			return detailCode;
		}

		public void setDetailCode(String detailCode) {
			this.detailCode = detailCode;
		}

		public String getDetailDescription() {
			return detailDescription;
		}

		public void setDetailDescription(String detailDescription) {
			this.detailDescription = detailDescription;
		}

		@Override
		public String toString() {
			return "SmsResponse [statusCode=" + statusCode + ", statusDescription=" + statusDescription
					+ ", detailCode=" + detailCode + ", detailDescription=" + detailDescription + "]";
		}
	}

	public SmsResponse getSendSmsResponse() {
		return sendSmsResponse;
	}

	public void setSendSmsResponse(SmsResponse sendSmsResponse) {
		this.sendSmsResponse = sendSmsResponse;
	}

	@Override
	public String toString() {
		return "ResponseSms [sendSmsResponse=" + sendSmsResponse + "]";
	}

}
