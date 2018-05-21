package com.znb.cms.common;

public class AjaxResult {

	private final int status;

	public AjaxResult(int status) {
		this.status = status;
	}

	public int getStatus() {
		return status;
	}

	public static class success extends AjaxResult {

		private final Object result;

		public success() {
			super(0);
			result = null;
		}

		public success(Object result) {
			super(0);
			this.result = result;
		}

		public Object getResult() {
			return result;
		}

	}

	public static class error extends AjaxResult {

		private final String code;

		private final String error;


		public error(String code, String error) {
			super(500);
			this.error = error;
			this.code = code;
		}

		public error(String error) {
			this("00001", error);
		}

		public String getError() {
			return error;
		}

		public String getCode() {
			return code;
		}

	}

	public static class redirct extends AjaxResult {

		private final String url;

		public redirct(String url) {
			super(302);
			this.url = url;
		}

		public String getUrl() {
			return url;
		}

	}

}
