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

package com.naryx.tagfusion.expression.function.array;

import com.naryx.tagfusion.cfm.engine.cfArgStructData;
import com.naryx.tagfusion.cfm.engine.cfArrayData;
import com.naryx.tagfusion.cfm.engine.cfData;
import com.naryx.tagfusion.cfm.engine.cfNumberData;
import com.naryx.tagfusion.cfm.engine.cfSession;
import com.naryx.tagfusion.cfm.engine.cfmRunTimeException;
import com.naryx.tagfusion.expression.function.functionBase;

public class arrayValueCountNoCase extends functionBase {
	private static final long serialVersionUID = 1L;

	public arrayValueCountNoCase() {
		min = max = 2;
		setNamedParams( new String[]{ "array", "element" } );
	}

	public String[] getParamInfo(){
		return new String[]{
			"Array Object",
			"search element"
		};
	}
	
	public java.util.Map getInfo(){
		return makeInfo(
				"array", 
				"Returns the number of times the search element occurs in the array, or return 0 if not found (case in-sensitive matching)", 
				ReturnType.NUMERIC );
	}
	
	public cfData execute(cfSession _session, cfArgStructData argStruct) throws cfmRunTimeException {

		cfData data = getNamedParam(argStruct, "array");
		cfData search = getNamedParam(argStruct, "element");

		if (data.getDataType() == cfData.CFARRAYDATA) {
			cfArrayData	array	= (cfArrayData)data;
			
			int total = 0;
			for ( int x=1; x <= array.size(); x++ ){
				cfData	element	= array.getElement( x ); 

				if ( cfData.isSimpleValue(element) && cfData.isSimpleValue(search) ){  
					if ( element.getDataType() == cfData.CFSTRINGDATA && search.getDataType() == cfData.CFSTRINGDATA ){
						if ( element.getString().equalsIgnoreCase( search.getString() ) )
							total++;
					}else if ( element.equals(search) ){
						total++;	
					}
				}else if ( element.getDataType() == search.getDataType() && element.equals(search) )
					total++;
			}
			
			return new cfNumberData( total );
		} else
			throwException(_session, "the parameter is not an Array");

		return null;
	}
	
}