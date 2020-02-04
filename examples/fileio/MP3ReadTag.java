import java.io.*;

// Reads MP3 tags 
// See http://id3.org/id3v2.3.0 for MP3 tag spec
public class MP3ReadTag {
    public static void main(String[] args) throws IOException {
        if (args.length != 1) {
            System.out.println("Usage: mp3readtag <mp3filename>");
            System.exit(1);
        }

        String filename = args[0];
        System.out.println("Reading " + filename);

        try (var br = new FileInputStream(filename)) {
            byte[] tag = new byte[3];
            byte[] version = new byte[2];
            byte[] flags = new byte[1];
            byte[] size = new byte[4];

            // Read Tag Header

            // ID3v2/file identifier "ID3"
            // ID3v2 version $03 00
            // ID3v2 flags % abc00000
            // ID3v2 size 4 * % 0xxxxxxx

            br.read(tag);
            br.read(version);
            br.read(flags);
            br.read(size);

            String fileIdentifier = bytesToString(tag);
            if (!fileIdentifier.equals("ID3")) {
                System.out.println(filename + " does not contain ID3 tags.");
                System.exit(1);
            }

            int frameNo = 0;
            int bytesToRead = bytesToSize(size);
            while (bytesToRead > 0) {
                // Read Frame Header

                // Frame ID $xx xx xx xx (four characters)
                // Size $xx xx xx xx
                // Flags $xx xx

                byte[] frameIdBytes = new byte[4];
                byte[] frameSizeBytes = new byte[4];
                byte[] frameFlags = new byte[2];
                br.read(frameIdBytes);
                br.read(frameSizeBytes);
                br.read(frameFlags);

                int frameSize = bytesToSize(frameSizeBytes);
                System.out.println("Frame #" + frameNo);
                System.out.println("Frame Data Size : " + frameSize);

                String frameId = bytesToString(frameIdBytes);
                System.out.println("Frame ID: " + frameId);

                // Read Frame Body

                if (frameId.charAt(0) == 'T') {
                    // a text frame
                    int encoding = br.read();

                    // Ignore encoding and hope for the best(!)

                    byte[] body = new byte[frameSize - 1];
                    br.read(body);
                    System.out.println("Data: " + bytesToString(body));
                } else {
                    // some other type of frame
                    byte[] body = new byte[frameSize];
                    br.read(body);

                }
                System.out.println("------------------------------------");

                bytesToRead -= frameSize + 10;
                ++frameNo;
            }

        }

    }

    // Converts an MP3 length field to int
    // "The ID3v2 tag size is encoded with four bytes where
    // the most significant bit (bit 7) is set to zero in every byte, making a total
    // of 28 bits."
    // See http://id3.org/id3v2.3.0#ID3v2_header

    private static int bytesToSize(byte[] sizeBytes) {
        return (sizeBytes[0] & 0xff) << 21 | (sizeBytes[1] & 0xff) << 14 | (sizeBytes[2] & 0xff) << 7
                | (sizeBytes[3] & 0xff);
    }

    // Converts an array of bytes to a String
    private static String bytesToString(byte[] data) {
        return new String(data);
    }

}
