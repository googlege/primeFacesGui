package de.homedev.primefaces.api.util;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Optional;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import de.homedev.primefaces.api.model.PersonEntity;

public final class ApiUtil {
	private ApiUtil() {
	}

	private static final int BUFFER_START_SIZE = 1024 * 40;
	private static final Charset utf8Charset = Charset.forName("UTF-8");
	private static final int BCRYPT_ROUNDS = 12;
	public static final String EMPTY = "";
	public static final SimpleDateFormat dfFull = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");

	public static PasswordEncoder getPasswordEncoder() {
		return new BCryptPasswordEncoder(BCRYPT_ROUNDS);
	}

	public static boolean autenticate(UserDetails entity, String rawPassword) {
		return Optional.ofNullable(entity).map(obj -> getPasswordEncoder().matches(rawPassword, obj.getPassword())).orElse(false);
	}

	public static void main(String[] args) {
		String rawPassword = "user";
		String encodedPassword = "$2a$12$kSx0dpfTkuFzNtxOR85GHOITwRRPg5oJa3k91zmBL5KkgdxwONKVG";
		PasswordEncoder pe = getPasswordEncoder();
		System.out.println(pe.encode(rawPassword));
		System.out.println("match:" + pe.matches(rawPassword, encodedPassword));
	}

	public static String toString(final Date date) {
		return date != null ? dfFull.format(date) : "";
	}

	public static String toString(final ZonedDateTime date) {
		if (date != null) {
			return toString(GregorianCalendar.from(date).getTime());
		}
		return EMPTY;
	}

	public static String throwableToString(Throwable t) {
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		t.printStackTrace(pw);
		return sw.toString(); // stack trace as a string
	}

	public static String stackTrace2String(final Throwable t) {
		final StringWriter stringWriter = new StringWriter();
		t.printStackTrace(new PrintWriter(stringWriter));
		return stringWriter.getBuffer().toString();
	}

	public static String objectToXMLFile(final PersonEntity obj) throws JAXBException {
		final JAXBContext context = JAXBContext.newInstance(PersonEntity.class);
		final Marshaller m = context.createMarshaller();
		m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
		m.setProperty(Marshaller.JAXB_ENCODING, utf8Charset.displayName());
		final StringWriter writer = new StringWriter(BUFFER_START_SIZE);
		m.marshal(obj, writer);
		return writer.toString();
	}
}
