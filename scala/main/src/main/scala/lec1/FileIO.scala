
package FileIO

  import scalaz.zio._

  object zio_resources {

    import java.io.{ File, FileInputStream }

    class InputStream private (is: FileInputStream) {
      def read: IO[Exception, Option[Byte]] =
        IO.effectTotal(is.read).map(i => if (i < 0) None else Some(i.toByte))
      def close: IO[Exception, Unit] =
        IO.effectTotal(is.close())
    }
    object InputStream {
      def openFile(file: File): IO[Exception, InputStream] =
        IO.effectTotal(new InputStream(new FileInputStream(file)))
    }

  }

