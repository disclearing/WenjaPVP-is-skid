package org.hqpots.bolt.utils;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class Pinger
{
  /* Error */
  public static int[] ping(String host, int port)
    throws IOException
  {
	  return null;
    // Byte code:
    //   0: new 2	java/net/Socket
    //   3: dup
    //   4: aload_0
    //   5: iload_1
    //   6: invokespecial 3	java/net/Socket:<init>	(Ljava/lang/String;I)V
    //   9: astore_2
    //   10: aconst_null
    //   11: astore_3
    //   12: new 4	java/io/DataOutputStream
    //   15: dup
    //   16: aload_2
    //   17: invokevirtual 5	java/net/Socket:getOutputStream	()Ljava/io/OutputStream;
    //   20: invokespecial 6	java/io/DataOutputStream:<init>	(Ljava/io/OutputStream;)V
    //   23: astore 4
    //   25: aconst_null
    //   26: astore 5
    //   28: new 7	java/io/DataInputStream
    //   31: dup
    //   32: aload_2
    //   33: invokevirtual 8	java/net/Socket:getInputStream	()Ljava/io/InputStream;
    //   36: invokespecial 9	java/io/DataInputStream:<init>	(Ljava/io/InputStream;)V
    //   39: astore 6
    //   41: aconst_null
    //   42: astore 7
    //   44: new 10	java/io/ByteArrayOutputStream
    //   47: dup
    //   48: invokespecial 11	java/io/ByteArrayOutputStream:<init>	()V
    //   51: astore 8
    //   53: aconst_null
    //   54: astore 9
    //   56: new 4	java/io/DataOutputStream
    //   59: dup
    //   60: aload 8
    //   62: invokespecial 6	java/io/DataOutputStream:<init>	(Ljava/io/OutputStream;)V
    //   65: astore 10
    //   67: aconst_null
    //   68: astore 11
    //   70: iconst_0
    //   71: aload 10
    //   73: invokestatic 12	net/flowz/lobby/Pinger:writeVarInt	(ILjava/io/DataOutput;)V
    //   76: iconst_4
    //   77: aload 10
    //   79: invokestatic 12	net/flowz/lobby/Pinger:writeVarInt	(ILjava/io/DataOutput;)V
    //   82: aload_0
    //   83: aload 10
    //   85: invokestatic 13	net/flowz/lobby/Pinger:writeString	(Ljava/lang/String;Ljava/io/DataOutput;)V
    //   88: aload 10
    //   90: iload_1
    //   91: invokevirtual 14	java/io/DataOutputStream:writeShort	(I)V
    //   94: iconst_1
    //   95: aload 10
    //   97: invokestatic 12	net/flowz/lobby/Pinger:writeVarInt	(ILjava/io/DataOutput;)V
    //   100: aload 8
    //   102: invokevirtual 15	java/io/ByteArrayOutputStream:size	()I
    //   105: aload 4
    //   107: invokestatic 12	net/flowz/lobby/Pinger:writeVarInt	(ILjava/io/DataOutput;)V
    //   110: aload 8
    //   112: aload 4
    //   114: invokevirtual 16	java/io/ByteArrayOutputStream:writeTo	(Ljava/io/OutputStream;)V
    //   117: aload 8
    //   119: invokevirtual 17	java/io/ByteArrayOutputStream:reset	()V
    //   122: iconst_0
    //   123: aload 10
    //   125: invokestatic 12	net/flowz/lobby/Pinger:writeVarInt	(ILjava/io/DataOutput;)V
    //   128: aload 8
    //   130: invokevirtual 15	java/io/ByteArrayOutputStream:size	()I
    //   133: aload 4
    //   135: invokestatic 12	net/flowz/lobby/Pinger:writeVarInt	(ILjava/io/DataOutput;)V
    //   138: aload 8
    //   140: aload 4
    //   142: invokevirtual 16	java/io/ByteArrayOutputStream:writeTo	(Ljava/io/OutputStream;)V
    //   145: aload 8
    //   147: invokevirtual 17	java/io/ByteArrayOutputStream:reset	()V
    //   150: aload 6
    //   152: invokestatic 18	net/flowz/lobby/Pinger:readVarInt	(Ljava/io/DataInput;)I
    //   155: istore 12
    //   157: iload 12
    //   159: newarray <illegal type>
    //   161: astore 13
    //   163: aload 6
    //   165: aload 13
    //   167: invokevirtual 19	java/io/DataInputStream:readFully	([B)V
    //   170: new 20	java/io/ByteArrayInputStream
    //   173: dup
    //   174: aload 13
    //   176: invokespecial 21	java/io/ByteArrayInputStream:<init>	([B)V
    //   179: astore 14
    //   181: aconst_null
    //   182: astore 15
    //   184: new 7	java/io/DataInputStream
    //   187: dup
    //   188: aload 14
    //   190: invokespecial 9	java/io/DataInputStream:<init>	(Ljava/io/InputStream;)V
    //   193: astore 16
    //   195: aconst_null
    //   196: astore 17
    //   198: aload 16
    //   200: invokestatic 18	net/flowz/lobby/Pinger:readVarInt	(Ljava/io/DataInput;)I
    //   203: istore 18
    //   205: iload 18
    //   207: ifeq +13 -> 220
    //   210: new 22	java/lang/IllegalStateException
    //   213: dup
    //   214: ldc 23
    //   216: invokespecial 24	java/lang/IllegalStateException:<init>	(Ljava/lang/String;)V
    //   219: athrow
    //   220: new 25	net/minecraft/util/com/google/gson/JsonParser
    //   223: dup
    //   224: invokespecial 26	net/minecraft/util/com/google/gson/JsonParser:<init>	()V
    //   227: astore 19
    //   229: aload 19
    //   231: aload 16
    //   233: invokestatic 27	net/flowz/lobby/Pinger:readString	(Ljava/io/DataInput;)Ljava/lang/String;
    //   236: invokevirtual 28	net/minecraft/util/com/google/gson/JsonParser:parse	(Ljava/lang/String;)Lnet/minecraft/util/com/google/gson/JsonElement;
    //   239: checkcast 29	net/minecraft/util/com/google/gson/JsonObject
    //   242: astore 20
    //   244: aload 20
    //   246: ldc 30
    //   248: invokevirtual 31	net/minecraft/util/com/google/gson/JsonObject:get	(Ljava/lang/String;)Lnet/minecraft/util/com/google/gson/JsonElement;
    //   251: checkcast 29	net/minecraft/util/com/google/gson/JsonObject
    //   254: astore 21
    //   256: iconst_2
    //   257: newarray <illegal type>
    //   259: dup
    //   260: iconst_0
    //   261: aload 21
    //   263: ldc 32
    //   265: invokevirtual 31	net/minecraft/util/com/google/gson/JsonObject:get	(Ljava/lang/String;)Lnet/minecraft/util/com/google/gson/JsonElement;
    //   268: invokevirtual 33	net/minecraft/util/com/google/gson/JsonElement:getAsInt	()I
    //   271: iastore
    //   272: dup
    //   273: iconst_1
    //   274: aload 21
    //   276: ldc 34
    //   278: invokevirtual 31	net/minecraft/util/com/google/gson/JsonObject:get	(Ljava/lang/String;)Lnet/minecraft/util/com/google/gson/JsonElement;
    //   281: invokevirtual 33	net/minecraft/util/com/google/gson/JsonElement:getAsInt	()I
    //   284: iastore
    //   285: astore 22
    //   287: aload 16
    //   289: ifnull +33 -> 322
    //   292: aload 17
    //   294: ifnull +23 -> 317
    //   297: aload 16
    //   299: invokevirtual 35	java/io/DataInputStream:close	()V
    //   302: goto +20 -> 322
    //   305: astore 23
    //   307: aload 17
    //   309: aload 23
    //   311: invokevirtual 37	java/lang/Throwable:addSuppressed	(Ljava/lang/Throwable;)V
    //   314: goto +8 -> 322
    //   317: aload 16
    //   319: invokevirtual 35	java/io/DataInputStream:close	()V
    //   322: aload 14
    //   324: ifnull +33 -> 357
    //   327: aload 15
    //   329: ifnull +23 -> 352
    //   332: aload 14
    //   334: invokevirtual 38	java/io/ByteArrayInputStream:close	()V
    //   337: goto +20 -> 357
    //   340: astore 23
    //   342: aload 15
    //   344: aload 23
    //   346: invokevirtual 37	java/lang/Throwable:addSuppressed	(Ljava/lang/Throwable;)V
    //   349: goto +8 -> 357
    //   352: aload 14
    //   354: invokevirtual 38	java/io/ByteArrayInputStream:close	()V
    //   357: aload 10
    //   359: ifnull +33 -> 392
    //   362: aload 11
    //   364: ifnull +23 -> 387
    //   367: aload 10
    //   369: invokevirtual 39	java/io/DataOutputStream:close	()V
    //   372: goto +20 -> 392
    //   375: astore 23
    //   377: aload 11
    //   379: aload 23
    //   381: invokevirtual 37	java/lang/Throwable:addSuppressed	(Ljava/lang/Throwable;)V
    //   384: goto +8 -> 392
    //   387: aload 10
    //   389: invokevirtual 39	java/io/DataOutputStream:close	()V
    //   392: aload 8
    //   394: ifnull +33 -> 427
    //   397: aload 9
    //   399: ifnull +23 -> 422
    //   402: aload 8
    //   404: invokevirtual 40	java/io/ByteArrayOutputStream:close	()V
    //   407: goto +20 -> 427
    //   410: astore 23
    //   412: aload 9
    //   414: aload 23
    //   416: invokevirtual 37	java/lang/Throwable:addSuppressed	(Ljava/lang/Throwable;)V
    //   419: goto +8 -> 427
    //   422: aload 8
    //   424: invokevirtual 40	java/io/ByteArrayOutputStream:close	()V
    //   427: aload 6
    //   429: ifnull +33 -> 462
    //   432: aload 7
    //   434: ifnull +23 -> 457
    //   437: aload 6
    //   439: invokevirtual 35	java/io/DataInputStream:close	()V
    //   442: goto +20 -> 462
    //   445: astore 23
    //   447: aload 7
    //   449: aload 23
    //   451: invokevirtual 37	java/lang/Throwable:addSuppressed	(Ljava/lang/Throwable;)V
    //   454: goto +8 -> 462
    //   457: aload 6
    //   459: invokevirtual 35	java/io/DataInputStream:close	()V
    //   462: aload 4
    //   464: ifnull +33 -> 497
    //   467: aload 5
    //   469: ifnull +23 -> 492
    //   472: aload 4
    //   474: invokevirtual 39	java/io/DataOutputStream:close	()V
    //   477: goto +20 -> 497
    //   480: astore 23
    //   482: aload 5
    //   484: aload 23
    //   486: invokevirtual 37	java/lang/Throwable:addSuppressed	(Ljava/lang/Throwable;)V
    //   489: goto +8 -> 497
    //   492: aload 4
    //   494: invokevirtual 39	java/io/DataOutputStream:close	()V
    //   497: aload_2
    //   498: ifnull +29 -> 527
    //   501: aload_3
    //   502: ifnull +21 -> 523
    //   505: aload_2
    //   506: invokevirtual 41	java/net/Socket:close	()V
    //   509: goto +18 -> 527
    //   512: astore 23
    //   514: aload_3
    //   515: aload 23
    //   517: invokevirtual 37	java/lang/Throwable:addSuppressed	(Ljava/lang/Throwable;)V
    //   520: goto +7 -> 527
    //   523: aload_2
    //   524: invokevirtual 41	java/net/Socket:close	()V
    //   527: aload 22
    //   529: areturn
    //   530: astore 18
    //   532: aload 18
    //   534: astore 17
    //   536: aload 18
    //   538: athrow
    //   539: astore 24
    //   541: aload 16
    //   543: ifnull +33 -> 576
    //   546: aload 17
    //   548: ifnull +23 -> 571
    //   551: aload 16
    //   553: invokevirtual 35	java/io/DataInputStream:close	()V
    //   556: goto +20 -> 576
    //   559: astore 25
    //   561: aload 17
    //   563: aload 25
    //   565: invokevirtual 37	java/lang/Throwable:addSuppressed	(Ljava/lang/Throwable;)V
    //   568: goto +8 -> 576
    //   571: aload 16
    //   573: invokevirtual 35	java/io/DataInputStream:close	()V
    //   576: aload 24
    //   578: athrow
    //   579: astore 16
    //   581: aload 16
    //   583: astore 15
    //   585: aload 16
    //   587: athrow
    //   588: astore 26
    //   590: aload 14
    //   592: ifnull +33 -> 625
    //   595: aload 15
    //   597: ifnull +23 -> 620
    //   600: aload 14
    //   602: invokevirtual 38	java/io/ByteArrayInputStream:close	()V
    //   605: goto +20 -> 625
    //   608: astore 27
    //   610: aload 15
    //   612: aload 27
    //   614: invokevirtual 37	java/lang/Throwable:addSuppressed	(Ljava/lang/Throwable;)V
    //   617: goto +8 -> 625
    //   620: aload 14
    //   622: invokevirtual 38	java/io/ByteArrayInputStream:close	()V
    //   625: aload 26
    //   627: athrow
    //   628: astore 12
    //   630: aload 12
    //   632: astore 11
    //   634: aload 12
    //   636: athrow
    //   637: astore 28
    //   639: aload 10
    //   641: ifnull +33 -> 674
    //   644: aload 11
    //   646: ifnull +23 -> 669
    //   649: aload 10
    //   651: invokevirtual 39	java/io/DataOutputStream:close	()V
    //   654: goto +20 -> 674
    //   657: astore 29
    //   659: aload 11
    //   661: aload 29
    //   663: invokevirtual 37	java/lang/Throwable:addSuppressed	(Ljava/lang/Throwable;)V
    //   666: goto +8 -> 674
    //   669: aload 10
    //   671: invokevirtual 39	java/io/DataOutputStream:close	()V
    //   674: aload 28
    //   676: athrow
    //   677: astore 10
    //   679: aload 10
    //   681: astore 9
    //   683: aload 10
    //   685: athrow
    //   686: astore 30
    //   688: aload 8
    //   690: ifnull +33 -> 723
    //   693: aload 9
    //   695: ifnull +23 -> 718
    //   698: aload 8
    //   700: invokevirtual 40	java/io/ByteArrayOutputStream:close	()V
    //   703: goto +20 -> 723
    //   706: astore 31
    //   708: aload 9
    //   710: aload 31
    //   712: invokevirtual 37	java/lang/Throwable:addSuppressed	(Ljava/lang/Throwable;)V
    //   715: goto +8 -> 723
    //   718: aload 8
    //   720: invokevirtual 40	java/io/ByteArrayOutputStream:close	()V
    //   723: aload 30
    //   725: athrow
    //   726: astore 8
    //   728: aload 8
    //   730: astore 7
    //   732: aload 8
    //   734: athrow
    //   735: astore 32
    //   737: aload 6
    //   739: ifnull +33 -> 772
    //   742: aload 7
    //   744: ifnull +23 -> 767
    //   747: aload 6
    //   749: invokevirtual 35	java/io/DataInputStream:close	()V
    //   752: goto +20 -> 772
    //   755: astore 33
    //   757: aload 7
    //   759: aload 33
    //   761: invokevirtual 37	java/lang/Throwable:addSuppressed	(Ljava/lang/Throwable;)V
    //   764: goto +8 -> 772
    //   767: aload 6
    //   769: invokevirtual 35	java/io/DataInputStream:close	()V
    //   772: aload 32
    //   774: athrow
    //   775: astore 6
    //   777: aload 6
    //   779: astore 5
    //   781: aload 6
    //   783: athrow
    //   784: astore 34
    //   786: aload 4
    //   788: ifnull +33 -> 821
    //   791: aload 5
    //   793: ifnull +23 -> 816
    //   796: aload 4
    //   798: invokevirtual 39	java/io/DataOutputStream:close	()V
    //   801: goto +20 -> 821
    //   804: astore 35
    //   806: aload 5
    //   808: aload 35
    //   810: invokevirtual 37	java/lang/Throwable:addSuppressed	(Ljava/lang/Throwable;)V
    //   813: goto +8 -> 821
    //   816: aload 4
    //   818: invokevirtual 39	java/io/DataOutputStream:close	()V
    //   821: aload 34
    //   823: athrow
    //   824: astore 4
    //   826: aload 4
    //   828: astore_3
    //   829: aload 4
    //   831: athrow
    //   832: astore 36
    //   834: aload_2
    //   835: ifnull +29 -> 864
    //   838: aload_3
    //   839: ifnull +21 -> 860
    //   842: aload_2
    //   843: invokevirtual 41	java/net/Socket:close	()V
    //   846: goto +18 -> 864
    //   849: astore 37
    //   851: aload_3
    //   852: aload 37
    //   854: invokevirtual 37	java/lang/Throwable:addSuppressed	(Ljava/lang/Throwable;)V
    //   857: goto +7 -> 864
    //   860: aload_2
    //   861: invokevirtual 41	java/net/Socket:close	()V
    //   864: aload 36
    //   866: athrow
    //   867: astore_2
    //   868: iconst_2
    //   869: newarray <illegal type>
    //   871: dup
    //   872: iconst_0
    //   873: iconst_m1
    //   874: iastore
    //   875: dup
    //   876: iconst_1
    //   877: iconst_m1
    //   878: iastore
    //   879: areturn
    // Line number table:
    //   Java source line #18	-> byte code offset #0
    //   Java source line #19	-> byte code offset #12
    //   Java source line #20	-> byte code offset #28
    //   Java source line #21	-> byte code offset #44
    //   Java source line #22	-> byte code offset #56
    //   Java source line #25	-> byte code offset #70
    //   Java source line #26	-> byte code offset #76
    //   Java source line #27	-> byte code offset #82
    //   Java source line #28	-> byte code offset #88
    //   Java source line #29	-> byte code offset #94
    //   Java source line #31	-> byte code offset #100
    //   Java source line #32	-> byte code offset #110
    //   Java source line #33	-> byte code offset #117
    //   Java source line #36	-> byte code offset #122
    //   Java source line #38	-> byte code offset #128
    //   Java source line #39	-> byte code offset #138
    //   Java source line #40	-> byte code offset #145
    //   Java source line #42	-> byte code offset #150
    //   Java source line #43	-> byte code offset #157
    //   Java source line #44	-> byte code offset #163
    //   Java source line #46	-> byte code offset #170
    //   Java source line #47	-> byte code offset #184
    //   Java source line #48	-> byte code offset #198
    //   Java source line #49	-> byte code offset #205
    //   Java source line #50	-> byte code offset #210
    //   Java source line #53	-> byte code offset #220
    //   Java source line #54	-> byte code offset #229
    //   Java source line #56	-> byte code offset #244
    //   Java source line #58	-> byte code offset #256
    //   Java source line #59	-> byte code offset #287
    //   Java source line #60	-> byte code offset #322
    //   Java source line #61	-> byte code offset #357
    //   Java source line #62	-> byte code offset #392
    //   Java source line #63	-> byte code offset #427
    //   Java source line #64	-> byte code offset #462
    //   Java source line #65	-> byte code offset #497
    //   Java source line #47	-> byte code offset #530
    //   Java source line #59	-> byte code offset #539
    //   Java source line #46	-> byte code offset #579
    //   Java source line #60	-> byte code offset #588
    //   Java source line #22	-> byte code offset #628
    //   Java source line #61	-> byte code offset #637
    //   Java source line #21	-> byte code offset #677
    //   Java source line #62	-> byte code offset #686
    //   Java source line #20	-> byte code offset #726
    //   Java source line #63	-> byte code offset #735
    //   Java source line #19	-> byte code offset #775
    //   Java source line #64	-> byte code offset #784
    //   Java source line #18	-> byte code offset #824
    //   Java source line #65	-> byte code offset #832
    //   Java source line #66	-> byte code offset #867
    //   Java source line #67	-> byte code offset #868
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	880	0	host	String
    //   0	880	1	port	int
    //   9	852	2	socket	java.net.Socket
    //   867	2	2	e	Exception
    //   11	841	3	localThrowable21	Throwable
    //   23	794	4	out	java.io.DataOutputStream
    //   824	6	4	localThrowable19	Throwable
    //   26	781	5	localThrowable22	Throwable
    //   39	729	6	in	java.io.DataInputStream
    //   775	7	6	localThrowable17	Throwable
    //   42	716	7	localThrowable23	Throwable
    //   51	668	8	frame	java.io.ByteArrayOutputStream
    //   726	7	8	localThrowable15	Throwable
    //   54	655	9	localThrowable24	Throwable
    //   65	605	10	frameOut	java.io.DataOutputStream
    //   677	7	10	localThrowable13	Throwable
    //   68	592	11	localThrowable25	Throwable
    //   155	3	12	len	int
    //   628	7	12	localThrowable11	Throwable
    //   161	14	13	packet	byte[]
    //   179	442	14	inPacket	java.io.ByteArrayInputStream
    //   182	429	15	localThrowable26	Throwable
    //   193	379	16	inFrame	java.io.DataInputStream
    //   579	7	16	localThrowable9	Throwable
    //   196	366	17	localThrowable27	Throwable
    //   203	3	18	id	int
    //   530	7	18	localThrowable7	Throwable
    //   227	3	19	parser	net.minecraft.util.com.google.gson.JsonParser
    //   242	3	20	jsonObject	net.minecraft.util.com.google.gson.JsonObject
    //   254	21	21	jsonPlayers	net.minecraft.util.com.google.gson.JsonObject
    //   285	243	22	arrayOfInt	int[]
    //   305	5	23	localThrowable	Throwable
    //   340	5	23	localThrowable1	Throwable
    //   375	5	23	localThrowable2	Throwable
    //   410	5	23	localThrowable3	Throwable
    //   445	5	23	localThrowable4	Throwable
    //   480	5	23	localThrowable5	Throwable
    //   512	4	23	localThrowable6	Throwable
    //   539	38	24	localObject1	Object
    //   559	5	25	localThrowable8	Throwable
    //   588	38	26	localObject2	Object
    //   608	5	27	localThrowable10	Throwable
    //   637	38	28	localObject3	Object
    //   657	5	29	localThrowable12	Throwable
    //   686	38	30	localObject4	Object
    //   706	5	31	localThrowable14	Throwable
    //   735	38	32	localObject5	Object
    //   755	5	33	localThrowable16	Throwable
    //   784	38	34	localObject6	Object
    //   804	5	35	localThrowable18	Throwable
    //   832	33	36	localObject7	Object
    //   849	4	37	localThrowable20	Throwable
    // Exception table:
    //   from	to	target	type
    //   297	302	305	java/lang/Throwable
    //   332	337	340	java/lang/Throwable
    //   367	372	375	java/lang/Throwable
    //   402	407	410	java/lang/Throwable
    //   437	442	445	java/lang/Throwable
    //   472	477	480	java/lang/Throwable
    //   505	509	512	java/lang/Throwable
    //   198	287	530	java/lang/Throwable
    //   198	287	539	finally
    //   530	541	539	finally
    //   551	556	559	java/lang/Throwable
    //   184	322	579	java/lang/Throwable
    //   530	579	579	java/lang/Throwable
    //   184	322	588	finally
    //   530	590	588	finally
    //   600	605	608	java/lang/Throwable
    //   70	357	628	java/lang/Throwable
    //   530	628	628	java/lang/Throwable
    //   70	357	637	finally
    //   530	639	637	finally
    //   649	654	657	java/lang/Throwable
    //   56	392	677	java/lang/Throwable
    //   530	677	677	java/lang/Throwable
    //   56	392	686	finally
    //   530	688	686	finally
    //   698	703	706	java/lang/Throwable
    //   44	427	726	java/lang/Throwable
    //   530	726	726	java/lang/Throwable
    //   44	427	735	finally
    //   530	737	735	finally
    //   747	752	755	java/lang/Throwable
    //   28	462	775	java/lang/Throwable
    //   530	775	775	java/lang/Throwable
    //   28	462	784	finally
    //   530	786	784	finally
    //   796	801	804	java/lang/Throwable
    //   12	497	824	java/lang/Throwable
    //   530	824	824	java/lang/Throwable
    //   12	497	832	finally
    //   530	834	832	finally
    //   842	846	849	java/lang/Throwable
    //   0	527	867	java/lang/Exception
    //   530	867	867	java/lang/Exception
  }
  
  public static void writeString(String s, DataOutput out)
    throws IOException
  {
    byte[] b = s.getBytes("UTF-8");
    writeVarInt(b.length, out);
    out.write(b);
  }
  
  public static String readString(DataInput in)
    throws IOException
  {
    int len = readVarInt(in);
    byte[] b = new byte[len];
    in.readFully(b);
    
    return new String(b, "UTF-8");
  }
  
  public static int readVarInt(DataInput input)
    throws IOException
  {
    int out = 0;
    int bytes = 0;
    for (;;)
    {
      byte in = input.readByte();
      
      out |= (in & 0x7F) << bytes++ * 7;
      if (bytes > 32) {
        throw new RuntimeException("VarInt too big");
      }
      if ((in & 0x80) != 128) {
        break;
      }
    }
    return out;
  }
  
  public static void writeVarInt(int value, DataOutput output)
    throws IOException
  {
    for (;;)
    {
      int part = value & 0x7F;
      
      value >>>= 7;
      if (value != 0) {
        part |= 0x80;
      }
      output.writeByte(part);
      if (value == 0) {
        break;
      }
    }
  }
}
