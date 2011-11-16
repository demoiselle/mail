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
package br.gov.frameworkdemoiselle.mail.internal.implementation;

import java.util.ArrayList;
import java.util.Collection;

import br.gov.frameworkdemoiselle.mail.internal.Attachment;
import br.gov.frameworkdemoiselle.mail.internal.Header;
import br.gov.frameworkdemoiselle.mail.internal.enums.ContentDisposition;

public class BaseAttachment implements Attachment {
    private String fileName;
    private String mimeType;
    private ContentDisposition contentDisposition;
    private Collection<Header> headers = new ArrayList<Header>();
    private byte[] bytes;

    public BaseAttachment(String fileName, ContentDisposition contentDisposition, byte[] bytes) {
        this();
        this.fileName = fileName;
        this.contentDisposition = contentDisposition;
        this.bytes = bytes;
    }

    public BaseAttachment(String fileName, ContentDisposition contentDisposition, byte[] bytes, String contentClass) {
        this(fileName, contentDisposition, bytes);
        this.addHeader(new Header("Content-Class", contentClass));
    }

    public BaseAttachment() {
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getMimeType() {
        return mimeType;
    }

    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }

    public ContentDisposition getContentDisposition() {
        return contentDisposition;
    }

    public void setContentDisposition(ContentDisposition contentDisposition) {
        this.contentDisposition = contentDisposition;
    }

    public Collection<Header> getHeaders() {
        return headers;
    }

    public void addHeader(Header header) {
        headers.add(header);
    }

    public void addHeaders(Collection<Header> headers) {
        headers.addAll(headers);
    }

    public byte[] getBytes() {
        return bytes;
    }

    public void setBytes(byte[] bytes) {
        this.bytes = bytes;
    }
}
