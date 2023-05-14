
package Model;

import java.nio.charset.StandardCharsets;

public class TestEncryption {

    public static void main(String[] args) {
        String str = "";

        byte[] key = {
            0x74, 0x68, 0x69, 0x73, 0x49, 0x73, 0x41, 0x53,
            0x65, 0x63, 0x72, 0x65, 0x74, 0x4b, 0x65, 0x79
        // thisisasecretkey
        };
        
        String key_string = new String(key, StandardCharsets.UTF_8);

        System.out.println("Original Str: " + key_string);
        String encrypted_str = Security.encrypt(key_string);

        System.out.println("Encrypted version: " + encrypted_str);

        System.out.println("Decrypted version: " + Security.decrypt(encrypted_str));
    }
}

/*

       private static byte[] key = {'l','a','w','r','e','n','c','e',
                                     'd','e','c','a','m','o','r','a'};

Original Str: university of santo tomas
Encrypted version: +dcrgAbzQxC16Oogbp1W4efGxGjVyfgTWzx/AXWK5Uo=
Decrypted version: university of santo tomas
 */

 /*
Original Str: university of santo tomas
Encrypted version: X03O/bZKNujj5EHOnGB41TLCWUWlvxtbMA9dViDNg9o=
Decrypted version: university of santo tomas


 */
