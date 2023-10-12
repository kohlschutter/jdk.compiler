/*
 * Copyright (c) 2015, 2018, Oracle and/or its affiliates. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License version 2 only, as
 * published by the Free Software Foundation.  Oracle designates this
 * particular file as subject to the "Classpath" exception as provided
 * by Oracle in the LICENSE file that accompanied this code.
 *
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License
 * version 2 for more details (a copy is included in the LICENSE file that
 * accompanied this code).
 *
 * You should have received a copy of the GNU General Public License version
 * 2 along with this work; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Please contact Oracle, 500 Oracle Parkway, Redwood Shores, CA 94065 USA
 * or visit www.oracle.com if you need additional information or have any
 * questions.
 */

package standalone.com.sun.tools.javac.util;

public class ModuleHelper {

    private static final String[] javacInternalPackages = new String[] {
            "standalone.com.sun.tools.javac.api",
            "standalone.com.sun.tools.javac.code",
            "standalone.com.sun.tools.javac.comp",
            "standalone.com.sun.tools.javac.file",
            "standalone.com.sun.tools.javac.jvm",
            "standalone.com.sun.tools.javac.main",
            "standalone.com.sun.tools.javac.model",
            "standalone.com.sun.tools.javac.parser",
            "standalone.com.sun.tools.javac.platform",
            "standalone.com.sun.tools.javac.processing",
            "standalone.com.sun.tools.javac.tree",
            "standalone.com.sun.tools.javac.util",

            "standalone.com.sun.tools.doclint",
    };

    public static void addExports(Module from, Module to) {
        for (String pack: javacInternalPackages) {
            from.addExports(pack, to);
        }
    }
}

