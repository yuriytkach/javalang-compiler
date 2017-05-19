/*
 * Copyright (C) 2015 Raquel Pau and Albert Coroleu.
 * 
 * Walkmod is free software: you can redistribute it and/or modify it under the terms of the GNU
 * Lesser General Public License as published by the Free Software Foundation, either version 3 of
 * the License, or (at your option) any later version.
 * 
 * Walkmod is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even
 * the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser
 * General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public License along with Walkmod. If
 * not, see <http://www.gnu.org/licenses/>.
 */
package org.walkmod.javalang.compiler.reflection;

import java.util.List;

import org.walkmod.javalang.ast.SymbolDataAware;
import org.walkmod.javalang.compiler.Builder;

public class ClassArrayFromSymTypeListBuilder<T extends SymbolDataAware<?>> implements Builder<Class<?>[]> {

    private List<T> members;

    public ClassArrayFromSymTypeListBuilder(List<T> members) {
        this.members = members;
    }

    @Override
    public Class<?>[] build(Class<?>[] obj) throws Exception {
        if (members != null && obj != null) {
            if (members.size() == obj.length) {
                int i = 0;
                for (T member : members) {
                    obj[i] = member.getSymbolData().getClazz();
                    i++;
                }
            }
        }
        return obj;
    }

}
