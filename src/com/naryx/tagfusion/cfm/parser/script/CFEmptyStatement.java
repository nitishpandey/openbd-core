/* 
 *  Copyright (C) 2000 - 2010 TagServlet Ltd
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
package com.naryx.tagfusion.cfm.parser.script;

/*
 * A completely empty statement (";") which does nothing.
 */

import org.antlr.runtime.Token;

import com.naryx.tagfusion.cfm.parser.CFContext;

public class CFEmptyStatement extends CFParsedStatement implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	public CFEmptyStatement( Token t ) {
		super(t);
	}

	public CFEmptyStatement() {
		super(0, 0);
	}

	public CFStatementResult Exec( CFContext context ) {
		setLineCol(context);
		return null;
		// do nothing
	}

	public String Decompile( int indent ) {
		return Indent(indent) + ";";
	}

}
