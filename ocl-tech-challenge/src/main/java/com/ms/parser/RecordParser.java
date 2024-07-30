package com.ms.parser;

public interface RecordParser<T> {

	T parseRecord(String record);
}
