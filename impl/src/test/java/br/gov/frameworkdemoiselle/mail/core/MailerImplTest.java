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
package br.gov.frameworkdemoiselle.mail.core;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMultipart;

import org.easymock.EasyMock;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.jvnet.mock_javamail.Mailbox;
import org.powermock.api.easymock.PowerMock;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.reflect.Whitebox;

import br.gov.frameworkdemoiselle.internal.producer.LoggerProducer;
import br.gov.frameworkdemoiselle.mail.Mail;
import br.gov.frameworkdemoiselle.mail.internal.BaseMessage;
import br.gov.frameworkdemoiselle.mail.internal.Config;
import br.gov.frameworkdemoiselle.mail.internal.Dispatcher;
import br.gov.frameworkdemoiselle.mail.internal.MailImpl;
import br.gov.frameworkdemoiselle.mail.internal.enums.MailHeader;
import br.gov.frameworkdemoiselle.mail.internal.enums.MessagePriority;
import br.gov.frameworkdemoiselle.mail.internal.enums.RecipientType;
import br.gov.frameworkdemoiselle.util.Beans;

@RunWith(PowerMockRunner.class)
@PrepareForTest({Beans.class, LoggerProducer.class})
public class MailerImplTest {

    @Test
    public void checkFrom1() {
        PowerMock.mockStatic(Beans.class);
        EasyMock.expect(Beans.getReference(Config.class)).andReturn(new Config());

        BaseMessage mailMessage = PowerMock.createMock(BaseMessage.class);

        mailMessage.addFromAddress(EasyMock.anyObject(InternetAddress.class));
        PowerMock.expectLastCall().times(1);

        PowerMock.replayAll(Beans.class);

        Mail mailer = new MailImpl();
        Whitebox.setInternalState(mailer, BaseMessage.class, mailMessage);
        mailer.from("from@frameworkdemoiselle.gov.br");

        PowerMock.verifyAll();
    }

    @Test
    public void checkFrom2() {
        PowerMock.mockStatic(Beans.class);
        EasyMock.expect(Beans.getReference(Config.class)).andReturn(new Config());

        BaseMessage mailMessage = PowerMock.createMock(BaseMessage.class);

        mailMessage.addFromAddress(EasyMock.anyObject(InternetAddress.class));
        PowerMock.expectLastCall().times(2);
        PowerMock.replayAll();

        Mail mailer = new MailImpl();
        Whitebox.setInternalState(mailer, BaseMessage.class, mailMessage);
        mailer.from("from@frameworkdemoiselle.gov.br").from("from@frameworkdemoiselle.gov.br");

        PowerMock.verifyAll();
    }

    @Test
    public void checkTo() {
        PowerMock.mockStatic(Beans.class);
        EasyMock.expect(Beans.getReference(Config.class)).andReturn(new Config());

        BaseMessage mailMessage = PowerMock.createMock(BaseMessage.class);

        mailMessage.addToAddress(EasyMock.anyObject(InternetAddress.class));
        PowerMock.expectLastCall().times(1);
        PowerMock.replayAll();

        Mail mailer = new MailImpl();
        Whitebox.setInternalState(mailer, BaseMessage.class, mailMessage);
        mailer.to("to@frameworkdemoiselle.gov.br");

        PowerMock.verifyAll();
    }

    @Test
    public void checkCC() {
        PowerMock.mockStatic(Beans.class);
        EasyMock.expect(Beans.getReference(Config.class)).andReturn(new Config());

        BaseMessage mailMessage = PowerMock.createMock(BaseMessage.class);

        mailMessage.addCcAddress(EasyMock.anyObject(InternetAddress.class));
        PowerMock.expectLastCall().times(1);
        PowerMock.replayAll();

        Mail mailer = new MailImpl();
        Whitebox.setInternalState(mailer, BaseMessage.class, mailMessage);
        mailer.cc("cc@frameworkdemoiselle.gov.br");

        PowerMock.verifyAll();
    }

    @Test
    public void checkBCC() {
        PowerMock.mockStatic(Beans.class);
        EasyMock.expect(Beans.getReference(Config.class)).andReturn(new Config());

        BaseMessage mailMessage = PowerMock.createMock(BaseMessage.class);

        mailMessage.addBccAddress(EasyMock.anyObject(InternetAddress.class));
        PowerMock.expectLastCall().times(1);
        PowerMock.replayAll();

        Mail mailer = new MailImpl();
        Whitebox.setInternalState(mailer, BaseMessage.class, mailMessage);
        mailer.bcc("bcc@frameworkdemoiselle.gov.br");

        PowerMock.verifyAll();
    }

    @Test
    public void checkReplyTo() {
        PowerMock.mockStatic(Beans.class);
        EasyMock.expect(Beans.getReference(Config.class)).andReturn(new Config());

        BaseMessage mailMessage = PowerMock.createMock(BaseMessage.class);

        mailMessage.addReplyToAddress(EasyMock.anyObject(InternetAddress.class));
        PowerMock.expectLastCall().times(1);
        PowerMock.replayAll();

        Mail mailer = new MailImpl();
        Whitebox.setInternalState(mailer, BaseMessage.class, mailMessage);
        mailer.replyTo("replyto@frameworkdemoiselle.gov.br");

        PowerMock.verifyAll();
    }

    @Test
    public void checkDeliveryReceipt() {
        PowerMock.mockStatic(Beans.class);
        EasyMock.expect(Beans.getReference(Config.class)).andReturn(new Config());

        BaseMessage mailMessage = PowerMock.createMock(BaseMessage.class);

        mailMessage.addDeliveryReceiptAddress(EasyMock.anyObject(InternetAddress.class));
        PowerMock.expectLastCall().times(1);
        PowerMock.replayAll();

        Mail mailer = new MailImpl();
        Whitebox.setInternalState(mailer, BaseMessage.class, mailMessage);
        mailer.deliveryReceipt("delivery@frameworkdemoiselle.gov.br");

        PowerMock.verifyAll();
    }

    @Test
    public void checkReadReceipt() {
        PowerMock.mockStatic(Beans.class);
        EasyMock.expect(Beans.getReference(Config.class)).andReturn(new Config());

        BaseMessage mailMessage = PowerMock.createMock(BaseMessage.class);

        mailMessage.addReadReceiptAddress(EasyMock.anyObject(InternetAddress.class));
        PowerMock.expectLastCall().times(1);
        PowerMock.replayAll();

        Mail mailer = new MailImpl();
        Whitebox.setInternalState(mailer, BaseMessage.class, mailMessage);
        mailer.readReceipt("read@frameworkdemoiselle.gov.br");

        PowerMock.verifyAll();
    }

    @Test
    public void checkImportance() {
        PowerMock.mockStatic(Beans.class);
        EasyMock.expect(Beans.getReference(Config.class)).andReturn(new Config());

        BaseMessage mailMessage = PowerMock.createMock(BaseMessage.class);

        mailMessage.setImportance(MessagePriority.HIGH);
        PowerMock.expectLastCall().times(1);

        mailMessage.setImportance(MessagePriority.LOW);
        PowerMock.expectLastCall().times(1);

        mailMessage.setImportance(MessagePriority.NORMAL);
        PowerMock.expectLastCall().times(1);

        PowerMock.replayAll();

        Mail mailer = new MailImpl();
        Whitebox.setInternalState(mailer, BaseMessage.class, mailMessage);

        mailer.importance().high();
        mailer.importance().low();
        mailer.importance().normal();

        PowerMock.verifyAll();
    }

    @Test
    public void checkSubject() {
        PowerMock.mockStatic(Beans.class);
        EasyMock.expect(Beans.getReference(Config.class)).andReturn(new Config());

        BaseMessage mailMessage = PowerMock.createMock(BaseMessage.class);

        mailMessage.setSubject("SUBJECT");

        PowerMock.expectLastCall().times(1);
        PowerMock.replayAll();

        Mail mailer = new MailImpl();
        Whitebox.setInternalState(mailer, BaseMessage.class, mailMessage);
        mailer.subject("SUBJECT");

        PowerMock.verifyAll();
    }

    @Test
    public void checkBodyText() {
        PowerMock.mockStatic(Beans.class);
        EasyMock.expect(Beans.getReference(Config.class)).andReturn(new Config());

        BaseMessage mailMessage = PowerMock.createMock(BaseMessage.class);

        mailMessage.setTextBody("BODY TEXT");

        PowerMock.expectLastCall().times(1);
        PowerMock.replayAll();

        Mail mailer = new MailImpl();
        Whitebox.setInternalState(mailer, BaseMessage.class, mailMessage);
        mailer.body().text("BODY TEXT");

        PowerMock.verifyAll();
    }

    @Test
    public void checkBodyHtml() {
        PowerMock.mockStatic(Beans.class);
        EasyMock.expect(Beans.getReference(Config.class)).andReturn(new Config());

        BaseMessage mailMessage = PowerMock.createMock(BaseMessage.class);

        mailMessage.setHtmlBody("BODY HTML");

        PowerMock.expectLastCall().times(1);
        PowerMock.replayAll();

        Mail mailer = new MailImpl();
        Whitebox.setInternalState(mailer, BaseMessage.class, mailMessage);
        mailer.body().html("BODY HTML");

        PowerMock.verifyAll();
    }

    @Test
    public void sendHtmlEmailWithImportanceHigh() {
        try {
            PowerMock.mockStatic(Beans.class);
            PowerMock.mockStatic(LoggerProducer.class);
            EasyMock.expect(Beans.getReference(Config.class)).andReturn(new Config());
            Logger logger = PowerMock.createMock(Logger.class);
            logger.info(EasyMock.anyObject(String.class));
            EasyMock.expectLastCall().anyTimes();
            logger.log(EasyMock.anyObject(Level.class), EasyMock.anyObject(String.class));
            EasyMock.expectLastCall().anyTimes();

            EasyMock.expect(LoggerProducer.create("br.gov.frameworkdemoiselle.mail.internal.Dispatcher")).andReturn(logger);
            PowerMock.replayAll();

            new MailImpl().to("to@frameworkdemoiselle.gov.br").from("from@frameworkdemoiselle.gov.br").body()
                    .html("<b>Testing Demoiselle Mail Componente</b>").importance().high().send();

            List<Message> inbox = Mailbox.get("to@frameworkdemoiselle.gov.br");
            Message message = inbox.get(0);
            MimeMultipart multipart = (MimeMultipart) message.getContent();
            String[] priority = message.getHeader(MailHeader.PRIORITY.headerValue());
            InternetAddress ia = (InternetAddress) message.getFrom()[0];

            MimeBodyPart part = (MimeBodyPart) multipart.getBodyPart(0);
            MimeMultipart m = (MimeMultipart) part.getContent();
            Assert.assertTrue(m.getBodyPart(0).getContentType().startsWith("text/html"));
            Assert.assertEquals("from@frameworkdemoiselle.gov.br", ia.getAddress());
            Assert.assertEquals(MessagePriority.HIGH.getPriority(), priority[0]);
            Assert.assertEquals("<b>Testing Demoiselle Mail Componente</b>", (m.getBodyPart(0).getContent()));
            Assert.assertEquals(1, inbox.size());

            inbox.clear();
        } catch (AddressException e) {
            Assert.fail();
        } catch (IOException e) {
            Assert.fail();
        } catch (MessagingException e) {
            Assert.fail();
        }
    }

    @Test
    public void sendTextEmailImportanceLowBCCAndCC() {
        try {

            PowerMock.mockStatic(Beans.class);
            PowerMock.mockStatic(LoggerProducer.class);
            EasyMock.expect(Beans.getReference(Config.class)).andReturn(new Config());
            Logger logger = PowerMock.createMock(Logger.class);
            logger.log(EasyMock.anyObject(Level.class), EasyMock.anyObject(String.class));
            EasyMock.expectLastCall().anyTimes();
            logger.info(EasyMock.anyObject(String.class));
            EasyMock.expectLastCall().anyTimes();
            EasyMock.expect(LoggerProducer.create("br.gov.frameworkdemoiselle.mail.internal.Dispatcher")).andReturn(logger);

            PowerMock.replayAll();

            new MailImpl().to("to@frameworkdemoiselle.gov.br").from("from@frameworkdemoiselle.gov.br")
                    .bcc("bcc@frameworkdemoiselle.gov.br").cc("cc@frameworkdemoiselle.gov.br").body()
                    .text("<b>Testing Demoiselle Mail Componente</b>").importance().low().subject("Subject").send();

            List<Message> inbox = Mailbox.get("to@frameworkdemoiselle.gov.br");
            Message message = inbox.get(0);
            MimeMultipart multipart = (MimeMultipart) message.getContent();
            String[] priority = message.getHeader(MailHeader.PRIORITY.headerValue());
            InternetAddress internetAddress = (InternetAddress) message.getFrom()[0];

            InternetAddress bcc = (InternetAddress) message.getRecipients(RecipientType.BCC.getRecipientType())[0];
            InternetAddress cc = (InternetAddress) message.getRecipients(RecipientType.CC.getRecipientType())[0];

            Assert.assertTrue(multipart.getBodyPart(0).getContentType().startsWith("text/plain"));
            Assert.assertEquals("Subject", message.getSubject());
            Assert.assertEquals("bcc@frameworkdemoiselle.gov.br", bcc.getAddress());
            Assert.assertEquals("cc@frameworkdemoiselle.gov.br", cc.getAddress());
            Assert.assertEquals("from@frameworkdemoiselle.gov.br", internetAddress.getAddress());
            Assert.assertEquals(MessagePriority.LOW.getPriority(), priority[0]);
            Assert.assertEquals("<b>Testing Demoiselle Mail Componente</b>", (multipart.getBodyPart(0).getContent()));
            Assert.assertEquals(1, inbox.size());

            inbox.clear();
        } catch (AddressException e) {
            Assert.fail();
        } catch (IOException e) {
            Assert.fail();
        } catch (MessagingException e) {
            Assert.fail();
        }

    }

    @Test
    public void sendTextEmailWithAttachment() {
        try {

            PowerMock.mockStatic(Beans.class);
            PowerMock.mockStatic(LoggerProducer.class);
            EasyMock.expect(Beans.getReference(Config.class)).andReturn(new Config());
            Logger logger = PowerMock.createMock(Logger.class);
            logger.log(EasyMock.anyObject(Level.class), EasyMock.anyObject(String.class));
            EasyMock.expectLastCall().anyTimes();
            logger.info(EasyMock.anyObject(String.class));
            EasyMock.expectLastCall().anyTimes();
            EasyMock.expect(LoggerProducer.create("br.gov.frameworkdemoiselle.mail.internal.Dispatcher")).andReturn(logger);

            PowerMock.replayAll();

            new MailImpl().to("to@frameworkdemoiselle.gov.br").from("from@frameworkdemoiselle.gov.br").body()
                    .text("Testing Demoiselle Mail Componente").attach()
                    .url("https://upload.wikimedia.org/wikipedia/commons/thumb/f/f7/Alberto_Santos-Dumont_with_Demoiselle.jpg/800px-Alberto_Santos-Dumont_with_Demoiselle.jpg", "logo.jpg")
                    .inline().subject("Subject").send();

            List<Message> inbox = Mailbox.get("to@frameworkdemoiselle.gov.br");
            Message message = inbox.get(0);
            MimeMultipart multipart = (MimeMultipart) message.getContent();
            InternetAddress internetAddress = (InternetAddress) message.getFrom()[0];

            Assert.assertTrue(multipart.getBodyPart(0).getContentType().startsWith("image/jpeg"));
            Assert.assertTrue(multipart.getBodyPart(1).getContentType().startsWith("text/plain"));
            Assert.assertEquals("Subject", message.getSubject());
            Assert.assertEquals("from@frameworkdemoiselle.gov.br", internetAddress.getAddress());
            Assert.assertEquals("Testing Demoiselle Mail Componente", (multipart.getBodyPart(1).getContent()));
            Assert.assertEquals(1, inbox.size());

            inbox.clear();
        } catch (AddressException e) {
            Assert.fail();
        } catch (IOException e) {
            Assert.fail();
        } catch (MessagingException e) {
            Assert.fail();
        }

    }
}