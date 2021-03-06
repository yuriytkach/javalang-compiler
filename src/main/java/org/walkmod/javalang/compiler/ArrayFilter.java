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
package org.walkmod.javalang.compiler;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class ArrayFilter<T> {

    private T[] elements;

    private List<Predicate<T>> predicates;

    public ArrayFilter(T[] elements) {
        this.elements = elements;
        predicates = new LinkedList<Predicate<T>>();

    }

    public List<Predicate<T>> getPredicates() {
        return predicates;
    }

    public ArrayFilter<T> appendPredicate(Predicate<T> predicate) {
        predicates.add(predicate);
        return this;
    }

    public void setElements(T[] elements) {
        this.elements = elements;
    }

    public List<T> filter() throws Exception {
        List<T> result = new LinkedList<T>();
        if (elements != null) {
            for (T elem : elements) {
                boolean filtered = true;
                Iterator<Predicate<T>> it = predicates.iterator();
                while (it.hasNext() && filtered) {
                    Predicate<T> pred = it.next();
                    if (pred.filter(elem)) {
                        result.add(elem);

                    } else {
                        filtered = false;
                    }
                }

            }
        }
        return result;
    }

    public T filterOne() throws Exception {
        T result = null;
        if (elements != null) {
            for (int i = 0; i < elements.length && result == null; i++) {
                boolean filtered = true;
                Iterator<Predicate<T>> it = predicates.iterator();
                while (it.hasNext() && filtered) {
                    Predicate<T> pred = it.next();
                    filtered = pred.filter(elements[i]);
                }
                if (filtered) {
                    result = elements[i];
                }
            }
        }
        return result;
    }
}
