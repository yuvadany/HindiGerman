package com.deutschbible.hindibible;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by user on 5/28/2018.
 */
public class BooksChapters {

    public static int  getChaptersCount(int book)
    {
        HashMap<Integer,Integer> booksChapters = new HashMap<Integer,Integer>();
        booksChapters.put(	1 , 50	);
        booksChapters.put(	2 , 40	);
        booksChapters.put(	3 , 27	);
        booksChapters.put(	4 , 36	);
        booksChapters.put(	5 , 34	);
        booksChapters.put(	6 , 24	);
        booksChapters.put(	7 , 21	);
        booksChapters.put(	8 , 4	);
        booksChapters.put(	9 , 31	);
        booksChapters.put(	10 , 24	);
        booksChapters.put(	11 , 22	);
        booksChapters.put(	12 , 25	);
        booksChapters.put(	13 , 29	);
        booksChapters.put(	14 , 36	);
        booksChapters.put(	15 , 10	);
        booksChapters.put(	16 , 13	);
        booksChapters.put(	17 , 10	);
        booksChapters.put(	18 , 42	);
        booksChapters.put(	19 , 150);
        booksChapters.put(	20 , 31	);
        booksChapters.put(	21 , 12	);
        booksChapters.put(	22 , 8	);
        booksChapters.put(	23 , 66	);
        booksChapters.put(	24 , 52	);
        booksChapters.put(	25 , 5	);
        booksChapters.put(	26 , 48	);
        booksChapters.put(	27 , 12	);
        booksChapters.put(	28 , 14	);
        booksChapters.put(	29 , 3	);
        booksChapters.put(	30 , 9	);
        booksChapters.put(	31 , 1	);
        booksChapters.put(	32 , 4	);
        booksChapters.put(	33 , 7	);
        booksChapters.put(	34 , 3	);
        booksChapters.put(	35 , 3	);
        booksChapters.put(	36 , 3	);
        booksChapters.put(	37 , 2	);
        booksChapters.put(	38 , 14	);
        booksChapters.put(	39 , 4	);
        booksChapters.put(	40 , 28	);
        booksChapters.put(	41 , 16	);
        booksChapters.put(	42 , 24	);
        booksChapters.put(	43 , 21	);
        booksChapters.put(	44 , 28	);
        booksChapters.put(	45 , 16	);
        booksChapters.put(	46 , 16	);
        booksChapters.put(	47 , 13	);
        booksChapters.put(	48 , 6	);
        booksChapters.put(	49 , 6	);
        booksChapters.put(	50 , 4	);
        booksChapters.put(	51 , 4	);
        booksChapters.put(	52 , 5	);
        booksChapters.put(	53 , 3	);
        booksChapters.put(	54 , 6	);
        booksChapters.put(	55 , 4	);
        booksChapters.put(	56 , 3	);
        booksChapters.put(	57 , 1	);
        booksChapters.put(	58 , 13	);
        booksChapters.put(	59 , 5	);
        booksChapters.put(	60 , 5	);
        booksChapters.put(	61 , 3	);
        booksChapters.put(	62 , 5	);
        booksChapters.put(	63 , 1	);
        booksChapters.put(	64 , 1	);
        booksChapters.put(	65 , 1	);
        booksChapters.put(	66 , 22	);
        return booksChapters.get(book);
    }

    public static String getBookName(int booknumber)
    {
        Map<Integer,String> bookName = new HashMap<Integer,String>();
        bookName.put(1 ,"उत्पत्ति-Genesis" );
        bookName.put(2 ,"निर्गमन-Exodus" );
        bookName.put(3 ,"लैव्यव्यवस्था-Levitikus");
        bookName.put(4 ,"गिनती--Numeri" );
        bookName.put(5 ,"व्यवस्थाविवरण-Deuteronomium" );
        bookName.put(6 ,"यहोशू-Josua" );
        bookName.put(7 ,"न्यायियों-Richter" );
        bookName.put(8 ,"रूत-Rut" );
        bookName.put(9 ,"1 शमूएल-1.Samuel" );
        bookName.put(10 ,"2 शमूएल-2.Samuel" );
        bookName.put(11 ,"1 राजा-1.Könige" );
        bookName.put(12 ,"2 राजा-2.Könige" );
        bookName.put(13 ,"1 इतिहास-1.Chronik" );
        bookName.put(14 ,"2 इतिहास-2.Chronik" );
        bookName.put(15 ,"एज्रा-Esra" );
        bookName.put(16 ,"नहेमायाह-Nehemia" );
        bookName.put(17 ,"एस्तेर-Ester" );
        bookName.put(18 ,"अय्यूब-Hiob" );
        bookName.put(19 ,"भजन संहिता-Psalter" );
        bookName.put(20 ,"नीतिवचन-Sprüche" );
        bookName.put(21 ,"सभोपदेशक-Prediger" );
        bookName.put(22 ,"श्रेष्ठगीत-Hohelied" );
        bookName.put(23 ,"यशायाह-Jesaja" );
        bookName.put(24 ,"यिर्मयाह-Jeremia" );
        bookName.put(25 ,"विलापगीत-Klagelieder" );
        bookName.put(26 ,"यहेजकेल-Hesekiel" );
        bookName.put(27 ,"दानिय्येल-Daniel" );
        bookName.put(28 ,"होशे-Hosea" );
        bookName.put(29 ,"योएल-Joel" );
        bookName.put(30 ,"आमोस-Amos" );
        bookName.put(31 ,"ओबद्दाह-Obadja" );
        bookName.put(32 ,"योना-Jona" );
        bookName.put(33 ,"मीका-Micha" );
        bookName.put(34 ,"नहूम-Nahum" );
        bookName.put(35 ,"हबक्कूक-Habakuk" );
        bookName.put(36 ,"सपन्याह-Zefanja" );
        bookName.put(37 ,"हाग्गै-Haggai" );
        bookName.put(38 ,"जकर्याह-Sacharja" );
        bookName.put(39 ,"मलाकी-Maleachi" );
        bookName.put(40 ,"मत्ती-Matthäus" );
        bookName.put(41 ,"मरकुस-Markus" );
        bookName.put(42 ,"लूका-Lukas" );
        bookName.put(43 ,"यूहन्ना-Johannes" );
        bookName.put(44 ,"प्रेरितों के काम-Apostelgeschichte" );
        bookName.put(45 ,"रोमियो-Römer" );
        bookName.put(46 ,"1 कुरिन्थियों- 1.Korinther" );
        bookName.put(47 ,"2 कुरिन्थियों -2.Korinther" );
        bookName.put(48 ,"गलातियों -Galater" );
        bookName.put(49 ,"इफिसियों -Epheser" );
        bookName.put(50 ,"फिलिप्पियों-Philipper" );
        bookName.put(51 ,"कुलुस्सियों-Kolosser" );
        bookName.put(52 ,"1 थिस्सलुनीकियों-1.Thessalonicher" );
        bookName.put(53 ,"2 थिस्सलुनीकियो -2.Thessalonicher" );
        bookName.put(54 ,"1 तीमुथियुस -1.Timotheus" );
        bookName.put(55 ,"2 तीमुथियुस -2.Timotheus" );
        bookName.put(56 ,"तीतुस - Titus" );
        bookName.put(57 ,"फिलेमोन - Philemon" );
        bookName.put(58 ,"इब्रानियों - Hebräer" );
        bookName.put(59 ,"याकूब - Jakobus" );
        bookName.put(60 ,"1 पतरस - 1.Petrus" );
        bookName.put(61 ,"2 पतरस - 2.Petrus" );
        bookName.put(62 ,"1 यूहन्ना- 1.Johannes" );
        bookName.put(63 ,"2 यूहन्ना- 2.Johannes" );
        bookName.put(64 ,"3 यूहन्ना-3.Johannes" );;
        bookName.put(65 ,"यहूदा-Judas" );
        bookName.put(66 ,"प्रकाशित वाक्य-Offenbarung" );
        return bookName.get(booknumber);

    }

}
