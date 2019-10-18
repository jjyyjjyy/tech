package me.jy.lang;

/**
 * Classfile /Users/jy/IdeaProjects/tech/basic-knowledge/target/classes/me/jy/lang/HelloWorld.class
 * Last modified 2019-10-5; size 555 bytes
 * MD5 checksum f7f312d08e60cc2b1a92d6ec9b7045be
 * Compiled from "HelloWorld.java"
 * public class me.jy.lang.HelloWorld
 * minor version: 0
 * major version: 52
 * flags: ACC_PUBLIC, ACC_SUPER
 * Constant pool:
 * #1 = Methodref          #6.#20         // java/lang/Object."<init>":()V
 * #2 = Fieldref           #21.#22        // java/lang/System.out:Ljava/io/PrintStream;
 * #3 = String             #23            // Hello World
 * #4 = Methodref          #24.#25        // java/io/PrintStream.println:(Ljava/lang/String;)V
 * #5 = Class              #26            // me/jy/lang/HelloWorld
 * #6 = Class              #27            // java/lang/Object
 * #7 = Utf8               <init>
 * #8 = Utf8               ()V
 * #9 = Utf8               Code
 * #10 = Utf8               LineNumberTable
 * #11 = Utf8               LocalVariableTable
 * #12 = Utf8               this
 * #13 = Utf8               Lme/jy/lang/HelloWorld;
 * #14 = Utf8               main
 * #15 = Utf8               ([Ljava/lang/String;)V
 * #16 = Utf8               args
 * #17 = Utf8               [Ljava/lang/String;
 * #18 = Utf8               SourceFile
 * #19 = Utf8               HelloWorld.java
 * #20 = NameAndType        #7:#8          // "<init>":()V
 * #21 = Class              #28            // java/lang/System
 * #22 = NameAndType        #29:#30        // out:Ljava/io/PrintStream;
 * #23 = Utf8               Hello World
 * #24 = Class              #31            // java/io/PrintStream
 * #25 = NameAndType        #32:#33        // println:(Ljava/lang/String;)V
 * #26 = Utf8               me/jy/lang/HelloWorld
 * #27 = Utf8               java/lang/Object
 * #28 = Utf8               java/lang/System
 * #29 = Utf8               out
 * #30 = Utf8               Ljava/io/PrintStream;
 * #31 = Utf8               java/io/PrintStream
 * #32 = Utf8               println
 * #33 = Utf8               (Ljava/lang/String;)V
 * {
 * public me.jy.lang.HelloWorld();
 * descriptor: ()V
 * flags: ACC_PUBLIC
 * Code:
 * stack=1, locals=1, args_size=1
 * 0: aload_0
 * 1: invokespecial #1                  // Method java/lang/Object."<init>":()V
 * 4: return
 * LineNumberTable:
 * line 23: 0
 * LocalVariableTable:
 * Start  Length  Slot  Name   Signature
 * 0       5     0  this   Lme/jy/lang/HelloWorld;
 * <p>
 * public static void main(java.lang.String[]);
 * descriptor: ([Ljava/lang/String;)V
 * flags: ACC_PUBLIC, ACC_STATIC
 * Code:
 * stack=2, locals=1, args_size=1
 * 0: getstatic     #2                  // Field java/lang/System.out:Ljava/io/PrintStream;
 * 3: ldc           #3                  // String Hello World
 * 5: invokevirtual #4                  // Method java/io/PrintStream.println:(Ljava/lang/String;)V
 * 8: return
 * LineNumberTable:
 * line 26: 0
 * line 27: 8
 * LocalVariableTable:
 * Start  Length  Slot  Name   Signature
 * 0       9     0  args   [Ljava/lang/String;
 * }
 * <p>
 * 00000000: cafe babe 0000 0034 0022 0a00 0600 1409  .......4."......
 * 00000010: 0015 0016 0800 170a 0018 0019 0700 1a07  ................
 * 00000020: 001b 0100 063c 696e 6974 3e01 0003 2829  .....<init>...()
 * 00000030: 5601 0004 436f 6465 0100 0f4c 696e 654e  V...Code...LineN
 * 00000040: 756d 6265 7254 6162 6c65 0100 124c 6f63  umberTable...Loc
 * 00000050: 616c 5661 7269 6162 6c65 5461 626c 6501  alVariableTable.
 * 00000060: 0004 7468 6973 0100 174c 6d65 2f6a 792f  ..this...Lme/jy/
 * 00000070: 6c61 6e67 2f48 656c 6c6f 576f 726c 643b  lang/HelloWorld;
 * 00000080: 0100 046d 6169 6e01 0016 285b 4c6a 6176  ...main...([Ljav
 * 00000090: 612f 6c61 6e67 2f53 7472 696e 673b 2956  a/lang/String;)V
 * 000000a0: 0100 0461 7267 7301 0013 5b4c 6a61 7661  ...args...[Ljava
 * 000000b0: 2f6c 616e 672f 5374 7269 6e67 3b01 000a  /lang/String;...
 * 000000c0: 536f 7572 6365 4669 6c65 0100 0f48 656c  SourceFile...Hel
 * 000000d0: 6c6f 576f 726c 642e 6a61 7661 0c00 0700  loWorld.java....
 * 000000e0: 0807 001c 0c00 1d00 1e01 000b 4865 6c6c  ............Hell
 * 000000f0: 6f20 576f 726c 6407 001f 0c00 2000 2101  o World..... .!.
 * 00000100: 0015 6d65 2f6a 792f 6c61 6e67 2f48 656c  ..me/jy/lang/Hel
 * 00000110: 6c6f 576f 726c 6401 0010 6a61 7661 2f6c  loWorld...java/l
 * 00000120: 616e 672f 4f62 6a65 6374 0100 106a 6176  ang/Object...jav
 * 00000130: 612f 6c61 6e67 2f53 7973 7465 6d01 0003  a/lang/System...
 * 00000140: 6f75 7401 0015 4c6a 6176 612f 696f 2f50  out...Ljava/io/P
 * 00000150: 7269 6e74 5374 7265 616d 3b01 0013 6a61  rintStream;...ja
 * 00000160: 7661 2f69 6f2f 5072 696e 7453 7472 6561  va/io/PrintStrea
 * 00000170: 6d01 0007 7072 696e 746c 6e01 0015 284c  m...println...(L
 * 00000180: 6a61 7661 2f6c 616e 672f 5374 7269 6e67  java/lang/String
 * 00000190: 3b29 5600 2100 0500 0600 0000 0000 0200  ;)V.!...........
 * 000001a0: 0100 0700 0800 0100 0900 0000 2f00 0100  ............/...
 * 000001b0: 0100 0000 052a b700 01b1 0000 0002 000a  .....*..........
 * 000001c0: 0000 0006 0001 0000 0017 000b 0000 000c  ................
 * 000001d0: 0001 0000 0005 000c 000d 0000 0009 000e  ................
 * 000001e0: 000f 0001 0009 0000 0037 0002 0001 0000  .........7......
 * 000001f0: 0009 b200 0212 03b6 0004 b100 0000 0200  ................
 * 00000200: 0a00 0000 0a00 0200 0000 1a00 0800 1b00  ................
 * 00000210: 0b00 0000 0c00 0100 0000 0900 1000 1100  ................
 * 00000220: 0000 0100 1200 0000 0200 13              ...........
 *
 * @author jy
 */
public class HelloWorld {

    public static void main(String[] args) {
        System.out.println("Hello World");
    }
}
