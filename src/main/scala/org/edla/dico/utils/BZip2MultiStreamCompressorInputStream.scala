package org.edla.dico.utils

//https://github.com/lzenczuk/akka-app-one/blob/ebfcd19704e9fcad129db982112e7aa75f4ae5a6/src/main/scala/com/github/lzenczuk/akka/course/streams/BZip2MultiStreamCompressorInputStream.scala
import java.io.InputStream

import org.apache.commons.compress.compressors.CompressorInputStream
import org.apache.commons.compress.compressors.bzip2.BZip2CompressorInputStream

/**
  * Created by dev on 06/09/16.
  *
  * This class solving problem/bug in BZip2CompressorInputStream when file is compressed using multiple streams.
  * It based on code by William Woody from http://chaosinmotion.com/blog/?p=723
  */
class BZip2MultiStreamCompressorInputStream(inputStream: InputStream)
    extends CompressorInputStream {

  private var bZip2CompressorInputStream: BZip2CompressorInputStream =
    new BZip2CompressorInputStream(inputStream)

  override def read(): Int = {
    var chunkSize: Int = bZip2CompressorInputStream.read()

    if (chunkSize == -1 && inputStream.available() > 0) {
      bZip2CompressorInputStream = new BZip2CompressorInputStream(inputStream)
      chunkSize = bZip2CompressorInputStream.read()
    }

    chunkSize
  }

  override def close(): Unit = {
    inputStream.close()
    bZip2CompressorInputStream.close()
  }
}
