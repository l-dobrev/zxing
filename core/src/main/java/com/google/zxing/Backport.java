/*
 * Copyright 2007 ZXing authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.google.zxing;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;

/**
 * Utility class containing back-ported constructions.<br>
 * This class is used to implement syntactic sugar and utility methods found in
 * newer Java versions, but lacking in the (older) Android world.<br>
 * These methods will throw {@link NullPointerException} when a <code>null</code>
 * arguments are used. This is on-par with the JVM crashing when calling a method
 * on a <code>null</code> reference.
 * 
 * @author Lachezar Dobrev
 */
public class Backport {
  private Backport() {
    // Static methods holder class.
  }

  /** Replacement for Arrays.copyOf(). */
  public static int[] copyOf(int[] source, int length) {
    int[] target = new int[length];
    System.arraycopy(source, 0, target, 0, Math.min(source.length, length));
    return target;
  }

  /** Replacement for String.getBytes(Charset). */
  public static byte[] getBytes(String contents, Charset charset) {
    if (contents == null) {
      throw new NullPointerException("getBytes(null, ...)");
    }
    if (charset == null) {
      throw new NullPointerException("getBytes(..., null)");
    }
    ByteBuffer output;
    output = charset.encode(contents);

    byte[] ba;
    ba = new byte[output.remaining()];

    output.get(ba);

    return ba;
  }

  /** Replacement for new String(byte[], Charset). */
  public static String getString(byte[] content, Charset charset) {
    if (content == null)
      throw new NullPointerException("getString(null, ...)");
    if (charset == null)
      throw new NullPointerException("getString(..., null)");
    return charset.decode(ByteBuffer.wrap(content)).toString();
  }

  /** Replacement for String.isEmpty(). */
  public static boolean isEmpty(String contents) {
    if (contents == null) {
      throw new NullPointerException("isEmpty(null)");
    }
    return contents.length() == 0;
  }

}