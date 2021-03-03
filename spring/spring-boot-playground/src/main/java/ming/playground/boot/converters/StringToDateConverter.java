package ming.playground.boot.converters;

import org.springframework.core.convert.converter.Converter;

import java.text.SimpleDateFormat;
import java.util.Date;

public class StringToDateConverter implements Converter<String, Date> {
  private SimpleDateFormat sdf;
  public StringToDateConverter(SimpleDateFormat sdf) {
    this.sdf = sdf;
  }

  @Override
  public Date convert(String source) {
    return new Date();
  }
}
