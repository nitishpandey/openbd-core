/* 
 *  Copyright (C) 2000 - 2008 TagServlet Ltd
 *
 *  This file is part of Open BlueDragon (OpenBD) CFML Server Engine.
 *  
 *  OpenBD is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  Free Software Foundation,version 3.
 *  
 *  OpenBD is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *  
 *  You should have received a copy of the GNU General Public License
 *  along with OpenBD.  If not, see http://www.gnu.org/licenses/
 *  
 *  Additional permission under GNU GPL version 3 section 7
 *  
 *  If you modify this Program, or any covered work, by linking or combining 
 *  it with any of the JARS listed in the README.txt (or a modified version of 
 *  (that library), containing parts covered by the terms of that JAR, the 
 *  licensors of this Program grant you additional permission to convey the 
 *  resulting work. 
 *  README.txt @ http://www.openbluedragon.org/license/README.txt
 *  
 *  http://www.openbluedragon.org/
 */

package com.naryx.tagfusion.expression.function.xml;

import java.util.List;

import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import com.naryx.tagfusion.cfm.engine.catchDataFactory;
import com.naryx.tagfusion.cfm.engine.cfData;
import com.naryx.tagfusion.cfm.engine.cfSession;
import com.naryx.tagfusion.cfm.engine.cfmRunTimeException;
import com.naryx.tagfusion.cfm.xml.cfXmlData;
import com.naryx.tagfusion.expression.function.functionBase;

public class XmlElemNew extends functionBase {
	private static final long serialVersionUID = 1L;

	public XmlElemNew() {
		min = 2;
		max = 3;
	}

  public String[] getParamInfo(){
		return new String[]{
			"xml object",
			"name",
			"namespace"
		};
	}
	
	public java.util.Map getInfo(){
		return makeInfo(
				"xml", 
				"Creates a new child element for the XML object", 
				ReturnType.XML );
	}

	
	public cfData execute(cfSession _session, List<cfData> parameters) throws cfmRunTimeException {
		String name = parameters.get(0).getString().trim();
		String namespace = null;
		cfData d = parameters.get(1);
		if (parameters.size() == 3) {
			namespace = d.getString().trim();
			d = parameters.get(2);
		}

		try {
			Element e = null;
			Document doc = null;
			if (!(d instanceof cfXmlData))
				throwException(_session, "XmlObj parameter isn't of type XML");
			Node node = ((cfXmlData) d).getXMLNode();
			if (node.getNodeType() == Node.DOCUMENT_NODE)
				doc = (Document) node;
			else
				doc = node.getOwnerDocument();
			if (namespace != null)
				e = doc.createElementNS(namespace, name);
			else
				e = doc.createElement(name);
			return new cfXmlData(e, ((cfXmlData) d).isCaseSensitive());
		} catch (DOMException ex) {
			throw new cfmRunTimeException(catchDataFactory.javaMethodException("errorCode.javaException", ex.getClass().getName(), ex.getMessage(), ex));
		}
	}
}
