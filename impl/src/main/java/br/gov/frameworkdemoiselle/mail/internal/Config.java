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

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

import br.gov.frameworkdemoiselle.configuration.Configuration;
import br.gov.frameworkdemoiselle.mail.internal.enums.MailType;

@Configuration(prefix = "frameworkdemoiselle.mail.")
public class Config {

	private String mailLookupClass;

	private String type;

	private String user;

	private String password;

	private String serverHost = "localhost";

	private int serverPort = 25;

	private String domainName;

	private boolean enableTls = false;

	private boolean requireTls = false;

	private boolean enableSsl = false;

	private boolean auth = false;

	public String getMailLookupClass() {
		return mailLookupClass;
	}

	public MailType getType() {
		if (type == null) {
			return null;
		} else {
			return MailType.valueOf(type.toUpperCase());
		}
	}

	public Authenticator getAuthenticator() {
		if (user == null || password == null) {
			return null;
		} else {
			final PasswordAuthentication pa = new PasswordAuthentication(user, password);
			return new Authenticator() {

				protected PasswordAuthentication getPasswordAuthentication() {
					return pa;
				}
			};
		}
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getServerHost() {
		return serverHost;
	}

	public void setServerHost(String serverHost) {
		this.serverHost = serverHost;
	}

	public int getServerPort() {
		return serverPort;
	}

	public void setServerPort(int serverPort) {
		this.serverPort = serverPort;
	}

	public String getDomainName() {
		return domainName;
	}

	public void setDomainName(String domainName) {
		this.domainName = domainName;
	}

	public boolean isEnableTls() {
		return enableTls;
	}

	public void setEnableTls(boolean enableTls) {
		this.enableTls = enableTls;
	}

	public boolean isRequireTls() {
		return requireTls;
	}

	public void setRequireTls(boolean requireTls) {
		this.requireTls = requireTls;
	}

	public boolean isEnableSsl() {
		return enableSsl;
	}

	public void setEnableSsl(boolean enableSsl) {
		this.enableSsl = enableSsl;
	}

	public boolean isAuth() {
		return auth;
	}

	public void setAuth(boolean auth) {
		this.auth = auth;
	}

	public void setMailLookupClass(String mailLookupClass) {
		this.mailLookupClass = mailLookupClass;
	}

	public void setType(String type) {
		this.type = type;
	}

}
