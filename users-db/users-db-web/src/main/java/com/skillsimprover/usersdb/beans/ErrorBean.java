package com.skillsimprover.usersdb.beans;

public class ErrorBean {

	private String errorCode;

	private String errorText;

	public ErrorBean() {
		super();
	}

	public ErrorBean(String errorCode, String errorText) {
		super();
		this.errorCode = errorCode;
		this.errorText = errorText;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorText() {
		return errorText;
	}

	public void setErrorText(String errorText) {
		this.errorText = errorText;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((errorCode == null) ? 0 : errorCode.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (obj == null) {
			return false;
		}

		if (getClass() != obj.getClass()) {
			return false;
		}

		ErrorBean other = (ErrorBean) obj;
		if (errorCode == null) {
			if (other.errorCode != null) {
				return false;
			}
		} else if (!errorCode.equals(other.errorCode)) {
			return false;
		}

		return true;
	}

	@Override
	public String toString() {
		return "ErrorBean [errorCode=" + errorCode + ", errorText=" + errorText + "]";
	}
}
