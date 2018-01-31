package fr.ajmani.apps.tehara.csv;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

public abstract class Importer<T> {

	protected abstract String getDatafilename();

	public abstract List<T> getDatas() throws IOException;

	public abstract T convertToEntity(CSVRecord record);

	protected Integer parseInt(String csvRecordValue) {
		return (csvRecordValue == null) ? null : Integer.valueOf(csvRecordValue);
	}

	protected List<T> getDatas(Class<T> clazz) throws IOException {

		List<T> datas = new ArrayList<T>();
		InputStream input = this.getClass().getClassLoader().getResourceAsStream("data/" + getDatafilename());

		Reader in = new InputStreamReader(input);

		Iterable<CSVRecord> records = CSVFormat.DEFAULT.withNullString("\\N").withQuote('"').withEscape('\\').parse(in);
		for (CSVRecord record : records) {
			T entity = convertToEntity(record);
			if (entity != null) {
				datas.add(entity);
			}
		}
		return datas;
	}

}
