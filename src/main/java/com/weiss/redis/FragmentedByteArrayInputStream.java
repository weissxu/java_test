package com.weiss.redis;

import java.io.ByteArrayInputStream;

public class FragmentedByteArrayInputStream extends ByteArrayInputStream {
	private int readMethodCallCount = 0;

	public FragmentedByteArrayInputStream(final byte[] buf) {
		super(buf);
	}

	@Override
	public synchronized int read(final byte[] b, final int off, final int len) {
		readMethodCallCount++;
		if (len <= 10) {
			// if the len <= 10, return as usual ..
			return super.read(b, off, len);
		} else {
			// else return the first half ..
			return super.read(b, off, len / 2);
		}
	}

	public int getReadMethodCallCount() {
		return readMethodCallCount;
	}

}