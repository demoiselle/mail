/*
 * Demoiselle Framework
 * Copyright (C) 2010 SERPRO
 * ----------------------------------------------------------------------------
 * This file is part of Demoiselle Framework.
 * 
 * Demoiselle Framework is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public License version 3
 * as published by the Free Software Foundation.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public License version 3
 * along with this program; if not,  see <http://www.gnu.org/licenses/>
 * or write to the Free Software Foundation, Inc., 51 Franklin Street,
 * Fifth Floor, Boston, MA  02110-1301, USA.
 * ----------------------------------------------------------------------------
 * Este arquivo é parte do Framework Demoiselle.
 * 
 * O Framework Demoiselle é um software livre; você pode redistribuí-lo e/ou
 * modificá-lo dentro dos termos da GNU LGPL versão 3 como publicada pela Fundação
 * do Software Livre (FSF).
 * 
 * Este programa é distribuído na esperança que possa ser útil, mas SEM NENHUMA
 * GARANTIA; sem uma garantia implícita de ADEQUAÇÃO a qualquer MERCADO ou
 * APLICAÇÃO EM PARTICULAR. Veja a Licença Pública Geral GNU/LGPL em português
 * para maiores detalhes.
 * 
 * Você deve ter recebido uma cópia da GNU LGPL versão 3, sob o título
 * "LICENCA.txt", junto com esse programa. Se não, acesse <http://www.gnu.org/licenses/>
 * ou escreva para a Fundação do Software Livre (FSF) Inc.,
 * 51 Franklin St, Fifth Floor, Boston, MA 02111-1301, USA.
 */
package br.gov.frameworkdemoiselle.mail.util;

import java.io.UnsupportedEncodingException;
import java.util.Collection;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Session;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.naming.InitialContext;

import br.gov.frameworkdemoiselle.mail.MailException;
import br.gov.frameworkdemoiselle.mail.internal.Lookup;
import br.gov.frameworkdemoiselle.mail.internal.Config;
import br.gov.frameworkdemoiselle.mail.internal.enums.MailType;

final public class MailUtil {

	private MailUtil() {
		throw new AssertionError();
	}

	public static InternetAddress getInternetAddress(String address) {
		try {
			return new InternetAddress(address);
		} catch (AddressException e) {
			throw new RuntimeException(e);
		}
	}

	public static InternetAddress getInternetAddress(String address, String name) {
		InternetAddress internetAddress;
		try {
			internetAddress = new InternetAddress(address);
			internetAddress.setPersonal(name);
			return internetAddress;
		} catch (AddressException e) {
			throw new RuntimeException(e);
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}
	}

	public static InternetAddress[] getInternetAddressses(InternetAddress emaiAddress) {
		InternetAddress[] internetAddresses = { emaiAddress };

		return internetAddresses;
	}

	public static InternetAddress[] getInternetAddressses(Collection<InternetAddress> recipients) {
		InternetAddress[] result = new InternetAddress[recipients.size()];
		recipients.toArray(result);
		return result;
	}

	public static Session createSession(final Config config) {
		Session session = null;

		MailType type = config.getType();
		if (type == null) {
			type = MailType.LOCAL;
		}

		switch (type) {
			case LOCAL:
				Properties props = new Properties();
				props.put("mail.smtp.host", config.getServerHost());
				props.put("mail.smtp.port", config.getServerPort());
				props.put("mail.smtp.starttls.enable", config.isEnableTls());
				props.put("mail.smtp.starttls.required", config.isRequireTls());
				props.put("mail.smtp.ssl.enable", config.isEnableSsl());
				props.put("mail.smtp.auth", config.isAuth());

				Authenticator auth = config.getAuthenticator();
				session = Session.getInstance(props, auth);

				break;
			case PROVIDED:
				String mailLookupClass = config.getMailLookupClass();
				String jndi;
				if (mailLookupClass != null) {
					Lookup lookup;
					try {
						lookup = (Lookup) Class.forName(mailLookupClass).newInstance();
					} catch (Exception e) {
						throw new MailException("Error in mail lookup class configuration: " + mailLookupClass, e);
					}
					jndi = lookup.getMailName();
				} else {
					jndi = Lookup.DEFAULT_MAIL_NAME;
				}

				try {
					InitialContext ctx = new InitialContext();
					session = (Session) ctx.lookup(jndi);
				} catch (Exception e) {
					throw new MailException("Error in lookup mail session " + jndi, e);
				}
				break;
			default:
				throw new MailException("Mail Type not implemented");
		}
		return session;
	}

}
