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
public final class Backport {
  /** Character-Set ISO-8859-1, a.k.a. Latin-1 */
  public static final Charset ISO_8859_1 = Charset.forName("ISO-8859-1");
  /** Character-Set UTF-8 */
  public static final Charset UTF_8      = Charset.forName("UTF-8");

  private Backport() {
    // Static methods holder class.
  }

  /**
   * Replacement for Arrays.copyOf().
   *
   * @param source array to copy from.
   * @param length number of elements to copy.
   * @return array with <code>length</code> elements from <code>source</code>.
   */
  public static int[] copyOf(int[] source, int length) {
    int[] target = new int[length];
    System.arraycopy(source, 0, target, 0, Math.min(source.length, length));
    return target;
  }

  /**
   * Replacement for Arrays.copyOfRange().
   *
   * @param source array to copy from.
   * @param from first element to copy (inclusive).
   * @param upto last element to copy (exclusive).
   * @return array with subset elements from <code>source</code>.
   */
  public static int[] copyOfRange(int[] source, int from, int upto) {
    int[] target = new int[upto - from];
    System.arraycopy(source, from, target, 0, upto - from);
    return target;
  }

  /**
   * Replacement for String.getBytes(Charset).
   *
   * @param contents String to get content from.
   * @param charset Character-Set to encode the content in.
   * @return String content encoded in the specified character-set.
   */
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

  /**
   * Replacement for new String(byte[], Charset).
   *
   * @param content Byte Content for the new string.
   * @param charset Character-Set for the Byte Content.
   * @return New string with the given content in the specified character-set.
   */
  public static String getString(byte[] content, Charset charset) {
    if (content == null) {
      throw new NullPointerException("getString(null, ...)");
    }
    if (charset == null) {
      throw new NullPointerException("getString(..., null)");
    }
    return charset.decode(ByteBuffer.wrap(content)).toString();
  }

  /**
   * Replacement for String.isEmpty().
   *
   * @param contents String to check.
   * @return <code>true</code> if empty, otherwise <code>false</code>
   */
  public static boolean isEmpty(String contents) {
    if (contents == null) {
      throw new NullPointerException("isEmpty(null)");
    }
    return contents.length() == 0;
  }

  /**
   * Replacement for Integer.compare(int, int).
   *
   * @param a First integer.
   * @param b Second integer.
   * @return Comparison result: 0 if equal, -1 if a &lt; b, +1 if a &gt; b.
   */
  public static int compare(int a, int b) {
    if (a < b) {
      return -1;
    }
    if (a > b) {
      return +1;
    }
    return 0;
  }

}
