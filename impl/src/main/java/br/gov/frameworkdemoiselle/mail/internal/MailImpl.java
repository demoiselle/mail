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
package br.gov.frameworkdemoiselle.mail.internal;

import java.io.File;

import javax.mail.SendFailedException;
import javax.mail.internet.InternetAddress;

import br.gov.frameworkdemoiselle.mail.Mail;
import br.gov.frameworkdemoiselle.mail.internal.enums.ContentDisposition;
import br.gov.frameworkdemoiselle.mail.internal.enums.MessagePriority;
import br.gov.frameworkdemoiselle.mail.internal.implementation.FileAttachment;
import br.gov.frameworkdemoiselle.mail.internal.implementation.URLAttachment;
import br.gov.frameworkdemoiselle.mail.util.MailUtil;
import br.gov.frameworkdemoiselle.util.Beans;

public class MailImpl implements Mail {

	private BaseMessage emailMessage = new BaseMessage();
	
	private Config config;

	public MailImpl() {
		config = Beans.getReference(Config.class);
	}
	
	public Mail from(String address) {
		emailMessage.addFromAddress(MailUtil.getInternetAddress(address));
		return this;
	}

	public Mail from(String address, String name) {
		emailMessage.addFromAddress(MailUtil.getInternetAddress(address, name));
		return this;
	}

	public Mail from(InternetAddress emailAddress) {
		emailMessage.addFromAddress(emailAddress);
		return this;
	}

	public Mail replyTo(String address) {
		emailMessage.addReplyToAddress(MailUtil.getInternetAddress(address));
		return this;
	}

	public Mail replyTo(String address, String name) {
		emailMessage.addReplyToAddress(MailUtil.getInternetAddress(address, name));
		return this;
	}

	public Mail replyTo(InternetAddress emailAddress) {
		emailMessage.addReplyToAddress(emailAddress);
		return this;
	}

	public Mail to(String address) {
		emailMessage.addToAddress(MailUtil.getInternetAddress(address));
		return this;
	}

	public Mail to(String address, String name) {
		emailMessage.addToAddress(MailUtil.getInternetAddress(address, name));
		return this;
	}

	public Mail to(InternetAddress emailAddress) {
		emailMessage.addToAddress(emailAddress);
		return this;
	}

	public Mail cc(String address) {
		emailMessage.addCcAddress(MailUtil.getInternetAddress(address));
		return this;
	}

	public Mail cc(String address, String name) {
		emailMessage.addCcAddress(MailUtil.getInternetAddress(address, name));
		return this;
	}

	public Mail cc(InternetAddress emailAddress) {
		emailMessage.addCcAddress(emailAddress);
		return this;
	}

	public Mail bcc(String address) {
		emailMessage.addBccAddress(MailUtil.getInternetAddress(address));
		return this;
	}

	public Mail bcc(String address, String name) {
		emailMessage.addBccAddress(MailUtil.getInternetAddress(address, name));
		return this;
	}

	public Mail bcc(InternetAddress emailAddress) {
		emailMessage.addBccAddress(emailAddress);
		return this;
	}

	public Mail deliveryReceipt(String address) {
		emailMessage.addDeliveryReceiptAddress(MailUtil.getInternetAddress(address));
		return this;
	}

	public Mail readReceipt(String address) {
		emailMessage.addReadReceiptAddress(MailUtil.getInternetAddress(address));
		return this;
	}

	public Mail subject(String value) {
		emailMessage.setSubject(value);
		return this;
	}

	public BaseMessage send() {
		Dispatcher dispatcher = new Dispatcher(MailUtil.createSession(config), emailMessage);
		BaseMessage old = this.emailMessage;
		try {
			dispatcher.send();
		} catch (SendFailedException e) {
			throw new RuntimeException(e);
		}
		this.emailMessage = new BaseMessage();
		return old;
	}

	public Attach attach() {
		Attach attach = new Attach() {

			public Disposition url(final String url, final String fileName) {
				Disposition disposition = new Disposition() {

					public Mail inline() {
						URLAttachment attachment = new URLAttachment(url, fileName, ContentDisposition.INLINE);
						emailMessage.addAttachment(attachment);
						return MailImpl.this;
					}

					public Mail attachment() {
						URLAttachment attachment = new URLAttachment(url, fileName, ContentDisposition.ATTACHMENT);
						emailMessage.addAttachment(attachment);
						return MailImpl.this;
					}

				};
				return disposition;
			}

			public Disposition file(final String fileName) {
				Disposition disposition = new Disposition() {

					public Mail inline() {
						FileAttachment attachment = new FileAttachment(ContentDisposition.INLINE, new File(fileName));
						emailMessage.addAttachment(attachment);
						return MailImpl.this;
					}

					public Mail attachment() {
						FileAttachment attachment = new FileAttachment(ContentDisposition.ATTACHMENT,
								new File(fileName));
						emailMessage.addAttachment(attachment);
						return MailImpl.this;
					}

				};
				return disposition;
			}

			public Disposition file(final File file) {
				Disposition disposition = new Disposition() {

					public Mail inline() {
						FileAttachment attachment = new FileAttachment(ContentDisposition.INLINE, file);
						emailMessage.addAttachment(attachment);
						return MailImpl.this;
					}

					public Mail attachment() {
						FileAttachment attachment = new FileAttachment(ContentDisposition.ATTACHMENT, file);
						emailMessage.addAttachment(attachment);
						return MailImpl.this;
					}

				};
				return disposition;
			}

		};
		return attach;
	}

	@Override
	public Importance importance() {
		Importance importance = new Importance() {

			@Override
			public Mail high() {
				emailMessage.setImportance(MessagePriority.HIGH);
				return MailImpl.this;
			}

			@Override
			public Mail normal() {
				emailMessage.setImportance(MessagePriority.NORMAL);
				return MailImpl.this;
			}

			@Override
			public Mail low() {
				emailMessage.setImportance(MessagePriority.LOW);
				return MailImpl.this;
			}

		};
		return importance;
	}

	@Override
	public Body body() {
		Body body = new Body() {

			@Override
			public Mail text(String text) {
				emailMessage.setTextBody(text);
				return MailImpl.this;
			}

			@Override
			public Mail html(String html) {
				emailMessage.setHtmlBody(html);
				return MailImpl.this;
			}
		};
		return body;
	}

}
