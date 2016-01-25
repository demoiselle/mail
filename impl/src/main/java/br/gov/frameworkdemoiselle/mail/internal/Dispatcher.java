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

import java.util.Date;

import javax.activation.DataHandler;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.SendFailedException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;

import java.util.logging.Level;
import java.util.logging.Logger;

import br.gov.frameworkdemoiselle.internal.producer.LoggerProducer;
import br.gov.frameworkdemoiselle.mail.MailException;
import br.gov.frameworkdemoiselle.mail.internal.enums.ContentDisposition;
import br.gov.frameworkdemoiselle.mail.internal.enums.ContentType;
import br.gov.frameworkdemoiselle.mail.internal.enums.MailHeader;
import br.gov.frameworkdemoiselle.mail.internal.enums.MessagePriority;
import br.gov.frameworkdemoiselle.mail.internal.enums.RecipientType;
import br.gov.frameworkdemoiselle.mail.util.MailUtil;
import br.gov.frameworkdemoiselle.util.Strings;

public class Dispatcher {

	private Logger logger;

	private Multipart multipart = new MimeMultipart(ContentType.MIXED.getValue());

	private MimeMultipart relatedMultipart = new MimeMultipart(ContentType.RELATED.getValue());

	private MimeMessage mimeMessage;

	private String charset;

	private Session session;

	private BaseMessage message;

	public Dispatcher(Session session, BaseMessage message) {
		this.logger = LoggerProducer.create("br.gov.frameworkdemoiselle.mail.internal.Dispatcher");
		logger.log(Level.FINE, "Mail Dispatcher initializing.");
		this.session = session;
		this.message = message;
		this.initialize();
	}

	public void setSentDate(Date date) {
		try {
			mimeMessage.setSentDate(date);
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}

	public void send() throws SendFailedException {
		try {
			logger.info("Preparing to send message");
			Transport.send(mimeMessage);
			logger.info("Message Sent!");
		} catch (MessagingException e) {
			logger.log(Level.SEVERE, "Error sending message.", e);
			throw new SendFailedException("Send Failed", e);
		}
	}

	private void initialize() {
		charset = "UTF-8";
		mimeMessage = createMimeMessage();
		setSentDate(new Date());
	}

	private void setFrom() throws MessagingException {
		logger.log(Level.FINE, "Setting " + message.getFromAddresses().size() + " addresses as 'From'.");

		if (message.getFromAddresses().size() == 1) {
			mimeMessage.setFrom(message.getFromAddresses().iterator().next());
		} else {
			mimeMessage.addFrom(MailUtil.getInternetAddressses(message.getFromAddresses()));
		}
	}

	private void setTo() throws MessagingException {
		logger.log(Level.FINE, "Setting " + message.getToAddresses().size() + " addresses as 'To'.");

		for (InternetAddress address : message.getToAddresses()) {
			mimeMessage.addRecipient(RecipientType.TO.getRecipientType(), address);
		}
	}

	private void setCc() throws MessagingException {
		logger.log(Level.FINE, "Setting " + message.getCcAddresses().size() + " addresses as 'CC'.");

		for (InternetAddress address : message.getCcAddresses()) {
			mimeMessage.addRecipient(RecipientType.CC.getRecipientType(), address);
		}
	}

	private void setBcc() throws MessagingException {
		logger.log(Level.FINE , "Setting " + message.getBccAddresses().size() + " addresses as 'BCC'.");

		for (InternetAddress address : message.getBccAddresses()) {
			mimeMessage.addRecipient(RecipientType.BCC.getRecipientType(), address);
		}
	}

	private void setReplyTo() throws MessagingException {
		logger.log(Level.FINE, "Setting " + message.getReplyToAddresses().size() + " addresses as 'Reply-To'.");

		if (message.getReplyToAddresses().size() > 0) {
			mimeMessage.setReplyTo(MailUtil.getInternetAddressses(message.getReplyToAddresses()));
		}
	}

	private void setReadReceipt() throws MessagingException {
		logger.log(Level.FINE, "Setting " + message.getReadReceiptAddresses().size() + " addresses as 'Read Receipt'.");

		for (InternetAddress address : message.getReadReceiptAddresses()) {
			mimeMessage.setHeader(MailHeader.READ_RECIEPT.headerValue(), "<" + address.getAddress() + ">");
		}
	}

	private void setDeliveryReceipt() throws MessagingException {
		logger.log(Level.FINE, "Setting " + message.getDeliveryReceiptAddresses().size() + " addresses as 'Delivery Receipt'.");

		for (InternetAddress address : message.getDeliveryReceiptAddresses()) {
			mimeMessage.setHeader(MailHeader.DELIVERY_RECIEPT.headerValue(), "<" + address.getAddress() + ">");
		}
	}

	private void setImportance() throws MessagingException {
		logger.log(Level.FINE, "Setting message importance:" + message.getImportance().getImportance());

		if (message.getImportance() != null && message.getImportance() != MessagePriority.NORMAL) {
			mimeMessage.setHeader(MailHeader.XPRIORITY.headerValue(), message.getImportance().getX_priority());
			mimeMessage.setHeader(MailHeader.PRIORITY.headerValue(), message.getImportance().getPriority());
			mimeMessage.setHeader(MailHeader.IMPORTANCE.headerValue(), message.getImportance().getImportance());
		}
	}

	private MimeBodyPart createTextBodyPart(String text) {
		logger.log(Level.FINE, "Defining Text Body.");

		MimeBodyPart textBodyPart = new MimeBodyPart();

		try {
			textBodyPart.setDisposition(ContentDisposition.INLINE.headerValue());
			textBodyPart.setText(text, charset);
		} catch (MessagingException e) {
			throw new MailException("Can't set Text Body", e);
		}

		return textBodyPart;
	}

	private void setContent() throws MessagingException {
		logger.log(Level.FINE, "Setting e-mail content.");

		if (!Strings.isEmpty(message.getTextBody())) {
			multipart.addBodyPart(createTextBodyPart(message.getTextBody()));
		} else if (!Strings.isEmpty(message.getHtmlBody())) {
			MimeBodyPart relatedBodyPart = new MimeBodyPart();
			try {
				relatedMultipart.addBodyPart(createHTMLBodyPart(message.getHtmlBody()));
				relatedBodyPart.setContent(relatedMultipart);
				multipart.addBodyPart(relatedBodyPart);
			} catch (MessagingException e) {
				throw new MailException("Can't set email content", e);
			}
		}
	}

	private MimeBodyPart createHTMLBodyPart(String html) {
		logger.log(Level.FINE, "Setting HTML body.");

		MimeBodyPart htmlBodyPart = new MimeBodyPart();
		try {
			htmlBodyPart.setDisposition(ContentDisposition.INLINE.headerValue());
			htmlBodyPart.setText(html, charset, "html");
		} catch (MessagingException e) {
			throw new MailException("Can't create HTML Body Part", e);
		}

		return htmlBodyPart;
	}

	private void setAttachments() {
		logger.log(Level.FINE, "Setting attachments.");

		for (Attachment attachment : message.getAttachments()) {
			MimeBodyPart messageBodyPart = new MimeBodyPart();
			ByteArrayDataSource datasource = new ByteArrayDataSource(attachment.getBytes(), attachment.getMimeType());
			try {
				messageBodyPart.setDataHandler(new DataHandler(datasource));
				messageBodyPart.setFileName(attachment.getFileName());
				multipart.addBodyPart(messageBodyPart);
			} catch (MessagingException e) {
				throw new MailException("Can't add attachment.", e);
			}
		}
	}

	private MimeMessage createMimeMessage() {
		mimeMessage = new MimeMessage(session);
		try {
			mimeMessage.setContent(multipart);
			setFrom();
			setTo();
			setCc();
			setBcc();
			setReplyTo();
			setReadReceipt();
			setDeliveryReceipt();
			setImportance();
			setAttachments();
			mimeMessage.setSubject(message.getSubject(), charset);
			setContent();
		} catch (Exception e) {
			throw new MailException("Error preparing e-mail message", e);
		}

		return mimeMessage;
	}

}
