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

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

import javax.mail.internet.InternetAddress;

import br.gov.frameworkdemoiselle.mail.internal.enums.MessagePriority;

public class BaseMessage {

	private Collection<InternetAddress> fromAddresses = new HashSet<InternetAddress>();

	private Collection<InternetAddress> replyToAddresses = new HashSet<InternetAddress>();

	private Collection<InternetAddress> toAddresses = new HashSet<InternetAddress>();

	private Collection<InternetAddress> ccAddresses = new HashSet<InternetAddress>();

	private Collection<InternetAddress> bccAddresses = new HashSet<InternetAddress>();

	private Collection<Attachment> attachments = new ArrayList<Attachment>();

	private Collection<InternetAddress> deliveryReceiptAddresses = new HashSet<InternetAddress>();

	private Collection<InternetAddress> readReceiptAddresses = new HashSet<InternetAddress>();

	private String subject;

	private String textBody;

	private String htmlBody;

	private MessagePriority importance = MessagePriority.NORMAL;

	public void addAttachment(Attachment attachment) {
		attachments.add(attachment);
	}

	public Collection<InternetAddress> getFromAddresses() {
		return fromAddresses;
	}

	public void addFromAddress(InternetAddress fromAddress) {
		this.fromAddresses.add(fromAddress);
	}

	public void addFromAddresses(Collection<InternetAddress> fromAddresses) {
		this.fromAddresses.addAll(fromAddresses);
	}

	public Collection<InternetAddress> getReplyToAddresses() {
		return replyToAddresses;
	}

	public void addReplyToAddress(InternetAddress replyToAddress) {
		this.replyToAddresses.add(replyToAddress);
	}

	public void addReplyToAddresses(Collection<InternetAddress> replyToAddresses) {
		this.replyToAddresses.addAll(replyToAddresses);
	}

	public Collection<InternetAddress> getToAddresses() {
		return toAddresses;
	}

	public void addToAddress(InternetAddress toAddress) {
		this.toAddresses.add(toAddress);
	}

	public void addToAddresses(Collection<InternetAddress> toAddresses) {
		this.toAddresses.addAll(toAddresses);
	}

	public Collection<InternetAddress> getCcAddresses() {
		return ccAddresses;
	}

	public void addCcAddress(InternetAddress ccAddress) {
		this.ccAddresses.add(ccAddress);
	}

	public void addCcAddresses(Collection<InternetAddress> ccAddresses) {
		this.ccAddresses.addAll(ccAddresses);
	}

	public Collection<InternetAddress> getBccAddresses() {
		return bccAddresses;
	}

	public void addBccAddress(InternetAddress bccAddress) {
		this.bccAddresses.add(bccAddress);
	}

	public void addBccAddresses(Collection<InternetAddress> bccAddresses) {
		this.bccAddresses.addAll(bccAddresses);
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getTextBody() {
		return textBody;
	}

	public void setTextBody(String textBody) {
		this.textBody = textBody;
	}

	public String getHtmlBody() {
		return htmlBody;
	}

	public void setHtmlBody(String htmlBody) {
		this.htmlBody = htmlBody;
	}

	public Collection<InternetAddress> getDeliveryReceiptAddresses() {
		return deliveryReceiptAddresses;
	}

	public void addDeliveryReceiptAddress(InternetAddress address) {
		deliveryReceiptAddresses.add(address);
	}

	public void addDeliveryReceiptAddresses(Collection<InternetAddress> deliveryReceiptAddresses) {
		deliveryReceiptAddresses.addAll(deliveryReceiptAddresses);
	}

	public Collection<InternetAddress> getReadReceiptAddresses() {
		return readReceiptAddresses;
	}

	public void addReadReceiptAddress(InternetAddress address) {
		readReceiptAddresses.add(address);
	}

	public void addReadReceiptAddresses(Collection<InternetAddress> readReceiptAddresses) {
		readReceiptAddresses.addAll(readReceiptAddresses);
	}

	public MessagePriority getImportance() {
		return importance;
	}

	public void setImportance(MessagePriority importance) {
		this.importance = importance;
	}

	public Collection<Attachment> getAttachments() {
		return attachments;
	}

	public void setAttachments(Collection<Attachment> attachments) {
		this.attachments = attachments;
	}

}
