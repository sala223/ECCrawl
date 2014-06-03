package com.eccrawl.core;

import java.io.IOException;
import java.net.MalformedURLException;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;

public class CrawlException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private long errorCode;

	public static final long MALFORMED_URL = 100000;

	public static final long IO_ERROR = 100001;
	
	public static final long SERVER_STATUS_ERROR = 100002;


	public CrawlException(long errorCode, Throwable cause) {
		super(cause);
		this.errorCode = errorCode;
	}

	public CrawlException(long errorCode, Throwable cause, String messageFormat, Object... args) {
		super(String.format(messageFormat, args), cause);
	}

	public CrawlException(String messageFormat, Object... args) {
		super(String.format(messageFormat, args));
	}

	public long getErrorCode() {
		return errorCode;
	}

	public static CrawlException malformedURL(MalformedURLException exception) {
		return new CrawlException(MALFORMED_URL, exception);
	}

	public static CrawlException ioError(IOException exception) {
		return new CrawlException(IO_ERROR, exception);
	}
	
	public static CrawlException serverStatusError(FailingHttpStatusCodeException exception) {
		return new CrawlException(SERVER_STATUS_ERROR, exception);
	}
}
