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
package br.gov.frameworkdemoiselle.mail;

import java.io.File;

import javax.mail.internet.InternetAddress;

import br.gov.frameworkdemoiselle.mail.internal.BaseMessage;

/**
 * Base interface for creating and sending email messages.
 *
 * @author CETEC
 */
public interface Mail {

	/**
	 * Add a FROM address to the message.
	 * 
	 * @param address E-mail address.
	 * @return ${@link Mail} instance.
	 */
	public Mail from(String address);

	/**
	 * Add a FROM address to the message.
	 * 
	 * @param address E-mail address.
	 * @param name Personal name.
	 * @return ${@link Mail} instance.
	 */
	public Mail from(String address, String name);

	/**
	 * Add a FROM address to the message.
	 * 
	 * @param E-mail {@link InternetAddress}.
	 * @return ${@link Mail} instance.
	 */
	public Mail from(InternetAddress address);

	/**
	 * Add a REPLY TO address to the message.
	 * 
	 * @param address E-mail address.
	 */
	public Mail replyTo(String address);

	/**
	 * Add a REPLY TO address to the message.
	 * 
	 * @param address E-mail address.
	 * @param name Personal name.
	 */
	public Mail replyTo(String address, String name);

	/**
	 * Add a REPLY TO address to the message.
	 * 
	 * @param E-mail {@link InternetAddress}.
	 * @return ${@link Mail} instance.
	 */
	public Mail replyTo(InternetAddress emailAddress);

	/**
	 * Add a TO address to the message.
	 * 
	 * @param address E-mail address.
	 * @return ${@link Mail} instance.
	 */
	public Mail to(String address);

	/**
	 * Add a TO address to the message.
	 * 
	 * @param address E-mail address.
	 * @param name Personal name.
	 * @return ${@link Mail} instance.
	 */
	public Mail to(String address, String name);

	/**
	 * Add a to address to the message.
	 * 
	 * @param address E-mail {@link InternetAddress}.
	 * @return ${@link Mail} instance.
	 */
	public Mail to(InternetAddress address);

	/**
	 * Add a Carbon Copy (CC) address to the message.
	 * 
	 * @param address E-mail address.
	 * @return ${@link Mail} instance.
	 */
	public Mail cc(String address);

	/**
	 * Add a Carbon Copy (CC) address to the message.
	 * 
	 * @param address E-mail address.
	 * @param name Personal name.
	 * @return ${@link Mail} instance.
	 */
	public Mail cc(String address, String name);

	/**
	 * Add a Carbon Copy (CC) address to the message.
	 * 
	 * @param address E-mail {@link InternetAddress}.
	 * @return ${@link Mail} instance.
	 */
	public Mail cc(InternetAddress address);

	/**
	 * Add a Blind Carbon Copy (BCC) address to the message.
	 * 
	 * @param address E-mail address.
	 * @return ${@link Mail} instance.
	 */
	public Mail bcc(String address);

	/**
	 * Add a Blind Carbon Copy (BCC) address to the message.
	 * 
	 * @param address E-mail address.
	 * @param name Personal name.
	 * @return ${@link Mail} instance.
	 */
	public Mail bcc(String address, String name);

	/**
	 * Add a Blind Carbon Copy (BCC) address to the message.
	 * 
	 * @param address E-mail {@link InternetAddress}.
	 * @return ${@link Mail} instance.
	 */
	public Mail bcc(InternetAddress address);

	/**
	 * Define the importance level of the message.
	 * 
	 * @return ${@link Importance} instance.
	 */
	public Importance importance();

	/**
	 * Add a delivery receipt "Return-Receipt-To".
	 * 
	 * @param address E-mail address.
	 * @return ${@link Mail} instance.
	 */
	public Mail deliveryReceipt(String address);

	/**
	 * Add a read receipt "Disposition-Notification-To".
	 * 
	 * @param address E-mail address.
	 * @return ${@link Mail} instance.
	 */
	public Mail readReceipt(String address);

	/**
	 * Set e-mail's subject.
	 * 
	 * @param value Subject value.
	 * @return ${@link Mail} instance.
	 */
	public Mail subject(String value);

	/**
	 * Set e-mail's body.
	 * 
	 * @return ${@link Mail} instance.
	 */
	public Body body();

	/**
	 * Send the message.
	 * 
	 * @return ${@link Mail} instance.
	 */
	public BaseMessage send();

	/**
	 * Attachs a file to the message.
	 * 
	 * @return ${@link Mail} instance.
	 */
	public Attach attach();

	/**
	 * Convenience interface used to define the importance level of the e-mail message.
	 * 
	 * @author CETEC
	 */
	public interface Importance {

		/**
		 * Sets the message importance level to HIGH.
		 * 
		 * @return ${@link Mail} instance.
		 */
		Mail high();

		/**
		 * Sets the message importance level to NORMAL.
		 * 
		 * @return ${@link Mail} instance.
		 */
		Mail normal();

		/**
		 * Sets the message importance level to LOW.
		 * 
		 * @return ${@link Mail} instance.
		 */
		Mail low();
	}

	/**
	 * Convenience interface used to define the method to get the attachment.
	 * 
	 * @author CETEC
	 */
	public interface Attach {

		/**
		 * Adds the given file as attachment to the message.
		 * 
		 * @param file File.
		 * @return ${@link Disposition} instance.
		 */
		Disposition file(File file);

		/**
		 * Adds a file based on the given filename.
		 * 
		 * @param fileName File name.
		 * @return ${@link Disposition} instance.
		 */
		Disposition file(String fileName);

		/**
		 * Adds a file based on the given URL.
		 * 
		 * @param url URL holding the file's content.
		 * @param fileName Name that will be used as filename.
		 * @return ${@link Disposition} instance.
		 */
		Disposition url(String url, String fileName);

	}

	/**
	 * Convenience interface used to define the  attachment's disposition in the message.
	 * 
	 * @author CETEC
	 */
	public interface Disposition {

		/**
		 * Inline disposition. The attachment will be put in the message's body.
		 * 
		 * @return ${@link Mail} instance.
		 */
		Mail inline();

		/**
		 * Attachment disposition.
		 * 
		 * @return ${@link Mail} instance.
		 */
		Mail attachment();
	}

	/**
	 * Convenience interface used to define the content of the message.
	 * 
	 * @author CETEC
	 */
	public interface Body {

		/**
		 * Define the content as a plain text.
		 * 
		 * @param text Text.
		 * @return ${@link Mail} instance.
		 */
		Mail text(String text);

		/**
		 * Define the content as an HTML.
		 * 
		 * @param html HTML.
		 * @return ${@link Mail} instance.
		 */
		Mail html(String html);
	}

}
